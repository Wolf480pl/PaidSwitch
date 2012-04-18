package com.github.wolf480pl.PaidSwitch;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;

public class Payment {
	public Payment(String account, String amount){
		if(account.substring(0, 1).equalsIgnoreCase("b:")){
			bank = true;
			Account = account.substring(2);
		} else {
			bank = false;
			Account = account;
		}
		try{
			Amount = Double.parseDouble(amount);
		} catch (NumberFormatException ex){
			Amount = 0;
		}
	}
	public Payment(String account, double amount){
		if(account.substring(0, 1).equalsIgnoreCase("b:")){
			bank = true;
			Account = account.substring(2);
		} else {
			bank = false;
			Account = account;
		}
		Amount = amount;
	}
	public String Account;
	public double Amount;
	public boolean bank;
	public boolean isValid(){
		return ((Account != null) && (Amount != 0));
	}
	public boolean isValid(Economy eco){
		return ((Account != null) && (bank ? eco.getBanks().contains(Account) : eco.hasAccount(Account)) && (Amount != 0));
	}
	public boolean execute(Economy eco){
		EconomyResponse resp;
		if(bank)
			resp = eco.bankDeposit(Account, Amount);
		else
			resp = eco.depositPlayer(Account, Amount);
		return resp.transactionSuccess();
	}
}
