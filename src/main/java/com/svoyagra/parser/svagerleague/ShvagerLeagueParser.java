package com.svoyagra.parser.svagerleague;

import com.svoyagra.domain.Tournament;
import com.svoyagra.parser.TournamentParser;
import com.svoyagra.tools.sheets.excel.workbook.provider.WorkbookProviderImpl;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;

public class ShvagerLeagueParser implements TournamentParser {

    private final String tournamentName;
    private final WorkbookProviderImpl workbookProvider;
    private final ShvagerLeagueRoundParser roundParser;

    public ShvagerLeagueParser(String tournamentName, WorkbookProviderImpl workbookProvider, ShvagerLeagueRoundParser roundParser) {
        this.tournamentName = tournamentName;
        this.workbookProvider = workbookProvider;
        this.roundParser = roundParser;
    }

    @Override
    public Tournament parse() {
        try (Workbook workbook = workbookProvider.provide()) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                roundParser.parse(workbook.getSheetAt(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
