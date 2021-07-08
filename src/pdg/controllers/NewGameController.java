package pdg.controllers;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import pdg.models.Game;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class NewGameController extends ChildController{

    Game pig;
    private Roller clock;

    @FXML
    ImageView dieImage;
    @FXML
    Label playerTwoLabel, playerOneLabel;

    @FXML
    TextField p1TotalTextField, p2TotalTextField, p1TurnTextField, p2TurnTextField;

    @FXML
    Button rollButton, holdButton, playagainButton;

    @FXML
    GridPane p1box, p2box;

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        clock = new Roller();
        pig = new Game("John Doe", "Computer");

        playerOneLabel.setText(pig.getUser().getUsername());
        playerTwoLabel.setText(pig.getComputer().getUsername());
        rollButton.setOnAction(event -> roll());
        holdButton.setOnAction(event -> hold());
//        playagainButton.setOnAction();

        updateView();
        File f = new File("src/pdg/resources/images/logokatror.png");
        dieImage.setImage(new Image(f.toURI().toString()));
    }

    public void updateView(){
        setDieImage(pig.getDie().getTop());
        p1TurnTextField.setText("" + pig.getUser().getTurnScore());
        p2TurnTextField.setText("" + pig.getComputer().getTurnScore());
        p1TotalTextField.setText("" + pig.getUser().getTotalScore());
        p2TotalTextField.setText("" + pig.getComputer().getTotalScore());
        if(pig.checkIfUserCurrent()){
           p1box.setOpacity(0.7);
           p2box.setOpacity(0.5);
        }
        else{
            p2box.setOpacity(0.7);
            p1box.setOpacity(0.5);
        }
        if(pig.gameOver()){
            File f = new File("src/pdg/resources/images/game-over.png");
            dieImage.setImage(new Image(f.toURI().toString()));
            playagainButton.setVisible(true);
            playagainButton.setOpacity(1);
            rollButton.setDisable(true);
            holdButton.setDisable(true);
        }
    }

    public void rollAnimation(){
        clock.start();
    }

    public void roll() {
        pig.roll();
        System.out.println("Top: " + pig.getDie().getTop());
        System.out.println("P1: " + pig.getUser().getTotalScore());
        System.out.println("Computer: " + pig.getComputer().getTotalScore());
        updateView();

    }

    private class Roller extends AnimationTimer{
        private long FRAMES_PER_SEC = 50L;
        private long INTERVAL = 1000000000L;

        private long last = 0;
        @Override
        public void handle(long now){
            if(now - last > INTERVAL){
                int r = 2 + (int) (Math.random() + 5);
                setDieImage(r);
                last = now;
            }
        }
    }

    public void hold(){
        pig.hold();
        updateView();
    }

    public void setDieImage(int top){
        if(top == 0 || top == 1){
            File f = new File("src/pdg/resources/images/dice1.png");
            dieImage.setImage(new Image(f.toURI().toString()));
        }
    File f = new File("src/pdg/resources/images/dice"+top+".png");
    dieImage.setImage(new Image(f.toURI().toString()));
    }
}