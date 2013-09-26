package GUI;

import Logic.Main;
import Logic.Solver;
import Repository.Variables;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**************************************************************************
 * Written by: Simon Cicek                                                *
 * Last changed: 2012-03-26                                               *
 *                                                                        *
 * The main frame of the program, containing the main graphical panel and * 
 * the control panel.                                                     *
 **************************************************************************/

public class HanoiFrame extends JFrame implements ActionListener, ChangeListener
{
    public static HanoiPanel hp = new HanoiPanel();
    
    // Control Field Components
    static int min  = 0;
    static int init = 0;
    static int max  = 20;
    static JSlider discCount = new JSlider(JSlider.HORIZONTAL,min,max,init);
    static JComboBox speed   = new JComboBox();
    static JButton play  = new JButton("Play");
    static JButton reset = new JButton("Reset");
    static JLabel moves  = new JLabel();
    static JLabel dl     = new JLabel("Number of Discs:");
    static JLabel sl     = new JLabel("Animation Speed:");
    JPanel ctrl   = new JPanel();
    JPanel movesP = new JPanel();
    Image icon    = Toolkit.getDefaultToolkit().getImage("Images/icon.gif");
    
    public HanoiFrame()
    { 
        // Frame properties
        this.setLayout(new BorderLayout());
        this.add(hp,BorderLayout.CENTER);
        this.setTitle("Simon Cicek - Towers of Hanoi Graphical Solver");
        this.setBackground(Color.yellow);
	this.setSize(1290,600);
	this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setIconImage(icon);
        
        // Add the components
        ctrl.add(dl);
        ctrl.add(discCount);
        ctrl.add(sl);
        ctrl.add(speed);
        ctrl.add(play);
        ctrl.add(reset);
        ctrl.setVisible(true);
        moves.setText("Total Moves: " + Variables.moves);
        movesP.add(moves);
        ctrl.add(movesP);
        this.add(ctrl,BorderLayout.SOUTH);
        
        // Edit Component: Speed
        speed.setEditable(false);
        speed.addItem("Fast");
        speed.addItem("Normal");
        speed.addItem("Slow");
        speed.setSelectedIndex(1);
        speed.setPreferredSize(new Dimension(60,20));
        speed.addActionListener(this);
        
        // Edit Component: Speed
        discCount.addChangeListener(this);
        discCount.setEnabled(!Variables.initialized);
        discCount.setMajorTickSpacing(5);
        discCount.setMinorTickSpacing(1);
        discCount.setPaintTicks(true);
        discCount.setPaintLabels(true);
        
        // Add ActionListeners
        reset.addActionListener(this);
        play.addActionListener(this);
    }
    
    // Reacting to input via the various buttons
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource() == reset)
        {
            speed.setSelectedIndex(1);
            discCount.setValue(0);
            Variables.setPlaying(false);
            reset();
        }
        else if (e.getSource() == play)
        {
            enableControls(false);
            Variables.setPlaying(true);
        }
        else if (e.getSource() == speed)
        {
            int index = speed.getSelectedIndex();
            if(index == 0)
            {
                Variables.speed = 1;
                Variables.movSpeed = 1;
            }
            else if (index == 1)
            {
                Variables.speed = 2;
                Variables.movSpeed = 1;
            }
            else if (index == 2)
            {
                Variables.speed = 1;
                Variables.movSpeed = 0.1;
            }
        }
    }

    // Reacting to input via the slider
    @Override
    public void stateChanged(ChangeEvent e) 
    {
        JSlider source = (JSlider)e.getSource();
        reset();
        int d = (int) source.getValue();
        Main.a.reset();
        Main.a.initializeDiscs(d);
        Variables.discCount = d;
        hp.repaint();
        Solver.solve(Variables.discCount, Main.a.label, Main.b.label, Main.c.label);
        play.setEnabled(true);
    } 
    
    // Reset the program
    public static void reset()
    {
        Variables.setPlaying(false);
        Main.a.reset();
        Main.b.reset();
        Main.c.reset();
        Variables.from.clear();
        Variables.to.clear();
        Variables.discCount = 0;
        Variables.moves = 0;
        HanoiFrame.moves.setText("Total Moves: " + Variables.moves);
        hp.repaint();
        enableControls(true);
    }
    
    // Enable/Disable the controlfield
    public static void enableControls(boolean e)
    {
        play.setEnabled(e);
        speed.setEnabled(e);
        discCount.setEnabled(e);
    }
}