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
import pdg.repositories.UserRepository;
import pdg.utils.SessionManager;

import java.io.File;
import java.net.URL;
import java.util.Random;
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
    @FXML
    private Label current_;
    @FXML
    Label winnerLabel;
    private ChildController childController = null;

    @Override
    public void initialize(URL url, ResourceBundle bundle){
        startGame();
    }

    public void startGame(){
        p1box.setOpacity(0.7);
        clock = new Roller();
        pig = new Game(SessionManager.user.getUsername(), "Computer");
        playagainButton.setVisible(false);
        rollButton.setDisable(false);
        holdButton.setDisable(false);
        playerOneLabel.setText(pig.getUser().getUsername());
        playerTwoLabel.setText(pig.getComputer().getUsername());
        rollButton.setOnAction(event -> rollAnimation());
        holdButton.setOnAction(event -> hold());
        playagainButton.setOnAction(event -> startGame());
        winnerLabel.setText("");
        updateView();
        File f = new File("src/pdg/resources/images/logokatror.png");
        dieImage.setImage(new Image(f.toURI().toString()));
        dieImage.setFitHeight(100);
        dieImage.setFitWidth(100);
        p1box.setOpacity(0.7);
    }

    public void updateView(){
        if(pig.checkIfUserCurrent()){
            p1box.setOpacity(0.7);
            p2box.setOpacity(0.5);
        }
        else{
            p2box.setOpacity(0.7);
            p1box.setOpacity(0.5);
        }

        setDieImage(pig.getDie().getTop());
        p1TurnTextField.setText("" + pig.getUser().getTurnScore());
        p2TurnTextField.setText("" + pig.getComputer().getTurnScore());
        p1TotalTextField.setText("" + pig.getUser().getTotalScore());
        p2TotalTextField.setText("" + pig.getComputer().getTotalScore());

        if(pig.gameOver()){
            File f = new File("src/pdg/resources/images/game-over.png");
            dieImage.setImage(new Image(f.toURI().toString()));
            dieImage.setFitHeight(250);
            dieImage.setFitWidth(250);
            playagainButton.setVisible(true);
            playagainButton.setOpacity(1);
            rollButton.setDisable(true);
            holdButton.setDisable(true);
            winnerLabel.setText(pig.getCurrent().getUsername() + " is the winner.");
            if(SessionManager.user.getUsername() == pig.getCurrent().getUsername()) {
                SessionManager.user.incrementNumberOfWins();
            }
            SessionManager.user.addTotalScore(pig.getCurrent().getTotalScore());
        }
        try {
            UserRepository.update(SessionManager.user);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public void rollAnimation(){
        clock.start();
    }

    public void roll() {
        pig.roll();
        updateView();
        checkTurn();

    }
    public void playComputer() {
        Random random = new Random();
        int pcMove = random.nextInt(4); //for value 1 hold
        if (pcMove == 1) {
            holdButton.fire();
        }
        rollButton.fire();
    }
    public void checkTurn(){
        if(pig.getCurrent().equals(pig.getComputer())){
            playComputer();
        }


    }
    private class Roller extends AnimationTimer{
        private long FRAMES_PER_SEC = 50L;
        private long INTERVAL = 1000000000L / FRAMES_PER_SEC;
        private int MAX_ROLLS = 20;

        private long last = 0;
        private int count = 0;

        @Override
        public void handle(long now){
            if(now - last > INTERVAL){
                int r = 2 + (int) (Math.random() * 5);
                setDieImage(r);
                last = now;
                count++;
                if(count > MAX_ROLLS){
                    clock.stop();
                    roll();
                    count = 0;
                }
            }
        }
    }

    public void hold(){
        pig.hold();

        if (!pig.gameOver()) {
            pig.switchTurn();
        }

        updateView();
        checkTurn();
    }

    public void setDieImage(int top){
        if(top == 0 || top == 1){
            File f = new File("src/pdg/resources/images/dice1.png");
            dieImage.setImage(new Image(f.toURI().toString()));
        }
        File f = new File("src/pdg/resources/images/dice"+top+".png");
        dieImage.setImage(new Image(f.toURI().toString()));
    }
    @Override
    public void loadLangTexts(ResourceBundle langBundle) {
        String player1 = langBundle.getString("player_1");
        String computer = langBundle.getString("computer");
        String current = langBundle.getString("current");
        String roll = langBundle.getString("roll");
        String playAgain = langBundle.getString("play_again");
        String hold = langBundle.getString("hold");

        playerOneLabel.setText(player1);
        playerTwoLabel.setText(computer);
        current_.setText(current);
        rollButton.setText(roll);
        holdButton.setText(hold);
        playagainButton.setText(playAgain);

        if(this.childController != null){
            this.childController.loadLangTexts(langBundle);
        }
    }
}