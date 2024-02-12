package mfalasconi_G10_A04;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Scanner;

/*
 * @author MFalasconi
 * 
 */

public class CarLoanFrame extends JFrame implements ActionListener {

	private JLabel lblMonthlyPay;
	private JLabel lblInterestRate;
	private JTextArea areaDisplay;
	private JButton btnCalculate;
	private JTextField fldTextM;
	private JTextField fldTextI;
	private JPanel inputPanel;

	public CarLoanFrame() {

		setTitle("Car Loan Calculator");

		inputPanel = new JPanel();

		lblMonthlyPay = new JLabel("Monthly Payment: ");
		inputPanel.add(lblMonthlyPay);

		fldTextM = new JTextField(15);
		inputPanel.add(fldTextM);

		lblInterestRate = new JLabel("Interest Rate: ");
		inputPanel.add(lblInterestRate);

		fldTextI = new JTextField(15);
		inputPanel.add(fldTextI);

		btnCalculate = new JButton("Calculate");
		inputPanel.add(btnCalculate);
		btnCalculate.addActionListener(this);

		areaDisplay = new JTextArea(20, 45);
		areaDisplay.setFont(new Font("Monospaced", Font.PLAIN, 12));
		areaDisplay.setEditable(false);

		getContentPane().add(areaDisplay, BorderLayout.SOUTH);
		getContentPane().add(inputPanel, BorderLayout.CENTER);
	} // init()

	public void actionPerformed(ActionEvent e) {
		NumberFormat money = NumberFormat.getCurrencyInstance();
		if (fldTextM.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "You must enter a number first for the monthly Payment.",
					"Missing Number", JOptionPane.ERROR_MESSAGE);
		} else if (Double.parseDouble(fldTextM.getText()) < 0) {
			JOptionPane.showMessageDialog(this, "You must enter a positive number for the Monthly Payment.",
					"Negative Number", JOptionPane.ERROR_MESSAGE);
		}
		if (fldTextI.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "You must enter a number first for the interest.", "Missing Number",
					JOptionPane.ERROR_MESSAGE);
		} else if (Double.parseDouble(fldTextI.getText()) < 0) {
			JOptionPane.showMessageDialog(this, "You must enter a positive number for the interest.", "Negative Number",
					JOptionPane.ERROR_MESSAGE);
		} else {
			double monthlyPay = Double.parseDouble(fldTextM.getText());
			double interestRate = Double.parseDouble(fldTextI.getText());
			File carloan;
			Scanner input = null;
			if (e.getSource() == btnCalculate) {

				CarLoan car = new CarLoan(Double.parseDouble(fldTextM.getText()),
						Double.parseDouble(fldTextI.getText()));
				car.calculatePaymentInformation();
				carloan = new File("carloan.txt");
				try {
					input = new Scanner(carloan);
					input.useDelimiter("~|\r?\n");
				} // try
				catch (FileNotFoundException e1) {
					areaDisplay.setText("Error: File could not be found.");
					JOptionPane.showMessageDialog(this, "You must have a readable file. (carloan.txt)", "Missing File",
							JOptionPane.ERROR_MESSAGE);
				} // catch (FileNotFoundException e)
				areaDisplay.setText(String.format("%s%32s%10s%10s%14s%16s\n", "Car", "Loan", "Monthly", "Number of",
						"Total", "Total"));
				areaDisplay.append(
						String.format("%35s%10s%10s%14s%16s\n", "Amount", "Payment", "Payments", "Interest", "Paid"));
				areaDisplay.append(String.format("%s%s%s%s%s%s\n", "--------------------- ", "------------- ",
						"--------- ", "--------- ", "------------- ", "---------------"));
				while (input.hasNext()) // start of outer loop
				{
					car = new CarLoan(monthlyPay, interestRate);
					car.setMakeAndModel(input.next());
					car.setCarCost(input.nextDouble());
					car.calculatePaymentInformation();
					if (car.calculatePaymentInformation() != false) {
						areaDisplay.append(String.format("%-22s%12s%11s%10s%14s%16s\n", car.getMakeAndModel(),
								money.format(car.getCarCost()), money.format(car.getMonthlyPay()),
								car.getTotalNumberPayments(), money.format(car.getTotalInterest()),
								money.format(car.getTotalPayment())));
					} else {
						JOptionPane.showMessageDialog(this, "You must enter a smaller interest value.", "Payment Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} // end of the outer loop
			}
		}
	} // actionPerformed(ActionEvent e)

	public static void main(String[] args) {
		CarLoanFrame frame = new CarLoanFrame();
		frame.setSize(620, 450);
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	} // main()
}