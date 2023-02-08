package CSC370_HW1;
import java.util.HashSet;
import java.util.HexFormat;
import java.util.PriorityQueue;
import java.util.ArrayList;

// TODO implement comparable interface in the Board class based on priority

public class AStar {
    public int search(Board goal, Board b, String heuristic) {
        Board board = new Board(b);
        int steps = 0;

        HashSet<Board> explored = new HashSet<Board>();
        PriorityQueue<Board> queue = new PriorityQueue<Board>();

        while (!queue.isEmpty()) {
            enqueue_neighbors(board, queue, heuristic);

        }


        return steps;
    }

    private void enqueue_neighbors(Board b, PriorityQueue<Board> pq, String heuristic) {
        int[] blank_location = b.getEmptyTile();
        ArrayList<Board> neighbors = b.getNeighbors(blank_location);
        for (Board n: neighbors) {
            n.distance++; // set g(state)
            int h = calculateHeuristic(n, heuristic); // get h(state)
            n.priority = n.distance + h;

        }
    }

    int calculateHeuristic(Board b, String heuristic) {
        if (heuristic.equalsIgnoreCase("taxicab")) {
            return b.total_taxicab();
        }

        if (heuristic.equalsIgnoreCase("displacement")) {
            return b.total_displaced();
        }

        return null;
    }
}
