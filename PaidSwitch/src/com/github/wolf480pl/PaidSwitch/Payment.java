package com.github.wolf480pl.PaidSwitch;

public class Payment {
	public Payment(String account, String amount){
		Account = account;
		try{
			Amount = Double.parseDouble(amount);
		} catch (NumberFormatException ex){
			Amount = 0;
		}
	}
	public Payment(String account, double amount){
		Account = account;
		Amount = amount;
	}
	public String Account;
	public double Amount;
	public boolean isValid(){
		return ((Account != null) && (Amount != 0));
	}
}
