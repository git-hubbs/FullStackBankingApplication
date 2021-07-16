package com.revature.controller;

import org.apache.log4j.Logger;


import com.revature.service.AccountService;
import com.revature.service.AccountServiceImpl;

import io.javalin.http.Context;

public class AccountControllerImpl implements AccountController{
	private AccountService accountService = new AccountServiceImpl();
	Logger log = Logger.getLogger(AccountControllerImpl.class.getName());
	
	public AccountControllerImpl() {
		
	}

	@Override
	public void getCustomer(Context ctx) {
		ctx.status(200);
		ctx.json(accountService.getAccounts(ctx));
	}
	
	@Override
	public void deposit(Context ctx) {
		
		String accountID = ctx.formParam("account_id");
		String depositAmount = ctx.formParam("amount");
		
		System.out.println(accountID);
		
		ctx.status(200);
		
		ctx.cookieStore("account_id",accountID);
		ctx.cookieStore("amount",depositAmount);
		
		accountService.depositIntoAccount(ctx);
		
		ctx.redirect("customer.html");
		
		log.info("Deposit initiated into account: " + accountID);
	}

	@Override
	public void withdraw(Context ctx) {
		String accountID = ctx.formParam("account_id");
		String withdrawAmount = ctx.formParam("amount");
		
		System.out.println(accountID);
		
		ctx.status(200);
		
		ctx.cookieStore("account_id",accountID);
		ctx.cookieStore("amount",withdrawAmount);
		
		accountService.withdrawFromAccount(ctx);
		
		ctx.redirect("customer.html");
		
		log.info("Withdraw initiated from account: " + accountID);
	}

	@Override
	public void transfer(Context ctx) {
		String sourceID = ctx.formParam("source_id");
		String targetID = ctx.formParam("target_id");
		String transferAmount = ctx.formParam("amount");
		
		ctx.status(200);
		
		ctx.cookieStore("source_id",sourceID);
		ctx.cookieStore("target_id",targetID);
		ctx.cookieStore("amount",transferAmount);
		
		accountService.transferBetweenAccounts(ctx);
		
		ctx.redirect("customer.html");
		
		log.info("Transfer initiated between source: " + sourceID + " and target: " + targetID);
	}

	@Override
	public void registerNewAccount(Context ctx) {
		String depositAmount = ctx.formParam("amount");
		
		ctx.status(200);
		
		ctx.cookieStore("amount",depositAmount);
		
		if(Double.parseDouble(depositAmount) >= 0)
			accountService.registerAccount(ctx);
		
		ctx.redirect("customer.html");
		
		log.info("New account registration initiated with deposit of: " + depositAmount);
	}

	@Override
	public void registerNewCustomer(Context ctx) {
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		String first_name = ctx.formParam("first_name");
		String last_name = ctx.formParam("last_name");
		String email = ctx.formParam("email");
		String phone_number = ctx.formParam("phone_number");
		
		System.out.println("username");
		
		ctx.status(200);
		
		ctx.cookieStore("username",username);
		ctx.cookieStore("password",password);
		ctx.cookieStore("first_name",first_name);
		ctx.cookieStore("last_name",last_name);
		ctx.cookieStore("email",email);
		ctx.cookieStore("phone_number",phone_number);
		
		ctx.redirect("login.html");
		accountService.registerCustomer(ctx);
		
		log.info("New customer registration initiated with username: " + username + " and password: " + password);
	}

	@Override
	public void loadRegistrationPage(Context ctx) {
		ctx.redirect("registration.html");
		log.info("Loading registration page...");
	}

	@Override
	public void getAllAccounts(Context ctx) {
		ctx.status(200);
		ctx.json(accountService.getAllAccounts());
		log.info("Fetching user accounts...");
	}


	@Override
	public void approveAccount(Context ctx) {
		// TODO Auto-generated method stub
		String accountID = ctx.formParam("account_id");	
		ctx.status(200);
		ctx.cookieStore("account_id",accountID);
		accountService.approveAccount(ctx);
		ctx.redirect("employee.html");
		log.info("Account approved by employee: " + accountID);
	}

	@Override
	public void denyAccount(Context ctx) {
		String accountID = ctx.formParam("account_id");	
		ctx.status(200);
		ctx.cookieStore("account_id",accountID);
		accountService.denyAccount(ctx);
		ctx.redirect("employee.html");
		log.info("Account approved by employee: " + accountID);
	}
}
