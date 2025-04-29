import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;

public class JumpingJim {
    public static void main(String[] args) throws FileNotFoundException {
        Map<Integer, Node> adjList = ReadInput.read("small.txt");
        ArrayList<String> path = Algorithm.bfs(adjList);

        for (String node : path) {
            System.out.print(node + " ");
        }
        //TODO: output to output.txt
        //TODO: comments
    }
}
