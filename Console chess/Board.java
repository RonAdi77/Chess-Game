import java.util.HashMap;

public class Board {
    /**
     * @return return classic chess initial board.
     */
    public static HashMap<Box , Piece> getChessBoard(){
        HashMap<Box , Piece> board = new HashMap<>();

        int blackPawnRow = 7;
        int blackPiecesRow = 8;
        int whitePawnRow = 2;
        int whitePiecesRow = 1;

        //white pawns.
        Pawn Wa = new Pawn(COLOR.W , new Box('a' , whitePawnRow));
        Pawn Wb = new Pawn(COLOR.W , new Box('b' , whitePawnRow));
        Pawn Wc = new Pawn(COLOR.W , new Box('c' , whitePawnRow));
        Pawn Wd = new Pawn(COLOR.W , new Box('d' , whitePawnRow));
        Pawn We = new Pawn(COLOR.W , new Box('e' , whitePawnRow));
        Pawn Wf = new Pawn(COLOR.W , new Box('f' , whitePawnRow));
        Pawn Wg = new Pawn(COLOR.W , new Box('g' , whitePawnRow));
        Pawn Wh = new Pawn(COLOR.W , new Box('h' , whitePawnRow));

        //white Rooks
        Rook whiteKingSideRook = new Rook(COLOR.W , new Box('h' , whitePiecesRow));
        Rook whiteQueenSideRook = new Rook(COLOR.W , new Box('a' , whitePiecesRow));

        //white Knight
        Knight whiteKingSideKnight = new Knight(COLOR.W , new Box('g' , whitePiecesRow));
        Knight whiteQueenSideKnight = new Knight(COLOR.W , new Box('b' , whitePiecesRow));

        //white bishop
        Bishop whiteKingSideBishop = new Bishop(COLOR.W , new Box('f' , whitePiecesRow));
        Bishop whiteQueenSideBishop = new Bishop(COLOR.W , new Box('c' , whitePiecesRow));

        //white Queen
        Queen whiteQueen = new Queen(COLOR.W ,new Box( 'd' , whitePiecesRow));

        //white King
        King whiteKing = new King(COLOR.W , new Box('e' , whitePiecesRow) , whiteKingSideRook , whiteQueenSideRook);

        //Black pawns.
        Pawn Ba = new Pawn(COLOR.B , new Box('a' , blackPawnRow));
        Pawn Bb = new Pawn(COLOR.B , new Box('b' , blackPawnRow));
        Pawn Bc = new Pawn(COLOR.B , new Box('c' , blackPawnRow));
        Pawn Bd = new Pawn(COLOR.B , new Box('d' , blackPawnRow));
        Pawn Be = new Pawn(COLOR.B , new Box('e' , blackPawnRow));
        Pawn Bf = new Pawn(COLOR.B , new Box('f' , blackPawnRow));
        Pawn Bg = new Pawn(COLOR.B , new Box('g' , blackPawnRow));
        Pawn Bh = new Pawn(COLOR.B , new Box('h' , blackPawnRow));

        //Black Rooks
        Rook blackKingSideRook = new Rook(COLOR.B , new Box('h' , blackPiecesRow));
        Rook blackQueenSideRook = new Rook(COLOR.B , new Box('a' , blackPiecesRow));

        //Black Knight
        Knight blackKingSideKnight = new Knight(COLOR.B , new Box('g' , blackPiecesRow));
        Knight blackQueenSideKnight = new Knight(COLOR.B , new Box('b' , blackPiecesRow));

        //Black bishop
        Bishop blackKingSideBishop = new Bishop(COLOR.B , new Box('f' , blackPiecesRow));
        Bishop blackQueenSideBishop = new Bishop(COLOR.B , new Box('c' , blackPiecesRow));

        //Black Queen
        Queen blackQueen = new Queen(COLOR.B ,new Box( 'd' , blackPiecesRow));

        //Black King
        King blackKing = new King(COLOR.B , new Box('e' , blackPiecesRow) , blackKingSideRook , blackQueenSideRook);
        
        //put white pieces into the Hash map.
        board.put(Wa.getLocation() , Wa);
        board.put(Wb.getLocation() , Wb);
        board.put(Wc.getLocation() , Wc);
        board.put(Wd.getLocation() , Wd);
        board.put(We.getLocation() , We);
        board.put(Wf.getLocation() , Wf);
        board.put(Wg.getLocation() , Wg);
        board.put(Wh.getLocation() , Wh);
        board.put(whiteKingSideRook.getLocation() , whiteKingSideRook);
        board.put(whiteKingSideKnight.getLocation() , whiteKingSideKnight);
        board.put(whiteKingSideBishop.getLocation() , whiteKingSideBishop);
        board.put(whiteQueenSideRook.getLocation() , whiteQueenSideRook);
        board.put(whiteQueenSideKnight.getLocation() , whiteQueenSideKnight);
        board.put(whiteQueenSideBishop.getLocation() , whiteQueenSideBishop);
        board.put(whiteKing.getLocation() , whiteKing);
        board.put(whiteQueen.getLocation() , whiteQueen);


        //put Black pieces into the Hash map.
        board.put(Ba.getLocation() , Ba);
        board.put(Bb.getLocation() , Bb);
        board.put(Bc.getLocation() , Bc);
        board.put(Bd.getLocation() , Bd);
        board.put(Be.getLocation() , Be);
        board.put(Bf.getLocation() , Bf);
        board.put(Bg.getLocation() , Bg);
        board.put(Bh.getLocation() , Bh);
        board.put(blackKingSideRook.getLocation() , blackKingSideRook);
        board.put(blackKingSideKnight.getLocation() , blackKingSideKnight);
        board.put(blackKingSideBishop.getLocation() , blackKingSideBishop);
        board.put(blackQueenSideRook.getLocation() , blackQueenSideRook);
        board.put(blackQueenSideKnight.getLocation() , blackQueenSideKnight);
        board.put(blackQueenSideBishop.getLocation() , blackQueenSideBishop);
        board.put(blackKing.getLocation() , blackKing);
        board.put(blackQueen.getLocation() , blackQueen);
        
        return board;
    }

    //____________________________________________________board tests____________________________________________________

    public static HashMap<Box , Piece> sanBoard(){
        HashMap<Box , Piece> board = new HashMap<>();

        int blackPawnRow = 7;
        int blackPiecesRow = 8;
        int whitePawnRow = 2;
        int whitePiecesRow = 1;

        //white pawns.
        Pawn Wa = new Pawn(COLOR.W , new Box('a' , whitePawnRow));
        Pawn Wb = new Pawn(COLOR.W , new Box('b' , whitePawnRow));
        Pawn Wc = new Pawn(COLOR.W , new Box('c' , whitePawnRow));
        Pawn Wd = new Pawn(COLOR.W , new Box('d' , whitePawnRow));
        Pawn We = new Pawn(COLOR.W , new Box('e' , 4));
        Pawn Wf = new Pawn(COLOR.W , new Box('f' , whitePawnRow));
        Pawn Wg = new Pawn(COLOR.W , new Box('g' , whitePawnRow));
        Pawn Wh = new Pawn(COLOR.W , new Box('h' , whitePawnRow));

        //white Rooks
        Rook whiteKingSideRook = new Rook(COLOR.W , new Box('h' , whitePiecesRow));
        Rook whiteQueenSideRook = new Rook(COLOR.W , new Box('a' , whitePiecesRow));

        //white Knight
        Knight whiteKingSideKnight = new Knight(COLOR.W , new Box('g' , whitePiecesRow));
        Knight whiteQueenSideKnight = new Knight(COLOR.W , new Box('b' , whitePiecesRow));

        //white bishop
        Bishop whiteKingSideBishop = new Bishop(COLOR.W , new Box('c' , 4));
        Bishop whiteQueenSideBishop = new Bishop(COLOR.W , new Box('c' , whitePiecesRow));

        //white Queen
        Queen whiteQueen = new Queen(COLOR.W ,new Box( 'f' , 3));

        //white King
        King whiteKing = new King(COLOR.W , new Box('e' , whitePiecesRow) , whiteKingSideRook , whiteQueenSideRook);

        //Black pawns.
        Pawn Ba = new Pawn(COLOR.B , new Box('a' , 6));
        Pawn Bb = new Pawn(COLOR.B , new Box('b' , blackPawnRow));
        Pawn Bc = new Pawn(COLOR.B , new Box('c' , blackPawnRow));
        Pawn Bd = new Pawn(COLOR.B , new Box('d' , blackPawnRow));
        Pawn Be = new Pawn(COLOR.B , new Box('e' , 5));
        Pawn Bf = new Pawn(COLOR.B , new Box('f' , blackPawnRow));
        Pawn Bg = new Pawn(COLOR.B , new Box('g' , blackPawnRow));
        Pawn Bh = new Pawn(COLOR.B , new Box('h' , 6));

        //Black Rooks
        Rook blackKingSideRook = new Rook(COLOR.B , new Box('h' , blackPiecesRow));
        Rook blackQueenSideRook = new Rook(COLOR.B , new Box('a' , blackPiecesRow));

        //Black Knight
        Knight blackKingSideKnight = new Knight(COLOR.B , new Box('g' , blackPiecesRow));
        Knight blackQueenSideKnight = new Knight(COLOR.B , new Box('b' , blackPiecesRow));

        //Black bishop
        Bishop blackKingSideBishop = new Bishop(COLOR.B , new Box('f' , blackPiecesRow));
        Bishop blackQueenSideBishop = new Bishop(COLOR.B , new Box('c' , blackPiecesRow));

        //Black Queen
        Queen blackQueen = new Queen(COLOR.B ,new Box( 'd' , blackPiecesRow));

        //Black King
        King blackKing = new King(COLOR.B , new Box('e' , blackPiecesRow) , blackKingSideRook , blackQueenSideRook);

        //put white pieces into the Hash map.
        board.put(Wa.getLocation() , Wa);
        board.put(Wb.getLocation() , Wb);
        board.put(Wc.getLocation() , Wc);
        board.put(Wd.getLocation() , Wd);
        board.put(We.getLocation() , We);
        board.put(Wf.getLocation() , Wf);
        board.put(Wg.getLocation() , Wg);
        board.put(Wh.getLocation() , Wh);
        board.put(whiteKingSideRook.getLocation() , whiteKingSideRook);
        board.put(whiteKingSideKnight.getLocation() , whiteKingSideKnight);
        board.put(whiteKingSideBishop.getLocation() , whiteKingSideBishop);
        board.put(whiteQueenSideRook.getLocation() , whiteQueenSideRook);
        board.put(whiteQueenSideKnight.getLocation() , whiteQueenSideKnight);
        board.put(whiteQueenSideBishop.getLocation() , whiteQueenSideBishop);
        board.put(whiteKing.getLocation() , whiteKing);
        board.put(whiteQueen.getLocation() , whiteQueen);


        //put Black pieces into the Hash map.
        board.put(Ba.getLocation() , Ba);
        board.put(Bb.getLocation() , Bb);
        board.put(Bc.getLocation() , Bc);
        board.put(Bd.getLocation() , Bd);
        board.put(Be.getLocation() , Be);
        board.put(Bf.getLocation() , Bf);
        board.put(Bg.getLocation() , Bg);
        board.put(Bh.getLocation() , Bh);
        board.put(blackKingSideRook.getLocation() , blackKingSideRook);
        board.put(blackKingSideKnight.getLocation() , blackKingSideKnight);
        board.put(blackKingSideBishop.getLocation() , blackKingSideBishop);
        board.put(blackQueenSideRook.getLocation() , blackQueenSideRook);
        board.put(blackQueenSideKnight.getLocation() , blackQueenSideKnight);
        board.put(blackQueenSideBishop.getLocation() , blackQueenSideBishop);
        board.put(blackKing.getLocation() , blackKing);
        board.put(blackQueen.getLocation() , blackQueen);

        return board;
    }
    public static HashMap<Box , Piece> getCheckBoard(){

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
        Queen whiteQueen = new Queen(COLOR.W ,new Box( 'f' , 3));
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
    public static HashMap<Box , Piece> getStalemateBoard(){

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

        //put white pieces into the Hash map.
        board.put(whiteQueenSideRook.getLocation() , whiteQueenSideRook);
        board.put(whiteKing.getLocation() , whiteKing);
        board.put(whiteQueen.getLocation() , whiteQueen);

        //put Black pieces into the Hash map.
        board.put(blackKing.getLocation() , blackKing);

        return board;
    }
    public static HashMap<Box , Piece> getDrawBoard(){

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


        Bishop blackBishop = new Bishop(COLOR.B , new Box('g' , blackPiecesRow));
        Pawn blackPawn = new Pawn(COLOR.B , new Box('h' , 7));
        Pawn blackPawn2 = new Pawn(COLOR.B , new Box('g' , 7));

        board.put(blackBishop.getLocation() , blackBishop);
        board.put(blackPawn.getLocation() , blackPawn);
        //put white pieces into the Hash map.
        board.put(whiteKing.getLocation() , whiteKing);



        //put Black pieces into the Hash map.
        board.put(blackKing.getLocation() , blackKing);

        return board;
    }
    public static HashMap<Box , Piece> getCastleBoard(){

        HashMap<Box , Piece> board = new HashMap<>();

        int blackPawnRow = 7;
        int blackPiecesRow = 8;
        int whitePawnRow = 2;
        int whitePiecesRow = 1;

        //white Rooks
        Rook whiteKingSideRook = new Rook(COLOR.W , new Box('h' , whitePiecesRow));
        Rook whiteQueenSideRook = new Rook(COLOR.W , new Box('a' , whitePiecesRow));

        King whiteKing = new King(COLOR.W , new Box('e' , whitePiecesRow) , whiteKingSideRook , whiteQueenSideRook);

        //Black Rooks
        Rook blackKingSideRook = new Rook(COLOR.B , new Box('h' , blackPiecesRow));
        Rook blackQueenSideRook = new Rook(COLOR.B , new Box('a' , blackPiecesRow));
        //Black King
        King blackKing = new King(COLOR.B , new Box('e' , blackPiecesRow) , blackKingSideRook , blackQueenSideRook);
        Queen blackQueen = new Queen(COLOR.B , new Box('b' , 8));

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

        //_White Pawns_____________________________________
        Pawn pawnWa = new Pawn(COLOR.W,new Box('h',2));
        Pawn pawnWb = new Pawn(COLOR.W,new Box('g',4));
        Pawn pawnWd=new Pawn(COLOR.W,new Box('d',2));
        Pawn pawnWe=new Pawn(COLOR.W,new Box('c',2));


        //White Rooks_______________________________________

        Rook rookWKing = new Rook(COLOR.W,new Box('f',whitePiecesRow));
        Rook rookWQueen = new Rook(COLOR.W,new Box('d',whitePiecesRow));

        //White Knights_______________________________________

//        Knight knightWKing = new Knight(COLOR.W,new Box('g',whitePiecesRow));

        //White Bishops_______________________________________

        Bishop bishopWKing = new Bishop(COLOR.W,new Box('e',5));
        Bishop bishopWQueen = new Bishop(COLOR.W,new Box('b',5));

        //White Queen_______________________________________

        Queen queenW = new Queen(COLOR.W,new Box('f',4));

        //White King_______________________________________

        King kingW = new King(COLOR.W,new Box('a',whitePiecesRow),rookWKing,rookWQueen);

        //_Black Pawns_____________________________________

        Pawn pawnBa = new Pawn(COLOR.B,new Box('a',2));
        Pawn pawnBb = new Pawn(COLOR.B,new Box('b',7));
        Pawn pawnBc =new Pawn(COLOR.B,new Box('c',6));
        Pawn pawnBd=new Pawn(COLOR.B,new Box('e',6));
        Pawn pawnBh=new Pawn(COLOR.B,new Box('h',6));
        Pawn pawnBt=new Pawn(COLOR.B,new Box('f',7));

        //Black Rooks_______________________________________

        Rook rookBKing = new Rook(COLOR.B,new Box('h',blackPiecesRow));
        Rook rookBQueen = new Rook(COLOR.B,new Box('a',blackPiecesRow));

        //Black Knights_______________________________________

//        Knight knightBKing = new Knight(COLOR.B,new Box('c',6));


        //Black Bishops_______________________________________

        Bishop bishopBKing = new Bishop(COLOR.B,new Box('a',3));
        Bishop bishopBQueen = new Bishop(COLOR.B,new Box('d',7));

        //Black Queen_______________________________________

        Queen queenB = new Queen(COLOR.B,new Box('g',2));

        //Black King_______________________________________

        King kingB = new King(COLOR.B,new Box('e',blackPiecesRow),rookBKing,rookBQueen);

        //_put white pieces into the board_____________________

        board.put(pawnWa.getLocation(),pawnWa);
        board.put(pawnWb.getLocation(),pawnWb);
//        board.put(pawnWc.getLocation(),pawnWc);
        board.put(pawnWd.getLocation(),pawnWd);
        board.put(pawnWe.getLocation(),pawnWe);



        board.put(rookWKing.getLocation(),rookWKing);
        board.put(rookWQueen.getLocation(),rookWQueen);

//        board.put(knightWKing.getLocation(),knightWKing);


        board.put(bishopWKing.getLocation(),bishopWKing);
        board.put(bishopWQueen.getLocation(),bishopWQueen);

        board.put(queenW.getLocation(),queenW);

        board.put(kingW.getLocation(),kingW);

        //_put black pieces into the board_____________________

        board.put(pawnBa.getLocation(),pawnBa);
        board.put(pawnBb.getLocation(),pawnBb);
        board.put(pawnBc.getLocation(),pawnBc);
        board.put(pawnBd.getLocation(),pawnBd);
        board.put(pawnBh.getLocation(),pawnBh);
        board.put(pawnBt.getLocation(),pawnBt);

//        board.put(rookBKing.getLocation(),rookBKing);
        board.put(rookBQueen.getLocation(),rookBQueen);

//        board.put(knightBKing.getLocation(),knightBKing);


        board.put(bishopBKing.getLocation(),bishopBKing);
        board.put(bishopBQueen.getLocation(),bishopBQueen);

        board.put(queenB.getLocation(),queenB);

        board.put(kingB.getLocation(),kingB);

        return board;
    }

    //____________________________________________________display methods____________________________________________________
    private static String printAtoH(){
        StringBuilder sb = new StringBuilder();
        sb.append("    ");
        for(char j = BOARD_LIMITS.FIRST_COLL.getColl();j <= BOARD_LIMITS.LAST_CALL.getColl() ;j++){
            sb.append(j).append("    ");
        }
        return sb.toString();
    }
    private static String printSeparator(){
        StringBuilder sb = new StringBuilder();
        sb.append("  |");
        for(int i = BOARD_LIMITS.FIRST_ROW.getRow() ; i <=  BOARD_LIMITS.LAST_ROW.getRow() ; i++){
            sb.append("----|");
        }
        return sb.toString();
    }
    public static void displayBoard(Pieces board){
        StringBuilder sb = new StringBuilder();
        sb.append(printAtoH()).append("\n").append(printSeparator()).append("\n");
        for(int i = BOARD_LIMITS.LAST_ROW.getRow() ; i >=  BOARD_LIMITS.FIRST_ROW.getRow() ; i--){
            sb.append(i).append(" |");
            for(char j = BOARD_LIMITS.FIRST_COLL.getColl();j <= BOARD_LIMITS.LAST_CALL.getColl() ;j++){
                Box current = new Box(j,i);
                if(board.getBoard().containsKey(current)){
                    sb.append(" ");
                    sb.append(board.getPiece(current).getPieceType().toString());
                    sb.append(board.getPiece(current).getColor().toString()).append(" |");
                }
                else {
                    sb.append("    |");
                }
            }
            sb.append(" ").append(i).append("\n").append(printSeparator()).append("\n");
        }
        sb.append(printAtoH());
        System.out.println(sb);
    }



}
