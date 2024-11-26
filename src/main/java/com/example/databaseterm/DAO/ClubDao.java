package com.example.databaseterm.DAO;
import com.example.databaseterm.Model.ClubModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.databaseterm.JDBC;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ClubDao {

    @Autowired
    private JDBC jdbc;

    // 클럽 저장
    public void saveClub(ClubModel clubModel) {
        String query = "INSERT INTO Club (ClubName, Professor, INTRO) VALUES (?, ?, ?)";
        try (Connection con = jdbc.getCon();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, clubModel.getClubname());
            stmt.setString(2, clubModel.getProfessor());
            stmt.setString(3, clubModel.getIntro());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save club", e);
        }
    }

    // 클럽 삭제
    public void deleteByName(String clubName) {
        String query = "DELETE FROM Club WHERE ClubName = ?";
        try (Connection con = jdbc.getCon();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, clubName);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete club by name", e);
        }
    }

    // 멤버 수 증가
    public void incrementMemberCount(String clubName) {
        String sql = "UPDATE Club SET NumberofMember = NumberofMember + 1 WHERE ClubName = ?";
        try (Connection con = jdbc.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, clubName);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to increment member count", e);
        }
    }

    // 멤버 수 감소
    public void decrementMemberCount(String clubName) {
        String sql = "UPDATE Club SET NumberofMember = NumberofMember - 1 WHERE ClubName = ?";
        try (Connection con = jdbc.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, clubName);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to decrement member count", e);
        }
    }

    // 클럽 조회
    public ClubModel findClubByName(String clubName) {
        String sql = "SELECT * FROM Club WHERE ClubName = ?";
        try (Connection con = jdbc.getCon();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, clubName);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    ClubModel club = new ClubModel();
                    club.setClubname(rs.getString("ClubName"));
                    club.setProfessor(rs.getString("Professor"));
                    club.setIntro(rs.getString("INTRO"));
                    club.setNumberofmember(rs.getInt("NumberofMember"));
                    return club;
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find club by name", e);
        }
    }
}
