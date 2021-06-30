package pdg;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {
    public static void main(String[] args){
    Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
            Parent root = FXMLLoader.load(getClass().getResource("views/login.fxml"));
            Scene scene = new Scene(root);

            primaryStage.setTitle("PDG - Pig Dice Game");
            primaryStage.setScene(scene);
            primaryStage.show();

            primaryStage.sizeToScene();
            primaryStage.setMinWidth(scene.getWidth());
            primaryStage.setMinHeight(scene.getHeight());
    }
}
