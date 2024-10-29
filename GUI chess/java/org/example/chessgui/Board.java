package org.example.chessgui;
import java.util.HashMap;

public class Board {
    /**
     * @return - classic chess initial board
     */
    public static HashMap<Box,Piece> getChessBoard(){
        HashMap<Box,Piece> board = new HashMap<>();

        int blackPawnRow = 7;
        int blackPiecesRow = 8;
        int whitePawnRow = 2;
        int whitePiecesRow = 1;

        //____________________________________________White Pawns______________________________________
        Pawn pawnWa = new Pawn(COLOR.W,new Box('a',whitePawnRow));
        Pawn pawnWb = new Pawn(COLOR.W,new Box('b',whitePawnRow));
        Pawn pawnWc =new Pawn(COLOR.W,new Box('c',whitePawnRow));
        Pawn pawnWd=new Pawn(COLOR.W,new Box('d',whitePawnRow));
        Pawn pawnWe=new Pawn(COLOR.W,new Box('e',whitePawnRow));
        Pawn pawnWf=new Pawn(COLOR.W,new Box('f',whitePawnRow));
        Pawn pawnWg=new Pawn(COLOR.W,new Box('g',whitePawnRow));
        Pawn pawnWh=new Pawn(COLOR.W,new Box('h',whitePawnRow));

        //___________________________________________White Rooks________________________________________

        Rook rookWKing = new Rook(COLOR.W,new Box('h',whitePiecesRow));
        Rook rookWQueen = new Rook(COLOR.W,new Box('a',whitePiecesRow));

        //___________________________________________White Knights________________________________________

        Knight knightWKing = new Knight(COLOR.W,new Box('g',whitePiecesRow));
        Knight knightWQueen = new Knight(COLOR.W,new Box('b',whitePiecesRow));

        //___________________________________________White Bishops________________________________________

        Bishop bishopWKing = new Bishop(COLOR.W,new Box('f',whitePiecesRow));
        Bishop bishopWQueen = new Bishop(COLOR.W,new Box('c',whitePiecesRow));

        //___________________________________________White Queen________________________________________

        Queen queenW = new Queen(COLOR.W,new Box('d',whitePiecesRow));

        //___________________________________________White King________________________________________

        King kingW = new King(COLOR.W,new Box('e',whitePiecesRow),rookWKing,rookWQueen);

        //____________________________________________Black Pawns______________________________________
        Pawn pawnBa = new Pawn(COLOR.B,new Box('a',blackPawnRow));
        Pawn pawnBb = new Pawn(COLOR.B,new Box('b',blackPawnRow));
        Pawn pawnBc =new Pawn(COLOR.B,new Box('c',blackPawnRow));
        Pawn pawnBd=new Pawn(COLOR.B,new Box('d',blackPawnRow));
        Pawn pawnBe=new Pawn(COLOR.B,new Box('e',blackPawnRow));
        Pawn pawnBf=new Pawn(COLOR.B,new Box('f',blackPawnRow));
        Pawn pawnBg=new Pawn(COLOR.B,new Box('g',blackPawnRow));
        Pawn pawnBh=new Pawn(COLOR.B,new Box('h',blackPawnRow));

        //___________________________________________Black Rooks________________________________________

        Rook rookBKing = new Rook(COLOR.B,new Box('h',blackPiecesRow));
        Rook rookBQueen = new Rook(COLOR.B,new Box('a',blackPiecesRow));

        //___________________________________________Black Knights________________________________________

        Knight knightBKing = new Knight(COLOR.B,new Box('g',blackPiecesRow));
        Knight knightBQueen = new Knight(COLOR.B,new Box('b',blackPiecesRow));

        //___________________________________________Black Bishops________________________________________

        Bishop bishopBKing = new Bishop(COLOR.B,new Box('f',blackPiecesRow));
        Bishop bishopBQueen = new Bishop(COLOR.B,new Box('c',blackPiecesRow));

        //___________________________________________Black Queen________________________________________

        Queen queenB = new Queen(COLOR.B,new Box('d',blackPiecesRow));

        //___________________________________________Black King________________________________________

        King kingB = new King(COLOR.B,new Box('e',blackPiecesRow),rookBKing,rookBQueen);

        //________________________________________put white pieces into the board______________________

        board.put(pawnWa.getLocation(),pawnWa);
        board.put(pawnWb.getLocation(),pawnWb);
        board.put(pawnWc.getLocation(),pawnWc);
        board.put(pawnWd.getLocation(),pawnWd);
        board.put(pawnWe.getLocation(),pawnWe);
        board.put(pawnWf.getLocation(),pawnWf);
        board.put(pawnWg.getLocation(),pawnWg);
        board.put(pawnWh.getLocation(),pawnWh);

        board.put(rookWKing.getLocation(),rookWKing);
        board.put(rookWQueen.getLocation(),rookWQueen);

        board.put(knightWKing.getLocation(),knightWKing);
        board.put(knightWQueen.getLocation(),knightWQueen);

        board.put(bishopWKing.getLocation(),bishopWKing);
        board.put(bishopWQueen.getLocation(),bishopWQueen);

        board.put(queenW.getLocation(),queenW);

        board.put(kingW.getLocation(),kingW);

        //________________________________________put black pieces into the board______________________

        board.put(pawnBa.getLocation(),pawnBa);
        board.put(pawnBb.getLocation(),pawnBb);
        board.put(pawnBc.getLocation(),pawnBc);
        board.put(pawnBd.getLocation(),pawnBd);
        board.put(pawnBe.getLocation(),pawnBe);
        board.put(pawnBf.getLocation(),pawnBf);
        board.put(pawnBg.getLocation(),pawnBg);
        board.put(pawnBh.getLocation(),pawnBh);

        board.put(rookBKing.getLocation(),rookBKing);
        board.put(rookBQueen.getLocation(),rookBQueen);

        board.put(knightBKing.getLocation(),knightBKing);
        board.put(knightBQueen.getLocation(),knightBQueen);

        board.put(bishopBKing.getLocation(),bishopBKing);
        board.put(bishopBQueen.getLocation(),bishopBQueen);

        board.put(queenB.getLocation(),queenB);

        board.put(kingB.getLocation(),kingB);

        return board;
    }

    //___________________________________________________Tests boards_______________________________________________________
    public static HashMap<Box , Piece> testMate(){

        HashMap<Box , Piece> board = new HashMap<>();


        int blackPawnRow = 7;
        int blackPiecesRow = 8;
        int whitePawnRow = 2;
        int whitePiecesRow = 1;

        //white Rooks
        Rook whiteKingSideRook = new Rook(COLOR.W , new Box('h' , whitePiecesRow));
        Rook whiteQueenSideRook = new Rook(COLOR.W , new Box('a' , whitePiecesRow));

        King whiteKing = new King(COLOR.W , new Box('e' , whitePiecesRow) , whiteKingSideRook , whiteQueenSideRook);
        //white Queen
        Queen whiteQueen = new Queen(COLOR.W ,new Box( 'f' , 7));
        //white bishop
        Bishop whiteKingSideBishop = new Bishop(COLOR.W , new Box('c' , 4));



        //Black Rooks
        Rook blackKingSideRook = new Rook(COLOR.B , new Box('h' , blackPiecesRow));
        Rook blackQueenSideRook = new Rook(COLOR.B , new Box('a' , blackPiecesRow));
        //Black King
        King blackKing = new King(COLOR.B , new Box('e' , blackPiecesRow) , blackKingSideRook , blackQueenSideRook);

        //put white pieces into the Hash map.
        board.put(whiteKingSideBishop.getLocation() , whiteKingSideBishop);
        board.put(whiteKing.getLocation() , whiteKing);
        board.put(whiteQueen.getLocation() , whiteQueen);
        //Black Queen
        Queen blackQueen = new Queen(COLOR.B ,new Box( 'd' , blackPiecesRow));

        //put Black pieces into the Hash map.
        board.put(blackQueen.getLocation() , blackQueen);
        board.put(blackKing.getLocation() , blackKing);

        return board;
    }

    public static HashMap<Box,Piece> testStalemate(){

        HashMap<Box , Piece> board = new HashMap<>();

        int blackPawnRow = 7;
        int blackPiecesRow = 8;
        int whitePawnRow = 2;
        int whitePiecesRow = 1;

        //white Rooks
        Rook whiteKingSideRook = new Rook(COLOR.W , new Box('h' , whitePiecesRow));
        Rook whiteQueenSideRook = new Rook(COLOR.W , new Box('b' , whitePiecesRow));


        King whiteKing = new King(COLOR.W , new Box('e' , whitePiecesRow) , whiteKingSideRook , whiteQueenSideRook);
        //white Queen
        Queen whiteQueen = new Queen(COLOR.W ,new Box( 'f' , 7));

        //Black Rooks
        Rook blackKingSideRook = new Rook(COLOR.B , new Box('h' , blackPiecesRow));
        Rook blackQueenSideRook = new Rook(COLOR.B , new Box('a' , blackPiecesRow));
        //Black King
        King blackKing = new King(COLOR.B , new Box('a' , blackPiecesRow) , blackKingSideRook , blackQueenSideRook);

        board.put(blackKing.getLocation(),blackKing);
        board.put(whiteQueen.getLocation(),whiteQueen);
        board.put(whiteKing.getLocation(),whiteKing);
        board.put(whiteQueenSideRook.getLocation(),whiteQueenSideRook);

        return board;


    }

    public static HashMap<Box,Piece> testDraw(){

        HashMap<Box , Piece> board = new HashMap<>();

        int blackPawnRow = 7;
        int blackPiecesRow = 8;
        int whitePawnRow = 2;
        int whitePiecesRow = 1;

        //white Rooks
        Rook whiteKingSideRook = new Rook(COLOR.W , new Box('h' , whitePiecesRow));
        Rook whiteQueenSideRook = new Rook(COLOR.W , new Box('b' , whitePiecesRow));


        King whiteKing = new King(COLOR.W , new Box('e' , whitePiecesRow) , whiteKingSideRook , whiteQueenSideRook);

        //Black Rooks
        Rook blackKingSideRook = new Rook(COLOR.B , new Box('h' , blackPiecesRow));
        Rook blackQueenSideRook = new Rook(COLOR.B , new Box('a' , blackPiecesRow));
        //Black King
        King blackKing = new King(COLOR.B , new Box('a' , blackPiecesRow) , blackKingSideRook , blackQueenSideRook);

//        Knight knightWKing = new Knight(COLOR.W,new Box('g',whitePiecesRow));
//        Bishop bishopBQueen = new Bishop(COLOR.B,new Box('c',blackPiecesRow));
        Bishop bishopWKing = new Bishop(COLOR.W,new Box('f',whitePiecesRow));
        Pawn pawnBf=new Pawn(COLOR.B,new Box('f',blackPawnRow));





        board.put(whiteKing.getLocation(),whiteKing);
        board.put(blackKing.getLocation(),blackKing);
//        board.put(bishopBQueen.getLocation(),bishopBQueen);
        board.put(bishopWKing.getLocation(),bishopWKing);
        board.put(pawnBf.getLocation(),pawnBf);


        return board;
    }

    public static HashMap<Box , Piece> getCastleBoard(){

        HashMap<Box , Piece> board = new HashMap<>();

        int blackPawnRow = 7;
        int blackPiecesRow = 8;
        int whitePawnRow = 2;
        int whitePiecesRow = 1;

        //white Rooks
        Rook whiteKingSideRook = new Rook(COLOR.W , new Box('f' , whitePiecesRow));
        Rook whiteQueenSideRook = new Rook(COLOR.W , new Box('a' , whitePiecesRow));

        King whiteKing = new King(COLOR.W , new Box('g' , whitePiecesRow) , whiteKingSideRook , whiteQueenSideRook);

        //Black Rooks
        Rook blackKingSideRook = new Rook(COLOR.B , new Box('f' , blackPiecesRow));
        Rook blackQueenSideRook = new Rook(COLOR.B , new Box('a' , blackPiecesRow));
        //Black King
        King blackKing = new King(COLOR.B , new Box('e' , blackPiecesRow) , blackKingSideRook , blackQueenSideRook);
        Queen blackQueen = new Queen(COLOR.B , new Box('b' , 1));

        //put white pieces into the Hash map.
        board.put(whiteKing.getLocation() , whiteKing);
        board.put(whiteQueenSideRook.getLocation() , whiteQueenSideRook);
        board.put(whiteKingSideRook.getLocation() , whiteKingSideRook);
        board.put(blackQueen.getLocation() , blackQueen);

        //put Black pieces into the Hash map.
        board.put(blackKing.getLocation() , blackKing);

        return board;
    }

    public static HashMap<Box,Piece> TestBoard(){
        HashMap<Box,Piece> board = new HashMap<>();

        int blackPawnRow = 7;
        int blackPiecesRow = 8;
        int whitePawnRow = 2;
        int whitePiecesRow = 1;

        //____________________________________________White Pawns______________________________________
        Pawn pawnWa = new Pawn(COLOR.W,new Box('a',whitePawnRow));
        Pawn pawnWb = new Pawn(COLOR.W,new Box('b',whitePawnRow));
        Pawn pawnWc =new Pawn(COLOR.W,new Box('c',whitePawnRow));
        Pawn pawnWd=new Pawn(COLOR.W,new Box('d',4));
        Pawn pawnWe=new Pawn(COLOR.W,new Box('e',whitePawnRow));
        Pawn pawnWf=new Pawn(COLOR.W,new Box('f',whitePawnRow));
        Pawn pawnWg=new Pawn(COLOR.W,new Box('g',3));
        Pawn pawnWh=new Pawn(COLOR.W,new Box('h',whitePawnRow));

        //___________________________________________White Rooks________________________________________

        Rook rookWKing = new Rook(COLOR.W,new Box('h',whitePiecesRow));
        Rook rookWQueen = new Rook(COLOR.W,new Box('a',whitePiecesRow));

        //___________________________________________White Knights________________________________________

        Knight knightWKing = new Knight(COLOR.W,new Box('f',3));
        Knight knightWQueen = new Knight(COLOR.W,new Box('b',whitePiecesRow));

        //___________________________________________White Bishops________________________________________

        Bishop bishopWKing = new Bishop(COLOR.W,new Box('g',2));
        Bishop bishopWQueen = new Bishop(COLOR.W,new Box('c',whitePiecesRow));

        //___________________________________________White Queen________________________________________

        Queen queenW = new Queen(COLOR.W,new Box('d',whitePiecesRow));

        //___________________________________________White King________________________________________

        King kingW = new King(COLOR.W,new Box('e',whitePiecesRow),rookWKing,rookWQueen);

        //____________________________________________Black Pawns______________________________________
        Pawn pawnBa = new Pawn(COLOR.B,new Box('a',blackPawnRow));
        Pawn pawnBb = new Pawn(COLOR.B,new Box('b',blackPawnRow));
        Pawn pawnBc =new Pawn(COLOR.B,new Box('c',blackPawnRow));
        Pawn pawnBd=new Pawn(COLOR.B,new Box('d',5));
        Pawn pawnBe=new Pawn(COLOR.B,new Box('e',blackPawnRow));
        Pawn pawnBf=new Pawn(COLOR.B,new Box('f',blackPawnRow));
        Pawn pawnBg=new Pawn(COLOR.B,new Box('g',6));
        Pawn pawnBh=new Pawn(COLOR.B,new Box('h',blackPawnRow));

        //___________________________________________Black Rooks________________________________________

        Rook rookBKing = new Rook(COLOR.B,new Box('h',blackPiecesRow));
        Rook rookBQueen = new Rook(COLOR.B,new Box('a',blackPiecesRow));

        //___________________________________________Black Knights________________________________________

        Knight knightBKing = new Knight(COLOR.B,new Box('f',6));
        Knight knightBQueen = new Knight(COLOR.B,new Box('b',blackPiecesRow));

        //___________________________________________Black Bishops________________________________________

        Bishop bishopBKing = new Bishop(COLOR.B,new Box('g',7));
        Bishop bishopBQueen = new Bishop(COLOR.B,new Box('c',blackPiecesRow));

        //___________________________________________Black Queen________________________________________

        Queen queenB = new Queen(COLOR.B,new Box('d',blackPiecesRow));

        //___________________________________________Black King________________________________________

        King kingB = new King(COLOR.B,new Box('e',blackPiecesRow),rookBKing,rookBQueen);

        //________________________________________put white pieces into the board______________________

        board.put(pawnWa.getLocation(),pawnWa);
        board.put(pawnWb.getLocation(),pawnWb);
        board.put(pawnWc.getLocation(),pawnWc);
        board.put(pawnWd.getLocation(),pawnWd);
        board.put(pawnWe.getLocation(),pawnWe);
        board.put(pawnWf.getLocation(),pawnWf);
        board.put(pawnWg.getLocation(),pawnWg);
        board.put(pawnWh.getLocation(),pawnWh);

        board.put(rookWKing.getLocation(),rookWKing);
        board.put(rookWQueen.getLocation(),rookWQueen);

        board.put(knightWKing.getLocation(),knightWKing);
        board.put(knightWQueen.getLocation(),knightWQueen);

        board.put(bishopWKing.getLocation(),bishopWKing);
        board.put(bishopWQueen.getLocation(),bishopWQueen);

        board.put(queenW.getLocation(),queenW);

        board.put(kingW.getLocation(),kingW);

        //________________________________________put black pieces into the board______________________

        board.put(pawnBa.getLocation(),pawnBa);
        board.put(pawnBb.getLocation(),pawnBb);
        board.put(pawnBc.getLocation(),pawnBc);
        board.put(pawnBd.getLocation(),pawnBd);
        board.put(pawnBe.getLocation(),pawnBe);
        board.put(pawnBf.getLocation(),pawnBf);
        board.put(pawnBg.getLocation(),pawnBg);
        board.put(pawnBh.getLocation(),pawnBh);

        board.put(rookBKing.getLocation(),rookBKing);
        board.put(rookBQueen.getLocation(),rookBQueen);

        board.put(knightBKing.getLocation(),knightBKing);
        board.put(knightBQueen.getLocation(),knightBQueen);

        board.put(bishopBKing.getLocation(),bishopBKing);
        board.put(bishopBQueen.getLocation(),bishopBQueen);

        board.put(queenB.getLocation(),queenB);

        board.put(kingB.getLocation(),kingB);

        return board;
    }

    public static HashMap<Box,Piece> testPromotion(){
        HashMap<Box,Piece> board = new HashMap<>();

        int blackPawnRow = 7;
        int blackPiecesRow = 8;
        int whitePawnRow = 2;
        int whitePiecesRow = 1;

        //____________________________________________White Pawns______________________________________
        Pawn pawnWa = new Pawn(COLOR.W,new Box('h',7));

        Rook rookWKing = new Rook(COLOR.W,new Box('f',whitePiecesRow));
        Rook rookWQueen = new Rook(COLOR.W,new Box('d',whitePiecesRow));

        King kingW = new King(COLOR.W,new Box('e',whitePiecesRow),rookWKing,rookWQueen);

        Rook rookBKing = new Rook(COLOR.B,new Box('h',blackPiecesRow));
        Rook rookBQueen = new Rook(COLOR.B,new Box('d',blackPiecesRow));

        King kingB = new King(COLOR.B,new Box('c',blackPiecesRow),rookBKing,rookBQueen);

        //________________________________________put white pieces into the board______________________

        board.put(pawnWa.getLocation(),pawnWa);
        board.put(kingB.getLocation(),kingB);
        board.put(kingW.getLocation(),kingW);

        return board;

    }



    //___________________________________________________Display Methods_______________________________________________________
    public static void displayBoard(Pieces board){
        StringBuilder sb = new StringBuilder(printFile());
        sb.append("\n").append(printLinesSeparator()).append("\n");
        for (int i = BOARD_LIMIT.LAST_ROW.getRowVal(); i >= BOARD_LIMIT.FIRST_ROW.getRowVal() ; i--) {
            sb.append(i).append(" |");
            for (char j = BOARD_LIMIT.FIRST_COLL.getColVal(); j <= BOARD_LIMIT.LAST_COLL.getColVal() ; j++) {
                Box box = new Box(j,i);
                if(board.getBoard().containsKey(box)){
                    sb.append(" ");
                    sb.append(board.getPiece(box).getPieceType().toString());
                    sb.append(board.getPiece(box).getColor().toString()).append(" |");
                }
                else{
                    sb.append("    |");
                }
            }
            sb.append(" ").append(i).append("\n").append(printLinesSeparator()).append("\n");
        }
        sb.append(printFile()).append("\n");
        System.out.println(sb);
    }

    private static String printFile(){
        StringBuilder sb = new StringBuilder("    ");
        for (char j = BOARD_LIMIT.FIRST_COLL.getColVal(); j <= BOARD_LIMIT.LAST_COLL.getColVal() ; j++) {
            sb.append(j).append("    ");
        }
        return sb.toString();
    }

    private static String printLinesSeparator(){
        StringBuilder sb = new StringBuilder("  |");
        for (int i = BOARD_LIMIT.FIRST_ROW.getRowVal(); i <= BOARD_LIMIT.LAST_ROW.getRowVal(); i++) {
            sb.append("----|");
        }
        return sb.toString();
    }



}
