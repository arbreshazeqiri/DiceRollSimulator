package pdg.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javax.swing.JOptionPane.showMessageDialog;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SignupController implements Initializable {
    public final String SIGN_UP_VIEW = "signup";
    public final String LOG_IN_VIEW2 = "login";

    private static final String VIEW_PATH = "../views";

    @FXML
    private VBox contentPane2;

    @FXML
    private ChoiceBox choiceBox;
    @FXML private TextField idField;
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll("Albania \uD83C\uDDE6\uD83C\uDDF1", "Kosovo \uD83C\uDDFD\uD83C\uDDF0");
        choiceBox.setItems(list);
    }
    
    @FXML
    private void onRegisterButtonClick(ActionEvent event) {
        try {
        	
        	if(passwordField.getText().equals(confirmPasswordField.getText()))
        	{
        		emailValidation();
        		FullNameValidation();
        		
        	}else {
        		showMessageDialog(null, "Keni futur te dhenat gabimisht");
        	}
//           
        } catch (Exception e) {
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
        }
    }
    
    public void emailValidation() {
    	String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
    	 
    	Pattern pattern = Pattern.compile(regex);
    	Matcher matcher = pattern.matcher(emailField.getText());
    	if (matcher.matches()) {
    		
    	}
    	else {
    		showMessageDialog(null,"Email nuk eshte ne formatin e duhur");
    		emailField.requestFocus();
    	}
    }
    
    public void FullNameValidation() {
    	String regex="^[a-zA-Z\\s]+"; 
    	if(nameField.getText().matches(regex)) {
    		
    	}else {
    		showMessageDialog(null,"Full Name duhet te permbaje vetem shkronja");
    		nameField.requestFocus();
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
}

