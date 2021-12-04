package za.co.taxman.taxmanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import za.co.taxman.taxmanapi.tax.TaxController;

@SpringBootApplication
public class TaxmanApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaxmanApiApplication.class, args);
	}

}
