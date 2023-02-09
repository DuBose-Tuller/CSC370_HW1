package CSC370_HW1;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Test {

    public static void main(String[] args) {
        //int [][] tiles = new int[3][3];
        // tiles[0][0] = 3;    
        // tiles[0][1] = 1;
        // tiles[0][2] = 2;
        // tiles[1][0] = 6;
        // tiles[1][1] = 4;
        // tiles[1][2] = 5;
        // tiles[2][0] = 0;
        // tiles[2][1] = 7;
        // tiles[2][2] = 8;
        //Board board = new Board(tiles);
        // Board board = new Board();
        // board.shuffle(100);
        // System.out.println(board);


        AStar a = new AStar();
        // int[] solution = a.search(new Board(), board, "taxicab");
        // System.out.println("Optimal Distance: " + solution[0]);
        // System.out.println("# Search Steps: " + solution[1]);

        int STEPS = 100;
        int[] depths = new int[STEPS];
        int[] costs = new int[STEPS];
        for (int i=0; i<STEPS; i++) {
            Board b = new Board();
            b.shuffle(100);
            int[] solution = a.search(new Board(), b, "taxicab");
            depths[i] = solution[0];
            costs[i] = solution[1];
        }
      
        try {
            FileWriter out = new FileWriter("CSC370_HW1/taxicab.csv");
            for (int i=0; i<STEPS; i++) {
                out.append(depths[i] + "," + costs[i] + "\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}