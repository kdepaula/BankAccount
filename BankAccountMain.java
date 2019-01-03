import java.util.ArrayList;
import java.util.Scanner;

public class BankAccountMain 
{
	private static final double OVER_DRAFT_FEE = 15;
	private static final double RATE = .0025;
	private static final double TRANSACTION_FEE = 1.5;
	private static final double MIN_BAL = 300;
	private static final double MIN_BAL_FEE = 10;
	private static final double FREE_TRANSACTIONS = 10;

	public static void main(String[] args) 
	{
		Scanner in = new Scanner(System.in);
		ArrayList<BankAccount> accounts = new ArrayList<BankAccount>();
		boolean playAgain = true;
		while(playAgain)
		{
			String bal;
			String name;
			System.out.println("Would you like to add an account, make a transaction, or terminate the program?");
			String answer = in.next().toLowerCase();
			in.nextLine();
			switch (answer)
			{
				case "account":
				{
					System.out.println("What is your name?");
					name = in.next();
					System.out.println("Would you like to create a checking or savings account?");
					answer= in.next().toLowerCase();
					in.nextLine();
					switch (answer)
					{
						case "checking":
						{
							
							System.out.println("How much money would you like to put into the account initially?");
							bal = in.nextLine();
							while(!isNumeric(bal))
							{
								System.out.println("Please type a number.");
								bal = in.next();
								in.nextLine();
							}
							accounts.add(new CheckingAccount(name, OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANSACTIONS));
							break;
						}
						
						case "savings":
						{
							System.out.println("How much money would you like to put into the account initially?");
							bal = in.next();
							in.nextLine();
							while(!isNumeric(bal))
							{
								System.out.println("Please type a number.");
								bal = in.next();
								in.nextLine();
							}
							accounts.add(new SavingsAccount(name, Double.parseDouble(bal), RATE, MIN_BAL, MIN_BAL_FEE));
							break;
						}
					
						default:
						{
							boolean play = true;
							while(play)
							{
								System.out.println("Please type checking or savings");	
								answer = in.next();
								in.nextLine();
								if(answer.equals("checking") || answer.equals("savings"))
								{	
								play = false;
								}
							}
							break;
						}
					}
					break;
				}
				case "transaction":
				{
					BankAccount currentAccount = null;
					System.out.println("What is your account number?");
					int num = in.nextInt();
					in.nextLine();
					while(currentAccount == null)
					{
						for(int i = 0; i < accounts.size(); i++)
						{
							if(accounts.get(i).getAccNum() == num)
							{
								System.out.println(accounts.get(i).toString());
								currentAccount = accounts.get(i);
							}
						}
						
						if(currentAccount == null)
						{
							System.out.println("Please enter the account number again. That was not a valid number");
							num = in.nextInt();
							in.nextLine();
						}
					}
					break;
				}
				
				case"terminate":
				{
					playAgain = false;
					break;
				}
				
				default:
				{
					System.out.println("Please type account or transaction or terminate");
					break;
				}
			}
		}
	}

	private static boolean isNumeric(String str) 
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
		
		catch(IllegalArgumentException e)
		{
			return false;
		}
	}
	
}
