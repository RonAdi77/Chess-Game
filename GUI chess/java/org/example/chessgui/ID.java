package org.example.chessgui;
public enum ID {
    King{
        @Override
        public String toString() {
            return "K";
        }
        public String toFullString(){
            return "King";
        }
    },
    Queen{
        @Override
        public String toString() {
            return "Q";
        }
        public String toFullString(){
            return "Queen";
        }
    },
    Rook{
        @Override
        public String toString() {
            return "R";
        }
        public String toFullString(){
            return "Rook";
        }
    },
    Knight{
        @Override
        public String toString() {
            return "N";
        }
        public String toFullString(){
            return "Knight";
        }
    },
    Bishop{
        @Override
        public String toString() {
            return "B";
        }
        public String toFullString(){
            return "Bishop";
        }
    },
    Pawn{
        @Override
        public String toString() {
            return "P";
        }
        public String toFullString(){
            return "Pawn";
        }
    };
    public abstract String toFullString();
}
