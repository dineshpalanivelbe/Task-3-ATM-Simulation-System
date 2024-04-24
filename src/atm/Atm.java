package atm;
import java.sql.*;
import java.util.Scanner;
public class Atm {
	public static void main(String[] args) {
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/atmlist","root","");
			Statement stmt=con.createStatement();


			try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Hey Welcome To Our ATM");
			System.out.println("Enter Your PIN Number:");
			int pin=sc.nextInt();
			ResultSet rs=stmt.executeQuery("select * from atmlist where pin="+pin);
			String name = null;
			int account_no=0;
			int count=0;
			int balance=0;
			while(rs.next()){
			account_no=rs.getInt(2);
			name=rs.getString(4);
			balance=rs.getInt(5);
			count++;
			}

			int amount = 0;
			int take_amount=0;
			int choice;

			if(count>0){
			System.out.println("Hello "+name);
			System.out.println("A/C No. : "+account_no);
			while(true){
			System.out.println("\nMain Menu:");
			System.out.println("Press 1 to Balance Inquiry");
			System.out.println("Press 2 to Deposit");
			System.out.println("Press 3 to Withdrawal");
			System.out.println("Press 4 to Print The Recipt");
			System.out.println("Press 5 to Exit");

			System.out.println();
			System.out.println("Enter Your Choice");
			choice=sc.nextInt();
			switch(choice){
			case 1:
			System.out.println("Your Balance is : "+balance);
			break;

			case 2:
			System.out.println("Enter the amount to deposit: ");
			amount=sc.nextInt();
			balance=balance+amount;
			int bal = stmt.executeUpdate("UPDATE atmlist SET balance = " + balance + " WHERE pin = " + pin);
			System.out.println("Deposit successful. Your new balance is: "+balance);
			break;

			case 3:
			System.out.print("Enter the amount to withdraw: ");
			take_amount=sc.nextInt();
			if(take_amount>balance){
			System.out.println("Your Balance is Insufficient");
			}
			else{
			balance=balance-take_amount;
			int sub = stmt.executeUpdate("UPDATE atmlist SET balance = " + balance + " WHERE pin = " + pin);
			System.out.println("Withdrawal successful. Your new balance is: "+balance);
			}
			break;

			case 4:
			System.out.println("Thanks for Coming");
			System.out.println("Your Current Balance is : "+balance);
			System.out.println("Amount added : "+amount);
			System.out.println("Amount taken : "+take_amount);
			break;

			}
			if(choice==5){
			break;
			}

			}
			}
			else{
			System.out.println("Wrong Pin Number");
			}
			}
			}
			catch(Exception e){
			System.out.println(e);
			}

	}

}
