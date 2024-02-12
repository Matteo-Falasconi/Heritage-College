package sudoku;

/*
 * Description: This class performs the validation of the inputs from the user. Making 
 * sure that the number is between 1 and 9 and that there is not existing number the same 
 * as the input in the same row, col, and block
 * 
 * Author: Matteo Falasconi
 * 
 */

public class Validate {

	public boolean validatePosition(int row, int col) {
		boolean flag = false;
		if ((row >= 0 && row <= 8) && (col >= 0 && col <= 8)) {
			flag = true;
		}
		return flag;
	} // validatePosition

	public boolean validateNumber(int num) {
		boolean flag = true;
		if (num < 1 || num > 9) {
			flag = false;
		}
		return flag;
	} // validateNumber(int)

	public boolean emptyPosition(int row, int col) {
		boolean flag = true;
		if (SudokuGame.gameBoard[row][col] != 0) {
			flag = false;
		}
		return flag;
	} // emptyPosition()

	private boolean validateRow(int row, int num) {
		for (int i = 0; i < SudokuGame.gameBoard.length; i++) {
			if (SudokuGame.gameBoard[row][i] == num) {
				return true;
			}
		}
		return false;
	} // validateRow(int, int, int)

	private boolean validateCol(int col, int num) {
		for (int i = 0; i < SudokuGame.gameBoard.length; i++) {
			if (SudokuGame.gameBoard[i][col] == num) {
				return true;
			}
		}
		return false;
	} // validateCol(int, int, int)

	private boolean validateBlock(int row, int col, int num) {
		int blockRow = row - row % 3;
		int blockCol = col - col % 3;
		for (int i = blockRow; i < blockRow + 3; i++) {
			for (int b = blockCol; b < blockCol + 3; b++) {
				if (SudokuGame.gameBoard[i][b] == num) {
					return true;
				}
			}
		}
		return false;
	} // validateBlock(int, int, int, int)

	public boolean validateInput(int row, int col, int num) {
		return (!validateBlock(row, col, num) && !validateCol(col, num) && !validateRow(row, num));
	} // validateInput(int, int, int)
} // class Validate
