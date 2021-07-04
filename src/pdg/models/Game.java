package pdg.models;

import pdg.utils.SessionManager;

public class Game {
    private static final int pointsToWin = 100;
    private Die die;
    private User player;
    private User computer;
    private User current;
    public Game(String username){
        die = new Die();
        player = SessionManager.user;
        computer = new User("Computer");
        current = player;
    }

    public User getUser() {
        return player;
    }

    public User getComputer() {
        return computer;
    }

    public User getCurrent() {
        return current;
    }
    public boolean checkIfUserCurrent(){
        return current == player;
    }
    public void switchTurn(){
        if( checkIfUserCurrent())
            current = computer;
        else{
            current = player;
        }
    }
    public void roll(){
        die.roll();
        int rollValue = die.getTop();
        current.changeTurnScore(rollValue);
        if(rollValue == 1){
            current.resetTurnScore();
            switchTurn();
        }
    }
    public void hold(){
        current.saveScore();
        switchTurn();
        die.setTop(0);
    }

}
