import java.util.Queue;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Collections;

public class Algorithm {
    /**
     * bfs method implements the bfs algorithm on the adjacency list, adjList
     * to get the shortest path from startNode to endNode
     * @param adjList a map representation of adjacency list
     */
    public static ArrayList<String> bfs(Map<Integer, Node> adjList) {
        //declare a linked-list implementation of a queue for Nodes
        Queue<Node> queue = new LinkedList<>();

        //visited bool array to keep track of which nodes we have visited
        boolean[] visited = new boolean[adjList.size()];
        
        //parent map so we can keep track of our path
        Map<Node, Node> parent = new HashMap<>();

        //put startNode to queue and mark as visited
        Node startNode = adjList.get(0);
        Node endNode = adjList.get(adjList.size()-1);
        queue.offer(startNode);
        visited[startNode.nodeNumber] = true;

        //bfs
        while (!queue.isEmpty()) {
            //dequeue first Node
            Node current = queue.poll();

            //if we have reached the endNode, break
            if (current.row == endNode.row && current.col == endNode.col) {
                break;
            }

            //if we are not at endNode, we iterate through the reachableNodes from current Node
            for (Node reachableNode : adjList.get(current.nodeNumber).reachableNodes) {
                if (!visited[reachableNode.nodeNumber]) { //for each reachableNode, if we haven't visited it before
                    visited[reachableNode.nodeNumber] = true;   //mark it as visited
                    parent.put(reachableNode, current);         //put it in the parent
                    queue.offer(reachableNode);                 //enqueue reachableNode
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

    /**
     * nodeNumberToDirection converts the nodes from path. path is where we stored each node
     * we visited in order. we only stored each node's nodeNumber, so we need to convert that
     * to its direction (E, W, S, N) in relation to the previous node.
     * @param path ArrayList of Nodes in the order we visited them in
     */
    public static ArrayList<String> nodeNumberToDirection(ArrayList<Node> path){

        //declare an ArrayList of strings directions, where we will store the corresponding E, W, S, N
        ArrayList<String> directions = new ArrayList<>();

        //for each Node in path
        for (int i = 1; i < path.size(); i++) {
            Node prev = path.get(i - 1);
            Node next = path.get(i);

            //if our next Node is on the same row as previous Node, the direction is E or W
            if (next.row == prev.row) {
                //if next Node is to the right of previous Node, direction is E
                if (next.col > prev.col) {
                    directions.add("E");
                }
                //if next Node is to the left of previous Node, direction is W
                else {
                    directions.add("W");
                }
            //if our next Node is on the same column as previous Node, the direction is S or N
            } else if (next.col == prev.col) {
                //if next Node is below the previous Node, direction is S
                if (next.row > prev.row) {
                    directions.add("S");
                }
                //if next Node is above the previous Node, direction is N
                else {
                    directions.add("N");
                }
            }
        }
        return directions;
    }
}
