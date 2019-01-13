public abstract class BankAccount 
{
		private double balance;
		private int accNum;
		private String name;
		private static int nextAccNum = 1;
		
		
		/**
		 * constructor that makes a bank account with parameters name and balance
		 * @param name the name of the account holder
		 * @param balance the amount of money initially put into the account, or the balance
		 */
		public BankAccount(String name, double balance)
		{
			this.name = name;
			this.balance = balance;
			accNum = nextAccNum;
			nextAccNum++;
			
		}
		
		/**
		 * *constructor that makes a bank account with parameter name
		 * @param name the name of the account holder
		 */
		public BankAccount(String name)
		{
			this(name, 0);
		}
	
		/**
		 * deposits money into the account
		 * @param amt amount that you want to deposit
		 */
		public void deposit(double amt)
		{
			balance = balance + amt;
		}
		
		/**
		 * withdraws money from the account
		 * @param amt amount that you want to withdraw
		 */
		public void withdraw(double amt)
		{
			balance = balance - amt;
		}
		
		/**
		 * returns the balance
		 * @return returns the balance in the account
		 */
		public double getBalance()
		{
			return balance;
		}
		
		/**
		 * returns the account number of the account
		 * @return returns the account number of the account
		 */
		public int getAccNum()
		{
			return accNum;
		}
		
		/**
		 * returns the name of the account holder
		 * @return the name of the account holder
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
		 * transfers money from one account to another account
		 * @param other the other bank account that you want to deposit money into
		 * @param amt the amount of money you want to transfer
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
		 * displays the account number, account holder, and balance of an account
		 */
		public String toString()
		{
			return "Account Number: " + accNum + "\tAccount Holder: " + name + "\tBalance: " + balance;
			
		}
}