package com.kurio.corona.domain.model;

public class TotalCases {
    int cases, deaths, recovered;

    public TotalCases(int cases, int deaths, int recovered) {
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
