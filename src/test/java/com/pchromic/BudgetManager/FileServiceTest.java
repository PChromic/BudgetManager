package com.pchromic.BudgetManager;


import com.pchromic.BudgetManager.repository.OperationRepository;
import com.pchromic.BudgetManager.service.FileService;
import com.pchromic.BudgetManager.service.OperationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FileServiceTest {

    private final String FILE_PATH = "E:\\Projects\\BudgetManager\\Historia_Rachunku_duplicate.xls";

    @Autowired
    private OperationService operationService;
    @Autowired
    private FileService fileService;
    @Autowired
    private OperationRepository repository;


/*    @Test(expected = FileNotFoundException.class)
    public void openFile() {
        fileService.openFile(FILE_PATH);
    }*/
/*

    @Before
    public void readFile() {
        // given
        fileService.openFile(FILE_PATH);

        // when
        fileService.readFile();

        // then
        assertEquals(repository.findAll().isEmpty(), false);
    }
*/

    @Test
    public void verifyIfDuplicates() {

        // given
        fileService.openFile(FILE_PATH);

        // when
        fileService.readFile();

        // then
        assertEquals(repository.findAll().isEmpty(), false);
    }
}
