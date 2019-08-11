package com.pchromic.BudgetManager;


import com.pchromic.BudgetManager.domain.Operation;
import com.pchromic.BudgetManager.repository.OperationRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperationRepositoryTest {

    @Autowired
    private OperationRepository repository;


    @Test
    public void saveOrders(){
        Operation operation = new Operation();
        Operation operation1 = repository.save(operation);
        Assert.assertEquals(repository.count(),1l);
    }
}
