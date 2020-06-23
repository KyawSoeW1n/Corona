package com.kurio.corona.data.model;

public class TotalCasesEntity {
    int cases, deaths, recovered;

    public TotalCasesEntity(int cases, int deaths, int recovered) {
        this.cases = cases;
        this.deaths = deaths;
        this.recovered = recovered;
    }

    public int getCases() {
        return cases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getRecovered() {
        return recovered;
    }
}
