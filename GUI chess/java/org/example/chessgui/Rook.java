package org.example.chessgui;
import java.util.ArrayList;
import java.util.Objects;

public class Rook extends Piece{

    Rook(COLOR color,Box initLocation){
        super(ID.Rook,color,initLocation,5);
    }

    @Override
    public ArrayList<Box> getRawMoves(Pieces board) {
        Objects.requireNonNull(board);

        ArrayList<Box> result = new ArrayList<>();
        result.addAll(Move.frontFreeBoxes(board,this));
        result.addAll(Move.backFreeBoxes(board,this));
        result.addAll(Move.rightFreeBoxes(board,this));
        result.addAll(Move.leftFreeBoxes(board,this));


        return result;
    }

    @Override
    public Piece makeCopy() {
        Rook result =  new Rook(getColor(),getInitLocation());
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
