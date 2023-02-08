package CSC370_HW1;
import java.util.HashSet;
import java.util.PriorityQueue;

import javax.imageio.plugins.tiff.ExifParentTIFFTagSet;

import java.util.ArrayList;


public class AStar {
    public int[] search(Board goal, Board b, String heuristic) {
        Board board = new Board(b);
        int steps = 0;

        HashSet<Board> explored = new HashSet<Board>();
        PriorityQueue<Board> frontier = new PriorityQueue<Board>();

        // Initialize frontier
        enqueue_neighbors(board, frontier, explored, heuristic);

        while (!frontier.isEmpty()) {
            Board cur_state = frontier.poll();
            steps++;
            if (cur_state.equals(goal)){
                int[] solution = {cur_state.distance, steps};
                return solution;
            }
            enqueue_neighbors(cur_state, frontier, explored, heuristic);
        }


        return null; // failure
    }


    private void enqueue_neighbors(Board start, PriorityQueue<Board> frontier, HashSet<Board> explored, String heuristic) {
        int[] blank_location = start.getEmptyTile();
        ArrayList<Board> neighbors = start.getNeighbors(blank_location);
        for (Board neighbor: neighbors) {
            if (explored.contains(neighbor)) {continue;} // skip if already seen
            neighbor.distance++; // set g(state)
            int h = calculateHeuristic(neighbor, heuristic); // get h(state)
            neighbor.priority = neighbor.distance + h;
            frontier.add(neighbor);
        }
    }

    int calculateHeuristic(Board b, String heuristic) {
        try {
            if (heuristic.equalsIgnoreCase("taxicab")) {
                return b.total_taxicab();
            }

            if (heuristic.equalsIgnoreCase("displacement")) {
                return b.total_displaced();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1; // make java happy
    }
}
