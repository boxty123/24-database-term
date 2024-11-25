package com.example.databaseterm.Service;
import com.example.databaseterm.DAO.ClubDao;
import com.example.databaseterm.Model.ClubModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ClubService {

    @Autowired
    private ClubDao clubDao;

    @PostMapping()
    public void saveClub(ClubModel clubModel){
        clubModel.setNumberofmember(1);
        clubDao.saveClub(clubModel);
    }

    public void deleteClub(String clubName){
        clubDao.deleteByName(clubName);
    }


}
