package com.example.uicustomviews;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uicustomviews.counstomEvents.EditEventsProcess;
import com.example.uicustomviews.utils.ObjectUtils;

/**
 * 自定义组件标题栏
 */
public class TitleLayout extends LinearLayout implements View.OnClickListener{
    //标题栏
    private TextView textView;
    private Button titleBack;
    private Button titleEdit;

    //默认的标题栏
    private final static  String CONTENT = "";
    //默认的编辑栏
    private final static  String EDIT = "";
    //默认的返回栏
    private final static  String BACK = "";

    private EditEventsProcess editEventsProcess;

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //加载布局文件
        LayoutInflater.from(context).inflate(R.layout.title, this);

         titleBack = (Button) findViewById(R.id.title_back);
         titleEdit = (Button) findViewById(R.id.title_edit);
         titleBack.setOnClickListener(this);
         titleEdit.setOnClickListener(this);
         textView = (TextView) findViewById(R.id.title_text);
    }

    /**
     * 设置标题栏
     * @param titleContent
     */
    public void setTextView(String titleContent){
           if(ObjectUtils.isEmpty(titleContent)){
               textView.setText(CONTENT);
           }
           textView.setText(titleContent);
    }

    /**
     * 设置编辑栏
     * @param titleEditContent
     */
    public void setTitleEdit(String titleEditContent){
        if(ObjectUtils.isEmpty(titleEdit)){
            titleEdit.setText(EDIT);
        }
        titleEdit.setText(titleEditContent);
    }

    /**
     * 设置返回栏
     * @param titleBackContent
     */
    public void setTitleBack(String titleBackContent){
        if(ObjectUtils.isEmpty(titleBack)){
            titleBack.setText(BACK);
        }
        titleBack.setText(titleBackContent);
    }

    /**
     * 设置当前的EditEventsProcess
     */
    public void setEditEventsProcess(EditEventsProcess editEventsProcess){
             if(ObjectUtils.isEmpty(editEventsProcess)){
                 throw new NullPointerException("not have a EditEventsProcess!");
             }
            this.editEventsProcess = editEventsProcess;
    }

    /**
     * 自定义的事件
     * 编辑按钮
     *
     */
    public void doEvents() {
        //TODO
        if(ObjectUtils.isEmpty(this.editEventsProcess)){
            throw new NullPointerException("not have a editEventsProcess,can not do editEventsProcess!");
        }
        this.editEventsProcess.doEvents();
    }

    /**
     * 自定义事件的按钮事件
     * @param v
     */
    @Override
    public void onClick(View v) {
       switch (v.getId()){
           case R.id.title_edit:
               doEvents();
               break;
           case R.id.title_back:
               ((Activity) getContext()).finish();
               break;
           default:break;
       }
    }
}
