package chess;

public class King extends Piece {

    public King(String coordinate, boolean white, Board board) {
        super("K", coordinate, white, board);
    }

    @Override
    public boolean canMoveTo(String coordinate) {
        Square desiredSquare = board.getSquareFromCoordinate(coordinate);
        if (!validDestinationSquare(desiredSquare)) {
            return false;
        }
        if (Math.abs(positionOnBoard.row - desiredSquare.row) == 1 && positionOnBoard.column == desiredSquare.column) {
            return true;
        }
        if (Math.abs(positionOnBoard.column - desiredSquare.column) == 1 && positionOnBoard.row == desiredSquare.row) {
            return true;
        }
        if (Math.abs(positionOnBoard.row - desiredSquare.row) == 1 && Math.abs(positionOnBoard.column - desiredSquare.column) == 1) {
            return true;
        }
        return false;
    }
}
