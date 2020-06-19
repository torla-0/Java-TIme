package main;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;


public class ClockPanel extends JPanel{
    // clock setup / gui
    Font font = new Font("TimesRoman", Font.BOLD, 72);
    SpringLayout springLayoutClock = new SpringLayout();
    JLabel showTimeLabel = new JLabel();
    Integer showTime = 1000;
    //resize timer variables
    int xAxisTimeLabel = 0;
    int yAxisTimeLabel = 0;
    
    JPanel clockPanel;
    ClockPanel()
    {
        clockPanel = this;
        time.start();
        resizeTimerForClock.start();
        
        setLayout(springLayoutClock);
        add(showTimeLabel);
        showTimeLabel.setFont(font);
        showTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    }
    // timer to show time
    Timer time = new Timer(showTime, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String pattern = " HH:mm:ss";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String time = simpleDateFormat.format(new Date());
            showTimeLabel.setText(time);
        }
    });
    // timer to keep time in middle \\ future feature to make it resizeble
        Timer resizeTimerForClock = new Timer(200, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            int widthLabel = showTimeLabel.getWidth();
            int heightLabel = showTimeLabel.getHeight();

            if(clockPanel.getWidth() > widthLabel)
            {
                xAxisTimeLabel = (clockPanel.getWidth() - widthLabel ) / 2;  
            }else
            {
                xAxisTimeLabel = 0;
            }
            
            if(clockPanel.getHeight() > heightLabel)
            {
                yAxisTimeLabel = (clockPanel.getHeight() - heightLabel ) / 2;
            }else
            {
                yAxisTimeLabel = 0;               
            } 
            springLayoutClock.putConstraint(SpringLayout.NORTH, showTimeLabel, yAxisTimeLabel, SpringLayout.WEST, clockPanel);
            springLayoutClock.putConstraint(SpringLayout.WEST, showTimeLabel, xAxisTimeLabel, SpringLayout.NORTH, clockPanel);
        }
    });
}
