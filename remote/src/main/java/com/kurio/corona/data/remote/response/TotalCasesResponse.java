package com.kurio.corona.data.remote.response;

import com.google.gson.annotations.SerializedName;

public class TotalCasesResponse {
    @SerializedName("cases")
    private int cases;
    @SerializedName("deaths")
    private int deaths;
    @SerializedName("recovered")
    private int recovered;

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
