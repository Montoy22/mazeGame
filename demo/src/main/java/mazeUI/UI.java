package mazeUI;

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
    public UI(){
        JFrame frame = new JFrame("Maze solver");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 700);
        frame.setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("MΛZΣ ƧӨᄂVΣЯ ",JLabel.CENTER);
        titleLabel.setFont(new Font("Hoefler Text",Font.BOLD,50));
        titleLabel.setPreferredSize(new Dimension(500, 600));
        frame.add(titleLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(200,50));
        JButton button1 = new JButton("Create a maze");
        JButton button2 = new JButton("Solve a maze");

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mazeBuilderUI();
            }
        });

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mazeUI(maze);
            }
        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);


    }


}
