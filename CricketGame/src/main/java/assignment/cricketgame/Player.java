package assignment.cricketgame;

import java.text.DecimalFormat;

public class Player {
    private String name;
    private int fours;
    private int sixes;
    private int ballsPlayed;
    private int totalRuns;
    private int wicketsTaken;
    private int runsConceded;
    private int oversBowled;
    private DecimalFormat decimalFormat = new DecimalFormat("#.##");



    private float strikeRate;
    private float economy;
    private Role role;
    public static enum Role {
        BATSMAN, BOWLER, ALLROUNDER;
    }



    public Player(String name, Role role) {
        this.name  = name;
        this.role = role;
        this.ballsPlayed = 0;
        this.fours = 0;
        this.sixes = 0;
        this.totalRuns = 0;
        this.wicketsTaken = 0;
        this.oversBowled = 0;
        this.runsConceded = 0;
        this.strikeRate = 0.0f;
        this.economy = 0.0f;
    }


    public String getName() {
        return this.name;
    }
    public int getFours() {
        return this.fours;
    }

    public int getSixes() {
        return this.sixes;
    }

    public int getBallsPlayed() {
        return this.ballsPlayed;
    }

    private void addFours() {
        this.fours++;
    }

    private void addSixes() {
        this.sixes++;
    }

    public void addballsPlayed() {
        this.ballsPlayed++;
    }

    public int gettotalRuns() {
        return this.totalRuns;
    }

    public int getWicketsTaken() {
        return this.wicketsTaken;
    }
    public void addWicketsTaken() {
        this.wicketsTaken++;
    }
    public void addOvers() {
        this.oversBowled++;
    }
    public Role getRole() {
        return this.role;
    }
    public int getOversBowled() {
        return this.oversBowled;
    }
    public int getRunsConceded() {
        return this.runsConceded;
    }
    public void addRunsconceded(int n) {
        this.runsConceded += n;
    }
    public void addRuns(int n) {
        this.totalRuns += n;
        if(n == 4 )
            addFours();
        else if(n == 6)
            addSixes();
    }
    public float getStrikeRate() {
        this.strikeRate = Float.valueOf(decimalFormat.format(computeStrikeRate())) * 100;
        return  this.strikeRate;
    }

    public float getEconomy() {
        this.economy = Float.valueOf(decimalFormat.format(computeEconomy()));
        return  this.economy;
    }
    private float computeStrikeRate() {
        if(ballsPlayed == 0)
            return 0;
        else
            return (float)totalRuns / ballsPlayed;
    }
    private float computeEconomy() {
        if(oversBowled == 0)
            return 0;
        else
            return (float)runsConceded / oversBowled;
    }
}
