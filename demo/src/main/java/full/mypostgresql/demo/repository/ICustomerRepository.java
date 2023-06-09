package full.mypostgresql.demo.repository;

import full.mypostgresql.demo.model.Customer;
import full.mypostgresql.demo.model.CustomerStatus;

import java.util.List;

public interface ICustomerRepository {

    String createCustomer(Customer customer);

    void updateCustomer(Customer customer, Integer id);

    void deleteCustomer(Integer id);

    List<Customer> getAllCustomers();

    Customer getCustomerById(Integer id);

    List<Integer> getAllIds();

    List<Customer> getCustomersByStatus(CustomerStatus status);
}
