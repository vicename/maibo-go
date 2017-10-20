package com.generallibrary.update;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v4.app.NotificationCompat;

import com.generallibrary.R;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 通知栏下载情况
 */
public class DownloadAsyncTask extends AsyncTask<String, Integer, File> {

    private final int PROGRESS = 100;
    public static boolean FLAG_KEEP_DOWNLOAD = true;
    private Context context;

    public DownloadAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setSmallIcon(R.drawable.picture);
        // 只通知一次
        builder.setOnlyAlertOnce(true);
        // 点击该通知后要跳转的Activity，这里是service
        Intent intent = new Intent(context, DownloadApkService.class);
        if (values[0] == 100) {
            // ACTION_INSTALL是DownloadApkService里的标志，这个是指启动安装界面
            intent.setAction(DownloadApkService.ACTION_INSTALL);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                    | Intent.FLAG_ACTIVITY_NEW_TASK);
            PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            // 获取通知管理器
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            builder.setContentTitle("准备安装更新");
            builder.setContentText("下载完成，点击进行安装");
            // 直接在标题栏显示的通知
            builder.setTicker("下载完成");
            // 控制点击后是否消失
            builder.setAutoCancel(false);
            mNotificationManager.notify(0, builder.build());
        } else {
            intent.setAction(DownloadApkService.ACTION_CANCEL);
            PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
            NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            builder.setContentTitle("正在下载");
            builder.setContentText("已下载" + values[0] + "%");
            //			builder.setContentInfo("点击取消下载");
            if (values[0] == 0) {
                builder.setProgress(100, values[0], true);
            } else {
                builder.setProgress(100, values[0], false);
            }
            builder.setOngoing(true);
            builder.setTicker("开始下载");
            builder.setAutoCancel(true);
            mNotificationManager.notify(0, builder.build());
        }
    }

    @Override
    protected File doInBackground(String... params) {
        try {
            FLAG_KEEP_DOWNLOAD = true;
            publishProgress(0);
            URL url = new URL(params[0]);
            URLConnection connection = url.openConnection();
            connection.connect();
            // progress bar
            int fileLength = connection.getContentLength();
            // download the file
            InputStream input = new BufferedInputStream(url.openStream());
            File dir = new File(Environment.getExternalStorageDirectory().getPath() + DownloadApkService.PASS_WIFI_PLUS);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(Environment.getExternalStorageDirectory().getPath() + DownloadApkService.PASS_WIFI_PLUS_UPDATE);
            OutputStream output = new FileOutputStream(file);
            byte data[] = new byte[1024];
            // 总共读入的文件
            long total = 0;
            // 每次读入文件
            int count;
            long curTime = System.currentTimeMillis();
            while ((count = input.read(data)) != -1 && FLAG_KEEP_DOWNLOAD) {
                total += count;
                if ((System.currentTimeMillis() - curTime) > 1000 || total == data.length) {
                    if (FLAG_KEEP_DOWNLOAD) {
                        publishProgress((int) (total * 100 / fileLength));
                    }
                    curTime = System.currentTimeMillis();
                }
                output.write(data, 0, count);
            }
            output.flush();
            output.close();
            input.close();
            return file;
        } catch (Exception e) {
        }
        if (!FLAG_KEEP_DOWNLOAD) {
            cancel(true);
        }
        return null;
    }

    @Override
    protected void onPostExecute(File result) {
        super.onPostExecute(result);
//        publishProgress(PROGRESS);
        if (result != null) {
            // 点击通知跳转的安装界面
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setAction(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(result), DownloadApkService.APK_ARCHIVE);
            context.startActivity(intent);

            Uri packageURI = Uri.parse("package:takeaway");
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            context.startActivity(uninstallIntent);
        }
    }
}
