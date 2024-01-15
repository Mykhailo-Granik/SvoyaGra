package com.svoyagra.parser.svagerleague;

import com.svoyagra.data.PlayerRepository;
import com.svoyagra.domain.Player;
import com.svoyagra.tools.sheets.excel.ExcelSheetsTools;
import com.svoyagra.tools.sheets.excel.workbook.provider.WorkbookProviderImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShvagerLeaguePlayersParserTest {

    @Mock
    private WorkbookProviderImpl workbookProvider;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private Workbook workbook;
    @Mock
    private Sheet sheet;
    @Mock
    private Row row;
    @Mock
    private Cell nameCell;
    @Mock
    private Cell cityCell;

    @Test
    public void shouldUpsertOnePlayer() {
        when(workbookProvider.provide()).thenReturn(workbook);
        when(workbook.getNumberOfSheets()).thenReturn(1);
        when(workbook.getSheetAt(0)).thenReturn(sheet);
        when(sheet.getRow(1)).thenReturn(row);
        when(row.getCell(1)).thenReturn(nameCell);
        when(nameCell.getStringCellValue()).thenReturn("Михайло Гранік");
        when(row.getCell(2)).thenReturn(cityCell);
        when(cityCell.getStringCellValue()).thenReturn("Вінниця");
        ShvagerLeaguePlayersParser underTest = new ShvagerLeaguePlayersParser(
                playerRepository,
                new ExcelSheetsTools(workbookProvider)
        );
        underTest.upsertPlayers();
        verify(playerRepository).upsert(new Player("Михайло", "Гранік", "Вінниця"));
    }

}