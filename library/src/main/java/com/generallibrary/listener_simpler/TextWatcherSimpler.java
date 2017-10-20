package com.generallibrary.listener_simpler;

import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by LiDaChang on 17/6/1.
 * __--__---__-------------__----__
 * 用于简化TextWatcher,只需要实现一个方法即可
 */

public abstract class TextWatcherSimpler implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
