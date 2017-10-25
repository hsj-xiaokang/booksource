package com.example.databasetest;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**SQLiteOpenHelper注意：如果数据库存在直接打开，否则创建一个数据库
 *      1.  getWritableDatabase   如果磁盘已经满了，那么出现异常exception
 *      2.  getReadableDatabase  如果磁盘已经满了，那么返回只读的方式打开数据库
 *
 *
 *
 *
 */
public class MainActivity extends AppCompatActivity {

    private MyDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //version大于上次才会调用onUpgrade方法
        //没有数据库才会调用onCreate方法
        dbHelper = new MyDatabaseHelper(this, "BookStore.db", null, 2);

        //创建数据库
        Button createDatabase = (Button) findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getWritableDatabase调用的时候才去创建数据库或者更新数据库，创建或者更新之后再一次调用就不去创建数据库或者更新数据库
                dbHelper.getWritableDatabase();
            }
        });

        /**
         * 增
         */
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getWritableDatabase第二次添加的时候调用不去创建数据库或者更新数据库
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                // 开始组装第一条数据
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values); // 插入第一条数据
                values.clear();
                // 开始组装第二条数据
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values); // 插入第二条数据
            }
        });

        /**
         * 更
         */
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("price", 10.99);
                //第三个参数就是where部分
                db.update("Book", values, "name = ?", new String[] { "The Da Vinci Code" });
            }
        });

        /**
         * 删
         */
        Button deleteButton = (Button) findViewById(R.id.delete_data);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                //第二个参数就是where部分
                db.delete("Book", "pages > ?", new String[] { "500" });
            }
        });

        /**
         * 查
         * 参数列表：
         * table            select from table_name
         * columns          column1,column2
         * selection        where column = value
         * selectionArgs    [where里面的value占位值]
         * groupBy          group by  column
         * having           having column = value
         * orderBy          order by column1,column2
         */
        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // 查询Book表中所有的数据
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                //原生sql语句查询
//                Cursor cursor = db.rawQuery("select * from Book where pages = ?",new String[]{"454"});
                if (cursor.moveToFirst()) {
                    do {
                        // 遍历Cursor对象，取出数据并打印
                        String name = cursor.getString(cursor.getColumnIndex("name"));
                        String author = cursor.getString(cursor.getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });

    }

}
