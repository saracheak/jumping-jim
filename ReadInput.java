import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class ReadInput {
    public static Map<Integer, Node> read(String filename) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream(filename);
        Scanner scanner = new Scanner(fs);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                if (line.charAt(i) != ' ') {
                    row.add(line.charAt(i) - '0');
                }
            }
            board.add(row);
        }
        return createAdjList(board, rows, cols);
    }

    public static Map<Integer, Node> createAdjList(ArrayList<ArrayList<Integer>> board, int rows, int cols) {
        Map<Integer, Node> adjList = new HashMap<>();

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int nodeNumber = r * cols + c;
                int availableJumps = board.get(r).get(c);
                Node current = new Node(nodeNumber, availableJumps, r, c, new ArrayList<>());
                adjList.put(nodeNumber, current);
            }
        }

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int nodeNumber = r * cols + c;
                Node current = adjList.get(nodeNumber);
                int availableJumps = current.value;

                // down
                if (r + availableJumps < rows) {
                    int neighborNumber = (r + availableJumps) * cols + c;
                    current.addReachableNode(adjList.get(neighborNumber));
                }
                // up
                if (r - availableJumps >= 0) {
                    int neighborNumber = (r - availableJumps) * cols + c;
                    current.addReachableNode(adjList.get(neighborNumber));
                }
                // right
                if (c + availableJumps < cols) {
                    int neighborNumber = r * cols + (c + availableJumps);
                    current.addReachableNode(adjList.get(neighborNumber));
                }
                // left
                if (c - availableJumps >= 0) {
                    int neighborNumber = r * cols + (c - availableJumps);
                    current.addReachableNode(adjList.get(neighborNumber));
                }
            }
        }
        return adjList;
    }
}