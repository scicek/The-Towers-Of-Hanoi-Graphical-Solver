
package Logic;

import Repository.Variables;

/****************************************************************
 * Written by: Simon Cicek                                      *
 * Last changed: 2012-03-26                                     *
 *                                                              *
 * The class containing the algorithm used to solve the puzzle. *
 ****************************************************************/

public class Solver
{
    public static void solve (int discs, String a, String b, String c)
    {
        // If the number of discs to be moved is zero, then we are done and return
        if(discs > 0)
        {
            solve(discs - 1, a, c, b);
            // Add the instruction
            Variables.from.add(a);
            Variables.to.add(c);
            solve(discs - 1, b, a, c);
        }
    }
}
