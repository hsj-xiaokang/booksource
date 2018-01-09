package hsj.com.loginpagedome.tools;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

/**
 * Created by hsj on 2018/1/8.
 */

public class EditTextClearTools {
    private static final String TAG = "EditTextClearTools";
    public static void addclerListener(final EditText e1, final ImageView m1) {

        e1.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                // TODO Auto-generated method stub
                Log.i(TAG,"onTextChanged");
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub
                Log.i(TAG,"beforeTextChanged");
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
                Log.i(TAG,"afterTextChanged");
                // 监听如果输入串长度大于0那么就显示clear按钮。
                String s1 = s + "";
                if (s.length() > 0) {
                    Log.i(TAG,">0");
                    m1.setVisibility(View.VISIBLE);
                } else {
                    Log.i(TAG,"<0");
                    m1.setVisibility(View.INVISIBLE);
                }

            }
        });

        m1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                // 清空输入框
                e1.setText("");

            }
        });

    }

}
