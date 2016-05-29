package com.nmk.snru.nui.quizgames;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by NUI on 29/5/2559.
 */
public class MyOpenHelper extends SQLiteOpenHelper{

    public static final String database_name = "MyQuizGames.db";
    private static final int database_version = 1;

    private static final String create_quiz_table = "create table quizTable("+
            "_id integer primary key, "+
            "question text, "+
            "answer1 text, "+
            "answer2 text, "+
            "answer3 text, "+
            "answer4 text, "+
            "answerTrue int);";

    private static final String create_rank_table = "create table rankTable("+
            "_id integer primary key, "+
            "name text, "+
            "score int);";

    public MyOpenHelper(Context context) {
        super(context, database_name, null, database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(create_quiz_table);
        sqLiteDatabase.execSQL(create_rank_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
