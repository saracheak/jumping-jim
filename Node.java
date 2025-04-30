import java.util.ArrayList;

public class Node {
    /**
     * @param nodeNumber the flattened node number between 0-(rows*col-1)
     * @param value how far jim can jump from this node
     * @param row this node's row index
     * @param col this node's col index
     * @param reachableNodes arrayList of other nodes reachable by this node
     */
    int nodeNumber;
    int value;
    int row;
    int col;
    ArrayList<Node> reachableNodes;

    public Node(int nodeNumber, int value, int row, int col, ArrayList<Node> reachableNodes) {
        this.nodeNumber = nodeNumber;
        this.value = value;
        this.row = row;
        this.col = col;
        this.reachableNodes = reachableNodes;
    }
    
    public void addReachableNode(Node n) {
        reachableNodes.add(n);
    }
}
