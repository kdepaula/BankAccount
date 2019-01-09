
public abstract class BankAccount 
{
		private double balance;
		private int accNum;
		private String name;
		private static int nextAccNum = 1;
		
		
		/**
		 * 
		 * @param name
		 * @param balance
		 */
		public BankAccount(String name, double balance)
		{
			this.name = name;
			this.balance = balance;
			accNum = nextAccNum;
			nextAccNum++;
			
		}
		
		/**
		 * 
		 * @param name
		 */
		public BankAccount(String name)
		{
			this(name, 0);
		}
	
		/**
		 * 
		 * @param amt
		 */
		public void deposit(double amt)
		{
			balance = balance + amt;
		}
		
		/**
		 * 
		 * @param amt
		 */
		public void withdraw(double amt)
		{
			balance = balance - amt;
		}
		
		/**
		 * 
		 * @return
		 */
		public double getBalance()
		{
			return balance;
		}
		
		/**
		 * 
		 * @return
		 */
		public int getAccNum()
		{
			return accNum;
		}
		
		/**
		 * 
		 * @return
		 */
		public String getName()
		{
			return name;
		}
		
		/**
		 * updates the account at the end of the month
		 */
		public abstract void endOfMonthUpdate();
		
		/**
		 * 
		 * @param other
		 * @param amt
		 */
		public void transfer(BankAccount other, double amt)
		{
			if(amt < 0 || this.getBalance() - amt < 0 || !other.getName().equals(this.getName()))
			{
				throw new IllegalArgumentException();
			}
			withdraw(amt);
			other.deposit(amt);
		}
		
		/**
		 * 
		 */
		public String toString()
		{
			return "Account Number: " + accNum + "\tAccount Holder: " + name + "\tBalance: " + balance;
			
		}
}
