package com.workshop;

import java.util.Arrays;

public class TicTacToeGame {
	private char[] board = new char[10];

	public void createBoard() {
		char[] board = new char[10];
		Arrays.fill(board, ' ');
		this.board = board;
	}

	public static void main(String[] args) {
		TicTacToeGame game = new TicTacToeGame();
		game.createBoard();
	}

}
