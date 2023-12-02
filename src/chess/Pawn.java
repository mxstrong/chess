package chess;

public class Pawn extends Piece {
    public Pawn(String coordinate, boolean white, Board board) {
        super("P", coordinate, white, board);
    }

    @Override
    public boolean canMoveTo(String coordinate) {
        Square desiredSquare = board.getSquareFromCoordinate(coordinate);
        if (!validDestinationSquare(desiredSquare)) {
            return false;
        }
        if (positionOnBoard.column == desiredSquare.column
                && Math.abs(positionOnBoard.row - desiredSquare.row) == 1
                && !desiredSquare.isOccupied) {
            return true;
        }
        if (desiredSquare.isOccupied
                && Math.abs(positionOnBoard.row - desiredSquare.row) == 1
                && Math.abs(positionOnBoard.column - desiredSquare.column) == 1) {
            return true;
        }
        if ((positionOnBoard.row == 2 || positionOnBoard.row == 7)
                && positionOnBoard.column == desiredSquare.column
                && Math.abs(positionOnBoard.row - desiredSquare.row) == 2
                && !desiredSquare.isOccupied) {
            return true;
        }
        return false;
    }
}
