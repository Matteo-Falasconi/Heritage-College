package sudoku;

/*
 * Description: This class is the frame version of the sudoku game it creates a user interface for the user to play on.
 * It gets its information from both SudokuGame and Validate for to make sure that the user is playing by the rules.
 * 
 * Author: Matteo Falasconi
 * 
 */

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class SudokuFrame extends JFrame implements ActionListener {

	private static SudokuGame game = new SudokuGame();
	private JPanel contentPane;
	private JMenuItem selectMenuItem;
	private JMenu fileMenu;
	private JMenuBar menuBar;
	private JMenuItem saveMenuItem;
	private JMenuItem quitMenuItem;
	private JMenuItem aboutMenuItem;
	private JMenuItem helpMenuItem;
	private JPanel panel;
	private JMenuItem undoMenuItem;
	private JLabel lblTitle;
	private JMenu aboutMenu;
	private int firstFile = 0;
	private JTextField[][] field = new JTextField[SudokuGame.gameBoard.length][SudokuGame.gameBoard.length];

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					SudokuFrame frame = new SudokuFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} // main(String[])

	public SudokuFrame() {
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 530, 613);

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);

		selectMenuItem = new JMenuItem("Select File");
		fileMenu.add(selectMenuItem);
		selectMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (firstFile != 0) {
					for (int row = 0; row < SudokuGame.gameBoard.length; row++) {
						for (int col = SudokuGame.gameBoard[row].length; col < 9; col++) {
							field[row][col].setText("");
						}
					}
				}
				firstFile++;
				try {
					if (game.fileExplorer()) {
						JOptionPane.showMessageDialog(new JFrame(), "Successfully changed file!", "File Changed!",
								JOptionPane.INFORMATION_MESSAGE);
						fields();
					} else {
						JOptionPane.showMessageDialog(new JFrame(), "File is not formatted properly.",
								"File Format Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(new JFrame(), "File could not be loaded.", "File Change Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		saveMenuItem = new JMenuItem("Save");
		fileMenu.add(saveMenuItem);
		saveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = true;
				try {
					game.writeSudoku();
				} catch (IOException e1) {
					flag = false;
					JOptionPane.showMessageDialog(new JFrame(), "Could not save to file.", "Save Error",
							JOptionPane.ERROR_MESSAGE);
				}
				if (flag == true) {
					JOptionPane.showMessageDialog(new JFrame(), "Successfully saved to file!", "Saved!",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});

		undoMenuItem = new JMenuItem("Undo");
		fileMenu.add(undoMenuItem);
		undoMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (game.undoSudoku()) {
					fields();
					JOptionPane.showMessageDialog(new JFrame(), "Successfully undid your last move!", "Undid!",
							JOptionPane.INFORMATION_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(new JFrame(), "You cannot undo multiple moves.", "Undo Error",
							JOptionPane.ERROR_MESSAGE);
				} 
			} // actionPerformed(ActionEvent)
		});

		quitMenuItem = new JMenuItem("Quit");
		fileMenu.add(quitMenuItem);
		quitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JFrame(), "Closing the game! Come back soon!", "Closing!",
						JOptionPane.INFORMATION_MESSAGE);
				game.quitSudoku();
			} // actionPerformed(ActionEvent)
		});

		aboutMenu = new JMenu("About");
		menuBar.add(aboutMenu);

		aboutMenuItem = new JMenuItem("About");
		aboutMenu.add(aboutMenuItem);
		aboutMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JPanel(), new AboutPanel(), "About", JOptionPane.PLAIN_MESSAGE);
			} // actionPerformed(ActionEvent)
		});

		helpMenuItem = new JMenuItem("Help");
		aboutMenu.add(helpMenuItem);
		helpMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(new JPanel(), new HelpPanel(), "Help", JOptionPane.PLAIN_MESSAGE);
			} // actionPerformed(ActionEvent)
		});

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 97, 497, 447);
		contentPane.add(panel);
		panel.setLayout(null);

		lblTitle = new JLabel("Welcome to Matteo's Sudoku!");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 27));
		lblTitle.setBounds(10, 0, 497, 98);
		contentPane.add(lblTitle);

		fields();
	} // SudokuFrame();

	public void fields() {
		for (int row = 0; row < SudokuGame.gameBoard.length; row++) {
			for (int col = 0; col < SudokuGame.gameBoard[row].length; col++) {
				if (SudokuGame.gameBoard[row][col] == 0) {
					if (firstFile == 0) {
						field[row][col] = new JTextField("");
						field[row][col].setEnabled(false);
						field[row][col].addActionListener(this);
						panel.add(field[row][col]);
					} else {
						field[row][col].setEnabled(true);
						field[row][col].setText("");
					}
				} else {
					if (firstFile == 0) {
						field[row][col] = new JTextField(Integer.toString(SudokuGame.gameBoard[row][col]));
						field[row][col].addActionListener(this);
						panel.add(field[row][col]);
					} else {
						field[row][col].setEnabled(false);
						field[row][col].setText(Integer.toString(SudokuGame.gameBoard[row][col]));
					}

				}
				field[row][col].setHorizontalAlignment(SwingConstants.CENTER);
				field[row][col].setFont(new Font("Segoe UI Black", Font.PLAIN, 30));

				// Padding for the fields
				field[row][col].setBounds(10 + col * 52 + (col / 3) * 5, 11 + row * 47 + (row / 3) * 5, 52, 47);
				field[row][col].setColumns(10);
			}
		}
		if (game.checkFull()) {
			JOptionPane.showMessageDialog(new JFrame(), "This file has already been filled out!", "Full!",
					JOptionPane.INFORMATION_MESSAGE);
		}
	} // fields()

	public void actionPerformed(ActionEvent e) {
		for (int row = 0; row < SudokuGame.gameBoard.length; row++) {
			for (int col = 0; col < SudokuGame.gameBoard[row].length; col++) {
				if (e.getSource() == field[row][col]) {
					inputEntered(row, col);
				}
			}
		}
	} // actionPerformed(ActionEvent)

	private void inputEntered(int row, int col) {
		try {
			int input = Integer.parseInt(field[row][col].getText());
			if (game.getValid().validateNumber(input)) {
				if (game.getValid().validateInput(row, col, input)) {
					game.updateSudoku(row, col, input);
					field[row][col].setEnabled(false);
					if (game.checkFull()) {
						JOptionPane.showMessageDialog(this, "Game over! You have filled in all the squares!",
								"Game Over!", JOptionPane.INFORMATION_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this,
							"Please enter a number that does not exist in the same row, column or block.",
							"Position Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 9", "Number Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(this, "Please enter a number between 1 and 9", "Number Error",
					JOptionPane.ERROR_MESSAGE);
		}
	} // inputEntered(int, int)
} // class SudokuFrame
