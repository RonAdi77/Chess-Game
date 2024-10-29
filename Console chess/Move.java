import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Move {
    /**
     * compere between two colors.
     * @param board the main current played board instance.
     * @param destination the piece to compare with.
     * @param color the color to compare with.
     * @return true if equals.
     */
    public static boolean isSameColor(Pieces board , Box destination , COLOR color){
        return board.getPiece(destination).getColor() == color;
    }

    /**
     * @param board the main current played board instance.
     * @param destination the location to check.
     * @return true if the box is not empty.
     */
    public static boolean isOccupiedBox(Pieces board , Box destination){
        return board.getPiece(destination) != null;
    }

    /**
     * @param board the main current played board instance.
     * @param piece instance of a piece to work with.
     * @return array list of boxes containing all empty boxes to the front of a given piece.
     */
    public static ArrayList<Box> frontFreeBoxes(Pieces board , Piece piece){
        boolean searchEnd = false;
        int direction = (piece.getColor() == COLOR.B) ? -1 :  1;//black or white piece.
        int moveLength = direction;
        int row = piece.getLocation().getRow();
        char coll = piece.getLocation().getColl();
        ArrayList<Box> result = new ArrayList<>();
        while (!searchEnd){
            Box checkFree = new Box(coll , row+moveLength);
            if(!Box.inBoard(checkFree))break;
            if(!Move.isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
               //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
               if(!isSameColor(board,checkFree,piece.getColor())){
                   result.add(checkFree);
               }
               else {
                   board.getPiece(checkFree).setIsProtected(true);
               }
               searchEnd = true;
            }
            moveLength += direction;
            if(piece.getPieceType() == ID.KING){
                searchEnd = true;
            }
        }
        return result;
    }
    /**
     * @param board the main current played board instance.
     * @param piece instance of a piece to work with.
     * @return array list of boxes containing all empty boxes to the back of a given piece.
     */
    public static ArrayList<Box> backFreeBoxes(Pieces board , Piece piece){
        boolean searchEnd = false;
        int direction = (piece.getColor() == COLOR.W) ? -1 :  1;
        int moveLength = direction;
        int row = piece.getLocation().getRow();
        char coll = piece.getLocation().getColl();
        ArrayList<Box> result = new ArrayList<>();
        while (!searchEnd){
            Box checkFree = new Box(coll , row+moveLength);
            if(!Box.inBoard(checkFree))break;
            if(!Move.isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else {
                    board.getPiece(checkFree).setIsProtected(true);
                }
                searchEnd = true;
            }
            moveLength += direction;
            if(piece.getPieceType() == ID.KING){
                searchEnd = true;
            }
        }
        return result;
    }
    /**
     * @param board the main current played board instance.
     * @param piece instance of a piece to work with.
     * @return array list of boxes containing all empty boxes to the right of a given piece.
     */
    public static ArrayList<Box> rightFreeBoxes(Pieces board , Piece piece){
        boolean searchEnd = false;
        int direction = 1;
        int row = piece.getLocation().getRow();
        char coll = piece.getLocation().getColl();
        ArrayList<Box> result = new ArrayList<>();
        while (!searchEnd){
            Box checkFree = new Box((char) (coll + direction) , row);
            if(!Box.inBoard(checkFree))break;
            if(!Move.isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else {
                    board.getPiece(checkFree).setIsProtected(true);
                }
                searchEnd = true;
            }
            direction++;
            if(piece.getPieceType() == ID.KING){
                searchEnd = true;
            }
        }
        return result;
    }
    /**
     * @param board the main current played board instance.
     * @param piece instance of a piece to work with.
     * @return array list of boxes containing all empty boxes to the left of a given piece.
     */
    public static ArrayList<Box> leftFreeBoxes(Pieces board , Piece piece){
        boolean searchEnd = false;
        int direction = -1;
        int row = piece.getLocation().getRow();
        char coll = piece.getLocation().getColl();
        ArrayList<Box> result = new ArrayList<>();
        while (!searchEnd){
            Box checkFree = new Box((char) (coll + direction) , row);
            if(!Box.inBoard(checkFree))break;
            if(!Move.isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else {
                    board.getPiece(checkFree).setIsProtected(true);
                }
                searchEnd = true;
            }
            direction--;
            if(piece.getPieceType() == ID.KING){
                searchEnd = true;
            }
        }
        return result;
    }
    /**
     * @param board the main current played board instance.
     * @param piece instance of a piece to work with.
     * @return array list of boxes containing all empty boxes to the front right diagonal of a given piece.
     */
    public static ArrayList<Box> diagFrontRight(Pieces board , Piece piece){
        boolean searchEnd = false;
        int direction = (piece.getColor() == COLOR.B) ? -1 :  1;
        int moveLength = direction;
        int row = piece.getLocation().getRow();
        char coll = piece.getLocation().getColl();
        ArrayList<Box> result = new ArrayList<>();
        while (!searchEnd){
            Box checkFree = new Box((char) (coll + moveLength) , row + moveLength);
            if(!Box.inBoard(checkFree))break;
            if(!Move.isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else {
                    board.getPiece(checkFree).setIsProtected(true);
                }
                searchEnd = true;
            }
            moveLength += direction;
            if(piece.getPieceType() == ID.KING){
                searchEnd = true;
            }
        }
        return result;
    }
    /**
     * @param board the main current played board instance.
     * @param piece instance of a piece to work with.
     * @return array list of boxes containing all empty boxes to the back right diagonal of a given piece.
     */
    public static ArrayList<Box> diagBackRight(Pieces board , Piece piece){
        boolean searchEnd = false;
        int rowDirection = (piece.getColor() == COLOR.W) ? -1 :  1;
        int collDirection = -1*rowDirection;
        int collLength = collDirection;
        int rowLength = rowDirection;
        int row = piece.getLocation().getRow();
        char coll = piece.getLocation().getColl();
        ArrayList<Box> result = new ArrayList<>();
        while (!searchEnd){
            Box checkFree = new Box((char) (coll + collLength) , row+rowLength);
            if(!Box.inBoard(checkFree))break;
            if(!Move.isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else {
                    board.getPiece(checkFree).setIsProtected(true);
                }
                searchEnd = true;
            }
            collLength += collDirection;
            rowLength += rowDirection;
            if(piece.getPieceType() == ID.KING){
                searchEnd = true;
            }
        }
        return result;
    }
    /**
     * @param board the main current played board instance.
     * @param piece instance of a piece to work with.
     * @return array list of boxes containing all empty boxes to the front left diagonal of a given piece.
     */
    public static ArrayList<Box> diagFrontLeft(Pieces board , Piece piece){
        boolean searchEnd = false;
        int rowDirection = (piece.getColor() == COLOR.B) ? -1 :  1;
        int collDirection = -1*rowDirection;
        int collLength = collDirection;
        int rowLength = rowDirection;
        int row = piece.getLocation().getRow();
        char coll = piece.getLocation().getColl();
        ArrayList<Box> result = new ArrayList<>();
        while (!searchEnd){
            Box checkFree = new Box((char) (coll + collLength) , row+rowLength);
            if(!Box.inBoard(checkFree))break;
            if(!Move.isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else {
                    board.getPiece(checkFree).setIsProtected(true);
                }
                searchEnd = true;
            }
            collLength += collDirection;
            rowLength += rowDirection;
            if(piece.getPieceType() == ID.KING){
                searchEnd = true;
            }
        }
        return result;
    }
    /**
     * @param board the main current played board instance.
     * @param piece instance of a piece to work with.
     * @return array list of boxes containing all empty boxes to the back left diagonal of a given piece.
     */
    public static ArrayList<Box> diagBackLeft(Pieces board , Piece piece){
        boolean searchEnd = false;
        int direction = (piece.getColor() == COLOR.W) ? -1 :  1;
        int moveLength = direction;
        int row = piece.getLocation().getRow();
        char coll = piece.getLocation().getColl();
        ArrayList<Box> result = new ArrayList<>();
        while (!searchEnd){
            Box checkFree = new Box((char) (coll + moveLength) , row+moveLength);
            if(!Box.inBoard(checkFree))break;
            if(!Move.isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else {
                    board.getPiece(checkFree).setIsProtected(true);
                }
                searchEnd = true;
            }
            moveLength += direction;
            if(piece.getPieceType() == ID.KING){
                searchEnd = true;
            }
        }
        return result;
    }

    /**
     *
     * @param board the main current played board instance.
     * @param knight instance of a knight to work with.
     * @return array list of boxes containing all empty boxes to the front of the knight.
     */
    public static ArrayList<Box> frontKnight(Pieces board , Knight knight){
        ArrayList<Box> result = new ArrayList<>();
        int row = knight.getLocation().getRow();
        char coll = knight.getLocation().getColl();
        int direction = (knight.getColor() == COLOR.W) ? 2 : -2;
        Box checkRight = new Box((char)(coll + 1) , row + direction);
        Box checkLeft = new Box((char)(coll - 1) , row + direction);
        ArrayList<Box> help = new ArrayList<>();
        help.add(checkRight);
        help.add(checkLeft);
        for(Box box : help){
            if(Box.inBoard(box)){
                if(!Move.isOccupiedBox(board ,box)){
                    result.add(box);
                }
                else {
                    //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
                    if (!isSameColor(board,box,knight.getColor())){
                        result.add(box);
                    }
                    else {
                        board.getPiece(box).setIsProtected(true);
                    }
                }
            }
        }
        return result;
    }
    /**
     *
     * @param board the main current played board instance.
     * @param knight instance of a knight to work with.
     * @return array list of boxes containing all empty boxes to the back of the knight.
     */
    public static ArrayList<Box> backKnight(Pieces board , Knight knight){
        ArrayList<Box> result = new ArrayList<>();
        int row = knight.getLocation().getRow();
        char coll = knight.getLocation().getColl();
        int direction = (knight.getColor() == COLOR.B) ? 2 : -2;
        Box checkRight = new Box((char)(coll + 1) , row + direction);
        Box checkLeft = new Box((char)(coll - 1) , row + direction);
        ArrayList<Box> help = new ArrayList<>();
        help.add(checkRight);
        help.add(checkLeft);
        for(Box box : help){
            if(Box.inBoard(box)){
                if(!Move.isOccupiedBox(board ,box)){
                    result.add(box);
                }
                else {
                    //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
                    if (!isSameColor(board,box,knight.getColor())){
                        result.add(box);
                    }
                    else{
                        board.getPiece(box).setIsProtected(true);
                    }
                }
            }
        }
        return result;
    }
    /**
     *
     * @param board the main current played board instance.
     * @param knight instance of a knight to work with.
     * @return array list of boxes containing all empty boxes to the right of the knight.
     */
    public static ArrayList<Box> rightKnight(Pieces board , Knight knight){
        ArrayList<Box> result = new ArrayList<>();
        int row = knight.getLocation().getRow();
        char coll = knight.getLocation().getColl();
        int direction = (knight.getColor() == COLOR.W) ? 2 : -2;
        Box checkRight = new Box((char)(coll + direction) , row + 1);
        Box checkLeft = new Box((char)(coll + direction) , row -1);
        ArrayList<Box> help = new ArrayList<>();
        help.add(checkRight);
        help.add(checkLeft);
        for(Box box : help){
            if(Box.inBoard(box)){
                if(!Move.isOccupiedBox(board ,box)){
                    result.add(box);
                }
                else {
                    //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
                    if (!isSameColor(board,box,knight.getColor())){
                        result.add(box);
                    }
                    else{
                        board.getPiece(box).setIsProtected(true);
                    }
                }
            }
        }
        return result;
    }
    /**
     *
     * @param board the main current played board instance.
     * @param knight instance of a knight to work with.
     * @return array list of boxes containing all empty boxes to the left of the knight.
     */
    public static ArrayList<Box> leftKnight(Pieces board , Knight knight){
        ArrayList<Box> result = new ArrayList<>();
        int row = knight.getLocation().getRow();
        char coll = knight.getLocation().getColl();
        int direction = (knight.getColor() == COLOR.B) ? 2 : -2;
        Box checkRight = new Box((char)(coll + direction) , row + 1);
        Box checkLeft = new Box((char)(coll + direction) , row -1);
        ArrayList<Box> help = new ArrayList<>();
        help.add(checkRight);
        help.add(checkLeft);
        for(Box box : help){
            if(Box.inBoard(box)){
                if(!Move.isOccupiedBox(board ,box)){
                    result.add(box);
                }
                else {
                    //is in this box is filled with opposite piece then add the option to eat it , else make this box protected.
                    if (!isSameColor(board,box,knight.getColor())){
                        result.add(box);
                    }
                    else{
                        board.getPiece(box).setIsProtected(true);
                    }
                }
            }
        }
        return result;
    }

    /**
     * private helper function to check if the user entered a potential move.
     * @param board the main current played board instance.
     * @param location the user location input - the current location of the piece.
     * @param destination the user destination input - the destination to move the piece to.
     * @return true if the move is valid.
     */
    private static boolean isPotenMove(Pieces board , Box location ,Box destination){
        if(!Box.inBoard(location) || !Box.inBoard(destination))return false;
        return board.getPiece(location).getPotenMoves().contains(destination);
    }

    /**
     * private help function.
     * @param str - user input.
     * @return convert the str to a box and return it or return null.
     */
    private static Box stringToBox(String str){
        if(str.length() == 2 && Character.isLetter(str.charAt(0)) && Character.isDigit(str.charAt(1))){
            char coll =Character.toLowerCase(str.charAt(0));
            int row = Character.getNumericValue(str.charAt(1));
            return new Box(coll , row);
        }
        return null;
    }

    /**
     * this function handle the user move query from string to box array with all the validation needed.
     * @param board the main current played board instance.
     * @return array list of box with two boxes(first - location , second - destination).
     */
    public static ArrayList<Box> getMoveInput(Pieces board){
        ArrayList<Box> result = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("Please enter piece location to play with(format example 'a5') / enter 'r' to resign");
            String input = sc.next();
            if(Objects.equals(input, "r")){
                result.add(new Box('r' , -1));
                return result;
            }
            Box pieceLocation = stringToBox(input);
            if(pieceLocation == null || !board.getBoard().containsKey(pieceLocation)){
                System.out.println("error");
                continue;
            }
            System.out.println("enter destination: (format example 'a5')");
            input = sc.next();
            Box pieceDestination = stringToBox(input);
            if(pieceDestination == null){
                System.out.println("error\n");
                continue;
            }
            if(!isPotenMove(board , pieceLocation , pieceDestination)){
                System.out.println("error\n");
                continue;
            }
            result.add(pieceLocation);
            result.add(pieceDestination);
            break;
        }
        return result;
    }


}
