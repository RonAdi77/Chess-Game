public enum BOARD_LIMITS {

    FIRST_COLL('a'),
    LAST_CALL('h'),
    FIRST_ROW(1),
    LAST_ROW(8);

    private char collVal;
    private int rowVal;

    BOARD_LIMITS(char val){
        collVal = val;
    }
    BOARD_LIMITS(int val){
        rowVal = val;
    }

    public char getColl(){
        return collVal;
    }
    public int getRow(){
        return rowVal;
    }
}
