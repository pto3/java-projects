import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/***************************************************************
 * GUI front end to a game of Craps
 * 
 * @author Parker O'Brien

 ***************************************************************/
public class CrapsGUI extends JFrame implements ActionListener{

    /** visual representation of the dice */
    private GVDie d1, d2;

    /** buttons */
    private JButton comeOutButton, rollButton;

    /** labels for message and credits */
    private JLabel message, credits;

    /** the game of craps object */
    private Craps game;    

    /****************************************************************
     Create all elements and display within the GUI
     ****************************************************************/        
    public static void main(String arg[]){
        CrapsGUI gui = new CrapsGUI();
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setTitle("Parker's Game of Craps");
        gui.pack();
        gui.setVisible(true);
    }

    /****************************************************************
     Create all elements and display within the GUI
     ****************************************************************/    
    public CrapsGUI(){ 

        // create the game object as well as the GUI Frame   
        this.game = new Craps();
        

        // set the layout to GridBag
        setLayout(new GridBagLayout());
        GridBagConstraints position = new GridBagConstraints();

        // create and place the this.message label
        this.message = new JLabel();
        this.message.setText(this.game.getMessage());
        position.gridx = 1;
        position.gridy = 1;
        add(this.message, position);

        // get Die #1 from this.game and place on frame
        this.d1 = this.game.getDie(1);
        position.gridx = 0;
        position.gridy = 1;
        add(this.d1, position);

        // FIX ME: get Die #2 from game and place on frame
        this.d2 = this.game.getDie(2);
        position.gridx = 2;
        position.gridy = 1;
        add(this.d2, position);

        // create and place the Come Out button
        this.comeOutButton = new JButton("Come Out");
        position.gridx = 0;
        position.gridy = 3;
        add(this.comeOutButton, position);

        // FIX ME: create and place the Roll button
        this.rollButton = new JButton("Roll");
        position.gridx = 2;
        position.gridy = 3;
        add(this.rollButton, position);
        
    

        // create and position the Credits label
        this.credits = new JLabel();
        this.credits.setText("Credits: " + this.game.getCredits());
        position.gridx = 1;
        position.gridy = 2;
        add(this.credits, position);

        // FIX ME: register the listeners
        rollButton.addActionListener(this);
        comeOutButton.addActionListener(this);
    }


    /****************************************************************
     Inner class to repond to the user action

     @param e - the JComponent just selected
     ****************************************************************/
    public void actionPerformed(ActionEvent event){

        // FIX ME: to test for roll and then game.roll()
        if (event.getSource() == rollButton)
        {
            game.roll();
            
        }
        

        // FIX ME: test for come out and then game.comeOut()
        if (event.getSource() == comeOutButton)
        {
            game.comeOut();
            
        }

        // FIX ME: enable/disable each button based on status of game
        if (game.toRoll == 0 && game.toComeOut == 1)
        {
            rollButton.setEnabled(true);
            comeOutButton.setEnabled(false);
        }
        else if (game.toComeOut == 0 && game.toRoll == 1) 
        {
            rollButton.setEnabled(false);
            comeOutButton.setEnabled(true);
        }
        // FIX ME: update the labels
        credits.setText("Credits: " + game.getCredits());
        message.setText(game.getMessage());
    }
}
