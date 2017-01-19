package com.generallibrary.utils;

import android.os.CountDownTimer;
import android.widget.Button;

/**
 * Created by feng on 2016/10/12.
 * 倒计时功能
 */
public class TimeCountUtil extends CountDownTimer {
    private Button btn;//按钮

    // 在这个构造方法里需要传入三个参数，一个是Activity，一个是总的时间millisInFuture 60000是60s，
    // 一个是countDownInterval 间隔1000是1s，然后就是你在哪个按钮上做这个是，就把这个按钮传过来就可以了
    public TimeCountUtil(long millisInFuture, long countDownInterval, Button btn) {
        super(millisInFuture, countDownInterval);
        this.btn = btn;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        btn.setEnabled(false);//设置不能点击
        btn.setText("等待" + millisUntilFinished / 1000 + "秒");//设置倒计时时间

        //设置按钮为灰色，这时是不能点击的
//        Spannable span = new SpannableString(btn.getText().toString());//获取按钮的文字
//        span.setSpan(new ForegroundColorSpan(Color.RED), 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);//讲倒计时时间显示为红色
//        btn.setText(span);
    }


    @Override
    public void onFinish() {
        btn.setText("重新获取");
        btn.setEnabled(true);//重新获得点击
    }
}
