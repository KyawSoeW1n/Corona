package com.kurio.corona.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import androidx.annotation.NonNull;

public class NetworkUtils {
    public static boolean isNetworkConnected(@NonNull Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
