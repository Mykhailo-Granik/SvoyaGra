package com.svoyagra.parser.svagerleague;

import com.svoyagra.data.PlayerRepository;
import com.svoyagra.domain.Player;
import com.svoyagra.tools.sheets.CellParameters;
import com.svoyagra.tools.sheets.SheetsDocument;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShvagerLeaguePlayersParserTest {

    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private SheetsDocument sheetsDocument;

    @Test
    public void shouldUpsertOnePlayer() {
        when(sheetsDocument.sheetNamesInOrder()).thenReturn(List.of("Ліга А"));
        when(sheetsDocument.textValueOfCell(new CellParameters(0, 1, 1))).thenReturn("Михайло Гранік");
        when(sheetsDocument.textValueOfCell(new CellParameters(0, 1, 2))).thenReturn("Вінниця");
        when(sheetsDocument.textValueOfCell(new CellParameters(0, 2, 1))).thenReturn("");
        ShvagerLeaguePlayersParser underTest = new ShvagerLeaguePlayersParser(
                playerRepository,
                sheetsDocument
        );
        underTest.upsertPlayers();
        verify(playerRepository).upsert(new Player("Михайло", "Гранік", "Вінниця"));
    }

    @Test
    public void shouldUpsertMultiplePlayers() {
        when(sheetsDocument.sheetNamesInOrder()).thenReturn(List.of("Ліга А"));
        when(sheetsDocument.textValueOfCell(new CellParameters(0, 1, 1))).thenReturn("Михайло Гранік");
        when(sheetsDocument.textValueOfCell(new CellParameters(0, 1, 2))).thenReturn("Вінниця");
        when(sheetsDocument.textValueOfCell(new CellParameters(0, 2, 1))).thenReturn("Іван Іванов");
        when(sheetsDocument.textValueOfCell(new CellParameters(0, 2, 2))).thenReturn("Київ");
        when(sheetsDocument.textValueOfCell(new CellParameters(0, 3, 1))).thenReturn("");
        ShvagerLeaguePlayersParser underTest = new ShvagerLeaguePlayersParser(
                playerRepository,
                sheetsDocument
        );
        underTest.upsertPlayers();
        verify(playerRepository).upsert(new Player("Михайло", "Гранік", "Вінниця"));
        verify(playerRepository).upsert(new Player("Іван", "Іванов", "Київ"));
    }

    @Test
    public void shouldOnlyParsePlayersFromLeagueSheets() {
        when(sheetsDocument.sheetNamesInOrder()).thenReturn(List.of("Ліга А", "Ліга Б", "Фінали"));
        when(sheetsDocument.textValueOfCell(new CellParameters(0, 1, 1))).thenReturn("Михайло Гранік");
        when(sheetsDocument.textValueOfCell(new CellParameters(0, 1, 2))).thenReturn("Вінниця");
        when(sheetsDocument.textValueOfCell(new CellParameters(0, 2, 1))).thenReturn("");
        when(sheetsDocument.textValueOfCell(new CellParameters(1, 1, 1))).thenReturn("Іван Іванов");
        when(sheetsDocument.textValueOfCell(new CellParameters(1, 1, 2))).thenReturn("Київ");
        when(sheetsDocument.textValueOfCell(new CellParameters(1, 2, 1))).thenReturn("");
        lenient().when(sheetsDocument.textValueOfCell(new CellParameters(2, 1, 1))).thenReturn("Петро Петренко");
        lenient().when(sheetsDocument.textValueOfCell(new CellParameters(2, 1, 2))).thenReturn("Львів");
        lenient().when(sheetsDocument.textValueOfCell(new CellParameters(2, 2, 1))).thenReturn("Микола Миколенко");
        lenient().when(sheetsDocument.textValueOfCell(new CellParameters(2, 2, 2))).thenReturn("Одеса");
        lenient().when(sheetsDocument.textValueOfCell(new CellParameters(2, 3, 1))).thenReturn("");
        ShvagerLeaguePlayersParser underTest = new ShvagerLeaguePlayersParser(
                playerRepository,
                sheetsDocument
        );
        underTest.upsertPlayers();
        verify(playerRepository).upsert(new Player("Михайло", "Гранік", "Вінниця"));
        verify(playerRepository).upsert(new Player("Іван", "Іванов", "Київ"));
        verifyNoMoreInteractions(playerRepository);
    }

}