import javax.swing.*;
import java.awt.*;
import java.util.Stack;

public class MazeGridPanel extends JPanel {
    private int rows;
    private int cols;
    private Cell[][] maze;

    public void generateMazeByDFS() {
        boolean[][] visited = new boolean[rows][cols];
        Stack<Cell> stack = new Stack<>();
        Cell start = maze[0][0];
        visited[start.row][start.col] = true;
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            java.util.List<Cell> neighbours = new java.util.ArrayList<>(); //list to store the unvisited cells
            //checking which cells are empty around current cell
            if (current.row > 0 && !visited[current.row - 1][current.col])
                neighbours.add(maze[current.row - 1][current.col]);
            if (current.row < rows - 1 && !visited[current.row + 1][current.col])
                neighbours.add(maze[current.row + 1][current.col]);
            if (current.col > 0 && !visited[current.row][current.col - 1])
                neighbours.add(maze[current.row][current.col - 1]);
            if (current.col < cols - 1 && !visited[current.row][current.col + 1])
                neighbours.add(maze[current.row][current.col + 1]);
            //going through list
            if (!neighbours.isEmpty()) {

                Cell next = neighbours.get((int) (Math.random() * neighbours.size()));//choosing one random unvisited cell

                //checking which cell it went to and removed walls accordingly
                if (next.row == current.row - 1) { // north
                    current.northWall = false;
                    next.southWall = false;
                } else if (next.row == current.row + 1) { // south
                    current.southWall = false;
                    next.northWall = false;
                } else if (next.col == current.col - 1) { // west
                    current.westWall = false;
                    next.eastWall = false;
                } else if (next.col == current.col + 1) { // east
                    current.eastWall = false;
                    next.westWall = false;
                }
                //changed visited to true of the cell that was visited and pushed it to the stack
                visited[next.row][next.col] = true;
                stack.push(next);

            } else { //if its a deadend popped it to do backtracking
                stack.pop();
            }
        }

        maze[0][0].setBackground(Color.GREEN);
        maze[rows - 1][cols - 1].setBackground(Color.RED);
        repaint();
    }


    public void solveMaze() {
        Stack<Cell> stack = new Stack<Cell>();

        Cell start = maze[0][0];
        start.setBackground(Color.GREEN);
        Cell finish = maze[rows - 1][cols - 1];
        finish.setBackground(Color.RED);
        stack.push(start);
        //changing color so that compiler knows its already visited as its our start position
        start.setBackground(Color.BLUE);
        while (!stack.isEmpty()) {

            Cell current = stack.peek();
            // first condition is checking if finish so that compiler doesn't have to check rest
            if (current.equals(finish)) {
                System.out.println("you have reached the end");
                break;
            } else if (current.row > 0 && !current.northWall && !visited(current.row - 1, current.col)) {
                Cell next = maze[current.row - 1][current.col]; //goes upwards
                stack.push(next);
                next.setBackground(Color.BLUE);

            } else if (current.row < maze.length - 1 && !current.southWall && !visited(current.row + 1, current.col)) {
                Cell next = maze[current.row + 1][current.col];//goes downwards
                stack.push(next);
                next.setBackground(Color.BLUE);

            } else if (current.col < maze[0].length - 1 && !current.eastWall && !visited(current.row, current.col + 1)) {
                Cell next = maze[current.row][current.col + 1];//goes right
                stack.push(next);
                next.setBackground(Color.BLUE);


            } else if (current.col > 0 && !current.westWall && !visited(current.row, current.col - 1)) {
                Cell next = maze[current.row][current.col - 1];//goes left
                stack.push(next);
                next.setBackground(Color.BLUE);

            } else {
                current.setBackground(Color.GRAY);
                stack.pop();
            }
        }
        start.setBackground(Color.GREEN);
        finish.setBackground(Color.RED);
        this.repaint();
    }

    public boolean visited(int row, int col) {
        Cell c = maze[row][col];
        Color status = c.getBackground();
        if (status.equals(Color.WHITE) || status.equals(Color.RED)) {
            return false;
        }
        return true;
    }

    public void generateMaze() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                if (row == 0 && col == 0) {
                    continue;
                } else if (row == 0) {
                    maze[row][col].westWall = false;
                    maze[row][col - 1].eastWall = false;
                } else if (col == 0) {
                    maze[row][col].northWall = false;
                    maze[row - 1][col].southWall = false;
                } else {
                    boolean north = Math.random() < 0.5;
                    if (north) {
                        maze[row][col].northWall = false;
                        maze[row - 1][col].southWall = false;
                    } else {
                        maze[row][col].westWall = false;
                        maze[row][col - 1].eastWall = false;
                    }
                    maze[row][col].repaint();
                }
            }
        }

        this.repaint();
    }

    public MazeGridPanel(int rows, int cols) {
        this.setPreferredSize(new Dimension(800, 800));
        this.rows = rows;
        this.cols = cols;
        this.setLayout(new GridLayout(rows, cols));
        this.maze = new Cell[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                maze[row][col] = new Cell(row, col);
                this.add(maze[row][col]);
            }
        }

        this.generateMazeByDFS();
        this.solveMaze();
    }
}
