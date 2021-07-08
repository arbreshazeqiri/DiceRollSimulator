package pdg.models;

public class User {
    private String username;
    private String fullname;
    private String email;
    private String password;
    private String salt;
    private String country;
    private int turnScore;
    private int totalScore;
    private int score;
    private int numberOfWins;

    public User(String username, String fullname, String email, String password, String salt, String country) {
        this.username = username;
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.country = country;
        this.turnScore = 0;
        this.totalScore = 0;
        this.score = 0;
        this.numberOfWins = 0;
    }

    public User(String username) {
        this.username = username;
        this.turnScore = 0;
        this.totalScore = 0;
    }


    public String getUsername() {
        return username;
    }


    public String getFullName() {
        return fullname;
    }

    public void setFullName(String fullName) {
        this.fullname = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTurnScore() {
        return turnScore;
    }

    public void setTurnScore(int turnScore) {
        this.turnScore = turnScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void incrementNumberOfWins() {
        this.numberOfWins++;
    }

    public void resetTurnScore() {
        this.turnScore = 0;
    }

    public void changeTurnScore(int rollValue) {
        this.turnScore += rollValue;
    }

    public void addTotalScore(int add){
        this.score += add;
    }

    public int getScore(){
        return this.score;
    }

    public void saveScore() {
        this.totalScore += this.turnScore;
        resetTurnScore();
    }
}