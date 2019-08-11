/*
package com.pchromic.BudgetManager;

import com.pchromic.BudgetManager.repository.OperationRepository;
import com.pchromic.BudgetManager.repository.impl.OperationRepositoryImpl;
import com.pchromic.BudgetManager.service.FileService;
import com.pchromic.BudgetManager.service.OperationService;
import com.pchromic.BudgetManager.service.impl.FileServiceImpl;
import com.pchromic.BudgetManager.service.impl.OperationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    public AppConfig() {
    }

    @Bean
    public OperationService operationService() {
        return new OperationServiceImpl(operationRepository());
    }

    @Bean
    public OperationRepository operationRepository() {
        return new OperationRepositoryImpl();
    }

    @Bean
    public FileService fileService() {
        return new FileServiceImpl();
    }

}
*/
