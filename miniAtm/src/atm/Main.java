package atm;
import java.util.*;
public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        ATM atm = new ATM("1234", 20000);
        System.out.print("Enter PIN: ");
        String enteredPin = sc.nextLine();
        
        int flag=0,count=3;
        String tryagain="";
        if(count>0) {
        	
	        if (!atm.checkPin(enteredPin)) {
	        	flag=1;
	            System.out.println("Incorrect PIN. Exiting...");
	            while(count>0) {
	            	System.out.println("Try Again");
	            	tryagain=sc.nextLine();
	            	if(atm.checkPin(tryagain)) {
	            		flag=0;
	            		break;	         
	            	}
	            	else {
	            		count--;
	            	}
	            }
	        }
        }
        if(flag==0) {
	        int choice;
	        do {
	            System.out.println("\nATM Menu");
	            System.out.println("1. Check Balance");
	            System.out.println("2. Deposit");
	            System.out.println("3. Withdraw");
	            System.out.println("4. Transaction History");
	            System.out.println("5. Exit");
	            System.out.print("Choose an option: ");
	            choice = sc.nextInt();
	
	            switch (choice) {
	                case 1:
	                    atm.checkbal();
	                    break;
	                case 2:
	                    System.out.print("Enter deposit amount: ");
	                    double depAmount = sc.nextDouble();
	                    atm.deposit(depAmount);
	                    break;
	                case 3:
	                    System.out.print("Enter withdrawal amount: ₹");
	                    double withdrawAmount = sc.nextDouble();
	                    atm.withdraw(withdrawAmount);
	                    break;
	                case 4:
	                    atm.showtransHist();
	                    break;
	                case 5:
	                    System.out.println("Thank you for using ATM");
	                    break;
	                default:
	                    System.out.println("Invalid option.");
	            }
	        } while (choice != 5 && count>0);
        }
        else if(count==0){
        	System.out.println("Account blocked temperorily. Contact bank");
        }

	}

}
