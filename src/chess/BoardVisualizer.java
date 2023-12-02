package chess;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BoardVisualizer {
    private BoardVisualizer() {}

    public static void visualiseBoard(Board board) {
        ArrayList<String> boardVisual = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            boardVisual.add("*  ".repeat(8 * 5 - 7));
            StringBuilder topBorder = new StringBuilder();
            StringBuilder centerRow = new StringBuilder();
            StringBuilder bottomBorder = new StringBuilder();
            for (int j = 0; j < 8; j++) {
                Square square = board.squares.get(i).get(j);
                visualiseSquare(square, topBorder, centerRow, bottomBorder);
            }
            boardVisual.add(topBorder.append("*").toString());
            boardVisual.add(centerRow.append("*").toString());
            boardVisual.add(bottomBorder.append("*").toString());
        }
        boardVisual.add("*  ".repeat(8 * 5 - 7));
        for (String row : boardVisual) {
            System.out.println(row);
        }
        System.out.println("\n");
    }

    private static void visualiseSquare( Square square, StringBuilder topBorder, StringBuilder centerRow, StringBuilder bottomBorder) {
        topBorder.append(visualiseSquareBorder(square));
        centerRow.append(
                visualiseSquareCenter(square)
        );
        bottomBorder.append(visualiseSquareBorder(square));
    }

    @NotNull
    private static String visualiseSquareCenter(Square square) {
        return square.isWhite ?
                visualiseWhiteSquareCenter(square) :
                visualiseBlackSquareCenter(square);
    }

    @NotNull
    private static String visualiseBlackSquareCenter(Square square) {
        return "*  * " + (square.isOccupied ? visualisePieceOnBlackSquare(square) : " * ") + " " + "*  ";
    }

    @NotNull
    private static String visualisePieceOnBlackSquare(Square square) {
        return square.occupiedByWhite ? visualiseWhitePiece(square) : visualiseBlackPiece(square);
    }

    @NotNull
    private static String visualiseWhiteSquareCenter(Square square) {
        return "*" + " ".repeat(4) + (square.isOccupied ? visualisePieceOnWhiteSquare(square) : "   ") + " ".repeat(4);
    }

    @NotNull
    private static String visualisePieceOnWhiteSquare(Square square) {
        return square.occupiedByWhite ? visualiseWhitePiece(square) : visualiseBlackPiece(square);
    }

    @NotNull
    private static String visualiseBlackPiece(Square square) {
        return "[" + square.occupiedBy + "]";
    }

    @NotNull
    private static String visualiseWhitePiece(Square square) {
        return "(" + square.occupiedBy + ")";
    }

    @NotNull
    private static String visualiseSquareBorder(Square square) {
        return square.isWhite ? "*" + " ".repeat(11) : "*  ".repeat(4);
    }
}
