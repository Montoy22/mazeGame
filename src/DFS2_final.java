import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
//depth first search(iterative) + no optimal(just a solution)
public class DFS2_final {

    int [][] DIRECTIONS= {{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0}};
    int ROWBOUNDS =0;
    int COLBOUNDS=0;
    boolean SOLUTIONEXIST=false;


    public String[][] solveMaze(int[][] maze,int startingRow, int startingCol)//outputs solutions of maze
    {
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
            path.add("["+row + "," + col+"]");

            if(maze[row][col]==3){
                SOLUTIONEXIST=true;
                return Solution(path);
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
        return new String[0][0];
    }

    public String[][] Solution(List<String> path){
        System.out.println(path.get(0));
        System.out.println(Arrays.toString(path.toArray()));
        String [][] solutionsDoubleArr = new String[path.size()][];
        for(int i=0; i<path.size();i++){
            solutionsDoubleArr[i]= new String[2];
            solutionsDoubleArr[i][0] = path.get(i).split(",")[0].replace("[","");
            solutionsDoubleArr[i][1] = path.get(i).split(",")[1].replace("]","");
        }
        System.out.println(Arrays.deepToString(solutionsDoubleArr));
        return solutionsDoubleArr;
    }

}
