package com.generallibrary.base;

import android.app.Application;

/**
 * Created by Li DaChang on 16/9/5.
 * ..-..---.-.--..---.-...-..-....-.
 */
public class ApplicationBase extends Application {
    private static ApplicationBase mInstance;
    private boolean mIsDebug;
    private boolean mIsLogToFile;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
    }

    public void setIsDebug(boolean isDebug) {
        mIsDebug = isDebug;
    }

    public boolean getIsDebug() {
        return mIsDebug;
    }

    public void setIsLogToFile(boolean isLogToFile) {
        mIsLogToFile = isLogToFile;
    }

    public boolean getIsLogToFile() {
        return mIsLogToFile;
    }

    public static ApplicationBase getInstance() {
        return mInstance;
    }
}
