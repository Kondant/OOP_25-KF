package org.example;

import java.util.ArrayList;
import java.util.List;

public class DeathCauseStatistic {

    private String kodICD;
    private int[] deaths;
    public static DeathCauseStatistic fromCsvLine (String line) {
        String[] parts= line.split(",",-1);
        if(parts.length!=22){
            throw new IllegalArgumentException("zly format linii");
        }
        DeathCauseStatistic res = new DeathCauseStatistic(parts[0].trim());
        res.deaths = new int[20];
        for(int i=0;i<20;i++) {
            if (parts[i + 2].equals("-")) {
                res.deaths[i] = 0;
            } else {
                res.deaths[i] = Integer.parseInt(parts[i + 2]);
            }
        }
        return res;
    }
    public String getKodICD() {
        return kodICD;
    }

    public DeathCauseStatistic(String kodICD) {
        this.kodICD = kodICD;
    }
    public AgeBracketDeaths ageDiff(int age){
        if(age>95){
            age=95;
        }
        int najm,najw,smierci;
            int y = (age / 5);
            najm = 5 * y;
            int o = (age / 5)+1;
            najw = (5 * o) - 1;
            smierci = deaths[y];
            AgeBracketDeaths res1 = new AgeBracketDeaths(najm, najw, smierci);
        return res1;
    }
    public class AgeBracketDeaths{
        public final int young;
        public final int old;
        public final int deathCount;

        public AgeBracketDeaths(int young, int old, int deathCount) {
            this.young = young;
            this.old = old;
            this.deathCount = deathCount;
        }
    }
}
