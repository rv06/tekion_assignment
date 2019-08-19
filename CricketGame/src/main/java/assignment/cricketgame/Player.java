package assignment.cricketgame;

public class Player {
    private String name;
    private int fours;
    private int sixes;
    private int ballsPlayed;
    private int totalRuns;
    private int wicketsTaken;
    private int runsConceded;
    private int oversBowled;
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
}
