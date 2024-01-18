package com.svoyagra.parser.svagerleague;

import com.svoyagra.data.PlayerRepository;
import com.svoyagra.domain.Player;
import com.svoyagra.parser.TournamentPlayersParser;
import com.svoyagra.tools.sheets.CellParameters;
import com.svoyagra.tools.sheets.SheetsDocument;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static java.util.stream.IntStream.range;

@RequiredArgsConstructor
public class ShvagerLeaguePlayersParser implements TournamentPlayersParser {

    public static final int NAMES_COLUMN = 1;
    public static final int CITY_COLUMN = 2;
    public static final String LEAGUE = "Ліга";
    private final PlayerRepository playerRepository;
    private final SheetsDocument sheetsDocument;

    @Override
    public void upsertPlayers() {
        List<String> sheetNames = sheetsDocument.sheetNamesInOrder();
        range(0, sheetNames.size())
                .filter(sheetIndex -> isLeagueSheet(sheetNames, sheetIndex))
                .forEach(this::upsertPlayersFromSheet);
    }

    private boolean isLeagueSheet(List<String> sheetNames, int sheetIndex) {
        return sheetNames.get(sheetIndex).startsWith(LEAGUE);
    }

    private void upsertPlayersFromSheet(int sheetIndex) {
        int playerIndex = 1;
        while (hasPlayer(sheetIndex, playerIndex)) {
            String name = name(sheetIndex, playerIndex);
            String city = city(sheetIndex, playerIndex);
            playerRepository.upsert(new Player(firstName(name), lastName(name), city));
            playerIndex++;
        }
    }

    private boolean hasPlayer(int sheetIndex, int playerIndex) {
        return !name(sheetIndex, playerIndex).isEmpty();
    }

    private String name(int sheetIndex, int playerIndex) {
        return sheetsDocument.textValueOfCell(
                new CellParameters(sheetIndex, playerIndex, NAMES_COLUMN)
        );
    }

    private String city(int sheetIndex, int playerIndex) {
        return sheetsDocument.textValueOfCell(
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
