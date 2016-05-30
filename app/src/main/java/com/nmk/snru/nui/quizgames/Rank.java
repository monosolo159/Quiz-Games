package com.nmk.snru.nui.quizgames;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Rank extends AppCompatActivity {

    private String[][] array;
    private TextView txtRankNo,txtRankName,txtRankScore;
    private String strRankNo,strRankName,strRankScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        txtRankNo = (TextView) findViewById(R.id.txtRankNo);
        txtRankName = (TextView) findViewById(R.id.txtRankName);
        txtRankScore = (TextView) findViewById(R.id.txtRankScore);

        loadRank();
    }

    public void loadRank(){
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,MODE_PRIVATE,null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM rankTable ORDER BY score DESC LIMIT 10", null);
        strRankNo = "";
        strRankName ="";
        strRankScore = "";
        array = new String[2][cursor.getCount()];
        int i = 0;

        while(cursor.moveToNext()){
            String sqlName = cursor.getString(cursor.getColumnIndex("name"));
            String sqlScore = cursor.getString(cursor.getColumnIndex("score"));
            array[0][i] = sqlName;
            array[1][i] = sqlScore;
            strRankNo = strRankNo+"No."+(i+1)+"\n";
            strRankName = strRankName+""+array[0][i]+"\n";
            strRankScore = strRankScore+""+array[1][i]+"\n";
            i++;
        }
        txtRankNo.setText(strRankNo);
        txtRankName.setText(strRankName);
        txtRankScore.setText(strRankScore);
    }

    public void clickRankToMain(View view){
        startActivity(new Intent(Rank.this,MainActivity.class));
    }
}
