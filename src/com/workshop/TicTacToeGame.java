package com.workshop;

import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
	private char[] board = new char[10];
	private char userSign;
	private char computerSign;

	public void createBoard() {
		char[] board = new char[10];
		Arrays.fill(board, ' ');
		this.board = board;
	}
	
	public void setUserSign(char sign) {
		this.userSign = sign;
	}
	public char userSign() {
		return userSign;
	}
	
	public void setComputerSign(char sign) {
		this.computerSign = sign;
	}
	public char ComputerSign() {
		return computerSign;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TicTacToeGame game = new TicTacToeGame();
		game.createBoard();
		System.out.println("Enter user choice to select mark(X or O):");
		char userSign = sc.nextLine().charAt(0);
		game.setUserSign(userSign);
		char computerSign;
		if (userSign=='X') 
			computerSign = 'O';
		else 
			computerSign = 'X';
		game.setComputerSign(computerSign);
		
	}
}
