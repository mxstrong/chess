package chess;

public class Bishop extends Piece {

    public Bishop(String coordinate, boolean white, Board board) {
        super("B", coordinate, white, board);
    }

    @Override
    public boolean canMoveTo(String coordinate) {
        Square desiredSquare = board.getSquareFromCoordinate(coordinate);
        if (!validDestinationSquare(desiredSquare)) {
            return false;
        }
        if (desiredSquare.isWhite != positionOnBoard.isWhite) {
            return false;
        }
        if (Math.abs(Board.COORDINATE_LETTERS.indexOf(this.coordinate.substring(0, 1)) - Board.COORDINATE_LETTERS.indexOf(desiredSquare.coordinate.substring(0, 1))) != Math.abs(positionOnBoard.row - desiredSquare.row)) {
            return false;
        }
        if (desiredSquare.row > positionOnBoard.row) {
            for (int i = positionOnBoard.row; i < desiredSquare.row; i++) {
                if (board.squares.get(i).get(i).isOccupied) {
                    return false;
                }
            }
            return true;
        }
        if (desiredSquare.row < positionOnBoard.row) {
            for (int i = positionOnBoard.row - 1; i < desiredSquare.row; i++) {
                if (board.squares.get(i).get(i).isOccupied) {
                    return false;
                }
            }
        }
        return false;
    }
}
