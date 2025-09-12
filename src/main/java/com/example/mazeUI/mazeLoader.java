package com.example.mazeUI;

import javax.swing.*;
import java.io.*;
import java.util.*;

public class mazeLoader {
    int[][] maze;
    public int[][] selectMaze() {
        JFileChooser fileChooser = new JFileChooser(new File("Saved_Mazes"));
        fileChooser.setDialogTitle("Select a Maze File");

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();

            try {
                List<int[]> rows = new ArrayList<>();
                BufferedReader reader = new BufferedReader(new FileReader(selectedFile));
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] tokens = line.trim().split("\\s+"); // split by space
                    int[] row = new int[tokens.length];

                    for (int i = 0; i < tokens.length; i++) {
                        row[i] = Integer.parseInt(tokens[i]);
                    }

                    rows.add(row);
                }
                reader.close();

                // Convert List<int[]> to int[][]
                maze = rows.toArray(new int[0][]);
            } catch (IOException | NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error reading file.");
            }
        }
        return maze;
    }
}
