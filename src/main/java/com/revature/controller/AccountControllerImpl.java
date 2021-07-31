package com.revature.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.revature.models.Deposit;
import com.revature.models.Transfer;
import com.revature.models.User;
import com.revature.models.Withdraw;
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
		ObjectMapper om = new ObjectMapper();
		try {
			Deposit deposit = om.readValue(ctx.body(), Deposit.class);
			if(deposit.getAccount_id() != 0 && deposit.getAmount() != 0.0) {
				ctx.cookieStore("deposit", deposit);
				if(accountService.depositIntoAccount(ctx)) {
					System.out.println(deposit.getAmount());
						ctx.status(200);
						return;
				}
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		ctx.status(300);
	}

	@Override
	public void withdraw(Context ctx) {
		System.out.println("withdraw started");
		ObjectMapper om = new ObjectMapper();
		try {
			Withdraw withdraw = om.readValue(ctx.body(), Withdraw.class);
			if(withdraw.getAccount_id() != 0 && withdraw.getAmount() != 0.0) {
				ctx.cookieStore("withdraw", withdraw);
				if(accountService.withdrawFromAccount(ctx)) {
					System.out.println(withdraw.getAmount());
						ctx.status(200);
						return;
				}
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		ctx.status(300);
	}

	@Override
	public void transfer(Context ctx) {
		System.out.println("withdraw started");
		ObjectMapper om = new ObjectMapper();
		try {
			Transfer transfer = om.readValue(ctx.body(), Transfer.class);
			if(transfer.getSourceAccountID() != 0 && transfer.getTargetAccountID() != 0 && transfer.getAmount() != 0.0) {
				ctx.cookieStore("transfer", transfer);
				if(accountService.transferBetweenAccounts(ctx)) {
					System.out.println(transfer.getAmount());
						ctx.status(200);
						return;
				}
			}
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		ctx.status(300);
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
