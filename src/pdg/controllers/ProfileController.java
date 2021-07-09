package pdg.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
    private ChildController childController = null;
    @FXML
    private Label usernameLabel1;
    @FXML
    private Label emailLabel1;
    @FXML
    private Label winsLabel1;
    @FXML
    private Label scoreLabel1;
    @FXML
    private Label countryLabel1;
    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        String pfUsername = langBundle.getString("pf_username");
        String pfEmail = langBundle.getString("pf_email");
        String pfCountry = langBundle.getString("pf_country");
        String pfWins = langBundle.getString("pf_wins");
        String pfScore = langBundle.getString("pf_score");

        usernameLabel1.setText(pfUsername);
        emailLabel1.setText(pfEmail);
        countryLabel1.setText(pfCountry);
        winsLabel1.setText(pfWins);
        scoreLabel1.setText(pfScore);

        if(this.childController != null){
            this.childController.loadLangTexts(langBundle);
        }
    }
}