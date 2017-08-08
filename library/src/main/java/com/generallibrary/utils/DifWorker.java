package com.generallibrary.utils;

import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池封装
 * Created by yueguang on 15-8-18.
 */
public class DifWorker {
    private ExecutorService mThreadPool;
    private static DifWorker mWorker;

    private DifWorker() {
        mThreadPool = Executors.newCachedThreadPool();
    }

    public static DifWorker getInstance() {
        if (mWorker == null) {
            mWorker = new DifWorker();
        }
        return mWorker;
    }

    public Future submitTask(Runnable task) {
        return mThreadPool.submit(task);
    }

    public void executeTask(Runnable task) {
        mThreadPool.execute(task);
    }

    public static boolean isInMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}
