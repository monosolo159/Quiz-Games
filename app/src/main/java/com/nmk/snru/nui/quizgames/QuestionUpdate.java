package com.nmk.snru.nui.quizgames;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class QuestionUpdate extends AppCompatActivity {
    private EditText eQuestion, eA1, eA2, eA3, eA4, eATrue;
    private String strQuestion, strA1, strA2, strA3, strA4, strAnsTrue;

    private MySQLite mySQLite;
    private MyAlert myAlert = new MyAlert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_update);
        bindWidget();
        setValue();
    }

    private void setValue() {
        eQuestion.setText(QuestionManager.strQuestionSelect);
        eA1.setText(QuestionManager.strA1Select);
        eA2.setText(QuestionManager.strA2Select);
        eA3.setText(QuestionManager.strA3Select);
        eA4.setText(QuestionManager.strA4Select);
        eATrue.setText(QuestionManager.strATrueSelect);
    }

    private void bindWidget() {
        eQuestion = (EditText) findViewById(R.id.eTxtQuestionUpdate);
        eA1 = (EditText) findViewById(R.id.eTxtAns1Update);
        eA2 = (EditText) findViewById(R.id.eTxtAns2Update);
        eA3 = (EditText) findViewById(R.id.eTxtAns3Update);
        eA4 = (EditText) findViewById(R.id.eTxtAns4Update);
        eATrue = (EditText) findViewById(R.id.eTxtAnsTrueUpdate);
    }

    public void cilckQuestionUpdate(View view){

        strQuestion = eQuestion.getText().toString().trim();
        strA1 = eA1.getText().toString().trim();
        strA2 = eA2.getText().toString().trim();
        strA3 = eA3.getText().toString().trim();
        strA4 = eA4.getText().toString().trim();
        strAnsTrue = eATrue.getText().toString().trim();

        if(checkSpace()){
            myAlert.myDialog(this,"มีช่องว่าง","กรุณาระบุข้อมูลทุกช่อง");
        }else{

            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("ต้องการแก้ไขคำถามนี้ใช่หรือไม่ ?").setPositiveButton("ใช่",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    SQLiteDatabase sqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,MODE_PRIVATE,null);
                    sqLiteDatabase.execSQL("UPDATE quizTable SET question='"+strQuestion+"', answer1='"+strA1+"', answer2='"+strA2+"', answer3='"+strA3+"', answer4='"+strA4+"', answerTrue='"+strAnsTrue+"' WHERE _id='"+QuestionManager.intSelect+"'");
                    startActivity(new Intent(QuestionUpdate.this,QuestionManager.class));
                }
            }).setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog,int id) {
                    dialog.cancel();
                }
            }).show();
        }
    }

    private boolean checkSpace() {
        return strQuestion.equals("")||strA1.equals("") || strA2.equals("") || strA3.equals("") || strA4.equals("") || strAnsTrue.equals("");
    }

    public void cilckQuestionReset(View view){
        editextNull();
    }

    public void cilckQuestionBack(View view){
        startActivity(new Intent(QuestionUpdate.this,QuestionManager.class));

    }

    private void editextNull(){
        eQuestion.setText("");
        eA1.setText("");
        eA2.setText("");
        eA3.setText("");
        eA4.setText("");
        eATrue.setText("");
    }
}
