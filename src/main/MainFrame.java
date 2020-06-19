
package main;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;


public class MainFrame extends JFrame{
    
    //main tabbed panel
    JTabbedPane mainPanel = new JTabbedPane();
    // create clock panel
    ClockPanel clockPanel = new ClockPanel();
    // create alarm panel
    AlarmClockPanel alarmClockFrame = new AlarmClockPanel();
    // create settings panel
    SettingsPanel settingsFrame = new SettingsPanel();
    // create timer panel
    TimerPanel timerPanel = new TimerPanel(this, clockPanel);
    //                  Constructor
    public MainFrame()
    {
        setTitle("Time app");

        
        mainPanel.add("Clock", clockPanel);
        mainPanel.add("Timer", timerPanel); // add timer panel
        mainPanel.add("Alarm", alarmClockFrame); // add alarm panel
        mainPanel.add("Settings", settingsFrame); // add settings panel
        add(mainPanel);
        
        setResizable(false);
        setSize(700, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
