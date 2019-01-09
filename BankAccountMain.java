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
			System.out.println("Would you like to add an account, make a transaction, or terminate the program? Type \"add\", \"transaction\", or \"terminate\"");
			String answer = in.next().toLowerCase();
			in.nextLine();
			switch (answer)
			{
				case "add":
				{
					System.out.println("What is your name?");
					name = in.next();
					System.out.println("Would you like to create a checking or savings account? Type \"checking\" or \"savings\".");
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
							try
							{
								accounts.get(accounts.size()-1).deposit(Double.parseDouble(bal));
							}
							
							catch(IllegalArgumentException e)
							{
								System.out.println("The initial deposit is invalid. The account was not created.");
							}
							
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
							accounts.add(new SavingsAccount(name, RATE, MIN_BAL, MIN_BAL_FEE));
							try
							{
								accounts.get(accounts.size()-1).deposit(Double.parseDouble(bal));
							}
							
							catch(IllegalArgumentException e)
							{
								System.out.println("The initial deposit is invalid. The account was not created.");
							}
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
									try
									{
										accounts.get(accounts.size()-1).deposit(Double.parseDouble(bal));
									}
									
									catch(IllegalArgumentException e)
									{
										System.out.println("The initial deposit is invalid. The account was not created.");
									}
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
									accounts.add(new SavingsAccount(name, RATE, MIN_BAL, MIN_BAL_FEE));
									try
									{
										accounts.get(accounts.size()-1).deposit(Double.parseDouble(bal));
									}
									
									catch(IllegalArgumentException e)
									{
										System.out.println("The initial deposit is invalid. The account was not created.");
									}
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
					boolean validName = false;
					boolean play = true;
					System.out.println("Would you like to deposit, withdraw, transfer, or get account numbers?");
					while(play)
					{
						answer = in.nextLine();
						switch(answer)
						{
							case "deposit":
							{
								System.out.println("What is your account number?");
								num = in.nextLine();
								while(!isNumeric(num))
								{
									System.out.println("Please type a number.");
									num = in.nextLine();
								}
									for(int i = 0; i < accounts.size(); i++)
									{
										if(accounts.get(i).getAccNum() == Integer.parseInt(num))
										{
											System.out.println(accounts.get(i).toString());
											currentAccount = accounts.get(i);
										}
									}
								
								while(currentAccount == null)
								{
									System.out.println("That was not a valid account number. Either enter your name to find your accounts, or reenter your account number.");
									answer = in.nextLine();
									if(!isNumeric(answer))
									{
										do
										{
											for(int i = 0; i < accounts.size(); i++)
												{
													if(accounts.get(i).getName().equals(answer))
													{
															System.out.println(accounts.get(i).toString());
															validName = true;
															if(accounts.get(i) instanceof CheckingAccount)
															{
																System.out.print("Account Type: Checking Account ");
															}
															else 
															{
																System.out.print("Account Type: Savings Account");
															}
													}
												}
												
												if(!validName)
												{
													System.out.println("There are currently no accounts under that name. Enter your name again to find your accounts.");
													answer = in.nextLine();
												}
										}while(!validName);
										
										if(currentAccount == null)
										{
											System.out.println("Enter the account number of the account you would like to choose to deposit.");
											num = in.nextLine();
											while(!isNumeric(num))
											{
												System.out.println("Please type a number.");
												num = in.nextLine();
											}
											for(int i = 0; i < accounts.size(); i++)
											{
												if(accounts.get(i).getAccNum() == Integer.parseInt(num))
												{
													currentAccount = accounts.get(i);
												}
											}
										}
									}
										
									else
									{
										for(int i = 0; i < accounts.size(); i++)
										{
											if(accounts.get(i).getAccNum() == Integer.parseInt(answer))
											{
												System.out.println(accounts.get(i).toString());
												currentAccount = accounts.get(i);
											}
										}
									}
								}
								
						System.out.println("How much would you like to deposit?");
								amt = in.nextLine();
								if(!isNumeric(amt))
								{
									System.out.println("Please type a number.");
									amt = in.nextLine();
								}
								
								try
								{
									currentAccount.deposit(Double.parseDouble(amt));
								}
								
								catch(IllegalArgumentException e)
								{
									System.out.println("That number is illegal. The transaction did not go through");
								}
								play = false;
								break;
						}
						
						case "withdraw":
						{
							System.out.println("What is your account number?");
							num = in.nextLine();
							while(!isNumeric(num))
							{
								System.out.println("Please type a number.");
								num = in.nextLine();
							}
								for(int i = 0; i < accounts.size(); i++)
								{
									if(accounts.get(i).getAccNum() == Integer.parseInt(num))
									{
										System.out.println(accounts.get(i).toString());
										currentAccount = accounts.get(i);
									}
								}
							
							while(currentAccount == null)
							{
								System.out.println("That was not a valid account number. Either enter your name to find your accounts, or reenter your account number.");
								answer = in.nextLine();
								if(!isNumeric(answer))
								{
									do
									{
										for(int i = 0; i < accounts.size(); i++)
											{
												if(accounts.get(i).getName().equals(answer))
												{
														System.out.println(accounts.get(i).toString());
														validName = true;
														if(accounts.get(i) instanceof CheckingAccount)
														{
															System.out.print("Account Type: Checking Account ");
														}
														else 
														{
															System.out.print("Account Type: Savings Account");
														}
												}
											}
											
											if(!validName)
											{
												System.out.println("There are currently no accounts under that name. Enter your name again to find your accounts.");
												answer = in.nextLine();
											}
									}while(!validName);
									
									if(currentAccount == null)
									{
										System.out.println("Enter the account number of the account you would like to choose to deposit.");
										num = in.nextLine();
										while(!isNumeric(num))
										{
											System.out.println("Please type a number.");
											num = in.nextLine();
										}
										for(int i = 0; i < accounts.size(); i++)
										{
											if(accounts.get(i).getAccNum() == Integer.parseInt(num))
											{
												currentAccount = accounts.get(i);
											}
										}
									}
								}
									
								else
								{
									for(int i = 0; i < accounts.size(); i++)
									{
										if(accounts.get(i).getAccNum() == Integer.parseInt(answer))
										{
											System.out.println(accounts.get(i).toString());
											currentAccount = accounts.get(i);
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
						try
						{
							currentAccount.withdraw(Double.parseDouble(amt));
						}
						
						catch(IllegalArgumentException e)
						{
							System.out.println("That number is illegal. The transaction did not go through");
						}
						play = false;
						break;
						}
						
						case "transfer":
						{
							System.out.println("What is your account number?");
							num = in.nextLine();
							while(!isNumeric(num))
							{
								System.out.println("Please type a number.");
								num = in.nextLine();
							}
								for(int i = 0; i < accounts.size(); i++)
								{
									if(accounts.get(i).getAccNum() == Integer.parseInt(num))
									{
										System.out.println(accounts.get(i).toString());
										currentAccount = accounts.get(i);
									}
								}
							
							while(currentAccount == null)
							{
								System.out.println("That was not a valid account number. Either enter your name to find your accounts, or reenter your account number.");
								answer = in.nextLine();
								if(!isNumeric(answer))
								{
									do
									{
										for(int i = 0; i < accounts.size(); i++)
											{
												if(accounts.get(i).getName().equals(answer))
												{
														System.out.println(accounts.get(i).toString());
														validName = true;
														if(accounts.get(i) instanceof CheckingAccount)
														{
															System.out.println("Account Type: Checking Account ");
														}
														else 
														{
															System.out.println("Account Type: Savings Account  ");
														}
												}
											}
											
											if(!validName)
											{
												System.out.println("There are currently no accounts under that name. Enter your name again to find your accounts.");
												answer = in.nextLine();
											}
									}while(!validName);
									
									if(currentAccount == null)
									{
										System.out.println("Enter the account number of the account you would like to choose to deposit.");
										num = in.nextLine();
										while(!isNumeric(num))
										{
											System.out.println("Please type a number.");
											num = in.nextLine();
										}
										for(int i = 0; i < accounts.size(); i++)
										{
											if(accounts.get(i).getAccNum() == Integer.parseInt(num))
											{
												currentAccount = accounts.get(i);
											}
										}
									}
								}
									
								else
								{
									for(int i = 0; i < accounts.size(); i++)
									{
										if(accounts.get(i).getAccNum() == Integer.parseInt(answer))
										{
											System.out.println(accounts.get(i).toString());
											currentAccount = accounts.get(i);
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
							try
							{
								currentAccount.transfer(secAccount, Double.parseDouble(amt));
							}
							
							catch(IllegalArgumentException e)
							{
								System.out.println("That number is illegal. The transaction did not go through");
							
							}
							play = false;
							break;
						}
						
						case "account numbers":
						{
							if(accounts.get(0) == null)
							{
								System.out.println("There are currently no existing accounts. However, you can make an account if you choose" );
								play = false;
							}
							
							else 
							{
							System.out.println("What is your name?");
							name = in.nextLine();
								do
								{
									for(int i = 0; i < accounts.size(); i++)
									{
										if(accounts.get(i).getName().equals(name))
										{
												System.out.println(accounts.get(i).toString());
												validName = true;
												if(accounts.get(i) instanceof CheckingAccount)
												{
													System.out.println("Account Type: Checking Account ");
													play = false;
												}
												else
												{
													System.out.println("Account Type: Savings Account");
													play = false;
												}
										}
									}
									
									if(!validName)
									{
										System.out.println("There are currently no accounts under that name. Enter your name again to find your accounts.");
										answer = in.nextLine();
									}
								}while(!validName);
							}
							
							break;
						}
						
						default:
						{
								System.out.println("That was not a valid answer. Make sure to type \"deposit\", \"withdraw\", \"transfer\", or \"account numbers\".");	
								break;
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
						System.out.println("That was not a valid answer.");	
						break;
				}
			}
		}
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
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