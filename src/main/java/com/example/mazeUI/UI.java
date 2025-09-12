package com.example.mazeUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI {
    int [][] maze = {
            {0,1,0,0,0},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {0,1,0,1,0},
            {0,0,0,1,0},
            {1,0,1,0,3}};//<-
            JFrame frame;
    public UI(){
        frame = new JFrame("Maze solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("MΛZΣ ƧӨLVΣЯ ",JLabel.CENTER);
        titleLabel.setFont(new Font("Hoefler Text",Font.BOLD,50));
        titleLabel.setPreferredSize(new Dimension(500, 600));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(200,50));
        JButton createButton = new JButton("Create a maze");
        JButton solveButton = new JButton("Solve a maze");

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mazeBuilderUI();
                frame.dispose();
            }
        });

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mazeUI(maze);
                frame.dispose();
            }
        });

        buttonPanel.add(createButton);
        buttonPanel.add(solveButton);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);


    }


}