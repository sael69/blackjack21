package models;

public class Result {
    private ResultType type;
    private String winner;
    private int score;

    public Result(ResultType type, String winner, int score) {
        this.type = type;
        this.winner = winner;
        this.score = score;
    }

    public ResultType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Tipo: " + type + ", Vencedor: " + winner + ", Pontuação: " + score;
    }
}
