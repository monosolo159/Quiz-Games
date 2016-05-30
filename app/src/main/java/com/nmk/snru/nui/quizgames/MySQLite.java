package com.nmk.snru.nui.quizgames;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by NUI on 29/5/2559.
 */
public class MySQLite {
    private MyOpenHelper myOpenHelper;
    private SQLiteDatabase sqLiteDatabase;

    public static final String quiz_table = "quizTable";
    public static final String column_id = "_id";
    public static final String column_question = "question";
    public static final String column_answer1 = "answer1";
    public static final String column_answer2 = "answer2";
    public static final String column_answer3 = "answer3";
    public static final String column_answer4 = "answer4";
    public static final String column_answer_true = "answerTrue";

    public static final String rank_table = "rankTable";
    public static final String columnR_id = "_id";
    public static final String columnR_name = "name";
    public static final String columnR_score = "score";

    public MySQLite(Context context){
        myOpenHelper = new MyOpenHelper(context);
        sqLiteDatabase = myOpenHelper.getWritableDatabase();
    }

    public long addNewQuestion(String strQuiz, String strA1, String strA2, String strA3, String strA4, String strATrue){
        ContentValues contentValues = new ContentValues();
        contentValues.put(column_question,strQuiz);
        contentValues.put(column_answer1,strA1);
        contentValues.put(column_answer2,strA2);
        contentValues.put(column_answer3,strA3);
        contentValues.put(column_answer4,strA4);
        contentValues.put(column_answer_true,strATrue);
        return sqLiteDatabase.insert(quiz_table,null,contentValues);
    }

    public long addNewRank(String strName, int intScore){
        ContentValues contentValues = new ContentValues();
        contentValues.put(columnR_name,strName);
        contentValues.put(columnR_score,intScore);
        return sqLiteDatabase.insert(rank_table,null,contentValues);
    }

    public void addUpdateQuestion(int id,String strQuiz, String strA1, String strA2, String strA3, String strA4, String strATrue){
        sqLiteDatabase.execSQL("UPDATE quizTable SET question="+strQuiz+", answer1="+strA1+", answer2="+strA2+", answer3="+strA3+", answer4="+strA4+", answerTrue="+strATrue+" WHERE _id="+id);
    }
}
