package chess;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class Board {
    public static final String COORDINATE_LETTERS = "abcdefgh";

    public final List<List<Square>> squares = new ArrayList<>();

    public Board() {
        boolean isWhiteSquare = true;
        for (int i = 0; i < 8; i++) {
            List<Square> row = new ArrayList<>();
            for (int j = 0; j < 8; j++) {
                row.add(new Square(i, j, COORDINATE_LETTERS.charAt(j) + String.valueOf(8 - i), isWhiteSquare, false, " ", false));
                isWhiteSquare = !isWhiteSquare;
            }
            squares.add(row);
            isWhiteSquare = !isWhiteSquare;
        }
    }

    public Square getSquareFromCoordinate(String coordinate) {
        Optional<Square> squareFromCoordinate = squares.stream().flatMap(Collection::stream).filter(square -> square.coordinate.equals(coordinate)).findFirst();
        return squareFromCoordinate.orElseGet(IllegalSquare::new);
    }
}
