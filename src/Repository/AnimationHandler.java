package Repository;

import Components.Tower;
import GUI.HanoiFrame;
import Logic.Main;

/*****************************************************************
 * Written by: Simon Cicek                                       *
 * Last changed: 2012-03-26                                      *
 *                                                               *
 * The class that manages the animation in it's very own thread. *
 *****************************************************************/

public class AnimationHandler extends Thread
{
    @Override
    public void run() 
    {
        // Needs to be running at all time, waiting to animate movement
        while(true)
        {
            // Check if the play button has been pressed
            if(Variables.playing())
            {
                // Move a disc according to the instructions
                for(int i = 0; i < Variables.from.size();i++)
                {
                    if(Variables.playing())
                        Main.hf.hp.moveDisc((Tower)Variables.mappings.get(Variables.from.get(i)), 
                                            (Tower)Variables.mappings.get(Variables.to.get(i)));
                }
                // Clear the instructions an enable the control field
                Variables.from.clear();
                Variables.to.clear();
                HanoiFrame.enableControls(true);
            }
            else
                this.yield();
        }
    } 
}
