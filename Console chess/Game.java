import java.util.ArrayList;

public class Game {
    public static void main(String[] args) {
        Pieces board = new Pieces();
        gameLoop(board);
    }
    public static void gameLoop(Pieces board){
        Box resign = new Box('r' , -1);
        boolean endGame = false;
        COLOR turn = COLOR.W;
        Board.displayBoard(board);
        System.out.println(turn.toFullString() +" to move");
        board.updatePotenMoves(turn);
        while (!endGame){
            ArrayList<Box> move = Move.getMoveInput(board);
            if(move.getFirst().equals(resign)){
                System.out.println(turn.toFullString() + " resigned\n" + COLOR.not(turn).toFullString() + " wins!!");
                endGame = true;
                continue;
            }
            Piece current =  board.getPiece(move.getFirst());
            if(current.getColor() != turn){
                System.out.println("you cannot move other player piece");
                continue;
            }
            if(!board.makeMove(move.getLast(),board.getPiece(move.getFirst())))continue;
            board.updatePotenMoves(COLOR.not(turn));
            Board.displayBoard(board);
            if(board.isMate(COLOR.not(turn))){ 
                System.out.println(turn.toFullString() + " wins!!");
                endGame = true;
            }
            if(board.stalemate(turn)){
                System.out.println("the game ends with stalemate");
                endGame = true;
            }
            if(board.isDraw() || board.isThreeFoldDraw()){
                System.out.println("the game ends with Draw");
                endGame = true;
            }
            turn = COLOR.not(turn);
            System.out.println(turn.toFullString() +" to move");
        }
    }
}
