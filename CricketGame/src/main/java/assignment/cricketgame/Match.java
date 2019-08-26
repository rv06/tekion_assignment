package assignment.cricketgame;


import org.slf4j.*;

import java.sql.Connection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Match {

    private static Match match;
    private final Logger LOGGER = LoggerFactory.getLogger(Match.class);

    private Team teamToBatFirst;
    private Team teamToBatSecond;
    private Player striker;
    private Player nonStriker;
    private Player currentBowler;
    private boolean isSecondInning = true;
    private DbConnect dbConnect = DbConnect.getInstance();
    private static int matchId;
    private Match() {
    }

    private static int OVERSPERINNINGS = 20;
    private boolean isAllOut = false;


    public void setBattingOrder(Team team1, Team team2) {
        teamToBatFirst = team1;
        teamToBatSecond = team2;
        matchId = dbConnect.updateMatchTable(teamToBatFirst.getTeamName(), teamToBatSecond.getTeamName());
        LOGGER.info("Team batting first is - " + teamToBatFirst.getTeamName());
    }
    /*
        Completes one inning for the team passed as method parameter
     */
    public void startInnings(Team team) {
        int overs = 0;
        isAllOut = false;
        isSecondInning = !isSecondInning;
        striker = team.getPlayer(0);
        nonStriker = team.getPlayer(1);
        int overCompleted;
        while(!isAllOut && overs < OVERSPERINNINGS) {
            int[] currentOverResult = overOutcome();
            overCompleted = overs;
            currentBowler = !isSecondInning ? teamToBatSecond.getPlayer(ThreadLocalRandom.current().nextInt(6,11)) : teamToBatFirst.getPlayer(ThreadLocalRandom.current().nextInt(6,11));
            currentBowler.addOvers();
            for (int i : currentOverResult) {

                if(i == 7) {
                    team.out();
                    striker.addballsPlayed();
                    currentBowler.addWicketsTaken();
                    if(team.getWickets() >= 10) {
                        isAllOut = true;
                        break;
                    }
                    else {
                        striker = team.getPlayer(team.getWickets() + 1);
                    }

                    //LOGGER.info("Player " + team.getPlayer(team.getWickets()).getName() + " lost his wicket.");


                }
                else {
                    LOGGER.info(i + " runs added to team " + team.getTeamName() + "'s score");
                    striker.addRuns(i);
                    team.addRuns(i);
                    striker.addballsPlayed();
                    currentBowler.addRunsconceded(i);
                    changeStrike(striker, nonStriker,i);

                    if(isSecondInning) {
                        if(teamToBatSecond.getRuns() > teamToBatFirst.getRuns()) {
                            overs = 20;
                            break;
                        }
                    }
                }

            }
            overs++;
            dbConnect.updateMatchData(matchId, overCompleted+1, team);

        }
    }

    /*
        Generates random outcome for an over
        Possible values for a ball are (0,1,2,3,4,5,6,7)
        where 0-6 represents runs scored and 7 represents fall of wicket
     */
    private int[] overOutcome() {
        int[] overResult = new int[6];

        for(int i=0;i<6;i++) {
            overResult[i] = generateRandomOutocme();

        }
        return overResult;
    }

    private int generateRandomOutocme() {
        ThreadLocalRandom result = ThreadLocalRandom.current();
        return  result.nextInt(0,8);
    }

    public void getScoreBoard() {
        LOGGER.info("\n************************************************************\n" +
                    "                         Score Board                        \n" +
                    "************************************************************\n ");


        printBattingScoreCard(teamToBatFirst);

        printBowlingScoreCard(teamToBatSecond);

        printBattingScoreCard(teamToBatSecond);

        printBowlingScoreCard(teamToBatFirst);

    }
    public void getMatchResult() {
        LOGGER.info("Team to bat first was: " + teamToBatFirst.getTeamName() + " and scored " + teamToBatFirst.getRuns() + " runs");

        LOGGER.info("Team to bat second was: " + teamToBatSecond.getTeamName() + " and scored " + teamToBatSecond.getRuns() + " runs");
        String scoreFirstInnings = teamToBatFirst.getRuns() + "-" + teamToBatFirst.getWickets();
        String scoreSecondInnings = teamToBatSecond.getRuns() + "-" + teamToBatSecond.getWickets();
        String result;
        int compare = Integer.compare(teamToBatFirst.getRuns(), teamToBatSecond.getRuns());
        if(compare > 0) {
            LOGGER.info(teamToBatFirst.getTeamName() + " won by " + (teamToBatFirst.getRuns() - teamToBatSecond.getRuns()) + " runs");
            result = teamToBatFirst.getTeamName() + " won by " + (teamToBatFirst.getRuns() - teamToBatSecond.getRuns()) + " runs";
            dbConnect.updateMatchResult(matchId, scoreFirstInnings, scoreSecondInnings, result);

        }
        else if(compare<0) {
            LOGGER.info(teamToBatSecond.getTeamName() + " won by " + (10 - teamToBatSecond.getWickets()) + " wickets.");
            result = teamToBatSecond.getTeamName() + " won by " + (10 - teamToBatSecond.getWickets()) + " wickets.";
            dbConnect.updateMatchResult(matchId, scoreFirstInnings, scoreSecondInnings, result);
        }
        else {
            LOGGER.info("It's a tie...");
            result = "It's a tie...";
            dbConnect.updateMatchResult(matchId, scoreFirstInnings, scoreSecondInnings, result);
        }
    }

    public Team getTeamToBatFirst() {
        return teamToBatFirst;
    }

    public Team getTeamToBatSecond() {
        return teamToBatSecond;
    }

    private void changeStrike(Player player1, Player player2, int i) {
        if(i == 1 || i == 3) {
            Player change = striker;
            striker = nonStriker;
            nonStriker = change;
        }
    }
    private void printBattingScoreCard(Team team) {
        System.out.println("\nTeam : " + team.getTeamName() + "\t " +  team.getRuns() + "-" + team.getWickets());
        System.out.println(String.format("%-20s%-5s%-5s%-5s%-5s%-5s","Batsman", "R" ,"B", "4s", "6s", "SR"));
        for(Player p : team.getTeam())
            System.out.println(String.format("%-20s%-5s%-5s%-5s%-5s%-5s" ,p.getName(), p.gettotalRuns(),  p.getBallsPlayed() ,p.getFours(), p.getSixes(), p.getStrikeRate()));
    }

    private void printBowlingScoreCard(Team team) {
        System.out.println("\nBowling Stats : " + team.getTeamName());
        System.out.println(String.format("%-20s%-5s%-5s%-5s%-5s","Bowler", "O" ,"R", "W", "E"));
        List<Player> playerList = team.getTeam();
        for(int i = 10; i>=6;i--) {
            Player p = playerList.get(i);
            System.out.println(String.format("%-20s%-5s%-5s%-5s%-5s" ,p.getName(), p.getOversBowled(), p.getRunsConceded(), p.getWicketsTaken(), p.getEconomy()));
        }
    }
    public static Match getInstance() {
        if(match == null)
            match = new Match();
        return match;
    }
}
