package maze_making_algo;

import java.util.*;

public class mazeMakerRB {//recursive backtracking

    static int[][] maze;
    static boolean[][] visited;
    static int rows, cols;
    static final int WALL = 1, PATH = 0;

    static final int[][] directions = {
            {-1, 0}, // up
            {1, 0},  // down
            {0, -1}, // left
            {0, 1}   // right
    };

    static void generateMaze(int r, int c) {
        visited[r][c] = true;
        int[] dirOrder = {0, 1, 2, 3};
        shuffleArray(dirOrder); // randomize direction order

        for (int dir : dirOrder) {
            int nr = r + directions[dir][0];
            int nc = c + directions[dir][1];

            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc]) {
                // Remove wall between (r, c) and (nr, nc)
                int wallR = r + nr + 1;
                int wallC = c + nc + 1;
                maze[wallR][wallC] = PATH;

                // Mark new cell as path
                maze[2 * nr + 1][2 * nc + 1] = PATH;

                generateMaze(nr, nc);
            }
        }
    }

    static void shuffleArray(int[] array) {
        Random rand = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
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

        // Mark the starting cell as path
        int startR = 0, startC = 0;
        maze[2 * startR + 1][2 * startC + 1] = PATH;

        generateMaze(startR, startC);

        // Entry and Exit
        maze[0][1] = PATH;  // entry
        maze[mazeRows - 1][mazeCols - 2] = PATH; // exit

        return maze;
    }
    public int[][] rBMaze(int row, int col){
        int[][] m = createMaze(row, col);
        int[][] maze =new int [row][col];
        for(int i =0;i<row;i++){
            for(int j =0;j<col;j++){
                maze[i][j]=m[i][j];
            }
        }
        maze[maze.length-1][maze[0].length-2]=3;
        return maze;
    }
}
