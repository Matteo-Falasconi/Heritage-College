package mfalasconi_G10_A04;

public class CarLoan {

	private double monthlyPay;
	private double interestRate;
	private String makeAndModel;
	private double carCost;
	private int totalNumberPayment;
	private double totalPayment;

	public CarLoan() {
		monthlyPay = 0;
		interestRate = 0;
		totalPayment = 0;
		carCost = 0;
		totalNumberPayment = 0;
		makeAndModel = "unknown";
	} // CarLoan()

	public CarLoan(String mAndM, double cost) {
		makeAndModel = mAndM;
		carCost = cost;
		monthlyPay = 0;
		interestRate = 0;
		totalNumberPayment = 0;
		totalPayment = 0;
	} // CarLoan(String, int)

	public CarLoan(double monthlyPayment, double interestR) {
		monthlyPay = monthlyPayment;
		interestRate = interestR;
		carCost = 0;
		totalNumberPayment = 0;
		totalPayment = 0;
	} // CarLoan(double, double)

	public void setMakeAndModel(String loan) {
		makeAndModel = loan;
	} // setLoanAmount

	public String getMakeAndModel() {
		return makeAndModel;
	} // getLoanAmount

	public void setMonthlyPay(double mPay) {
		monthlyPay = mPay;
	} // setmonthlyPay()

	public double getMonthlyPay() {
		return monthlyPay;
	} // getmonthlyPay

	public void setInterestRate(double intRate) {
		interestRate = intRate;
	} // setInterest()

	public double getInterestRate() {
		return interestRate;
	} // getInterest()

	public void setCarCost(double cost) {
		carCost = cost;
	} //setCarCost()

	public double getCarCost() {
		return carCost;
	} // getCarCost

	public boolean calculatePaymentInformation() {
		boolean works = true;
		double balanceOwing = carCost;
		double interest;
		totalNumberPayment = 0;
		totalPayment = 0;
		double firstInt = balanceOwing * (interestRate / 100) / 12;
		if (firstInt < monthlyPay) {
			while (balanceOwing > 0) {
				++totalNumberPayment; // increase number of payments
				interest = balanceOwing * (interestRate / 100) / 12;
				balanceOwing += interest;
				if (balanceOwing <= monthlyPay) {
					totalPayment += balanceOwing;
					balanceOwing = 0;
				} else {
					totalPayment += monthlyPay;
					balanceOwing -= monthlyPay;
				}
			} // (balanceOwing >= monthlyPay)
		} else
			works = false;
		return works;
	} // totalPayment()

	public int getTotalNumberPayments() {
		return totalNumberPayment;
	} // getTotalNumberPayments()

	public double getTotalPayment() {
		return totalPayment;
	} // getTotalPayment()

	public double getTotalInterest() {
		return totalPayment - carCost;
	} // getTotalInterest()

} // CarLoan class