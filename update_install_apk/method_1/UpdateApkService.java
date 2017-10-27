package cn.agrithings.ito.service;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageInfo;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.agrithings.ito.ITOApplication;
import cn.agrithings.ito.R;
import cn.agrithings.ito.entity.AppVersionInfo;
import cn.agrithings.ito.interfaces.RequestServes;
import cn.agrithings.ito.util.ActivityManager;
import cn.agrithings.ito.util.PublicUtil;
import cn.agrithings.ito.util.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateApkService extends Service {
    private Context context;

//    private DownLoadDialog dialog;

    private PackageInfo info;

    private AppVersionInfo.Data versionData;

    /** 安卓系统下载类 **/
    private DownloadManager manager;

    /** 接收下载完的广播 **/
    private DownloadCompleteReceiver receiver;

    private String downLoadPath;
    private String appName = "ITO";

    private long lastDownloadId = 0;

    //"content://downloads/my_downloads"必须这样写不可更改
    public static final Uri CONTENT_URI = Uri.parse("content://downloads/my_downloads");
    private boolean isAutoInsaller=true;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        context = ActivityManager.getActivityManager().currentActivity();
        if (context==null)context =getApplicationContext();
        if (intent!=null){
            isAutoInsaller = intent.getBooleanExtra("isAutoInstaller",true);
            checkedNewVersion();
        }
		initDownManager();
        return super.onStartCommand(intent, flags, startId);
    }

    private void checkedNewVersion(){
        final ProgressDialog dialog1 = new ProgressDialog(context,R.style.dialog);
        if (!isAutoInsaller){
            dialog1.setTitle("版本检测");
            dialog1.setMessage("正在检测新版本，请稍后...");
            dialog1.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
            dialog1.show();
        }
        PublicUtil.requestData().getNewVersion(appName,1).enqueue(new Callback<AppVersionInfo>() {
            @Override
            public void onResponse(Call<AppVersionInfo> call, Response<AppVersionInfo> response) {
                if (response.isSuccessful()){
                    AppVersionInfo versionInfo = response.body();
                    if (versionInfo!=null && versionInfo.getFlag()==1){
                        info = PublicUtil.getLocalVersion();
                        versionData = versionInfo.getData();
                        if (versionData!=null){
                            double version = TextUtils.isEmpty(versionData.getVerCode())?
                                    0:Double.parseDouble(versionData.getVerCode());
                            if (version>info.versionCode){
                                String url = versionInfo.getData().getUrl();
                                if (!TextUtils.isEmpty(url)){
                                    downLoadPath = RequestServes.HOST+"/"+url;
//                                    dialog = new DownLoadDialog();
                                    AlertDialog alertDialog = new AlertDialog.Builder(
                                            context,R.style.dialog).create();
                                    alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                                    alertDialog.setTitle("发现新版本");
                                    alertDialog.setMessage(versionData.getRemark());
                                    alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "更新", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            progressDialog();
                                            // 调用下载
                                            initDownManager();
                                        }
                                    });
                                    alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "稍后更新", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    alertDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialog) {
                                            if (dialog1.isShowing()){
                                                dialog1.dismiss();
                                            }
                                        }
                                    });
                                    alertDialog.show();
                                }
                            }else {
                                if (!isAutoInsaller){
                                    AlertDialog dialog = new AlertDialog.Builder(context,R.style.dialog).create();
                                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                                    dialog.setTitle("提示！");
                                    dialog.setMessage("已是最新版本！");
                                    dialog.setButton(DialogInterface.BUTTON_NEUTRAL, "确定", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                                        @Override
                                        public void onDismiss(DialogInterface dialog) {
                                            if (dialog1.isShowing()){
                                                dialog1.dismiss();
                                            }
                                        }
                                    });
                                    dialog.show();
                                    return;
                                }
                            }
                        }
                    }
                }
                if (dialog1.isShowing()){
                    dialog1.dismiss();
                }
            }

            @Override
            public void onFailure(Call<AppVersionInfo> call, Throwable t) {
                if (dialog1.isShowing()){
                    dialog1.dismiss();
                }
            }
        });
    }


    /** 初始化下载器 **/
    private void initDownManager() {
        manager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
        receiver = new DownloadCompleteReceiver();
        //设置下载地址
        DownloadManager.Request down = new DownloadManager.Request(
                Uri.parse(downLoadPath));
        // 设置允许使用的网络类型，这里是移动网络和wifi都可以
        down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                | DownloadManager.Request.NETWORK_WIFI);
        // 下载时，通知栏显示途中
        down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);
        // 显示下载界面
        down.setVisibleInDownloadsUi(true);
        // 设置下载后文件存放的位置
        down.setDestinationInExternalFilesDir(this,Environment.DIRECTORY_DOWNLOADS, appName+".apk");
        // 将下载请求放入队列
        lastDownloadId = manager.enqueue(down);
        //注册下载广播
        registerReceiver(receiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
//        //10.采用内容观察者模式实现进度
        DownloadChangeObserver downloadObserver = new DownloadChangeObserver(null);
        getContentResolver().registerContentObserver(CONTENT_URI, true, downloadObserver);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        // 注销下载广播
        if (receiver != null)
            unregisterReceiver(receiver);
        super.onDestroy();
    }
    //用于显示下载进度
    private class DownloadChangeObserver extends ContentObserver {
        private DownloadManager dManager;
        private DownloadManager.Query query;

        public DownloadChangeObserver(Handler handler) {
            super(handler);
            dManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
            query = new DownloadManager.Query();
            dManager.remove(lastDownloadId-1);
//            TextView mContent = dialog.getContentText();
//            mContent.setText("版本信息"+versionData.getVersion()+"\n"+versionData.getRemark()+"\n正在下载.....");
        }

        @Override
        public void onChange(boolean selfChange) {
            query.setFilterById(lastDownloadId);
            final Cursor cursor = dManager.query(query);
            if (cursor != null && cursor.moveToFirst()) {
                final int totalColumn = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES);
                final int currentColumn = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR);
                int totalSize = cursor.getInt(totalColumn);
                int currentSize = cursor.getInt(currentColumn);
                double percent =totalSize>0? (double)currentSize/totalSize:0;
                int program = (int) (percent*100);
                mProgress.setProgress(program);
            }
        }
    }

    // 接受下载完成后的intent
    private class DownloadCompleteReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            //判断是否下载完成的广播
            if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
                mProgress.dismiss();
                //获取下载的文件id
                long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//                //自动安装apk
                final Uri apk = manager.getUriForDownloadedFile(downId);
                if (apk!=null){
                    if (isAutoInsaller){
                        installAPK(apk);
                    }else {
                        AlertDialog dialog = new AlertDialog.Builder(context).create();
                        dialog.setTitle("下载完成");
                        dialog.setMessage("是否安装该版本");
                        dialog.setButton(DialogInterface.BUTTON_POSITIVE,"是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                installAPK(apk);
                                dialog.dismiss();
                            }
                        });
                        dialog.setButton(DialogInterface.BUTTON_NEGATIVE,"否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                        dialog.show();
                    }

                }
                //停止服务并关闭广播
                UpdateApkService.this.stopSelf();
            }
        }

        /**
         * 安装apk文件
         */
        private void installAPK(Uri apk) {
            // 通过Intent安装APK文件
            Intent intents = new Intent();
            intents.setAction("android.intent.action.VIEW");
            intents.addCategory("android.intent.category.DEFAULT");
            intents.setType("application/vnd.android.package-archive");
            intents.setData(apk);
            intents.setDataAndType(apk,"application/vnd.android.package-archive");
            intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            stopSelf();
            startActivity(intents);
            // 如果不加上这句的话在apk安装完成之后点击单开会崩溃
            android.os.Process.killProcess(android.os.Process.myPid());
        }

    }

    private ProgressDialog mProgress;

    /**
     * 进度条Dialog
     */
    private void progressDialog(){
        mProgress = new ProgressDialog(context,R.style.dialog);
        mProgress.setIcon(R.drawable.ic_launcher);
        mProgress.setTitle("正在下载中");
        mProgress.setMessage(versionData.getRemark());
        mProgress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgress.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        mProgress.setProgress(0);
        mProgress.show();
    }

}
