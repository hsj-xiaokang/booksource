package com.example.sharedpreferencestest;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 *  1.getSharedPreferences
 *                   说明：该方法是context里面的，第一个参数文件名称，文件都存放在/data/data/<package-name>/shared_prefs/目录下面
 *                                              第二个参数是唯一的一个MODE_PRIVATE模式
 *  2.getPreferences()
 *                   说明：该方法是activity里面，第一个参数【只有一个参数，回把当前的activity类名作为文件名称】，是唯一的一个MODE_PRIVATE模式
 *  3.PreferenceManager.getDefaultSharedPreferences()
 *                   说明：接受一个参数【只有一个参数，回把当前的程序包名作为文件名称】
 *                   PreferenceManager.getDefaultSharedPreferences(this).edit().putString("data","data").apply();
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button saveData = (Button) findViewById(R.id.save_data);
        saveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
                editor.putString("name", "Tom");
                editor.putInt("age", 28);
                editor.putBoolean("married", false);
                editor.apply();
            }
        });

        Button restoreData = (Button) findViewById(R.id.restore_data);
        restoreData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
                String name = pref.getString("name", "");
                int age = pref.getInt("age", 0);
                boolean married = pref.getBoolean("married", false);
                Log.d("MainActivity", "name is " + name);
                Log.d("MainActivity", "age is " + age);
                Log.d("MainActivity", "married is " + married);
            }
        });
    }

}
