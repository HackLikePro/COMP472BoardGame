

public class Player {
	public String color;
	public boolean winLose;
	public int turn;
	
	
	public Player(String color, boolean winLose, int turn) {
		this.color = color;
		this.winLose = winLose;
		this.turn = turn;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public boolean isWinLose() {
		return winLose;
	}

	public void setWinLose(boolean winLose) {
		this.winLose = winLose;
	}

	public int getTurn() {
		return turn;
	}

	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	
}
