package assignment.cricketgame;

public class Player {
    private String name;
    private int fours;
    private int sixes;
    private int ballsPlayed;
    private Role role;
    public static enum Role {
        BATSMAN, BOWLER, ALLROUNDER;
    }
    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public void addFours() {
        fours++;
    }

    public void addSixes() {
        sixes++;
    }

    public void addballsPlayed() {
        ballsPlayed++;
    }
    public Player(String name, Role role) {
        this.name  = name;
        this.role = role;
        this.ballsPlayed = 0;
        this.fours = 0;
        this.sixes = 0;
    }


    public String getName() {
        return name;
    }
}
