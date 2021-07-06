package pdg.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pdg.models.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class NewGameController extends ChildController {
    @FXML
    Label playerTwoLabel, playerOneLabel;

    @FXML
    TextField p1TotalTextField, p2TotalTextField, p1TurnTextField, p2TurnTextField;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        Game g = new Game();
    }
}