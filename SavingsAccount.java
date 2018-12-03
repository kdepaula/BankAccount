
public class SavingsAccount extends BankAccount
{

	public SavingsAccount(String holder, int num, double startBal) 
	{
		super(holder, num, startBal);
	}

	public final double INT_RATE = 0.25;
	
	public void addInterest()
	{
		deposit(getBalance() * INT_RATE);
	}
}
