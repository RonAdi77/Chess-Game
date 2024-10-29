package org.example.chessgui;
import java.util.Objects;

public class Box {
    private char col;
    private int row;

    //____________________________________________________Constructor's___________________________________________________________

    Box(char col,int row){
        this.col = col;
        this.row = row;
    }

    Box(Box other){
        this(other.getCol(),other.getRow());
    }

    // __________________________________________________Getters & Setters__________________________________________________________

    public char getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }

    //___________________________________________________toString Method_______________________________________________________

    @Override
    public String toString() {
        return getCol()+""+getRow();
    }

    //___________________________________________________Box Method_______________________________________________________

    /**
     * @param box - given argument box
     * @return - if the given argument is in the board limit
     */
    public static boolean inBoard(Box box){
        if(box == null){return false;}
        char boxCol = box.getCol();
        int boxRow = box.getRow();
        return boxCol >= BOARD_LIMIT.FIRST_COLL.getColVal() && boxRow >= BOARD_LIMIT.FIRST_ROW.getRowVal() &&
                boxCol <= BOARD_LIMIT.LAST_COLL.getColVal() && boxRow <= BOARD_LIMIT.LAST_ROW.getRowVal();
    }


    //____________________________________________________Overridden Methods_____________________________________________________

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return getCol() == box.getCol() && getRow() == box.getRow();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCol(), getRow());
    }
}
