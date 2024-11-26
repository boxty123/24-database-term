package com.example.databaseterm.DAO;

import com.example.databaseterm.JDBC;
import com.example.databaseterm.Model.JoinModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class JoinDao {

    @Autowired
    private JDBC jdbc;

    @Autowired
    private ClubDao clubDao;
    
    public void joinClub(JoinModel joinModel) {
        String query = "INSERT INTO Join (SN, CN, Role) VALUES (?, ?, ?)";

        try (Connection con = jdbc.getCon()) {

            con.setAutoCommit(false);

            try (PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setInt(1, joinModel.getSn());
                stmt.setString(2, joinModel.getCn());
                stmt.setBoolean(3, joinModel.isRole());
                stmt.executeUpdate();

                clubDao.incrementMemberCount(joinModel.getCn());

                con.commit();
            } catch (SQLException e) {

                con.rollback();
                throw new RuntimeException("Failed to join the club and update member count", e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }
    public void withdrawClub(int sn, String cn) {
        String query = "DELETE FROM Join WHERE SN = ? AND CN = ?";

        try (Connection con = jdbc.getCon()) {
            con.setAutoCommit(false);

            try (PreparedStatement stmt = con.prepareStatement(query)) {

                stmt.setInt(1, sn);
                stmt.setString(2, cn);
                stmt.executeUpdate();

                clubDao.decrementMemberCount(cn);

                con.commit();
            } catch (SQLException e) {
                con.rollback();
                throw new RuntimeException("Failed to withdraw from the club and update member count", e);
            } finally {
                con.setAutoCommit(true);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database connection error", e);
        }
    }


    public int countBySN(int sn) {
        String query = "SELECT COUNT(*) FROM Join WHERE SN = ?";
        try (Connection con = jdbc.getCon();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, sn);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to count clubs by SN", e);
        }
        return 0;
    }

}
