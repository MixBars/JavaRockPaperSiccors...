package game;

public class Rules {

    static boolean[][] data;

    public Rules(String[] options){
        data = new boolean[options.length][options.length];
        for (int i=0;i<data.length;i++){
            int j = i-1;
            int counter = (options.length-1)/2;
            while (counter>0) {
                data[i][(j+data.length)%data.length] = true;
                j--;
                counter--;
            }
        }

    }
    public static boolean CheckWin(int player, int computer){
        return data[player][computer];
    }
}
