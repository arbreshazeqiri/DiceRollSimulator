package pdg.controllers;

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
import pdg.components.ErrorPopupComponent;
import pdg.models.User;
import pdg.repositories.UserRepository;
import pdg.utils.SecurityHelper;
import pdg.utils.SessionManager;

import java.net.URL;
import java.util.ResourceBundle;

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
            ErrorPopupComponent.show(e.toString());
        }
    }

        public void validateLogin(ActionEvent eventi){
                try
                {
//                    String query = "SELECT * FROM user_account WHERE username = ?";
//
//                    PreparedStatement preparedStatement = DatabaseConnection.getConnection().prepareStatement(query);
//                    preparedStatement.setString(1,  username.getText());
//                    ResultSet resultSet = preparedStatement.executeQuery();
                    User user = UserRepository.find(username.getText());

                    if(user != null)
                    {
                        String hashedPassword = SecurityHelper.computeHash(password.getText(), user.getSalt());
                        if (user.getPassword().equals(hashedPassword)) {
                            FXMLLoader loader = new FXMLLoader();
                            SessionManager.user = user;
                            loader.setLocation(getClass().getResource("../views/main-screen.fxml"));
                            Parent root = loader.load();
                            MainController controller = loader.getController();
                            controller.loadView(MainController.NEW_GAME_VIEW);
                            Scene scene = new Scene(root);

                            Stage primaryStage = (Stage) ((Node) eventi.getSource()).getScene().getWindow();
                            primaryStage.setScene(scene);
                            primaryStage.show();
                        }
                        else {
                            loginMessageLabel.setText("Wrong credentials!");
                        }
                    }
                    else
                    {
                        loginMessageLabel.setText("User doesn't exist. Sign up to continue.");
                    }
                }
                catch (Exception ex)
                {
                    ErrorPopupComponent.show(ex.toString());
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
                e.printStackTrace();
            }
    }
}