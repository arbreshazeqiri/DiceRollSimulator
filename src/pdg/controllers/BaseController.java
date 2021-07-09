package pdg.controllers;

import javafx.fxml.Initializable;
import pdg.utils.SessionManager;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public abstract class BaseController implements Initializable {

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadLangTexts(getLangBundle());
    }
    public ResourceBundle getLangBundle(){
        Locale locale = SessionManager.getLocale();
        return ResourceBundle.getBundle("pdg.resources.bundles.LangBundle", locale);
    }

    public abstract void loadLangTexts(ResourceBundle langBundle);
}
