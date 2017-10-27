# 关注郭霖大神的微信公众号学习更多技术
致歉郭霖大神：非常感谢你的第一行代码这本书。同时电子书自己在网上找的，现在上传到该项目下，如果郭霖大神不许的话我会尽快删除电子书！

<img src="https://raw.githubusercontent.com/guolindev/booksource/master/qrcode.jpg" width="250" />

如果是对书中源码有疑问，也可以到公众号里给郭霖大神留言。微信扫一扫上方二维码即可关注。



我自己fork从大神的github仓库：https://github.com/guolindev/booksource



本人学习过程中添加自己的理解的注释和一些功能的补充（使用到某些大神的github代码我都注明仓库地址，没有注明的请谅解）


****************
一：
比如限于篇幅，郭霖大神讲解listView RecyclerView中没有下拉刷新上拉加载功能，
我有集成了该功能，RecyclerView方式【booksource\chapter3\ListViewTest】： * https://github.com/niniloveyou/SwipeRecyclerView      * SwipeRefreshLayout + RecyclerView 实现的下拉刷新，上拉加载更多；
                  listView方式    【booksource\chapter3\RecyclerViewTest】：     * https://github.com/chrisbanes/Android-PullToRefresh   * listView 实现的下拉刷新，上拉加载更多；
****************

																	
																	
****************
二：自定义UI组件和利用自定义属性attrs.xml，其中实现了自定UI attrs功能的的仿支付宝和QQ右上角的三个点点的pupmenu效果。
【booksource\chapter3\UIWidgetTest实现了自己定义UI Attrs 】
参考:http://blog.csdn.net/brokge/article/details/9713041/
http://www.cnblogs.com/xch-yang/p/6017685.html
http://www.cnblogs.com/whoislcj/p/5714760.html
************************


*****************************
三：动态权限的最佳实践解决方式之一，参考郭神视频
 * 郭神视频录播地址：http://edu.csdn.net/course/detail/3539
 * 整理的博客地址：http://blog.csdn.net/qq_31715429/article/details/52885787
booksource\chapter7\RuntimePermissionTest
******************************

*******************************
四.只有第一次调用SQLiteOpenHelper.getWritableDatabase或者SQLiteOpenHelper.getReadableDatabase才会创建数据库
*******************************

*******************************
五.视频播放
1.TextureView+SurfaceTexture+OpenGL ES来播放视频 github:https://github.com/ChouRay/PlayVideo-OpenGL和博客：
                                     1.http://blog.csdn.net/Ray_Chou/article/details/48416467
									 2.http://blog.csdn.net/ray_chou/article/details/48453473
									 3.http://blog.csdn.net/ray_chou/article/details/48473055
2.MediaPlayer+SurfaceView来进行播放视频
                                     1.http://blog.csdn.net/sno_guo/article/details/7770033
3.第一行代码第二版第七章
*********************************

***************************************
六.通知可以再activity，广播接收器，service里面创建
***************************************		

***************************************
七.使用fastjson
https://yq.aliyun.com/articles/69641
http://blog.csdn.net/feelang/article/details/41783317
compile 'com.alibaba:fastjson:1.2.10'
compile 'com.alibaba:fastjson:1.2.24'
compile 'com.alibaba:fastjson:1.1.56.android'
compile 'com.alibaba:fastjson:latest.integration'
***************************************		

*****************************************
八.sax pull 解析
sax   SAX加载到内存（DOM） 实际项目基本不用
pull  Pull基于事件 实际项目基本不用	

网络使用gson解析json 或者阿里巴巴的fastjson
okhttp3 或者 retrofit2（底层依然是okhttp，继续封装成restful风格的编程方式）

rxjava 基于观察者模式，推方式：传递数据给watcher
                       拉方式：传递被观察者自己给watcher，watcher自己获取

观察者模式介绍如下：
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2016/0518/4270.html

*****************************************	



**************************************************
九.
服务service是依赖于自己的当前的运用程序的进程里面，当前程序被杀掉的时候进程也就挂了；
服务并没有开启新的线程，需要自己开启线程，不然会阻塞主线程

多线程的学习！！！！！！博客：http://blog.csdn.net/aboy123/article/details/38307539/
1.继承Thread，然后start()运行
2.实现Runable（无返回值的任务必须Runnable接口）, new Thread(aRunableImplementObject).start();

3.使用ExecutorService、Callable、Future实现有返回结果的多线程（可返回值的任务必须实现Callable接口）


import java.util.concurrent.*;  
import java.util.Date;  
import java.util.List;  
import java.util.ArrayList;  
  
/** 
* 有返回值的线程 
*/  
@SuppressWarnings("unchecked")  
public class Test {  
public static void main(String[] args) throws ExecutionException,  
    InterruptedException {  
   System.out.println("----程序开始运行----");  
   Date date1 = new Date();  
  
   int taskSize = 5;  
   // 创建一个线程池  
   ExecutorService pool = Executors.newFixedThreadPool(taskSize);  
   // 创建多个有返回值的任务  
   List<Future> list = new ArrayList<Future>();  
   for (int i = 0; i < taskSize; i++) {  
    Callable c = new MyCallable(i + " ");  
    // 执行任务并获取Future对象  
    Future f = pool.submit(c);  
    // System.out.println(">>>" + f.get().toString());  
    list.add(f);  
   }   
  
   // 获取所有并发任务的运行结果  
   for (Future f : list) {  
    // 从Future对象上获取任务的返回值，并输出到控制台  
    System.out.println(">>>" + f.get().toString());  
   }  
  
   Date date2 = new Date();  
   System.out.println("----程序结束运行----，程序运行时间【"  
     + (date2.getTime() - date1.getTime()) + "毫秒】");  
}  
}  
  
class MyCallable implements Callable<Object> {  
private String taskNum;  
  
MyCallable(String taskNum) {  
   this.taskNum = taskNum;  
}  
  
public Object call() throws Exception {  
   System.out.println(">>>" + taskNum + "任务启动");  
   Date dateTmp1 = new Date();  
   Thread.sleep(1000);  
   Date dateTmp2 = new Date();  
   long time = dateTmp2.getTime() - dateTmp1.getTime();  
   System.out.println(">>>" + taskNum + "任务终止");  
   return taskNum + "任务返回运行结果,当前任务时间【" + time + "毫秒】";  
}  
} 

*************************************************



****************************************************
十.下载
http://www.cnblogs.com/liyiran/p/6393813.html
****************************************************

**************************************************
十一.通知和前台服务的区别
通知：booksource\chapter8\NotificationTest\app\src\main\java\com\example\notificationtest
前台服务：booksource\chapter10\ServiceTest\app\src\main\java\com\example\servicetest


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

                Intent intent = new Intent(this, NotificationActivity.class);
                //设置可以点击intent【延迟的intent】
                PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
                //获取NotificationManager
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                //构建Notification
                Notification notification = new NotificationCompat.Builder(this)
                        .setContentTitle("This is content title")
                        .setContentText("This is content text")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                        .setContentIntent(pi)
                        //.setAutoCancel(true)
                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
                        .setVibrate(new long[]{0, 1000, 1000, 1000})
                        .setLights(Color.GREEN, 1000, 1000)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        //.setStyle(new NotificationCompat.BigTextStyle().bigText("Learn how to build notifications, send and sync data, and use voice actions. Get the official Android IDE and developer tools to build apps for Android."))
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.big_image)))
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .build();
                //启动NotificationManager
                manager.notify(1, notification);
				
				
				
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;

        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("This is content title")
                .setContentText("This is content text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
				 //*********************前台服务取消无效********************
                .setAutoCancel(true)
                .build();

         // 不同于通知的地方
         //启动NotificationManager
         // manager.notify(1, notification);
        startForeground(1, notification);
****************************************************
			 
                                      									 

*******************************************************************************************************
android权限：参见博客http://blog.csdn.net/hijson/article/details/53783217
我们需要在AndroidManifest文件中声明我们所需要的权限(无论是normal permissions还是Dangerous Permission)

正常权限：

ACCESS_LOCATION_EXTRA_COMMANDS
ACCESS_NETWORK_STATE
ACCESS_NOTIFICATION_POLICY
ACCESS_WIFI_STATE
BLUETOOTH
BLUETOOTH_ADMIN
BROADCAST_STICKY
CHANGE_NETWORK_STATE
CHANGE_WIFI_MULTICAST_STATE
CHANGE_WIFI_STATE
DISABLE_KEYGUARD
EXPAND_STATUS_BAR
GET_PACKAGE_SIZE
INSTALL_SHORTCUT
INTERNET
KILL_BACKGROUND_PROCESSES
MODIFY_AUDIO_SETTINGS
NFC
READ_SYNC_SETTINGS
READ_SYNC_STATS
RECEIVE_BOOT_COMPLETED
REORDER_TASKS
REQUEST_IGNORE_BATTERY_OPTIMIZATIONS
REQUEST_INSTALL_PACKAGES
SET_ALARM
SET_TIME_ZONE
SET_WALLPAPER
SET_WALLPAPER_HINTS
TRANSMIT_IR
UNINSTALL_SHORTCUT
USE_FINGERPRINT
VIBRATE
WAKE_LOCK
WRITE_SYNC_SETTINGS

危险权限组和权限：

每个组申请一个权限，这个组的其他权限也一并申请了。
所有危险的 Android 系统权限都属于权限组。如果设备运行的是 Android 6.0（API 级别 23），并且应用的 targetSdkVersion 是 23 或更高版本，则当用户请求危险权限时系统会发生以下行为：

如果应用请求其清单中列出的危险权限，而应用目前在权限组中没有任何权限，则系统会向用户显示一个对话框，描述应用要访问的权限组。对话框不描述该组内的具体权限。例如，如果应用请求READ_CONTACTS 权限，系统对话框只说明该应用需要访问设备的联系信息。如果用户批准，系统将向应用授予其请求的权限。
如果应用请求其清单中列出的危险权限，而应用在同一权限组中已有另一项危险权限，则系统会立即授予该权限，而无需与用户进行任何交互。例如，如果某应用已经请求并且被授予了 READ_CONTACTS 权限，然后它又请求 WRITE_CONTACTS，系统将立即授予该权限。
任何权限都可属于一个权限组，包括正常权限和应用定义的权限。但权限组仅当权限危险时才影响用户体验。可以忽略正常权限的权限组。

如果设备运行的是 Android 5.1（API 级别 22）或更低版本，并且应用的 targetSdkVersion 是 22 或更低版本，则系统会在安装时要求用户授予权限。再次强调，系统只告诉用户应用需要的权限组，而不告知具体权限。

具体的危险权限组和权限如下：

CALENDAR

READ_CALENDAR
WRITE_CALENDAR

CAMERA

CAMERA

CONTACTS

READ_CONTACTS
WRITE_CONTACTS
GET_ACCOUNTS

LOCATION

ACCESS_FINE_LOCATION
ACCESS_COARSE_LOCATION

MICROPHONE

RECORD_AUDIO

PHONE

READ_PHONE_STATE
CALL_PHONE
READ_CALL_LOG
WRITE_CALL_LOG
ADD_VOICEMAIL
USE_SIP
PROCESS_OUTGOING_CALLS

SENSORS

BODY_SENSORS

SMS

SEND_SMS
RECEIVE_SMS
READ_SMS
RECEIVE_WAP_PUSH
RECEIVE_MMS

STORAGE

READ_EXTERNAL_STORAGE
WRITE_EXTERNAL_STORAGE


特殊权限：


SYSTEM_ALERT_WINDOW
WRITE_SETTINGS
有许多权限其行为方式与正常权限及危险权限都不同。SYSTEM_ALERT_WINDOW 和WRITE_SETTINGS 特别敏感，因此大多数应用不应该使用它们。如果某应用需要其中一种权限，必须在清单中声明该权限，并且发送请求用户授权的 intent。系统将向用户显示详细管理屏幕，以响应该 intent。

如需了解有关如何请求这些权限的详情，请参阅 SYSTEM_ALERT_WINDOW 和WRITE_SETTINGS 参考条目。


版权声明：本文为博主原创文章，未经博主允许不得转载。------http://blog.csdn.net/hijson/article/details/53783217
*************************************************************************************************************************																
																																					
