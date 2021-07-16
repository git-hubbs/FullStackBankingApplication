package com.revature.controller;

import io.javalin.http.Context;

public interface AccountController {
	
	public void deposit(Context ctx);
	public void withdraw(Context ctx);
	public void getCustomer(Context ctx);
	public void transfer(Context ctx);
	public void registerNewAccount(Context ctx);
	public void registerNewCustomer(Context ctx);
	public void loadRegistrationPage(Context ctx);
	public void getAllAccounts(Context ctx);
	public void approveAccount(Context ctx);
	public void denyAccount(Context ctx);

}
