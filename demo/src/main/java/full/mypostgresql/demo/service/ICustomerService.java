package full.mypostgresql.demo.service;

import full.mypostgresql.demo.model.Customer;

import java.util.List;

public interface ICustomerService {

    String createCustomer(Customer customer);

    void updateCustomer(Customer customer, Integer id);

    void deleteCustomer(Integer id);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer id);

    List<Integer> getAllIds();
}
