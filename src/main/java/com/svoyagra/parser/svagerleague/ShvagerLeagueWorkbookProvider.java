package com.svoyagra.parser.svagerleague;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;

public class ShvagerLeagueWorkbookProvider {

    private final String path;

    public ShvagerLeagueWorkbookProvider(String path) {
        this.path = path;
    }

    public Workbook provide() {
        try (FileInputStream fileInputStream = new FileInputStream(path); Workbook workbook = WorkbookFactory.create(fileInputStream)) {
            return workbook;
        } catch (Exception e) {
            throw new RuntimeException("Unable to open file", e);
        }
    }
}
