package es.sportradar.football.worldcup.scoreboard.model;

public class Game {

    private int id;
    private String homeTeam;
    private String awayTeam;
    private int homeTeamScore;
    private int awayTeamScore;

    private Game() {}

    public int getId() {
        return id;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    public static class GameBuilder {

        private int id;
        private String homeTeam;
        private String awayTeam;
        private int homeTeamScore;
        private int awayTeamScore;

        public GameBuilder withId(int id) {
            this.id = id;
            return this;
        }

        public GameBuilder withHomeTeam(String homeTeam) {
            this.homeTeam = homeTeam;
            return this;
        }

        public GameBuilder withAwayTeam(String awayTeam) {
            this.awayTeam = awayTeam;
            return this;
        }

        public GameBuilder withHomeTeamScore(int homeTeamScore) {
            this.homeTeamScore = homeTeamScore;
            return this;
        }

        public GameBuilder withAwayTeamScore(int awayTeamScore) {
            this.awayTeamScore = awayTeamScore;
            return this;
        }

        public Game build() {
            Game game = new Game();
            game.setHomeTeamScore(homeTeamScore);
            game.setAwayTeamScore(awayTeamScore);
            game.id = this.id;
            game.homeTeam = this.homeTeam;
            game.awayTeam = this.awayTeam;
            return game;
        }
    }
}
