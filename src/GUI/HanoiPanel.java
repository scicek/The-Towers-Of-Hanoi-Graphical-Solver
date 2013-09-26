package GUI;

import Components.Disc;
import Components.Tower;
import Logic.Main;
import Repository.Variables;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/***********************************************************************
 * Written by: Simon Cicek                                             *
 * Last changed: 2012-03-26                                            *
 *                                                                     *
 * The graphical panel of the program, containing the methods required *
 * to animate the movement of the discs.                               * 
 ***********************************************************************/


public class HanoiPanel extends JPanel
{
    // Images for the animation components
    BufferedImage bg;
    BufferedImage stick;
    BufferedImage base;
    BufferedImage disc;
    
    public HanoiPanel()
    {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.lightGray);
        
        // Try to load the images
        try
        {
            bg = ImageIO.read(new File("Images/bg.jpg"));
            stick = ImageIO.read(new File("Images/stick.png"));
            base = ImageIO.read(new File("Images/base.png"));
            disc = ImageIO.read(new File("Images/disc.png"));
        }
        catch(Exception e){e.printStackTrace();}
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
        RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Draw the images for the animation components
        if(bg != null)
        {
            g2.drawImage(bg, null, 0, 0);
            g2.drawImage(stick, null, (int)Main.a.stick.getX(), 
                                      (int)Main.a.stick.getY()+1);            
            
            g2.drawImage(stick, null, (int)Main.b.stick.getX(), 
                                      (int)Main.b.stick.getY()+2);            
            
            g2.drawImage(stick, null, (int)Main.c.stick.getX(), 
                                      (int)Main.c.stick.getY()+2);            
            
            g2.drawImage(base, null, (int)Main.a.base.getX(), 
                                     (int)Main.a.base.getY()-1);            
            
            g2.drawImage(base, null, (int)Main.b.base.getX(), 
                                     (int)Main.b.base.getY()+1);            
            
            g2.drawImage(base, null, (int)Main.c.base.getX(), 
                                     (int)Main.c.base.getY()+1);            
        }
        
        // Draw the names of the towers
        g2.setColor(Color.white);
        g2.setFont(new Font("Arial",50,20));
        g2.drawString("Source", (int)Main.a.base.getCenterX()-30, 
                                (int)Main.a.base.getCenterY()+5);
        
        g2.drawString("Auxiliary", (int)Main.b.base.getCenterX()-35, 
                                   (int)Main.b.base.getCenterY()+7);
        
        g2.drawString("Destination", (int)Main.c.base.getCenterX()-41, 
                                     (int)Main.c.base.getCenterY()+8);
        
        // Draw all the discs for the Source tower
        for (Disc d : (ArrayList<Disc>)Main.a.discs.clone())  
        {     
            if(d != null)
            {
                Rectangle2D r = d.getRectangle();
                g2.drawImage(disc, (int)r.getX(), (int)r.getY(), (int)r.getWidth(), 
                                                                 (int)r.getHeight(), this);
            }
        }
        
        // Draw all the discs for the Auxiliary tower
        for (Disc d : (ArrayList<Disc>)Main.b.discs.clone())  
        {     
            if(d != null)
            {
                Rectangle2D r = d.getRectangle();
                g2.drawImage(disc, (int)r.getX(), (int)r.getY(), (int)r.getWidth(), 
                                                                 (int)r.getHeight(), this);
            }
        }
        // Draw all the discs for the Destination tower
        for (Disc d : (ArrayList<Disc>)Main.c.discs.clone())  
        {     
            if(d != null)
            {
                Rectangle2D r = d.getRectangle();
                g2.drawImage(disc, (int)r.getX(), (int)r.getY(), (int)r.getWidth(), 
                                                                 (int)r.getHeight(), this);
            }
        }
    }
    
    // Move the top disc from one tower to another
    public synchronized void moveDisc (Tower src, Tower des)
    {
        // Get the top disc from both towers
        Rectangle2D tmp = null;
        Rectangle2D r = null;
        if(des.getTopDisc() != null)
            tmp = des.getTopDisc().getRectangle();
        if(src.getTopDisc() != null)
            r = src.getTopDisc().getRectangle();
        
        // In case the source tower is empty, return
        if(r == null)
            return;
        
        // Define and execute movement of a disc
        while(r.getY() > src.stick.getY() - 30)
            animate(r,r.getX(),r.getY() - Variables.movSpeed);
        while(r.getCenterX() < des.stick.getCenterX())
            animate(r,r.getX()+ Variables.movSpeed,r.getY());
        while(r.getCenterX() > des.stick.getCenterX())
            animate(r,r.getX()- Variables.movSpeed,r.getY());
        while(!r.intersects(des.base))
        {
            animate(r,r.getX(),r.getY()+ Variables.movSpeed);
            
            // If an intersection occurs, abort the movement of the disc, 
            // update information and return
            if(tmp != null)
                if(r.intersects(tmp))
                {
                    r.setRect(r.getX(),tmp.getY()-r.getHeight(),r.getWidth(),r.getHeight());
                    des.addTopDisc(src.getTopDisc());
                    src.removeTopDisc();
                    Variables.moves += 1;
                    HanoiFrame.moves.setText("Total Moves: " + Variables.moves);
                    return;
                }
        }
        
        // If the disc intersects the destination base, update information
        Variables.moves += 1;
        HanoiFrame.moves.setText("Total Moves: " + Variables.moves);
        des.addTopDisc(src.getTopDisc());
        src.removeTopDisc();
    }
    
    // Animate movement of a disc
    private void animate (Rectangle2D r, double x,double y)
    {
        r.setRect(x, y, r.getWidth(), r.getHeight());
        try 
        {
            Thread.sleep(Variables.speed);
            repaint();
        } 
        catch (InterruptedException ex) {ex.printStackTrace();}
    }
}