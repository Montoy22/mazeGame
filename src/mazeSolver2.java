import java.util.ArrayList;
import java.util.List;

//depth first search(recursive) + no optimal(just a solution)
public class mazeSolver2 {
    int [][] DIRECTIONS= {{-1,1},{0,1},{1,1},{1,0},{1,-1},{0,-1},{-1,-1},{-1,0}};
    int ROWBOUNDS =0;
    int COLBOUNDS=0;

    boolean SOLUTIONEXIST=false;
    ArrayList<String> OPTIMAL= new ArrayList<>();

    public void solveMaze(int[][] maze,int startingRow, int startingCol)//outputs solutions of maze
    {
        List<String> list = new ArrayList<>();
        ROWBOUNDS = maze.length;
        COLBOUNDS = maze[0].length;
        boolean[][] visited = new boolean[ROWBOUNDS][COLBOUNDS];
        solveMazeHelper(maze,list, null,startingRow, startingCol, visited);
        if(!SOLUTIONEXIST){
            System.out.println("No solution exists.");
        }else{
        System.out.println("There were "+list.size()+" paths.");
//        for(String paths:list){
//            System.out.println(paths);
//        }
        System.out.println("The optimal path is:");
        System.out.println(OPTIMAL.toString());}

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
