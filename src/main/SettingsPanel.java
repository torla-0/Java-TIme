package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SpringLayout;


public class SettingsPanel extends JPanel{

    // gui setup
    JPanel mainPanel = new JPanel();
    JLabel setToneLabel = new JLabel("Set new tune:");
    SpringLayout springLayout = new SpringLayout();
    Font font = new Font("TimesRoman", Font.BOLD, 24);
    boolean controlBoolean = false;
    Clip clipOne = null;
    Clip clipTwo = null;
    Clip clipThree = null;
    
    static Clip chooseClip = null;
    
    
    
    // gui choose new tune
    JRadioButton radioButtonOne = new JRadioButton();
    JRadioButton radioButtonTwo = new JRadioButton();
    JRadioButton radioButtonThree = new JRadioButton();
    ButtonGroup radioGroup = new ButtonGroup();
    
    JLabel labelRadioButtonOne = new JLabel("Sound 1");
    JLabel labelRadioButtonTwo = new JLabel("Sound 2");
    JLabel labelRadioButtonThree= new JLabel("Sound 3");
    
    Font fontForRadioLabels = new Font("TimesRoman", Font.PLAIN, 16);
    
    // Play tune buttons
    JButton playOneJButton = new JButton("PLAY");
    JButton playTwoJButton = new JButton("PLAY");
    JButton playThreeJButton = new JButton("PLAY");
    Dimension dimensionJButton = new Dimension(70, 20);
    
    // set tune to be global
    JButton setTuneButton = new JButton("SET TUNE");
    
    public SettingsPanel()
    {
        mainPanel.setLayout(springLayout);
        
        springLayout.putConstraint(SpringLayout.NORTH, setToneLabel, 10, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.WEST, setToneLabel, 10, SpringLayout.NORTH, mainPanel);
        mainPanel.add(setToneLabel);
        
        setToneLabel.setFont(font);
        
        // radio buttons
        radioButtonOne.setSelected(true);
        radioGroup.add(radioButtonOne);
        radioGroup.add(radioButtonTwo);
        radioGroup.add(radioButtonThree);
        radioButtonOne.setBounds(75,100,100,3);
        radioButtonTwo.setBounds(75,100,100,3);
        radioButtonThree.setBounds(75,100,100,3);
        
        springLayout.putConstraint(SpringLayout.NORTH, radioButtonOne, 72, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.WEST, radioButtonOne, 30, SpringLayout.NORTH, mainPanel);
        
        springLayout.putConstraint(SpringLayout.NORTH, radioButtonTwo, 102, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.WEST, radioButtonTwo, 30, SpringLayout.NORTH, mainPanel);
        
        springLayout.putConstraint(SpringLayout.NORTH, radioButtonThree, 132, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.WEST, radioButtonThree, 30, SpringLayout.NORTH, mainPanel);
        
        mainPanel.add(radioButtonOne);
        mainPanel.add(radioButtonTwo);
        mainPanel.add(radioButtonThree);
        
        // labels for buttons
        springLayout.putConstraint(SpringLayout.NORTH, labelRadioButtonOne, 70, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.WEST, labelRadioButtonOne, 60, SpringLayout.NORTH, mainPanel);
        
        springLayout.putConstraint(SpringLayout.NORTH, labelRadioButtonTwo, 100, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.WEST, labelRadioButtonTwo, 60, SpringLayout.NORTH, mainPanel);
        
        springLayout.putConstraint(SpringLayout.NORTH, labelRadioButtonThree, 130, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.WEST, labelRadioButtonThree, 60, SpringLayout.NORTH, mainPanel);
        
        mainPanel.add(labelRadioButtonOne);
        mainPanel.add(labelRadioButtonTwo);
        mainPanel.add(labelRadioButtonThree);
        
        labelRadioButtonOne.setFont(fontForRadioLabels);
        labelRadioButtonTwo.setFont(fontForRadioLabels);
        labelRadioButtonThree.setFont(fontForRadioLabels);

        // Jbuttons for tune
        springLayout.putConstraint(SpringLayout.NORTH, playOneJButton, 72, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.WEST, playOneJButton, 150, SpringLayout.NORTH, mainPanel);
        
        springLayout.putConstraint(SpringLayout.NORTH, playTwoJButton, 102, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.WEST, playTwoJButton, 150, SpringLayout.NORTH, mainPanel);
        
        springLayout.putConstraint(SpringLayout.NORTH, playThreeJButton, 132, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.WEST, playThreeJButton, 150, SpringLayout.NORTH, mainPanel);
        
        mainPanel.add(playOneJButton);
        mainPanel.add(playTwoJButton);
        mainPanel.add(playThreeJButton);
        
        playOneJButton.setPreferredSize(dimensionJButton);
        playTwoJButton.setPreferredSize(dimensionJButton);
        playThreeJButton.setPreferredSize(dimensionJButton);
        
        // JButton to set tune to be global
        springLayout.putConstraint(SpringLayout.NORTH, setTuneButton, 180, SpringLayout.WEST, mainPanel);
        springLayout.putConstraint(SpringLayout.WEST, setTuneButton, 80, SpringLayout.NORTH, mainPanel);
        
        mainPanel.add(setTuneButton);
        
        
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        
        
        
    }
    
    
    // events for buttons
    
    {
        playOneJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(clipOne.isRunning())
                {
                    clipOne.stop();
                }else{
                    clipOne.loop(Clip.LOOP_CONTINUOUSLY);
                }
            }
        });
        playTwoJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(clipTwo.isRunning())
                {
                    clipTwo.stop();
                }else{
                    clipTwo.loop(Clip.LOOP_CONTINUOUSLY);
                }
            }
        });
        playThreeJButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(clipThree.isRunning())
                {
                    clipThree.stop();
                }else{
                    clipThree.loop(Clip.LOOP_CONTINUOUSLY);
                }
            }
        });
        setTuneButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if(radioButtonOne.isSelected())
                {
                    chooseClip = clipOne;
                }else if(radioButtonTwo.isSelected())
                {
                    chooseClip = clipTwo;
                }else{
                    chooseClip = clipThree;
                }
            }
        });
        // event for labels
        labelRadioButtonOne.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
                radioButtonOne.setSelected(true);
            }
        });
        labelRadioButtonTwo.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
                radioButtonTwo.setSelected(true);
            }
        });
        labelRadioButtonThree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                
                radioButtonThree.setSelected(true);
            }
        });
    }
    
    
    

    // import sounds
    {
        File soundFileOne = new File("sound 1.wav");
        File soundFileTwo = new File("sound 2.wav");
        File soundFileThree = new File("sound 3.wav");
        AudioInputStream audioIn;
                try {
                    audioIn = AudioSystem.getAudioInputStream(soundFileOne);
                    
                    clipOne = AudioSystem.getClip();
                    clipOne.open(audioIn);
                    // default sound is sound 1
                    chooseClip = clipOne;
                    
                    audioIn = AudioSystem.getAudioInputStream(soundFileTwo);
                    clipTwo = AudioSystem.getClip();
                    clipTwo.open(audioIn);
                    
                    audioIn = AudioSystem.getAudioInputStream(soundFileThree);
                    clipThree = AudioSystem.getClip();
                    clipThree.open(audioIn);
                } catch (UnsupportedAudioFileException ex) {
                    Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                } catch (LineUnavailableException ex) {
                    Logger.getLogger(SettingsPanel.class.getName()).log(Level.SEVERE, null, ex);
                }
    }


    
}
