package pdg.controllers;

import java.util.ResourceBundle;

public abstract class ChildController extends BaseController {
    public MainController parentController;

    public void setParentController(MainController controller) {
        this.parentController = controller;
    }
    @Override
    public void loadLangTexts(ResourceBundle langBundle) {

    }
}