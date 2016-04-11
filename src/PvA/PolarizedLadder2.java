package PvA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;



@SuppressWarnings("serial")
public class PolarizedLadder2 extends JFrame implements MouseListener, MouseMotionListener {
	public JPanel chessBoard;
	public JLayeredPane layeredPane;
	public Player yellow_player = new Player("yellow", false, 1);
	public Player black_player = new Player("black", false, 1);
	public String[][] gameResult = new String[8][14];
	public static String[] board_init = new String[] { "7 ", "0", "0", "0", "0", "0", "0", "1", "0", "0", "0", "0", "0",
			"0", "6 ", "0", "0", "0", "0", "0", "1", "1", "1", "0", "0", "0", "0", "0", "5 ", "0", "0", "0", "0", "1",
			"1", "1", "1", "1", "0", "0", "0", "0", "4 ", "0", "0", "0", "1", "1", "1", "1", "1", "1", "1", "0", "0",
			"0", "3 ", "0", "0", "1", "1", "1", "1", "1", "1", "1", "1", "1", "0", "0", "2 ", "0", "1", "1", "1", "1",
			"1", "1", "1", "1", "1", "1", "1", "0", "l ", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1",
			"1", "  ", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M" };
	public int rolecount = 0;

	/* Constructor to setup GUI components */
	public PolarizedLadder2() {
		Dimension boardSize = new Dimension(840, 480);

		layeredPane = new JLayeredPane();
		getContentPane().add(layeredPane);
		layeredPane.setPreferredSize(boardSize);
		layeredPane.addMouseListener(this);
		layeredPane.addMouseMotionListener(this);

		chessBoard = new JPanel();
		layeredPane.add(chessBoard, JLayeredPane.DEFAULT_LAYER);
		chessBoard.setLayout(new GridLayout(8, 14));
		chessBoard.setPreferredSize(boardSize);
		chessBoard.setBounds(0, 0, boardSize.width, boardSize.height);

		for (int i = 0; i < 112; i++) {
			JPanel square = new JPanel(new BorderLayout());
			chessBoard.add(square);
			if (board_init[i].equals("1")) {
				square.setBackground(Color.white);
				square.setBorder(BorderFactory.createLineBorder(Color.black));
			} else if (board_init[i].equals("0")) {
				square.setBackground(Color.WHITE);
			} else {
				square.setBackground(Color.WHITE);
				JLabel jlabel = new JLabel(board_init[i].toString(), SwingConstants.CENTER);
				square.add(jlabel);
			}
		}
	}

	public void store2DResult(String[] resultin, int x, int y) {
		int k = 0;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 14; j++) {
				this.gameResult[i][j] = resultin[k];
				k++;
			}
		}
		gameVerifier(x, y);
	}

	public void gameVerifier(int clickedx, int clickedy) {

		// left polarizedladder test
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 14; j++) {
				if (this.gameResult[i][j].equals(this.gameResult[clickedx][clickedy])) {
					if (checkPolarized(i, j, gameResult[clickedx][clickedy].toString()) == true) {
						String popup = gameResult[clickedx][clickedy].toString() + "  Wins!";
						JOptionPane.showMessageDialog(this.chessBoard, popup, "Game Over", JOptionPane.WARNING_MESSAGE);

					}

				}
			}
		}

	}

	private boolean checkPolarized(int x, int y, String role) {

		// TODO Auto-generated method stub
		boolean checkPolarized = false;

		if (this.gameResult[x][y].equals(role) && this.gameResult[x + 1][y].equals(role)
				&& this.gameResult[x + 1][y + 1].equals(role) && this.gameResult[x + 2][y + 1].equals(role)
				&& this.gameResult[x + 2][y + 2].equals(role)) {
			if (neutralized(role, x, y) == false) {
				checkPolarized = true;
			}

		}

		if (this.gameResult[x][y].equals(role) && this.gameResult[x + 1][y].equals(role)
				&& this.gameResult[x + 1][y - 1].equals(role) && this.gameResult[x + 2][y - 1].equals(role)
				&& this.gameResult[x + 2][y - 2].equals(role)) {
			if (neutralized(role, x, y) == false) {
				checkPolarized = true;
			}

		}

		return checkPolarized;

	}

	private boolean neutralized(String role, int x, int y) {
		// TODO Auto-generated method stub
		String counterplayer = "";
		if (role.equals("black")) {
			counterplayer = "yellow";
		}

		if (role.equals("yellow")) {
			counterplayer = "black";
		}

		if ((this.gameResult[x + 2][y].equals(counterplayer) && this.gameResult[x][y + 2].equals(counterplayer))
				|| ((this.gameResult[x + 2][y].equals(counterplayer)
						&& this.gameResult[x][y - 2].equals(counterplayer)))) {
			return true;
		} else {
			return false;
		}

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int x = arg0.getX() / 60;
		int y = arg0.getY() / 60;
		;
		int role = rolecount % 2;
		int clickedsquare = x + y * 14;
		MiniMax minmax = new MiniMax();
		if (PolarizedLadder2.board_init[clickedsquare].equals("1")) {
			this.chessBoard.getComponent(clickedsquare).setBackground(Color.black);
			PolarizedLadder2.board_init[clickedsquare] = "black";
			// rolecount++;
			store2DResult(PolarizedLadder2.board_init, y, x);
			int[] result = minmax.minimax(1, yellow_player, gameResult, yellow_player, black_player, y, x);
			int colorChange = result[1] * 14 + result[2];
			this.chessBoard.getComponent(colorChange).setBackground(Color.yellow);
			PolarizedLadder2.board_init[colorChange] = "yellow";
			store2DResult(PolarizedLadder2.board_init, y, x);

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

	public static void main(String[] args) {
		JFrame frame = new PolarizedLadder2();
		frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}