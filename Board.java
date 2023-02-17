package CSC370_HW1;

import java.util.ArrayList;

/** Implementation of A* algorithm to solve 8-puzzle
 * 
 * Total time spent: 
 * 
 * @author DuBose Tuller
 * @author Shahin Ahmadi 
 */


public class Board implements Comparable<Board>{
    private int SIZE = 3;
    private int[][] tiles;
    public int distance = 0;
    public Integer priority = Integer.MAX_VALUE; 
    private int last = -1;
    
    /*
     * Left: 0
     * Right: 1
     * Above: 2
     * Below: 3
     */

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
        this.distance = b.distance;
    }

   /** Finds the empty the tile
     * 
     * @return the indices of the empty tile
     */
    public int[] getEmptyTile() {
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                if (this.tiles[i][j] == 0) {
                    int[] toReturn = {j,i}; // (x,y) coordinates of the blank tile
                    return toReturn;
                }
            }
        }

        return null;
    }

    /** Finds the neighbors of a given tile
      *
      * There are four possibilities for neighbors:
      * above, below, left, and right. We check for
      * all of them individually and add their 
      * coordinates to a list if we're in bounds.
      * 
      * @param x the row
      * @param y the column
      * @return a list containing all the neighbors
      */ 
    public ArrayList<Board> getNeighbors(int[] coords) {
        ArrayList<Board> neighbors = new ArrayList<Board>();
        int x = coords[0];
        int y = coords[1];

        //left
        if (last != 1 && 0 <= (x-1) && (x-1) <= 2 && 0 <= (y) && (y) <= 2) {
            Board left = new Board(this);
            left.swap(x, y, x-1, y);
            left.last = 0;
            neighbors.add(left);
        }
        
        //right
        if (last != 0 && 0 <= (x+1) && (x+1) <= 2 && 0 <= (y) && (y) <= 2) {
            Board right = new Board(this);
            right.swap(x, y, x+1, y);
            right.last = 1;
            neighbors.add(right);
        }

        //above
        if (last != 3 && 0 <= (x) && (x) <= 2 && 0 <= (y-1) && (y-1) <= 2) {
            Board above = new Board(this);
            above.swap(x, y, x, y-1);
            above.last = 2;
            neighbors.add(above);
        }

        //below
        if (last != 2 && 0 <= (x) && (x) <= 2 && 0 <= (y+1) && (y+1) <= 2) {
            Board below = new Board(this);
            below.swap(x, y, x, y+1);
            below.last = 3;
            neighbors.add(below);
        }

        return neighbors;
    }

    /** Swaps two given tiles
      *
      * @param x1 the row of the first tile
      * @param y1 the column of the first tile
      * @param x2 the row of the second tile
      * @param y2 the column of the second tile
      */ 
    private void swap(int x1, int y1, int x2, int y2) {
        // y-coordinates go first because they select the row
        int temp = this.tiles[y1][x1];
        this.tiles[y1][x1] = this.tiles[y2][x2];
        this.tiles[y2][x2] = temp;
    }

    /** Shuffles the board state by taking random legal steps
      *
      * @param steps number of steps taken in shuffling the board state
      */
    public void shuffle(int steps) {
        Board state = this;

        for (int t=0; t<steps; t++) {
            int[] emptyTile = state.getEmptyTile();
            ArrayList<Board> neighbors = state.getNeighbors(emptyTile);
            int nextNeighbor = (int)(Math.random()*neighbors.size());
            state = neighbors.get(nextNeighbor);
        }

        this.tiles = state.tiles;
    }

    /** Counts how many of the 9 tiles are not in the right spot
      *
      * @return number of misplaced tiles
      */
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

    /** Finds the total taxicab distance of the board state
     *
     * @return total taxicab distance
     */
    public int total_taxicab() {
        int total_taxicab = 0;

        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                int val = this.tiles[i][j];
                if (val == 0) {continue;}

                int expected_i = (int)Math.floor(val/SIZE);
                int expected_j = val % SIZE;
                total_taxicab += Math.abs(i-expected_i);
                total_taxicab += Math.abs(j-expected_j);
            }
        }
        return total_taxicab;
    }

    public int off_checkerboard() {
        int off_checkerboard = 0;

        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                int val = this.tiles[i][j];
                int index = SIZE*i + j;
                if (val == index) {continue;} //in correct spot
                else if ((index - val) % 2 == 0) { // same 'side' of board
                    off_checkerboard += 2;
                } else { // opposite 'side'
                    off_checkerboard += 1;
                }
            }
        }

        return off_checkerboard;
    }

    /** Checks whether the two board states are the same
     *
     * @return false if tiles do not match and true otherwise
     */
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

    /** Creates a string representation of the board state
     *
     * @return string representation of the board state
     */
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

    /** Creates a string representation of the board state
     *
     * @return string representation of the board state
     */
    public int compareTo(Board b) {
        if (this.priority - b.priority != 0) {
            return this.priority - b.priority;
        }

        // tiebreaker: smaller g
        return this.distance - b.distance;
    }
}
