package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.Timer;
import static main.SettingsPanel.chooseClip;


public class TimerPanel extends JPanel{
    // timer setup / gui
    JPanel clockPanel;
    JPanel timerPanel;
    SpringLayout springLayoutTimer = new SpringLayout();
    JPanel carryPanel = new JPanel();
    SpinnerModel spinnerModelOne = new SpinnerNumberModel(0, 0, 23, 1);
    SpinnerModel spinnerModelTwo = new SpinnerNumberModel(0, 0, 59, 1);
    SpinnerModel spinnerModelTree = new SpinnerNumberModel(0, 0, 59, 1);
    JSpinner hourSpinner = new JSpinner(spinnerModelOne);
    JSpinner minuteSpinner = new JSpinner(spinnerModelTwo);
    JSpinner secondSpinner = new JSpinner(spinnerModelTree);
    FlowLayout flowLayout = new FlowLayout();
    JLabel hourLabel = new JLabel("h");
    JLabel minuteLabel = new JLabel("m");
    JLabel secondLabel = new JLabel("s");
    JButton startButton = new JButton("Start");
    Font font = new Font("TimesRoman", Font.BOLD, 72);
    // resize timer variables
    int xAxisStartButton = 0;
    int xAxisCarryPanel = 0;
    int yAxisCarryPanel = 0;
    // logic variables
    Integer getHour = null;
    Integer getMinutes = null;
    Integer getSeconds = null;
    
    CountdownTimeFrame countdownTimeFrame;
    JLabel helpLabelForCountdownTimeFrame = new JLabel();
    
    public TimerPanel(JFrame frame, JPanel panel) 
    {
        timerPanel = this;
        clockPanel = panel;
        resizeTimerForTimer.start();
        
        
        
        hourSpinner.setFont(font);
        minuteSpinner.setFont(font);
        secondSpinner.setFont(font);
        hourLabel.setFont(font);
        minuteLabel.setFont(font);
        secondLabel.setFont(font);
        startButton.setFont(new Font("TimesRoman", Font.BOLD, 24));
        
        carryPanel.add(hourSpinner);
        carryPanel.add(hourLabel);
        carryPanel.add(minuteSpinner);
        carryPanel.add(minuteLabel);
        carryPanel.add(secondSpinner);
        carryPanel.add(secondLabel);
        
        setLayout(springLayoutTimer);
        add(carryPanel);
        add(startButton);
    }
        Timer resizeTimerForTimer = new Timer(200, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            int widthCarryPanel = carryPanel.getWidth();
            int heightCarryPanel = carryPanel.getHeight();

            if(clockPanel.getWidth() > widthCarryPanel)
            {
                xAxisCarryPanel = (clockPanel.getWidth() - widthCarryPanel ) / 2;  
            }else
            {
                xAxisCarryPanel = 0;
            }
            
            if(clockPanel.getHeight() > heightCarryPanel)
            {
                yAxisCarryPanel = (clockPanel.getHeight() - heightCarryPanel ) / 2;
            }else
            {
                yAxisCarryPanel = 0;               
            }
            springLayoutTimer.putConstraint(SpringLayout.WEST, carryPanel, xAxisCarryPanel, SpringLayout.WEST, timerPanel);
            springLayoutTimer.putConstraint(SpringLayout.NORTH, carryPanel, yAxisCarryPanel, SpringLayout.NORTH, timerPanel);
            
            xAxisStartButton = carryPanel.getWidth() / 2;
            springLayoutTimer.putConstraint(SpringLayout.NORTH, startButton, 10, SpringLayout.SOUTH, carryPanel);
            springLayoutTimer.putConstraint(SpringLayout.WEST, startButton, xAxisStartButton-(startButton.getWidth()/2), SpringLayout.WEST, carryPanel);
        }
    });
            Timer timerCountDownTime = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            String setTime = "Time: " + getHour + ":" + getMinutes + ":" + getSeconds ;
            countdownTimeFrame.setTimeForCountDown(setTime);

            if(getSeconds > 0)
            {
                getSeconds =  getSeconds - 1;
                setTime = "Time: " + getHour + ":" + getMinutes + ":" + getSeconds ;
                countdownTimeFrame.setTimeForCountDown(setTime);
            }else if(getMinutes > 0)
            {
                getMinutes = getMinutes - 1;
                getSeconds = 59;
                setTime = "Time: " + getHour + ":" + getMinutes + ":" + getSeconds ;
                countdownTimeFrame.setTimeForCountDown(setTime);
            }else if(getHour > 0)
            {
                getHour = getHour - 1;
                getMinutes = 59;
                setTime = "Time: " + getHour + ":" + getMinutes + ":" + getSeconds ;
                countdownTimeFrame.setTimeForCountDown(setTime);
            }else
            {
               chooseClip.loop(Clip.LOOP_CONTINUOUSLY);
               shutdownTimer();
               
               ShutDownTimerFrame f = new ShutDownTimerFrame();
               f.setClip(chooseClip);
            }  
        }
    });
    private void shutdownTimer()
    {
        timerCountDownTime.stop();
        countdownTimeFrame.dispose();
    }
    {
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getHour = ((Integer)hourSpinner.getValue());
                getMinutes = ((Integer)minuteSpinner.getValue());
                getSeconds = ((Integer)secondSpinner.getValue());

                countdownTimeFrame = new CountdownTimeFrame(timerCountDownTime);
                timerCountDownTime.start();
            }
        });
    }
}
