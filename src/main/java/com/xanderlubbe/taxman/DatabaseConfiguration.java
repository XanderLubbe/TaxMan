package com.xanderlubbe.taxman;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@Configuration
class DatabaseConfiguration {
    private static final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);

    @Bean
    CommandLineRunner initDatabase(EntryRepository repository) {

        return args -> {


            File f = new File("/Users/xander/Desktop/Projects/TaxMan/src/main/resources/data.sql");
            if(f.exists()) {
                System.out.println("fileName exists");
            } else {
                System.out.println("fileName does not exist");
                ClassPathResource classPathResource = new ClassPathResource("data.csv");
                InputStreamReader inputStreamReader = new InputStreamReader(classPathResource.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = bufferedReader.readLine();

                while (line != null){
                    System.out.println(line);
                    String[] cols = line.split(", ");
                    int size = cols.length;
                    int[] intArr = new int[size];
                    for(int i=0; i<size; i++) {
                        intArr[i] = Integer.parseInt(cols[i]);
                    }

                    repository.save(new Entry(intArr[0], intArr[1], intArr[2], intArr[3], intArr[4]));
                    line = bufferedReader.readLine();
                }

                bufferedReader.close();

                // TODO generate backup sql file here
            }





//            log.info("Preloading " + repository.save(new Entry(00,11,22,33,44)));
//            log.info("Preloading " + repository.save(new Entry(10,9,8,7,6)));
        };
    }
}
