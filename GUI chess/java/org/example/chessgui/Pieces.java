package org.example.chessgui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;


public class Pieces {
    private HashMap<Box,Piece> board; // the board of the game
    public ArrayList<HashMap<Box,Piece>> boardProgress; // use for saving all the game activities

    //____________________________________________________Constructor's___________________________________________________________
    Pieces(){
        board = Board.getChessBoard();
        boardProgress = new ArrayList<>();
        boardProgress.add(copyBoard(getBoard()));
    }

    Pieces(HashMap<Box,Piece> otherBoard){
        board = copyBoard(otherBoard);
        boardProgress = new ArrayList<>();
        boardProgress.add(copyBoard(getBoard()));
    }

    /**
     * @param otherBoard - the board to copy
     * @return - a deep copy board
     */
    public HashMap<Box,Piece> copyBoard(HashMap<Box,Piece> otherBoard){
        HashMap<Box,Piece> result = new HashMap<>();
        for(Box box : otherBoard.keySet()){
            Piece newPiece = otherBoard.get(box).makeCopy();
            Box newBox = new Box(box);
            result.put(newBox,newPiece);
        }
        return result;
    }

    // __________________________________________________Getter__________________________________________________________

    public HashMap<Box,Piece> getBoard(){return board;}

    // __________________________________________________Methods__________________________________________________________
    public void addPiece(Piece piece){
        Objects.requireNonNull(piece);
        board.put(piece.getLocation(),piece);
    }

    public void removePiece(Box location){
        if(!Box.inBoard(location)) throw new IllegalArgumentException();
        board.remove(location);
    }

    public Box getKingLocation(COLOR color){
        for(Piece piece : getBoard().values()){
            if(piece.getPieceType() == ID.King && piece.getColor() == color){
                return piece.getLocation();
            }
        }
        throw new RuntimeException("King does not exist");
    }

    public Piece getPiece(Box location){
        if(!Box.inBoard(location)) throw new IllegalArgumentException();
        return board.get(location);
    }


    public HashSet<Box> getPiecesPotenMoves(COLOR color){
        HashSet<Box> result = new HashSet<>();
        for (Box key : board.keySet()){
            if(board.get(key).getColor() == color){
                result.addAll(board.get(key).getPotenMoves());
            }
        }
        return result;
    }

    public HashSet<Box> getPiecesRawMoves(COLOR color){
        HashSet<Box> result = new HashSet<>();
        for (Box key : board.keySet()){
            if(board.get(key).getColor() == color){
                result.addAll(board.get(key).getRawMoves(this));
            }
        }
        return result;
    }

    public boolean isCheck(COLOR color){
        return getPiecesPotenMoves(COLOR.NOT(color)).contains(getKingLocation(color));
    }

    public boolean isMate(COLOR color){
        return isCheck(color) && getPiecesPotenMoves(color).isEmpty();
    }

    /**
     * @return - true if the board contained only two kings, two kings and bishop or Knight, two kings and black bishop and white bishop
     */
    public boolean isDraw(){
        if(getBoard().size() == 2){
            return true;
        } else if (getBoard().size() == 3) {
            boolean isNotKing = false;
            for(Piece piece : getBoard().values()){
                if(piece.getPieceType() == ID.Bishop || piece.getPieceType() == ID.Knight){
                    isNotKing = true;
                    break;
                }
            }
            return isNotKing;
        } else if (getBoard().size() == 4) {
            Bishop whiteBishop = null;
            Bishop blackBishop = null;
            for (Piece piece : getBoard().values()){
                if(piece.getPieceType() == ID.Bishop){
                    if (piece.getColor() == COLOR.W){
                        whiteBishop = (Bishop) piece;
                    }
                    else {
                        blackBishop = (Bishop) piece;
                    }
                }
            }
            if(whiteBishop != null && blackBishop != null){
                return whiteBishop.getInitLocation().getCol() != blackBishop.getInitLocation().getCol();
            }
            return false;
        }
        return false;
    }

    /**
     *
     * @return - true if there is three time the same board state
     */
    public boolean isThreeFoldDraw() {
        if (boardProgress.size() >= 3) {
            int counter = 0;
            for (int i = 0; i < boardProgress.size(); i++) {
                for (int j = 0; j < boardProgress.size(); j++) {
                    if (i != j) {
                        if (boardProgress.get(i).equals(boardProgress.get(j))) {
                            counter++;
                        }
                    }
                }
            }
            return counter >= 3;
        }
        return false;
    }

    public boolean isStalemate(COLOR color){
        return !isCheck(color) && getPiecesPotenMoves(color).isEmpty();
    }

    /**
     * This function  move a piece to a given destination
     * handle pawn enPassant and previous location
     * @param destination - given argument destination
     * @param piece - the piece to be moved
     */
    public void pieceMove(Box destination, Piece piece){
        Objects.requireNonNull(piece);
        if(!Box.inBoard(destination)) throw new IllegalArgumentException();

        Box currentLocation = piece.getLocation();
        piece.setLocation(destination);
        addPiece(piece);
        piece.moved();
        if(piece.getPieceType() == ID.Pawn){
            Pawn pawn = (Pawn) piece;
            pawn.setPrevLocation(currentLocation);
            pawn.setEnPassantLeft(null);
            pawn.setEnPassantRight(null);
        }
        removePiece(currentLocation);
    }



    public void updatePotenMove(COLOR color){
        for (Piece val : board.values()){
            val.setProtected(false);
        }
        for (Piece val : board.values()){
            if(val.getColor() != color){
                val.updatePotenMoves(this);
            }
        }
        for (Piece val : board.values()){
            if(val.getColor() == color){
                val.updatePotenMoves(this);
            }
        }

    }

    //____________________________________________________Overridden Methods_____________________________________________________

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pieces pieces = (Pieces) o;
        return Objects.equals(getBoard(), pieces.getBoard());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBoard());
    }

}