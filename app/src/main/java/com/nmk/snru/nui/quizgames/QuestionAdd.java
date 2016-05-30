package com.nmk.snru.nui.quizgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class QuestionAdd extends AppCompatActivity {

    private EditText eQuestion, eA1, eA2, eA3, eA4, eATrue;;
    private String strQuestion, strA1, strA2, strA3, strA4, strAnsTrue;

    private MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_add);

        bindWidget();
    }

    private void bindWidget() {
        eQuestion = (EditText) findViewById(R.id.eTxtQuestion);
        eA1 = (EditText) findViewById(R.id.eTxtAns1);
        eA2 = (EditText) findViewById(R.id.eTxtAns2);
        eA3 = (EditText) findViewById(R.id.eTxtAns3);
        eA4 = (EditText) findViewById(R.id.eTxtAns4);
        eATrue = (EditText) findViewById(R.id.eTxtAnsTrue);
    }

    public void cilckQuestionAdd(View view){

        strQuestion = eQuestion.getText().toString().trim();
        strA1 = eA1.getText().toString().trim();
        strA2 = eA2.getText().toString().trim();
        strA3 = eA3.getText().toString().trim();
        strA4 = eA4.getText().toString().trim();
        strAnsTrue = eATrue.getText().toString().trim();
        MyAlert myAlert = new MyAlert();
        if(checkSpace()){
            myAlert.myDialog(this,"มีช่องว่าง","กรุณาระบุข้อมูลทุกช่อง");
        }else{
            mySQLite = new MySQLite(this);
            mySQLite.addNewQuestion(strQuestion,strA1,strA2,strA3,strA4,strAnsTrue);
            myAlert.myDialogGreen(this,"สถานะบันทึกข้อมูล","บันทึกข้อมูลสำเร็จ");
            editextNull();
        }

    }

    private boolean checkSpace() {
        return strQuestion.equals("")||strA1.equals("") || strA2.equals("") || strA3.equals("") || strA4.equals("") || strAnsTrue.equals("");
    }

    public void cilckQuestionReset(View view){
        editextNull();
    }

    public void cilckQuestionBack(View view){
        startActivity(new Intent(QuestionAdd.this,QuestionManager.class));

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
