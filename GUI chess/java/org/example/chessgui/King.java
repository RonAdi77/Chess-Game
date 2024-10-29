package org.example.chessgui;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public class King extends Piece{

    private Box afterKCastleBox; // King side after castling location
    private Box afterQCastleBox; // Queen side after castling location
    private Box middleKCastleBox; // King side when castling location
    private Box middleQCastleBox; // Queen side when castling location
    private Rook kingSideRook;
    private Rook queenSideRook;

    //____________________________________________________Constructor___________________________________________________________
    King(COLOR color,Box initLocation,Rook kingSideRook,Rook queenSideRook) {
        super(ID.King, color, initLocation,0);

        Objects.requireNonNull(kingSideRook, "King side Rook cannot be null");
        Objects.requireNonNull(queenSideRook, "Queen side Rook cannot be null");

        this.kingSideRook = kingSideRook;
        this.queenSideRook = queenSideRook;
        if (color == COLOR.W) {
            afterKCastleBox = new Box('g', 1);
            middleKCastleBox = new Box('f', 1);
            afterQCastleBox = new Box('c', 1);
            middleQCastleBox = new Box('d', 1);
        } else {
            afterKCastleBox = new Box('g', 8);
            middleKCastleBox = new Box('f', 8);
            afterQCastleBox = new Box('c', 8);
            middleQCastleBox = new Box('d', 8);
        }
    }



    // __________________________________________________Getters__________________________________________________________
    public Box getAfterKCastleBox() {
        return afterKCastleBox;
    }

    public Box getAfterQCastleBox() {
        return afterQCastleBox;
    }

    public Box getMiddleKCastleBox() {
        return middleKCastleBox;
    }

    public Box getMiddleQCastleBox() {
        return middleQCastleBox;
    }

    public Rook getKingSideRook() {
        return kingSideRook;
    }

    public Rook getQueenSideRook() {
        return queenSideRook;
    }


    // __________________________________________________Methods__________________________________________________________

    /**
     * private help function
     * @param board - the current played board
     * @return - ArrayList of all valid potential castling moves
     */
    private ArrayList<Box> getPotenCastleMoves(Pieces board){
        ArrayList<Box> result = new ArrayList<>();
        if(board.isCheck(getColor())){
            return result;
        }
        if(canCastleK(board)){
            result.add(getAfterKCastleBox());
            result.add(getMiddleKCastleBox());
        }
        if(canCastleQ(board)){
            result.add(getAfterQCastleBox());
            result.add(getMiddleQCastleBox());
        }
        return result;
    }

    public boolean canCastleK(Pieces board){
        if(this.isHasMoved() || getKingSideRook().isHasMoved())return false;
        King currentKing = (King) this.makeCopy();
        Pieces currentBoard = new Pieces(board.getBoard());
        HashSet<Box> potenMoves = getPotenMoves();
        if(potenMoves.contains(middleKCastleBox)){
            currentBoard.pieceMove(middleKCastleBox, currentKing);
            if(currentBoard.isCheck(getColor())){
                return false;
            }
            if(potenMoves.contains(afterKCastleBox)){
                currentBoard.pieceMove(afterKCastleBox, currentKing);
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
        if(potenMoves.contains(middleQCastleBox)){
            currentBoard.pieceMove(middleQCastleBox, currentKing);
            if(currentBoard.isCheck(getColor())){
                return false;
            }
            ArrayList<Box> rookRaw = queenSideRook.getRawMoves(board);
            Box afterAfterCastle = new Box((char)(queenSideRook.getLocation().getCol() + 1), queenSideRook.getLocation().getRow());
            if(potenMoves.contains(afterQCastleBox) && rookRaw.contains(afterAfterCastle) ){
                currentBoard.pieceMove(afterQCastleBox, currentKing);
                return !currentBoard.isCheck(getColor());
            }
        }
        return false;
    }

    @Override
    public ArrayList<Box> getRawMoves(Pieces board) {
        Objects.requireNonNull(board);

        ArrayList<Box> result = new ArrayList<>();

        if(!Move.diagFrontRight(board,this).isEmpty()){
            result.add(Move.diagFrontRight(board,this).getFirst());
        }
        if(!Move.diagFrontLeft(board,this).isEmpty()){
            result.add(Move.diagFrontLeft(board,this).getFirst());
        }
        if(!Move.diagBackRight(board,this).isEmpty()){
            result.add(Move.diagBackRight(board,this).getFirst());
        }
        if(!Move.diagBackLeft(board,this).isEmpty()){
            result.add(Move.diagBackLeft(board,this).getFirst());
        }

        if(!Move.frontFreeBoxes(board , this).isEmpty()){
            result.add(Move.frontFreeBoxes(board , this).getFirst());
        }
        if(!Move.backFreeBoxes(board,this).isEmpty()){
            result.add(Move.backFreeBoxes(board,this).getFirst());
        }

        if(!Move.rightFreeBoxes(board,this).isEmpty()){
            result.add(Move.rightFreeBoxes(board,this).getFirst());
            // check if there is option to castling
            if(!this.isHasMoved() && this.getKingSideRook() != null && !this.getKingSideRook().isHasMoved() && !Move.isOccupiedBox(board, afterKCastleBox)){
                result.add(afterKCastleBox);
            }
        }
        if(!Move.leftFreeBoxes(board,this).isEmpty()){
            result.add(Move.leftFreeBoxes(board,this).getFirst());
            // check if there is option to castling
            if(!this.isHasMoved() && this.getQueenSideRook() != null && !this.getQueenSideRook().isHasMoved()  && !Move.isOccupiedBox(board, afterQCastleBox)){
                result.add(afterQCastleBox);
            }
        }

        this.setPotenMoves(result);
        result.addAll(getPotenCastleMoves(board));

        return result;
    }

    @Override
    public Piece makeCopy() {
        King result = new King(getColor(),getInitLocation(),getKingSideRook(),getQueenSideRook());
        result.setLocation(getLocation());
        for (Box box : getPotenMoves()){
            result.getPotenMoves().add(new Box(box));
        }
        if(isHasMoved()){
            result.moved();
        }
        return result;
    }
}
