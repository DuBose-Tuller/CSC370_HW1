package CSC370_HW1;

public class Board {
    private int SIZE = 3;
    public int[][] tiles;
    private int taxicab;
    private int NUM_SHUFFLE_STEPS;

    
    public Board() {
        int [][] tiles = new int[SIZE][SIZE];
        for (int i =0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                tiles[i][j] = i*SIZE + j; 
            }
        }

        this.tiles = tiles;
    }

    public Board(int[][] tiles) {
        this.tiles = tiles;
    }

    // TO IMPLEMENT

    //public int total_taxicab(); 
    //public ArrayList<Board> getNeighbors();
    //public void takeStep(ArrayList<Board> neighbors);
    //public void shuffle(); -- randomize numbers in the puzzle in a solvable way

    public int total_displaced() {
        int total_displaced = 0;

        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                // Check if the board at this position 
                if (this.tiles[i][j] != SIZE*i + j) {
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
        s.append("\n");

        return s.toString();
    }
    
    
}
