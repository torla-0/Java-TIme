package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class CountdownTimeFrame extends JFrame{
    
    int hours;
    int minutes;
    int seconds;
    String time = "";
    Timer timer = null;
    
        
    JButton cancelBtn = new JButton("Cancel");
    JButton pauseBtn = new JButton("Pause");
        
    JPanel labelPanel = new JPanel();
    FlowLayout labelPanelFlowLayout = new FlowLayout();
        
    JPanel buttonPanel = new JPanel();
    
    JLabel showTimeCountdownLabel = new JLabel();
    Font font = new Font("TimesRoman", Font.BOLD, 72);
    
    
        CountdownTimeFrame(Timer timer)
        {
            setTitle("Countdown");
            this.timer = timer;

            
            buttonPanel.setLayout(labelPanelFlowLayout);
            buttonPanel.add(cancelBtn);
            buttonPanel.add(pauseBtn);
            
            
            add(showTimeCountdownLabel, BorderLayout.NORTH);
            add(buttonPanel, BorderLayout.SOUTH);
            

            showTimeCountdownLabel.setFont(font);
            showTimeCountdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
            
            
            setSize(600, 300);
            setLocationRelativeTo(null);
            setVisible(true);
            
        }
    void setTimeForCountDown(String timeTime)
    {
        this.time = timeTime;
        showTimeCountdownLabel.setText(time);
    }
      // events for pause and cancel buttons
    {
        pauseBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(timer.isRunning())
                {
                    timer.stop();
                }else{
                    timer.start();
                }
            }
        });
        cancelBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shutdownThisFrame();
            }
        });
    }
private void shutdownThisFrame()
        {
            timer.stop();
            this.dispose();
        }

        
}
