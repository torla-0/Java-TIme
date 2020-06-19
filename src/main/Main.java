
package main;

import java.lang.reflect.InvocationTargetException;
import javax.swing.SwingUtilities;


public class Main {

    public static void main(String[] args) {
        
        MainFrame frame = new MainFrame();
        
        try {
            SwingUtilities.invokeAndWait(() -> {
                frame.setVisible(true);
            });
        } catch (InterruptedException | InvocationTargetException ex) {
        }

    }
    
}
