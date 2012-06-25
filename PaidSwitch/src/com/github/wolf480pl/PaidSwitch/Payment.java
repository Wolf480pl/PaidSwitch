package com.github.wolf480pl.PaidSwitch;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Payment {
	public Payment(String account, String amount){
		/*if(account.substring(0, 2).equalsIgnoreCase("b:")){
			bank = true;
			Account = account.substring(2);
		} else {
			bank = false;
			Account = account;
		}*/
		parseAccount(account);
		try{
			Amount = Double.parseDouble(amount);
		} catch (NumberFormatException ex){
			Amount = 0;
		}
	}
	public Payment(String account, double amount){
		/*if(account.substring(0, 2).equalsIgnoreCase("b:")){
			bank = true;
			Account = account.substring(2);
		} else {
			bank = false;
			Account = account;
		}*/
		parseAccount(account);
		Amount = amount;
	}
	public Payment(Payment payment){
		Account = payment.Account;
		Amount = payment.Amount;
		bank = payment.bank;
		none = payment.none;
	}
	public String Account;
	public double Amount;
	public boolean bank;
	public boolean none;
	public boolean isValid(){
		return ((none || Account != null) && (Amount != 0));
	}
	public boolean isValid(Economy eco){
		return (none || ((Account != null) && (bank ? eco.getBanks().contains(Account) : eco.hasAccount(Account)))) && (Amount != 0);
	}
	public boolean execute(Economy eco){
		EconomyResponse resp;
		if(none) return true;
		if(bank)
			resp = eco.bankDeposit(Account, Amount);
		else
			resp = eco.depositPlayer(Account, Amount);
		return resp.transactionSuccess();
	}
	public String toString(){
		return "Payment: " + String.valueOf(Amount) + " for " + (bank ? "bank" : "player") + ": " + Account;
	}
	void parseAccount(String account){
		if(account.equalsIgnoreCase("none")){
			none = true;
			return;
		}
		if(account.length() >= 2 && account.substring(0, 2).equalsIgnoreCase("b:")){
			bank = true;
			Account = account.substring(2);
		} else {
			bank = false;
			Account = account;
		}

	}
}
