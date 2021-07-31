package com.revature.models;

public class Transfer {
	private double amount;
	private int sourceAccountID;
	private int targetAccountID;
	
	public Transfer() {
		super();
	}
	
	public Transfer(double amount, int sourceAccountID, int targetAccountID) {
		this.amount = amount;
		this.sourceAccountID = sourceAccountID;
		this.targetAccountID = targetAccountID;
	}
	
	public int getTargetAccountID() {
		return targetAccountID;
	}
	public void setTargetAccountID(int targetAccountID) {
		this.targetAccountID = targetAccountID;
	}
	public int getSourceAccountID() {
		return sourceAccountID;
	}
	public void setSourceAccountID(int sourceAccountID) {
		this.sourceAccountID = sourceAccountID;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
