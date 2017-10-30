package com.example.materialtest;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class FruitActivity extends AppCompatActivity implements View.OnClickListener{
    public static final String FRUIT_NAME = "fruit_name";

    public static final String FRUIT_IMAGE_ID = "fruit_image_id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit);

        Intent intent = getIntent();
        String fruitName = intent.getStringExtra(FRUIT_NAME);
        int fruitImageId = intent.getIntExtra(FRUIT_IMAGE_ID, 0);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ImageView fruitImageView = (ImageView) findViewById(R.id.fruit_image_view);
        TextView fruitContentText = (TextView) findViewById(R.id.fruit_content_text);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //返回按钮,将home设置成返回上一步
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        //设置折叠toolbar的文字和图片，使用Glide设置图片
        collapsingToolbar.setTitle(fruitName);
        Glide.with(this).load(fruitImageId).into(fruitImageView);

        //模拟长文本
        String fruitContent = generateFruitContent(fruitName);
        fruitContentText.setText(fruitContent);


        //浮动ActionButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_fruit);
        fab.setOnClickListener(this);
    }

    /**
     * 模拟长文本
     * @param fruitName
     * @return
     */
    private String generateFruitContent(String fruitName) {
        StringBuilder fruitContent = new StringBuilder();
        for (int i = 0; i < 500; i++) {
            fruitContent.append(fruitName);
        }
        return fruitContent.toString();
    }

    /**
     * 返回的事件
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
           switch (v.getId()){
               case R.id.fab_fruit:
                           Snackbar.make(v, "操作评论!", Snackbar.LENGTH_LONG)
                                   .setAction("点赞", new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           Toast.makeText(FruitActivity.this, "+1", Toast.LENGTH_SHORT).show();
                                       }
                                   })
                                   .show();
                       break;
               default:break;

           }
    }
}
