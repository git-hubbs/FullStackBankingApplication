package com.revature.util; 

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.revature.dao.*;
import com.revature.models.Account;
import com.revature.models.User;
import com.revature.service.AccountService;
import com.revature.service.AccountServiceImpl;

public class Test {
	AccountService ac = new AccountServiceImpl();
	AccountDAO aDao = new AccountDAOImpl();
	UserDAO uDao = new UserDAOImpl();
	
	public void main(String[] args) {
		this.TestEmail();
	}
	
	
	@org.junit.Test
	public void TestEmail() {
		assertTrue(ac.verifyEmailInput("testh@d.ds"));
		assertTrue(ac.verifyEmailInput("test@h.ds"));
		assertFalse(ac.verifyEmailInput("test@h.s"));
		assertFalse(ac.verifyEmailInput("testh.ss"));
		assertFalse(ac.verifyEmailInput("@h.ss"));
	}
	
	@org.junit.Test
	public void TestPhoneNumber() {
		assertTrue(ac.verifyPhoneNumberInput("1111111111"));
		assertTrue(ac.verifyPhoneNumberInput("1234567890"));
		assertFalse(ac.verifyPhoneNumberInput("111"));
		assertFalse(ac.verifyPhoneNumberInput("@@@@@@@@@@"));
		assertFalse(ac.verifyPhoneNumberInput("%11"));
	}
	
	@org.junit.Test
	public void TestUsername() {
		assertTrue(ac.verifyUsernameInput("12345@78"));
		assertTrue(ac.verifyUsernameInput("12345abc"));
		assertFalse(ac.verifyUsernameInput(""));
		assertFalse(ac.verifyUsernameInput("1234"));
		assertFalse(ac.verifyUsernameInput("123ab"));
	}
	
	@org.junit.Test
	public void TestPassword() {
		assertTrue(ac.verifyUsernameInput("123456@8"));
		assertTrue(ac.verifyUsernameInput("12345abc"));
		assertFalse(ac.verifyUsernameInput(""));
		assertFalse(ac.verifyUsernameInput("1234"));
		assertFalse(ac.verifyUsernameInput("123ab"));
	}
	
	@org.junit.Test
	public void TestInsertAccount() {
		Account account1 = new Account(1, 8, 100, false);
		Account account2 = new Account(1, 8, 100, false);
		Account account3 = new Account(1, 8, 100, false);
		Account account4 = new Account(1, 8, 100, false);
		assertTrue(aDao.InsertAccount(account1));
		assertTrue(aDao.InsertAccount(account2));
		assertTrue(aDao.InsertAccount(account3));
		assertTrue(aDao.InsertAccount(account4));
	}
	
	@org.junit.Test
	public void TestAccountWithdrawal() {
		assertTrue(aDao.WithdrawFromAccount(20, 1));
		assertTrue(aDao.WithdrawFromAccount(21, 1));
	}
	
	@org.junit.Test
	public void TestAccountDeposit() {
		assertTrue(aDao.DepositIntoAccount(134, 1));
		assertTrue(aDao.DepositIntoAccount(142, 1));
	}
	
	@org.junit.Test
	public void TestGetAccountByID() {
		Account account1 = new Account(20, 8, 100, true);
		Account account2 = new Account(21, 8, 100, true);
		assertEquals(account1.getAccount_id(), aDao.GetAccountByAccountID(20).getAccount_id());
		assertEquals(account2.getAccount_id(), aDao.GetAccountByAccountID(21).getAccount_id());
	}
	
	@org.junit.Test
	public void TestInsertUser() {
		
		User user1 = new User("userdnamafgeds", "passwadffgorsdfd");
		User user2 = new User("usernasmadfgeasd", "passwoasdfgrdfs");
		User user3 = new User("usernamfdafgasfe1", "pafdfgfassword");
		assertTrue(uDao.InsertUser(user1));
		assertTrue(uDao.InsertUser(user2));
		assertTrue(uDao.InsertUser(user3));
	}
}
