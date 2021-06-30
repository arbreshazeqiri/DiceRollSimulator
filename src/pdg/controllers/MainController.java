package pdg.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MainController implements Initializable {
    public final String LEADERBOARD_VIEW = "leaderboard";
    public final String PROFILE_VIEW = "profile";
    public final String NEW_GAME_VIEW = "new-game";
    public final String LOG_IN_VIEW = "login";

    private static final String VIEW_PATH = "../views";

    @FXML
    private VBox contentPane;


    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    public void setView(String view) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewPath(view)));
        Pane pane = null;
        switch (view) {
            case LEADERBOARD_VIEW:
                pane = loader.load();
                contentPane.setAlignment(Pos.TOP_LEFT);
                break;
            case PROFILE_VIEW:
                pane = loader.load();
                break;
            case LOG_IN_VIEW:
                pane = loader.load();
                break;
            case NEW_GAME_VIEW:
                pane = loader.load();
                break;
            default:
                throw new Exception("ERR_VIEW_NOT_FOUND");
        }

        contentPane.getChildren().clear();
        contentPane.getChildren().add(pane);
        VBox.setVgrow(pane, Priority.ALWAYS);
    }

    private String viewPath(String view) {
        return VIEW_PATH + "/" + view + ".fxml";
    }
}
