package com.example.maze_making_algo;

import java.util.*;

public class mazeMakerKruskal {

    static class DisjointSet {
        int[] parent;
        DisjointSet(int size) {
            parent = new int[size];
            for (int i = 0; i < size; i++)
                parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int x, int y) {
            int px = find(x);
            int py = find(y);
            if (px == py) return false;
            parent[px] = py;
            return true;
        }
    }

    static class Wall {
        int cell1, cell2;
        int r1, c1, r2, c2;

        Wall(int cell1, int cell2, int r1, int c1, int r2, int c2) {
            this.cell1 = cell1;
            this.cell2 = cell2;
            this.r1 = r1;
            this.c1 = c1;
            this.r2 = r2;
            this.c2 = c2;
        }
    }

    static int[][] generateMaze(int rows, int cols) {
        int totalCells = rows * cols;
        DisjointSet ds = new DisjointSet(totalCells);
        List<Wall> walls = new ArrayList<>();

        // Create walls between adjacent cells
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int cell = r * cols + c;
                if (c < cols - 1)  // Right wall
                    walls.add(new Wall(cell, cell + 1, r, c, r, c + 1));
                if (r < rows - 1)  // Bottom wall
                    walls.add(new Wall(cell, cell + cols, r, c, r + 1, c));
            }
        }
        Collections.shuffle(walls);
        // Initialize maze grid with all walls (1s)
        int mazeRows = 2 * rows + 1;
        int mazeCols = 2 * cols + 1;
        int[][] maze = new int[mazeRows][mazeCols];
        for (int[] row : maze) Arrays.fill(row, 1);

        // Mark cell centers as paths (0s)
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                maze[2 * r + 1][2 * c + 1] = 0;
            }
        }

        // Carve paths by removing walls
        for (Wall w : walls) {
            if (ds.union(w.cell1, w.cell2)) {
                int wallR = w.r1 + w.r2 + 1;
                int wallC = w.c1 + w.c2 + 1;
                maze[wallR][wallC] = 0;
            }
        }

        return maze;
    }

    static void printMazeArray(int[][] maze) {
        for (int[] row : maze) {
            for (int cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }
    public int[][] kruskalMaze(int row, int col){
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
    public static void main(String[] args) {
        int rows = 5, cols = 5;
        int[][] maze = generateMaze(rows, cols);
        maze[0][0]=0;
        maze[maze[0].length-1][maze[0].length-1]=3;
        printMazeArray(maze);
    }
}

