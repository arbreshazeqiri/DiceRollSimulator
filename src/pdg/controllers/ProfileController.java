package pdg.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pdg.utils.SessionManager;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController extends ChildController {

    @FXML
    Label usernameLabel, fullnameLabel, emailLabel, winsLabel, scoreLabel;

    @FXML
    ImageView countryImage, avatarImage;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        usernameLabel.setText(SessionManager.user.getUsername());
        fullnameLabel.setText(SessionManager.user.getFullName());
        emailLabel.setText(SessionManager.user.getEmail());
        winsLabel.setText("" + SessionManager.user.getNumberOfWins());
        scoreLabel.setText("" + SessionManager.user.getScore());

        File f;
        if(SessionManager.user.getCountry().equals("Kosovo")){
            f = new File("src/pdg/resources/images/Kosovo.png");
        }
        else{
            f = new File("src/pdg/resources/images/Albania.png");
        }
        countryImage.setImage(new Image(f.toURI().toString()));
    }
}