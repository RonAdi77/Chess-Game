package org.example.chessgui;
public enum COLOR {

    B {
        @Override
        public String toString() {
            return "b";
        }

        public String toFullString() {
            return "Black";
        }
    },

    W{
        @Override
        public String toString() {
            return "w";
        }
        public String toFullString(){
            return "White";
        }
    };

    public static COLOR NOT(COLOR c){
        return c == COLOR.W?COLOR.B:COLOR.W;
    }

    public abstract String toFullString();
}


