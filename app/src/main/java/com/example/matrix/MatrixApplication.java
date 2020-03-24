package com.example.matrix;

import android.app.Application;

import com.google.firebase.messaging.FirebaseMessaging;

public class MatrixApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseMessaging.getInstance().subscribeToTopic("android");

    }
}
