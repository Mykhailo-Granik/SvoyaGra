package com.svoyagra.tools.sheets.excel.workbook.provider;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;

public class WorkbookProviderImpl implements WorkbookProvider {

    private final String path;

    public WorkbookProviderImpl(String path) {
        this.path = path;
    }

    @Override
    public Workbook provide() {
        try (FileInputStream fileInputStream = new FileInputStream(path); Workbook workbook = WorkbookFactory.create(fileInputStream)) {
            return workbook;
        } catch (Exception e) {
            throw new RuntimeException("Unable to open file", e);
        }
    }
}
