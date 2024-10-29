package org.example.chessgui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class GuiBoard {

    @FXML
    private Button a1, a2, a3, a4, a5, a6, a7, a8;
    @FXML
    private Button b1, b2, b3, b4, b5, b6, b7, b8;
    @FXML
    private Button c1, c2, c3, c4, c5, c6, c7, c8;
    @FXML
    private Button d1, d2, d3, d4, d5, d6, d7, d8;
    @FXML
    private Button e1, e2, e3, e4, e5, e6, e7, e8;
    @FXML
    private Button f1, f2, f3, f4, f5, f6, f7, f8;
    @FXML
    private Button g1, g2, g3, g4, g5, g6, g7, g8;
    @FXML
    private Button h1, h2, h3, h4, h5, h6, h7, h8;

    @FXML
    private Button resginButton;

    @FXML
    private Label turnLabel,blackScoreLabel,whiteScoreLabel;

    @FXML
    private AnchorPane anchorPane;

    private Pieces board;
    private COLOR turn;
    private HashSet<Button> buttons;
    private HashSet<Box> clickedButtonPotenMoves;
    private Piece toMove;
    private boolean endGame;
    private int whiteScore,blackScore;


    public void initialize() {
        whiteScore = 0;
        blackScore = 0;
        whiteScoreLabel.setText("White: "+0);
        blackScoreLabel.setText("Black: "+0);
        board = new Pieces();
        turn = COLOR.W;
        resginButton.setDisable(false);
        turnLabel.setText("White turn");
        endGame = false;
        initButtonsIcons();
        board.updatePotenMove(turn);
        clickedButtonPotenMoves = new HashSet<>();
        setCursor();
    }


    private void initButtonsIcons() {
        buttons = new HashSet<>();
        buttons.add(a1);buttons.add(a2);buttons.add(a3);buttons.add(a4);buttons.add(a5);buttons.add(a6);buttons.add(a7);buttons.add(a8);
        buttons.add(b1);buttons.add(b2);buttons.add(b3);buttons.add(b4);buttons.add(b5);buttons.add(b6);buttons.add(b7);buttons.add(b8);
        buttons.add(c1);buttons.add(c2);buttons.add(c3);buttons.add(c4);buttons.add(c5);buttons.add(c6);buttons.add(c7);buttons.add(c8);
        buttons.add(d1);buttons.add(d2);buttons.add(d3);buttons.add(d4);buttons.add(d5);buttons.add(d6);buttons.add(d7);buttons.add(d8);
        buttons.add(e1);buttons.add(e2);buttons.add(e3);buttons.add(e4);buttons.add(e5);buttons.add(e6);buttons.add(e7);buttons.add(e8);
        buttons.add(f1);buttons.add(f2);buttons.add(f3);buttons.add(f4);buttons.add(f5);buttons.add(f6);buttons.add(f7);buttons.add(f8);
        buttons.add(g1);buttons.add(g2);buttons.add(g3);buttons.add(g4);buttons.add(g5);buttons.add(g6);buttons.add(g7);buttons.add(g8);
        buttons.add(h1);buttons.add(h2);buttons.add(h3);buttons.add(h4);buttons.add(h5);buttons.add(h6);buttons.add(h7);buttons.add(h8);

        for (Piece piece : board.getBoard().values()) {
            for (Button button : buttons) {
                if (piece.getLocation().equals(Move.stringToBox(button.getText()))) {
                    String imagePath = getPieceIconPath(piece.getPieceType(), piece.getColor());
                    Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(imagePath)), 60, 60, true, true);
                    button.setGraphic(new ImageView(img));
                }
            }
        }
    }

    public static String getPieceIconPath(ID id, COLOR color) {
        return switch (id) {
            case ID.Pawn -> (color == COLOR.W) ? "/icons/WPawn.png" : "/icons/BPawn.png";
            case ID.Bishop -> (color == COLOR.W) ? "/icons/WBishop.png" : "/icons/BBishop.png";
            case ID.Knight -> (color == COLOR.W) ? "/icons/WKnight.png" : "/icons/BKnight.png";
            case ID.Queen -> (color == COLOR.W) ? "/icons/WQueen.png" : "/icons/BQueen.png";
            case ID.Rook -> (color == COLOR.W) ? "/icons/WRook.png" : "/icons/BRook.png";
            case ID.King -> (color == COLOR.W) ? "/icons/WKing.png" : "/icons/BKing.png";
        };
    }


    @FXML
    public void buttonPress(ActionEvent event) throws IOException {
        String clicked = ((Button) event.getSource()).getText();
        Box clickedBox = Move.stringToBox(clicked);
        // check if there is a piece in the clicked button location
        if (!clickedButtonPotenMoves.isEmpty()) {
            if (!board.getBoard().containsKey(clickedBox) && !clickedButtonPotenMoves.contains(clickedBox)) return;
        } else {
            if (!board.getBoard().containsKey(clickedBox)) return;
        }
        // if this is event for moving a piece
        if (clickedButtonPotenMoves.contains(clickedBox)) {
            guiMakeMove(clickedBox);
            board.updatePotenMove(COLOR.NOT(turn));
            gameStatus();
            resetButtonColor();
            turn = COLOR.NOT(turn);
            if(!endGame)turnLabel.setText(turn.toFullString()+" turn");
            setCursor();
            clickedButtonPotenMoves.clear();

        } else { //if this is just for choosing a piece to move
            Piece choice = board.getBoard().get(clickedBox);
            if (choice.getColor().equals(COLOR.NOT(turn))) return;
            clickedButtonPotenMoves.clear();
            clickedButtonPotenMoves.addAll(choice.getPotenMoves());
            toMove = choice.makeCopy();
            resetButtonColor();
            paintPotenMoves(choice);
        }
    }

    @FXML
    private void newGameButtonPressed(){
        for(Button button : buttons){
            button.setGraphic(null);
            button.setDisable(false);
        }
        resetButtonColor();
        initialize();
    }

    @FXML
    private void resginButtonPressed(){
        turnLabel.setText(turn.toFullString()+" resigned\n"+COLOR.NOT(turn).toFullString() +" Wins!!");
        for (Button button : buttons){
            button.setDisable(true);
        }
    }

    // this method paint all potential moves - buttons and location -  button of a piece
    private void paintPotenMoves(Piece piece) {
        for (Box box : piece.getPotenMoves()) {
            for (Button button : buttons) {
                if (box.equals(Move.stringToBox(button.getText()))) {
                    button.setStyle("-fx-background-color: #cfbfa7 ; -fx-background-radius: 0 ; -fx-text-fill:  transparent ;-fx-border-color: #0a0a0a ; -fx-border-width: 1 ");
                }
                if(piece.getLocation().equals(Move.stringToBox(button.getText()))){
                    button.setStyle("-fx-background-color: #cfbfa7 ; -fx-background-radius: 0 ; -fx-text-fill:  transparent ;-fx-border-color: #ffffff ; -fx-border-width: 4 ");
                }
            }
        }
    }

    private void resetButtonColor() {
        HashSet<Character> oddColl = new HashSet<>();
        oddColl.add('a');
        oddColl.add('c');
        oddColl.add('e');
        oddColl.add('g');
        for (Button button : buttons) {
            Box box = Move.stringToBox(button.getText());
            assert box != null;
            if (oddColl.contains(box.getCol())) {
                if (box.getRow() % 2 == 0) {
                    button.setStyle("-fx-background-color: #00000000;-fx-background-radius: 0;-fx-text-fill:  transparent");
                } else {
                    button.setStyle("-fx-background-color: #8d5018;-fx-background-radius: 0;-fx-text-fill:  transparent");
                }
            } else {
                if (box.getRow() % 2 == 0) {
                    button.setStyle("-fx-background-color: #8d5018;-fx-background-radius: 0;-fx-text-fill:  transparent");
                } else {
                    button.setStyle("-fx-background-color: #00000000;-fx-background-radius: 0;-fx-text-fill:  transparent");
                }
            }
        }
    }

    // this method move the UI objects and call the "logic" make move method
    private void guiMakeMove(Box destination) throws IOException {
        // update score
        if(board.getBoard().containsKey(destination)){
            if(board.getBoard().get(destination).getColor().equals(COLOR.NOT(turn))){
                if(turn.equals(COLOR.W)){
                    whiteScore += board.getBoard().get(destination).getScore();
                    whiteScoreLabel.setText("White: "+whiteScore);
                }
                else {
                    blackScore += board.getBoard().get(destination).getScore();
                    blackScoreLabel.setText("Black: "+blackScore);
                }
            }
        }
        // if its case of castling
        if (toMove.getPieceType().equals(ID.King) && Math.abs(destination.getCol() - toMove.getLocation().getCol()) == 2) {
            guiCastle(destination);
        } else {
            for (Button button : buttons) {
                if (toMove.getLocation().equals(Move.stringToBox(button.getText()))) {
                    button.setGraphic(null);
                }
                if (destination.equals(Move.stringToBox(button.getText()))) {
                    String iconPath = getPieceIconPath(toMove.getPieceType(), toMove.getColor());
                    Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)), 60, 60, true, true);
                    button.setGraphic(new ImageView(img));
                }
            }
        }
        makeMove(destination, toMove);
    }


    private void guiCastle(Box destination) {
        if (destination.getCol() == 'g' && destination.getRow() == BOARD_LIMIT.FIRST_ROW.getRowVal()) {
            h1.setGraphic(null);
            e1.setGraphic(null);
            String iconPath = getPieceIconPath(ID.Rook, COLOR.W);
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)), 60, 60, true, true);
            f1.setGraphic(new ImageView(img));
            iconPath = getPieceIconPath(ID.King, COLOR.W);
            img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)), 60, 60, true, true);
            g1.setGraphic(new ImageView(img));
        }
        if (destination.getCol() == 'c' && destination.getRow() == BOARD_LIMIT.FIRST_ROW.getRowVal()) {
            a1.setGraphic(null);
            e1.setGraphic(null);
            String iconPath = getPieceIconPath(ID.Rook, COLOR.W);
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)), 60, 60, true, true);
            d1.setGraphic(new ImageView(img));
            iconPath = getPieceIconPath(ID.King, COLOR.W);
            img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)), 60, 60, true, true);
            c1.setGraphic(new ImageView(img));
        }
        if (destination.getCol() == 'g' && destination.getRow() == BOARD_LIMIT.LAST_ROW.getRowVal()) {
            h8.setGraphic(null);
            e8.setGraphic(null);
            String iconPath = getPieceIconPath(ID.Rook, COLOR.B);
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)), 60, 60, true, true);
            f8.setGraphic(new ImageView(img));
            iconPath = getPieceIconPath(ID.King, COLOR.B);
            img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)), 60, 60, true, true);
            g8.setGraphic(new ImageView(img));
        }
        if (destination.getCol() == 'c' && destination.getRow() == BOARD_LIMIT.LAST_ROW.getRowVal()) {
            a8.setGraphic(null);
            e8.setGraphic(null);
            String iconPath = getPieceIconPath(ID.Rook, COLOR.B);
            Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)), 60, 60, true, true);
            d8.setGraphic(new ImageView(img));
            iconPath = getPieceIconPath(ID.King, COLOR.B);
            img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)), 60, 60, true, true);
            c8.setGraphic(new ImageView(img));
        }
    }

    // this method shows a query promotion prompt
    private Piece promotionQuery(Box destenation) throws IOException {
        anchorPane.setDisable(true);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Promotion.fxml"));
        Parent root = loader.load();

        ShareData shareData = new ShareData();
        PromotionController promotionController = loader.getController();
        int row = (turn == COLOR.W) ? -1 : 1;
        promotionController.initPromotion(shareData, turn, new Box(destenation.getCol(), destenation.getRow() + row));

        Stage stage = new Stage();
        stage.setTitle("Promotion Query");
        stage.setScene(new Scene(root));
        stage.setAlwaysOnTop(true);
        stage.setResizable(false);
        stage.showAndWait();

        for (Button button : buttons) {
            if (destenation.equals(Move.stringToBox(button.getText()))) {
                String iconPath = getPieceIconPath(shareData.getPiece().getPieceType(), shareData.getPiece().getColor());
                Image img = new Image(Objects.requireNonNull(getClass().getResourceAsStream(iconPath)), 60, 60, true, true);
                button.setGraphic(new ImageView(img));
            }
        }
        anchorPane.setDisable(false);
        return shareData.getPiece();
    }

     /**
     * This function handle the moving procedure
     * if the piece is a king - handle castling and native king move
     * if the piece is a pawn - handle promotion, enPassant and native pawn move
     * else handle the other pieces native moves
     * @param destination - the destination to move
     * @param piece - the piece to move
     */
    private void makeMove(Box destination, Piece piece) throws IOException {
        if (piece.isValidMove(destination)) {
            if (piece.getPieceType() == ID.King) {
                King king = (King) piece;
                if (king.canCastleK(board) && destination.equals(king.getAfterKCastleBox())) {
                    board.pieceMove(destination, king);
                    board.pieceMove(king.getMiddleKCastleBox(), king.getKingSideRook());
                } else if (king.canCastleQ(board) && destination.equals(king.getAfterQCastleBox())) {
                    board.pieceMove(destination, king);
                    board.pieceMove(king.getMiddleQCastleBox(), king.getQueenSideRook());
                } else {
                    if (Math.abs(destination.getCol() - king.getLocation().getCol()) <= 1) {
                        board.pieceMove(destination, king);
                    }
                }
            } else if (piece.getPieceType() == ID.Pawn) {
                Pawn pawn = (Pawn) piece;
                if (pawn.canPromote()) {
                    pawn.setPromtedPiece(promotionQuery(destination));
                    Piece promote = pawn.getPromtedPiece();
                    board.addPiece(promote);
                    board.pieceMove(destination, promote);
                } else if (destination.equals(pawn.getEnPassantRight())) {
                    board.pieceMove(destination, pawn);
                    if (pawn.getColor() == COLOR.W) {
                        Box removeBox = new Box(destination.getCol(), destination.getRow() - 1);
                        board.removePiece(removeBox);
                        whiteScore += 1;
                        whiteScoreLabel.setText("White: "+whiteScore);
                        for (Button button : buttons){
                            if(Objects.requireNonNull(Move.stringToBox(button.getText())).equals(removeBox)){
                                button.setGraphic(null);
                            }
                        }
                    } else {
                        Box removeBox = new Box(destination.getCol(), destination.getRow() + 1);
                        board.removePiece(removeBox);
                        blackScore += 1;
                        blackScoreLabel.setText("Black: "+blackScore);
                        for (Button button : buttons){
                            if(Objects.requireNonNull(Move.stringToBox(button.getText())).equals(removeBox)){
                                button.setGraphic(null);
                            }
                        }
                    }
                } else if (destination.equals(pawn.getEnPassantLeft())) {
                    board.pieceMove(destination, pawn);
                    if (pawn.getColor() == COLOR.W) {
                        Box removeBox = new Box(destination.getCol(), destination.getRow() - 1);
                        board.removePiece(removeBox);
                        whiteScore += 1;
                        whiteScoreLabel.setText("White: "+whiteScore);
                        for (Button button : buttons){
                            if(Objects.requireNonNull(Move.stringToBox(button.getText())).equals(removeBox)){
                                button.setGraphic(null);
                            }
                        }
                    } else {
                        Box removeBox = new Box(destination.getCol(), destination.getRow() + 1);
                        board.removePiece(removeBox);
                        blackScore += 1;
                        blackScoreLabel.setText("Black: "+blackScore);
                        for (Button button : buttons){
                            if(Objects.requireNonNull(Move.stringToBox(button.getText())).equals(removeBox)){
                                button.setGraphic(null);
                            }
                        }
                    }
                } else {
                    board.pieceMove(destination, pawn);
                }
            } else {
                //  not a king nor pawn
                board.pieceMove(destination, piece);
            }
            board.boardProgress.add(board.copyBoard(board.getBoard()));
        }
    }

    // this method set a hand cursor for the active player piece and default cursor for non-active
    private void setCursor(){
        for (Button button : buttons){
            if(endGame){
                button.setCursor(Cursor.DEFAULT);
                continue;
            }
            if(board.getBoard().containsKey(Move.stringToBox(button.getText()))){
                if(board.getBoard().get(Move.stringToBox(button.getText())).getColor().equals(turn)){
                    button.setCursor(Cursor.HAND);
                }
                else {
                    button.setCursor(Cursor.DEFAULT);
                }
            }else if(clickedButtonPotenMoves.contains(Move.stringToBox(button.getText()))){
                button.setCursor(Cursor.HAND);
            }
            else {
                button.setCursor(Cursor.DEFAULT);
            }
        }
    }

    private void gameStatus(){
        if(board.isMate(COLOR.NOT(turn))){
            turnLabel.setText("Checkmate "+turn.toFullString() + " Wins!!");
            resginButton.setDisable(true);
            endGame = true;
        }
        if(board.isStalemate(COLOR.NOT(turn))){
            turnLabel.setText("Stalemate!!");
            resginButton.setDisable(true);
            endGame = true;
        }
        if(board.isDraw() || board.isThreeFoldDraw()){
            turnLabel.setText("The game end with draw");
            resginButton.setDisable(true);
            endGame = true;
        }
    }

}
