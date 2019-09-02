package com.pchromic.BudgetManager.utility;

import com.pchromic.BudgetManager.service.OperationService;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;


public class ExcelUtilities implements FileUtilities {

    @Autowired
    private OperationService operationService;

    private HSSFWorkbook workbook = null;

    public ExcelUtilities() {
    }

    @Override
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
/*//Get iterator to all the rows in current sheet

        Iterator<Row> rowIterator = sheet.iterator();

        Iterator<Cell> cellIterator = null;
        while (rowIterator.hasNext()) {

            Row row = rowIterator.next();
            cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                Object cellValue = getCellValue(cell);
                System.out.print(cellValue + "\t");


            }
            System.out.println();
        }*/
        System.out.println("Reading file is done!");

    }


    private void readRow (HSSFSheet sheet) {

        Iterator<Row> rowIterator = sheet.iterator();
        String columnName = rowIterator.next().getCell(0).getStringCellValue();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //readCell(row);
            operationService.getOperationFromRow(row);
        }

    }

/*    private Object readCell(Row row) {
        Iterator<Cell> cellIterator = row.cellIterator();
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            Object cellValue = getCellValue(cell);
            System.out.print(cellValue + "\t");
        }
      *//*  while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            if (isDateColumn(cell.getStringCellValue())) {

            }
            operationDate.add(parseStringToLocalDate(cell.getStringCellValue()));
        }
        // System.out.print(cell + "\t");*//*
    }*/



    @Override
    public void saveFile(String path) {

    }


    private Object getCellValue(Cell cell) {

        switch (cell.getCellType()) {
            case _NONE:
                break;
            case NUMERIC:
                return cell.getNumericCellValue();
            case STRING:
                return cell.getStringCellValue();
            case BLANK:
                break;
        }

        return null;
    }
}
