
public abstract class BankAccount 
{
		private double balance;
		private int accNum;
		private String name;
		private static int nextAccNum = 1;
		

		public BankAccount(String name, double balance)
		{
			this.name = name;
			this.balance = balance;
			accNum = nextAccNum;
			nextAccNum++;
			
		}
		
		public BankAccount(String name)
		{
			this(name, 0);
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
		
		public int getAccNum()
		{
			return accNum;
		}
		
		public String getName()
		{
			return name;
		}
		
		public abstract void endOfMonthUpdate();
		
		public void transfer(BankAccount other, double amt)
		{
			if(amt < 0 || this.getBalance() - amt < 0 || !other.getName().equals(this.getName()))
			{
				throw new IllegalArgumentException();
			}
			withdraw(amt);
			other.deposit(amt);
		}
		
		public String toString()
		{
			return "Account Number: " + accNum + "\tAccount Holder: " + name + "\tBalance: " + balance;
			
		}
}
