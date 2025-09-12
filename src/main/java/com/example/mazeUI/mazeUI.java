package com.example.mazeUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import com.example.path_finding_algo.BFS_Final;
import com.example.path_finding_algo.DFS2_Final;
import com.example.path_finding_algo.DFS_Final;
import java.util.Objects;


public class mazeUI {
int[][] maze;
    int ROWBOUNDS =0;
    int COLBOUNDS=0;
    JButton[][] buttons;
    boolean step=false;
    String[][] solutionArr;
    JFrame frame;

    mazeUI(int[][] maze){
        this.maze=maze;
        ROWBOUNDS = maze.length;
        COLBOUNDS = maze[0].length;
        buttons=  new JButton[ROWBOUNDS][COLBOUNDS];
        frame = new JFrame("Maze Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLayout(new GridLayout(ROWBOUNDS, COLBOUNDS));
        maze[0][0]=0;//forcing starting zone

        //making maze with buttons
        for(int i=0; i<ROWBOUNDS;i++) {
            for (int j = 0; j < COLBOUNDS; j++) {
                int finalJ = j;
                int finalI = i;
                if(maze[i][j]==0){
                    if(i==0&&j==0){
                        JButton button = new JButton("START");
                        button.setBackground(new Color(255, 255, 0));//yellow
                        buttons[i][j]=button;
                        frame.add(button);

                    }else{
                        JButton button = new JButton("");
                        buttons[i][j]=button;
                        button.addActionListener(new ActionListener() {
                        boolean pressed = false;
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            if(!pressed){
                                button.setBackground(new Color(118,238,0));//green
                                maze[finalI][finalJ]=2;
                                pressed=true;
                            }else{
                                    button.setBackground(UIManager.getColor("Button.background"));
                                    maze[finalI][finalJ]=0;
                                    pressed = false;
                            }
                        }
                    });
                    frame.add(button);
                }}
                else if (maze[i][j]==1){
                    JButton button = new JButton("");
                    button.setBackground(new Color(29, 29, 29));//black
                    buttons[i][j]=button;
                    frame.add(button);
                }//118,238,0->chartreuse2; 182,245,248->light blue
                else{
                    JButton button = new JButton("END");
                    button.setBackground(new Color(255, 255, 0));//yellow
                    buttons[i][j]=button;
                    frame.add(button);
                }
            }
        }

        // Creating a menu bar
        JMenuBar menuBar = new JMenuBar();

        // Creating menus
        JMenu answerMenu = new JMenu("Check Answer");
        JMenu solutionMenu = new JMenu("See Solution");
        JMenu optionMenu = new JMenu("Option");
        JMenu helpMenu = new JMenu("Help");
        JMenu returnMenu = new JMenu("Return");

        // Creating menu items
        JMenuItem show = new JMenuItem("check");
        JMenuItem bfs = new JMenuItem("BFS");
        JMenuItem dfs = new JMenuItem("DFS iterative");
        JMenuItem dfs2 = new JMenuItem("DFS recursive");
        JMenuItem aboutItem = new JMenuItem("how to play");
        JMenuItem stepOption = new JMenuItem("step by step");
        JMenuItem changeMazeOption = new JMenuItem("select maze");
        JMenuItem resetScreen = new JMenuItem("reset screen");
        JMenuItem returnToMenu = new JMenuItem("return to menu"); 

        // Adding menu items to menus
        answerMenu.add(show);
        solutionMenu.add(bfs);
        solutionMenu.add(dfs);
        solutionMenu.add(dfs2);
        optionMenu.add(stepOption);
        optionMenu.add(changeMazeOption);
        optionMenu.add(resetScreen);
        helpMenu.add(aboutItem);
        returnMenu.add(returnToMenu);

        // Adding menus to the menu bar
        menuBar.add(answerMenu);
        menuBar.add(solutionMenu);
        menuBar.add(optionMenu);
        menuBar.add(helpMenu);
        menuBar.add(returnMenu);

        //Adding behaviors to menu items
        show.addActionListener(e -> checkAnswer());
        bfs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    BFS_Final bfs_final = new BFS_Final();
                    solutionArr = bfs_final.solveMaze(maze,0,0,step);
                    for(String[] cell:solutionArr){
                        colorCells(Integer.parseInt(cell[0]),Integer.parseInt(cell[1]));
                    }

            }
        });
        dfs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    DFS_Final dfs_final = new DFS_Final();
                    solutionArr = dfs_final.solveMaze(maze, 0, 0, step);
                    for(String[] cell:solutionArr){
                    colorCells(Integer.parseInt(cell[0]),Integer.parseInt(cell[1]));
                    }
            }
        });
        dfs2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(step){}
                else{
                    DFS2_Final dfs2_final = new DFS2_Final();
                    solutionArr = dfs2_final.solveMaze(maze, 0, 0);
                    for(String[] cell:solutionArr){
                    colorCells(Integer.parseInt(cell[0]),Integer.parseInt(cell[1]));
                    }
                }
            }
        });
        stepOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(step){
                    stepOption.setText("Disable step");
                }
                else{
                    stepOption.setText("Enable step");
                }
                step=!step;
            }
        });
        resetScreen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for(int i=0; i<ROWBOUNDS;i++) {
                    for (int j = 0; j < COLBOUNDS; j++) {
                        if(!Objects.equals(buttons[i][j].getBackground(), new Color(29, 29, 29))
                                &&!Objects.equals(buttons[i][j].getBackground(), new Color(255, 255, 0))){//comparing with black and yellow
                            buttons[i][j].setBackground(UIManager.getColor("Button.background"));//resetting to default colour
                            if(maze[i][j]==2){maze[i][j]=0;}
                        }
                    }
                }
            }
        });

        changeMazeOption.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mazeLoader mL = new mazeLoader();
                int[][] newMaze=mL.selectMaze();
                new mazeUI(newMaze);
            }
        });

        returnToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UI();
                frame.dispose();
            }
        });

        // Set the menu bar to the frame
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }
    public void checkAnswer(){
        userSolutionChecker checker = new userSolutionChecker();
        boolean isSolutionValid = checker.answerChecker(maze,0,0);
        JFrame frame = new JFrame("Result");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        if(isSolutionValid){
            JOptionPane.showMessageDialog(frame,"Your solution is valid!!!");
        }else{
            JOptionPane.showMessageDialog(frame," :(,Your solution is invalid");
        }
    }
    public void colorCells(int row, int col){
        buttons[row][col].setBackground(new Color(245,123,0));
    }

}