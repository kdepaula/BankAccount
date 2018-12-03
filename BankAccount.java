
public class BankAccount 
{
		private double balance;
		private int accNum;
		private String name;
		

		public BankAccount(String holder, int num)
		{
			name = holder;
			accNum = num;
			balance = 0;
			
			//you cannot name arguments the same name as the field
		}
		
		public BankAccount(String holder, int num, double startBal)
		
		{
			name = holder;
			accNum = num;
			balance = startBal;
		}
		
		public void deposit(double amt)
		{
			balance = balance + amt;
		}
		
		public void withdraw(double amt)
		{
			balance = balance - amt;
		}
		
		public double getBalance()
		{
			return balance;
		}
		
		public String toString()
		{
			return "Account Holder: " + name + "\tAccount Number: " + accNum + "\tBalance: " + balance;
			
		}
//the  toString() method returns the value of all the fields
}
