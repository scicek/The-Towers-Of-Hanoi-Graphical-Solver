package Components;

import Repository.Variables;
import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/*****************************************************************************
 * Written by: Simon Cicek                                                   *
 * Last changed: 2012-03-26                                                  *
 *                                                                           *
 * The class representing the towers. Contains methods to initiate-, clear-, *
 * -add-to-top, -remove-from-top and get-from-top-discs.                     *
 * The backbone of the program.                                              *
 *****************************************************************************/

public class Tower 
{
    // Necessary components of the tower
    public Rectangle2D base;
    public Rectangle2D stick;
    public ArrayList<Disc> discs = new ArrayList();
    public String label;
    
    public Tower(int[] b, int[] s, String l)
    {
        label = l;
        base = new Rectangle2D.Float(b[0], b[1] , b[2], b[3]); 
        stick = new Rectangle2D.Float(s[0], s[1] , s[2], s[3]); 
    }
    
    public void initializeDiscs(int discCount)
    {
        // Relocate the stick to the center of the base
        stick.setRect(base.getCenterX()-10, 
                                           stick.getY(), 
                                           stick.getWidth(), 
                                           stick.getHeight());
        // If the method is called to initialize a tower with 0 discs, return
        if(discCount == 0)
            return;
        
        // Create and add the specified amount of discs
        for(int i = 0; i <= discCount; i++)
        {
            if(i < 1)
                i = 1;
            int x = (int) stick.getX() - i*9-10;
            int y = (int) base.getY()+i*15-discCount*15 - 16;
            discs.add(new Disc(new Rectangle2D.Double(x+10, y, 
                                                     (i*18+20), Variables.discHeight),
                                                      new Color(150+i*1, 75, 0)));
        }
        
        // Declare that a tower has been initialized
        Variables.initialized = true;
    }
    
    // Remove the disc at the top
    public void removeTopDisc ()
    {
        if(!discs.isEmpty())
            discs.remove(0);
    }
    
    // Add the specified disc to the top
    public void addTopDisc (Disc d)
    {
        discs.add(0, d);
    }
    
    // Get the top disc
    public Disc getTopDisc ()
    {
        if(!discs.isEmpty())
            return discs.get(0);
        else
            return null;
    }
    
    // Reset the tower (clear it of discs)
    public void reset()
    {
        discs.clear();
        Variables.initialized = false;
    }
}
