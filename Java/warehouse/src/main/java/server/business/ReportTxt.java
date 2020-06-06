package server.business;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ReportTxt implements ReportInterface {

    @Override
    public void makeReport(String msg, File location) {
        try {
            String loc = location.toString();
            File myObj = new File(loc);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                try {
                    FileWriter myWriter = new FileWriter(loc);
                    myWriter.write(msg);
                    myWriter.close();
                    System.out.println("Successfully wrote to the file.");
                } catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
