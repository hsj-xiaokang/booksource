package hsj.com.attrs.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import hsj.com.attrs.R;

/**
 * Created by hsj on 2018/1/4.
 * blog:http://blog.csdn.net/eyu8874521/article/details/8552534
 */

public class MyView extends View{
    private static final String TAG = "MyView";
    public Paint paint;
    private Context context;
    private String text;
    private int textColor;
    private float textSize;

    public MyView(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        context = context;
        paint = new Paint();
        //context通过调用obtainStyledAttributes方法来获取一个TypeArray，然后由该TypeArray来对属性进行设置
        //obtainStyledAttributes方法有三个，
        // 我们最常用的是有一个参数的obtainStyledAttributes(int[] attrs)，
        // 其参数直接styleable中获得
        //TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyView);
        TypedArray a = context.obtainStyledAttributes(attrs,R.styleable.MyView);
        textColor = a.getColor(R.styleable.MyView_myColor, 003344);
        textSize = a.getDimension(R.styleable.MyView_myTextSize, 33);
        String textTem = a.getString(R.styleable.MyView_myText);
        text = textTem == null ? "":textTem;
        Log.i("----------->textColor:"+textColor,"textSize:"+textSize+"text:"+text);
        paint.setTextSize(textSize);
        paint.setColor(textColor);
        //调用结束后务必调用recycle()方法，否则这次的设定会对下次的使用造成影响
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        //Android在用画笔的时候有三种Style，分别是
        //Paint.Style.STROKE 只绘制图形轮廓（描边）
        //Paint.Style.FILL 只绘制图形内容
        //Paint.Style.FILL_AND_STROKE 既绘制轮廓也绘制内容
        paint.setStyle(Style.FILL);

        //自定义的没有文本内容属性的时候使用
//        canvas.drawText("context.getString(R.string.app_name_func_attrs)", 10, 50, paint);
        canvas.drawText(text, 0, text.length(), 10,100,paint);
    }



}
