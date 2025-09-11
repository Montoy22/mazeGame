import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class BFS_final {
    //breath first search maze solving algorithm
    int [][] DIRECTIONS= {{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0}};
    static int ROWBOUNDS =0;
    static int COLBOUNDS=0;
    static int index=0;
    boolean SOLUTIONEXIST=false;
    String [][] stepByStep;
    static mazeUI mazeui;


    public String[][] solveMaze(int[][] maze, int startingRow, int startingCol, boolean step)//outputs solutions of maze
    {
        ROWBOUNDS = maze.length;//maximum number of row in maze
        COLBOUNDS = maze[0].length;// max number of columns in maze
        boolean[][] visited = new boolean[ROWBOUNDS][COLBOUNDS];// prevents same locations to be visited twice
        Queue<int[]> queue = new LinkedList<>();// for bfs
        Map<String, String> pathMap = new HashMap<>();//path tracking
        stepByStep= new String[ROWBOUNDS*COLBOUNDS][];

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
                if (step) {
                    return stepByStep;
                }

                return printPath(pathMap, startingRow, startingCol, row, col);
            }

            for (int[] vector : DIRECTIONS) {//checks surrounding
                int newRow = row + vector[0];
                int newCol = col + vector[1];
                if (newRow >= 0 && newCol >= 0 && newRow < ROWBOUNDS && newCol < COLBOUNDS
                        && !visited[newRow][newCol] && maze[newRow][newCol] != 1) {

                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                    stepByStep[index]=new String[2];
                    stepByStep[index][0]=Integer.toString(newRow);
                    stepByStep[index][1]=Integer.toString(newCol);
                    index++;
                    //track the path
                    pathMap.put(newRow +","+newCol,row + "," + col);

                }
            }
        }

        if(!SOLUTIONEXIST){
            System.out.println("no solution exists");
        }
        return new String[0][0];
    }
    private String[][] printPath(Map<String, String> pathMap, int startRow, int startCol, int goalRow, int goalCol) {
        List<String> path = new LinkedList<>();
        String current = goalRow + "," + goalCol;

        while (current != null) {
            path.add(0, current); // Add to the front
            current = pathMap.get(current);
        }
        if(!path.get(0).equals(startRow+","+startCol)){
            System.out.println("ERROR wrong starting cell");
        }
        String[][] solutionsDoubleArr=new String[path.size()][];
        for(int i=0; i<path.size();i+=1){
            solutionsDoubleArr[i] = new String[2];
            solutionsDoubleArr[i]=(path.get(i).split(","));
        }
        System.out.println(Arrays.deepToString(solutionsDoubleArr));
        //
       return solutionsDoubleArr;
    }

}
