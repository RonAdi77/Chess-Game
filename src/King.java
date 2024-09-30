import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class King extends Piece{
    private Box afterKCastleLocation;
    private Box afterQCastleLocation;
    private Box middleKCastleLocation;
    private Box middleQCastleLocation;
    private Rook kingSideRook;
    private Rook queenSideRook;

    //____________________________________________________constructor's____________________________________________________
    public King(COLOR color , Box initLocation , Rook kingSideRook , Rook queenSideRook){
        super(ID.KING , color , initLocation);
        Objects.requireNonNull(kingSideRook , "king side Rook cannot be NULL");
        Objects.requireNonNull(queenSideRook , "Queen side Rook cannot be NULL");

        this.kingSideRook = kingSideRook;
        this.queenSideRook = queenSideRook;

        if (color == COLOR.W){
            middleKCastleLocation = new Box('f' , 1);
            afterKCastleLocation = new Box('g' , 1);
            middleQCastleLocation = new Box('d' , 1);
            afterQCastleLocation = new Box('c' , 1);
        }
        else {
            middleKCastleLocation = new Box('f' , 8);
            afterKCastleLocation = new Box('g' , 8);
            middleQCastleLocation = new Box('d' , 8);
            afterQCastleLocation = new Box('c' , 8);
        }
    }

    //____________________________________________________getters____________________________________________________
    public Box getAfterKCastleLocation() {
        return afterKCastleLocation;
    }
    public Box getAfterQCastleLocation() {
        return afterQCastleLocation;
    }
    public Box getMiddleKCastleLocation() {
        return middleKCastleLocation;
    }
    public Box getMiddleQCastleLocation() {
        return middleQCastleLocation;
    }
    public Rook getKingSideRook() {
        return kingSideRook;
    }
    public Rook getQueenSideRook() {
        return queenSideRook;
    }

    /**
     * private help function.
     * @param board the main current played board instance.
     * @return array list of all valid and potential castling moves.
     */
    private ArrayList<Box> getPotenCastleMoves(Pieces board){
        ArrayList<Box> result = new ArrayList<>();
        if(board.isCheck(getColor())){
            return result;
        }
        if(canCastleK(board)){
            result.add(afterKCastleLocation);
            result.add(middleKCastleLocation);
        }
        if(canCastleQ(board)){
            result.add(afterQCastleLocation);
            result.add(middleQCastleLocation);
        }
        return result;
    }

    public boolean canCastleK(Pieces board){
        if(this.isHasMoved() || getKingSideRook().isHasMoved())return false;
        King currentKing = (King) this.makeCopy();
        Pieces currentBoard = new Pieces(board.getBoard());
        HashSet<Box> potenMoves = getPotenMoves();
        if(potenMoves.contains(middleKCastleLocation)){
            currentBoard.pieceMove(middleKCastleLocation , currentKing);
            if(currentBoard.isCheck(getColor())){
                return false;
            }
            if(potenMoves.contains(afterKCastleLocation)){
                currentBoard.pieceMove(afterKCastleLocation , currentKing);
                return !currentBoard.isCheck(getColor());
            }
        }
        return false;
    }

    public boolean canCastleQ(Pieces board) {
        if(this.isHasMoved() || getQueenSideRook().isHasMoved())return false;
        King currentKing = (King) this.makeCopy();
        Pieces currentBoard = new Pieces(board.getBoard());
        HashSet<Box> potenMoves = getPotenMoves();
        if(potenMoves.contains(middleQCastleLocation)){
            currentBoard.pieceMove(middleQCastleLocation , currentKing);
            if(currentBoard.isCheck(getColor())){
                return false;
            }
            ArrayList<Box> rookRaw = queenSideRook.getRawMoves(board);
            Box afterAfterCastle = new Box((char)(queenSideRook.getLocation().getColl() + 1), queenSideRook.getLocation().getRow());
            if(potenMoves.contains(afterQCastleLocation) &&
                rookRaw.contains(afterAfterCastle)){
                currentBoard.pieceMove(afterQCastleLocation , currentKing);
                return !currentBoard.isCheck(getColor());
            }
        }
        return false;
    }

    @Override
    public ArrayList<Box> getRawMoves(Pieces board) {
        Objects.requireNonNull(board);

        ArrayList<Box> result = new ArrayList<>();
        //bishop moves
        if(!Move.diagFrontRight(board , this).isEmpty()){
            result.add(Move.diagFrontRight(board , this).getFirst());
        }
        if(!Move.diagFrontLeft(board , this).isEmpty()){
            result.add(Move.diagFrontLeft(board , this).getFirst());
        }
        if(!Move.diagBackRight(board , this).isEmpty()){
            result.add(Move.diagBackRight(board , this).getFirst());
        }
        if(!Move.diagBackLeft(board , this).isEmpty()){
            result.add(Move.diagBackLeft(board , this).getFirst());
        }

        //rook moves.
        if(!Move.frontFreeBoxes(board , this).isEmpty()){
            result.add(Move.frontFreeBoxes(board , this).getFirst());
        }
        if(!Move.backFreeBoxes(board,this).isEmpty()){
            result.add(Move.backFreeBoxes(board,this).getFirst());
        }
        if(!Move.rightFreeBoxes(board,this).isEmpty()){
            result.add(Move.rightFreeBoxes(board,this).getFirst());
            //check if there is option to castle.
            if(!this.isHasMoved() && this.getKingSideRook() != null && !this.getKingSideRook().isHasMoved() && !Move.isOccupiedBox(board ,afterKCastleLocation)){
                result.add(afterKCastleLocation);
            }
        }
        if(!Move.leftFreeBoxes(board,this).isEmpty()){
            result.add(Move.leftFreeBoxes(board,this).getFirst());
            //check if there is option to castle.
            if(!this.isHasMoved() && this.getQueenSideRook() != null && !this.getQueenSideRook().isHasMoved() && !Move.isOccupiedBox(board, afterQCastleLocation)){
                result.add(afterQCastleLocation);
            }
        }
        this.setPotenMoves(result);
        result.addAll(getPotenCastleMoves(board));
        return result;
    }

    @Override
    public Piece makeCopy() {
        King result = new King(getColor() , getInitLocation() , getKingSideRook() , getQueenSideRook());
        result.setLocation(this.getLocation());
        for(Box box : getPotenMoves()){
            result.getPotenMoves().add(new Box(box));
        }
        if(isHasMoved()){
            result.moved();
        }
        return result;
    }
}
