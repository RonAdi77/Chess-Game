package org.example.chessgui;
import java.util.ArrayList;
import java.util.Objects;


public class Pawn extends Piece {
    private Box enPassantRight;
    private Box enPassantLeft;
    private Piece promtedPiece; // use after promotion instead of this pawn
    private Box prevLocation;


    //____________________________________________________Constructor___________________________________________________________
    Pawn(COLOR color, Box initLocation) {
        super(ID.Pawn, color, initLocation,1);
        enPassantLeft = null;
        enPassantRight = null ;
        promtedPiece = null;
        prevLocation = initLocation;
    }

    // __________________________________________________Getters & Setters__________________________________________________________

    public Box getEnPassantRight() {
        return enPassantRight;
    }

    public void setEnPassantRight(Box enPassantRight) {
        this.enPassantRight = enPassantRight;
    }

    public Box getEnPassantLeft() {
        return enPassantLeft;
    }

    public void setEnPassantLeft(Box enPassantLeft) {
        this.enPassantLeft = enPassantLeft;
    }

    public Piece getPromtedPiece() {
        return promtedPiece;
    }


    public Box getPrevLocation() {
        return prevLocation;
    }

    public void setPrevLocation(Box prevLocation) {
        this.prevLocation = prevLocation;
    }

    public void setPromtedPiece(Piece promtedPiece) {
        this.promtedPiece = promtedPiece;
    }

    //____________________________________________________Move Methods___________________________________________________________

    /**
     * @param board - The current played board
     * @return - ArrayList of all enPassant valid moves (can be empty)
     */
    public ArrayList<Box> enPassant(Pieces board){
        ArrayList<Box> result = new ArrayList<>();
        int direction = getColor() == COLOR.W?1:-1;
        Box right = new Box((char)(getLocation().getCol()+direction),getLocation().getRow());
        Box left = new Box((char)(getLocation().getCol()- direction),getLocation().getRow());

        if(Box.inBoard(right) && Move.isOccupiedBox(board,right) ){
            Piece piece = board.getPiece(right);
            if(piece.getPieceType() == ID.Pawn && piece.getColor() != getColor()){
                Pawn pawn = (Pawn) piece;
                if(pawn.getPrevLocation().equals(pawn.getInitLocation())){
                    result.add(new Box(pawn.getLocation().getCol(),pawn.getLocation().getRow()+direction));
                    setEnPassantRight(result.getFirst());
                }
            }
        }

        if(Box.inBoard(left) && Move.isOccupiedBox(board,left) ){
            Piece piece = board.getPiece(left);
            if(piece.getPieceType() == ID.Pawn && piece.getColor() != getColor()){
                Pawn pawn = (Pawn) piece;
                if(pawn.getPrevLocation().equals(pawn.getInitLocation())){
                    result.add(new Box(pawn.getLocation().getCol(),pawn.getLocation().getRow()+direction));
                    setEnPassantRight(result.getLast());
                }
            }
        }

        return result;
    }

    //____________________________________________________Promotion Methods___________________________________________________________
    public boolean canPromote() {
        if(getColor() == COLOR.B){
            return getLocation().getRow() == BOARD_LIMIT.FIRST_ROW.getRowVal() + 1;
        }
        return getLocation().getRow() == BOARD_LIMIT.LAST_ROW.getRowVal() - 1;
    }

    @Override
    public ArrayList<Box> getRawMoves(Pieces board) {
        Objects.requireNonNull(board);

        ArrayList<Box> result = new ArrayList<>();

        if(!isHasMoved()){
            if(!Move.frontFreeBoxes(board,this).isEmpty()){
                if(!Move.isOccupiedBox(board,Move.frontFreeBoxes(board,this).get(0))){
                    result.add(Move.frontFreeBoxes(board,this).getFirst());
                }
                if(Move.frontFreeBoxes(board,this).size() > 1){
                    if(!Move.isOccupiedBox(board,Move.frontFreeBoxes(board,this).get(1))) {
                        result.add(Move.frontFreeBoxes(board, this).get(1));
                    }
                }
            }
        }
        else{
            if(!Move.frontFreeBoxes(board,this).isEmpty()) {
                if(!Move.isOccupiedBox(board,Move.frontFreeBoxes(board,this).getFirst())){
                    result.add(Move.frontFreeBoxes(board, this).getFirst());
                }
            }
        }

        if(!Move.diagFrontRight(board,this).isEmpty()){
            Box rightDiag = Move.diagFrontRight(board,this).getFirst();
            if(Move.isOccupiedBox(board,rightDiag)){
                if(board.getPiece(rightDiag).getColor() != this.getColor()){
                    result.add(rightDiag);
                }
            }
        }

        if (!Move.diagFrontLeft(board,this).isEmpty()){
            Box leftDiag = Move.diagFrontLeft(board,this).getFirst();
            if(Move.isOccupiedBox(board,leftDiag)){
                if(board.getPiece(leftDiag).getColor() != this.getColor()){
                    result.add(leftDiag);
                }
            }
        }

        result.addAll(enPassant(board));

        return result;
    }

    @Override
    public Piece makeCopy() {
        Pawn result = new Pawn(getColor(),getInitLocation());
        result.prevLocation = prevLocation;
        result.promtedPiece = promtedPiece==null? null : promtedPiece.makeCopy();
        result.enPassantRight = enPassantRight;
        result.enPassantLeft = enPassantLeft;
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