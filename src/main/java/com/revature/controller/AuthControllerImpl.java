package com.revature.controller;

import com.revature.service.AuthService;
import com.revature.service.AuthServiceImpl;
import com.revature.service.AccountService;
import com.revature.service.AccountServiceImpl;

import io.javalin.http.Context;

public class AuthControllerImpl implements AuthController{
	private AuthService authService= new AuthServiceImpl();
	private AccountService accountService = new AccountServiceImpl();

	@Override
	public void login(Context ctx) {
	
		String username = ctx.formParam("username");
		String password = ctx.formParam("password");
		
		if(username.equals("admin") && password.equals("password")) {
			ctx.status(200);
			ctx.redirect("employee.html");
			
		}else if(authService.authenticateUser(username, password)) {
			
			ctx.status(200);
			
			ctx.cookieStore("username",username);
			ctx.cookieStore("password",password);

			ctx.redirect("customer.html");
			//if user doesn't exists you'd set it to 407 
			
			
		}else {
			
			ctx.status(407);
			ctx.redirect("login.html");
		}

		System.out.println(username);
		System.out.println(password);

	}

	
	@Override
	public void logout(Context ctx) {
		ctx.clearCookieStore();
		ctx.redirect("login.html");
	}

	@Override
	public boolean checkUser(Context ctx) {
		
		return authService.validateToken(ctx.cookieStore("user"));
	}
}
