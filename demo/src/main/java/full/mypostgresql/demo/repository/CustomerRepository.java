package full.mypostgresql.demo.repository;

import full.mypostgresql.demo.model.Customer;
import full.mypostgresql.demo.model.CustomerMapper;
import full.mypostgresql.demo.model.CustomerStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepository implements ICustomerRepository{


    private static final String CUSTOMER_TABLE_NAME = "customer";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String createCustomer(Customer customer) {
        try {
            String query = String.format("INSERT INTO %s (first_name, last_name, email, status) VALUES (?, ?, ?, ?)", CUSTOMER_TABLE_NAME);
            jdbcTemplate.update(query, customer.getFirstName(), customer.getLastName(),
                    customer.getEmail(), customer.getStatus().name());
            return null;
        }
        catch (Exception e) {
            return "{\"Error\": \"" + e.toString() + "\" }";
        }
    }

    @Override
    public void updateCustomer(Customer customer, Integer id) {
        String query = String.format("UPDATE %s SET first_name=?, last_name=?, email=? status = ? WHERE id= ?", CUSTOMER_TABLE_NAME);
        jdbcTemplate.update(query, customer.getFirstName(), customer.getLastName(), customer.getEmail(),
                customer.getStatus().name(), id);
    }

    @Override
    public void deleteCustomer(Integer id) {
        String query = String.format("DELETE FROM %s WHERE id= ?", CUSTOMER_TABLE_NAME);
        jdbcTemplate.update(query, id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        String query = String.format("Select * from %s", CUSTOMER_TABLE_NAME); // 1 3 5 7
        return jdbcTemplate.query(query, new CustomerMapper());

        // inline mapper
        /*
        return jdbcTemplate.query(
                query,
                (rs, rowNum) ->
                        new Customer(
                                rs.getInt("id"),
                                rs.getString("first_name"),
                                rs.getString("last_name"),
                                rs.getString("email")));
         */

    }

    @Override
    public Customer getCustomerById(Integer id) {
        String query = String.format("Select * from %s where id=?", CUSTOMER_TABLE_NAME);
        try
        {
            return jdbcTemplate.queryForObject(query, new CustomerMapper(), id);
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Integer> getAllIds() {
//        String query = String.format("Select * from %s", CUSTOMER_TABLE_NAME); // 1 3 5 7
//        return jdbcTemplate.query(query, new CustomerMapper());

        String query = String.format("SELECT id FROM %s", CUSTOMER_TABLE_NAME);
        return jdbcTemplate.queryForList(query, Integer.class);
    }

    @Override
    public List<Customer> getCustomersByStatus(CustomerStatus status) {
        String query = String.format("Select * from %s where status like ?", CUSTOMER_TABLE_NAME);
        try
        {
            return jdbcTemplate.query(query, new CustomerMapper(), status.name());
        }
        catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
