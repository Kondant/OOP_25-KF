package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DeathCauseStaticList {
    private final List<DeathCauseStatistic> stat = new ArrayList<DeathCauseStatistic>();

    public void repopulate(String filePath) {
        stat.clear();
        try {
            FileReader file = new FileReader(filePath);
            BufferedReader br = new BufferedReader(file);
            String line;
            while ((line = br.readLine()) != null) {

                if (!line.trim().isEmpty()) {
                    try {
                        DeathCauseStatistic statistic = DeathCauseStatistic.fromCsvLine(line);
                        stat.add(statistic);
                    } catch (IllegalArgumentException e) {
                        System.err.println("Błąd parsowania linii: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd odczytu pliku: " + e.getMessage());

        }
    }

    public List<DeathCauseStatistic> getStat() {
        return stat;
    }
}