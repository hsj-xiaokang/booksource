package com.example.servicebestpractice;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

public class DownloadService extends Service {
    private static final String TAG = "DownloadService";

    private DownloadTask downloadTask;

    private String downloadUrl;

    private DownloadListener listener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            //通知
            getNotificationManager().notify(1, getNotification("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            downloadTask = null;
            // 下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            //通知,不通知了，直接安装
            installAPK(getUri());
//            getNotificationManager().notify(1, getNotification("Download Success", -1));


            Toast.makeText(DownloadService.this, "Download Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            downloadTask = null;
            // 下载失败时将前台服务通知关闭，并创建一个下载失败的通知
            stopForeground(true);
            //通知
            getNotificationManager().notify(1, getNotification("Download Failed", -1));
            Toast.makeText(DownloadService.this, "Download Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            downloadTask = null;
            Toast.makeText(DownloadService.this, "Paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            downloadTask = null;
            //前台服务停止
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
        }

    };

    private DownloadBinder mBinder = new DownloadBinder();

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class DownloadBinder extends Binder {

        public void startDownload(String url) {
            if (downloadTask == null) {
                downloadUrl = url;
                downloadTask = new DownloadTask(listener);
                downloadTask.execute(downloadUrl);
                //前台服务
                startForeground(1, getNotification("Downloading...", 0));
                Toast.makeText(DownloadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
            }
        }

        public void pauseDownload() {
            if (downloadTask != null) {
                downloadTask.pauseDownload();
            }
        }

        /**
         * 1.下载中点击暂停再点击取消会删除
         * 2.下载完成点击取消会删除
         * 3.下载中点击取消不会删除，现在已经改造成会删除！
         */
        public void cancelDownload() {
            if (downloadTask != null) {
                downloadTask.cancelDownload();
                deleteFileByCancelButton(downloadUrl);
            } else {
                deleteFileByCancelButton(downloadUrl);
            }
        }

    }

    /**
     * 删除文件
     * @param downloadUrl
     */
    private void deleteFileByCancelButton(String downloadUrl){
        if (downloadUrl != null) {
            // 取消下载时需将文件删除，并将通知关闭
            String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
            String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
            File file = new File(directory + fileName);

            if (file.exists()) {
                file.delete();
            }
            //并将通知关闭
            getNotificationManager().cancel(1);
            //服务取消
            stopForeground(true);
            Toast.makeText(DownloadService.this, "Canceled", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * NotificationManager获取
     * @return
     */
    private NotificationManager getNotificationManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    /**
     * Notification获取
     * @param title
     * @param progress
     * @return
     */
    private Notification getNotification(String title, int progress) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        builder.setAutoCancel(true);
        if (progress >= 0) {
            // 当progress大于或等于0时才需显示下载进度
            builder.setContentText(progress + "%");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

    /**
     * 安装apk文件
     */
    private void installAPK(Uri uriApk) {
        if(uriApk == null){
            return ;
        }
        // 通过Intent安装APK文件
       /* Intent intents = new Intent();
        intents.setAction("android.intent.action.VIEW");
        intents.addCategory("android.intent.category.DEFAULT");
        intents.setType("application/vnd.android.package-archive");
        intents.setData(uriApk);
        intents.setDataAndType(uriApk,"application/vnd.android.package-archive");
        intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);*/
        Intent intent = new Intent(Intent.ACTION_VIEW);
       //判断是否是AndroidN以及更高的版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".fileProvider", getFile());
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
        } else {
            intent.setDataAndType(Uri.fromFile(getFile()), "application/vnd.android.package-archive");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        startActivity(intent);

        stopSelf();
//        startActivity(intents);
        // 如果不加上这句的话在apk安装完成之后点击单开会崩溃
        android.os.Process.killProcess(android.os.Process.myPid());
    }

    /**
     * 获取Uri
     * @return
     */
    private Uri getUri(){
        if(downloadUrl == null){
            return null;
        }
        //文件名
        String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
        //download目录
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();

        Log.d(TAG, "手机qq文件目录地址: "+directory+fileName);
        Uri uri = Uri.fromFile(new File(directory + fileName));
        Log.d(TAG, "手机qq文件目录地址Uri: "+uri.toString());

        return uri;
    }

    /**
     * File
     * @return
     */
    private File getFile(){
        //文件名
        String fileName = downloadUrl.substring(downloadUrl.lastIndexOf("/"));
        //download目录
        String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
        return new File(directory + fileName);
    }

}
