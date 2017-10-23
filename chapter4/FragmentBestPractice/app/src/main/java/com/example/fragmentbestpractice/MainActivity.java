package com.example.fragmentbestpractice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * sw->shortest width 最短宽度(最小宽度限制)
 * 最小宽度限制  layout-sw600dp
 *              小于600dp的加载layout下面的布局
 *              大于600dp的加载layout_sw600dp下面的布局
 * http://blog.csdn.net/mafei852213034/article/details/51190309
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
/*最近做一个项目需要适配到不同的平板和手持设备上，在屏幕适配上遇到了一些问题，查了Android官方文档了解了一些技巧的，现在总结如下：
        先解释几个概念：
        1、dpi（dot per inch）,即每英寸像素，所有的android设备都会被分成120（low）,160(medium),240(high),320(xhigh)四种，
        后来随着市场上android设备越来越多，google官方又增加了213（Added in API level13）,480（Added in API level16）,
        640（Added in API level18）,三种dpi。比如320*240分辨率的屏幕物理尺寸2英寸*1.5英寸，dpi=160；
        2、dp或dip（density-independent pixel）逻辑密度计算单位，与像素的换算方式为px=dp*（dpi/160）。


        在android3.2以前，所有的资源文件都有相应的xhdpi,hdpi,mdpi,ldpi四种文件来对应，
        android3.2以后，为了提供更精准的对布局文件的控制，
        可以通过为资源文件（res目录下文件）增加后缀来指定该文件夹里的xml布局文件或color.xml，string.xml是为哪种大小的屏幕使用。
        第一种后缀：sw<N>dp,如layout-sw600dp, values-sw600dp
        这里的sw代表smallwidth的意思，当你所有屏幕的最小宽度都大于600dp时,
        屏幕就会自动到带sw600dp后缀的资源文件里去寻找相关资源文件，这里的最小宽度是指屏幕宽高的较小值，每个屏幕都是固定的，
        不会随着屏幕横向纵向改变而改变。
        第二种后缀w<N>dp 如layout-w600dp, values-w600dp
        带这样后缀的资源文件的资源文件制定了屏幕宽度的大于Ndp的情况下使用该资源文件，但它和sw<N>dp不同的是，
        当屏幕横向纵向切换时，屏幕的宽度是变化的，以变化后的宽度来与N相比，看是否使用此资源文件下的资源。
        第三种后缀h<N>dp 如layout-h600dp, values-h600dp
        这个后缀的使用方式和w<N>dp一样，随着屏幕横纵向的变化，屏幕高度也会变化，
        根据变化后的高度值来判断是否使用h<N>dp ，但这种方式很少使用，因为屏幕在纵向上通常能够滚动导致长度变化，
        不像宽度那样基本固定，因为这个方法灵活性不是很好，google官方文档建议尽量少使用这种方式。*/
