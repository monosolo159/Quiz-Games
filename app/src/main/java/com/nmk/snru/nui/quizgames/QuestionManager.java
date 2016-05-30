package com.nmk.snru.nui.quizgames;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class QuestionManager extends AppCompatActivity {

    private String[][] array;
    private Spinner spinner;
    public static int intSelect = 0;
    public static String strQuestionSelect,strA1Select,strA2Select,strA3Select,strA4Select,strATrueSelect;
    private MyAlert myAlert = new MyAlert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_manager);

        spinner = (Spinner) findViewById(R.id.spinnerQuestion);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intSelect = Integer.parseInt(array[0][position]);
                strQuestionSelect = array[1][position];
                strA1Select = array[2][position];
                strA2Select = array[3][position];
                strA3Select = array[4][position];
                strA4Select = array[5][position];
                strATrueSelect = array[6][position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        loadQuestion();
    }

    private void loadQuestion(){
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

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,   android.R.layout.simple_spinner_item, array[1]);
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinner.setAdapter(spinnerArrayAdapter);
    }

    public void clickManagerDelete(View view){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("ต้องการลบคำถามนี้ใช่หรือไม่ ?").setPositiveButton("ใช่",  new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,MODE_PRIVATE,null);
                sqLiteDatabase.execSQL("DELETE FROM quizTable WHERE _id = '"+intSelect+"'");
                //myAlert.myDialogGreen(this,"ลบข้อมูล","ลบคำถามสำเร็จ");
                loadQuestion();
            }
        }).setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog,int id) {

                dialog.cancel();
            }
        }).show();
    }

    public void  clickManageToMain(View view){
        startActivity(new Intent(QuestionManager.this,MainActivity.class));
    }

    public void  clickManageToAdd(View view){
        startActivity(new Intent(QuestionManager.this,QuestionAdd.class));
    }

    public void  clickManageToUpdate(View view){
        startActivity(new Intent(QuestionManager.this,QuestionUpdate.class));
    }


}
