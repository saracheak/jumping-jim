import java.util.Queue;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Collections;

public class Algorithm {
    public static ArrayList<String> bfs(Map<Integer, Node> adjList) {
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visited = new HashSet<>();
        Map<Node, Node> parent = new HashMap<>();

        Node startNode = adjList.get(0);
        Node endNode = adjList.get(adjList.size()-1);
        queue.offer(startNode);
        visited.add(startNode);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            //if we reached the end
            if (current.row == endNode.row && current.col == endNode.col) {
                break;
            }

            for (Node neighbor : adjList.get(current.nodeNumber).reachableNodes) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.offer(neighbor);
                }
            }
        }

        if (!parent.containsKey(endNode)) {
            return new ArrayList<>();
        }

        ArrayList<Node> path = new ArrayList<>();
        Node current = endNode;
        while (current != startNode) {
            path.add(current);
            current = parent.get(current);
        }
        path.add(startNode);
        Collections.reverse(path);

        return nodeNumberToDirection(path);
    }

    public static ArrayList<String> nodeNumberToDirection(ArrayList<Node> path){
        ArrayList<String> directions = new ArrayList<>();
        for (int i = 1; i < path.size(); i++) {
            Node prev = path.get(i - 1);
            Node next = path.get(i);

            if (next.row == prev.row) {
                if (next.col > prev.col) {
                    directions.add("E");
                }
                else {
                    directions.add("W");
                }
            } else if (next.col == prev.col) {
                if (next.row > prev.row) {
                    directions.add("S");
                }
                else {
                    directions.add("N");
                }
            }
        }
        return directions;
    }
}
