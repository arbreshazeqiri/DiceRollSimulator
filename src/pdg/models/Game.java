package pdg.models;

public class Game {
    private static final int pointsToWin = 50;
    private Die die;
    private User player;
    private User computer;
    private User current;

    public Game(String playername, String computername) {
        die = new Die();
        player = new User(playername);
        computer = new User(computername);
        current = player;
    }

    public Die getDie() {
        return die;
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

    public boolean checkIfUserCurrent() {
        return current == player;
    }

    public void switchTurn() {
        if (checkIfUserCurrent())
            current = computer;
        else {
            current = player;
        }
    }

    public void roll() {
        die.roll();
        int rollValue = die.getTop();
        current.changeTurnScore(rollValue);
        if (rollValue == 1) {
            current.resetTurnScore();
            switchTurn();
        }
    }

    public void hold() {
        current.saveScore();
    }

    //    public void playComputer() {
//        Random random = new Random();
//        int pcMove = random.nextInt(4); //for value 1 hold
//        if (pcMove == 1) {
//            current.saveScore();
//            switchTurn();
//        }
//        roll();
//    }
    public boolean gameOver(){
        return current.getTotalScore() >= pointsToWin;
    }

}