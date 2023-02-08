package CSC370_HW1;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.ArrayList;

public class AStar {
    public int search_with_displacement(Board goal, Board b) {
        Board board = new Board(b);
        int steps = 0;

        HashSet<Board> explored = new HashSet<Board>();
        PriorityQueue<Board> queue = new PriorityQueue<Board>();

        while (!queue.isEmpty()) {
            int[] blank_coords = board.getEmptyTile();
            ArrayList<Board> neighbors = board.getNeighbors(blank_coords);
        }


        return steps;
    }
}
