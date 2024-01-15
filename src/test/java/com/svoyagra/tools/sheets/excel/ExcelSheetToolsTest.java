package com.svoyagra.tools.sheets.excel;

import com.svoyagra.tools.sheets.CellParameters;
import com.svoyagra.tools.sheets.excel.workbook.provider.WorkbookProvider;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ExcelSheetToolsTest {

    public static final String TEXT = "text";
    @Mock
    private WorkbookProvider workbookProvider;

    @Test
    public void shouldReturnTextValueOfCell() {
        CellParameters cellParameters = new CellParameters(0, 0, 0);
        prepareCell(cellParameters);
        ExcelSheetTools excelSheetTools = new ExcelSheetTools(workbookProvider);
        assertEquals(TEXT,
                excelSheetTools.textValueOfCell(cellParameters)
        );
    }

    private void prepareCell(CellParameters cellParameters) {
        Workbook workbook = mock(Workbook.class);
        when(workbookProvider.provide()).thenReturn(workbook);
        Sheet sheet = mock(Sheet.class);
        when(workbook.getSheetAt(cellParameters.getSheetIndex()))
                .thenReturn(sheet);
        Row row = mock(Row.class);
        when(sheet.getRow(cellParameters.getRowIndex())).thenReturn(row);
        Cell cell = mock(Cell.class);
        when(cell.getStringCellValue()).thenReturn(TEXT);
        when(row.getCell(cellParameters.getColumnIndex()))
                .thenReturn(cell);
    }

}