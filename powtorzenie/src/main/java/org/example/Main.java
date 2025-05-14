package org.example;

public class Main {
    public static void main(String[] args) {
        DeathCauseStatistic res = DeathCauseStatistic.fromCsvLine("A04.7,758,-,-,-,-,-,1,-,1,3,5,9,12,30,58,64,94,161,192,95,33");
        System.out.println(res.getKodICD());
        DeathCauseStatistic.AgeBracketDeaths result= res.ageDiff(100);
        System.out.println("young: "+ result.deathCount);
        DeathCauseStaticList resul=new DeathCauseStaticList();
        resul.repopulate();
    }

}