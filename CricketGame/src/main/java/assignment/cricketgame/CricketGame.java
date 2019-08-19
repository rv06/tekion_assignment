package assignment.cricketgame;

import java.util.ArrayList;
import java.util.List;

public class CricketGame {



    public static void main(String[] args) {
        List<Player> team1Players = new ArrayList<>();
        List<Player> team2Players = new ArrayList<>();
        List<Player> winnerTeamScoreBoard = null;
        List<Player> losingTeamScoreBoard = null;

        Team team1 = null, team2 = null;


        // Enter players in the series you want them to Bat first
        Player team1Player1 = new Player("Virat Kohli", Player.Role.BATSMAN);
        Player team1Player2 = new Player("Rohit Sharma", Player.Role.BATSMAN);
        Player team1Player3 = new Player("Kedar Jadhav", Player.Role.BATSMAN;
        Player team1Player4 = new Player("K.L. Rahul", Player.Role.BATSMAN);
        Player team1Player5 = new Player("Ravindra Jadeja", Player.Role.ALLROUNDER);
        Player team1Player6 = new Player("Hardik Pandya",  Player.Role.ALLROUNDER);
        Player team1Player7 = new Player("M.S Dhoni", Player.Role.BATSMAN);
        Player team1Player8 = new Player("Jasprit Bumrah", Player.Role.BOWLER);
        Player team1Player9 = new Player("Mohammed Shami", Player.Role.BOWLER);
        Player team1Player10 = new Player("Kuldeep Yadav", Player.Role.BOWLER);
        Player team1Player11 = new Player("Yuzvendra Chahal", Player.Role.BOWLER);

        team1Players.add(team1Player1);
        team1Players.add(team1Player2);
        team1Players.add(team1Player3);
        team1Players.add(team1Player4);
        team1Players.add(team1Player5);
        team1Players.add(team1Player6);
        team1Players.add(team1Player7);
        team1Players.add(team1Player8);
        team1Players.add(team1Player9);
        team1Players.add(team1Player10);
        team1Players.add(team1Player11);

        Player team2Player1 = new Player("Babar Azam", Player.Role.BATSMAN);
        Player team2Player2 = new Player("Fakhar Zaman", Player.Role.BATSMAN);
        Player team2Player3 = new Player("Haris Sohail", Player.Role.BATSMAN);
        Player team2Player4 = new Player("Asif Ali", Player.Role.BATSMAN);
        Player team2Player5 = new Player("Shoaib Malik", Player.Role.ALLROUNDER);
        Player team2Player6 = new Player("Mohammed Hafeez", Player.Role.BATSMAN);
        Player team2Player7 = new Player("Sarfaraz Ahmed", Player.Role.BATSMAN);
        Player team2Player8 = new Player("Shaheen Afridi", Player.Role.BOWLER);
        Player team2Player9 = new Player("Wahab Riaz", Player.Role.BOWLER);
        Player team2Player10 = new Player("Mohammad Hasnain", Player.Role.BOWLER);
        Player team2Player11 = new Player("Wasim Akram", Player.Role.BOWLER);

        team2Players.add(team2Player1);
        team2Players.add(team2Player2);
        team2Players.add(team2Player3);
        team2Players.add(team2Player4);
        team2Players.add(team2Player5);
        team2Players.add(team2Player6);
        team2Players.add(team2Player7);
        team2Players.add(team2Player8);
        team2Players.add(team2Player9);
        team2Players.add(team2Player10);
        team2Players.add(team2Player11);

        // Create two objects of class Team
        team1 = new Team("India", team1Players);
        team2 = new Team("Pakistan", team2Players);

        MatchController mtc = new MatchController(team1, team2);
        mtc.startMatch();
    }
}
