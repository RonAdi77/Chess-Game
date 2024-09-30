import java.util.*;

public abstract class Piece {
    private ID pieceType;//the ID of the piece.
    private  COLOR color;//the colo of the piece.
    private Box location;//current location of the piece.
    private Box initLocation;//the first location of the piece when creating the board.
    private HashSet<Box> potenMoves;//contain all valid moves for this piece.
    private boolean hasMoved;//true if the piece was moved at least one time.

    //true if this piece location is in one of the same color piece potential Moves array , needed to prevent an in check king to eat this piece.
    private boolean isProtected;

    //____________________________________________________constructor's____________________________________________________

    public Piece(ID pieceType , COLOR color , Box initLocation){
        Objects.requireNonNull(pieceType , "The piece ID cannot be NULL");
        Objects.requireNonNull(color , "The piece color cannot be NULL");
        Objects.requireNonNull(initLocation , "The piece initLocation cannot be NULL");
        if(!Box.inBoard(initLocation))throw new IllegalArgumentException("invalid Box");

        this.pieceType = pieceType;
        this.color = color;
        this.location = initLocation;
        this.initLocation = initLocation;
        potenMoves = new HashSet<>();
        hasMoved = false;
        isProtected = false;
    }


    //____________________________________________________getters & setters____________________________________________________

    public void setLocation(Box location) {
        Objects.requireNonNull(location , "The Box cannot be NULL");
        if(!Box.inBoard(initLocation))throw new IllegalArgumentException("invalid Box");
        this.location = location;
    }

    public void moved(){
        hasMoved = true;
    }

    public ID getPieceType() {
        return pieceType;
    }

    public COLOR getColor() {
        return color;
    }

    public boolean isHasMoved() {
        return hasMoved;
    }

    public HashSet<Box> getPotenMoves() {
        return potenMoves;
    }

    public Box getInitLocation() {
        return initLocation;
    }

    public Box getLocation() {
        return location;
    }

    public void setIsProtected(boolean pro){isProtected = pro;}

    public void setPotenMoves(ArrayList<Box> poten){
        this.potenMoves.addAll(poten);
    }

    //____________________________________________________movement____________________________________________________

    /**
     * @param moves array of boxes to be added to this potential moves field.
     */
    public void addMoves(ArrayList<Box> moves){
        if(getPieceType() == ID.KING){
            clearMoves();
        }
        potenMoves.addAll(moves);
    }
    public void clearMoves(){
        potenMoves.clear();
    }
    //check if a given box is a potential move.
    public boolean isValidMove(Box move){
        return getPotenMoves().contains(move);
    }

    /**
     * this function takes all the raw legal moves and remove all moves that cause or did not prevent check state.
     * @param board the main current played board instance.
     * @return an Array list of boxes with all potential valid moves.
     */
    public ArrayList<Box> removeOwnCheck(Pieces board){
        ArrayList<Box> result = getRawMoves(board);
        Iterator<Box> iterator = result.iterator();

        while (iterator.hasNext()){
            Box currentMove = iterator.next();
            Pieces currentBoard = new Pieces(board.getBoard());//virtual board for checking and validating moves.
            if(Move.isOccupiedBox(currentBoard , currentMove) && getPieceType() == ID.KING && board.getPiece(currentMove).isProtected){
                iterator.remove();
                continue;
            }
            //make the move in the virtual board, get all opposite player raw moves , check if one of them is the king location.
            currentBoard.pieceMove(currentMove , makeCopy());
            HashSet<Box> temp = currentBoard.getPiecesRawMoves(COLOR.not(getColor()));
            if(temp.contains(currentBoard.getKingLocation(getColor()))){
                iterator.remove();
            }
        }
        return result;
    }

    /**
     * this function clear the previous potential moves and update it with new ones.
     * @param board the main current played board instance.
     */
    public void updatePotenMoves(Pieces board){
        clearMoves();
        addMoves(removeOwnCheck(board));
    }

    //____________________________________________________Overridden Methods____________________________________________________


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return pieceType == piece.pieceType && color == piece.color && Objects.equals(initLocation, piece.initLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pieceType, color, initLocation);
    }

    //____________________________________________________Abstract Methods____________________________________________________

    /**
     * @param board the main current played board instance.
     * @return all the possible moves for a specific piece(including illegal moves).
     */
    public abstract ArrayList<Box> getRawMoves(Pieces board);

    /**
     * @return a deep copy of this specific piece.
     */
    public abstract Piece makeCopy();
}
