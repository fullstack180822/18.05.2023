package full.mypostgresql.demo.model;

import lombok.Getter;
import lombok.Setter;

public class Customer {

    @Getter
    @Setter
    protected Integer id;

    @Getter@Setter
    protected String firstName;

    @Getter@Setter
    protected String lastName;

    @Getter@Setter
    protected String email;

    @Getter@Setter
    protected CustomerStatus status;

    public Customer(Integer id, String firstName, String lastName, String email, CustomerStatus status) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", status=" + status.name() +
                '}';
    }
}

