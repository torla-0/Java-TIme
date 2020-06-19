package main;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.sound.sampled.Clip;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import static main.SettingsPanel.chooseClip;


public class AlarmClockPanel extends JPanel{
    
    LocalTime timeAtThisMoment;
    JLabel showTextEnterTimeLabel = new JLabel("Enter time");
    
    Font font = new Font("TimesRoman", Font.PLAIN, 72);
    JPanel panelForParam = new JPanel();
    JLabel hourLabel = new JLabel("Hour");
    JLabel minuteLabel = new JLabel("Minute");
    
    JTextField hourTextfield = new JTextField(2);
    JTextField minuteTextfield = new JTextField(2);
    
    Font fontTwo = new Font("TimesRoman", Font.PLAIN, 36);
    
    JButton addAlarm = new JButton("Add alarm");
    JButton deleteAlarm = new JButton("Delete alarm");
    JPanel buttonPanel = new JPanel();
    JLabel newAlarm = new JLabel("");
    
    JPanel mainPanel = new JPanel();
    JPanel showAllAlarmsPanel = new JPanel();
    JLabel allAlarmsLabel = new JLabel("Alarm set to:");
    //__________________________________________________________________________ Logic
    
    int alarmHour;
    int alarmMinute;
    int alarmSecond;
    
    //List<String> allAlarms = new ArrayList();
    
    Clip clip;
    boolean isTime = false;
    String alarmString;

    AlarmClockPanel()
    {    
        panelForParam.setLayout(new FlowLayout());
        panelForParam.add(hourLabel);
        panelForParam.add(hourTextfield);
        panelForParam.add(minuteLabel);
        panelForParam.add(minuteTextfield);
        
        hourLabel.setFont(fontTwo);
        hourTextfield.setFont(fontTwo);
        minuteLabel.setFont(fontTwo);
        minuteTextfield.setFont(fontTwo);
        
        buttonPanel.add(addAlarm);
        buttonPanel.add(deleteAlarm);
        buttonPanel.setLayout(new FlowLayout());

        showAllAlarmsPanel.setLayout(new FlowLayout());
        showAllAlarmsPanel.add(allAlarmsLabel);
        showAllAlarmsPanel.add(newAlarm);
        allAlarmsLabel.setFont(fontTwo);

        
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(panelForParam, BorderLayout.CENTER);
        mainPanel.add(showAllAlarmsPanel, BorderLayout.SOUTH);
        
        showTextEnterTimeLabel.setFont(font); 
        showTextEnterTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        setLayout(new BorderLayout());  
        add(showTextEnterTimeLabel, BorderLayout.NORTH);        
        add(mainPanel, BorderLayout.CENTER);  
        add(buttonPanel, BorderLayout.SOUTH);
        
    }
    //__________________________________________________________________________    Event's
    {
        addAlarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                alarmString = hourTextfield.getText() + ":" + minuteTextfield.getText();
                
                    String hour = hourTextfield.getText();
                    String minute = minuteTextfield.getText();
                    newAlarm.setText("["+hour+":"+minute+"]");
                    newAlarm.setFont(fontTwo);
                    //showAllAlarmsPanel.add(newAlarm);
                    JOptionPane.showMessageDialog(null, "Alarm added: " + hour + ":" + minute);
                    
                    checkForAlarm.start(); 
            }
        });
        deleteAlarm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alarmString = "";
                newAlarm.setText("");
                hourTextfield.setText("");
                minuteTextfield.setText("");
            }
        });
    }
    //__________________________________________________________________________    Method's
        private void checkForAlarm()
    {
        timeAtThisMoment = LocalTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        String formatTime = timeAtThisMoment.format(dtf);
        String[] formatTimeSplit = formatTime.split(":");
        String[] splitListElement = alarmString.split(":");
            
            isTime = formatTimeSplit[0].equals(splitListElement[0]) && formatTimeSplit[1].equals(splitListElement[1]);;
            if(isTime)
            {
                chooseClip.loop(Clip.LOOP_CONTINUOUSLY);
                alarmString = "";
                isTime = false;
                newAlarm.setText("");
                hourTextfield.setText("");
                minuteTextfield.setText("");
                ShutDownTimerFrame f = new ShutDownTimerFrame();
                f.setClip(chooseClip);
            }
    }
    //__________________________________________________________________________    Timer's
    Timer checkForAlarm = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            checkForAlarm();
        }
    });
}
