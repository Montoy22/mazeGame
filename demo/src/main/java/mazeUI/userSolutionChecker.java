package mazeUI;

import java.util.*;

public class userSolutionChecker {
    int [][] DIRECTIONS= {{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0}};
    int ROWBOUNDS =0;
    int COLBOUNDS=0;
    boolean SOLUTIONISCORRECT =false;


    public boolean answerChecker(int[][] maze1,int startingRow, int startingCol)//checks user solution
    {
        int [][] mazeCopy = maze1;
        ROWBOUNDS = mazeCopy.length;//maximum number of row in maze
        COLBOUNDS = mazeCopy[0].length;// max number of columns in maze
        boolean[][] visited = new boolean[ROWBOUNDS][COLBOUNDS];// prevents same locations to be visited twice

        Queue<int[]> queue = new LinkedList<>();// for bfs
        //start
        queue.offer(new int []{startingRow, startingCol});
        visited[startingRow][startingCol] = true;// sets initial to

        //iteration
        while(!queue.isEmpty()){
            int[] curr = queue.poll();
            int row = curr[0];
            int col = curr[1];

            if(mazeCopy[row][col]==3){//goal check condition
                SOLUTIONISCORRECT =true;
            }

            for (int[] vector : DIRECTIONS) {//checks surrounding
                int newRow = row + vector[0];
                int newCol = col + vector[1];
                if (newRow >= 0 && newCol >= 0 && newRow < ROWBOUNDS && newCol < COLBOUNDS
                        && !visited[newRow][newCol] && mazeCopy[newRow][newCol] != 1 && mazeCopy[newRow][newCol] != 0) {
                    queue.offer(new int[]{newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }
        return SOLUTIONISCORRECT;
    }

}
