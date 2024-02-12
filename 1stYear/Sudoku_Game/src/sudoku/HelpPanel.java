package sudoku;

/*
 * Description: This class gives a rule set for the user to gain an understanding of the game through
 * simple rule descriptions.
 * 
 * Author: Matteo Falasconi
 * 
 */


import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class HelpPanel extends JPanel {

	
	
	public HelpPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{430, 0};
		gridBagLayout.rowHeights = new int[]{26, 26, 26, 26, 51, 51, 26, 26, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblSudokuRules = new JLabel("Sudoku Rules:");
		lblSudokuRules.setHorizontalAlignment(SwingConstants.CENTER);
		lblSudokuRules.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		GridBagConstraints gbc_lblSudokuRules = new GridBagConstraints();
		gbc_lblSudokuRules.insets = new Insets(0, 0, 5, 0);
		gbc_lblSudokuRules.gridx = 0;
		gbc_lblSudokuRules.gridy = 0;
		add(lblSudokuRules, gbc_lblSudokuRules);
		
		JLabel lblRule_1 = new JLabel("1. The puzzle consists of a 9x9 grid, divided into nine 3x3 boxes.");
		lblRule_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		GridBagConstraints gbc_lblRule_1 = new GridBagConstraints();
		gbc_lblRule_1.fill = GridBagConstraints.BOTH;
		gbc_lblRule_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblRule_1.gridx = 0;
		gbc_lblRule_1.gridy = 1;
		add(lblRule_1, gbc_lblRule_1);
		
		JLabel lblRule_2 = new JLabel("2. The objective is to fill in the grid so that each row,");
		lblRule_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule_2.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		GridBagConstraints gbc_lblRule_2 = new GridBagConstraints();
		gbc_lblRule_2.fill = GridBagConstraints.BOTH;
		gbc_lblRule_2.insets = new Insets(0, 0, 5, 0);
		gbc_lblRule_2.gridx = 0;
		gbc_lblRule_2.gridy = 2;
		add(lblRule_2, gbc_lblRule_2);
		
		JLabel lblRule_2_1 = new JLabel("column, and 3x3 box contains the numbers 1-9 exactly once.");
		lblRule_2_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule_2_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		GridBagConstraints gbc_lblRule_2_1 = new GridBagConstraints();
		gbc_lblRule_2_1.fill = GridBagConstraints.BOTH;
		gbc_lblRule_2_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblRule_2_1.gridx = 0;
		gbc_lblRule_2_1.gridy = 3;
		add(lblRule_2_1, gbc_lblRule_2_1);
		
		JLabel lblRule_4 = new JLabel("4. Each new number must be placed in an empty cell and must not violate the");
		lblRule_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule_4.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		GridBagConstraints gbc_lblRule_4 = new GridBagConstraints();
		gbc_lblRule_4.anchor = GridBagConstraints.SOUTH;
		gbc_lblRule_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRule_4.insets = new Insets(0, 0, 5, 0);
		gbc_lblRule_4.gridx = 0;
		gbc_lblRule_4.gridy = 4;
		add(lblRule_4, gbc_lblRule_4);
		
		JLabel lblRule_3 = new JLabel("3. The puzzle starts with some numbers already filled in, known as \"givens\".");
		lblRule_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule_3.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		GridBagConstraints gbc_lblRule_3 = new GridBagConstraints();
		gbc_lblRule_3.anchor = GridBagConstraints.NORTH;
		gbc_lblRule_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRule_3.insets = new Insets(0, 0, 5, 0);
		gbc_lblRule_3.gridx = 0;
		gbc_lblRule_3.gridy = 4;
		add(lblRule_3, gbc_lblRule_3);
		
		JLabel lblRule_5 = new JLabel("5. Once a number is placed, it cannot be changed.");
		lblRule_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule_5.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		GridBagConstraints gbc_lblRule_5 = new GridBagConstraints();
		gbc_lblRule_5.anchor = GridBagConstraints.SOUTH;
		gbc_lblRule_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRule_5.insets = new Insets(0, 0, 5, 0);
		gbc_lblRule_5.gridx = 0;
		gbc_lblRule_5.gridy = 5;
		add(lblRule_5, gbc_lblRule_5);
		
		JLabel lblRule_4_1 = new JLabel("above rules.");
		lblRule_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule_4_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		GridBagConstraints gbc_lblRule_4_1 = new GridBagConstraints();
		gbc_lblRule_4_1.anchor = GridBagConstraints.NORTH;
		gbc_lblRule_4_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblRule_4_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblRule_4_1.gridx = 0;
		gbc_lblRule_4_1.gridy = 5;
		add(lblRule_4_1, gbc_lblRule_4_1);
		
		JLabel lblRule_6 = new JLabel("6. The puzzle is solved when all cells are filled in and the above rules");
		lblRule_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule_6.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		GridBagConstraints gbc_lblRule_6 = new GridBagConstraints();
		gbc_lblRule_6.fill = GridBagConstraints.BOTH;
		gbc_lblRule_6.insets = new Insets(0, 0, 5, 0);
		gbc_lblRule_6.gridx = 0;
		gbc_lblRule_6.gridy = 6;
		add(lblRule_6, gbc_lblRule_6);
		
		JLabel lblRule_6_1 = new JLabel("are satisfied.");
		lblRule_6_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblRule_6_1.setFont(new Font("Segoe UI Black", Font.PLAIN, 11));
		GridBagConstraints gbc_lblRule_6_1 = new GridBagConstraints();
		gbc_lblRule_6_1.fill = GridBagConstraints.BOTH;
		gbc_lblRule_6_1.gridx = 0;
		gbc_lblRule_6_1.gridy = 7;
		add(lblRule_6_1, gbc_lblRule_6_1);

	}
}
