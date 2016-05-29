package com.nmk.snru.nui.quizgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends AppCompatActivity {

    private EditText nameEditText;
    private String strName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        bindWidget();
    }

    public void clickRegisterRegister(View view){
        strName = nameEditText.getText().toString().trim();

        if (checkSpace()) {
            MyAlert myAlert = new MyAlert();
            myAlert.myDialog(this,"มีช่องว่าง","กรุณาระบุชื่อ");
        }else{
            updateValueToSQLite();
            cilckRegisterToQuestion();
        }
    }

    public void cilckRegisterToQuestion(){
        startActivity(new Intent(Register.this,Question.class));
    }

    private void updateValueToSQLite() {

    }

    private boolean checkSpace() {
        return strName.equals("");
    }

    private void bindWidget() {
        nameEditText = (EditText) findViewById(R.id.eTxtRegisterName);
    }
}
