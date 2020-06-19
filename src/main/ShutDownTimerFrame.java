
package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;


public class ShutDownTimerFrame extends JFrame{
    
    JButton button = new JButton("Close");
    Clip clip;
    
    ShutDownTimerFrame()
    {
        setTitle("Time elapsed");
        add(button);
        
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        
    }
    {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                    stopMusic();
                    shutdownFrame();
            }
        });
    }
    void setClip(Clip clip)
    {
        this.clip = clip;
    }
    void stopMusic()
    {
        clip.stop();
    }
    void shutdownFrame()
    {
        this.dispose();
    }

}
