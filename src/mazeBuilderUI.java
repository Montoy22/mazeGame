import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class mazeBuilderUI {
        int [][] maze = {
                {0,1,0,0,0},
                {0,1,0,1,0},
                {0,1,0,1,0},
                {0,1,0,1,0},
                {0,0,0,1,0},
                {1,0,1,0,3}//<-
        };

    int rows = 6;
    int cols=5;
    String mazeGenerationAlgorithm="";
    JPanel[][] preview;
    boolean mazeModified= false;
    JFrame frame;
    mazeBuilderUI(){
            frame = new JFrame("Maze solver");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 700);
            frame.setLayout(new BorderLayout());

            JLabel titleLabel = new JLabel("MΛZΣ ƧӨLVΣЯ ");
            titleLabel.setFont(new Font("Hoefler Text",Font.BOLD,25));
            titleLabel.setPreferredSize(new Dimension(50, 60));
            frame.add(titleLabel, BorderLayout.NORTH);

            JPanel buttonPanel = new JPanel(new GridLayout(4, 1, 5, 5));
            buttonPanel.setPreferredSize(new Dimension(200,50));
            JButton generationAlgorithmKruskal = new JButton("Kruskal");
            JButton generationAlgorithmPrim = new JButton("Prim");
            JButton generationAlgorithmRB = new JButton("Recursive Backtracking");
            JButton generationAlgorithmAB = new JButton("Aldous-Broder");

            JPanel buttonPanel1 = new JPanel(new FlowLayout());
            JButton buttonGenerateMaze = new JButton("Generate");
            JButton saveButton = new JButton("save");
            JButton returnButton = new JButton("return to main menu"); 


            JLabel rowLabel = new JLabel("Rows:");
            JTextField rowField = new JTextField();

            JLabel colLabel = new JLabel("Columns:");
            JTextField colField = new JTextField();

            JButton submitButton = new JButton("Submit");

            JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10)); // 2 rows, 2 columns, with spacing
            inputPanel.setBorder(BorderFactory.createTitledBorder("Maze Specifications"));

            inputPanel.add(rowLabel);
            inputPanel.add(rowField);
            inputPanel.add(colLabel);
            inputPanel.add(colField);

            //maze preview
            JPanel boardPreview = new JPanel(new GridLayout(8, 8));
            boardPreview.setPreferredSize(new Dimension(400, 200));
            preview = new JPanel[8][8];
            for (int row = 0; row < 8; row++) {
                for (int col = 0; col < 8; col++) {
                    JPanel square = new JPanel();
                    square.setPreferredSize(new Dimension(40, 40)); // optional size
                    square.setBackground((row + col) % 2 == 0 ? Color.WHITE : Color.BLACK);
                    preview[row][col] = square;
                    boardPreview.add(square);
                }
            }
            JPanel paddedBoardPreview = new JPanel(new BorderLayout());
            paddedBoardPreview.setBorder(new EmptyBorder(20, 20, 20, 80));


            generationAlgorithmKruskal.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mazeGenerationAlgorithm="Kruskal";
                }
            });

            generationAlgorithmPrim.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mazeGenerationAlgorithm="Prim";
                }
            });

            generationAlgorithmRB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mazeGenerationAlgorithm="RecursiveBacktracking";
                    }
            });

            generationAlgorithmAB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    mazeGenerationAlgorithm="Aldous-Broder";
                }
            });

            buttonGenerateMaze.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    switch (mazeGenerationAlgorithm) {
                        case "Kruskal" -> maze = new mazeMakerKruskal().kruskalMaze(rows, cols);
                        case "Prim" -> maze = new mazeMakerPrim().primMaze(rows, cols);
                        case "RecursiveBacktracking" -> maze = new MazeMakerRB().rBMaze(rows, cols);
                        default -> maze = new mazeMakerAB().ABMaze(rows, cols);
                    }
                    boardPreview.removeAll();
                    boardPreview.setLayout(new GridLayout(rows, cols));
                    for (int row = 0; row < rows; row++) {
                        for (int col = 0; col < cols; col++) {
                            JPanel square = new JPanel();
                            square.setPreferredSize(new Dimension(40, 40)); // optional size
                            if((row==0 && col==0)||(row==rows-1&& col ==cols-2)){square.setBackground(Color.YELLOW);}
                            else if(maze[row][col]==1){square.setBackground(Color.BLACK);}
                            else{square.setBackground(Color.WHITE);}
                            square.addMouseListener(new MouseAdapter() {
                                @Override
                                public void mouseClicked(MouseEvent e) {
                                    mazeModified=true;
                                    if(square.getBackground().equals(Color.BLACK)){square.setBackground(Color.WHITE);}
                                    else{square.setBackground(Color.BLACK);}
                                }
                            });
                            //preview[row][col] = square;
                            boardPreview.add(square);
                        }
                    }
                    boardPreview.revalidate();
                    boardPreview.repaint();
                }
            });

            saveButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(mazeModified){//remaking maze from boardPreview
                        int rowIndex =-1;
                        int colIndex = 0;
                        for(Component element : boardPreview.getComponents()){
                            if(colIndex==0){rowIndex++;}
                            if(element.getBackground().equals(Color.BLACK)){
                                maze[rowIndex][colIndex]=1;
                            }
                            else if(element.getBackground().equals(Color.YELLOW)){
                                maze[rowIndex][colIndex]=3;
                            }
                            else{
                                maze[rowIndex][colIndex]=0;
                            }
                            colIndex++;
                            colIndex=colIndex%cols;
                        }
                    }
                    String filename = JOptionPane.showInputDialog(null, "Enter filename:", "Input filename", JOptionPane.PLAIN_MESSAGE);
                    File directory = new File("Saved_Mazes");
                    File file = new File(directory, filename + ".txt");
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                        for (int[] row : maze) {
                            for (int cell : row) {
                                writer.write(cell + " ");
                            }
                            writer.newLine();
                        }
                        System.out.println("newly created maze has been saved!");
                    } catch (IOException x) {
                        x.printStackTrace();
                    }
                }
            });

            submitButton.addActionListener(e -> {
                try {
                     rows = Integer.parseInt(rowField.getText());
                     cols = Integer.parseInt(colField.getText());
                    JOptionPane.showMessageDialog(frame, "You entered: " + rows + " rows and " + cols + " columns");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Please enter valid integers!", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new UI();
                    frame.dispose();
                }
            });
            returnButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Main();
                    frame.dispose();
                }
            });
        buttonPanel.add(generationAlgorithmKruskal);
        buttonPanel.add(generationAlgorithmPrim);
        buttonPanel.add(generationAlgorithmRB);
        buttonPanel.add(generationAlgorithmAB);

        buttonPanel1.add(buttonGenerateMaze);
        buttonPanel1.add(saveButton);
        buttonPanel1.add(submitButton);
        buttonPanel1.add(returnButton);
        frame.add(buttonPanel, BorderLayout.WEST);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.add(inputPanel);
        bottomPanel.add(buttonPanel1);

        paddedBoardPreview.add(boardPreview, BorderLayout.CENTER);
        frame.add(paddedBoardPreview, BorderLayout.EAST);

        frame.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(new JLabel());
        frame.setVisible(true);
    }
}
