package path_finding_algo;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//depth first search(recursive) + no optimal(just a solution)
public class DFS_Final {

    int [][] DIRECTIONS= {{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0}};
    int ROWBOUNDS =0;
    int COLBOUNDS=0;

    boolean SOLUTIONEXIST=false;
    ArrayList<String> OPTIMAL= new ArrayList<>();
    boolean stepByStep;
    public String[][] solveMaze(int[][] maze,int startingRow, int startingCol, boolean step)//outputs solutions of maze
    {
        stepByStep=step;
        List<String> list = new ArrayList<>();
        ROWBOUNDS = maze.length;
        COLBOUNDS = maze[0].length;
        boolean[][] visited = new boolean[ROWBOUNDS][COLBOUNDS];
        solveMazeHelper(maze,list, null,startingRow, startingCol, visited);
        if(!SOLUTIONEXIST){
            System.out.println("No solution exists.");
        }else{
            String solutionString=OPTIMAL.get(0).replace("null","");
            String [] solutionStringArray = solutionString.split("->");
            String[][] solutionsDoubleArr= new String[solutionStringArray.length][];
            for(int i=0; i<solutionStringArray.length;i++){
                solutionsDoubleArr[i] = new String[2];
                solutionsDoubleArr[i][0]=solutionStringArray[i].split(",")[0].replace("[","");
                solutionsDoubleArr[i][1]=solutionStringArray[i].split(",")[1].replace("]","");
            }
            System.out.println(Arrays.toString(solutionStringArray));
            System.out.println(Arrays.deepToString(solutionsDoubleArr));
            return solutionsDoubleArr;
        }return new String[0][0];
    }
    private void solveMazeHelper(int[][] maze,List<String> list,String path, int row, int col, boolean[][] visited){
        if(SOLUTIONEXIST){return;}
        else if(visited[row][col]){return;}
        // marks cell as visited
        visited[row][col]= true;
        

        if(maze[row][col]==3){
            list.add(path+"["+row+","+col+"]");
            if(!SOLUTIONEXIST){OPTIMAL.add(path+"["+row+","+col+"]");SOLUTIONEXIST=true;}
            visited[row][col] = false;
            return;
        }
        else if(maze[row][col]==1){return;}

        for(int[] vector: DIRECTIONS){
            if(row+vector[0]>=0&&col+vector[1]>=0&&row+vector[0]<ROWBOUNDS&&col+vector[1]<COLBOUNDS){
                solveMazeHelper(maze,list, path+"["+row+","+col+"]->",row+vector[0],col+vector[1], visited);
            }
        }
        visited[row][col] = false;

    }
}
