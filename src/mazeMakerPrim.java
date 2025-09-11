import java.util.*;

public class mazeMakerPrim {

    static class Wall {
        int wallR, wallC;
        int nextR, nextC;

        Wall(int wallR, int wallC, int nextR, int nextC) {
            this.wallR = wallR;
            this.wallC = wallC;
            this.nextR = nextR;
            this.nextC = nextC;
        }
    }

    static int[][] generateMaze(int rows, int cols) {
        int mazeRows = 2 * rows + 1;
        int mazeCols = 2 * cols + 1;
        int[][] maze = new int[mazeRows][mazeCols];
        for (int[] row : maze) Arrays.fill(row, 1);

        boolean[][] visited = new boolean[rows][cols];
        List<Wall> wallList = new ArrayList<>();

        // Start from random cell
        Random rand = new Random();
        int startR = rand.nextInt(rows);
        int startC = rand.nextInt(cols);

        // Mark the starting cell as path
        maze[2 * startR + 1][2 * startC + 1] = 0;
        visited[startR][startC] = true;

        // Add initial walls of the starting cell
        addWalls(startR, startC, rows, cols, wallList, visited);

        // Process walls
        while (!wallList.isEmpty()) {
            Wall wall = wallList.remove(rand.nextInt(wallList.size()));

            int nr = wall.nextR;
            int nc = wall.nextC;

            if (!visited[nr][nc]) {
                // Remove the wall
                maze[wall.wallR][wall.wallC] = 0;
                maze[2 * nr + 1][2 * nc + 1] = 0;
                visited[nr][nc] = true;

                addWalls(nr, nc, rows, cols, wallList, visited);
            }
        }

        // Add entry and exit
        maze[0][1] = 0;
        maze[mazeRows - 1][mazeCols - 2] = 0;

        return maze;
    }

    static void addWalls(int r, int c, int rows, int cols, List<Wall> wallList, boolean[][] visited) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];
            if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && !visited[nr][nc]) {
                int wallR = r + nr + 1;
                int wallC = c + nc + 1;
                wallList.add(new Wall(wallR, wallC, nr, nc));
            }
        }
    }

    public int[][] primMaze(int row, int col){
        int[][] premaze = generateMaze(row, col);
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
