package view;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JTextArea;

public class CustomOutput extends OutputStream {
    private JTextArea jta;

    public CustomOutput(JTextArea jta) {
        this.jta = jta;
    }
    
    @Override
    public void write(int b) throws IOException {
    	jta.append(String.valueOf((char)b));
    	jta.setCaretPosition(jta.getDocument().getLength());
    	jta.update(jta.getGraphics());
    }
}