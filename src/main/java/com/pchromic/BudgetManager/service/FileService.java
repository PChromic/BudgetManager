package com.pchromic.BudgetManager.service;

import com.pchromic.BudgetManager.domain.operation.Operation;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;

@Service
public class FileService {

    private final OperationService operationService;


    public FileService(OperationService operationService) {
        this.operationService = operationService;
    }

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
        System.out.println("Reading file ended");
    }

    public void readFile() {
        //Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);
        readRow(sheet);
    }


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
        operationService.add(operation);
    }


}