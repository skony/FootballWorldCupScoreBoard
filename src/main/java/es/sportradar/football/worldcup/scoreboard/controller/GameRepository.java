package es.sportradar.football.worldcup.scoreboard.controller;

import es.sportradar.football.worldcup.scoreboard.model.Game;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameRepository {

    private static final int INITIAL_NUMBER_OF_GAMES = 0;
    private static final int INITIAL_SCORE = 0;

    private List<Game> games;
    private int gameSequencer;

    public GameRepository() {
        games = new ArrayList<>();
        gameSequencer = INITIAL_NUMBER_OF_GAMES;
    }

    public int addGame(String homeTeam, String awayTeam) {
        gameSequencer = gameSequencer + 1;
        Game game = new Game.GameBuilder()
                .withId(gameSequencer)
                .withHomeTeam(homeTeam)
                .withAwayTeam(awayTeam)
                .withHomeTeamScore(INITIAL_SCORE)
                .withAwayTeamScore(INITIAL_SCORE)
                .build();
        games.add(game);
        return gameSequencer;
    }

    public Optional<Game> getGame(int id){
        return games.stream()
                .filter(game -> game.getId() == id)
                .findFirst();
    }

    public List<Game> getAllGames() {
        return games;
    }

    public void removeGame(int id) {
        games.removeIf(game -> game.getId() == id);
    }

    public void updateGame(int id, int homeTeamScore, int awayTeamScore) {
        getGame(id).ifPresent(game -> {
            game.setHomeTeamScore(homeTeamScore);
            game.setAwayTeamScore(awayTeamScore);
        });
    }
}
