package com.nmk.snru.nui.quizgames;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class Question extends AppCompatActivity {

    private String[][] array;
    private Random rand = new Random();
    private int countQa = 0,intQaRan = 0, intTrue =0, intFalse =0, intAnsRan=0;
    private final int maxQuestion = 10;

    private TextView txtCountQuestion, txtCountTrue, txtCountFalse, txtQuestion;
    private Button btnAns1, btnAns2, btnAns3, btnAns4;
    private MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        bindWidget();
        loadQuestion();

    }

    private void bindWidget() {
        txtCountQuestion = (TextView) findViewById(R.id.txtNumQuestion);
        txtCountTrue = (TextView) findViewById(R.id.txtNumTrue);
        txtCountFalse = (TextView) findViewById(R.id.txtNumFalse);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);
        btnAns1 = (Button) findViewById(R.id.btnA1);
        btnAns2 = (Button) findViewById(R.id.btnA2);
        btnAns3 = (Button) findViewById(R.id.btnA3);
        btnAns4 = (Button) findViewById(R.id.btnA4);
    }

    private void loadQuestion(){
        countQa = 0;

        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,MODE_PRIVATE,null);
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM quizTable", null);

        array = new String[7][cursor.getCount()];
        int i = 0;

        while(cursor.moveToNext()){
            String sqlId = cursor.getString(cursor.getColumnIndex("_id"));
            String sqlQuestion = cursor.getString(cursor.getColumnIndex("question"));
            String sqlA1 = cursor.getString(cursor.getColumnIndex("answer1"));
            String sqlA2 = cursor.getString(cursor.getColumnIndex("answer2"));
            String sqlA3 = cursor.getString(cursor.getColumnIndex("answer3"));
            String sqlA4 = cursor.getString(cursor.getColumnIndex("answer4"));
            String sqlATrue = cursor.getString(cursor.getColumnIndex("answerTrue"));
            array[0][i] = sqlId;
            array[1][i] = sqlQuestion;
            array[2][i] = sqlA1;
            array[3][i] = sqlA2;
            array[4][i] = sqlA3;
            array[5][i] = sqlA4;
            array[6][i] = sqlATrue;
            i++;
        }
        ranQuestion();
    }

    public void checkQuestion(View view){
        if(view.getId()==R.id.btnA1){
            if(intAnsRan==1){
                intTrue++;
                txtCountTrue.setText("ตอบถูก : "+intTrue);
            }else{
                intFalse++;
                txtCountFalse.setText("ตอบถูก : "+intFalse);
            }
        }else if(view.getId()==R.id.btnA2){
            if(intAnsRan==2){
                intTrue++;
                txtCountTrue.setText("ตอบถูก : "+intTrue);
            }else{
                intFalse++;
                txtCountFalse.setText("ตอบถูก : "+intFalse);
            }
        }else if(view.getId()==R.id.btnA3){
            if(intAnsRan==3){
                intTrue++;
                txtCountTrue.setText("ตอบถูก : "+intTrue);
            }else{
                intFalse++;
                txtCountFalse.setText("ตอบถูก : "+intFalse);
            }
        }else if(view.getId()==R.id.btnA4){
            if(intAnsRan==4){
                intTrue++;
                txtCountTrue.setText("ตอบถูก : "+intTrue);
            }else{
                intFalse++;
                txtCountFalse.setText("ตอบถูก : "+intFalse);
            }
        }

        if(countQa<=maxQuestion){
            ranQuestion();
        }

    }

    private void ranQuestion(){

        if(countQa>=maxQuestion){
            mySQLite = new MySQLite(this);
            mySQLite.addNewRank(Register.strName,intTrue);
            startActivity(new Intent(Question.this,Rank.class));
        }else{
            intQaRan = rand.nextInt(maxQuestion - countQa);
            txtCountQuestion.setText("คำถามที่ : " + (countQa+1) + "/" + maxQuestion);
            txtCountTrue.setText("ตอบถูก : " + intTrue);
            txtCountFalse.setText("ตอบผิด : " + intFalse);
            txtQuestion.setText("" + array[1][intQaRan]);
            btnAns1.setText("" + array[2][intQaRan].toString());
            btnAns2.setText("" + array[3][intQaRan].toString());
            btnAns3.setText("" + array[4][intQaRan].toString());
            btnAns4.setText("" + array[5][intQaRan].toString());
            intAnsRan = Integer.parseInt(array[6][intQaRan]);

            for (int a = intQaRan; a < (maxQuestion - countQa); a++) {
                if (a < (maxQuestion - 1)) {
                    array[0][a] = array[0][a + 1];
                    array[1][a] = array[1][a + 1];
                    array[2][a] = array[2][a + 1];
                    array[3][a] = array[3][a + 1];
                    array[4][a] = array[4][a + 1];
                    array[5][a] = array[5][a + 1];
                    array[6][a] = array[6][a + 1];
                }
            }
            countQa++;
        }//end if


    }
}
