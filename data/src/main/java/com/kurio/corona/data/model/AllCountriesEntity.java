package com.kurio.corona.data.model;

public class AllCountriesEntity {
    private String country;
    private int cases;
    private int todayCases;
    private int deaths;
    private int todayDeaths;
    private int recovered;
    private double casesPerOneMillion;
    private double deathsPerOneMillion;
    private CountryInfo countryInfo;


    public AllCountriesEntity(String country,
                              int cases,
                              int todayCases,
                              int deaths,
                              int todayDeaths,
                              int recovered,
                              double casesPerOneMillion,
                              double deathsPerOneMillion,
                              CountryInfo countryInfo) {
        this.country = country;
        this.cases = cases;
        this.todayCases = todayCases;
        this.deaths = deaths;
        this.todayDeaths = todayDeaths;
        this.recovered = recovered;
        this.casesPerOneMillion = casesPerOneMillion;
        this.deathsPerOneMillion = deathsPerOneMillion;
        this.countryInfo = countryInfo;
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

    public CountryInfo getCountryInfo() {
        return countryInfo;
    }
}
