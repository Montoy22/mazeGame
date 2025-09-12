package com.example.path_finding_test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import com.example.path_finding_algo.BFS_Final;
import com.example.path_finding_algo.DFS2_Final;
import com.example.path_finding_algo.DFS_Final;
import java.util.ArrayList;
import java.util.Arrays;
public class BfsDfsTest {
    @Test
    void testingBFS_Final(){
        int [][] maze = {
            {0,1,0,0,0},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {0,0,0,1,0},
            {1,0,1,0,3}};
        String[] expectedSolution = {"[0, 0]","[1, 0]","[2, 0]","[3, 0]","[4, 1]","[4, 2]",
                                    "[5, 3]","[5, 4]"};
        BFS_Final ms = new BFS_Final();
        String [][] obtainedSolution = ms.solveMaze(maze, 0, 0, false);
        System.out.println(Arrays.deepToString(obtainedSolution));
        assertEquals(Arrays.toString(expectedSolution), Arrays.deepToString(obtainedSolution));
    }

    @Test
    void testingBFS_Final1(){//no solution exists
        int [][] maze = {
            {0,1,0,0,0},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {1,1,0,1,0},
            {0,1,0,1,0},
            {1,0,1,0,3}};
        String[] expectedSolution = {};
        BFS_Final ms = new BFS_Final();
        String [][] obtainedSolution = ms.solveMaze(maze, 0, 0, false);
        System.out.println(Arrays.deepToString(obtainedSolution));
        assertEquals(Arrays.toString(expectedSolution), Arrays.deepToString(obtainedSolution));
    }

    @Test
    void testingDFS_Final(){//solution exists
        int [][] maze = {
            {0,1,0,0,0},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {0,0,0,1,0},
            {1,0,1,0,3}};
        String[] expectedSolution = {"[0, 0]","[1, 0]","[2, 0]","[3, 0]","[4, 1]","[3, 2]",
                                    "[4, 2]","[5, 3]", "[4, 4]", "[5, 4]"};
        DFS_Final ms = new DFS_Final();
        String [][] obtainedSolution = ms.solveMaze(maze, 0, 0, false);
        System.out.println(Arrays.deepToString(obtainedSolution));
        assertEquals(Arrays.toString(expectedSolution), Arrays.deepToString(obtainedSolution));
    }

    @Test
    void testingDFS_Final1(){// NO solution exists
        int [][] maze = {
            {0,1,0,0,0},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {1,1,0,1,0},
            {0,1,0,1,0},
            {1,0,1,0,3}};
        String[] expectedSolution = {};
        DFS_Final ms = new DFS_Final();
        String [][] obtainedSolution = ms.solveMaze(maze, 0, 0, false);
        System.out.println(Arrays.deepToString(obtainedSolution));
        assertEquals(Arrays.toString(expectedSolution), Arrays.deepToString(obtainedSolution));
    }

    @Test
    void testingDFS2_Final(){//solution exists
        int [][] maze = {
            {0,1,0,0,0},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {0,0,0,1,0},
            {1,0,1,0,3}};
        String[] expectedSolution = {"[0, 0]","[1, 0]","[2, 0]","[3, 0]","[4, 0]","[5, 1]",
                                    "[4, 1]","[4, 2]", "[3, 2]", "[2, 2]", "[1, 2]", "[0, 2]", "[0, 3]", "[1, 4]", "[2, 4]", "[3, 4]", "[4, 4]", "[5, 3]", "[5, 4]"};;
        DFS2_Final ms = new DFS2_Final();
        String [][] obtainedSolution = ms.solveMaze(maze, 0, 0);
        System.out.println(Arrays.deepToString(obtainedSolution));
        assertEquals(Arrays.toString(expectedSolution), Arrays.deepToString(obtainedSolution));
    }

    @Test
    void testingDFS2_Final1(){// NO solution exists
        int [][] maze = {
            {0,1,0,0,0},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {1,1,0,1,0},
            {0,1,0,1,0},
            {1,0,1,0,3}};
        String[] expectedSolution = {};
        DFS2_Final ms = new DFS2_Final();
        String [][] obtainedSolution = ms.solveMaze(maze, 0, 0);
        System.out.println(Arrays.deepToString(obtainedSolution));
        assertEquals(Arrays.toString(expectedSolution), Arrays.deepToString(obtainedSolution));
    }

    
}
