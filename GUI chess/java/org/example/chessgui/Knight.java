package org.example.chessgui;
import java.util.ArrayList;
import java.util.Objects;

public class Knight extends Piece{

    Knight(COLOR color,Box initLocation){
        super(ID.Knight,color,initLocation,3);
    }

    @Override
    public ArrayList<Box> getRawMoves(Pieces board) {
        Objects.requireNonNull(board);

        ArrayList<Box> result = new ArrayList<>();
        result.addAll(Move.frontKnight(board,this));
        result.addAll(Move.backKnight(board,this));
        result.addAll(Move.rightKnight(board,this));
        result.addAll(Move.leftKnight(board,this));

        return result;
    }

    @Override
    public Piece makeCopy() {
        Knight result =  new Knight(getColor(),getInitLocation());
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
