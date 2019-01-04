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
								if(answer.equals("checking"))
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
									play = false;
								}
								
								if(answer.equals("savings"))
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
					String num;
					String amt;
					boolean validResponse = true;
					int currentAccNum = 0;
					System.out.println("Would you like to deposit, withdraw, transfer, or get account numbers?");
					answer = in.next();
					in.nextLine();
					switch(answer)
					{
						case "deposit":
						{
							boolean again = true;
							do
							{
								System.out.println("What is your account number?");
								num = in.nextLine();
								if(!isNumeric(num))
								{
									System.out.println("Please type a number.");
									num = in.nextLine();
								}
								while(currentAccount == null)
								{
									for(int i = 0; i < accounts.size(); i++)
									{
										if(accounts.get(i).getAccNum() == Integer.parseInt(num))
										{
											System.out.println(accounts.get(i).toString());
											currentAccount = accounts.get(i);
										}
									}
								
									if(currentAccount == null)
									{
										System.out.println("That was not a valid account number. Type \"reenter\" to reenter your account number or type \"name\" to use your name to find your accounts.");
										answer = in.nextLine();
										switch(answer)
										{
											case "name":
											{	
												do
												{
													System.out.println("What is your name?");
													name = in.nextLine();
													int i;
													for(i = 0; i < accounts.size(); i++)
													{
														if(accounts.get(i).getName().equals(name))
														{
															System.out.println(accounts.get(i).toString());
														}
													}
													while(!accounts.get(i).getName().equals(name))
													{
														System.out.println("There are currently no accounts under that name. Please enter your name again to see all your accounts.");
													}
												} while(!validResponse);
												break;
											}
											
											case "reenter":
											{
												
											}
											
											default:
											{
												System.out.println("Please type name or reenter");
												answer = in.nextLine();
												break;
											}
									}
									}
								}
							} while(again);
							System.out.println("How much would you like to deposit?");
							amt = in.nextLine();
							if(!isNumeric(amt))
							{
								System.out.println("Please type a number.");
								amt = in.nextLine();
							}
							currentAccount.deposit(Double.parseDouble(amt));
							break;
						}
					
						case "withdraw":
						{
							System.out.println("What is your account number?");
							num = in.nextLine();
							if(!isNumeric(num))
							{
								System.out.println("Please type a number.");
								num = in.nextLine();
							}
							while(currentAccount == null)
							{
								for(int i = 0; i < accounts.size(); i++)
								{
									if(accounts.get(i).getAccNum() == Integer.parseInt(num))
									{
										System.out.println(accounts.get(i).toString());
										currentAccount = accounts.get(i);
										currentAccNum = accounts.get(i).getAccNum();
									}
								}
								
								if(currentAccount == null)
								{
									validResponse = true;
									while(validResponse)
									{
										System.out.println("That was not a valid account number. Please enter your name to see your accounts.");
										name = in.nextLine();
										for(int i = 0; i < accounts.size(); i++)
										{
											if(accounts.get(i).getName().equals(name))
											{
												System.out.println(accounts.get(i).toString());
												validResponse = false;
											}
										}
										if(validResponse)
										{
											System.out.println("There are currently no accounts under that name. Please enter your name again to see all your accounts.");
										}
									}
								}
							}
							System.out.println("How much would you like to withdraw?");
							amt = in.nextLine();
							if(!isNumeric(amt))
							{
								System.out.println("Please type a number.");
								amt = in.nextLine();
							}
							currentAccount.withdraw(Double.parseDouble(amt));
							break;	
						}
						
						case "transfer":
						{
							System.out.println("What is your account number?");
							num = in.nextLine();
							if(!isNumeric(num))
							{
								System.out.println("Please type a number.");
								num = in.nextLine();
							}
							while(currentAccount == null)
							{
								for(int i = 0; i < accounts.size(); i++)
								{
									if(accounts.get(i).getAccNum() == Integer.parseInt(num))
									{
										System.out.println(accounts.get(i).toString());
										currentAccount = accounts.get(i);
										currentAccNum = accounts.get(i).getAccNum();
									}
								}
								
								if(currentAccount == null)
								{
									validResponse = true;
									while(validResponse)
									{
										System.out.println("That was not a valid account number. Please enter your name to see your accounts.");
										name = in.nextLine();
										for(int i = 0; i < accounts.size(); i++)
										{
											if(accounts.get(i).getName().equals(name))
											{
												System.out.println(accounts.get(i).toString());
												validResponse = false;
											}
										}
										if(validResponse)
										{
											System.out.println("There are currently no accounts under that name. Please enter your name again to see all your accounts.");
										}
									}
								}
							}
							BankAccount secAccount = null;
							System.out.println("What is the account number of the account you would like to transfer money to?");
							num = in.nextLine();
							if(!isNumeric(num))
							{
								System.out.println("Please type a number.");
								num = in.nextLine();
							}
							while(secAccount == null)
							{
								for(int i = 0; i < accounts.size(); i++)
								{
									if(accounts.get(i).getAccNum() == Integer.parseInt(num))
									{
										System.out.println(accounts.get(i).toString());
										secAccount = accounts.get(i);
									}
								}
								
								if(secAccount == null || secAccount == currentAccount)
								{
									System.out.println("Please enter the account number again. That was not a valid account number");
									num = in.next();
									in.nextLine();
								}

							}
							
							System.out.println("What is the amount you would like to transfer?");
							amt = in.nextLine();
							if(!isNumeric(amt))
							{
								System.out.println("Please type a number.");
								amt = in.nextLine();
							}
							currentAccount.transfer(secAccount, Double.parseDouble(amt));
							break;
						}
						
						case "get account number":
						{
							System.out.println(currentAccNum);
						}
						
						default:
						{
							boolean play = true;
							while(play)
							{
								System.out.println("Please type deposit, withdraw, transfer, or get account number");	
								answer = in.next();
								in.nextLine();
								if(answer.equals("deposit") || answer.equals("withdraw") || answer.equals("transfer") || answer.equals("get account number"))
								{	
								play = false;
								}
							}
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
						System.out.println("Please type account, transaction, or terminate.");	
						answer = in.next();
				}
			}
		}
		
		System.out.println("remember to put JAVA COMMENTS DONT FORGET");
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