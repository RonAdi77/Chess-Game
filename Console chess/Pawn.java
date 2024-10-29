import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Pawn extends Piece{
    private Box enPassantRight;
    private Box enPassantLeft;
    private Piece promotedPiece;//used after promotion instead of this.
    Box prevLocation;

    //____________________________________________________constructors____________________________________________________
    public Pawn(COLOR color , Box initLocation){
        super(ID.PAWN , color , initLocation);
        enPassantRight = null;
        enPassantLeft = null;
        promotedPiece = null;
        prevLocation = getLocation();
    }

    //____________________________________________________getters & setters____________________________________________________
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
    public Piece getPromotedPiece() {
        return promotedPiece;
    }
    public Box getPrevLocation() {
        return prevLocation;
    }
    public void setPrevLocation(Box prevLocation) {
        this.prevLocation = prevLocation;
    }

    //____________________________________________________Move methods____________________________________________________
    /**
     * @param board the main current played board instance.
     * @return array list of all enPassant valid moves(can be empty).
     */
    public ArrayList<Box> enPassant(Pieces board){
        int dir = (getColor() == COLOR.W) ? 1 : -1;
        Box right = new Box((char)(getLocation().getColl() + dir) , getLocation().getRow());
        Box left = new Box((char)(getLocation().getColl() - dir) , getLocation().getRow());
        ArrayList<Box> result = new ArrayList<>();
        if(Box.inBoard(right) && Move.isOccupiedBox(board , right)){
            Piece piece = board.getPiece(right);
            if(piece.getPieceType() == ID.PAWN && piece.getColor() != getColor()){
                Pawn pawn = (Pawn) piece;
                if(pawn.getPrevLocation().equals(pawn.getInitLocation())){
                    result.add(new Box(pawn.getLocation().getColl() , pawn.getLocation().getRow() + dir));
                    enPassantRight = result.getFirst();
                }
            }
        }
        if(Box.inBoard(left) && Move.isOccupiedBox(board , left)){
            Piece piece = board.getPiece(left);
            if(piece.getPieceType() == ID.PAWN && piece.getColor() != getColor()){
                Pawn pawn = (Pawn) piece;
                if(pawn.getPrevLocation().equals(pawn.getInitLocation())){
                    result.add(new Box(pawn.getLocation().getColl() , pawn.getLocation().getRow() + dir));
                    enPassantRight = result.getLast();
                }
            }
        }
        return result;
    }
    public void userPromotionChoice(Box promotionLocation){
        Objects.requireNonNull(promotionLocation , "promotion location cannot be NULL");

        Scanner sc = new Scanner(System.in);
        boolean correctInput = false;
        String choice = "";
        while (!correctInput){
            System.out.println("This pawn can be promoted , choose promotion type:");
            System.out.println("Press (1) for Queen.\nPress (2) for Rook.\nPress (3) for Bishop.\nPress (4) for Knight.");
            choice = sc.next();
            switch (choice){
                case "1":{
                    promotedPiece = new Queen(getColor() , promotionLocation);
                    correctInput = true;
                    break;
                }
                case "2":{
                    promotedPiece = new Rook(getColor() , promotionLocation);
                    correctInput = true;
                    break;
                }
                case "3":{
                    promotedPiece = new Bishop(getColor() , promotionLocation);
                    correctInput = true;
                    break;
                }
                case "4":{
                    promotedPiece = new Knight(getColor() , promotionLocation);
                    correctInput = true;
                    break;
                }
                default:{
                    System.out.println("Invalid input please try again.");
                }
            }

            sc.close();
            Objects.requireNonNull(promotedPiece);
        }
    }
    public boolean canPromote(){
        if(getColor() == COLOR.B){
            return getLocation().getRow() == BOARD_LIMITS.FIRST_ROW.getRow();
        }
        else {
            return getLocation().getRow() == BOARD_LIMITS.LAST_ROW.getRow();
        }
    }

    @Override
    public ArrayList<Box> getRawMoves(Pieces board){
        Objects.requireNonNull(board);

        ArrayList<Box> result = new ArrayList<>();
        if(!isHasMoved()){
            if(!Move.frontFreeBoxes(board , this).isEmpty()){
                if(!Move.isOccupiedBox(board,Move.frontFreeBoxes(board , this).get(0))){
                    result.add(Move.frontFreeBoxes(board , this).get(0));
                }
            }
            if(Move.frontFreeBoxes(board , this).size() > 1){
                if(!Move.isOccupiedBox(board,Move.frontFreeBoxes(board , this).get(1))) {
                    result.add(Move.frontFreeBoxes(board, this).get(1));
                }
            }
        }
        else {
            if(!Move.frontFreeBoxes(board , this).isEmpty()){
                if(!Move.isOccupiedBox(board,Move.frontFreeBoxes(board , this).getFirst())) {
                    result.add(Move.frontFreeBoxes(board, this).getFirst());
                }
            }
        }
        if(!Move.diagFrontRight(board,this).isEmpty()){
            Box rightDiag = Move.diagFrontRight(board,this).getFirst();
            if(Move.isOccupiedBox(board , rightDiag)){
                if(board.getPiece(rightDiag).getColor() != getColor()){
                    result.add(rightDiag);
                }
            }
        }
        if(!Move.diagFrontLeft(board , this).isEmpty()){
            Box leftDiag = Move.diagFrontLeft(board , this).getFirst();

            if(Move.isOccupiedBox(board , leftDiag)){
                if(board.getPiece(leftDiag).getColor() != getColor()){
                    result.add(leftDiag);
                }
            }
        }
        //add en passant logic.
        result.addAll(enPassant(board));
        return result;
    }

    @Override
    public Piece makeCopy() {
        Pawn result = new Pawn(getColor() , getInitLocation());
        result.prevLocation = prevLocation;
        result.enPassantLeft = enPassantLeft;
        result.enPassantRight = enPassantRight;
        result.promotedPiece = (promotedPiece == null) ? null : promotedPiece.makeCopy();
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
