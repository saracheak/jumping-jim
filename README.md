# CS590 Project 3 - "Jumping Jim"

## Installation
1. clone the repo
```
git clone https://github.com/saracheak/jumping-jim.git
```

2. compile
```
javac *.java
```
## Usage
upload `input.txt` to the jumping-jim project folder

run the program
```
java JumpingJim
```

check output in `"output.txt"`

## Project Description
This problem is based on the “Jumping Jim” maze problem (from “MAD MAZES: Intriguing
Mind Twisters for Puzzle Buffs, Game Nuts and Other Smart People” by Robert Abbott). The
text of the problem is quoted below. A diagram of the maze is provided on the next page.
Jumping Jim is about to begin his grand performance at the circus, but his jealous en-
emy, Dastardly Dan, has restrung all the trampolines. The number on each trampoline
indicates how tightly strung each one is; in other words, the number indicates how far
Jim will have to move (horizontally or vertically, but NOT diagonally) when he bounces
off the trampoline. Jim begins his routine by leaping onto the trampoline at the upper
left. He must get to the Goal at the lower right, where he will take his bow. How can
he get there?

The diagram shows all of the trampolines with each represented as a square. Begin
on the square at the upper left. That square is marked 3. From there you could, for
example, move three squares down to a square marked 2. From there, you might move
two squares right to a square marked 4, and from there you could move four squares
right to another square marked 2. That path, incidentally, won’t get you to the goal.

<img width="516" alt="Screenshot 2025-05-02 at 19 08 32" src="https://github.com/user-attachments/assets/cec291e8-28b1-48d6-80bf-d3c5e9d5610b" />

## Input
The input must begin with two positive integers on a line indicating the number of rows r and columns c of
the maze, respectively. The next r lines detail all of the trampolines in the maze. Each line has c
integers, where each integer represents the number of spaces Jim will move when landing on that
trampoline. The goal square will always be in the lower right of the maze.  

For the original “Jumping Jim” maze, the input is:  
7 7  
3 6 4 3 2 4 3  
2 1 2 3 2 5 2  
2 3 4 3 4 2 3  
2 4 4 3 4 2 2  
4 5 1 3 2 5 4  
4 3 2 2 4 5 6  
2 5 2 5 6 1 0

## Project Discussion
### 1. What type of graph would you use to model the problem input, and how would you construct this graph? (i.e., what do the vertices, edges, etc., correspond to?)  

**Discussing the type of graph:**
   - Jim can only jump to a next trampoline current trampoline's number from current trampoline, no more and no less. Therefore, we may be able to jump from current to next, but not necessarily next back to current. Therefore, this is a directed graph as we cannot freely move bilaterally.  
   - If the number is 1, we have to jump to the next trampoline above, below, left, or right of current trampoline. Therefore, this graph is _generally_ acyclic as we cannot "jump" to the current trampoline from the current trampoline. However, it is possible that we revisit a node via a different path. To prevent this from happening, I used a boolean 1d array, `visited`, mark the nodes we come across as visited, and only move to nodes that are unvisited.
   - Although the number of spaces between current trampoline and next trampoline is equal to the current trampoline's number, (e.g. (row, col) (0,0) to (0, 3)), this is technically 1 "move". Since we are optimizing for the number of moves from start to goal, and each move is worth 1, we use an unweighted graph.  
   - If we assume that all the trampolines are reachable somehow, this graph is connected. If some trampolines are unreachable, this graph is disconnected. For the purposes of this project we are assuming all trampolines are reachable and therefore we use a connected graph.  

Overall, I am using a directed, acyclic, unweighted, connected graph.

**What do the vertices, edges, etc... correspond to?**
   - The vertices correspond to trampoline squares and each vertex is an object of the Node class (refer to the javadoc comments in `Node.java` for explanation of Node fields)
   - The edges connect vertices which Jim is able to jump to, not vertices adjacent to each other in the diagram.
   - For example, start (0,0) is a vertex with degree 2, with 2 directed edges pointed to vertex(4,0) and vertex(0,4).


**How would you construct this graph?**  
    1. Parse `"input.txt"` to a 2d grid with Node objects  
    2. Find the reachable neighbour Nodes of each Node and put into an adjacency list  
    3. Perform bfs  
    4. Get output

### 2. What algorithm will you use to solve the problem? Be sure to describe not just the general algorithm you will use, but how you will identify the sequence of moves Jim must take in order to reach the goal.  

After putting all our Nodes into a `Map<Integer, Node> adjList` adjacency list we have a way to map the flattened 
vertex number (e.g. 0-24 in the diagram) to the Node object which holds information about its value, 
reachable neighbour nodes, row index, column index, and which Node it came from (we need this to keep track of the path). 

From here I used a breadth-first search approach because it gives us the shortest path in an unweighted graph.  

1. declare an empty queue, `queue`, for the bfs, and boolean 1d array, `visited` to keep track of which vertices we have already gone to. 
2. begin bfs by putting start node into `queue` and mark it as visited
3. while the queue isn't empty,   
    • dequeue the node,   
    • check if it's the goal,  
    • if not, add all the reachable vertices to the queue and update their parent field as the current Node
4. reaching the goal means current node = goal node  

Once current node = goal node, we can construct the path  

1. declare 1d `ArrayList<Node> path` and add current Node
2. while current Node's parent field is not NULL (aka current Node is not the start Node), add the parent Node to `path`
3. because we added the path from end -> start, we have to reverse it
4. iterate through consecutive pairs of Nodes, compare their row and column indices to find the direction, and add it to 1d `ArrayList<String> direction`
   - e.g. if Jim moves from (0,0) to (0, 4), direction for this move is east ("E")
5. output direction to `"output.txt"`