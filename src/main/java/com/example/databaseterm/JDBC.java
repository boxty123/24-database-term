package com.example.databaseterm;
import org.springframework.stereotype.Component;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//JdbcTemplate로 인한 불필요한 클래스.
@Component
public class JDBC {

    private Connection con;

    public Connection getCon() throws SQLException {

        if(con==null || con.isClosed())
            con=DriverManager.getConnection("192.168.111.131:4567/mydb","leejeonghyeon","password");
        return con;
    }

    public void closeCon(){
        try{
            if(con!=null&&!con.isClosed())
                con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
