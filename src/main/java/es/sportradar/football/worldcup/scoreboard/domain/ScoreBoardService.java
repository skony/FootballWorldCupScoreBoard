package es.sportradar.football.worldcup.scoreboard.domain;

import es.sportradar.football.worldcup.scoreboard.model.Game;

import java.util.List;

public interface ScoreBoardService {

    int startGame(String homeTeam, String awayTeam);
    void finishGame(int gameId);
    void updateGame(int gameId, int homeTeamScore, int awayTeamScore);
    List<Game> getSummaryOfGamesByTotalScore();
}
