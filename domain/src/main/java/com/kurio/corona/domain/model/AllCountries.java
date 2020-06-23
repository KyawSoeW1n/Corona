package com.kurio.corona.domain.model;

public class AllCountries {
    private String country;
    private int cases;
    private int todayCases;
    private int deaths;
    private int todayDeaths;
    private int recovered;
    private double casesPerOneMillion;
    private double deathsPerOneMillion;
    private String flag;

    public AllCountries(String country, int cases, int todayCases, int deaths, int todayDeaths, int recovered, double casesPerOneMillion, double deathsPerOneMillion, String flag) {
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.casesPerOneMillion = casesPerOneMillion;
        this.deathsPerOneMillion = deathsPerOneMillion;
        this.flag = flag;
    }

    public String getCountry() {
        return country;
    }

    public int getCases() {
        return cases;
    }

    public int getTodayCases() {
        return todayCases;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getTodayDeaths() {
        return todayDeaths;
    }

    public int getRecovered() {
        return recovered;
    }

    public double getCasesPerOneMillion() {
        return casesPerOneMillion;
    }

    public double getDeathsPerOneMillion() {
        return deathsPerOneMillion;
    }

    public String getFlag() {
        return flag;
    }
}
