
package atm;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class ATM {
	private double bal;
    private String pin;
    private double withdrawalLim;
    private double dailyWithdrawn;
    private ArrayList<String> transHist;

    private final String bal_FILE = "bal.txt";
    private final String TRANSACTION_FILE = "transactions.txt";

    public ATM(String pin, double withdrawalLim) {
        this.pin = pin;
        this.withdrawalLim = withdrawalLim;
        this.dailyWithdrawn = 0;
        this.transHist = new ArrayList<>();
        loadData();
    }

    // Load balance and transactions from files
    private void loadData() {
        try {
            // Loading balance
            File balFile = new File(bal_FILE);
            if (balFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(balFile));
                bal = Double.parseDouble(br.readLine());
                br.close();
            } else {
                bal = 5000; // default 
                savebal();
            }

            // Load transaction history
            File transFile = new File(TRANSACTION_FILE);
            if (transFile.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(transFile));
                String line;
                while ((line = br.readLine()) != null) {
                    transHist.add(line);
                }
                br.close();
            }
        } catch (Exception e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    // Save balance to file
    private void savebal() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(bal_FILE))) {
            bw.write(Double.toString(bal));
        } catch (IOException e) {
            System.out.println("Error saving bal: " + e.getMessage());
        }
    }

    // Save transactions to file
    private void saveTransactions() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(TRANSACTION_FILE))) {
            for (String transaction : transHist) {
                bw.write(transaction);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving transactions: " + e.getMessage());
        }
    }

    public boolean checkPin(String enteredPin) {
        return this.pin.equals(enteredPin);
    }

    public void checkbal() {
        System.out.println("Current bal:₹" + bal);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            bal += amount;
            transHist.add("Deposited: ₹" + amount);
            savebal();
            saveTransactions();
            System.out.println("Deposit Successful");
        } else {
            System.out.println("Invalid deposit amount");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount");
        }
        if (amount > bal) {
            System.out.println("Insufficient balance");
        }
        if (dailyWithdrawn + amount > withdrawalLim) {
            System.out.println("Daily withdrawal limit exceeded!");
        }
        bal -= amount;
        dailyWithdrawn += amount;
        transHist.add("Withdrew: ₹" + amount);
        savebal();
        saveTransactions();
        System.out.println("Withdrawal Successful!");
    }

    public void showtransHist() {
        if (transHist.isEmpty()) {
            System.out.println("No transactions yet");
        } else {
            System.out.println("Transaction History");
            for (String transaction : transHist) {
                System.out.println(transaction);
            }
        }
    }
}
