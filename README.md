# 关注郭霖大神的微信公众号学习更多技术
致歉郭霖大神：非常感谢你的第一行代码这本书。同时电子书自己在网上找的，现在上传到该项目下，如果郭霖大神不许的话我会尽快删除电子书！

<img src="https://raw.githubusercontent.com/guolindev/booksource/master/qrcode.jpg" width="250" />

如果是对书中源码有疑问，也可以到公众号里给郭霖大神留言。微信扫一扫上方二维码即可关注。



我自己fork从大神的github仓库：https://github.com/guolindev/booksource



本人学习过程中添加自己的理解的注释和一些功能的补充（使用到某些大神的github代码我都注明仓库地址，没有注明的请谅解）


********************
零.自定义样式
booksource\chapter3\UIWidgetTest
********************


****************
一：
比如限于篇幅，郭霖大神讲解listView RecyclerView中没有下拉刷新上拉加载功能，
我有集成了该功能，RecyclerView方式【booksource\chapter3\ListViewTest】： * https://github.com/niniloveyou/SwipeRecyclerView      * SwipeRefreshLayout + RecyclerView 实现的下拉刷新，上拉加载更多；
                  listView方式    【booksource\chapter3\RecyclerViewTest】：     * https://github.com/chrisbanes/Android-PullToRefresh   * listView 实现的下拉刷新，上拉加载更多；
****************

																	
																	
****************
二：自定义UI组件和利用自定义属性attrs.xml，其中实现了自定UI attrs功能的的仿支付宝和QQ右上角的三个点点的pupmenu效果。
【booksource\chapter3\UIWidgetTest实现了自己定义UI Attrs 】【chapter3\UICustomViews】
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
http://blog.csdn.net/yy1300326388/article/details/52787853
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

************************************************
十二 
 material design   
 http://blog.csdn.net/column/details/androiddesign.html
 http://blog.csdn.net/leejizhou/article/details/50479934
 http://www.androidchina.net/1381.html
这和了下拉刷新，上拉加载 book\booksource\chapter12\MaterialTest
***********************************************


************************************************
十三 
多窗口模式
activity android:configChangs="orientation|keyborderHidden|screenSize|screenLayout" 
加入该配置后，无论是多窗口还是横竖屏，活动都不会被重新创建，而是会将屏幕变化的事件通知到Activity的onConfigurationChanged()方法里面，处理屏幕变化的逻辑代码.

多窗口模式视频的暂停逻辑应该在onstop里面处理，在onstart里面恢复视频播放.



禁用多窗口模式代码：该属性只能在API大于24上面有用
application android:resizebleActivity="false"





横屏竖屏portrait landscape：
activity android:screenOrientation="portrait" 
***********************************************

***********************************************
十四
activity启动模式
http://blog.csdn.net/mynameishuangshuai/article/details/51491074
***********************************************


***********************************************
十五
碎片
http://blog.csdn.net/guolin_blog/article/details/8881711
http://blog.csdn.net/wxx614817/article/details/50975265
获取屏幕宽高，动态碎片
```
 @Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.activity_main);  
        Display display = getWindowManager().getDefaultDisplay();  
        if (display.getWidth() > display.getHeight()) {  
            Fragment1 fragment1 = new Fragment1();  
            getFragmentManager().beginTransaction().replace(R.id.main_layout, fragment1).commit();  
        } else {  
            Fragment2 fragment2 = new Fragment2();  
            getFragmentManager().beginTransaction().replace(R.id.main_layout, fragment2).commit();  
        }  
    } 
```
***********************************************

***********************************************
十六
goson
http://blog.csdn.net/oqihaogongyuan/article/details/50944755
https://www.cnblogs.com/liqw/p/4266209.html
https://www.cnblogs.com/zhaoyanjun/p/5842601.html
***********************************************
	
***********************************************
十七
layout布局文件相关
http://blog.csdn.net/guolin_blog/article/details/8744943
http://blog.csdn.net/wxx614817/article/details/50975265
***********************************************	


***********************************************
十八
android五大布局
https://www.cnblogs.com/chiao/archive/2011/08/24/2152435.html
https://www.jianshu.com/p/4fac6304d872
***********************************************	

```
网络框架retrofit：
http://blog.csdn.net/carson_ho/article/details/73732076
https://www.jianshu.com/p/308f3c54abdd
https://www.jianshu.com/p/dfaf8e51f720
```

```
gson教程
https://www.jianshu.com/p/e740196225a4
```

```
四大activity模式
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0520/2897.html
```

```
V21
 在开发中遇到这个问题，把xml文件放在drawable-v21文件夹下，在Android版本API 22中，可以正常运行，但是在API19中则报出xml布局文件错误的问题，经过一番排查，才知道是手机版本兼容问题，drawable-v21文件夹是v7包用于存放xml文件，但是在API 21以上才可使用，否则会报出xml布局文件的错误，要在API 21以下使用drawable中的xml文件，还是要把 其放在drawable中。
 
 
values-v19/style.xml—对应api19+手机型号在此调用。 
values-v21/style.xml—对应api21+手机型号在此调用。 
values/style.xml—对应values-v19和values-v21的style.xml中没有对应主题时默认在此调用。
```

```
android:configChanges
http://blog.csdn.net/jkkjklmn/article/details/7349517
```

```
自定义属性
http://blog.csdn.net/eyu8874521/article/details/8552534
自定义控件
https://www.cnblogs.com/whoislcj/p/5714760.html
```

```
android selector和shape====【drawable】---动态样式
http://blog.csdn.net/qq_20785431/article/details/50198315
http://blog.csdn.net/wenwen091100304/article/details/49667293
https://www.cnblogs.com/kest/p/5153357.html
```

```
android 样式和主题====【values】---静态样式
https://www.cnblogs.com/wuyudong/p/5863285.html
http://www.jianshu.com/p/5b6b2b511e85
http://www.jcodecraeer.com/a/basictutorial/2016/0812/6533.html
http://blog.csdn.net/sshhbb/article/details/7219838/
http://blog.csdn.net/geek_geek/article/details/47669259
```

```
layer_list
http://blog.csdn.net/brokge/article/details/9713041/
```

```
android功能库
简书：https://www.jianshu.com
https://www.jianshu.com/p/3baddcf948af
https://www.jianshu.com/p/9c5c97762bfe
https://www.jianshu.com/p/634f18d74ab0
https://www.jianshu.com/p/da1ca645b95c
```
```
material
https://www.jianshu.com/p/776cc6329fff
```

```
android 学习
https://www.jianshu.com/p/8e73248d52f5
```
```
仿微信底部tab
Android Design新控件之TabLaout（二），仿微信实现App底部Tab布局
https://www.jianshu.com/p/ae8b3e4514c6
```

```
Android 仿美团顶部滑动菜单
https://www.jianshu.com/p/885a6922cdb0
```
```
compile 'com.google.guava:guava:22.0-android'
```

```
报表
Android强大的图表开源——MPAndroidChart
hellocharts-android开源图表库（效果非常好）
http://blog.csdn.net/zhangphil/article/details/47656521
http://blog.csdn.net/qq_37293612/article/details/54959726
http://blog.csdn.net/u010151514/article/details/52062052
```

```
findViewById--butterknife
--原理-不是注解反射-https://www.jianshu.com/p/0f3f4f7ca505#
apt-https://joyrun.github.io/2016/07/19/AptHelloWorld/
APT大概就是你声明的注解的生命周期为CLASS,然后继承AbstractProcessor类。继承这个类后，在编译的时候，编译器会扫描所有带有你要处理的注解的类，然后再调用AbstractProcessor的process方法，对注解进行处理，那么我们就可以在处理的时候，动态生成绑定事件或者控件的java代码，然后在运行的时候，直接调用bind方法完成绑定。
其实这种方式的好处是我们不用再一遍一遍地写findViewById和onClick了，这个框架在编译的时候帮我们自动生成了这些代码，然后在运行的时候调用就行了。

作者：尸情化异
链接：https://www.jianshu.com/p/0f3f4f7ca505#
來源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。

https://www.jianshu.com/p/9ad21e548b69
https://www.cnblogs.com/zhaoyanjun/p/6016341.html
https://www.jianshu.com/p/5dead31a84f6--8.x版本
```

```
ButterKnife结合RecyclerView.Adapter一起使用
http://blog.csdn.net/zhuhai__yizhi/article/details/50777067
```
```
ButterKnife数组
@BindArray() : 绑定string里面array数组
<resources>
    <string name="app_name">WiFi管家</string>
    
    <string-array name="city">
        <item>厦门市</item>
        <item>福州市</item>
        <item>泉州市</item>
        <item>漳州市</item>
        <item>龙岩市</item>
    </string-array>
    
</resources>



package com.zyj.wifi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ButterknifeActivity extends AppCompatActivity {

    @BindView( R.id.button1 ) //绑定button 控件
    public Button button1 ;

    @BindArray(R.array.city )  //绑定string里面array数组
    String [] citys ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);

        //绑定activity
        ButterKnife.bind( this ) ;

        button1.setText( citys[0] );
    }
}

```

```
android横竖屏切换
https://www.jianshu.com/p/d9281857c69a
```
```
android日历控件
https://www.jianshu.com/p/8396a4d39c01
https://www.jianshu.com/p/a2f102c728ce
http://blog.csdn.net/zhaozhuzi/article/details/70598382
```
```
ios风格的actionsheet 时间选择
https://www.jianshu.com/p/dd44981c038e
```
```
litePal
https://www.jianshu.com/p/bc68e763c7a2
```
```
arrays.xml
http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2015/0105/2263.html
```
```
热修复技术
https://mp.weixin.qq.com/s?__biz=MzI1MTA1MzM2Nw==&mid=400118620&idx=1&sn=b4fdd5055731290eef12ad0d17f39d4a&scene=1&srcid=1106Imu9ZgwybID13e7y2nEi#wechat_redirect
http://blog.csdn.net/lmj623565791/article/details/49883661
```
```
 Android开发——JVM、Dalvik以及ART的区别
http://blog.csdn.net/seu_calvin/article/details/52354964
```
```
android-apt(javac) VS anotationProcessor(javac jack)
http://blog.csdn.net/asce1885/article/details/52878076
```
```
android控件
https://www.jianshu.com/p/ca5d3e2a6ce8
```

```
本地项目依赖
compile project(':3Dtagcloudlib')
http://blog.csdn.net/it_talk/article/details/51144463
```

```
   //状态栏状态(沉浸式状态栏)-use:http://blog.csdn.net/lixuce1234/article/details/73991906
    compile 'com.jaeger.statusbarutil:library:1.4.0'
```

```
    //类似iOS的滑动返回界面
    compile 'cn.bingoogolapple:bga-swipebacklayout:1.1.4@aar'
```

```
    //上下拉刷新
    compile 'com.scwang.smartrefresh:SmartRefreshLayout:1.0.3'
    //没有使用特殊Header，可以不加这行
    compile 'com.scwang.smartrefresh:SmartRefreshHeader:1.0.3'
```
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
##目录
```
第1章　开始启程——你的第一行Android代码　1
1.1　了解全貌——Android王国简介　2
1.1.1　Android系统架构　2
1.1.2　Android已发布的版本　3
1.1.3　Android应用开发特色　4
1.2　手把手带你搭建开发环境　5
1.2.1　准备所需要的工具　5
1.2.2　搭建开发环境　5
1.3　创建你的第一个Android项目　9
1.3.1　创建HelloWorld项目　9
1.3.2　启动模拟器　12
1.3.3　运行HelloWorld　15
1.3.4　分析你的第一个Android程序　16
1.3.5　详解项目中的资源　22
1.3.6　详解build.gradle文件　23
1.4　前行必备——掌握日志工具的使用　26
1.4.1　使用Android的日志工具Log　26
1.4.2　为什么使用Log而不使用System.out　27
1.5　小结与点评　29
第2章　先从看得到的入手——探究活动　30
2.1　活动是什么　30
2.2　活动的基本用法　30
2.2.1　手动创建活动　31
2.2.2　创建和加载布局　32
2.2.3　在AndroidManifest文件中注册　35
2.2.4　在活动中使用Toast　37
2.2.5　在活动中使用Menu　38
2.2.6　销毁一个活动　40
2.3　使用Intent在活动之间穿梭　41
2.3.1　使用显式Intent　41
2.3.2　使用隐式Intent　44
2.3.3　更多隐式Intent的用法　46
2.3.4　向下一个活动传递数据　50
2.3.5　返回数据给上一个活动　51
2.4　活动的生命周期　53
2.4.1　返回栈　53
2.4.2　活动状态　54
2.4.3　活动的生存期　55
2.4.4　体验活动的生命周期　56
2.4.5　活动被回收了怎么办　62
2.5　活动的启动模式　63
2.5.1　standard　64
2.5.2　singleTop　65
2.5.3　singleTask　67
2.5.4　singleInstance　68
2.6　活动的最佳实践　71
2.6.1　知晓当前是在哪一个活动　71
2.6.2　随时随地退出程序　72
2.6.3　启动活动的最佳写法　74
2.7　小结与点评　75
第3章　软件也要拼脸蛋——UI开发的点点滴滴　76
3.1　如何编写程序界面　76
3.2　常用控件的使用方法　77
3.2.1　TextView　77
3.2.2　Button　80
3.2.3　EditText　82
3.2.4　ImageView　86
3.2.5　ProgressBar　88
3.2.6　AlertDialog　91
3.2.7　ProgressDialog　93
3.3　详解4种基本布局　94
3.3.1　线性布局　94
3.3.2　相对布局　100
3.3.3　帧布局　103
3.3.4　百分比布局　105
3.4　系统控件不够用？创建自定义控件　108
3.4.1　引入布局　109
3.4.2　创建自定义控件　111
3.5　最常用和最难用的控件——ListView　113
3.5.1　ListView的简单用法　114
3.5.2　定制ListView的界面　115
3.5.3　提升ListView的运行效率　119
3.5.4　ListView的点击事件　120
3.6　更强大的滚动控件——RecyclerView　122
3.6.1　RecyclerView的基本用法　122
3.6.2　实现横向滚动和瀑布流布局　125
3.6.3　RecyclerView的点击事件　130
3.7　编写界面的最佳实践　132
3.7.1　制作Nine-Patch图片　132
3.7.2　编写精美的聊天界面　135
3.8　小结与点评　141
第4章　手机平板要兼顾——探究碎片　142
4.1　碎片是什么　142
4.2　碎片的使用方式　144
4.2.1　碎片的简单用法　144
4.2.2　动态添加碎片　147
4.2.3　在碎片中模拟返回栈　150
4.2.4　碎片和活动之间进行通信　151
4.3　碎片的生命周期　151
4.3.1　碎片的状态和回调　151
4.3.2　体验碎片的生命周期　153
4.4　动态加载布局的技巧　156
4.4.1　使用限定符　156
4.4.2　使用最小宽度限定符　159
4.5　碎片的最佳实践——一个简易版的新闻应用　160
4.6　小结与点评　169
第5章　全局大喇叭——详解广播机制　170
5.1　广播机制简介　170
5.2　接收系统广播　171
5.2.1　动态注册监听网络变化　171
5.2.2　静态注册实现开机启动　174
5.3　发送自定义广播　177
5.3.1　发送标准广播　177
5.3.2　发送有序广播　179
5.4　使用本地广播　183
5.5　广播的最佳实践——实现强制下线功能　185
5.6　Git时间——初识版本控制工具　192
5.6.1　安装Git　192
5.6.2　创建代码仓库　193
5.6.3　提交本地代码　195
5.7　小结与点评　195
第6章　数据存储全方案——详解持久化技术　196
6.1　持久化技术简介　196
6.2　文件存储　197
6.2.1　将数据存储到文件中　197
6.2.2　从文件中读取数据　201
6.3　SharedPreferences存储　203
6.3.1　将数据存储到SharedPreferences中　203
6.3.2　从SharedPreferences中读取数据　206
6.3.3　实现记住密码功能　208
6.4　SQLite数据库存储　211
6.4.1　创建数据库　211
6.4.2　升级数据库　216
6.4.3　添加数据　219
6.4.4　更新数据　222
6.4.5　删除数据　224
6.4.6　查询数据　225
6.4.7　使用SQL操作数据库　228
6.5　使用LitePal操作数据库　229
6.5.1　LitePal简介　229
6.5.2　配置LitePal　230
6.5.3　创建和升级数据库　231
6.5.4　使用LitePal添加数据　236
6.5.5　使用LitePal更新数据　237
6.5.6　使用LitePal删除数据　240
6.5.7　使用LitePal查询数据　241
6.6　小结与点评　243
第7章　跨程序共享数据——探究内容提供器　244
7.1　内容提供器简介　244
7.2　运行时权限　245
7.2.1　Android权限机制详解　245
7.2.2　在程序运行时申请权限　249
7.3　访问其他程序中的数据　254
7.3.1　ContentResolver的基本用法　254
7.3.2　读取系统联系人　256
7.4　创建自己的内容提供器　260
7.4.1　创建内容提供器的步骤　261
7.4.2　实现跨程序数据共享　265
7.5　Git时间——版本控制工具进阶　275
7.5.1　忽略文件　275
7.5.2　查看修改内容　276
7.5.3　撤销未提交的修改　278
7.5.4　查看提交记录　279
7.6　小结与点评　280
第8章　丰富你的程序——运用手机多媒体　281
8.1　将程序运行到手机上　281
8.2　使用通知　283
8.2.1　通知的基本用法　283
8.2.2　通知的进阶技巧　289
8.2.3　通知的高级功能　291
8.3　调用摄像头和相册　293
8.3.1　调用摄像头拍照　294
8.3.2　从相册中选择照片　298
8.4　播放多媒体文件　303
8.4.1　播放音频　303
8.4.2　播放视频　307
8.5　小结与点评　311
第9章　看看精彩的世界——使用网络技术　312
9.1　WebView的用法　312
9.2　使用HTTP协议访问网络　314
9.2.1　使用HttpURLConnection　315
9.2.2　使用OkHttp　319
9.3　解析XML格式数据　321
9.3.1　Pull解析方式　324
9.3.2　SAX解析方式　326
9.4　解析JSON格式数据　329
9.4.1　使用JSONObject　330
9.4.2　使用GSON　331
9.5　网络编程的最佳实践　334
9.6　小结与点评　338
第10章　后台默默的劳动者——探究服务　339
10.1　服务是什么　339
10.2　Android多线程编程　340
10.2.1　线程的基本用法　340
10.2.2　在子线程中更新UI　341
10.2.3　解析异步消息处理机制　345
10.2.4　使用AsyncTask　347
10.3　服务的基本用法　349
10.3.1　定义一个服务　349
10.3.2　启动和停止服务　352
10.3.3　活动和服务进行通信　355
10.4　服务的生命周期　359
10.5　服务的更多技巧　359
10.5.1　使用前台服务　359
10.5.2　使用IntentService　361
10.6　服务的最佳实践——完整版的下载示例　365
10.7　小结与点评　378
第11章　Android特色开发——基于位置的服务　379
11.1　基于位置的服务简介　379
11.2　申请API Key　380
11.3　使用百度定位　384
11.3.1　准备LBS SDK　384
11.3.2　确定自己位置的经纬度　386
11.3.3　选择定位模式　391
11.3.4　看得懂的位置信息　393
11.4　使用百度地图　395
11.4.1　让地图显示出来　395
11.4.2　移动到我的位置　397
11.4.3　让“我”显示在地图上　400
11.5　Git时间——版本控制工具的高级用法　402
11.5.1　分支的用法　403
11.5.2　与远程版本库协作　404
11.6　小结与点评　406
第12章　最佳的UI体验——MaterialDesign实战　407
12.1　什么是Material Design　407
12.2　Toolbar　408
12.3　滑动菜单　415
12.3.1　DrawerLayout　415
12.3.2　NavigationView　418
12.4　悬浮按钮和可交互提示　423
12.4.1　FloatingActionButton　424
12.4.2　Snackbar　427
12.4.3　CoordinatorLayout　428
12.5　卡片式布局　430
12.5.1　CardView　431
12.5.2　AppBarLayout　437
12.6　下拉刷新　440
12.7　可折叠式标题栏　443
12.7.1　CollapsingToolbarLayout　443
12.7.2　充分利用系统状态栏空间　453
12.8　小结与点评　456
第13章　继续进阶——你还应该掌握的高级技巧　457
13.1　全局获取Context的技巧　457
13.2　使用Intent传递对象　461
13.2.1　Serializable方式　461
13.2.2　Parcelable方式　463
13.3　定制自己的日志工具　464
13.4　调试Android程序　466
13.5　创建定时任务　469
13.5.1　Alarm机制　469
13.5.2　Doze模式　471
13.6　多窗口模式编程　472
13.6.1　进入多窗口模式　473
13.6.2　多窗口模式下的生命周期　475
13.6.3　禁用多窗口模式　479
13.7　Lambda表达式　481
13.8　总结　485
第14章　进入实战——开发酷欧天气　486
14.1　功能需求及技术可行性分析　486
14.2　Git时间——将代码托管到GitHub上　489
14.3　创建数据库和表　494
14.4　遍历全国省市县数据　499
14.5　显示天气信息　509
14.5.1　定义GSON实体类　509
14.5.2　编写天气界面　514
14.5.3　将天气显示到界面上　520
14.5.4　获取必应每日一图　526
14.6　手动更新天气和切换城市　532
14.6.1　手动更新天气　532
14.6.2　切换城市　535
14.7　后台自动更新天气　540
14.8　修改图标和名称　542
14.9　你还可以做的事情　543
第15章　最后一步——将应用发布到360应用商店　545
15.1　生成正式签名的APK文件　545
15.1.1　使用Android Studio生成　546
15.1.2　使用Gradle生成　548
15.1.3　生成多渠道APK文件　551
15.2　申请360开发者账号　554
15.3　发布应用程序　556
15.4　嵌入广告进行盈利　560
15.4.1　注册腾讯广告联盟账号　560
15.4.2　新建媒体和广告位　562
15.4.3　接入广告SDK　564
15.4.4　重新发布应用程序　569
15.5　结束语　570
```
