package assignment.cricketgame;


import org.slf4j.*;
import java.util.concurrent.ThreadLocalRandom;

public class Match {

    private static Match match;
    private final Logger LOGGER = LoggerFactory.getLogger(Match.class);

    private Team teamToBatFirst;
    private Team teamToBatSecond;
    private boolean isSecondInning = true;
    private Match() {
    }

    private static int OVERSPERINNINGS = 20;
    private boolean isAllOut = false;


    public void setBattingOrder(Team team1, Team team2) {
        teamToBatFirst = team1;
        teamToBatSecond = team2;
        LOGGER.info("Team batting first is - " + teamToBatFirst.getTeamName());
    }
    /*
        Completes one inning for the team passed as method parameter
     */
    public void startInnings(Team team) {
        int overs = 0;
        isAllOut = false;
        isSecondInning = !isSecondInning;
        while(!isAllOut && overs < OVERSPERINNINGS) {
            int[] currentOverResult = overOutcome();
            for (int i : currentOverResult) {
                if(i == 7) {
                    team.out();
                    LOGGER.info("Player " + team.getPlayer(team.getWickets()).getName() + " lost his wicket.");
                    if(team.getWickets() >= 10) {
                        isAllOut = true;
                        break;
                    }
                }
                else {
                    LOGGER.info(i + " runs added to team " + team.getTeamName() + "'s score");
                    team.addRuns(i);
                    if(isSecondInning) {
                        if(teamToBatSecond.getRuns() > teamToBatFirst.getRuns()) {
                            overs = 20;
                            break;
                        }
                    }
                }
            }
            overs++;
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

    public void getMatchResult() {
        LOGGER.info("Team to bat first was: " + teamToBatFirst.getTeamName() + " and scored " + teamToBatFirst.getRuns() + " runs");

        LOGGER.info("Team to bat second was: " + teamToBatSecond.getTeamName() + " and scored " + teamToBatSecond.getRuns() + " runs");

        int result = Integer.compare(teamToBatFirst.getRuns(), teamToBatSecond.getRuns());
        if(result > 0) {
            LOGGER.info(teamToBatFirst.getTeamName() + " won by " + (teamToBatFirst.getRuns() - teamToBatSecond.getRuns()) + " runs");

        }
        else if(result<0) {
            LOGGER.info(teamToBatSecond.getTeamName() + " won by " + (10 - teamToBatSecond.getWickets()) + " wickets.");

        }
        else
            LOGGER.info("It's a tie...");

    }

    public Team getTeamToBatFirst() {
        return teamToBatFirst;
    }

    public Team getTeamToBatSecond() {
        return teamToBatSecond;
    }

    public static Match getInstance() {
        if(match == null)
            match = new Match();
        return match;
    }
}
