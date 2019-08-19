package assignment.cricketgame;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private List<Player> team;
    private String teamName;
    private int wickets;
    private int runs ;

    public Team(String teamName, List<Player> players) {
        this.teamName = teamName;
        this.runs = 0;
        this.wickets = 0;
        addPlayers(players);
    }

    private void addPlayers(List<Player> players) {
        team = new ArrayList<Player>(players);
    }

    public void out() {
        wickets++;
    }

    public void addRuns(int n) {
        runs += n;
    }

    public int getWickets() {
        return wickets;
    }

    public int getRuns() {
        return runs;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Player> getTeam() {
        return team;
    }

    public Player getPlayer(int n) {
        if(n > team.size()-1)
            return null;
        else
            return team.get(n);
    }
}
