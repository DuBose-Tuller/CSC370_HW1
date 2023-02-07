package CSC370_HW1;

import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        int [][] tiles = new int[3][3];
        tiles[0][0] = 1;    
        tiles[0][1] = 0;
        tiles[0][2] = 2;
        tiles[1][0] = 3;
        tiles[1][1] = 4;
        tiles[1][2] = 5;
        tiles[2][0] = 6;
        tiles[2][1] = 7;
        tiles[2][2] = 8;


        Board board = new Board(tiles);

        // System.out.println(board.toString());
        // ArrayList<Board> n = board.getNeighbors(0, 0);
        // for (Board b: n) {
        //     System.out.println(b.toString());
        // }

        Board b2 = new Board();
        b2.shuffle(100);
        System.out.println(b2);
    }

}