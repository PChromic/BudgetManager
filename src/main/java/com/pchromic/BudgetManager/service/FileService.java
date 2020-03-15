package com.pchromic.BudgetManager.service;

import com.pchromic.BudgetManager.domain.operation.Operation;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

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
            readFile();
            excelFile.close();
            System.out.println("Reading file ended");
         } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void readFile() {
        HSSFSheet sheet = workbook.getSheetAt(0);
        Set<Operation> operations = new LinkedHashSet<>();
        HSSFRow row;
        Iterator rows = sheet.rowIterator();

       // Iterator<Row> rowIterator = sheet.iterator();
        while (rows.hasNext()) {
                row = (HSSFRow)rows.next();
            if (row.getRowNum() == 0) {
                continue; //just skip the rows if row number is 0 or 1
            }
            operations.add(operationService.getOperationFromRow(row));
        }
        operationService.addOperations(operations);
    }


    public void saveFile(String path) {

    }

}