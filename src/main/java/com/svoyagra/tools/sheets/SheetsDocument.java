package com.svoyagra.tools.sheets;

public interface SheetsDocument {

    String textValueOfCell(CellParameters cellParameters);

    int numberOfSheets();

}
