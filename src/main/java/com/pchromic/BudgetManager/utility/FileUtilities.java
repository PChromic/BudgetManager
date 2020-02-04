package com.pchromic.BudgetManager.utility;

import org.springframework.stereotype.Component;

@Component
public interface FileUtilities {

    void openFile(String path);

    void saveFile(String path);

}