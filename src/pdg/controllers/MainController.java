package pdg.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pdg.components.AboutComponent;

public class MainController implements Initializable {
    public final static String LEADERBOARD_VIEW = "leaderboard";
    public final static String PROFILE_VIEW = "profile";
    public final static String NEW_GAME_VIEW = "new-game";
    public final static String LOG_IN_VIEW = "login";

    private static final String VIEW_PATH = "../views";

    @FXML
    private VBox contentPane;


    @Override
    public void initialize(URL url, ResourceBundle bundle) {
    }

    public void loadView(String screen) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        Parent node;
        switch (screen) {
            case LEADERBOARD_VIEW:
                loader.setLocation(getClass().getResource(viewPath(LEADERBOARD_VIEW)));
                node = loader.load();
                break;
            case PROFILE_VIEW:
                loader.setLocation(getClass().getResource(viewPath(PROFILE_VIEW)));
                node = loader.load();
                break;
            case NEW_GAME_VIEW:
                loader.setLocation(getClass().getResource(viewPath(NEW_GAME_VIEW)));
                node = loader.load();
                break;
            case LOG_IN_VIEW:
                loader.setLocation(getClass().getResource(viewPath(LOG_IN_VIEW)));
                node = loader.load();
                break;
            default:
                throw new Exception("ERR_SCREEN_NOT_FOUND");
        }

        ChildController controller = loader.getController();
        controller.setParentController(this);

        contentPane.getChildren().clear();
        contentPane.getChildren().add(node);
        VBox.setVgrow(node, Priority.ALWAYS);


        switch (screen) {
            case LEADERBOARD_VIEW:
                contentPane.setAlignment(Pos.TOP_CENTER);
                break;
            case PROFILE_VIEW:
                contentPane.setAlignment(Pos.TOP_LEFT);
                break;
            case LOG_IN_VIEW:
                contentPane.setAlignment(Pos.CENTER);
                break;
            case NEW_GAME_VIEW:
                contentPane.setAlignment(Pos.TOP_CENTER);
                break;
            default:
                throw new Exception("ERR_SCREEN_NOT_FOUND");
        }
    }

    @FXML
    private void onLeaderboardNavClick(ActionEvent event) {
        try {
            this.loadView(LEADERBOARD_VIEW);
        } catch (Exception e) {

        }
    }

    @FXML
    private void onProfileNavClick(ActionEvent event) {
        try {
            this.loadView(PROFILE_VIEW);
        } catch (Exception e) {

        }
    }

    @FXML
    private void onLogoutNavClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(viewPath("login")));
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {

        }
    }

    @FXML
    private void onLogoutMenuClick(ActionEvent event) {
        try {
            //nvideo t fundit e bon kta me statuslabel
        } catch (Exception e) {

        }
    }

    @FXML
    private void onExitMenuClick(ActionEvent event) {
        try {
            //videoja e fundit
        } catch (Exception e) {

        }
    }

    @FXML
    private void onNewGameMenuClick(ActionEvent event) {
        try {
            this.loadView(NEW_GAME_VIEW);
        } catch (Exception e) {

        }
    }


    @FXML
    private void onAboutClick(ActionEvent event) {
        try {
            new AboutComponent().showDialog();
        } catch (Exception e) {
        }
    }

    private String viewPath(String view) {
        return VIEW_PATH + "/" + view + ".fxml";
    }
}
