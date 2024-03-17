import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.logging.*;

public class Account {
	// variables
	Date date = new Date();
	SimpleFormatter formatter = new SimpleFormatter();

	private Logger checking = Logger.getLogger("Logging");
	private Logger savings = Logger.getLogger("Logging");
	private Logger investment = Logger.getLogger("Logging");

	FileHandler checkingFile;
	FileHandler savingsFile;
	FileHandler investmentsFile;

	{
		try {
			checkingFile = new FileHandler("/Users/michael/Desktop/Projects/ATM-Machine-Java/checking.log",true);
			savingsFile = new FileHandler("/Users/michael/Desktop/Projects/ATM-Machine-Java/savings.log",true);
			investmentsFile = new FileHandler("/Users/michael/Desktop/Projects/ATM-Machine-Java/investment.log",true);

			checking.addHandler(checkingFile);
			savings.addHandler(savingsFile);
			investment.addHandler(investmentsFile);

			checking.setLevel(Level.INFO);
			savings.setLevel(Level.INFO);
			investment.setLevel(Level.INFO);

			checkingFile.setFormatter(formatter);
			savingsFile.setFormatter(formatter);
			investmentsFile.setFormatter(formatter);

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void getCheckingLogs() {
		File checking = new File("checking.log");
		Scanner scan1;
		{
			try {
				scan1 = new Scanner(checking);
				while (scan1.hasNextLine()) {
					System.out.println(scan1.nextLine());
				}
			} catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
	}

	public void getSavingsLogs() {
		File savings = new File("savings.log");
		Scanner scan1;
		{
			try {
				scan1 = new Scanner(savings);
				while (scan1.hasNextLine()) {
					System.out.println(scan1.nextLine());
				}
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void getInvestmentsLogs() {
		File investments = new File("investment.log");
		Scanner scan1;
		{
			try {
				scan1 = new Scanner(investments);
				while (scan1.hasNextLine()) {
					System.out.println(scan1.nextLine());
				}
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private int customerNumber;
	private int pinNumber;
	private double checkingBalance = 0;
	private double savingBalance = 0;

	Scanner input = new Scanner(System.in);
	DecimalFormat moneyFormat = new DecimalFormat("'$'###,##0.00");

	public Account() {
	}

	public Account(int customerNumber, int pinNumber) {
		this.customerNumber = customerNumber;
		this.pinNumber = pinNumber;
	}

	public Account(int customerNumber, int pinNumber, double checkingBalance, double savingBalance) {
		this.customerNumber = customerNumber;
		this.pinNumber = pinNumber;
		this.checkingBalance = checkingBalance;
		this.savingBalance = savingBalance;
	}

	public int setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber;
		return customerNumber;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public int setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
		return pinNumber;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public double getCheckingBalance() {
		return checkingBalance;
	}

	public double getSavingBalance() {
		return savingBalance;
	}

	public double calcCheckingWithdraw(double amount) {
		checkingBalance = (checkingBalance - amount);
		return checkingBalance;
	}

	public double calcSavingWithdraw(double amount) {
		savingBalance = (savingBalance - amount);
		return savingBalance;
	}

	public double calcCheckingDeposit(double amount) {
		checkingBalance = (checkingBalance + amount);
		return checkingBalance;
	}

	public double calcSavingDeposit(double amount) {
		savingBalance = (savingBalance + amount);
		return savingBalance;
	}

	public void calcCheckTransfer(double amount) {
		checkingBalance = checkingBalance - amount;
		savingBalance = savingBalance + amount;
	}

	public void calcSavingTransfer(double amount) {
		savingBalance = savingBalance - amount;
		checkingBalance = checkingBalance + amount;
	}

	public void getCheckingWithdrawInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
				System.out.print("\nAmount you want to withdraw from Checking Account: ");
				double amount = input.nextDouble();
				if ((checkingBalance - amount) >= 0 && amount >= 0) {
					calcCheckingWithdraw(amount);
					System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
					end = true;
				} else {
					System.out.println("\nBalance Cannot be Negative.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}

	public void getsavingWithdrawInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
				System.out.print("\nAmount you want to withdraw from Savings Account: ");
				double amount = input.nextDouble();
				if ((savingBalance - amount) >= 0 && amount >= 0) {
					calcSavingWithdraw(amount);
					System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
					end = true;
				} else {
					System.out.println("\nBalance Cannot Be Negative.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}

	public void getCheckingDepositInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
				System.out.print("\nAmount you want to deposit from Checking Account: ");
				double amount = input.nextDouble();
				if ((checkingBalance + amount) >= 0 && amount >= 0) {
					calcCheckingDeposit(amount);
					System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
					end = true;
				} else {
					System.out.println("\nBalance Cannot Be Negative.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}

	public void getSavingDepositInput() {
		boolean end = false;
		while (!end) {
			try {
				System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
				System.out.print("\nAmount you want to deposit into your Savings Account: ");
				double amount = input.nextDouble();

				if ((savingBalance + amount) >= 0 && amount >= 0) {
					calcSavingDeposit(amount);
					System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
					end = true;
				} else {
					System.out.println("\nBalance Cannot Be Negative.");
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}

	public void getTransferInput(String accType) {
		boolean end = false;
		while (!end) {
			try {
				if (accType.equals("Checking")) {
					System.out.println("\nSelect an account you wish to transfer funds to:");
					System.out.println("1. Savings");
					System.out.println("2. Exit");
					System.out.print("\nChoice: ");
					int choice = input.nextInt();
					switch (choice) {
						case 1:
							System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
							System.out.print("\nAmount you want to deposit into your Savings Account: ");
							double amount = input.nextDouble();
							if ((savingBalance + amount) >= 0 && (checkingBalance - amount) >= 0 && amount >= 0) {
								calcCheckTransfer(amount);
								System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
								System.out.println("\nCurrent Checking Account Balance: " + moneyFormat.format(checkingBalance));
								end = true;
							} else {
								System.out.println("\nBalance Cannot Be Negative.");
							}
							break;
						case 2:
							return;
						default:
							System.out.println("\nInvalid Choice.");
							break;
					}
				} else if (accType.equals("Savings")) {
					System.out.println("\nSelect an account you wish to transfer funds to: ");
					System.out.println("1. Checking");
					System.out.println("2. Exit");
					System.out.print("\nChoice: ");
					int choice = input.nextInt();
					switch (choice) {
						case 1:
							System.out.println("\nCurrent Savings Account Balance: " + moneyFormat.format(savingBalance));
							System.out.print("\nAmount you want to deposit into your savings account: ");
							double amount = input.nextDouble();
							if ((checkingBalance + amount) >= 0 && (savingBalance - amount) >= 0 && amount >= 0) {
								calcSavingTransfer(amount);
								System.out.println("\nCurrent checking account balance: " + moneyFormat.format(checkingBalance));
								System.out.println("\nCurrent savings account balance: " + moneyFormat.format(savingBalance));
								end = true;
							} else {
								System.out.println("\nBalance Cannot Be Negative.");
							}
							break;
						case 2:
							return;
						default:
							System.out.println("\nInvalid Choice.");
							break;
					}
				}
			} catch (InputMismatchException e) {
				System.out.println("\nInvalid Choice.");
				input.next();
			}
		}
	}
}
