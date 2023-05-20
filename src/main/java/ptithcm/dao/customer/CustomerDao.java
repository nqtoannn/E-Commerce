package ptithcm.dao.customer;

import java.util.List;

import ptithcm.model.customer.Customer;

public interface CustomerDao {
	public Customer getCustomerById(int id);
	public Customer getCustomerByUsernamePassword(String userName, String password);
}
