package pdg.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pdg.components.DatabaseConnection;

public class LoginController implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
    }

    @FXML
    private Label loginMessageLabel;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private void onLoginButtonClick(ActionEvent event) {
        try {
            if(username.getText().isBlank()==false && password.getText().isBlank()==false){
                validateLogin(event);
            }
            else {
                loginMessageLabel.setText("Username or Password is empty!");
            }
        } catch (Exception e) {
        }
    }

        public void validateLogin(ActionEvent eventi){
            DatabaseConnection connectNow = new DatabaseConnection();
            Connection connectDB = connectNow.getConnection();

            String verifyLogin = "SELECT count(1) FROM user_account WHERE username = '" + username.getText() + "' " +
                                 "AND password = '" + password.getText() + "'";
            try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);

            while(queryResult.next()){
                if(queryResult.getInt(1) == 1){
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("../views/main-screen.fxml"));
                    Parent root = loader.load();
                    MainController controller = loader.getController();
                    controller.loadView(MainController.LEADERBOARD_VIEW);
                    Scene scene = new Scene(root);

                    Stage primaryStage = (Stage) ((Node) eventi.getSource()).getScene().getWindow();
                    primaryStage.setScene(scene);
                    primaryStage.show();
                }else{
                    loginMessageLabel.setText("Wrong credentials.");
                }
            }

            }catch(Exception e){
             e.printStackTrace();
            }

        }

        @FXML
        private void onSignupButtonClick(ActionEvent event) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("../views/signup.fxml"));
                Pane root = loader.load();
                SignupController controller = loader.getController();
                controller.setView2(controller.SIGN_UP_VIEW);

                Scene scene = new Scene(root);
                Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                primaryStage.setScene(scene);
                primaryStage.show();
            } catch (Exception e) {
            }
    }
}