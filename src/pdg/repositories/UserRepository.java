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
        return parseRes(res);
    }
    public static User findByEmail(String email) throws Exception {
        Connection conn = DbHelper.getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM user_account WHERE email = ? LIMIT 1");
        stmt.setString(1, email);

        ResultSet res = stmt.executeQuery();
        if (!res.next()) return null;
        return parseRes(res);
    }

    public static User create(User model) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "INSERT INTO user_account (username, fullname, email, password, salt, country, numberOfWins) VALUES (?, ?, ?, ?, ?, ?,?)";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, model.getUsername());
        stmt.setString(2, model.getFullName());
        stmt.setString(3, model.getEmail());
        stmt.setString(4, model.getPassword());
        stmt.setString(5, model.getSalt());
        stmt.setString(6, model.getCountry());
        stmt.setInt(7,model.getNumberOfWins());

        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        ResultSet res = conn.createStatement().executeQuery("SELECT * FROM user_account  LIMIT 1");
        res.next();
        return parseRes(res);
    }

    public static User update(User model) throws Exception {
        Connection conn = DbHelper.getConnection();
        String query = "UPDATE user_account SET fullname = ? , email = ?, password = ?, salt = ?, country = ?, numberOfWins = ?, updatedAt = CURRENT_TIMESTAMP WHERE id = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, model.getFullName());
        stmt.setString(2, model.getEmail());
        stmt.setString(3, model.getPassword());
        stmt.setString(4, model.getSalt());
        stmt.setString(5, model.getCountry());
        stmt.setInt(6, model.getNumberOfWins());
        stmt.setString(7, model.getUsername());

        if (stmt.executeUpdate() != 1)
            throw new Exception("ERR_NO_ROW_CHANGE");

        return find(model.getUsername());
    }

    public static boolean remove(String username) throws Exception {
        String query = "DELETE FROM user_account WHERE username = ?";
        PreparedStatement stmt = DbHelper.getConnection().prepareStatement(query);
        stmt.setString(1, username);
        return stmt.executeUpdate() == 1;
    }

    private static User parseRes(ResultSet res) throws Exception {
        int id = res.getInt("id");
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
}
