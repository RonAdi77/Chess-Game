import java.util.Objects;

public class Box {
    private char coll;
    private int row;//rank

    //____________________________________________________constructor's____________________________________________________

    public Box(char coll , int row){
        this.coll = coll;
        this.row = row;
    }
    public Box(Box other){
        this(other.getColl(), other.getRow());
    }

    //____________________________________________________getters & setters____________________________________________________

    public int getRow() {
        return row;
    }

    public char getColl() {
        return coll;
    }

    /**
     * @param box given box.
     * @return - if the argument is in the board limits.
     */
    public static boolean inBoard(Box box){
        if(box == null)return false;
        char coll = box.getColl();
        int row = box.getRow();
        return (coll >= BOARD_LIMITS.FIRST_COLL.getColl() &&
                coll <= BOARD_LIMITS.LAST_CALL.getColl() &&
                row >= BOARD_LIMITS.FIRST_ROW.getRow() &&
                row <= BOARD_LIMITS.LAST_ROW.getRow());
    }

    //____________________________________________________toString method____________________________________________________

    public String toString(){
        return coll + "" + row;
    }

    //____________________________________________________Overridden Methods____________________________________________________

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Box box = (Box) o;
        return coll == box.coll && row == box.row;
    }

    @Override
    public int hashCode() {
        return Objects.hash(coll, row);
    }
}
