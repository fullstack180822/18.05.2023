package full.mypostgresql.demo.service;

import full.mypostgresql.demo.model.Customer;
import full.mypostgresql.demo.model.CustomerStatus;
import full.mypostgresql.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Value("${maxvip}")
    private Integer maxvip;

    @Override
    public String createCustomer(Customer customer) {
//        System.out.println(maxVIP);
        if (customer.getStatus() == CustomerStatus.VIP &&
                customerRepository.getCustomersByStatus(CustomerStatus.VIP).size() >= maxvip)
        {
            return  "{\"Warning\": \"Cannot create more VIP customers\" }";
        }
        return customerRepository.createCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer, Integer id)
    {
        customerRepository.updateCustomer(customer, id);
    }

    @Override
    public void deleteCustomer(Integer id)
    {
        customerRepository.deleteCustomer(id);
    }

    @Override
    public List<Customer> getAllCustomers()
    {
        return customerRepository.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(Integer id)
    {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public List<Integer> getAllIds()
    {
        return customerRepository.getAllIds();
    }


}