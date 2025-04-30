import java.util.*;

public class BFS {
    //breath first search maze solving algorithm
    int [][] DIRECTIONS= {{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0}};
    int ROWBOUNDS =0;
    int COLBOUNDS=0;
    boolean SOLUTIONEXIST=false;


    public void solveMaze(int[][] maze,int startingRow, int startingCol)//outputs solutions of maze
    {
        ROWBOUNDS = maze.length;//maximum number of row in maze
        COLBOUNDS = maze[0].length;// max number of columns in maze
        boolean[][] visited = new boolean[ROWBOUNDS][COLBOUNDS];// prevents same locations to be visited twice

        Queue<int[]> queue = new LinkedList<>();// for bfs
        Map<String, String> pathMap = new HashMap<>();//path tracking

        //start
        queue.offer(new int []{startingRow, startingCol});
        visited[startingRow][startingCol] = true;// sets initial to
        pathMap.put(startingRow+","+ startingCol,null);//for path tracking

        //iteration
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            if(maze[row][col]==3){//goal check condition
                SOLUTIONEXIST=true;
                System.out.println("Goal reached at " + row + "," + col);
                printPath(pathMap, startingRow, startingCol, row, col);
                return;
            }

            for (int[] vector : DIRECTIONS) {//checks surrounding
                int newRow = row + vector[0];
                int newCol = col + vector[1];
                if (newRow >= 0 && newCol >= 0 && newRow < ROWBOUNDS && newCol < COLBOUNDS
                        && !visited[newRow][newCol] && maze[newRow][newCol] != 1) {

                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;

                    //track the path
                    pathMap.put(newRow +","+newCol,row + "," + col);

                    //FOR DEBBUGING
                    //System.out.println("Tracking path: " + newRow + "," + newCol + " -> " + row + "," + col);
                }
            }
        }

        if(!SOLUTIONEXIST){
            System.out.println("no solution exists");
        }

    }
    private static void printPath(Map<String, String> pathMap, int startRow, int startCol, int goalRow, int goalCol) {
        List<String> path = new LinkedList<>();
        String current = goalRow + "," + goalCol;

        while (current != null) {
            path.add(0, current); // Add to the front
            current = pathMap.get(current);
        }
        if(!path.get(0).equals(startRow+","+startCol)){
            System.out.println("ERROR wrong starting cell");
        }
        System.out.println("Path: " + String.join(" -> ", path));


    }
}
