
/**
 * Write a description of class CrapsTest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CrapsTest
{
    public static void main(String [] args)
    {
        Craps game = new Craps();
        System.out.println(game.getMessage());
        game.comeOut();
        System.out.println(game.getMessage());
        while (game.okToRoll())
        {
            game.roll();
            System.out.println(game.getMessage());
        }
        System.out.println(game.getCredits());
    }
    
}
