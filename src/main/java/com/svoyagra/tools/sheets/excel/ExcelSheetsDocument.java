package com.svoyagra.tools.sheets.excel;

import com.svoyagra.tools.sheets.CellParameters;
import com.svoyagra.tools.sheets.SheetsDocument;
import com.svoyagra.tools.sheets.excel.workbook.provider.WorkbookProvider;
import org.apache.poi.ss.usermodel.Workbook;

public class ExcelSheetsDocument implements SheetsDocument {
    private final Workbook workbook;

    public ExcelSheetsDocument(WorkbookProvider workbookProvider) {
        this.workbook = workbookProvider.provide();
    }

    @Override
    public String textValueOfCell(CellParameters cellParameters) {
        return workbook.getSheetAt(cellParameters.getSheetIndex())
                .getRow(cellParameters.getRowIndex())
                .getCell(cellParameters.getColumnIndex())
                .getStringCellValue();
    }

    @Override
    public int numberOfSheets() {
        return workbook.getNumberOfSheets();
    }
}