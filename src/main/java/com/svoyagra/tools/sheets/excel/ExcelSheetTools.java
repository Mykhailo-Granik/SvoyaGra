package com.svoyagra.tools.sheets.excel;

import com.svoyagra.tools.sheets.CellParameters;
import com.svoyagra.tools.sheets.SheetTools;
import com.svoyagra.tools.sheets.excel.workbook.provider.WorkbookProvider;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

public class ExcelSheetTools implements SheetTools {
    private WorkbookProvider workbookProvider;
    public ExcelSheetTools(WorkbookProvider workbookProvider) {
        this.workbookProvider = workbookProvider;
    }

    @Override
    public String textValueOfCell(CellParameters cellParameters) {
        try (Workbook workbook = workbookProvider.provide()) {
            return workbook.getSheetAt(cellParameters.getSheetIndex())
                    .getRow(cellParameters.getRowIndex())
                    .getCell(cellParameters.getColumnIndex())
                    .getStringCellValue();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
