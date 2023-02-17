package CSC370_HW1;
import java.util.HashSet;
import java.util.PriorityQueue;

import javax.imageio.plugins.tiff.ExifParentTIFFTagSet;

import java.util.ArrayList;


public class AStar {

    /*
     * 
     */

    public int[] search(Board goal, Board b, String heuristic) {
        Board board = new Board(b);
        int steps = 0;

        HashSet<Board> explored = new HashSet<Board>();
        PriorityQueue<Board> frontier = new PriorityQueue<Board>();

        // Initialize frontier
        steps += enqueue_neighbors(board, frontier, explored, heuristic);

        while (!frontier.isEmpty()) {
            Board cur_state = frontier.poll();
            if (cur_state.equals(goal)){
                int[] solution = {cur_state.distance, steps};
                return solution;
            }
            steps += enqueue_neighbors(cur_state, frontier, explored, heuristic);
        }


        return null; // failure
    }


    /*
     * @return steps: the number of neighbors addeed to the queue, which determines the search cost
     */
    private int enqueue_neighbors(Board start, PriorityQueue<Board> frontier, HashSet<Board> explored, String heuristic) {
        int addedToQueue = 0;
        int[] blank_location = start.getEmptyTile();
        ArrayList<Board> neighbors = start.getNeighbors(blank_location);
        for (Board neighbor: neighbors) {
            if (explored.contains(neighbor)) {continue;} // skip if already seen
            neighbor.distance++; // set g(state)
            int h = calculateHeuristic(neighbor, heuristic); // get h(state)
            neighbor.priority = neighbor.distance + h;
            frontier.add(neighbor);
            addedToQueue++;
        }

        return addedToQueue;
    }

    int calculateHeuristic(Board b, String heuristic) {
        if (heuristic.equalsIgnoreCase("taxicab")) {
            return b.total_taxicab();
        }

        if (heuristic.equalsIgnoreCase("displacement")) {
            return b.total_displaced();
        }

        if (heuristic.equalsIgnoreCase("checkerboard")) {
            return b.off_checkerboard();
        }   

        return -1;
    }
}
