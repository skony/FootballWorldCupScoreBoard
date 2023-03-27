package es.sportradar.football.worldcup.scoreboard.controller;

import es.sportradar.football.worldcup.scoreboard.domain.ScoreBoardService;
import es.sportradar.football.worldcup.scoreboard.model.Game;

import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoardServiceImpl implements ScoreBoardService {

    private GameRepository gameRepository;

    public ScoreBoardServiceImpl() {
        gameRepository = new GameRepository();
    }

    public int startGame(String homeTeam, String awayTeam) {
        return gameRepository.addGame(homeTeam, awayTeam);
    }

    public void finishGame(int gameId) {
        gameRepository.removeGame(gameId);
    }

    public void updateGame(int gameId, int homeTeamScore, int awayTeamScore) {
        gameRepository.updateGame(gameId, homeTeamScore, awayTeamScore);
    }

    public List<Game> getSummaryOfGamesByTotalScore() {
        return gameRepository.getAllGames().stream()
                .sorted(new GameTotalScoreComparator())
                .collect(Collectors.toList());
    }
}
