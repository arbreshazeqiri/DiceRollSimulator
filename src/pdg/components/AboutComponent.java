package pdg.components;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AboutComponent {
    public void showDialog() throws Exception {
        Stage stage = new Stage();

        Parent root = FXMLLoader.load(this.getClass().getResource("../views/partials/about.fxml"));
        Scene scene = new Scene(root);

        stage.setTitle("PDG - About");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UTILITY);
        stage.setResizable(false);
        stage.setScene(scene);
        stage.showAndWait();
    }
}