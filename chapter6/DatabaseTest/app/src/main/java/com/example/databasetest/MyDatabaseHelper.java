package com.example.databasetest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * 数据文件会在data/data/<package name>/databases下面
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {

    /**
     * 建立表语句
     * real表示浮点型数据
     */
    public static final String CREATE_BOOK = "create table Book ("
            + "id integer primary key autoincrement, "
            + "author text, "
            + "price real, "
            + "pages integer, "
            + "name text)";

    public static final String CREATE_CATEGORY = "create table Category ("
            + "id integer primary key autoincrement, "
            + "category_name text, "
            + "category_code integer)";

    private Context mContext;

    public MyDatabaseHelper(Context context,
                            String name,//数据库名称
                            SQLiteDatabase.CursorFactory factory,
                            int version//版本
      ) {
        super(context, name, factory, version);
        mContext = context;
    }

    /**
     * 建立数据库和数据表
     * 数据库存在就不会再一次创建了调用该方法了
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        db.execSQL(CREATE_CATEGORY);
        Toast.makeText(mContext, "onCreate succeeded", Toast.LENGTH_SHORT).show();
    }

    /**
     * version大于上一次就会执行该方法
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Book");
        db.execSQL("drop table if exists Category");
        onCreate(db);
        Toast.makeText(mContext, "onUpgrade succeeded", Toast.LENGTH_SHORT).show();
    }

}
///*输入 abd shell 进入设备控制台*/
//C:\Users\dudon>adb shell
//
///*使用 cd 命令进入到数据库存放目录下*/
//        root@vbox86p:/ # cd /data/data/com.example.dudon.databasetest/databases/
//
///*使用 ls 命令查看该目录里的文件*/
//        root@vbox86p: # ls
//
//        BookStore.db
///*db-journal是为了让数据库能够支持事务而产生的临时日志文件*/
//        BookStore.db-journal
//
///*借助 sqlite 命令打开数据库，输入sqlite3加数据库名即可*/
//        root@vbox86p: # sqlite3 BookStore.db                                                              <
//        SQLite version 3.7.11 2012-03-20 11:35:50
//        Enter ".help" for instructions
//        Enter SQL statements terminated with a ";"
//
///*输入 .table 命令，查看数据库中的表*/
//        sqlite> .table
//        android_metadata  book
//
///*输入 .schema 命令查看建表语句*/
//        sqlite> .schema
//        CREATE TABLE android_metadata (locale TEXT);
//        CREATE TABLE book (id  integer PRIMARY KEY Autoincrement ,
//        author text ,price real ,pages integer,name text );
//
