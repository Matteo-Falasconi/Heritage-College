package sudoku;

/*
 * Description: This class presents a about  panel/page for the users to gain information on the name, author,
 * year it was made, and what school it was made from.
 * 
 * Author: Matteo Falasconi
 * 
 */

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class AboutPanel extends JPanel {
	
	public AboutPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblTitle = new JLabel("Matteo's Sudoku Game!");
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 0;
		add(lblTitle, gbc_lblTitle);
		
		JLabel lblAuthor = new JLabel("By: Matteo Falasconi o(*\u00B0U\u00B0*)o");
		lblAuthor.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthor.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.insets = new Insets(0, 0, 5, 0);
		gbc_lblAuthor.gridx = 0;
		gbc_lblAuthor.gridy = 1;
		add(lblAuthor, gbc_lblAuthor);
		
		JLabel lblYear = new JLabel("2023");
		lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYear.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		GridBagConstraints gbc_lblYear = new GridBagConstraints();
		gbc_lblYear.insets = new Insets(0, 0, 5, 0);
		gbc_lblYear.gridx = 0;
		gbc_lblYear.gridy = 2;
		add(lblYear, gbc_lblYear);
		
		JLabel lblCollege = new JLabel("Heritage College");
		lblCollege.setHorizontalAlignment(SwingConstants.CENTER);
		lblCollege.setFont(new Font("Segoe UI Black", Font.PLAIN, 14));
		GridBagConstraints gbc_lblCollege = new GridBagConstraints();
		gbc_lblCollege.insets = new Insets(0, 0, 5, 0);
		gbc_lblCollege.gridx = 0;
		gbc_lblCollege.gridy = 3;
		add(lblCollege, gbc_lblCollege);
		
		JLabel lblHappy = new JLabel(":D");
		lblHappy.setHorizontalAlignment(SwingConstants.CENTER);
		lblHappy.setFont(new Font("Segoe UI Black", Font.PLAIN, 40));
		GridBagConstraints gbc_lblHappy = new GridBagConstraints();
		gbc_lblHappy.gridx = 0;
		gbc_lblHappy.gridy = 4;
		add(lblHappy, gbc_lblHappy);

	}
}
