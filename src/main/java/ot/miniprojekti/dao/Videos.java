package ot.miniprojekti.dao;

import ot.miniprojekti.domain.Video;

import java.sql.*;
import java.util.ArrayList;

public class Videos {

    private Connection db;

    public Videos(String data) throws SQLException {
        db = DriverManager.getConnection("jdbc:sqlite:" + data);
        try {
            PreparedStatement stmt = db.prepareStatement("CREATE TABLE IF NOT EXISTS videos \n"
                    + "(id SERIAL PRIMARY KEY, title TEXT, url TEXT, comment TEXT)");
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void add(String title, String url, String comment) throws SQLException {
        PreparedStatement stmt = db.prepareStatement("INSERT INTO videos (title, url, comment) VALUES (?, ?, ?)");
        stmt.setString(2, title);
        stmt.setString(1, url);
        stmt.setString(3, comment);
        stmt.executeUpdate();
        stmt.close();
    }

    public ArrayList<Video> getAll() throws SQLException {
        ArrayList<Video> videos = new ArrayList<Video>();

        PreparedStatement stmt = db.prepareStatement("SELECT * FROM videos");
        ResultSet result = stmt.executeQuery();

        while (result.next()) {
            int id = result.getInt("id");
            String title = result.getString("title");
            String url = result.getString("url");
            String comment = result.getString("comment");
            videos.add(new Video(id, title, url, comment));
        }
        db.close();

        return videos;
    }
    
    public void dropTable() {
        try {
            PreparedStatement stmt = db.prepareStatement("DROP TABLE videos");
            stmt.executeQuery();
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
