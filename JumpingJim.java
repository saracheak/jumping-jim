import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

//TODO: can we assume input is correct

public class JumpingJim {
    public static void main(String[] args) throws IOException {
        //read input and put into adjacency list
        Map<Integer, Node> adjList = ReadInput.read("small.txt");

        //get the path of directions
        ArrayList<String> path = Algorithm.bfs(adjList);

        //write the path of directions to output.txt
        FileOutputStream fs = new FileOutputStream("output.txt");
        for (String direction : path) {
            fs.write(direction.getBytes());
        }
    }
}
