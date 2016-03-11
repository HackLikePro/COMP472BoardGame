import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


@SuppressWarnings("serial")
public class PolarizedLadder extends JFrame implements MouseListener, MouseMotionListener{
	   public JPanel chessBoard;
	   public JLabel chessPiece;
	   public JLayeredPane layeredPane;
	   public static String[] multi = new String[]
		   {"7 ", "0", "0", "0", "0", "0", "0", "1", "0", "0", "0", "0","0","0",
		    "6 ", "0", "0", "0", "0", "0", "1", "1", "1", "0", "0", "0","0","0",
		    "5 ", "0", "0", "0", "0", "1", "1", "1", "1", "1", "0", "0","0","0",
		    "4 ", "0", "0", "0", "1", "1", "1", "1", "1", "1", "1", "0","0","0",
		    "3 ", "0", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1","0","0",
		    "2 ", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1","1","0",
		    "1 ", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1","1","1",
		    "  ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K","L","M"}; 
	  public int rolecount = 0;
	  
   /* Constructor to setup GUI components */
   public PolarizedLadder() {
	   Dimension boardSize = new Dimension(840, 480);
	   
	  
	   layeredPane = new JLayeredPane();
	   getContentPane().add(layeredPane);
	   layeredPane.setPreferredSize(boardSize);
	   layeredPane.addMouseListener(this);
	   layeredPane.addMouseMotionListener(this);

	   chessBoard = new JPanel();
	   layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
	   chessBoard.setLayout( new GridLayout(8, 14));
	   chessBoard.setPreferredSize( boardSize );
	   chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);
	  
	   for(int i = 0 ; i < 112; i++){
		    JPanel square = new JPanel( new BorderLayout() );
		    chessBoard.add(square);
		    if(multi[i].equals("1")){
		    	square.setBackground(Color.RED);
		    	square.setBorder(BorderFactory.createLineBorder(Color.black));
		    }
		    else if(multi[i].equals("0")){
		    	square.setBackground(Color.WHITE);
		    }
		    else{
		    	square.setBackground(Color.WHITE);
		    	JLabel jlabel = new JLabel(multi[i].toString(),SwingConstants.CENTER);		    	
		    	square.add(jlabel);
		    }
	   }
	  
	   
	   //Add a few pieces to the board
	 
      
   }
 
   
   public static void main(String[] args) {
      
      JFrame frame = new PolarizedLadder();
      frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE );
      frame.pack();
      frame.setResizable(true);
      frame.setLocationRelativeTo( null );
      frame.setVisible(true);
   }


	

	
	
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int x = arg0.getX()/60;
		int y = arg0.getY()/60;;
	    int role = rolecount % 2; 
		int clickedsquare = x + y*14;
		System.out.println(rolecount);
		if( this.multi[clickedsquare].equals("1") && role == 0)
		{
		this.chessBoard.getComponent(clickedsquare).setBackground(Color.BLACK);
		this.multi[clickedsquare] = "black";
		rolecount++;
		}
		else if( this.multi[clickedsquare].equals("1") && role == 1) 
		{
		this.chessBoard.getComponent(clickedsquare).setBackground(Color.yellow);
		this.multi[clickedsquare] = "yellow";
		rolecount++;
		}
		
	}


	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	   
	   
   
}