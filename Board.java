package CSC370_HW1;

import java.util.ArrayList;

/** Implementation of A* algorithm to solve 8-puzzle
 * 
 * Total time spent: 
 * 
 * @author DuBose Tuller
 * @author Shahin Ahmadi 
 */

 // QUESTION: does blank tile count in heuristics?

public class Board {
    private int SIZE = 3;
    private int[][] tiles;
    private int taxicab;
    private int NUM_SHUFFLE_STEPS;

    /** Generates the goal state of the 8-puzzle board
      *
      * Goal state:
      * 0 1 2
      * 3 4 5
      * 6 7 8
      */
    public Board() {
        int [][] tiles = new int[SIZE][SIZE];
        for (int i =0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                tiles[i][j] = i*SIZE + j; 
            }
        }

        this.tiles = tiles;
    }

   /** Constructs a board state with the given tiles
     * 
     * @param tiles a 2D array containing tile values
     */
    public Board(int[][] tiles) {
        this.tiles = tiles.clone();
    }

    /** Constructs a copy of a given board state
      *
      * @param b the given board state
      */ 
    public Board(Board b) {
        int[][] tiles = new int[SIZE][SIZE];

        for (int i=0; i<SIZE; i++) {
            tiles[i] = b.tiles[i].clone();
        }

        this.tiles = tiles;
    }

    // TO IMPLEMENT

    //public int total_taxicab(); 
    //public void shuffle(); -- randomize numbers in the puzzle in a solvable way

   /** Finds the empty the tile
     * 
     * @return the indices of the empty tile
     */
    private int[] getEmptyTile() {
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                if (this.tiles[i][j] == 0) {
                    int[] toReturn = {i,j};  
                    return toReturn;
                }
            }
        }

        return null;
    }

    /**
      * There are four possibilities for neighbors:
      * above, below, left, and right. We check for
      * all of them individually and add their 
      * coordinates to a list if we're in bounds.
      */ 
    public ArrayList<Board> getNeighbors(int x, int y) {
        ArrayList<Board> neighbors = new ArrayList<Board>();

        //left
        if (0 <= (x-1) && (x-1) <= 2 && 0 <= (y) && (y) <= 2) {
            Board left = new Board(this);
            left.swap(x, y, x-1, y);
            neighbors.add(left);
        }
        
        //right
        if (0 <= (x+1) && (x+1) <= 2 && 0 <= (y) && (y) <= 2) {
            Board right = new Board(this);
            right.swap(x, y, x+1, y);
            neighbors.add(right);
        }

        //above
        if (0 <= (x) && (x) <= 2 && 0 <= (y-1) && (y-1) <= 2) {
            Board above = new Board(this);
            above.swap(x, y, x, y-1);
            neighbors.add(above);
        }

        //below
        if (0 <= (x) && (x) <= 2 && 0 <= (y+1) && (y+1) <= 2) {
            Board below = new Board(this);
            below.swap(x, y, x, y+1);
            neighbors.add(below);
        }

        return neighbors;
    }

    // y-coordinates go first because they select the row
    public void swap(int x1, int y1, int x2, int y2) {
        int temp = this.tiles[y1][x1];
        this.tiles[y1][x1] = this.tiles[y2][x2];
        this.tiles[y2][x2] = temp;
    }

    public int total_displaced() {
        int total_displaced = 0;

        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                // Check if the board at this position 
                if (this.tiles[i][j] != SIZE*i + j && this.tiles[i][j] != 0) {
                    total_displaced++;
                }
            }
        }

        return total_displaced;
    }
     
    public int total_taxicab() {
        return 0;
    }

    public boolean equals(Board board) {
        for (int i =0; i<this.tiles.length; i++) {
            for (int j=0; j<this.tiles[i].length; j++) {
                if (board.tiles[i][j] != this.tiles[i][j]) {
                    return false;
                } 
            }
        }

        return true;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (int i =0; i<this.tiles.length; i++) {
            for (int j=0; j<this.tiles[i].length; j++) {
                s.append(this.tiles[i][j]);
            }
            s.append("\n");
        }

        return s.toString();
    }
}
