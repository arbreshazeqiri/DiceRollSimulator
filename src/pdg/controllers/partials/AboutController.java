package pdg.controllers.partials;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pdg.controllers.BaseController;
import pdg.controllers.ChildController;


public class AboutController extends BaseController {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        super.initialize(arg0, arg1);
    }
    private ChildController childController = null;

    @FXML
    private Label  versionLabel;
    @FXML
    private Label releasedLabel;

    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        String version = langBundle.getString("about_version");
        String release = langBundle.getString("about_release");

        versionLabel.setText(version);
        releasedLabel.setText(release);

        if (this.childController != null) {
            this.childController.loadLangTexts(langBundle);
        }
    }
}
