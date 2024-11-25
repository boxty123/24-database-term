package com.example.databaseterm.DAO;
import com.example.databaseterm.Model.ClubModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClubDao {

    @Autowired
    private JdbcTemplate jdbc;

    public void saveClub(ClubModel clubModel){
        String query="insert into Club (ClubName,Professor,INTRO) values (?,?,?)";
        jdbc.update(query,clubModel.getClubname(),clubModel.getProfessor(),clubModel.getIntro());
    }

    public void deleteByName(String clubName) {
        String query="delete from Club where ClubName=?";
        jdbc.update(query,clubName);
    }
    public void incrementMemberCount(String clubName) {
        String sql = "UPDATE Club SET NumberofMember = NumberofMember + 1 WHERE ClubName = ?";
        jdbc.update(sql, clubName);
    }

    public void decrementMemberCount(String clubName) {
        String sql = "UPDATE Club SET NumberofMember = NumberofMember - 1 WHERE ClubName = ?";
        jdbc.update(sql, clubName);
    }

    /* 클럽 조회
    public ClubModel findClubByName(String clubName) {
        String sql = "SELECT * FROM Club WHERE ClubName = ?";

    }*/
}
