package com.workshop;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
	private Scanner sc;
	private char[] board = new char[10];
	private char userSign;
	private char computerSign;

	public TicTacToeGame(Scanner sc) {
		this.sc = sc;
	}

	public void createBoard() {
		char[] board = new char[10];
		Arrays.fill(board, ' ');
		this.board = board;
		System.out.println("Created empty game board");
	}

	public void selectUserSign() {
		System.out.println("Enter user choice to select mark/sign (X or O):");
		userSign = sc.nextLine().toUpperCase().charAt(0);
		if (userSign == 'X')
			computerSign = 'O';
		else
			computerSign = 'X';
		System.out.println(String.format("User player sign: %s, Computer player sign: %s", userSign, computerSign));
	}
	
	public void selectComputerSign() {
		char computerSign;
		Random rand = new Random();
		computerSign = (rand.nextInt(2)+1==1)?'X':'O';
		if (computerSign == 'X')
			userSign = 'O';
		else
			userSign = 'X';
		System.out.println(String.format("User player sign: %s, Computer player sign: %s", userSign, computerSign));
	}
	
	public void showBoard() {
		for (int i = 1; i < 10; ++i) {
			System.out.print(String.format("[%s]", board[i]));
			System.out.print((i % 3 == 0) ? "\n" : "");
		}
	}

	public void userMove() {
		System.out.println("Enter a position to move(1 to 9): ");
		int move = Integer.parseInt(sc.nextLine());
		if (move < 0 || move > 9 || board[move] != ' ') {
			System.out.println("Enter a valid move: ");
			userMove();
		} else {
			System.out.println("The position is free to move:");
			board[move] = userSign;
			showBoard();
		}
	}

	public void playFirst() {
		System.out.println("Enter choice (Head or Tail): ");
		String userChoice = sc.nextLine();
		Random rand = new Random();
		String toss = (rand.nextInt(2)+1==1)?"Head":"Tail";
		if(toss.equalsIgnoreCase(userChoice)){
			System.out.println("User goes first");
			selectUserSign();
		}
		else {
			System.out.println("Computer goes first");
			selectComputerSign();
		}
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

	public char getComputerSign() {
		return computerSign;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		TicTacToeGame game = new TicTacToeGame(sc);
		game.createBoard();
		//game.selectUserSign();
		game.playFirst();
		game.showBoard();
		game.userMove();
	}
}
