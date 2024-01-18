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
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ExcelSheetsDocumentTest {

    public static final String TEXT = "text";
    public static final String LEAGUE_A = "Ліга А";
    public static final String LEAGUE_B = "Ліга Б";
    public static final String FINALS = "Фінали";
    public static final String RANDOM_SHEET_NAME = "Рандом";
    @Mock
    private WorkbookProvider workbookProvider;

    @Test
    public void shouldReturnTextValueOfCell() {
        CellParameters cellParameters = new CellParameters(0, 0, 0);
        prepareCell(cellParameters);
        ExcelSheetsDocument excelSheetTools = new ExcelSheetsDocument(workbookProvider);
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

    @Test
    public void shouldReturnNumberOfSheets() {
        Workbook workbook = mock(Workbook.class);
        when(workbookProvider.provide()).thenReturn(workbook);
        int numberOfSheets = 5;
        when(workbook.getNumberOfSheets()).thenReturn(numberOfSheets);
        ExcelSheetsDocument excelSheetTools = new ExcelSheetsDocument(workbookProvider);
        assertEquals(numberOfSheets, excelSheetTools.numberOfSheets());
    }

    @Test
    public void shouldReturnSheetNamesInOrder() {
        Workbook workbook = mock(Workbook.class);
        when(workbookProvider.provide()).thenReturn(workbook);
        when(workbook.getNumberOfSheets()).thenReturn(4);
        when(workbook.getSheetName(0)).thenReturn(LEAGUE_A);
        when(workbook.getSheetName(1)).thenReturn(LEAGUE_B);
        when(workbook.getSheetName(2)).thenReturn(FINALS);
        when(workbook.getSheetName(3)).thenReturn(RANDOM_SHEET_NAME);
        assertEquals(
                List.of(LEAGUE_A, LEAGUE_B, FINALS, RANDOM_SHEET_NAME),
                new ExcelSheetsDocument(workbookProvider).sheetNamesInOrder()
        );
    }

}