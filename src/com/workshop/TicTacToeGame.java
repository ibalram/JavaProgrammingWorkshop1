package com.workshop;

import java.util.Arrays;

public class TicTacToeGame {
	private char[] board = new char[10];
	
	public char[] createBoard() {
		char[] board = new char[10];
		Arrays.fill(board,' ');
		return board;
	}

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		game.createBoard();
	}

}
