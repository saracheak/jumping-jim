import java.util.Queue;
import java.util.ArrayList;
import java.util.Map;
import java.util.LinkedList;
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
        
        //ArrayList path keeps track of the nodes we visited in order
        ArrayList<Node> path = new ArrayList<>();

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
            if (current.equals(endNode)) {
                break;
            }

            //if we are not at endNode, we iterate through the reachableNodes from current Node
            for (Node reachableNode : adjList.get(current.nodeNumber).reachableNodes) {
                if (!visited[reachableNode.nodeNumber]) { //for each reachableNode, if we haven't visited it before
                    visited[reachableNode.nodeNumber] = true;   //mark it as visited
                    reachableNode.parent = current;
                    queue.offer(reachableNode);                 //enqueue reachableNode
                }
            }
        }

        //now we need to construct the path
        //since we kept track of the parent, we need to construct the path from endNode -> startNode

        //add endNode to path ArrayList
        Node current = endNode;
        path.add(current);

        //while the currentNode has a parent, we add the parent to the path and set currentNode to parent
        while(current.parent != null){
            path.add(current.parent);
            current = current.parent;
        }

        //since we constructed the path from endNode -> startNode we need to reverse it
        Collections.reverse(path);

        //convert path to directions
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
                    directions.add("E ");
                }
                //if next Node is to the left of previous Node, direction is W
                else {
                    directions.add("W ");
                }
            //if our next Node is on the same column as previous Node, the direction is S or N
            } else if (next.col == prev.col) {
                //if next Node is below the previous Node, direction is S
                if (next.row > prev.row) {
                    directions.add("S ");
                }
                //if next Node is above the previous Node, direction is N
                else {
                    directions.add("N ");
                }
            }
        }
        return directions;
    }
}
