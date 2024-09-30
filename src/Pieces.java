import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Pieces {
    HashMap<Box , Piece> board;//the board of the game.
    private ArrayList<HashMap<Box , Piece>> boardProgress;//for saving all game activity's.

    //____________________________________________________constructor's____________________________________________________
    public Pieces(){
        board = Board.getChessBoard();
        boardProgress = new ArrayList<>();
        boardProgress.add(copyBoard(this.getBoard()));
    }
    public Pieces(HashMap<Box , Piece> otherBoard){
        this.board = copyBoard(otherBoard);
        boardProgress = new ArrayList<>();
        boardProgress.add(copyBoard(this.getBoard()));
    }
    private HashMap<Box ,Piece> copyBoard(HashMap<Box , Piece> otherBoard){//deep copy of this board.
        HashMap<Box , Piece> result = new HashMap<>();
        for(Box box : otherBoard.keySet()){
            Piece newPiece = otherBoard.get(box).makeCopy();
            Box newBox = new Box(box);
            result.put(newBox , newPiece);
        }
        return result;
    }

    //____________________________________________________getters & setters____________________________________________________
    public HashMap<Box , Piece> getBoard(){
        return board;
    }

    public void addPiece(Piece piece){
        Objects.requireNonNull(piece);
        board.put(piece.getLocation(), piece);
    }
    public void removePiece(Box location){
        if(!Box.inBoard(location))throw new IllegalArgumentException();
        board.remove(location);
    }

    public Box getKingLocation(COLOR color){
        for(Piece piece : board.values()){
            if(piece.getPieceType() == ID.KING && piece.getColor() == color){
               return piece.getLocation();
            }
        }
        throw new RuntimeException("king not found");
    }
    public Piece getPiece(Box location){
        if(!Box.inBoard(location))throw new IllegalArgumentException();
        return board.get(location);
    }
    public HashSet<Box> getPiecesPotenMoves(COLOR color){
        HashSet<Box> result = new HashSet<>();
        for(Box key : board.keySet()){
            if(board.get(key).getColor() == color){
                result.addAll(board.get(key).getPotenMoves());
            }
        }
        return result;
    }
    public HashSet<Box> getPiecesRawMoves(COLOR color){
        HashSet<Box> result = new HashSet<>();
        for(Box key : board.keySet()){
            if(board.get(key).getColor() == color){
                result.addAll(board.get(key).getRawMoves(this));
            }
        }
        return result;
    }

    //____________________________________________________methods____________________________________________________
    public boolean isCheck(COLOR color){
        return getPiecesPotenMoves(COLOR.not(color)).contains(getKingLocation(color));
    }
    public boolean isMate(COLOR color){
        return isCheck(color) && getPiecesPotenMoves(color).isEmpty();
    }

    /**
     * @return true if the board contain only two kings , two kings and a bishop or knight ,
     * two kings and two opposite color bishop.
     */
    public boolean isDraw(){
        if(getBoard().size() == 2){
            return true;
        }
        else if(getBoard().size() == 3){
            boolean isNotKing = false;
            for(Piece piece : getBoard().values()){
                if(piece.getPieceType() == ID.BISHOP || piece.getPieceType() == ID.KNIGHT){
                    isNotKing = true;
                    break;
                }
            }
            return isNotKing;
        }
        else if(getBoard().size() == 4){
            Bishop white = null;
            Bishop black = null;
            for(Piece piece : getBoard().values()){
                if(piece.getPieceType() == ID.BISHOP){
                    if(piece.getColor() == COLOR.W){
                        white = (Bishop) piece;
                    }
                    else{
                        black = (Bishop) piece;
                    }

                }
            }
            if(white != null && black != null){
                return white.getInitLocation().getColl() != black.getInitLocation().getColl();
            }
            else {
                return false;
            }
        }
        return false;
    }

    /**
     * @return truw if there was three time the same board state.
     */
    public boolean isThreeFoldDraw(){
        if(boardProgress.size() >= 3){
            int counter = 0;
            for(int i = 0;i < boardProgress.size() ; i++){
                for(int j = 0 ; j < boardProgress.size() ; j++){
                    if(i != j){
                        if(boardProgress.get(i).equals(boardProgress.get(j))){
                            counter++;
                        }
                    }
                }
            }
            return counter >= 3;
        }
        return false;
    }

    public boolean stalemate(COLOR color){
        return !isCheck(color) && getPiecesPotenMoves(color).isEmpty();
    }

    /**
     * this function move a piece to a given destination.
     * handle pawn enPassant previous location.
     * @param destination given argument destination.
     * @param piece the piece to move.
     */
    public void pieceMove(Box destination , Piece piece){
        Objects.requireNonNull(piece);
        if(!Box.inBoard(destination))throw new IllegalArgumentException();

        Box currentLocation = piece.getLocation();
        piece.setLocation(destination);
        piece.moved();
        addPiece(piece);
        if(piece.getPieceType() == ID.PAWN){
            Pawn pawn = (Pawn) piece;
            pawn.setPrevLocation(currentLocation);
            pawn.setEnPassantLeft(null);
            pawn.setEnPassantRight(null);
        }
        removePiece(currentLocation);
    }

    /**
     * this function handle the moving procedure.
     * if it's a king - handle castling and native king move.
     * if it's a pawn - handle pawn promotion , enPassant and native pawn move.
     * else handle the other pieces native move.
     * @param destination destination to move to.
     * @param piece the piece to move.
     * @return true if the move success.
     */
    public boolean makeMove(Box destination , Piece piece){
        boolean success = true;
        if(piece.isValidMove(destination)){
            if(piece.getPieceType() == ID.KING){
                King king = (King) piece;
                if(king.canCastleK(this) && destination.equals(king.getAfterKCastleLocation())){
                    pieceMove(destination , king);
                    pieceMove(king.getMiddleKCastleLocation(),king.getKingSideRook());
                }
                else if(king.canCastleQ(this) && destination.equals(king.getAfterQCastleLocation())){
                    pieceMove(destination , king);
                    pieceMove(king.getMiddleQCastleLocation(),king.getQueenSideRook());
                }
                else {
                    if(Math.abs(destination.getColl() - king.getLocation().getColl()) <= 1){
                        pieceMove(destination , king);
                    }
                    else success = false;
                }
            }
            else if(piece.getPieceType() == ID.PAWN){
                Pawn pawn = (Pawn) piece;
                if(pawn.canPromote()){
                    pawn.userPromotionChoice(pawn.getLocation());
                    Piece promote = pawn.getPromotedPiece();
                    addPiece(promote);
                    pieceMove(destination , promote);
                }
                else if(destination.equals(pawn.getEnPassantRight())){
                    pieceMove(destination , pawn);
                    if(pawn.getColor() == COLOR.W){
                        removePiece(new Box(destination.getColl() , destination.getRow()-1));
                    }
                    else {
                        removePiece(new Box(destination.getColl() , destination.getRow()+1));
                    }
                }
                else if(destination.equals(pawn.getEnPassantLeft())){
                    pieceMove(destination , pawn);
                    if(pawn.getColor() == COLOR.W){
                        removePiece(new Box(destination.getColl() , destination.getRow()-1));
                    }
                    else {
                        removePiece(new Box(destination.getColl() , destination.getRow()+1));
                    }
                }
                else {
                    pieceMove(destination , pawn);
                }
            }
            else {//it's not a king nor pawn.
                pieceMove(destination , piece);
            }
            boardProgress.add(copyBoard(getBoard()));
        }
        return success;
    }

    public void updatePotenMoves(COLOR color){
        for(Piece value : board.values()){
            value.setIsProtected(false);
        }
        for(Piece value : board.values()){
            if(value.getColor() != color){
                value.updatePotenMoves(this);
            }
        }
        for(Piece value : board.values()){
            if(value.getColor() == color){
                value.updatePotenMoves(this);
            }
        }
    }

    //____________________________________________________Overridden Methods____________________________________________________
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pieces pieces = (Pieces) o;
        return Objects.equals(getBoard(), pieces.getBoard());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(board);
    }
}
