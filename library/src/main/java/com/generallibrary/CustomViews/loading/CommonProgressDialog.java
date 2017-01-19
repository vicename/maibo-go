package com.generallibrary.CustomViews.loading;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.widget.TextView;

import com.generallibrary.CustomViews.progressBarCircular.ProgressBarCircular;
import com.generallibrary.R;

/**
 * 自定义loadding对话框
 *
 * @author feng
 */
public class CommonProgressDialog extends Dialog {

    public CommonProgressDialog(Context context) {
        super(context, R.style.CustomProgressDialog);
        this.setContentView(R.layout.activity_base_dialog);
        this.getWindow().getAttributes().gravity = Gravity.CENTER;
    }

    public void setBackgroundColor(int color) {
        ProgressBarCircular progressBar = (ProgressBarCircular) this.findViewById(R.id.progress_bar);
        progressBar.setBackgroundColor(color);
    }

    public void setMessage(String s) {
        TextView tvMsg = (TextView) this.findViewById(R.id.id_tv_loadingmsg);
        tvMsg.setText(s);
    }
}
