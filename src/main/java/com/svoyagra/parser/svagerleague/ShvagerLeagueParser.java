package com.svoyagra.parser.svagerleague;

import com.svoyagra.domain.Tournament;
import com.svoyagra.parser.TournamentParser;
import com.svoyagra.tools.sheets.SheetsDocument;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShvagerLeagueParser implements TournamentParser {

    private final String tournamentName;
    private final SheetsDocument sheetsDocument;
    private final ShvagerLeagueRoundParser roundParser;

    @Override
    public Tournament parse() {
        for (int i = 0; i < sheetsDocument.numberOfSheets(); i++) {
            roundParser.parse(i);
        }
        return null;
    }
}
