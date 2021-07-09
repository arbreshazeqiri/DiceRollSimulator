package pdg.controllers;

import javafx.fxml.FXML;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LeaderboardController extends ChildController {
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
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
        if(this.childController != null){
            this.childController.loadLangTexts(langBundle);
        }
    }
}