package chess;

public class Queen extends Piece {

    public Queen(String coordinate, boolean white, Board board) {
        super("Q", coordinate, white, board);
    }

    @Override
    public boolean canMoveTo(String coordinate) {
        Square desiredSquare = board.getSquareFromCoordinate(coordinate);
        if (!validDestinationSquare(desiredSquare)) {
            return false;
        }
        return canMoveVertically(desiredSquare) || canMoveHorizontally(desiredSquare) || canMoveDiagonically(desiredSquare);
    }

    private boolean canMoveVertically(Square desiredSquare) {
        if (positionOnBoard.column == desiredSquare.column) {
            return canMoveUpwards(desiredSquare) || canMoveDownwards(desiredSquare);
        }
        return false;
    }

    private boolean canMoveUpwards(Square desiredSquare) {
        if (positionOnBoard.row < desiredSquare.row) {
            for (int i = positionOnBoard.row + 1; i < desiredSquare.row; i++) {
                if (board.squares.get(i).get(positionOnBoard.column).isOccupied) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    private boolean canMoveDownwards(Square desiredSquare) {
        if (positionOnBoard.row > desiredSquare.row) {
            for (int i = positionOnBoard.row - 1; i > desiredSquare.row; i--) {
                if (board.squares.get(i).get(positionOnBoard.column).isOccupied) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean canMoveHorizontally(Square desiredSquare) {
        if (positionOnBoard.row == desiredSquare.row) {
            return canMoveRight(desiredSquare) || canMoveLeft(desiredSquare);
        }
        return false;
    }

    private boolean canMoveRight(Square desiredSquare) {
        if (positionOnBoard.column < desiredSquare.column) {
            for (int i = positionOnBoard.column + 1; i < desiredSquare.column; i++) {
                if (board.squares.get(positionOnBoard.row).get(i).isOccupied) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    private boolean canMoveLeft(Square desiredSquare) {
        if (positionOnBoard.column > desiredSquare.column) {
            for (int i = positionOnBoard.column - 1; i > desiredSquare.column; i--) {
                if (board.squares.get(positionOnBoard.row).get(i).isOccupied) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private boolean canMoveDiagonically(Square desiredSquare) {
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
