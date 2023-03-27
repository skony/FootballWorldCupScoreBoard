package es.sportradar.football.worldcup.scoreboard.controller;

import es.sportradar.football.worldcup.scoreboard.model.Game;

import java.util.Comparator;

public class GameTotalScoreComparator implements Comparator<Game> {

    @Override
    public int compare(Game gameOne, Game gameTwo) {
        if(getTotalScore(gameOne) != getTotalScore(gameTwo)) {
            return Integer.compare(getTotalScore(gameTwo), getTotalScore(gameOne));
        }
        return Integer.compare(gameTwo.getId(), gameOne.getId());
    }

    private int getTotalScore(Game game) {
        return game.getHomeTeamScore() + game.getAwayTeamScore();
    }
}
