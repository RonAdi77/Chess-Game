package org.example.chessgui;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public abstract class Piece {
    private ID pieceType; // the ID of the piece
    private COLOR color; // the color of the piece
    private Box location; // the current location
    private Box initLocation; // the initial location of the piece
    private HashSet<Box> potenMoves; // the set that contains all the valid potential moves
    private boolean hasMoved; // true if the piece was moved at least one time
    private boolean isProtected; // true if this piece location is in one of the same color piece potential moves (needed to prevent check)
    private int score;

    //____________________________________________________Constructor's___________________________________________________________
    Piece(ID pieceType,COLOR color,Box initLocation,int score){

        Objects.requireNonNull(pieceType,"The piece ID cannot be null");
        Objects.requireNonNull(color,"The piece COLOR cannot be null");
        Objects.requireNonNull(initLocation,"The piece BOX cannot be null");

        if(!Box.inBoard(initLocation))throw new IllegalArgumentException("Invalid BOX");

        this.pieceType = pieceType;
        this.color = color;
        this.initLocation = initLocation;
        this.location = initLocation;
        this.potenMoves = new HashSet<>();
        this.hasMoved = false;
        this.isProtected = false;
        this.score = score;
    }

    // __________________________________________________Getters & Setters__________________________________________________________
    public ID getPieceType() {
        return pieceType;
    }

    public COLOR getColor() {
        return color;
    }

    public Box getLocation() {
        return location;
    }

    public int getScore() {
        return score;
    }

    public void setLocation(Box location) {
        Objects.requireNonNull(location,"The piece BOX cannot be null");
        if(!Box.inBoard(initLocation))throw new IllegalArgumentException("Invalid BOX");
        this.location = location;
    }

    public Box getInitLocation() {
        return initLocation;
    }

    public HashSet<Box> getPotenMoves() {
        return potenMoves;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public void moved(){
          hasMoved = true;
    }

    public boolean isProtected() {
        return isProtected;
    }

    public void setProtected(boolean aProtected) {
        isProtected = aProtected;
    }

    public void setPotenMoves(ArrayList<Box> potenMoves) {
        this.potenMoves.addAll(potenMoves);
    }

    //___________________________________________________Movement_____________________________________________________________

    /**
     * @param otherMoves -  array of box's to be added to this potential moves field
     */
    public void addMoves(ArrayList<Box> otherMoves){
        if(getPieceType() == ID.King){
            clearMoves();
        }
        potenMoves.addAll(otherMoves);
    }

    public void clearMoves(){
        potenMoves.clear();
    }

    /**
     * Check if a given box is a potential move
     */
    public boolean isValidMove(Box box){
        return getPotenMoves().contains(box);
    }

    /**
     * This function takes all the raw legal moves and remove all moves that cause or did not prevent check
     * @param board - The current played board
     * @return - array list of boxes with all potential valid moves.
     */
    public ArrayList<Box> removeOwnCheck(Pieces board){
        ArrayList<Box> result = getRawMoves(board);
        Iterator<Box> iterator = result.iterator();

        while (iterator.hasNext()){
            Box currentMove = iterator.next();
            Pieces currentBoard = new Pieces(board.getBoard()); // virtual board for checking and validating moves

            if(Move.isOccupiedBox(currentBoard,currentMove) && getPieceType() == ID.King && board.getPiece(currentMove).isProtected()){
                iterator.remove();
                continue;
            }
            // make the move, get all opposite player raw moves and then check if one of them is the king location
            currentBoard.pieceMove(currentMove,makeCopy());
            HashSet<Box> temp = currentBoard.getPiecesRawMoves(COLOR.NOT(getColor()));
            if(temp.contains(currentBoard.getKingLocation(getColor()))){
                iterator.remove();
            }
        }
        return result;
    }

    /**
     * This function clear the previous potential moves, and updated with new ones
     * @param board - The current played board
     */
    public void updatePotenMoves(Pieces board){
        clearMoves();
        addMoves(removeOwnCheck(board));
    }

    //____________________________________________________Overridden Methods_____________________________________________________

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return getPieceType() == piece.getPieceType() && getColor() == piece.getColor() && Objects.equals(getInitLocation(), piece.getInitLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPieceType(), getColor(), getInitLocation());
    }

    //____________________________________________________Abstract Method______________________________________________________

    /**
     * @param board - The current played board
     * @return - all the possible moves (including illegal moves) for a specific piece
     */
    public abstract ArrayList<Box> getRawMoves(Pieces board);

    /**
     * @return - a deep copy of this specific piece
     */
    public abstract Piece makeCopy();
}
