import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Frame extends JFrame
{
	public Frame()
	{
		<BankAccount> accList = new ArrayList<BankAccount>();
		setTitle("Bank Account");
		setBounds(600, 300, 400, 250);
		setLayout(null);
		
		JLabel name = new JLabel("Name: ");
		name.setBounds(30, 30, 80, 20);
		add(name);
		
		JTextField nameTxt = new JTextField("");
		nameTxt.setBounds(140, 30, 200, 20);
		add(nameTxt);
		
		JLabel bal = new JLabel("Initial Balance: ");
		bal.setBounds(30, 90, 100, 20);
		add(bal);
		
		JTextField balTxt = new JTextField("");
		balTxt.setBounds(140, 90, 200, 20);
		add(balTxt);
		
		JLabel acc = new JLabel("Account Type: ");
		acc.setBounds(30, 60, 100, 20);
		add(acc);
		
		JComboBox type = new JComboBox(new String[] {"", "Checking", "Savings"});
		type.setBounds(140, 60, 200, 20);
		add(type);
		
		JButton displayAcc = new JButton("Display All Accounts");
		displayAcc.setBounds(200, 120, 150, 30);
		add(displayAcc);
		displayAcc.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!isNumeric(balTxt.getText()))
				{
					
				}
				else
				{
					
				}
			}
		});
		
		JButton createAcc = new JButton("Create Account");
		createAcc.setSize(new Dimension(140, 30));
		createAcc.setLocation(new Point(30, 120));
		add(createAcc);
		createAcc.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!isNumeric(balTxt.getText()))
				{
					
				}
				else
				{
					//amt 
					DecimalFormat df = new DecimalFormat("#.##");
					//amt = Double.parseDouble(df.format(amt));
					//salary.setText("Annual Salary: $ " + amt);
				}
			}
		});
		
		setVisible(true);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		
		
	}
	
	
	
	public static void main(String args[])
	{
		Frame fr = new Frame();
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
