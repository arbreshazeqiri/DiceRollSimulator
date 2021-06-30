package pdg.controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SignupController implements Initializable {
    public final String SIGN_UP_VIEW = "signup";
    public final String LOG_IN_VIEW2 = "login";

    private static final String VIEW_PATH = "../views";

    @FXML
    private VBox contentPane2;

    @FXML
    private ChoiceBox choiceBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Albania \uD83C\uDDE6\uD83C\uDDF1", "Kosovo \uD83C\uDDFD\uD83C\uDDF0");
        choiceBox.setItems(list);
    }

    public void setView2(String view) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewPath2(view)));
        Pane pane = null;
        switch (view) {
            case LOG_IN_VIEW2:
                pane = loader.load();
                break;
            case SIGN_UP_VIEW:
                pane = loader.load();
                contentPane2.setAlignment(Pos.TOP_LEFT);
                break;
            default:
                throw new Exception("ERR_VIEW_NOT_FOUND");
        }

        contentPane2.getChildren().clear();
        contentPane2.getChildren().add(pane);
        VBox.setVgrow(pane, Priority.ALWAYS);
    }

    private String viewPath2(String view) {
        return VIEW_PATH + "/" + view + ".fxml";
    }
}
