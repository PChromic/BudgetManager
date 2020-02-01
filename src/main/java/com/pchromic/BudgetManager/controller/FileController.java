package com.pchromic.BudgetManager.controller;

import com.pchromic.BudgetManager.service.FileService;
import org.springframework.web.bind.annotation.*;

import java.io.File;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FileController {

    private final FileService service;

    public FileController(FileService fileService) {
        this.service = fileService;
    }

    @GetMapping("/file/open")
    void openFile(@RequestParam(name = "path") String path) {
        //String filePath = "E://Projects/Angular/BudgetManager/src/assets/" + path;
        String filePath = new File("").getAbsolutePath() + "\\" + path;
        System.out.println(filePath);
        service.openFile(filePath);
        //service.readFile();
    }

    @GetMapping("/file/read")
    void readFile() {
        service.readFile();
    }

}