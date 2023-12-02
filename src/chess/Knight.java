package chess;

public class Knight extends Piece {

    public Knight(String coordinate, boolean white, Board board) {
        super("N", coordinate, white, board);
    }

    @Override
    public boolean canMoveTo(String coordinate) {
        Square desiredSquare = board.getSquareFromCoordinate(coordinate);
        if (!validDestinationSquare(desiredSquare)) {
            return false;
        }
        if (Math.abs(positionOnBoard.row - desiredSquare.row) == 1 && Math.abs(positionOnBoard.column - desiredSquare.column) == 2) {
            return true;
        }
        if (Math.abs(positionOnBoard.row - desiredSquare.row) == 2 && Math.abs(positionOnBoard.column - desiredSquare.column) == 1) {
            return true;
        }
        return false;
    }
}
