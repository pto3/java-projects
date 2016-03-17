import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.text.DefaultCaret;
import javax.swing.JApplet;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
public class GUI extends JPanel implements ActionListener
{
    /** declare game object to be used by GUI */
    private Game g;

    /** JButtons */
    private JButton north;
    private JButton south;
    private JButton east;
    private JButton west;
    private JButton southeast;
    private JButton northwest;

   
    private JButton help;
    private JButton pickup;
    private JButton drop;
    private JButton backup;
    private JButton eat;
    private JButton show;
    private JButton inv;
    private JButton turnOnPower;
    private JButton useComputer;
    private JButton search;

    /** results box */
    private JTextArea results;
    private JFrame theGUI;

    /** menu items */
    private JMenuBar menus;
    private JMenu fileMenu;
    private JMenuItem quitItem;
    private JMenuItem newGame; 


    public static void main(String arg[]){

        new GUI();

    }

    /*********************************************************************
    Constructor - instantiates and displays all of the GUI commponents
     *********************************************************************/
    public GUI(){ 
        g = new Game();
        g.setIntroMessage();
        theGUI = new JFrame("Escape the shelter");
        theGUI.setVisible(true);
        theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the Results Area for the Center area
        results = new JTextArea(30,60);
        JScrollPane scrollPane = new JScrollPane(results);
        results.setLineWrap(true);
        results.setWrapStyleWord(true);

        DefaultCaret caret = (DefaultCaret) results.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        theGUI.add(BorderLayout.CENTER, scrollPane);

        // set up File menus
        setupMenus();

        // set up selection
        //setupSelection();

        // set up buttons
        setupButtons ();
        theGUI.pack();
        
        //configure beginning of game messages
        results.setText(g.getMessage());
        g.show();
        results.append(g.getMessage());

    }

    /*********************************************************************
    Set up the menu items
     *********************************************************************/
    private void setupMenus(){

        // create menu components
        fileMenu = new JMenu("File");
        quitItem = new JMenuItem("Quit");
        newGame = new JMenuItem("New");

        // assign action listeners        
        quitItem.addActionListener(this);
        newGame.addActionListener(this);

        // display menu components
        fileMenu.add(newGame);
        fileMenu.add(quitItem);   
        menus = new JMenuBar();

        menus.add(fileMenu);
        theGUI.setJMenuBar(menus);
    }   

    /********************************************************
     *  Set up the Buttons declare buttons, add them to the different panels, add panels to layouts add buttons to action listener
     **********************************************************/
    private void setupButtons()
    {
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));

        
        help = new JButton ("Help");
        pickup = new JButton ("Pickup");
        drop = new JButton ("Drop");
        backup = new JButton ("Backup");
        show = new JButton ("Show");
        eat = new JButton ("Eat");
        inv = new JButton ("Inventory");
        turnOnPower = new JButton ("Turn On Power");
        useComputer = new JButton ("Use Computer");
        search = new JButton ("Search");
        
        
        
        
        actionPanel.add(new JLabel("Actions"));
        
        actionPanel.add(help);
        actionPanel.add(pickup);
        actionPanel.add(drop);
        actionPanel.add(backup);
        actionPanel.add(show);
        actionPanel.add(eat);
        actionPanel.add(inv);
        actionPanel.add(turnOnPower);
        actionPanel.add(useComputer);
        actionPanel.add(search);

        
        help.addActionListener(this);
        pickup.addActionListener(this);
        drop.addActionListener(this);
        backup.addActionListener(this);
        show.addActionListener(this);
        eat.addActionListener(this);
        inv.addActionListener(this);
        turnOnPower.addActionListener(this);
        useComputer.addActionListener(this);
        search.addActionListener(this);

        theGUI.add(BorderLayout.SOUTH, actionPanel);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

        north = new JButton ("North");
        south = new JButton ("South");
        east = new JButton ("East");
        west = new JButton ("West");
        
        northwest = new JButton ("Northwest");
        southeast = new JButton ("Southeast");
        
        north.setBackground(Color.blue);
        east.setBackground(Color.red);
        west.setBackground(Color.green);
        south.setBackground(Color.orange);

        eastPanel.add(new JLabel("Directions"));
        eastPanel.add(north);
        eastPanel.add(south);
        eastPanel.add(west);
        eastPanel.add(east);
        eastPanel.add(southeast);
        eastPanel.add(northwest);

        north.addActionListener(this);
        south.addActionListener(this);
        east.addActionListener(this);
        west.addActionListener(this);
        southeast.addActionListener(this);
        northwest.addActionListener(this);

        theGUI.add(BorderLayout.EAST, eastPanel);
    }

    //used at each movement action to indicate if the game is complete
    private void gameOver(){
        if(g.gameOver() == true){
            
            help.setEnabled(false);
            pickup.setEnabled(false);
            drop.setEnabled(false);
            backup.setEnabled(false);
            show.setEnabled(false);
            inv.setEnabled(false);
            eat.setEnabled(false);
            useComputer.setEnabled(false);
            turnOnPower.setEnabled(false);
            search.setEnabled(false);
            north.setEnabled(false);
            south.setEnabled(false);
            east.setEnabled(false);
            west.setEnabled(false);
            northwest.setEnabled(false);
            southeast.setEnabled(false);
        }        
    }

    //method used by the new button in the menu to restart the game
    private void newGame(){
        g = new Game();
        g.setIntroMessage();
        results.setText(g.getMessage());
        
        help.setEnabled(true);
        pickup.setEnabled(true);
        drop.setEnabled(true);
        backup.setEnabled(true);
        show.setEnabled(true);
        inv.setEnabled(true);
        eat.setEnabled(true);
        useComputer.setEnabled(true);
        turnOnPower.setEnabled(true);
        search.setEnabled(true);
        north.setEnabled(true);
        south.setEnabled(true);
        east.setEnabled(true);
        west.setEnabled(true);
        northwest.setEnabled(true);
        southeast.setEnabled(true);
    }

    /*********************************************************************
     *Responds to menu selections and button clicks
     *
     *@param e the button or menu item that was selected
     * *********************************************************************/

    public void actionPerformed(ActionEvent e){
    {
        // menu item - quit
        if (e.getSource() == quitItem)
        {
            System.exit(1);   
        }

        if (e.getSource() == eat){
            String message = "What do you want to eat?";
            String toEat = JOptionPane.showInputDialog(null, message);
            g.eat(toEat);
            results.append(g.getMessage());
        }

        if (e.getSource() == help){
            g.help();
            results.append(g.getMessage());
        }

        if (e.getSource() == north){
            g.move("north");
            gameOver();
            results.append(g.getMessage());
        }

        if (e.getSource() == south){
            g.move("south");
            gameOver();    
           results.append(g.getMessage());
        }

        if (e.getSource() == east){
            g.move("east");
            gameOver();
            results.append(g.getMessage());            
        }

        if (e.getSource() == west){
            g.move("west");
            gameOver();            
            results.append(g.getMessage());
        }
        
        if (e.getSource() == northwest){
            g.move("northwest");
            gameOver();
            results.append(g.getMessage());
        }
        
        if (e.getSource() == southeast){
            g.move("southeast");
            gameOver();
            results.append(g.getMessage());
        }

        if (e.getSource() == newGame){
            newGame();
        }

        if (e.getSource() == backup){
            g.backup();
            results.append(g.getMessage());
        }

        if (e.getSource() == show){
            g.show();
            results.append(g.getMessage());
        }

        if (e.getSource() == inv){
            g.inventory();
            results.append(g.getMessage());
        }

        if (e.getSource() == pickup){
            g.pickup();
            results.append(g.getMessage());
        }

        if (e.getSource() == drop) {
            String message = "What do you want to Drop?";
            String toDrop = JOptionPane.showInputDialog(null, message);
            g.drop(toDrop);
            results.append(g.getMessage());
        }

        if (e.getSource() == useComputer) {
            g.useComputer();
            results.append(g.getMessage());
        }
        
        if (e.getSource() == turnOnPower) {
            g.turnOnPower();
            results.append(g.getMessage());
        }
        
        if (e.getSource() == search) {
            g.search();
            results.append(g.getMessage());
        }
}
}
}
