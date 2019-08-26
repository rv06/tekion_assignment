package assignment.cricketgame;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CricketGame {

    private static DbConnect dbconnection =  DbConnect.getInstance();

    public static void main(String[] args) throws SQLException {
        List<Player> team1Players = new ArrayList<>();
        List<Player> team2Players = new ArrayList<>();
        List<Player> winnerTeamScoreBoard = null;
        List<Player> losingTeamScoreBoard = null;

        Team team1 = null, team2 = null;

        // Create two objects of class Team
        team1 = new Team("India", team1Players);
        team2 = new Team("Pakistan", team2Players);

        dbconnection.updateTeams(team1);
        dbconnection.updateTeams(team2);
        MatchController mtc = new MatchController(team1, team2);
        mtc.startMatch();
        dbconnection.closeConnection();
    }
}
