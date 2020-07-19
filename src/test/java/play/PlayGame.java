package play;

import tictactoe.Game;

import java.util.Random;

public class PlayGame {

    public static void main(String... args) {
        var game = new Game();
        var board = game.getBoard();

        while (!game.isThereAWinner(7)) {
            while (!game.isOver()) {
                var spaces = board.getOpenSpaces();
                board.play(spaces.get(new Random().nextInt(spaces.size())));
            }
            board = game.restart();
        }
        game.printResults();
        game.end();
    }
}
