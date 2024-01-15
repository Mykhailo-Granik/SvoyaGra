package com.svoyagra.parser.svagerleague;

import com.svoyagra.data.PlayerRepository;
import com.svoyagra.domain.Player;
import com.svoyagra.parser.TournamentPlayersParser;
import com.svoyagra.tools.sheets.CellParameters;
import com.svoyagra.tools.sheets.SheetsTools;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ShvagerLeaguePlayersParser implements TournamentPlayersParser {

    public static final int NAMES_COLUMN = 1;
    public static final int CITY_COLUMN = 2;
    private final PlayerRepository playerRepository;
    private final SheetsTools sheetsTools;

    @Override
    public void upsertPlayers() {
        for (int sheetIndex = 0; sheetIndex < sheetsTools.numberOfSheets(); sheetIndex++) {
            String name = name(sheetIndex, 1);
            String city = city(sheetIndex, 1);
            playerRepository.upsert(new Player(firstName(name), lastName(name), city));
        }
    }

    private String name(int sheetIndex, int playerIndex) {
        return sheetsTools.textValueOfCell(
                new CellParameters(sheetIndex, playerIndex, NAMES_COLUMN)
        );
    }

    private String city(int sheetIndex, int playerIndex) {
        return sheetsTools.textValueOfCell(
                new CellParameters(sheetIndex, playerIndex, CITY_COLUMN)
        );
    }

    private String firstName(String name) {
        return name.split(" ")[0];
    }

    private String lastName(String name) {
        return name.split(" ")[1];
    }
}
