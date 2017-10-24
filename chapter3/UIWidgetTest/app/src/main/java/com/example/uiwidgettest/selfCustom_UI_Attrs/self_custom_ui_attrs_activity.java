package com.example.uiwidgettest.selfCustom_UI_Attrs;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.uiwidgettest.MainActivity;
import com.example.uiwidgettest.R;
import com.example.uiwidgettest.interFace.SelfCustomUIAttrsEvents;
import com.example.uiwidgettest.utils.ObjectUtils;

/**
 * Created by hsj on 2017/10/24.
 */

public class self_custom_ui_attrs_activity extends AppCompatActivity{
    private static final String TAG = "self_custom_ui_attrs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //隐藏actionbar
        ActionBar ab = getSupportActionBar();
        if(ab!= null){
            ab.hide();
        }
        setContentView(R.layout.activity_main_self_ustom_ui_attrs);

        //添加事件
        CustomTitleBar ui_attrs_0 = (CustomTitleBar) findViewById(R.id.ui_attrs_0);
        /**
         * 设置菜单风格，是PopupMenu，还是没有PopupMenu
         * CustomTitleBar.MENU_HAVE就不需要重写SelfCustomUIAttrsEvents.titleBarRightBtnEvent,写了也白写，不会调用它
         */
        ui_attrs_0.setMenu(CustomTitleBar.MENU_HAVE);
        ui_attrs_0.setSelfCustomUIAttrsEvents(new SelfCustomUIAttrsEvents(){

            @Override
            public void titleBarLeftBtnEvent() {
                Toast.makeText(self_custom_ui_attrs_activity.this,"title_bar_left-from[self_custom_ui_attrs_activity]",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void titleBarTitleEvent() {
                Toast.makeText(self_custom_ui_attrs_activity.this,"title_bar_title-from[self_custom_ui_attrs_activity]",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void titleBarRightBtnEvent() {
                Toast.makeText(self_custom_ui_attrs_activity.this,"title_bar_right-from[self_custom_ui_attrs_activity]",Toast.LENGTH_SHORT).show();

            }
        });

    }




}
