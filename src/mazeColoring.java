import javax.swing.*;

public class mazeColoring {
    mazeUI mazeUI;
    JButton button;

    mazeColoring(mazeUI mazeui){
        this.mazeUI=mazeui;
    }
    public void coloring(String[][] cells){
        for(String[] cell:cells){
            mazeUI.colorCells(Integer.parseInt(cell[0]),Integer.parseInt(cell[1]));
        }
    }
}
