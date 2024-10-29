package org.example.chessgui;
import java.util.ArrayList;


public class Move {

    /**
     * compare between to colors
     * @param board - the current played board
     * @param destination - the piece to compare with
     * @param color - the color to compare with
     * @return - true if they are same color
     */
    public static boolean isSameColor(Pieces board, Box destination,COLOR color){
        return board.getPiece(destination).getColor() == color;
    }

    /**
     * @param board - the current played board
     * @param destination - the box to check
     * @return - true if the box is occupied
     */
    public static boolean isOccupiedBox(Pieces board, Box destination){
        return board.getPiece(destination) != null;
    }

    /**
     * @param board - the current played board
     * @param piece - instance of a piece to work with
     * @return - array list of boxes containing all empty boxes to the front of the given piece
     */
    public static ArrayList<Box> frontFreeBoxes(Pieces board, Piece piece){
        ArrayList<Box> result = new ArrayList<>();
        boolean endSearch = false;
        int direction = piece.getColor() == COLOR.B? -1: 1; // black or white piece
        int moveLength = direction, row = piece.getLocation().getRow();
        char col = piece.getLocation().getCol();
        while (!endSearch){
            Box checkFree = new Box(col,row+moveLength);
            if(!Box.inBoard(checkFree))break;
            if(!isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                // if in this box there is opposite piece than add to option to eat it, else make this box protected
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else{
                    board.getPiece(checkFree).setProtected(true);
                }
                endSearch = true;
            }
            moveLength += direction;
            if(piece.getPieceType() == ID.King){
                endSearch = true;
            }
        }
        return result;
    }

    /**
     * @param board - the current played board
     * @param piece - instance of a piece to work with
     * @return - array list of boxes containing all empty boxes to the back of the given piece
     */
    public static ArrayList<Box> backFreeBoxes(Pieces board, Piece piece){
        ArrayList<Box> result = new ArrayList<>();
        boolean endSearch = false;
        int direction = piece.getColor() == COLOR.W? -1: 1;
        int moveLength = direction, row = piece.getLocation().getRow();
        char col = piece.getLocation().getCol();
        while (!endSearch){
            Box checkFree = new Box(col,row+moveLength);
            if(!Box.inBoard(checkFree))break;
            if(!isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                // if in this box there is opposite piece than add to option to eat it, else make this box protected
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else{
                    board.getPiece(checkFree).setProtected(true);
                }
                endSearch = true;
            }
            moveLength += direction;
            if(piece.getPieceType() == ID.King){
                endSearch = true;
            }
        }
        return result;
    }

    /**
     * @param board - the current played board
     * @param piece - instance of a piece to work with
     * @return - array list of boxes containing all empty boxes to the right of the given piece
     */
    public static ArrayList<Box> rightFreeBoxes(Pieces board, Piece piece){
        ArrayList<Box> result = new ArrayList<>();
        boolean endSearch = false;
        int row = piece.getLocation().getRow(),direction =  1;
        char col = piece.getLocation().getCol();
        while (!endSearch){
            Box checkFree = new Box((char) (col + direction),row);
            if(!Box.inBoard(checkFree))break;
            if(!isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                // if in this box there is opposite piece than add to option to eat it, else make this box protected
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else{
                    board.getPiece(checkFree).setProtected(true);
                }
                endSearch = true;
            }
           direction++;
            if(piece.getPieceType() == ID.King){
                endSearch = true;
            }
        }
        return result;
    }

    /**
     * @param board - the current played board
     * @param piece - instance of a piece to work with
     * @return - array list of boxes containing all empty boxes to the left of the given piece
     */
    public static ArrayList<Box> leftFreeBoxes(Pieces board, Piece piece){
        ArrayList<Box> result = new ArrayList<>();
        boolean endSearch = false;
        int row = piece.getLocation().getRow(),direction =  -1;
        char col = piece.getLocation().getCol();
        while (!endSearch){
            Box checkFree = new Box((char) (col + direction),row);
            if(!Box.inBoard(checkFree))break;
            if(!isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                // if in this box there is opposite piece than add to option to eat it, else make this box protected
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else{
                    board.getPiece(checkFree).setProtected(true);
                }
                endSearch = true;
            }
            direction--;
            if(piece.getPieceType() == ID.King ){
                endSearch = true;
            }
        }
        return result;
    }

    /**
     * @param board - the current played board
     * @param piece - instance of a piece to work with
     * @return - array list of boxes containing all empty boxes to the front right diagonal of the given piece
     */
    public static ArrayList<Box> diagFrontRight(Pieces board, Piece piece){
        ArrayList<Box> result = new ArrayList<>();
        boolean endSearch = false;
        int direction = piece.getColor() == COLOR.B? -1: 1;
        int moveLength = direction, row = piece.getLocation().getRow();
        char col = piece.getLocation().getCol();
        while (!endSearch){
            Box checkFree = new Box((char) (col+moveLength),row+moveLength);
            if(!Box.inBoard(checkFree))break;
            if(!isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                // if in this box there is opposite piece than add to option to eat it, else make this box protected
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else{
                    board.getPiece(checkFree).setProtected(true);
                }
                endSearch = true;
            }
            moveLength += direction;
            if(piece.getPieceType() == ID.King){
                endSearch = true;
            }
        }
        return result;
    }

    /**
     * @param board - the current played board
     * @param piece - instance of a piece to work with
     * @return - array list of boxes containing all empty boxes to the front left diagonal of the given piece
     */
    public static ArrayList<Box> diagFrontLeft(Pieces board, Piece piece){
        ArrayList<Box> result = new ArrayList<>();
        boolean endSearch = false;
        int rowDirection = piece.getColor() == COLOR.B? -1: 1;
        int colDirection = rowDirection*-1;
        int colLength = colDirection , rowLength = rowDirection;
        int  row = piece.getLocation().getRow();
        char col = piece.getLocation().getCol();
        while (!endSearch){
            Box checkFree = new Box((char) (col+colLength),row+rowLength);
            if(!Box.inBoard(checkFree))break;
            if(!isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                // if in this box there is opposite piece than add to option to eat it, else make this box protected
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else{
                    board.getPiece(checkFree).setProtected(true);
                }
                endSearch = true;
            }
            colLength += colDirection;
            rowLength += rowDirection;
            if(piece.getPieceType() == ID.King){
                endSearch = true;
            }
        }
        return result;
    }

    /**
     * @param board - the current played board
     * @param piece - instance of a piece to work with
     * @return - array list of boxes containing all empty boxes to the back right diagonal of the given piece
     */
    public static ArrayList<Box> diagBackRight(Pieces board, Piece piece){
        ArrayList<Box> result = new ArrayList<>();
        boolean endSearch = false;
        int rowDirection = piece.getColor() == COLOR.W? -1: 1;
        int colDirection = rowDirection*-1;
        int colLength = colDirection , rowLength = rowDirection;
        int  row = piece.getLocation().getRow();
        char col = piece.getLocation().getCol();
        while (!endSearch){
            Box checkFree = new Box((char) (col+colLength),row+rowLength);
            if(!Box.inBoard(checkFree))break;
            if(!isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                // if in this box there is opposite piece than add to option to eat it, else make this box protected
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else{
                    board.getPiece(checkFree).setProtected(true);
                }
                endSearch = true;
            }
            colLength += colDirection;
            rowLength += rowDirection;
            if(piece.getPieceType() == ID.King){
                endSearch = true;
            }
        }
        return result;
    }

    /**
     * @param board - the current played board
     * @param piece - instance of a piece to work with
     * @return - array list of boxes containing all empty boxes to the back left diagonal of the given piece
     */
    public static ArrayList<Box> diagBackLeft(Pieces board, Piece piece){
        ArrayList<Box> result = new ArrayList<>();
        boolean endSearch = false;
        int direction = piece.getColor() == COLOR.W? -1: 1;
        int moveLength = direction, row = piece.getLocation().getRow();
        char col = piece.getLocation().getCol();
        while (!endSearch){
            Box checkFree = new Box((char) (col+moveLength),row+moveLength);
            if(!Box.inBoard(checkFree))break;
            if(!isOccupiedBox(board,checkFree)){
                result.add(checkFree);
            }
            else{
                // if in this box there is opposite piece than add to option to eat it, else make this box protected
                if(!isSameColor(board,checkFree,piece.getColor())){
                    result.add(checkFree);
                }
                else{
                    board.getPiece(checkFree).setProtected(true);
                }
                endSearch = true;
            }
            moveLength += direction;
            if(piece.getPieceType() == ID.King){
                endSearch = true;
            }
        }
        return result;
    }

    /**
     * @param board - the current played board
     * @param knight - instance of a knight to work with
     * @return - array list of boxes containing all empty boxes to the front of the given knight
     */
    public static ArrayList<Box> frontKnight(Pieces board, Knight knight) {
        ArrayList<Box> result = new ArrayList<>();
        int row = knight.getLocation().getRow();
        char col = knight.getLocation().getCol();
        int direction = knight.getColor() == COLOR.W ? 2 : -2;
        Box checkRightFree = new Box((char) (col + 1), row + direction);
        Box checkLeftFree = new Box((char) (col - 1), row + direction);
        ArrayList<Box> help = new ArrayList<>();
        help.add(checkRightFree);
        help.add(checkLeftFree);
        for (Box box : help) {
            if (Box.inBoard(box)) {
                if (!isOccupiedBox(board, box)) {
                    result.add(box);
                } else {
                    // if in this box there is opposite piece than add to option to eat it, else make this box protected
                    if (!isSameColor(board,box,knight.getColor())) {
                        result.add(box);
                    }
                    else{
                        board.getPiece(box).setProtected(true);
                    }
                }
            }
        }
        return result;
    }

    /**
     * @param board - the current played board
     * @param knight - instance of a knight to work with
     * @return - array list of boxes containing all empty boxes to the back of the given knight
     */
    public static ArrayList<Box> backKnight(Pieces board, Knight knight){
        ArrayList<Box> result = new ArrayList<>();
        int row = knight.getLocation().getRow();
        char col = knight.getLocation().getCol();
        int direction = knight.getColor() == COLOR.B ? 2 : -2;
        Box checkRightFree = new Box((char) (col + 1), row + direction);
        Box checkLeftFree = new Box((char) (col - 1), row + direction);
        ArrayList<Box> help = new ArrayList<>();
        help.add(checkRightFree);
        help.add(checkLeftFree);
        for (Box box : help) {
            if (Box.inBoard(box)) {
                if (!isOccupiedBox(board, box)) {
                    result.add(box);
                } else {
                    // if in this box there is opposite piece than add to option to eat it, else make this box protected
                    if (!isSameColor(board,box,knight.getColor())) {
                        result.add(box);
                    }
                    else{
                        board.getPiece(box).setProtected(true);
                    }
                }
            }
        }
        return result;
    }

    /**
     * @param board - the current played board
     * @param knight - instance of a knight to work with
     * @return - array list of boxes containing all empty boxes to the right of the given knight
     */
    public static ArrayList<Box> rightKnight(Pieces board, Knight knight){
        ArrayList<Box> result = new ArrayList<>();
        int row = knight.getLocation().getRow();
        char col = knight.getLocation().getCol();
        int direction = knight.getColor() == COLOR.W ? 2 : -2;
        Box checkRightFree = new Box((char) (col + direction), row + 1);
        Box checkLeftFree = new Box((char) (col + direction), row - 1 );
        ArrayList<Box> help = new ArrayList<>();
        help.add(checkRightFree);
        help.add(checkLeftFree);
        for (Box box : help) {
            if (Box.inBoard(box)) {
                if (!isOccupiedBox(board, box)) {
                    result.add(box);
                } else {
                    // if in this box there is opposite piece than add to option to eat it, else make this box protected
                    if (!isSameColor(board,box,knight.getColor())) {
                        result.add(box);
                    }
                    else{
                        board.getPiece(box).setProtected(true);
                    }
                }
            }
        }
        return result;
    }

    /**
     * @param board - the current played board
     * @param knight - instance of a knight to work with
     * @return - array list of boxes containing all empty boxes to the left of the given knight
     */
    public static ArrayList<Box> leftKnight(Pieces board, Knight knight){
        ArrayList<Box> result = new ArrayList<>();
        int row = knight.getLocation().getRow();
        char col = knight.getLocation().getCol();
        int direction = knight.getColor() == COLOR.B? 2 : -2;
        Box checkRightFree = new Box((char) (col + direction), row + 1);
        Box checkLeftFree = new Box((char) (col + direction), row - 1 );
        ArrayList<Box> help = new ArrayList<>();
        help.add(checkRightFree);
        help.add(checkLeftFree);
        for (Box box : help) {
            if (Box.inBoard(box)) {
                if (!isOccupiedBox(board, box)) {
                    result.add(box);
                } else {
                    // if in this box there is opposite piece than add to option to eat it, else make this box protected
                    if (!isSameColor(board,box,knight.getColor())) {
                        result.add(box);
                    }
                    else{
                        board.getPiece(box).setProtected(true);
                    }
                }
            }
        }
        return result;
    }

    /**
     * private help function
     * @param str - the string user input
     * @return - convert the str to BOX
     */
    public static Box stringToBox(String str){
        if(str.length() == 2 && Character.isLetter(str.charAt(0)) && Character.isDigit(str.charAt(1))){
            char coll =Character.toLowerCase(str.charAt(0));
            int row = Character.getNumericValue(str.charAt(1));
            return new Box(coll , row);
        }
        return null;
    }



}
