package chess;

public abstract class Piece implements Movable {

    private String name;
    protected String coordinate;
    protected boolean white;
    protected Square positionOnBoard;

    protected Board board;

    private Piece() {}

    protected Piece(String name, String coordinate, boolean white, Board board) {
        this.name = name;
        this.coordinate = coordinate;
        this.white = white;
        this.board = board;
        this.positionOnBoard = board.getSquareFromCoordinate(coordinate);
    }

    protected boolean validDestinationSquare(Square desiredSquare) {
        if (!desiredSquare.isLegal) {
            return false;
        }
        if (desiredSquare.isOccupied && (desiredSquare.occupiedByWhite == white)) {
            return false;
        }
        if (desiredSquare.row < 0 || desiredSquare.row > 7 || desiredSquare.column < 0 || desiredSquare.column > 7) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public void move(String moveToCoordinate) {
        positionOnBoard.isOccupied = false;
        positionOnBoard.occupiedBy = " ";
        coordinate = moveToCoordinate;
        positionOnBoard.occupiedByWhite = false;
    }

}
