public enum COLOR {
    B{
        @Override
        public String toString() {
            return "b";
        }
        public String toFullString(){
            return "Black";
        }
    },
    W{
      @Override
      public String toString(){
          return "w";
      }
      public String toFullString(){
          return "White";
      }
    };
    abstract String toFullString();

    public static COLOR not(COLOR color){
        return color == COLOR.W ? COLOR.B : COLOR.W;
    }
}
