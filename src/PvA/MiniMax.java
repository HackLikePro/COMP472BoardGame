package PvA;

import java.util.*;

public class MiniMax {

	public int[] minimax(int depth, Player player, String gameResult[][], Player yellow_Player, Player black_Player,
			int y, int x) {
		// Generate possible next moves in a List of int[2] of {row, col}.
		List<int[]> nextMoves = generateMoves(gameResult);

		//
		int bestScore = (player.getColor() == "yellow") ? Integer.MIN_VALUE : Integer.MAX_VALUE;
		int currentScore = 0;
		int bestRow = 0;
		int bestCol = 0;

		if (nextMoves.isEmpty() || depth == 0) {
			// Gameover or depth reached, evaluate score
			bestScore = evaluate(gameResult, y, x, player);

		} else {
			for (int[] move : nextMoves) {
				// Try this move for the current "player"
				// System.out.print(gameResult[move[0]][move[1]]);
				gameResult[move[0]][move[1]] = player.getColor();
				// System.out.print(" ( ||"+move[0]+" "+move[1]+"|| ) ");
				if (player.getColor().equals("black")) { // mySeed (computer) is
															// maximizing player
					currentScore = minimax(depth - 1, yellow_Player, gameResult, yellow_Player, black_Player, y, x)[0];
					if (currentScore < bestScore) {
						bestScore = currentScore;
						bestRow = move[0];
						bestCol = move[1];

					}
				} else { // oppSeed is minimizing player
					int[] result = minimax(depth - 1, black_Player, gameResult, yellow_Player, black_Player, y, x);
					currentScore = result[0];

					if (currentScore > bestScore) {
						bestScore = currentScore;
						// System.out.print(move[0] + "||" + move[1] + "||");
						bestRow = move[0];
						bestCol = move[1];
					}
				}
				// Undo move
				gameResult[move[0]][move[1]] = "1";

			}

		}

		return new int[] { bestScore, bestRow, bestCol };
	}

	private int evaluate(String[][] gameResult, int row, int col, Player player) {
		// TODO Auto-generated method stub
		int score = 0;
		int leftorRight = 0;
		String color = player.getColor();

		String orignal = gameResult[row][col];
		String o_d_1 = gameResult[row + 1][col];
		String o_u_1 = gameResult[row - 1][col];

		// System.out.print(player.getColor());

		if (player.getColor() == "black") {
			// System.out.print(o_d_1);
			// 1

			if (o_d_1 == "1") {
				score -= 1;
			}
			if (o_u_1 == "1" && o_d_1 == "black") {
				score -= 1;
			}

			// 2
			if (row < 7) {
				String o_d_1_r_1 = gameResult[row + 1][col + 1];
				// String o_d_1_r_2 = gameResult[row + 1][col + 2];
				// String o_d_2_r_2 = gameResult[row + 2][col + 2];
				String o_d_1_l_1 = gameResult[row + 1][col - 1];
				String o_d_1_l_2 = gameResult[row + 1][col - 2];
				// String o_d_2_l_2 = gameResult[row + 2][col - 2];

				// String cross_p = gameResult[row + 2][col];
				String cross_p_l = gameResult[row][col + 2];
				String cross_p_r = gameResult[row][col + 2];

				String o_u_1_l_1 = gameResult[row - 1][col - 1];
				String o_u_2_l_1 = gameResult[row - 2][col - 1];
				String o_u_1_r_1 = gameResult[row - 1][col + 1];
				String o_u_2_r_1 = gameResult[row - 2][col + 1];
				String o_l = gameResult[row][col - 1];
				String o_r = gameResult[row][col + 1];
				if (o_d_1 == "black" && (o_d_1_r_1 == "1" || o_d_1_l_1 == "1")) {
					score -= 15;
					// System.out.print(o_d_1);
				}

				if (o_u_1 == "black" && (o_l == "1" || o_r == "1")) {
					score -= 15;
					// System.out.print(o_d_1);
				}

				if ((o_l == "black" || o_r == "black") && o_u_1 == "1") {
					score -= 15;
				}

				// 3

				if (o_l == "black" && o_d_1 == "black" && o_d_1_r_1 == "1") {
					score -= 16;
				}

				if (o_r == "black" && o_d_1 == "black" && o_d_1_l_1 == "1") {
					score -= 16;
				}

				if (o_u_1 == "black" && o_u_1_r_1 == "black" && o_u_2_r_1 == "1") {
					score -= 16;
				}

				if (o_u_1 == "black" && o_u_1_l_1 == "black" && o_u_2_l_1 == "1") {
					score -= 16;
				}

				// 4

				if (o_l == "black" && o_d_1 == "black" && o_d_1_r_1 == "black" && o_u_1_l_1 == "1") {
					score -= 17;
				}
				if (o_r == "black" && o_d_1 == "black" && o_d_1_l_1 == "black" && o_u_1_r_1 == "1") {
					score -= 17;
				}
			}

		}
		
		if(player.getColor() == "yellow"){
			int count = 9;
			for (int i = row - 1 ; i < row + 1 ; i++)
			{
				for (int k= col - 1 ; k < col - 1; col++)
				{
					if (gameResult[i][k] == "black")
					{
						count--;
					}
				}
			}
			return score;
		}

		return score;
	}

	private List<int[]> generateMoves(String gameResult[][]) {
		List<int[]> nextMoves = new ArrayList<int[]>(); // allocate List

		// If gameover, i.e., no next move
		/*
		 * if (hasWon(mySeed) || hasWon(oppSeed)) { return nextMoves; // return
		 * empty list }
		 */

		int count = 0;
		// Search for empty cells and add to the List
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 14; col++) {
				if (gameResult[row][col].contains("1")) {

					nextMoves.add(new int[] { row, col });

				}
			}
		}
		nextMoves.size();

		return nextMoves;
	}
}
