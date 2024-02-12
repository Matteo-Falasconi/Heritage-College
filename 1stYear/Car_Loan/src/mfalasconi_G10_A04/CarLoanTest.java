package mfalasconi_G10_A04;

import java.util.Scanner;
import java.text.NumberFormat;

public class CarLoanTest {

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		NumberFormat money = NumberFormat.getCurrencyInstance();

		CarLoan car1 = new CarLoan(1000, 2);
		CarLoan car2 = new CarLoan(2500, 6);
		CarLoan car3 = new CarLoan(250, 4);
		CarLoan car4 = new CarLoan(-1, 2);
		CarLoan car5 = new CarLoan(500, 100);

		car1.setCarCost(10000);
		car1.calculatePaymentInformation();
		if ((int) car1.getTotalInterest() != 92)
			System.out.println("car1, The interest does not calculate properly.");
		if (car1.getTotalNumberPayments() != 11)
			System.out.println("car1, The number of payments does not calculate properly.");
		if ((int) car1.getTotalPayment() != 10092)
			System.out.println("car1, The total payment does not calculate properly.");

		car2.setCarCost(10000);
		car2.calculatePaymentInformation();
		if ((int) car2.getTotalInterest() != 126)
			System.out.println("car2, The interest does not calculate properly.");
		if (car2.getTotalNumberPayments() != 5)
			System.out.println("car2, The number of payments does not calculate properly.");
		if ((int) car2.getTotalPayment() != 10126)
			System.out.println("car2, The total payment does not calculate properly.");

		car3.setCarCost(10000);
		car3.calculatePaymentInformation();
		if ((int) car3.getTotalInterest() != 750)
			System.out.println("car3, The interest does not calculate properly.");
		if (car3.getTotalNumberPayments() != 44)
			System.out.println("car3, The number of payments does not calculate properly.");
		if ((int) car3.getTotalPayment() != 10750)
			System.out.println("car3, The total payment does not calculate properly.");

		car4.setCarCost(10000);
		if (car4.calculatePaymentInformation())
			System.out.println("car4, Does not work detect negative variables");

		car5.setCarCost(10000);
		if (car5.calculatePaymentInformation())
			System.out.println("car5, Does not detect interest greater than monthly payment");
		else
			System.out.println("Everything looks good!");

		keyboard.close();
	} // main()
} // class CarLoanTest