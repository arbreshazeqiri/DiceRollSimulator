package pdg.repositories;
import pdg.models.User;
import pdg.utils.DbHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    public static int count() throws Exception {
        Connection conn = DbHelper.getConnection();
        ResultSet res = conn.createStatement().executeQuery("SELECT COUNT(*) FROM user_account");
        res.next();
        return res.getInt(1);
    }

    public static List<User> getAll(int pageSize, int page) throws Exception {
        ArrayList<User> list = new ArrayList<>();

        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_account LIMIT ? OFFSET ?");
        stmt.setInt(1, pageSize);
        stmt.setInt(2, pageSize * page);
        ResultSet res = stmt.executeQuery();
        while (res.next()) {
            list.add(parseRes(res));
        }
        return list;
    }
    public static User find(String username) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_account WHERE username = ? LIMIT 1");
        stmt.setString(1, username);

        ResultSet res = stmt.executeQuery();
        if (!res.next()) return null;
        return parseReslogin(res);
    }

    public static boolean findByEmail(String email) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_account WHERE email = ? LIMIT 1");
        stmt.setString(1, email);

        ResultSet res = stmt.executeQuery();
        if (!res.next()) return false;
        return true;
    }

    public static boolean findByUsername(String username) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_account WHERE username = ? LIMIT 1");
        stmt.setString(1, username);

        ResultSet res = stmt.executeQuery();
        if (!res.next()) return false;
        return true;
    }

    public static User create(String username, String fullname, String email, String password, String salt, String country) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "INSERT INTO user_account (username, fullname, email, password, salt, country, numberOfWins, score) VALUES (?, ?, ?, ?, ?, ?,?, ?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, username);
        stmt.setString(2, fullname);
        stmt.setString(3, email);
        stmt.setString(4, password);
        stmt.setString(5, salt);
        stmt.setString(6, country);
        stmt.setInt(7, 0);
        stmt.setInt(8, 0);

        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        ResultSet res = conn.createStatement().executeQuery("SELECT * FROM user_account  LIMIT 1");
        res.next();
        return parseRes(res);
    }

    public static void update(User model) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "UPDATE user_account SET numberOfWins = ?, score = ? WHERE username = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, model.getNumberOfWins());
        stmt.setInt(2, model.getScore());
        stmt.setString(3, model.getUsername());


        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

    }

    public static boolean remove(String username) throws Exception {
        String query = "DELETE FROM user_account WHERE username = ?";
        PreparedStatement stmt = DbHelper.getConnection().prepareStatement(query);
        stmt.setString(1, username);
        return stmt.executeUpdate() == 1;
    }

    private static User parseRes(ResultSet res) throws Exception {
        String username = res.getString("username");
        String fullname = res.getString("fullname");
        String email = res.getString("email");
        String password = res.getString("password");
        String salt = res.getString("salt");
        String country = res.getString("country");

        return new User(
                username, fullname, email, password, salt, country
        );
    }

    private static User parseReslogin(ResultSet res) throws Exception {
        String username = res.getString("username");
        String fullname = res.getString("fullname");
        String email = res.getString("email");
        String password = res.getString("password");
        String salt = res.getString("salt");
        String country = res.getString("country");
        int numberOfWins = res.getInt("numberOfWins");
        int score = res.getInt("score");

        return new User(
                username, fullname, email, password, salt, country, numberOfWins, score
        );
    }
}