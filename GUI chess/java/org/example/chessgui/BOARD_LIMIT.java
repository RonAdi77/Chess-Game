package org.example.chessgui;
public enum BOARD_LIMIT {

    FIRST_COLL('a'),
    LAST_COLL('h'),
    FIRST_ROW(1),
    LAST_ROW(8);
    private char colVal;
    private int rowVal;

    BOARD_LIMIT(char val){
        colVal = val;
    }

    BOARD_LIMIT(int val){
        rowVal = val;
    }

    public char getColVal(){
        return colVal;
    }

    public int getRowVal() {
        return rowVal;
    }
}
