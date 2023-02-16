package CSC370_HW1;

import java.io.FileWriter;
import java.io.IOException;

public class Test {

    public static void main(String[] args) {
        AStar a = new AStar();

        // int [][] tiles = new int[3][3];
        // tiles[0][0] = 0;    
        // tiles[0][1] = 2;
        // tiles[0][2] = 1;
        // tiles[1][0] = 3;
        // tiles[1][1] = 4;
        // tiles[1][2] = 5;
        // tiles[2][0] = 6;
        // tiles[2][1] = 7;
        // tiles[2][2] = 8;
        // Board board = new Board(tiles);
        // board.shuffle(100);
        // System.out.println(board);
        // int[] solution = a.search(new Board(), board, "taxicab");
        // System.out.print(solution[0] + " " + solution[1]);


        int STEPS = 1000;
        int[] depths = new int[STEPS];
        int[] costs = new int[STEPS];
        for (int i=0; i<STEPS; i++) {
            // progress bar
            if (i % 100 == 0) {
                System.out.println(i);
            }
            Board b = new Board();
            b.shuffle(12);
            int[] solution = a.search(new Board(), b, "displacement");
            depths[i] = solution[0];
            costs[i] = solution[1];
        }
      
        try {
            FileWriter out = new FileWriter("displacement-12.csv");
            for (int i=0; i<STEPS; i++) {
                out.append(depths[i] + "," + costs[i] + "\n");
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}