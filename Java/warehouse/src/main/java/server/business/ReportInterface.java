package server.business;

import javax.swing.*;
import java.io.File;

public interface ReportInterface {

    void makeReport(String msg, File location);

    static File getLocation(){
        JFrame parentFrame = new JFrame();
        File fileToSave = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Specify a file to save");
        int userSelection = fileChooser.showSaveDialog(parentFrame);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToSave = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fileToSave.getAbsolutePath());
        }
        return  fileToSave;
    }
}
