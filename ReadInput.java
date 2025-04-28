import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class ReadInput {
    public static void main(String[] args) throws FileNotFoundException {
        FileInputStream fs = new FileInputStream("small.txt");
        Scanner scanner = new Scanner(fs);
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        scanner.nextLine();
        ArrayList<ArrayList<Integer>> board = new ArrayList<>();
        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            ArrayList<Integer> row = new ArrayList<>();
            for (int i = 0; i < line.length(); i++) {
                if(line.charAt(i) != ' '){
                    row.add(line.charAt(i) - '0');
                }
            }
            board.add(row);
        }
//        for(ArrayList<Integer> row : board){
//            System.out.println(row);
//        }
        createAdjList(board, rows, cols);

    }

    public static void createAdjList(ArrayList<ArrayList<Integer>> board, int rows, int cols){
        Map<Integer, ArrayList<int[]>> adjList = new HashMap<>();

        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                int node = r * cols + c;
                int availableJumps = board.get(r).get(c);
                adjList.put(node, new ArrayList<>());

                //down
                if(r+availableJumps < rows){
                    int[] availNode = {r+availableJumps, c};
                    adjList.get(node).add(availNode);
                }
                //up
                if(r-availableJumps >= 0){
                    int[] availNode = {r-availableJumps, c};
                    adjList.get(node).add(availNode);
                }
                //right
                if(c+availableJumps < cols){
                    int[] availNode = {r, c+availableJumps};
                    adjList.get(node).add(availNode);
                }
                //left
                if(c-availableJumps >= 0){
                    int[] availNode = {r, c-availableJumps};
                    adjList.get(node).add(availNode);
                }
            }
        }
//        for (Map.Entry<Integer, ArrayList<int[]>> entry : adjList.entrySet()) {
//            int node = entry.getKey();
//            ArrayList<int[]> neighbors = entry.getValue();
//            System.out.print("Node " + node + " -> ");
//            for (int[] n : neighbors) {
//                System.out.print(Arrays.toString(n) + " ");
//            }
//            System.out.println();
//        }
    }
}