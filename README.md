# CS590 Project 3 - "Jumping Jim"

## Installation
1. clone the repo
```
git clone https://github.com/saracheak/jumping-jim.git
```

2. compile and run the program
```
javac *.java

java JumpingJim
```

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

### How did we solve this project? 
Before you write a program to solve this problem, you will first write a report describing (in English
and pseudocode) how you will solve this problem. This report should answer two basic questions:
1. What type of graph would you use to model the problem input (detailed in the Section 3.1),
and how would you construct this graph? (I.e., what do the vertices, edges, etc., correspond
to?) Be specific here; we discussed a number of different types of graphs in class.


2. What algorithm will you use to solve the problem? Be sure to describe not just the general
algorithm you will use, but how you will identify the sequence of moves Jim must take in
order to reach the goal.
