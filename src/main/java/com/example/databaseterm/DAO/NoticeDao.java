package com.example.databaseterm.DAO;
import com.example.databaseterm.JDBC;
import com.example.databaseterm.Model.NoticeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class NoticeDao {

    @Autowired
    private JDBC jdbc;

    public void saveNotice(NoticeModel noticeModel) {
        String query = "INSERT INTO Notice (title, content, date, SN, CN) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = jdbc.getCon();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, noticeModel.getTitle());
            stmt.setString(2, noticeModel.getContent());
            stmt.setDate(3, new Date(noticeModel.getDate().getTime()));
            stmt.setInt(4, noticeModel.getSn());
            stmt.setString(5, noticeModel.getCn()); 

            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to save notice", e);
        }
    }

    public void deleteNoticeByTitle(String title) {
        String query = "DELETE FROM Notice WHERE NID = ?";
        try (Connection con = jdbc.getCon();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, title);
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete notice by title", e);
        }
    }

    public List<NoticeModel> findNoticesByClub(String cn) {
        String query = "SELECT title, content, date FROM Notice WHERE CN = ?";
        List<NoticeModel> notices = new ArrayList<>();

        try (Connection con = jdbc.getCon();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setString(1, cn);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    NoticeModel notice = new NoticeModel();
                    notice.setTitle(rs.getString("title"));
                    notice.setContent(rs.getString("content"));
                    notice.setDate(rs.getDate("date"));

                    notices.add(notice);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find notices by club", e);
        }

        return notices;
    }

    public NoticeModel findNoticeById(int nid) {
        String query = "SELECT title, content, date FROM Notice WHERE NID = ?";
        try (Connection con = jdbc.getCon();
             PreparedStatement stmt = con.prepareStatement(query)) {

            stmt.setInt(1, nid);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    NoticeModel notice = new NoticeModel();
                    notice.setTitle(rs.getString("title"));
                    notice.setContent(rs.getString("content"));
                    notice.setDate(rs.getDate("date")); 
                    return notice;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to find notice by ID", e);
        }

        return null; 
    }
}
