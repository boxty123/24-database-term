package com.example.databaseterm.DAO;


import com.example.databaseterm.JDBC;
import com.example.databaseterm.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class UserDao {

    private JDBC jdbc;


    public void saveUser(UserModel userModel){
        String query="insert into User (SN,Dept) values (?,?)";
        try(Connection con= jdbc.getCon();
            PreparedStatement stmt=con.prepareStatement(query)) {
            stmt.setInt(1,userModel.getSn());
            stmt.setString(2,userModel.getDept());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteBySn(int sn) {
        String query="delete from User where SN=?";
        try(Connection con= jdbc.getCon();
            PreparedStatement stmt=con.prepareStatement(query)) {
            stmt.setInt(1,sn);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
