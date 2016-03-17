
/**
 * Write a description of class Test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Test
{
    public static void main (String args[]){
        Game testGame = new Game();
        testGame.setIntroMessage();
        System.out.println(testGame.getMessage());
        testGame.move("north");
        System.out.println(testGame.getMessage());
        testGame.move("west");
        System.out.println(testGame.getMessage());
        testGame.turnOnPower();
        System.out.println(testGame.getMessage());        
        testGame.move("east");
        System.out.println(testGame.getMessage());        
        testGame.useComputer();
        System.out.println(testGame.getMessage());
        testGame.move("east");
        System.out.println(testGame.getMessage());
        testGame.search();
        System.out.println(testGame.getMessage());
        testGame.move("north");
        System.out.println(testGame.getMessage());
        testGame.move("east");
        
        

        if(testGame.gameOver()){
            System.out.println(testGame.getMessage());
        }
    }
}
