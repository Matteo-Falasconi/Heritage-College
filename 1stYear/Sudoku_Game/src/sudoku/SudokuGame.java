package sudoku;

/*
 * Description: This class performs all the thinking for the other classes. 
 * It provides output for the classes to use so that all they need to do is relay those messages.
 * 
 * Author: Matteo Falasconi
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SudokuGame {
	static int[][] gameBoard = new int[9][9];
	private FileNameExtensionFilter sudokuExFilter;
	private Scanner boardReader;
	private FileWriter sudokuWriter;
	private JFileChooser chooser;
	private File sudokuFile;
	private Validate valid = new Validate();
	private int prevRow;
	private int prevCol;
	
	public SudokuGame() {
		prevRow = -1;
		prevCol = -1;
	}
	
	public void setSudokuFIle(File file) {
		sudokuFile = file;
	} // setSudokuFIle(File)
	
	public File getSudokuFile() {
		return sudokuFile;
	} // getSudokuFile()
	
	public void setValid(Validate validate) {
		valid = validate;
	} // setSudokuFIle(File)
	
	public Validate getValid() {
		return valid;
	} // getSudokuFile()

	public void openSudoku() throws IOException {
		String num;
		boardReader = new Scanner(sudokuFile);
		boardReader.useDelimiter("\n|~");
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				num = boardReader.next();
				if (!num.equals("*")) {
					gameBoard[row][col] = Integer.parseInt(num);
				} else {
					gameBoard[row][col] = 0;
				}
				if (col == 8) {
					num = boardReader.nextLine();
				}
			}
		}
	} // openSudoku()

	public void open(String file) {
		if (file.isEmpty()) {
			file = "sudoku.txt";
		}
		sudokuFile = new File(file);
	} // open()

	public boolean checkFile() throws IOException {
		String rowInFile;
		boolean flag = false;
		Scanner fileReader = new Scanner(sudokuFile);
		fileReader.useDelimiter("\n");
		while (fileReader.hasNextLine()) {
			rowInFile = fileReader.nextLine();
			if (Pattern.matches("[1-9*]~[1-9*]~[1-9*]~[1-9*]~[1-9*]~[1-9*]~[1-9*]~[1-9*]~[1-9*]~", rowInFile)) {
				flag = true;
			} else {
				return false;
			}
		}
		fileReader.close();
		return flag;
	} // checkFile()

	public void writeSudoku() throws IOException {
		sudokuWriter = new FileWriter(sudokuFile);
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				if (gameBoard[row][col] == 0) {
					sudokuWriter.write("*~");
				} else {
					sudokuWriter.write(String.valueOf(gameBoard[row][col]) + "~");
				}
			}
			sudokuWriter.write("\r\n");
		}
		sudokuWriter.close();
	} // writeSudoku()

	public boolean undoSudoku() {
		if (prevRow != -1 && prevCol != -1) {
			gameBoard[prevRow][prevCol] = 0;
			prevRow = -1;
			prevCol = -1;
			return true;
		}
		return false;
	} // undoSudoku(int, int, int)
	
	public void quitSudoku() {
		System.exit(0);
	} // quitSudoku()

	public int updateSudoku(int row, int col, int num) {
		prevRow = row;
		prevCol = col;
		return gameBoard[row][col] = num;
	} // updateSudoku()

	public boolean checkFull() {
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				if (gameBoard[row][col] == 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean sudokuWin() {
		for (int row = 0; row < gameBoard.length; row++) {
			for (int col = 0; col < gameBoard[row].length; col++) {
				if (gameBoard[row][col] == 0) {
					for (int num = 0; num <= 9; num++) {
						if (valid.validateInput(row, col, num)) {
							gameBoard[row][col] = num;
							if (sudokuWin()) {
								return true;
							} else {
								gameBoard[row][col] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	} // sudokuWin()

	public boolean fileExplorer() throws IOException {
		String file = "";
		chooser = new JFileChooser("./");
		sudokuExFilter = new FileNameExtensionFilter("Text File (.txt)", "txt");
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setFileFilter(sudokuExFilter);
		int open = chooser.showOpenDialog(chooser);
		if (open == JFileChooser.APPROVE_OPTION) {
			sudokuFile = chooser.getSelectedFile();
			file = sudokuFile.getName();
			open(file);
			if (checkFile()) {
				openSudoku();
				return true;
			}
		}
		return false;

	}
} // class SudokuGame
