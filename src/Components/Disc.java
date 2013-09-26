package Components;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/*************************************
 * Written by: Simon Cicek           *
 * Last changed: 2012-03-26          *
 *                                   *
 * The class representing the discs. *
 ***'*********************************/


public class Disc 
{
    Rectangle2D rec;
    Color color;
    
    public Disc(Rectangle2D r, Color c)
    {
        rec = r;
        color = c;
    }
    
    public Rectangle2D getRectangle()
    {
        return rec;
    }
    
    public void seRectangle(Rectangle2D r)
    {
        rec = r;
    }
    
    public Color getColor()
    {
        return color;
    }
}
