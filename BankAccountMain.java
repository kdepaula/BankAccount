
public class BankAccountMain {

	public static void main(String[] args) 
	{
		BankAccount acc = new BankAccount("Katelyn", 122);
		BankAccount acc2 = new BankAccount("Dean", 133, 10);
		System.out.println(acc.getBalance());
		acc.deposit(500);
		acc2.withdraw(100);
		System.out.println(acc);
		System.out.println(acc2);
		acc.deposit(1000);
		System.out.println(acc.getBalance());
	}

}
