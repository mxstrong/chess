import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main{
    public static void main(String[] args) throws IOException {
        var board = new Board();
        List<Piece> pieces = new ArrayList<>();
        pieces.add(new Piece("P", "a2", true));
        pieces.add(new Piece("P", "b2", true));
        pieces.add(new Piece("P", "c2", true));
        pieces.add(new Piece("P", "d2", true));
        pieces.add(new Piece("P", "e2", true));
        pieces.add(new Piece("P", "f2", true));
        pieces.add(new Piece("P", "g2", true));
        pieces.add(new Piece("P", "h2", true));
        pieces.add(new Piece("R", "a1", true));
        pieces.add(new Piece("N", "b1", true));
        pieces.add(new Piece("B", "c1", true));
        pieces.add(new Piece("Q", "d1", true));
        pieces.add(new Piece("K", "e1", true));
        pieces.add(new Piece("B", "f1", true));
        pieces.add(new Piece("N", "g1", true));
        pieces.add(new Piece("R", "h1", true));

        pieces.add(new Piece("P", "a7", false));
        pieces.add(new Piece("P", "b7", false));
        pieces.add(new Piece("P", "c7", false));
        pieces.add(new Piece("P", "d7", false));
        pieces.add(new Piece("P", "e7", false));
        pieces.add(new Piece("P", "f7", false));
        pieces.add(new Piece("P", "g7", false));
        pieces.add(new Piece("P", "h7", false));
        pieces.add(new Piece("R", "a8", false));
        pieces.add(new Piece("N", "b8", false));
        pieces.add(new Piece("B", "c8", false));
        pieces.add(new Piece("Q", "d8", false));
        pieces.add(new Piece("K", "e8", false));
        pieces.add(new Piece("B", "f8", false));
        pieces.add(new Piece("N", "g8", false));
        pieces.add(new Piece("R", "h8", false));

        while (true) {
            for (Piece piece : pieces) {
                Square pieceSquare = board.squares.stream().flatMap(Collection::stream).filter(square -> square.coordinate.equals(piece.coordinate)).findFirst().get();
                pieceSquare.isOccupied = true;
                pieceSquare.occupiedBy = piece.name;
                pieceSquare.occupiedByWhite = piece.isWhite;
            }
            ArrayList<String> boardVisual = new ArrayList<>();
            for (int i = 0; i < 8; i++) {
                boardVisual.add("*  ".repeat(8 * 5 - 7));
                StringBuilder row1 = new StringBuilder();
                StringBuilder row2 = new StringBuilder();
                StringBuilder row3 = new StringBuilder();
                for (int j = 0; j < 8; j++) {
                    Square square = board.squares.get(i).get(j);
                    row1.append(square.isWhite ? "*" + " ".repeat(11) : "*  ".repeat(4));
                    row2.append(
                            square.isWhite ?
                                    "*" + " ".repeat(4) + (square.isOccupied ? (square.occupiedByWhite ? "(" + square.occupiedBy + ")" : "["+ square.occupiedBy + "]") : "   ") + " ".repeat(4) :
                                    "*  * " + (square.isOccupied ? (square.occupiedByWhite ? "(" + square.occupiedBy + ")" : "["+ square.occupiedBy + "]") : " * ") + " " + "*  "
                    );
                    row3.append(square.isWhite ? "*" + " ".repeat(11) : "*  ".repeat(4));
                }
                boardVisual.add(row1.append("*").toString());
                boardVisual.add(row2.append("*").toString());
                boardVisual.add(row3.append("*").toString());
            }
            boardVisual.add("*  ".repeat(8 * 5 - 7));
            for (String row : boardVisual) {
                System.out.println(row);
            }
            System.out.println("\n");
            Scanner scanner = new Scanner(System.in); //System.in is a standard input stream
            System.out.println("Enter your move ([from coordinate] [to coodinate]): ");
            String moveRaw = scanner.nextLine();
            if (moveRaw.length() != 5) {
                System.out.println("Wrong move format");
                continue;
            }
            String moveFrom = moveRaw.substring(0, 2);
            String moveTo = moveRaw.substring(3, 5);
            if (board.squares.stream().flatMap(Collection::stream).filter(square -> square.coordinate.equals(moveFrom)).findAny().isEmpty()) {
                System.out.println("Wrong from coordinate");
                continue;
            }
            if (board.squares.stream().flatMap(Collection::stream).filter(square -> square.coordinate.equals(moveTo)).findAny().isEmpty()) {
                System.out.println("Wrong to coordinate");
                continue;
            }
            Optional<Piece> pieceToMove = pieces.stream().filter(piece -> moveFrom.equals(piece.coordinate)).findAny();
            pieceToMove.ifPresentOrElse(piece -> {
                try {
                    Square pieceSquare = board.squares.stream().flatMap(Collection::stream).filter(square -> square.coordinate.equals(piece.coordinate)).findFirst().get();
                    Square desiredSquare = board.squares.stream().flatMap(Collection::stream).filter(square -> square.coordinate.equals(moveTo)).findFirst().get();
                    if (!isMoveLegal(piece, pieceSquare, desiredSquare, board.squares)) {
                        System.out.println("Illegal move");
                        return;
                    }
                    pieceSquare.isOccupied = false;
                    pieceSquare.occupiedBy = " ";
                    piece.coordinate = moveTo;
                    pieceSquare.occupiedByWhite = false;
                } catch (NoSuchElementException e) {
                    System.out.println("Illegal coordinates");
                }
            }, () -> {
                System.out.println("There are no pieces at square with selected from coordinate");
            });
        }
    }

    public static boolean isMoveLegal(Piece piece, Square pieceSquare, Square desiredSquare, List<List<Square>> squares) {
        if (desiredSquare.isOccupied && (desiredSquare.occupiedByWhite == piece.isWhite)) {
            return false;
        }
        if (desiredSquare.row < 0 || desiredSquare.row > 7 || desiredSquare.column < 0 || desiredSquare.column > 7) {
            return false;
        }
        if ("N".equals(piece.name)) {
            if (Math.abs(pieceSquare.row - desiredSquare.row) == 1 && Math.abs(pieceSquare.column - desiredSquare.column) == 2) {
                return true;
            }
            if (Math.abs(pieceSquare.row - desiredSquare.row) == 2 && Math.abs(pieceSquare.column - desiredSquare.column) == 1) {
                return true;
            }
            return false;
        }
        if ("P".equals(piece.name)) {
            if (pieceSquare.column == desiredSquare.column
                    && Math.abs(pieceSquare.row - desiredSquare.row) == 1
                    && !desiredSquare.isOccupied) {
                return true;
            }
            if (desiredSquare.isOccupied
                    && Math.abs(pieceSquare.row - desiredSquare.row) == 1
                    && Math.abs(pieceSquare.column - desiredSquare.column) == 1) {
                return true;
            }
            if ((pieceSquare.row == 2 || pieceSquare.row == 7)
                    && pieceSquare.column == desiredSquare.column
                    && Math.abs(pieceSquare.row - desiredSquare.row) == 2
                    && !desiredSquare.isOccupied) {
                return true;
            }
            return false;
        }
        if ("R".equals(piece.name)) {
            if (pieceSquare.column == desiredSquare.column) {
                if (pieceSquare.row < desiredSquare.row) {
                    for (int i = pieceSquare.row + 1; i < desiredSquare.row; i++) {
                        if (squares.get(i).get(pieceSquare.column).isOccupied) {
                            return false;
                        }
                    }
                    return true;
                }
                if (pieceSquare.row > desiredSquare.row) {
                    for (int i = pieceSquare.row - 1; i > desiredSquare.row; i--) {
                        if (squares.get(i).get(pieceSquare.column).isOccupied) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            if (pieceSquare.row == desiredSquare.row) {
                if (pieceSquare.column < desiredSquare.column) {
                    for (int i = pieceSquare.column + 1; i < desiredSquare.column; i++) {
                        if (squares.get(pieceSquare.row).get(i).isOccupied) {
                            return false;
                        }
                    }
                    return true;
                }
                if (pieceSquare.column > desiredSquare.column) {
                    for (int i = pieceSquare.column - 1; i > desiredSquare.column; i--) {
                        if (squares.get(pieceSquare.row).get(i).isOccupied) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            return false;
        }
        if ("B".equals(piece.name)) {
            if (desiredSquare.isWhite != pieceSquare.isWhite) {
                return false;
            }
            if (Math.abs(Board.coordinateLetters.indexOf(piece.coordinate.substring(0, 1)) - Board.coordinateLetters.indexOf(desiredSquare.coordinate.substring(0, 1))) != Math.abs(pieceSquare.row - desiredSquare.row)) {
                return false;
            }
            if (desiredSquare.row > pieceSquare.row) {
                for (int i = pieceSquare.row; i < desiredSquare.row; i++) {
                    if (squares.get(i).get(i).isOccupied) {
                        return false;
                    }
                }
                return true;
            }
            if (desiredSquare.row < pieceSquare.row) {
                for (int i = pieceSquare.row - 1; i < desiredSquare.row; i++) {
                    if (squares.get(i).get(i).isOccupied) {
                        return false;
                    }
                }
            }
        }

        if ("Q".equals(piece.name)) {
            if (pieceSquare.column == desiredSquare.column) {
                if (pieceSquare.row < desiredSquare.row) {
                    for (int i = pieceSquare.row + 1; i < desiredSquare.row; i++) {
                        if (squares.get(i).get(pieceSquare.column).isOccupied) {
                            return false;
                        }
                    }
                    return true;
                }
                if (pieceSquare.row > desiredSquare.row) {
                    for (int i = pieceSquare.row - 1; i > desiredSquare.row; i--) {
                        if (squares.get(i).get(pieceSquare.column).isOccupied) {
                            return false;
                        }
                    }
                    return true;
                }
                return false;
            }
            if (pieceSquare.row == desiredSquare.row) {
                if (pieceSquare.column < desiredSquare.column) {
                    for (int i = pieceSquare.column + 1; i < desiredSquare.column; i++) {
                        if (squares.get(pieceSquare.row).get(i).isOccupied) {
                            return false;
                        }
                    }
                    return true;
                }
                if (pieceSquare.column > desiredSquare.column) {
                    for (int i = pieceSquare.column - 1; i > desiredSquare.column; i--) {
                        if (squares.get(pieceSquare.row).get(i).isOccupied) {
                            return false;
                        }
                    }
                    return true;
                }
            }
            if (desiredSquare.isWhite != pieceSquare.isWhite) {
                return false;
            }
            if (Math.abs(Board.coordinateLetters.indexOf(piece.coordinate.substring(0, 1)) - Board.coordinateLetters.indexOf(desiredSquare.coordinate.substring(0, 1))) != Math.abs(pieceSquare.row - desiredSquare.row)) {
                return false;
            }
            if (desiredSquare.row > pieceSquare.row) {
                for (int i = pieceSquare.row; i < desiredSquare.row; i++) {
                    if (squares.get(i).get(i).isOccupied) {
                        return false;
                    }
                }
                return true;
            }
            if (desiredSquare.row < pieceSquare.row) {
                for (int i = pieceSquare.row - 1; i < desiredSquare.row; i++) {
                    if (squares.get(i).get(i).isOccupied) {
                        return false;
                    }
                }
            }
        }

        if ("K".equals(piece.name)) {
            if (Math.abs(pieceSquare.row - desiredSquare.row) == 1 && pieceSquare.column == desiredSquare.column) {
                return true;
            }
            if (Math.abs(pieceSquare.column - desiredSquare.column) == 1 && pieceSquare.row == desiredSquare.row) {
                return true;
            }
            if (Math.abs(pieceSquare.row - desiredSquare.row) == 1 && Math.abs(pieceSquare.column - desiredSquare.column) == 1) {
                return true;
            }
            return false;
        }
        return true;
    }

    private static class Board {
        public static final String coordinateLetters = "abcdefgh";

        public final List<List<Square>> squares = new ArrayList<>();
        public Board() {
            boolean isWhiteSquare = true;
            for (int i = 0; i < 8; i++) {
                List<Square> row = new ArrayList<>();
                for (int j = 0; j < 8; j++) {
                    row.add(new Square(i, j, coordinateLetters.charAt(j) + String.valueOf(8 - i), isWhiteSquare, false, " ", false));
                    isWhiteSquare = !isWhiteSquare;
                }
                squares.add(row);
                isWhiteSquare = !isWhiteSquare;
            }
        }
    }

    private static class Square {
        public Square(int row, int column, String coordinate, boolean isWhite, boolean isOccupied, String occupiedBy, boolean occupiedByWhite) {
            this.row = row;
            this.column = column;
            this.coordinate = coordinate;
            this.isWhite = isWhite;
            this.occupiedByWhite = occupiedByWhite;
            this.isOccupied = isOccupied;
            this.occupiedBy = occupiedBy;
        }

        public int row;
        public int column;
        public String coordinate;
        public boolean isWhite;

        public boolean isOccupied = false;
        public String occupiedBy = " ";

        public boolean occupiedByWhite = false;
    }

    private static class Piece {
        String name;
        String coordinate;

        boolean isWhite;

        public Piece(String name, String coordinate, boolean isWhite) {
            this.name = name;
            this.coordinate = coordinate;
            this.isWhite = isWhite;
        }
    }
}