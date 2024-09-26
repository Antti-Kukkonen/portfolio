import java.util.HashMap;

public class CoinTossFirstTo3 extends Game {
    // This game heavily favors the first player.
    private HashMap<Integer, Integer> scores;

    @Override
    public void initializeGame(int numberOfPlayers) {
        scores = new HashMap<>();
        for (int i = 0; i < numberOfPlayers; i++) {
            scores.put(i, 0);
        }
    }

    @Override
    public boolean endOfGame() {
        for (int score : scores.values()) {
            if (score == 3) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void playSingleTurn(int player) {
        int score = scores.get(player);
        if (Math.random() < 0.5) {
            scores.put(player, score + 1);
            System.out.println("Player " + (player + 1) + " got a point!");
        } else {
            System.out.println("Player " + (player + 1) + " missed!");
        }
    }

    @Override
    public void displayWinner() {
        for (int player : scores.keySet()) {
            if (scores.get(player) == 3) {
                System.out.println("Player " + (player + 1) + " wins!");
            }
        }
    }
}
