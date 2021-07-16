package com.revature.dao;

import com.revature.models.User;

public interface UserDAO {
	public User GetUserByUsernameAndPassword(String username, String password);
	public boolean InsertUser(User user);
}
