
package com.revature.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.CustomerDAO;
import com.revature.dao.CustomerDAOImpl;
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAOImpl;
import com.revature.models.Account;
import com.revature.models.Customer;
import com.revature.models.Deposit;
import com.revature.models.Transfer;
import com.revature.models.User;
import com.revature.models.Withdraw;

import io.javalin.http.Context;

public class AccountServiceImpl implements AccountService {

	Pattern emailPattern = Pattern.compile("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}");
	CustomerDAO cDao = new CustomerDAOImpl();
	UserDAO uDao = new UserDAOImpl();
	AccountDAO aDao = new AccountDAOImpl();
	
	public List<Account> getAccounts(Context ctx) {
		User user = uDao.GetUserByUsernameAndPassword(ctx.cookieStore("username"), ctx.cookieStore("password"));
		Customer customer = cDao.GetCustomerByUser(user);
		List<Account> accounts = aDao.GetAccountsByCustomerID(customer.getCustomerId());
		return accounts;
	}
	
	public boolean depositIntoAccount(Context ctx) {
		Deposit deposit = ctx.cookieStore("deposit");
		User user = uDao.GetUserByUsernameAndPassword(ctx.cookieStore("username"), ctx.cookieStore("password"));
		Customer customer = cDao.GetCustomerByUser(user);
		try {
			if(deposit.getAmount() > 0 && aDao.GetAccountByAccountID(deposit.getAccount_id()).getCustomer_id() == customer.getCustomerId() && aDao.GetAccountByAccountID(deposit.getAccount_id()).isApproved()) {
				if(aDao.DepositIntoAccount(deposit.getAccount_id(), deposit.getAmount())) {
					return true;
				}else {	
					return false;
				}
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean withdrawFromAccount(Context ctx) {
		Withdraw withdraw = ctx.cookieStore("withdraw");
		User user = uDao.GetUserByUsernameAndPassword(ctx.cookieStore("username"), ctx.cookieStore("password"));
		Customer customer = cDao.GetCustomerByUser(user);
		try {
			if(withdraw.getAmount() > 0 && aDao.GetAccountByAccountID(withdraw.getAccount_id()).getCustomer_id() == customer.getCustomerId() && aDao.GetAccountByAccountID(withdraw.getAccount_id()).isApproved()
					&& aDao.GetAccountByAccountID(withdraw.getAccount_id()).getBalance() >= withdraw.getAmount()) {
				if(aDao.WithdrawFromAccount(withdraw.getAccount_id(), withdraw.getAmount())) {
					return true;
				}else {	
					return false;
				}
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean transferBetweenAccounts(Context ctx) {
		Transfer transfer = ctx.cookieStore("transfer");
		User user = uDao.GetUserByUsernameAndPassword(ctx.cookieStore("username"), ctx.cookieStore("password"));
		Customer customer = cDao.GetCustomerByUser(user);
		try {
			if(transfer.getAmount() > 0 && aDao.GetAccountByAccountID(transfer.getSourceAccountID()).getCustomer_id() == customer.getCustomerId() && aDao.GetAccountByAccountID(transfer.getTargetAccountID()).isApproved()
					&& aDao.GetAccountByAccountID(transfer.getSourceAccountID()).isApproved() && aDao.GetAccountByAccountID(transfer.getSourceAccountID()).getBalance() >= transfer.getAmount()) {
				if(aDao.TransferMoneyBetweenAccounts(transfer.getAmount(), transfer.getTargetAccountID(), transfer.getSourceAccountID())) {
					return true;
				}else {	
					return false;
				}
			}
		}catch(NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void registerAccount(Context ctx) {
		if(IsDoubleParsable(ctx.cookieStore("amount"))) {
			User user = uDao.GetUserByUsernameAndPassword(ctx.cookieStore("username"), ctx.cookieStore("password"));
			Customer customer = cDao.GetCustomerByUser(user);
			aDao.InsertAccount(new Account(0, customer.getCustomerId(), Double.parseDouble(ctx.cookieStore("amount")), false));
		}
	}

	@Override
	public boolean registerCustomer(Context ctx) {
		if(verifyUsernameInput(ctx.cookieStore("username")) && verifyPasswordInput(ctx.cookieStore("password")) 
				&& verifyEmailInput(ctx.cookieStore("email")) && verifyPhoneNumberInput(ctx.cookieStore("phone_number"))) {
			
			if(uDao.InsertUser(new User(ctx.cookieStore("username"), ctx.cookieStore("password")))
					&& cDao.InsertCustomer(new Customer(uDao.GetUserByUsernameAndPassword(ctx.cookieStore("username"),
							ctx.cookieStore("password")).getUser_id(), ctx.cookieStore("first_name"), ctx.cookieStore("last_name"), 
							ctx.cookieStore("phone_number"), ctx.cookieStore("email")))){
						return true;
			}
		}
		return false;
	}

	@Override
	public List<Account> getAllAccounts() {
		return aDao.getAllAccounts();
	}
	
	@Override
	public List<Account> getAccountsByCustomer(int customerID){
		return aDao.GetAccountsByCustomerID(customerID);
	}
	

	@Override
	public boolean verifyUsernameInput(String username) {
		if(username.length() < 2 || username == null)
			return false;
		return true;
	}

	@Override
	public boolean verifyPasswordInput(String password) {
		if(password.length() < 6 || password == null)
			return false;
		return true;
	}

	@Override
	public boolean verifyEmailInput(String email) {
		Matcher emailMatcher = emailPattern.matcher(email);
		if(email.length() < 6 || email == null) {
			return false;
		}else if(!emailMatcher.matches())
        	return false;
        return true;
	}

	@Override
	public boolean verifyPhoneNumberInput(String number) {
		System.out.println(number);
		if(number.length() == 10) {
			try {
			    Long.parseLong(number);
			    return true;
			} catch (NumberFormatException e) {
			    System.out.println("Input String cannot be parsed to Integer.");
			}
		}
		return false;
	}

	@Override
	public boolean approveAccount(Context ctx) {

		if(aDao.ApproveAccount(Integer.parseInt(ctx.cookieStore("account_id"))))
			return true;
		return false;
	}
	


	public boolean IsDoubleParsable(String stringDouble) {
		if(!(stringDouble.length() == 0 || stringDouble == null)) {
			try
			{
			  Double.parseDouble(stringDouble);
			}
			catch(NumberFormatException e)
			{
			  return false;
			}
		}else {
			return false;
		}
		return true;
	}
	
	//returns whether a string can be parsed into an integer
	public boolean IsIntParsable(String stringInt) {
		if(!(stringInt.length() == 0 || stringInt == null)) {
			try
			{
			  Integer.parseInt(stringInt);
			}
			catch(NumberFormatException e)
			{
			  return false;
			}
		}else {
			return false;
		}
		return true;
	}

	@Override
	public boolean denyAccount(Context ctx) {

		if(aDao.DenyAccount(Integer.parseInt(ctx.cookieStore("account_id"))))
			return true;
		return false;
	}

}
