 
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import PvP.PolarizedLadder;
import PvA.PolarizedLadder2;
import AvP.PolarizedLadder3;

 
public class run
{
  static JFrame frame;
  PvP.PolarizedLadder pvpgame = new PvP.PolarizedLadder();
  
 
  public static void main(String[] args)
  {
	 
    // schedule this for the event dispatch thread (edt)
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        displayJFrame();
      }
    });
  }
 
  static void displayJFrame()
  {
    frame = new JFrame("Polarized Ladeer Game");
 
    // create our jbutton
    JButton Button1 = new JButton("Player vs Player ");
    JButton Button2 = new JButton("AI vs Player ");
    JButton Button3 = new JButton("Player vs AI ");
     
    ImageIcon icon = new ImageIcon("1.jpg"); 
    JLabel thumb = new JLabel();
    thumb.setIcon(icon);
    
    
    JLabel thumb2 = new JLabel();
    thumb2.setText("Mode:");;
    
    // add the listener to the jbutton to handle the "pressed" event
    Button1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        // display/center the jdialog when the button is pressed
    	  PvP.PolarizedLadder.main(null);    	  
    	  
      }
    });
    Button2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        // display/center the jdialog when the button is pressed
    	  AvP.PolarizedLadder3.main(null);    	  
    	  
      }
    });
    Button3.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        // display/center the jdialog when the button is pressed
    	  PvA.PolarizedLadder2.main(null);    	  
    	  
      }
    });
 
    // put the button on the frame
    frame.getContentPane().setLayout(new FlowLayout());
    frame.add(thumb);
    frame.add(thumb2);
    frame.add(Button1);
    frame.add(Button2);
    frame.add(Button3);
 
    // set up the jframe, then display it
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(550, 420));
    frame.pack();
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}