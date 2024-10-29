package org.example.chessgui;
import java.util.ArrayList;
import java.util.Objects;

public class Queen extends Piece{


    Queen(COLOR color,Box initLocation){
        super(ID.Queen,color,initLocation,9);
    }

    @Override
    public ArrayList<Box> getRawMoves(Pieces board) {
        Objects.requireNonNull(board);
        ArrayList<Box> result = new ArrayList<>();
        result.addAll(Move.frontFreeBoxes(board,this));
        result.addAll(Move.backFreeBoxes(board,this));
        result.addAll(Move.rightFreeBoxes(board,this));
        result.addAll(Move.leftFreeBoxes(board,this));

        result.addAll(Move.diagFrontRight(board,this));
        result.addAll(Move.diagFrontLeft(board,this));
        result.addAll(Move.diagBackRight(board,this));
        result.addAll(Move.diagBackLeft(board,this));

        return result;
    }

    @Override
    public Piece makeCopy() {
        Queen result =  new Queen(getColor(),getInitLocation());
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
