package es.sportradar.football.worldcup.scoreboard;

import es.sportradar.football.worldcup.scoreboard.controller.ScoreBoardServiceImpl;
import es.sportradar.football.worldcup.scoreboard.domain.ScoreBoardService;
import es.sportradar.football.worldcup.scoreboard.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class ScoreBoardServiceTest {

    ScoreBoardService scoreBoardService;

    private static final String SAMPLE_TEAM_1 = "Argentina";
    private static final String SAMPLE_TEAM_2 = "France";
    private static final String SAMPLE_TEAM_3 = "Spain";
    private static final String SAMPLE_TEAM_4 = "Poland";
    private static final String SAMPLE_TEAM_5 = "Croacia";
    private static final String SAMPLE_TEAM_6 = "Marocco";
    private static final int SAMPLE_TEAM_1_SCORE = 3;
    private static final int SAMPLE_TEAM_2_SCORE = 3;
    private static final int SAMPLE_TEAM_3_SCORE = 1;
    private static final int SAMPLE_TEAM_4_SCORE = 1;
    private static final int SAMPLE_TEAM_5_SCORE = 2;
    private static final int SAMPLE_TEAM_6_SCORE = 4;
    private static final int INITIAL_SCORE = 0;

    @BeforeEach
    void initTest() {
        scoreBoardService = new ScoreBoardServiceImpl();
    }

    @Test
    void testStartGameIdGreaterThanZero() {
        assertTrue(scoreBoardService.startGame(SAMPLE_TEAM_1, SAMPLE_TEAM_2) > 0);
    }

    @Test
    void testStartGameTwoGameDifferentIds() {
        assertTrue(scoreBoardService.startGame(SAMPLE_TEAM_1, SAMPLE_TEAM_2)
                != scoreBoardService.startGame(SAMPLE_TEAM_3, SAMPLE_TEAM_4));
    }

    @Test
    void testStartGameInitScore() {
        scoreBoardService.startGame(SAMPLE_TEAM_1, SAMPLE_TEAM_2);
        Game game = scoreBoardService.getSummaryOfGamesByTotalScore().get(0);
        assertEquals(INITIAL_SCORE, game.getHomeTeamScore());
        assertEquals(INITIAL_SCORE, game.getAwayTeamScore());
    }

    @Test
    void testFinishGameFinishAllGames() {
        int gameOneId = scoreBoardService.startGame(SAMPLE_TEAM_1, SAMPLE_TEAM_2);
        scoreBoardService.finishGame(gameOneId);
        int gameTwoId = scoreBoardService.startGame(SAMPLE_TEAM_3, SAMPLE_TEAM_4);
        scoreBoardService.finishGame(gameTwoId);
        List<Game> currentGames = scoreBoardService.getSummaryOfGamesByTotalScore();
        assertTrue(currentGames.isEmpty());
    }

    @Test
    void testFinishGameFinishNotAllGames() {
        int gameOneId = scoreBoardService.startGame(SAMPLE_TEAM_1, SAMPLE_TEAM_2);
        scoreBoardService.startGame(SAMPLE_TEAM_3, SAMPLE_TEAM_4);
        scoreBoardService.finishGame(gameOneId);
        List<Game> currentGames = scoreBoardService.getSummaryOfGamesByTotalScore();
        assertTrue(currentGames.size() > 0);
    }

    @Test
    void testUpdateScore() {
        int gameId = scoreBoardService.startGame(SAMPLE_TEAM_5, SAMPLE_TEAM_6);
        scoreBoardService.updateGame(gameId, SAMPLE_TEAM_5_SCORE, SAMPLE_TEAM_6_SCORE );
        Game game = scoreBoardService.getSummaryOfGamesByTotalScore().get(0);
        assertEquals(SAMPLE_TEAM_5_SCORE, game.getHomeTeamScore());
        assertEquals(SAMPLE_TEAM_6_SCORE, game.getAwayTeamScore());
    }

    @Test
    void testGetSummaryOfGamesByTotalScore() {
        int gameOneId = scoreBoardService.startGame(SAMPLE_TEAM_1, SAMPLE_TEAM_2);
        scoreBoardService.updateGame(gameOneId, SAMPLE_TEAM_1_SCORE, SAMPLE_TEAM_2_SCORE );

        int gameTwoId = scoreBoardService.startGame(SAMPLE_TEAM_3, SAMPLE_TEAM_4);
        scoreBoardService.updateGame(gameTwoId, SAMPLE_TEAM_3_SCORE, SAMPLE_TEAM_4_SCORE );

        int gameThreeId = scoreBoardService.startGame(SAMPLE_TEAM_5, SAMPLE_TEAM_6);
        scoreBoardService.updateGame(gameThreeId, SAMPLE_TEAM_5_SCORE, SAMPLE_TEAM_6_SCORE );

        List<Game> summaryOfGames = scoreBoardService.getSummaryOfGamesByTotalScore();
        assertEquals(gameThreeId, summaryOfGames.get(0).getId());
        assertEquals(gameOneId, summaryOfGames.get(1).getId());
        assertEquals(gameTwoId, summaryOfGames.get(2).getId());
    }
}
