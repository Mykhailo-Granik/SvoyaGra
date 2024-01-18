package com.svoyagra.tools.sheets;

import java.util.List;

public interface SheetsDocument {

    String textValueOfCell(CellParameters cellParameters);

    int numberOfSheets();

    List<String> sheetNamesInOrder();

}
