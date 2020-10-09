package com.workshop;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {
	private int user;
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

	public void showBoard() {
		for (int i = 1; i < 10; ++i) {
			System.out.print(String.format("[%s]", board[i]));
			System.out.print((i % 3 == 0) ? "\n" : "");
		}
	}

	public void playFirst() {
		System.out.println("Enter choice (Head or Tail): ");
		String userChoice = sc.nextLine();
		Random rand = new Random();
		String toss = (rand.nextInt(2) + 1 == 1) ? "Head" : "Tail";
		if (toss.equalsIgnoreCase(userChoice)) {
			System.out.println("User goes first");
			user = 1;
			selectUserSign();
		} else {
			System.out.println("Computer goes first");
			user = 0;
			selectComputerSign();
		}
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
		Random rand = new Random();
		computerSign = (rand.nextInt(2) + 1 == 1) ? 'X' : 'O';
		if (computerSign == 'X')
			userSign = 'O';
		else
			userSign = 'X';
		System.out.println(String.format("User player sign: %s, Computer player sign: %s", userSign, computerSign));
	}

	public void userMove() {
		System.out.println("Enter a position to move(1 to 9): ");
		int move = Integer.parseInt(sc.nextLine());
		if (move < 0 || move > 9 || board[move] != ' ') {
			System.out.println(((board[move] != ' ') ? "Already Occupied. " : "") + "Enter a valid move: ");
			userMove();
		} else {
			System.out.println("The position is free to move:");
			board[move] = userSign;
			showBoard();
		}
	}

	public void computerMove() {
		int move = positionToWin(computerSign);
		if (move == -1) {
			Random rand = new Random();
			move = rand.nextInt(9) + 1;
		}
		if (board[move] != ' ') {
			computerMove();
		} else {
			System.out.println("Computer makes move");
			board[move] = computerSign;
			showBoard();
		}
	}

	public int positionToWin(char player) {
		int countLeftDiag = 0, posLeftDiag = -1;
		int countRightDiag = 0, posRightDiag = -1;
		for (int i = 0; i < 3; ++i) {
			int countHorizontal = 0, posHr = -1;
			int countVertical = 0, posVr = -1;

			countLeftDiag += board[i * 3 + i + 1] == player ? 1 : 0;
			posLeftDiag = board[i * 3 + i + 1] == ' ' ? (i * 3 + i + 1) : posLeftDiag;
			countRightDiag += board[i * 3 + (2 - i) + 1] == player ? 1 : 0;
			posRightDiag = board[i * 3 + (2 - i) + 1] == ' ' ? (i * 3 + (2 - i) + 1) : posRightDiag;
			for (int j = 0; j < 3; ++j) {
				countHorizontal += (board[i * 3 + j + 1] == player) ? 1 : 0;
				posHr = board[i * 3 + j + 1] == ' ' ? (i * 3 + j + 1) : posHr;
				countVertical += (board[j * 3 + i + 1] == player) ? 1 : 0;
				posVr = board[j * 3 + i + 1] == ' ' ? (j * 3 + i + 1) : posVr;
			}
			if (countHorizontal == 2 && posHr != -1)
				return posHr;
			if (countVertical == 2 && posVr != -1)
				return posVr;
		}
		if (countLeftDiag == 2 && posLeftDiag != -1)
			return posLeftDiag;
		if (countRightDiag == 2 && posRightDiag != -1) {
			return posRightDiag;
		}
		return -1;
	}

	public boolean checkWinner(char player) {
		boolean win = false;
		boolean matchLeftDiag = true;
		boolean matchRightDiag = true;
		for (int i = 0; i < 3; ++i) {
			boolean matchHorizontal = true;
			boolean matchVertical = true;

			matchLeftDiag &= board[i * 3 + i + 1] == player;
			matchRightDiag &= board[i * 3 + (2 - i) + 1] == player;
			for (int j = 0; j < 3; ++j) {
				matchHorizontal &= board[i * 3 + j + 1] == player;
				matchVertical &= board[j * 3 + i + 1] == player;
			}
			if (matchHorizontal || matchVertical) {
				win = true;
				break;
			}
		}
		if (win || matchLeftDiag || matchRightDiag) {
			System.out.println(((player == userSign) ? "User" : "Computer") + " Won!!");
			return true;
		}
		return false;
	}

	public boolean tie() {
		int count = 0;
		for (int i = 1; i <= 9; ++i) {
			count += board[i] != ' ' ? 1 : 0;
		}
		return count == 9;
	}

	public void changeTurn() {
		user ^= 1;
	}

	public boolean isUserTurn() {
		return user == 1;
	}

	public void setUserSign(char sign) {
		this.userSign = sign;
	}

	public char getUserSign() {
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
		// game.selectUserSign();
		game.playFirst();
		game.showBoard();
		while (true) {
			if (game.isUserTurn()) {
				game.userMove();
				if (game.checkWinner(game.getUserSign())) {
					break;
				}
			} else {
				game.computerMove();
				if (game.checkWinner(game.getComputerSign())) {
					break;
				}
			}
			if (game.tie()) {
				System.out.println("Game is Tie\n");
				break;
			}
			game.changeTurn();
		}
	}
}
