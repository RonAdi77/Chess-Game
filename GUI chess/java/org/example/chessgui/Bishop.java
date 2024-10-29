package org.example.chessgui;
import java.util.ArrayList;
import java.util.Objects;

public class Bishop extends Piece{

    Bishop(COLOR color,Box initLocation){
        super(ID.Bishop,color,initLocation,3);
    }

    @Override
    public ArrayList<Box> getRawMoves(Pieces board){
        Objects.requireNonNull(board);

        ArrayList<Box> result = new ArrayList<>();
        result.addAll(Move.diagFrontRight(board,this));
        result.addAll(Move.diagFrontLeft(board,this));
        result.addAll(Move.diagBackRight(board,this));
        result.addAll(Move.diagBackLeft(board,this));

        return result;
    }

    @Override
    public Piece makeCopy() {
        Bishop result =  new Bishop(getColor(),getInitLocation());
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
