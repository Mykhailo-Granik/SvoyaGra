package com.svoyagra.data;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileRawPlayerData implements RawPlayerData {

    private final String fileName;

    public FileRawPlayerData(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public List<String> loadAllPlayers() {
        try {
            return Files.readAllLines(Paths.get(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
