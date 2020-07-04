package com.kurio.corona.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import androidx.appcompat.app.AppCompatActivity;

import com.kurio.corona.utils.NetworkUtils;

public abstract class NetworkActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String intentAction = intent.getAction();
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intentAction)) {
                if (NetworkUtils.isNetworkConnected(context)) {
                    isOnline();
                } else {
                    isOffline();
                }
            } else {
                throw new IllegalStateException("Unexpected value: " + intentAction);
            }
        }
    };

    abstract void isOnline();

    abstract void isOffline();
}
