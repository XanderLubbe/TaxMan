package com.xanderlubbe.taxman;

import com.xanderlubbe.taxman.model.TaxDTO;
import com.xanderlubbe.taxman.repository.TaxRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import static com.xanderlubbe.taxman.Constants.CSV_DATA_FILE;
import static com.xanderlubbe.taxman.Constants.SQL_DATA_FILE;

@Configuration
class DatabaseConfiguration {
    @Bean
    CommandLineRunner initDatabase(TaxRepository repository) {

        return args -> {

            ClassPathResource otherPathResource = new ClassPathResource(SQL_DATA_FILE);

            if (!otherPathResource.exists()) {
                System.out.println(SQL_DATA_FILE + " does not exist");

                ClassPathResource classPathResource = new ClassPathResource(CSV_DATA_FILE);
                InputStreamReader inputStreamReader = new InputStreamReader(classPathResource.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();

                while (line != null) {

                    // Java map (stream)
                    String[] cols = line.split(", ");
                    int size = cols.length;
                    int[] intArr = new int[size];

                    for (int i = 0; i < size; i++) {
                        // check that parse does not fail
                        intArr[i] = Integer.parseInt(cols[i]);
                        // catch exception here
                    }
                    // check that index does not go out of bounds :)
                    repository.save(new TaxDTO(intArr[0], intArr[1], intArr[2], intArr[3], intArr[4]));

                    line = bufferedReader.readLine();
                }

                bufferedReader.close();

                // TODO generate backup sql file here
            }

        };
    }
}
