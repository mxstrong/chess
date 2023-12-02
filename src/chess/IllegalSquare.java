package chess;

public class IllegalSquare extends Square {
    public IllegalSquare() {
        super();
        this.isLegal = false;
    }
}
