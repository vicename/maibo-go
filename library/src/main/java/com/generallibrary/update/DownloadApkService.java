package com.generallibrary.update;

import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.widget.Toast;

import com.generallibrary.R;

import java.io.File;


public class DownloadApkService extends Service {
    //下载
    public static String APK_ARCHIVE = "application/vnd.android.package-archive";
    public static String PASS_WIFI_PLUS = "/takeaway/apk/";
    public static String PASS_WIFI_PLUS_UPDATE = "/takeaway/apk/update.apk";
    public static final String ACTION_CANCEL = "cn.WiFiManager.android.app.service.BVDownloadApkService.ACTION_CANCEL";
    public static final String ACTION_INSTALL = "cn.WiFiManager.android.app.service.BVDownloadApkService.ACTION_INSTALL";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String action = intent.getAction();//
        if (action.equals(ACTION_INSTALL)) {
            // 跳转到安装界面
            Intent installIntent = new Intent(Intent.ACTION_VIEW);
            installIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            installIntent.setAction(Intent.ACTION_VIEW);
            installIntent.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory()
                            .getPath() + PASS_WIFI_PLUS_UPDATE)),
                    APK_ARCHIVE);
            startActivity(installIntent);
        } else if (action.equals(ACTION_CANCEL)) {
            //取消下载
            DownloadAsyncTask.FLAG_KEEP_DOWNLOAD = false;
            Toast.makeText(this, "下载已取消", Toast.LENGTH_SHORT).show();
        }
        return super.onStartCommand(intent, flags, startId);
    }
}
