package assignment.cricketgame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class MatchController {
    private final Logger LOGGER = LoggerFactory.getLogger(MatchController.class);

    private static Match match = Match.getInstance();
    private Team team1;
    private Team team2;
    private Team teamToBatFirst;
    private Team teamToBatSecond;
    public MatchController(Team team1, Team team2) {
        this.team1 = team1;
        this.team2 = team2;
    }

    public void startMatch() {

        toss(team1, team2);

        match.startInnings(match.getTeamToBatFirst());

        match.startInnings(match.getTeamToBatSecond());

        match.getScoreBoard();

        match.getMatchResult();

        endMatch();

    }

    private void endMatch() {
        LOGGER.info("Match is ended...");
    }

    public void toss(Team team1, Team team2) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        /*
            Toss result -->  0 -> Team1 won toss
                             1 -> Team2 won toss
         */
        int tossResult = random.nextInt(0,2);
        if(tossResult == 0)
            chooseOption(team1, team2);
        else
            chooseOption(team2, team1);

    }

    public void chooseOption(Team team1, Team team2) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int batOrBowl = random.nextInt(0,2);
        /*
            Toss result -->  0 -> Bar first
                             1 -> Bowl first
         */
        if(batOrBowl == 0)
        {
           match.setBattingOrder(team1, team2);
        }
        else {
            match.setBattingOrder(team2, team1);
        }
    }
}
