
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions = 0;
	
	/**
	 * 
	 * @param name the name of the account holder
	 * @param balance the initial balance you want in the account
	 * @param odf the overdraft fee of the account 
	 * @param tf the transaction fee of the account
	 * @param freeTrans the number of free transactions you get for the account
	 */
	public CheckingAccount(String name, double balance, double odf, double tf, double freeTrans) 
	{
		super(name, balance);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	/**
	 * 
	 *@param name the name of the account holder
	 * @param odf the overdraft fee of the account 
	 * @param tf the transaction fee of the account
	 * @param freeTrans the number of free transactions you get for the account
	 */
	public CheckingAccount(String name, double odf, double tf, double freeTrans)
	{
		super(name ,0);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	/**
	 * @param the amount you want to deposit
	 */
	public void deposit(double amt)
	{
		if(amt < 0)
		{
			throw new IllegalArgumentException();
		}
		
		super.deposit(amt);
		
		if(numTransactions >= FREE_TRANS)
		{
			super.withdraw(TRANSACTION_FEE);
		}
		
		numTransactions++;
	}
	
	/**
	 * @param amt the amount you want to withdraw
	 */
	public void withdraw(double amt)
	{
		if(amt < 0 || super.getBalance() < 0)
		{
			throw new IllegalArgumentException();
		}
		
		super.withdraw(amt);
		
		numTransactions++;
		
		if(numTransactions > FREE_TRANS)
		{
			super.withdraw(TRANSACTION_FEE);
		}
		
		if((super.getBalance()) < 0)
		{
			super.withdraw(OVER_DRAFT_FEE);
		}
		
	}
	/**
	 * updates the account at the end of the month
	 */
	public void endOfMonthUpdate() 
	{
		numTransactions = 0;
	}
}
