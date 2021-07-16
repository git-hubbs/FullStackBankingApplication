package com.revature;

import java.awt.Button;

import com.revature.controller.AuthController;
import com.revature.controller.AuthControllerImpl;
import com.revature.controller.AccountController;
import com.revature.controller.AccountControllerImpl;

import io.javalin.Javalin;

public class MainDriver {
	private static final String LOGIN_PATH = "/login";
	private static AuthController authController = new AuthControllerImpl();
	
	private static final String CUSTOMER_PATH = "/customer";
	private static AccountController accountController = new AccountControllerImpl();
	
	public static void main(String[] args) {
		
		
		
		Javalin app = Javalin.create(
				config -> {
					config.addStaticFiles("/public");
				}
			).start(9000);
		

		app.post("/login", ctx -> authController.login(ctx));
		app.get("/logout", ctx -> authController.logout(ctx));
		app.get("/customer", ctx -> accountController.getCustomer(ctx));
		app.post("/deposit", ctx -> accountController.deposit(ctx));
		app.post("/withdraw", ctx -> accountController.withdraw(ctx));
		app.post("/transfer", ctx -> accountController.transfer(ctx));
		app.post("/account-registration", ctx -> accountController.registerNewAccount(ctx));
		app.post("/customer-registration", ctx -> accountController.registerNewCustomer(ctx));
		app.post("/customer-registration-button", ctx -> accountController.loadRegistrationPage(ctx));
		app.get("/customers", ctx -> accountController.getAllAccounts(ctx));
		app.post("/approve", ctx -> accountController.approveAccount(ctx));
		app.post("/deny", ctx -> accountController.denyAccount(ctx));
	}
}
