import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ReadInput {
    public static Map<Integer, Node> read(String filename) throws FileNotFoundException {
        //Read input from file
        FileInputStream fs = new FileInputStream(filename);
        Scanner scanner = new Scanner(fs);

        //first line of file is number of rows number of cols
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();

        //create the maze board
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();

        //one line in file corresponds to one ArrayList<Integer> row
        // add to each row as an arraylist to board[element]
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                //if line.charAt(i) is not a space (probably is an int) add it to row
                if (line.charAt(i) != ' ') {
                    row.add(line.charAt(i) - '0');
                }
            }
            //add entire row to board
            board.add(row);
        }

        return createAdjList(board, rows, cols);
    }

    public static Map<Integer, Node> createAdjList(ArrayList<ArrayList<Integer>> board, int rows, int cols) {
        //create an adjacency list from board
        Map<Integer, Node> adjList = new HashMap<>();

        //for each integer on the board
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                //flatten  node number so each vertex has a unique nodeNumber between 0 to (rows*cols-1)
                int nodeNumber = r * cols + c;

                //get the number of jumps we can make from here
                int availableJumps = board.get(r).get(c);

                //create the node
                Node current = new Node(nodeNumber, availableJumps, r, c, new ArrayList<>(), null);

                //put node into the map's value at map's key which corresponds to the current nodeNumber
                adjList.put(nodeNumber, current);
            }
        }

        //here we are adding the reachable nodes into node.reachableNodes for each node
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                //flatten  node number so each vertex has a unique nodeNumber between 0 to (rows*cols-1)
                int nodeNumber = r * cols + c;

                //get the number of jumps we can make from here
                Node current = adjList.get(nodeNumber);
                int availableJumps = current.value;

                //if we can move availableJumps down from current node and still be in the board,
                //add it to the arraylist of reachableNodes of this current node
                if (r + availableJumps < rows) {
                    int neighborNumber = (r + availableJumps) * cols + c;
                    current.addReachableNode(adjList.get(neighborNumber));
                }

                //if we can move availableJumps up from current node and still be in the board,
                //add it to the arraylist of reachableNodes of this current node
                if (r - availableJumps >= 0) {
                    int neighborNumber = (r - availableJumps) * cols + c;
                    current.addReachableNode(adjList.get(neighborNumber));
                }

                //if we can move availableJumps right from current node and still be in the board,
                //add it to the arraylist of reachableNodes of this current node
                if (c + availableJumps < cols) {
                    int neighborNumber = r * cols + (c + availableJumps);
                    current.addReachableNode(adjList.get(neighborNumber));
                }

                //if we can move availableJumps left from current node and still be in the board,
                //add it to the arraylist of reachableNodes of this current node
                if (c - availableJumps >= 0) {
                    int neighborNumber = r * cols + (c - availableJumps);
                    current.addReachableNode(adjList.get(neighborNumber));
                }
            }
        }
        return adjList;
    }
}