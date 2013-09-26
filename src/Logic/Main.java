package Logic;

import Components.Tower;
import GUI.HanoiFrame;
import Repository.AnimationHandler;
import Repository.Variables;
import javax.swing.UIManager;

/*********************************************************
 * Written by: Simon Cicek                               *
 * Last changed: 2012-03-26                              *
 *                                                       *
 * The main class, initializing and starting everything. *
 *********************************************************/

public class Main 
{
    // Key Components
    public static Tower a,b,c;
    public static HanoiFrame hf;
    public static AnimationHandler ah = new AnimationHandler();
    
    public Main()
    {
        // Create towers and initialize the source tower
        a = new Tower(Variables.towerABase,Variables.towerAStick,"A");
        a.initializeDiscs(Variables.discCount);
        b = new Tower(Variables.towerBBase,Variables.towerBStick,"B");
        c = new Tower(Variables.towerCBase,Variables.towerCStick,"C");
        
        // Create the mapping between tower and instruction
        Variables.mappings.put("A", a);
        Variables.mappings.put("B", b);
        Variables.mappings.put("C", c);
        
        // Set the feel of the GUI
        try{UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
        catch (Exception e) {e.printStackTrace();}
        
        hf = new HanoiFrame();
    }
    
    public static void main(String[] args)
    {
        Main m = new Main();
        m.ah.start();
    }
}
