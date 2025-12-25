# Labyrinth Solver Engine

## Overview
A Java-based maze solving engine that finds a path through a labyrinth
using a **stack-based depth-first search (DFS)** algorithm.

The solver navigates from the **top-left corner** of the maze to the
**bottom-right exit**, backtracking when it reaches dead ends.

## Problem Solved
- The maze is represented as a grid of cells
- Each cell may have walls on the north, south, east, or west sides
- The solver explores valid paths while avoiding revisited cells
- Dead ends are detected and handled through backtracking

## Algorithm
- Uses **Depth-First Search (DFS)**
- Implemented with an explicit **stack**
- Marks visited cells to avoid cycles
- Backtracks when no valid moves remain

## Features
- Solves arbitrarily sized mazes
- Visual representation using Java graphics
- Stack-based backtracking logic
- Clear separation between maze structure and solving logic

## Implementation Details
- Maze stored as a 2D array of `Cell` objects
- Each cell tracks walls and visited state
- DFS continues until the exit is found or no path exists

## How to Run
1. Compile all Java files
2. Run the main `Maze` class
3. Watch the solver explore the maze and find a path to the exit

## Tech Stack
- Java
- Stacks
- Depth-First Search
- Backtracking
- 2D Arrays

## License
MIT
