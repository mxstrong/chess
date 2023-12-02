package chess;

public class Square {
    public int row;
    public int column;
    public String coordinate;
    public boolean isWhite;

    public boolean isOccupied = false;
    public String occupiedBy = " ";
    public Boolean occupiedByWhite = null;
    public boolean isLegal = true;
    public Square() {}
    public Square(int row, int column, String coordinate, boolean isWhite, boolean isOccupied, String occupiedBy, boolean occupiedByWhite) {
        this.row = row;
        this.column = column;
        this.coordinate = coordinate;
        this.isWhite = isWhite;
        this.occupiedByWhite = occupiedByWhite;
        this.isOccupied = isOccupied;
        this.occupiedBy = occupiedBy;
    }
}
