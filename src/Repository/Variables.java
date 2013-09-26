package Repository;

import java.util.ArrayList;
import java.util.HashMap;

/***************************************************************************
 * Written by: Simon Cicek                                                 *
 * Last changed: 2012-03-26                                                *
 *                                                                         *
 * A utility class containing crucial information. The rest of the classes *
 * rely on this class for accurate values and shared information.          *
 ***************************************************************************/

public class Variables 
{
    // Utility variables
    public static HashMap mappings = new HashMap();
    public static ArrayList<String> from = new ArrayList();
    public static ArrayList<String> to = new ArrayList();
    public static boolean initialized = false;
    public static boolean play = false;
    public static long speed = 2;
    public static double movSpeed = 1;
    public static int discCount = 0;
    public static int moves = 0;
    public static int discHeight = 15;
    
    // Tower width/height
    public static int towerBaseWidth = 400;
    public static int towerBaseHeight = 25;
    public static int towerStickWidth = 20;
    public static int towerStickHeight = 400;
    
    // Tower X-coordinates
    public static int towerABaseX = 20;
    public static int towerAStickX = 160;
    public static int towerBBaseX = 440;
    public static int towerBStickX = 630;
    public static int towerCBaseX = 865;
    public static int towerCStickX = 1060;
    
    // Tower Y-coordinates
    public static int towerABaseY = 450;
    public static int towerAStickY = 50;
    public static int towerBBaseY = 450;
    public static int towerBStickY = 50;
    public static int towerCBaseY = 450;
    public static int towerCStickY = 50;
    
    // Vectors containing information about each tower
    public static int[] towerABase = {towerABaseX,towerABaseY,towerBaseWidth,towerBaseHeight};
    public static int[] towerAStick = {towerAStickX,towerAStickY,towerStickWidth,towerStickHeight};
    public static int[] towerBBase = {towerBBaseX,towerBBaseY,towerBaseWidth,towerBaseHeight};
    public static int[] towerBStick = {towerBStickX,towerBStickY,towerStickWidth,towerStickHeight};
    public static int[] towerCBase = {towerCBaseX,towerCBaseY,towerBaseWidth,towerBaseHeight};
    public static int[] towerCStick = {towerCStickX,towerCStickY,towerStickWidth,towerStickHeight};
  
    // Private constructor implies a static class
    private Variables(){}
    
    // Check if the animation is playing
    public static boolean playing ()
    {
        return play;
    }
    
    // Only one thread at a time is allowed to change the condition of the animation
    public synchronized static void setPlaying (boolean choice)
    {
        play = choice;
    }
}
