package com.lambda.employee;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j //Lombok auto creates Slf4j-based logs
@Configuration //Indicate that a class declares one or more @Beans
                // Bean - object, method, controlled by spring,
public class seedDB {
    @Bean // CLR spring boot runs all beans at start up;
    public CommandLineRunner initDB(EmployeeRepository employeerepository){
        return args ->  {
            log.info("Seeding " + employeerepository.save(new Employee("steve", "Green", true, 1)) );
            log.info("Seeding " + employeerepository.save(new Employee("steve2", "Green2", false, 1)) );
            log.info("Seeding " + employeerepository.save(new Employee("steve3", "Green3", false, 1)) );
        };
    }
}
