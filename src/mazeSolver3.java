import java.util.*;

//depth first search(iterative) + no optimal(just a solution)
public class mazeSolver3 {

    int [][] DIRECTIONS= {{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0}};
    int ROWBOUNDS =0;
    int COLBOUNDS=0;
    boolean SOLUTIONEXIST=false;
    ArrayList<String> SOLUTION = new ArrayList<>();


    public void solveMaze(int[][] maze,int startingRow, int startingCol)//outputs solutions of maze
    {
        List<String> list = new ArrayList<>();
        ROWBOUNDS = maze.length;
        COLBOUNDS = maze[0].length;
        boolean[][] visited = new boolean[ROWBOUNDS][COLBOUNDS];
        Stack<Object> stack = new Stack<>();
        //start
       stack.push(Arrays.asList(startingRow, startingCol, new ArrayList<String>()));//arraylist for path tracking
       //iteration
       while(!stack.isEmpty()){
           List<Object> curr = (List<Object>) stack.pop();
           int row = (int)curr.get(0);
           int col = (int)curr.get(1);
           List<String> path = (List<String>) curr.get(2);

            // cell are visited
           visited[row][col] = true;

            //update path
           path.add("(" + row + "," + col + ")");

           if(maze[row][col]==3){
               SOLUTIONEXIST=true;
               System.out.println("Goal reached at " + row + ", " + col);
               System.out.println("Path: " + path);
               return;
           }

           for (int[] vector : DIRECTIONS) {
               int newRow = row + vector[0];
               int newCol = col + vector[1];
               if (newRow >= 0 && newCol >= 0 && newRow < ROWBOUNDS && newCol < COLBOUNDS
                       && !visited[newRow][newCol] && maze[newRow][newCol] != 1) {

                   stack.push(Arrays.asList(newRow, newCol, new ArrayList<>(path)));
               }
           }
       }

        if(!SOLUTIONEXIST){
            System.out.println("no solution exists");
        }

    }


}
