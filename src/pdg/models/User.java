package pdg.models;

import java.util.Date;

public class User {
    private int userId;
    private String username;
    private String fullName;
    private String email;
    private String password;
    private String salt;
    private String country;
    private Date createdAt;
    private Date updatedAt;
    private int turnScore;
    private int totalScore;
    private int numberOfWins;

    public User(int userId,String username,String fullName, String email, String password, String salt, String country){
        this.userId = userId;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.salt = salt;
        this.country = country;
        this.turnScore = 0;
        this.totalScore = 0;
        this.numberOfWins = 0;
    }
    public User(String username){
        this.username = username;
        this.turnScore =0;
        this.totalScore = 0;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
    public void incrementNumberOfWins(){
        this.numberOfWins++;
    }
    public void resetTurnScore(){
        this.turnScore = 0;
    }
    public void changeTurnScore(int rollValue){
        this.turnScore += rollValue;
    }
    public void saveScore(){
        this.totalScore += this.turnScore;
        resetTurnScore();
    }
}
