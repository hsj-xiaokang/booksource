package com.example.uibestpractice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<Msg>();
    //输入框
    private EditText inputText;
    //发送框
    private Button send;
    //比listview强的大RecyclerView
    private RecyclerView msgRecyclerView;
    //适配器
    private MsgAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //主布局文件
        setContentView(R.layout.activity_main);

        initMsgs(); // 初始化消息数据

        inputText = (EditText) findViewById(R.id.input_text);
        send = (Button) findViewById(R.id.send);

        msgRecyclerView = (RecyclerView) findViewById(R.id.msg_recycler_view);
       //三大布局管理之一  布局管理器见：http://www.jianshu.com/p/6d278427b18b
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        msgRecyclerView.setLayoutManager(layoutManager);
        /**
         * msgList仅仅是传给MsgAdapter的构造函数引用了，此时MsgAdapter和MainActivity指向同一个msgList
         */
        adapter = new MsgAdapter(msgList);
        msgRecyclerView.setAdapter(adapter);

        //发送按钮事件
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String content = inputText.getText().toString();

                if (!"".equals(content)) {
                    Msg msg = new Msg(content, Msg.TYPE_SENT);
                    msgList.add(msg);
                    //模拟回复
                    Msg msg_ = new Msg("hello "+Math.random(), Msg.TYPE_RECEIVED);
                    msgList.add(msg_);
                    // 当有新消息时，刷新ListView中的显示
                    adapter.notifyItemInserted(msgList.size() - 1);
                    // 将ListView定位到最后一行 展示最后一条
                    msgRecyclerView.scrollToPosition(msgList.size() - 1);
                    // 清空输入框中的内容
                    inputText.setText("");
                }
            }
        });
    }

    /**
     * 初始化数据条目
     */
    private void initMsgs() {
        Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
        msgList.add(msg1);
        Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
        msgList.add(msg2);
        Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED);
        msgList.add(msg3);
    }

}
