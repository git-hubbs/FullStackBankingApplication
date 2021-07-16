package com.revature.service;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.Customer;

import io.javalin.http.Context;

public interface AccountService {
	
	public void depositIntoAccount(Context ctx);
	public List<Account> getAccounts(Context ctx);
	public void withdrawFromAccount(Context ctx);
	public void transferBetweenAccounts(Context ctx);
	public void registerAccount(Context ctx);
	public boolean registerCustomer(Context ctx);
	public List<Account> getAllAccounts();
	public List<Account> getAccountsByCustomer(int customerID);
	public boolean approveAccount(Context ctx);
	public boolean denyAccount(Context ctx);
	public boolean verifyUsernameInput(String username);
	public boolean verifyPasswordInput(String password);
	public boolean verifyEmailInput(String email);
	public boolean verifyPhoneNumberInput(String number);
	
}
