package CSC370_HW1;

public class Board {
    public int[][] tiles;
    private int numMisplaced;
    private int taxicab;
    private int NUM_SHUFFLE_STEPS;

    
    public Board() {
        int [][] tiles = new int[3][3];
        for (int i =0; i<3; i++) {
            for (int j=0; j<3; j++) {
                tiles[i][j] = i*3 + j; 
            }
        }

        this.tiles = tiles;
    }

    public Board(int[][] tiles) {
        this.tiles = tiles;
    }

    // TO IMPLEMENT
    
    //public int total_displaced();
    //public int total_taxicab(); 
    //public ArrayList<Board> getNeighbors();
    //public void takeStep(ArrayList<Board> neighbors);
    //public void shuffle(); -- randomize numbers in the puzzle in a solvable way

    // Question: all possible goal states?
     

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
