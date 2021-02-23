package com.classicmodels;

import com.classicmodels.service.ClassicModelsService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

    private final ClassicModelsService classicModelsService;

    public MainApplication(ClassicModelsService classicModelsService) {
        this.classicModelsService = classicModelsService;
    }

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

    // This code result in:
    // Cannot insert duplicate key in object 'dbo.sale'. The duplicate key value is (1).
    
    @Bean
    public ApplicationRunner init() {
        return args -> {

           // classicModelsService.batchQueriesInOneTransaction();
            classicModelsService.batchQueriesInTransactionPerBatch();
        };
    }
}
