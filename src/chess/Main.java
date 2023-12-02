package chess;
public class Main {
    public static void main(String[] args) {
        var board = new Board();

        GameEngine engine = new GameEngine(board);
        engine.run();
    }
}