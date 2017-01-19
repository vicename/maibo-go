package com.generallibrary.utils;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * 弱引用的Handler
 */
public class WeakHandler extends Handler {

    public interface IHandler {
        void handleMessage(Message msg);
    }

    private WeakReference<IHandler> ref;

    public WeakHandler(IHandler t) {
        ref = new WeakReference<>(t);
    }

    @Override
    public void handleMessage(Message msg) {
        IHandler t = ref.get();
        if (t != null) {
            t.handleMessage(msg);
        }
    }
}
