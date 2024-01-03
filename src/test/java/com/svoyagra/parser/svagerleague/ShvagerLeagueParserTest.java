package com.svoyagra.parser.svagerleague;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ShvagerLeagueParserTest {

    public static final int NUMBER_OF_ROUNDS = 3;
    @Mock
    private ShvagerLeagueWorkbookProvider workbookProvider;
    @Mock
    private ShvagerLeagueRoundParser roundParser;
    @Mock
    private Workbook workbook;

    @Test
    public void shouldParseTournament() {
        when(workbookProvider.provide()).thenReturn(workbook);
        when(workbook.getNumberOfSheets()).thenReturn(NUMBER_OF_ROUNDS);
        when(workbook.getSheetAt(0)).thenReturn(null);
        ShvagerLeagueParser parser = new ShvagerLeagueParser("Tournament", workbookProvider, roundParser);
        parser.parse();
        verify(workbookProvider).provide();
        verify(roundParser, times(NUMBER_OF_ROUNDS)).parse(any());
    }

}