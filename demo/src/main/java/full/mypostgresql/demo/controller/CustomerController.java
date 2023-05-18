package full.mypostgresql.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import full.mypostgresql.demo.model.Customer;
import full.mypostgresql.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    ObjectMapper objectMapper;

//    @Autowired
//    private UserServiceClient apiClient;

    @GetMapping
    public List<Customer> get()
    {
        return customerService.getAllCustomers();
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity getById(@PathVariable Integer id)
    {
        // run with id which does not exist -- 404
        // run with id which exists -- 200
        // change the query to a bad grammer SELECTTTTT -- 500
        try {
            Customer result = customerService.getCustomerById(id);
            if (result != null) {
                return new ResponseEntity<Customer>(result, HttpStatus.OK);
            }
            return new ResponseEntity<String>("{ \"Warning\": \"not found customer with Id " + id + "\" }",
                    HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            return new ResponseEntity<String>("{ \"Error\": \"" + e.toString() + "\" }",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<String> post(@RequestBody Customer customer) throws JsonProcessingException {
        String json = objectMapper.writeValueAsString(customer);
        System.out.println(json);
        Customer c = objectMapper.readValue(json, Customer.class);
        System.out.println(c);
        String result = customerService.createCustomer(customer);
        if (result == null) {
            return new ResponseEntity<String>("{ \"result\": \"created\" }", HttpStatus.CREATED);
        }
        return new ResponseEntity<String>(result, HttpStatus.BAD_REQUEST);
    }

    @PutMapping(value = "/{id}")
    public Customer put(@PathVariable Integer id, @RequestBody Customer customer) {
        customerService.updateCustomer(customer, id);
        return customer;
    }

    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id)
    {
        customerService.deleteCustomer(id);
    }

    @GetMapping(value = "/ids")
    public List<Integer> getIds()
    {
        return customerService.getAllIds();
    }

    //@GetMapping(value = "/shows/{id}")
    //public TVMazeShowResponse getIds(@PathVariable Integer id)
//    {
//        return apiClient.getShow(id);
//    }


}
