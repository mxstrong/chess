package chess;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class GameEngine {
    private final Board board;
    private List<Piece> pieces;
    public GameEngine(Board board) {
        this.board = board;
        initializePieces(board);
    }

    public void run() {
        while (true) {
            updatePieceLocations();
            BoardVisualizer.visualiseBoard(board);
            String moveRaw = requestMove();
            executeMove(moveRaw);
        }
    }

    private void executeMove(String moveRaw) {
        if (!isMoveFormatValid(moveRaw)) {
            return;
        }
        String moveFrom = moveRaw.substring(0, 2);
        String moveTo = moveRaw.substring(3, 5);
        if (!areMoveCoordinatesValid(moveFrom, moveTo)) {
            return;
        }
        tryMoving(moveFrom, moveTo);
    }

    private void tryMoving(String moveFrom, String moveTo) {
        Optional<Piece> pieceToMove = pieces.stream().filter(piece -> moveFrom.equals(piece.coordinate)).findAny();
        pieceToMove.ifPresentOrElse(piece -> {
            try {
                if (!piece.canMoveTo(moveTo)) {
                    System.out.println("Illegal move");
                    return;
                }
                piece.move(moveTo);
            } catch (NoSuchElementException e) {
                System.out.println("Illegal coordinates");
            }
        }, () -> System.out.println("There are no pieces at square with selected from coordinate"));
    }

    private static boolean isMoveFormatValid(String moveRaw) {
        if (moveRaw.length() != 5) {
            System.out.println("Wrong move format");
            return false;
        }
        return true;
    }

    private boolean areMoveCoordinatesValid(String moveFrom, String moveTo) {
        if (board.squares.stream().flatMap(Collection::stream).filter(square -> square.coordinate.equals(moveFrom)).findAny().isEmpty()) {
            System.out.println("Wrong from coordinate");
            return false;
        }
        if (board.squares.stream().flatMap(Collection::stream).filter(square -> square.coordinate.equals(moveTo)).findAny().isEmpty()) {
            System.out.println("Wrong to coordinate");
            return false;
        }
        return true;
    }

    private static String requestMove() {
        Scanner scanner = new Scanner(System.in); //System.in is a standard input stream
        System.out.println("Enter your move ([from coordinate] [to coodinate]): ");
        return scanner.nextLine();
    }

    private void updatePieceLocations() {
        for (Piece piece : pieces) {
            Square pieceSquare = board.squares.stream().flatMap(Collection::stream).filter(square -> square.coordinate.equals(piece.coordinate)).findFirst().get();
            pieceSquare.isOccupied = true;
            pieceSquare.occupiedBy = piece.getName();
            pieceSquare.occupiedByWhite = piece.white;
        }
    }

    @NotNull
    private void initializePieces(Board board) {
        pieces = new ArrayList<>();
        pieces.add(new Pawn("a2", true, board));
        pieces.add(new Pawn("b2", true, board));
        pieces.add(new Pawn("c2", true, board));
        pieces.add(new Pawn("d2", true, board));
        pieces.add(new Pawn("e2", true, board));
        pieces.add(new Pawn("f2", true, board));
        pieces.add(new Pawn("g2", true, board));
        pieces.add(new Pawn("h2", true, board));
        pieces.add(new Rook("a1", true, board));
        pieces.add(new Knight("b1", true, board));
        pieces.add(new Bishop("c1", true, board));
        pieces.add(new Queen("d1", true, board));
        pieces.add(new King("e1", true, board));
        pieces.add(new Bishop("f1", true, board));
        pieces.add(new Knight("g1", true, board));
        pieces.add(new Rook("h1", true, board));

        pieces.add(new Pawn("a7", false, board));
        pieces.add(new Pawn("b7", false, board));
        pieces.add(new Pawn("c7", false, board));
        pieces.add(new Pawn("d7", false, board));
        pieces.add(new Pawn("e7", false, board));
        pieces.add(new Pawn("f7", false, board));
        pieces.add(new Pawn("g7", false, board));
        pieces.add(new Pawn("h7", false, board));
        pieces.add(new Rook("a8", false, board));
        pieces.add(new Knight("b8", false, board));
        pieces.add(new Bishop("c8", false, board));
        pieces.add(new Queen("d8", false, board));
        pieces.add(new King("e8", false, board));
        pieces.add(new Bishop("f8", false, board));
        pieces.add(new Knight("g8", false, board));
        pieces.add(new Rook("h8", false, board));
    }
}
