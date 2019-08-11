package com.pchromic.BudgetManager.service;

import org.apache.poi.hssf.usermodel.HSSFSheet;

public interface FileService {


    void readFile();
    void openFile(String path);
    void saveFile(String path);
}
