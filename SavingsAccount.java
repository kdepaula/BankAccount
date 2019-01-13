public class SavingsAccount extends BankAccount
{
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	/**
	 * constructor that makes a savings account with parameters name, balance, interest rate, minimum balance, and minimum balance fee
	 * @param name the name of the account holder
	 * @param balance the balance you want to initially put into the account
	 * @param intRate the interest rate on the account
	 * @param mb the minimum balance in the account
	 * @param mbf the minimum balance fee of the account if the minimum balance is not met
	 */
	public SavingsAccount(String name, double balance, double intRate, double mb, double mbf) 
	{
		super(name, balance);
		this.intRate = intRate;
		MIN_BAL = mb;
		MIN_BAL_FEE = mbf;
	}
	
	/**
	 * 
	 * constructor that makes a savings account with parameters name, interest rate, minimum balance, and minimum balance fee
	 * @param name the name of the account holder
	 * @param intRate the interest rate on the account
	 * @param mb the minimum balance in the account
	 * @param mbf the minimum balance fee of the account if the minimum balance is not met
	 */
	public SavingsAccount(String name, double intRate, double mb, double mbf)
	{
		this(name, 0, intRate, mb, mbf);
	}
	
	/**
	 * withdraws money from the account
	 * @param amt the amount of money you want to withdraw
	 * withdraws the amount of money in the account
	 */
	public void withdraw(double amt)
	{
		if(amt < 0 || super.getBalance() - amt < 0)
		{
			throw new IllegalArgumentException();
		}
		
		super.withdraw(amt);
		
		if(this.getBalance() < MIN_BAL)
		{
			super.withdraw(MIN_BAL_FEE);
		}
	}
	
	/**
	 * deposits money into the account
	 * @param amt the amount you want to deposit
	 */
	public void deposit(double amt)
	{
		if(amt<0)
		{
			throw new IllegalArgumentException();
		}
		super.deposit(amt);
	}
	
	/**
	 * updates the account at the end of the month
	 */
	public void endOfMonthUpdate() 
	{
		deposit(getBalance() * intRate);
	}
	
	/**
	 * adds the interest on the account
	 */
	public void addInterest()
	{
		deposit(getBalance() * intRate);
	}
		
}

