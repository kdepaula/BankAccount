
public class CheckingAccount extends BankAccount
{
	private final double OVER_DRAFT_FEE;
	private final double TRANSACTION_FEE;
	private final double FREE_TRANS;
	private int numTransactions = 0;
	

	public CheckingAccount(String name, double balance, double odf, double tf, double freeTrans) 
	{
		super(name, balance);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
	public CheckingAccount(String name, double odf, double tf, double freeTrans)
	{
		super(name ,0);
		OVER_DRAFT_FEE = odf;
		TRANSACTION_FEE = tf;
		FREE_TRANS = freeTrans;
	}
	
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

	public void endOfMonthUpdate() 
	{
		numTransactions = 0;
	}
}
