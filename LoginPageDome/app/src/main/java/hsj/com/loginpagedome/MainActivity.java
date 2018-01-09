package hsj.com.loginpagedome;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import hsj.com.loginpagedome.tools.EditTextClearTools;
import hsj.com.loginpagedome.tools.ObjectUtils;

/**
 * blog:http://blog.csdn.net/androidmsky/article/details/49870823
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    EditText e1, e2;
    ImageView m1, m2;
    private EditText phonenumber;
    private EditText password;
    private Button login;

    private final static String LOGININFO = "loginInfo";
    private final static String PHONEN = "phoneN";
    private final static String PASSW = "passW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_login);
        phonenumber = (EditText) findViewById(R.id.phonenumber);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(this);
        if(checkLoginStatus()){
            //go home activity TODO
            goHome();
        }
//        init();
    }

    private void init() {
        // TODO Auto-generated method stub
        e1 = (EditText) findViewById(R.id.phonenumber);
        e2 = (EditText) findViewById(R.id.password);
        m1 = (ImageView) findViewById(R.id.del_phonenumber);
        m2 = (ImageView) findViewById(R.id.del_password);
        // 添加清楚监听器大气
        EditTextClearTools.addclerListener(e1, m1);
        EditTextClearTools.addclerListener(e2, m2);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                boolean flag = savePhoneNumberPassword();
                if(!flag){
                    Toast.makeText(this,"保存登录信息失败！",Toast.LENGTH_SHORT).show();
                }
                //go home activity TODO
                goHome();
                break;
            default:break;
        }
    }

    /**保存用户名，密码**/
    private boolean savePhoneNumberPassword(){
        String phoneN = phonenumber.getText().toString().trim();
        String passW  = password.getText().toString().trim();
        if(ObjectUtils.isEmpty(phoneN)){
            Toast.makeText(this,"phonenumber 不能为空！",Toast.LENGTH_SHORT).show();return Boolean.FALSE;}
        if(ObjectUtils.isEmpty(passW)){
            Toast.makeText(this,"password 不能为空！",Toast.LENGTH_SHORT).show();return Boolean.FALSE;}
        try{
            SharedPreferences.Editor editor = getSharedPreferences(LOGININFO, MODE_PRIVATE).edit();
            editor.putString(PHONEN, phoneN);
            editor.putString(PASSW, passW);
            editor.apply();
        }catch (Exception e){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**获取用户名和密码**/
    private boolean checkLoginStatus(){
        SharedPreferences pref = getSharedPreferences(LOGININFO, MODE_PRIVATE);
        String phoneN = pref.getString(PHONEN, "");
        String passW = pref.getString(PASSW, "");
        if(ObjectUtils.isEmpty(phoneN)){return Boolean.FALSE;}
        if(ObjectUtils.isEmpty(passW)){return Boolean.FALSE;}
        return Boolean.TRUE;
    }

    /**去主页**/
    private void goHome(){
        //TODO
        Toast.makeText(this,"go home!",Toast.LENGTH_SHORT).show();
    }

}
