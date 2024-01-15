package com.svoyagra.parser.svagerleague;

import com.svoyagra.data.PlayerRepository;
import com.svoyagra.domain.Player;
import com.svoyagra.parser.TournamentPlayersParser;
import com.svoyagra.tools.sheets.excel.workbook.provider.WorkbookProviderImpl;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

public class ShvagerLeaguePlayersParser implements TournamentPlayersParser {

    public static final int NAMES_COLUMN = 1;
    public static final int CITY_COLUMN = 2;
    private final WorkbookProviderImpl workbookProvider;
    private final PlayerRepository playerRepository;

    public ShvagerLeaguePlayersParser(WorkbookProviderImpl workbookProvider, PlayerRepository playerRepository) {
        this.workbookProvider = workbookProvider;
        this.playerRepository = playerRepository;
    }

    @Override
    public void upsertPlayers() {
        Workbook workbook = workbookProvider.provide();
        for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
            String name = name(workbook, sheetIndex, 1);
            String city = city(workbook, sheetIndex, 1);
            playerRepository.upsert(new Player(firstName(name), lastName(name), city));
        }
    }

    private String name(Workbook workbook, int sheetIndex, int playerIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(playerIndex);
        Cell cell = row.getCell(NAMES_COLUMN);
        return cell.getStringCellValue();
    }

    private String city(Workbook workbook, int sheetIndex, int playerIndex) {
        Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(playerIndex);
        Cell cell = row.getCell(CITY_COLUMN);
        return cell.getStringCellValue();
    }

    private String firstName(String name) {
        return name.split(" ")[0];
    }

    private String lastName(String name) {
        return name.split(" ")[1];
    }
}
