/**
 * 
 * @author kdepa_t74iu3m
 *
 */
public class BankAccount 
{
	private double balance;
	private String accNum;
	private String name;
	

	public BankAccount(String holder, String num)
	{
		name = holder;
		accNum = num;
		balance = 0;
	}
	
	public BankAccount(String holder, String num, double startBal)
	
	{
		name = holder;
		accNum = num;
		balance = startBal;
	}
	
	public void deposit(double a)
	{
		balance = balance + a;
	}
	
	public void withdraw(double b)
	{
		balance = balance - b;
	}
	
	public double getBalance()
	{
		return balance;
	}
	
	public String toString()
	{
		return "Balance: " + balance;
		
	}
}
