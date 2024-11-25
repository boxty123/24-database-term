package com.example.databaseterm.DAO;


import com.example.databaseterm.Model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jdbc;

    public void saveUser(UserModel userModel){
        String query="insert into User (SN,Dept) values (?,?)";
        jdbc.update(query,userModel.getSn(),userModel.getDept());
    }

    public void deleteBySn(int sn) {
        String query="delete from User where SN=?";
        jdbc.update(query,sn);
    }
}
