package sudoku;

/*
 * Description: This class performs the CLI interface and creates a playable game in the console.
 * It gets its information from both SudokuGame and Validate for to make sure that the user is playing by the rules.
 * 
 * Author: Matteo Falasconi
 * 
 */

import java.io.IOException;
import java.util.Scanner;

public class SudokuInterface {
	private static SudokuGame game = new SudokuGame();
	private static Scanner input = new Scanner(System.in);

	public static void main(String[] args) {
		SudokuInterface sudokuInt = new SudokuInterface();
		System.out.println("WELCOME TO MATTEO'S SUDOKU!\n");
		System.out.println("What file would you like to play sudoku on? (enter nothing to play on sudoku.txt)");
		game.open(input.nextLine());
		try {
			if (!game.checkFile()) {
				System.err.println(game.getSudokuFile() + " does follow format. Re-enter please!\n");
				main(args);
			}
		} catch (IOException e) {
			System.out.println(game.getSudokuFile() + " File could not be read. Re-enter please!\n");
			main(args);
		}
		try {
			game.openSudoku();

		} catch (IOException e) {
			System.out.println("ERROR: File " + game.getSudokuFile() + " could not opened Try again\n");
			main(args);
		}
		sudokuInt.mainLooping();
	} // main()

	private void mainLooping() {
		if (game.checkFull()) {
			System.out.println("This board has already been solved");
		} else {
			displayBoard();
			System.out.println("Options: q = quit, s = save, u = undo");
			System.out.println("Where would you like to place a number (example: 1 1): ");
			int col = 0;
			int row = 0;
			String choice = input.next();
			if (selectedOption(choice)) {
				mainLooping();
			}
			row = Integer.parseInt(choice) - 1;
			col = input.nextInt() - 1;
			while (!(game.getValid().validatePosition(row, col))) {
				System.err.println("Please submit a valid position from 1 1 to 9 9 on the board");
				choice = input.next();
				if (selectedOption(choice)) {
					mainLooping();
				}
				row = Integer.parseInt(choice) - 1;
				col = input.nextInt() - 1;
			}
			while (!(game.getValid().emptyPosition(row, col))) {
				System.err.println("Please submit a valid position in an empty spot on the board");
				if (selectedOption(choice)) {
					mainLooping();
				}
				row = Integer.parseInt(choice) - 1;
				col = input.nextInt() - 1;
			}
			System.out.println("What would you like your number to be (example: 1 - 9): ");
			int num = 0;
			choice = input.next();
			if (selectedOption(choice)) {
				mainLooping();
			}
			num = Integer.parseInt(choice);
			while (!game.getValid().validateNumber(num)) {
				System.err.println("Please submit a valid number from 1 - 9");
				choice = input.next();
				if (selectedOption(choice)) {
					mainLooping();
				}
				num = Integer.parseInt(choice);
			}
			while (!game.getValid().validateInput(row, col, num)) {
				System.err.println("Please enter a number that does not exist in the same row, column or block.");
				choice = input.next();
				if (selectedOption(choice)) {
					mainLooping();
				}
				num = Integer.parseInt(choice);
			}
			game.updateSudoku(row, col, num);
			if (game.sudokuWin()) {
				System.out.println("YOU WON!");
			} else {
				System.out.println("You lost...");
			}
			mainLooping();
		}
		System.out.println("Would you like to continue with another game, (yes or no)?");
		String confirm = input.next();
		continueGame(confirm);

	} // mainLooping()

	public boolean selectedOption(String option) {
		if (option.equalsIgnoreCase("q")) {
			System.out.println("\nClosing out the program. Come back again soon!");
			game.quitSudoku();
		} else if (option.equalsIgnoreCase("s")) {
			try {
				game.writeSudoku();
				return true;
			} catch (IOException e) {
				System.err.println("Could no write to the " + game.getSudokuFile());
			}
			System.out.println("\nSuccessfully saved to " + game.getSudokuFile());
		} else if (option.equalsIgnoreCase("u")) {
			if (!game.undoSudoku()) {
				System.err.println(
						"\nYou cannot undo at this moment. (Either you have not played yet or have recently selected undo)");
				return true;
			} else {
				System.out.println("\nSuccessfully undone your last move!");
				return true;
			}
		}
		return false;
	} // selectedOption(String)

	private void continueGame(String confirm) {
		if (confirm.equals("yes")) {
			System.out.println("Loading next game!\n");
			main(null);
		}
		System.out.println("Come back again or don't...");
	} // continueGame(Scanner, SudokuGame)

	private void displayBoard() {
		System.out.println("-------------------------");
		for (int row = 0; row < SudokuGame.gameBoard.length; row++) {
			if (row % 3 == 0 && row != 0) {
				System.out.println("|-----------------------|");
			}
			System.out.print("| ");
			for (int col = 0; col < SudokuGame.gameBoard.length; col++) {
				if (col % 3 == 0 && col != 0) {
					System.out.print("| ");
				}
				if (SudokuGame.gameBoard[row][col] == 0) {
					System.out.print("* ");
				} else {
					System.out.print(SudokuGame.gameBoard[row][col] + " ");
				}
			}
			System.out.println("|");
		}
		System.out.println("-------------------------");
	} // displayBoard()
} // class SudokuInterface
