package com.pchromic.BudgetManager.service.impl;

import com.pchromic.BudgetManager.domain.Operation;
import com.pchromic.BudgetManager.repository.OperationRepository;
import com.pchromic.BudgetManager.service.FileService;
import com.pchromic.BudgetManager.service.OperationService;
import com.pchromic.BudgetManager.utility.ExcelUtilities;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.tomcat.util.modeler.OperationInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private OperationService operationService;

    private HSSFWorkbook workbook;


    public void openFile(String path) {

        System.out.println("Start reading file");

//Get the workbook instance for XLS file
        try {
            FileInputStream excelFile = new FileInputStream(new File(path));
            workbook = new HSSFWorkbook(excelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readFile() {
        //Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);
        readRow(sheet);
    }

    @Override
    public void saveFile(String path) {

    }

    private void readRow(HSSFSheet sheet) {

        Iterator<Row> rowIterator = sheet.iterator();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if (row.getRowNum() == 0) {
                continue; //just skip the rows if row number is 0 or 1
            }
            saveRow(row);
        }

    }


    private void saveRow(Row row) {
        Operation operation = operationService.getOperationFromRow(row);
        operationService.addOperation(operation);
    }
}