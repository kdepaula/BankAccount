
public class SavingsAccount extends BankAccount
{
	private double intRate;
	private final double MIN_BAL;
	private final double MIN_BAL_FEE;
	
	/**
	 * 
	 * @param name
	 * @param balance
	 * @param intRate
	 * @param mb
	 * @param mbf
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
	 * @param name
	 * @param intRate
	 * @param mb
	 * @param mbf
	 */
	public SavingsAccount(String name, double intRate, double mb, double mbf)
	{
		this(name, 0, intRate, mb, mbf);
	}
	
	/**
	 * 
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
	 * 
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
	 * 
	 */
	public void endOfMonthUpdate() 
	{
		deposit(getBalance() * intRate);
	}
	
	/**
	 * 
	 */
	public void addInterest()
	{
		deposit(getBalance() * intRate);
	}
		
}

