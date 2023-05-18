package full.mypostgresql.demo;

import full.mypostgresql.demo.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(JdbcTemplate jdbcTemplate) {
		return args -> {
			jdbcTemplate.execute(
					"DROP TABLE IF EXISTS customer_order cascade;\n" +
							"DROP TABLE IF EXISTS customer cascade;\n" +
							"CREATE TABLE customer ("+
							"    id SERIAL PRIMARY KEY,\n" +
							"    first_name varchar(255) NOT NULL default '',\n" +
							"    last_name varchar(255) NOT NULL default '',\n" +
							"    email varchar(255) NOT NULL default '',\n" +
							"    status varchar(255) NOT NULL default 'REGULAR');\n" +
							"CREATE TABLE customer_order (" +
							"    id SERIAL PRIMARY KEY,\n" +
							"    customer_id int NOT NULL,\n" +
							"    item_name varchar(255) NOT NULL default '',\n" +
							"    price DECIMAL(100,2) NOT NULL default 0,\n" +
							"    FOREIGN KEY (customer_id) REFERENCES customer(id));");

			//customerRepository.createCustomer(new Customer(0, "tomer", "avivi",
			//		"tomeravivi@gmail.com", CustomerStatus.REGULAR));
		};
	}

}
