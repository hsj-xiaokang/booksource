package com.example.hsj.beastactiontest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.hsj.beastactiontest.entity.Person;
import com.example.hsj.beastactiontest.utils.LogUtil;
import com.example.hsj.beastactiontest.utils.MyApplication;

/**
 * @author heshnegjin
 * @date 2017-10-31
 * Intent传参Parcelable方式
 */
public class PersonActivityIntentBySerializable extends AppCompatActivity {

    public final static String TAG = "IntentBySerializable";
    public final static String PERSON_DATA = "Person_data";

    public static void startPersonActivityIntentBySerializable(Person person){
        Intent intent = new Intent(MyApplication.getContext(),PersonActivityIntentBySerializable.class);
        intent.putExtra(PERSON_DATA,person);
        MyApplication.getContext().startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_intent_by_serializable);

        Person person = (Person) getIntent().getParcelableExtra(PERSON_DATA);
        LogUtil.d(TAG,"onCreate->"+person.getName());
        LogUtil.d(TAG,"onCreate->"+person.getAge());
        LogUtil.d(TAG,"onCreate->"+person.getAddr());

        TextView textView = (TextView) findViewById(R.id.PersonActivityIntentBySerializable);
        textView.setText("name="+person.getName() +" age="+ person.getAge()+" address="+person.getAddr());
    }
}
