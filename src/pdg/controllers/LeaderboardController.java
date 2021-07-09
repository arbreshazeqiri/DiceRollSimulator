package pdg.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pdg.models.User;
import pdg.repositories.UserRepository;
import pdg.utils.DbHelper;
import javafx.scene.control.TextField;
import pdg.utils.SessionManager;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class LeaderboardController extends ChildController {
    @FXML
    TextField p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
            c1, c2, c3, c4, c5, c6, c7, c8, c9, c10,
            s1, s2, s3, s4, s5, s6, s7, s8, s9, s10,
            w1, w2, w3, w4, w5, w6, w7, w8, w9, w10;

    ObservableList<User> users = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        leadCountry.setText("Country");
        leadPlayer.setText("Player");
        leadScore.setText("Score");
        leadWins.setText("Wins");

        TextField[] players = new TextField[]{p1, p2, p3, p4, p5, p6, p7, p8, p9, p10};
        TextField[] countries = new TextField[]{c1, c2, c3, c4, c5, c6, c7, c8, c9, c10};
        TextField[] scores = new TextField[]{s1, s2, s3, s4, s5, s6, s7, s8, s9, s10};
        TextField[] wins = new TextField[]{w1, w2, w3, w4, w5, w6, w7, w8, w9, w10};

        try {
            Connection conn = DbHelper.getConnection();
            ResultSet res = conn.createStatement().executeQuery("SELECT * FROM user_account order by numberOfWins desc, score desc");
            while (res.next()) {
                if (UserRepository.find(res.getString("username")) != null) {
                    users.add(UserRepository.find(res.getString("username")));
                }
            }
            for (int i = 0; i < 10; i++) {
                players[i].setText(users.get(i).getUsername());
                countries[i].setText(users.get(i).getCountry());
                scores[i].setText(users.get(i).getScore() + "");
                wins[i].setText(users.get(i).getNumberOfWins() + "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ChildController childController = null;
    @FXML
    private Label leadPlayer;
    @FXML
    private Label leadCountry;
    @FXML
    private Label leadScore;
    @FXML
    private Label leadWins;

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        String leaderboardPlayer = langBundle.getString("leaderboard_player");
        String leaderboardCountry = langBundle.getString("leaderboard_country");
        String leaderboardScore = langBundle.getString("leaderboard_score");
        String leaderboardWins = langBundle.getString("leaderboard_wins");

        leadPlayer.setText(leaderboardPlayer);
        leadCountry.setText(leaderboardCountry);
        leadWins.setText(leaderboardWins);
        leadScore.setText(leaderboardScore);

        if (this.childController != null) {
            this.childController.loadLangTexts(langBundle);
        }
    }
}