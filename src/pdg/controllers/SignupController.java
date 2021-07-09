package pdg.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import pdg.components.ErrorPopupComponent;
import pdg.models.User;
import pdg.repositories.UserRepository;
import pdg.utils.SecurityHelper;
import pdg.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static javax.swing.JOptionPane.showMessageDialog;

public class SignupController extends ChildController {
    public final String SIGN_UP_VIEW = "signup";
    public final String LOG_IN_VIEW2 = "login";

    private static final String VIEW_PATH = "../views";

    @FXML
    private Label suUser;
    @FXML
    private Label suFull;
    @FXML
    private Label suEmail_;
    @FXML
    private Label suPass;
    @FXML
    private Label suConPass;
    @FXML
    private Label suCount;
    @FXML
    private Button cancelButton;
    @FXML
    private Button RegisterId;
    @FXML
    private VBox contentPane2;

    @FXML
    private ComboBox choiceBox;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField fullnameField;
    @FXML
    private TextField emailField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label registerMessageLabel, registerMessageLabel1;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Albania", "Kosovo");
        choiceBox.setItems(list);
        super.initialize(url, rb);
    }

    @FXML
    private void onRegisterButtonClick(ActionEvent event) {
        try {
            if (!usernameField.getText().isBlank() && !fullnameField.getText().isBlank() && !emailField.getText().isBlank() && !passwordField.getText().isBlank() && !confirmPasswordField.getText().isBlank() && !choiceBox.getSelectionModel().isEmpty()) {
                if (passwordField.getText().equals(confirmPasswordField.getText())) {
                    if (passwordField.getLength() > 5) {
                        emailValidation(event);
                        registerMessageLabel1.setText("");
                    } else {
                        registerMessageLabel1.setText("Password must be at least 6 characters.");
                    }
                } else {
                    registerMessageLabel1.setText("Passwords do not match.");
                }
            } else {
                registerMessageLabel.setText("All fields must be filled.");
            }
//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void emailValidation(ActionEvent eventi) throws Exception {
        String regex = "^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(emailField.getText());
        if (matcher.matches()) {
            registerMessageLabel.setText("");
            if (!UserRepository.findByEmail(emailField.getText())) {
                if (!UserRepository.findByUsername(usernameField.getText())) {
                    String salt = SecurityHelper.generateSalt();
                    String hashedpassword = SecurityHelper.computeHash(passwordField.getText(), salt);
                    try {
                        User user = UserRepository.create(usernameField.getText(), fullnameField.getText(), emailField.getText().toLowerCase(), hashedpassword, salt, choiceBox.getValue().toString());
                        SessionManager.user = user;
                        showMessageDialog(null, "Registration: successful. Login with your new account!");
                        Parent root = FXMLLoader.load(getClass().getResource(viewPath2("login")));
                        Scene scene = new Scene(root);

                        Stage primaryStage = (Stage) ((Node) eventi.getSource()).getScene().getWindow();
                        primaryStage.setScene(scene);
                        primaryStage.show();
                    } catch (Exception e) {
                        ErrorPopupComponent.show(e.toString());
                    }
                } else {
                    registerMessageLabel.setText("Username is already taken.");
                }
            } else {
                registerMessageLabel.setText("Email is already taken.");
            }
        } else {
            registerMessageLabel.setText("Email pattern is incorrect.");
            emailField.requestFocus();
        }
    }

    @FXML
    private void onCancelButtonClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(viewPath2("login")));
            Scene scene = new Scene(root);

            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setView2(String view) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(viewPath2(view)));
        Pane pane = null;
        switch (view) {
            case LOG_IN_VIEW2:
                pane = loader.load();
                contentPane2.setAlignment(Pos.CENTER);
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

    private ChildController childController = null;

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        String suUsername = langBundle.getString("su_username");
        String suFullName = langBundle.getString("su_full_name");
        String suEmail = langBundle.getString("su_email");
        String suPassword = langBundle.getString("su_password");
        String suConfirmPassword = langBundle.getString("su_confirm_password");
        String suCountry = langBundle.getString("su_country");
        String suCancelButton = langBundle.getString("su_cancel_button");
        String suRegisterButton = langBundle.getString("su_register_button");
        try {
            suUser.setText(suUsername);
            suFull.setText(suFullName);
            suEmail_.setText(suEmail);
            suPass.setText(suPassword);
            suConPass.setText(suConfirmPassword);
            suCount.setText(suCountry);
            cancelButton.setText(suCancelButton);
            RegisterId.setText(suRegisterButton);
            choiceBox.setPromptText(suCountry);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (this.childController != null) {
            this.childController.loadLangTexts(langBundle);
        }
    }
}

