package com.svoyagra.tools.sheets;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CellParameters {

    private final int sheetIndex;
    private final int rowIndex;
    private final int columnIndex;

}
