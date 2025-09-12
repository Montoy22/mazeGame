package maze_making_algo;

import java.util.*;

public class mazeMakerAB {

    static int[][] maze;
    static boolean[][] visited;
    static int rows, cols;
    static final int WALL = 1, PATH = 0;

    static final int[][] directions = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    // Randomly shuffle direction order
    static void shuffleArray(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
    }

    static void generateMaze(int startR, int startC) {
        visited[startR][startC] = true;
        int unvisitedCells = rows * cols - 1; // Exclude the start cell

        // Start a random walk until all cells are visited
        Random rand = new Random();
        int currentR = startR;
        int currentC = startC;

        while (unvisitedCells > 0) {
            int[] dirOrder = {0, 1, 2, 3};
            shuffleArray(dirOrder); // Randomize the direction

            boolean moved = false;
            for (int dir : dirOrder) {
                int nr = currentR + directions[dir][0];
                int nc = currentC + directions[dir][1];

                // Check bounds and if the cell is unvisited
                if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc]) {
                    // Remove wall between current and new cell
                    maze[2 * currentR + 1 + directions[dir][0]][2 * currentC + 1 + directions[dir][1]] = PATH;

                    // Mark the new cell as visited
                    maze[2 * nr + 1][2 * nc + 1] = PATH;
                    visited[nr][nc] = true;

                    currentR = nr;
                    currentC = nc;
                    unvisitedCells--;
                    moved = true;
                    break;
                }
            }

            // If no movement is made, pick a new random cell
            if (!moved) {
                currentR = rand.nextInt(rows);
                currentC = rand.nextInt(cols);
            }
        }
    }

    static int[][] createMaze(int r, int c) {
        rows = r;
        cols = c;
        int mazeRows = 2 * rows + 1;
        int mazeCols = 2 * cols + 1;
        maze = new int[mazeRows][mazeCols];
        visited = new boolean[rows][cols];

        // Initialize maze as all walls
        for (int[] row : maze) Arrays.fill(row, WALL);

        // Pick a random start position
        Random rand = new Random();
        int startR = rand.nextInt(rows);
        int startC = rand.nextInt(cols);

        maze[2 * startR + 1][2 * startC + 1] = PATH;

        // Generate the maze using Aldous-Broder
        generateMaze(startR, startC);

        // Add entry and exit points
        maze[0][1] = PATH;  // entry at top
        maze[mazeRows - 1][mazeCols - 2] = PATH; // exit at bottom

        return maze;
    }

    public int[][] ABMaze(int row, int col){
        int[][] premaze = createMaze(row, col);
        int[][] maze =new int [row][col];
        for(int i =0;i<row;i++){
            for(int j =0;j<col;j++){
                maze[i][j]=premaze[i][j];
            }
        }
        maze[maze.length-1][maze[0].length-2]=3;
        return maze;
    }

}
