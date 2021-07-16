package com.revature.dao;

import java.util.List;

import com.revature.models.Customer;
import com.revature.models.User;

public interface CustomerDAO {
	public Customer GetCustomerByUser(User user);
	public List<Customer> GetAllCustomers();
	public boolean InsertCustomer(Customer customer);
}
