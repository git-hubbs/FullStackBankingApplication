package com.revature.models;

public class Deposit {
	
	private double amount;
	private int account_id;
	
	public Deposit() {
		super();
	}
	
	public Deposit(double amount, int account_id) {
		this.amount = amount;
		this.account_id = account_id;
	}
	
	
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getAccount_id() {
		return account_id;
	}
	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}

}
