package com.nmk.snru.nui.quizgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private MySQLite mySQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySQLite = new MySQLite(this);
    }

    public void cilckRegisterMain(View view){
        startActivity(new Intent(MainActivity.this,Register.class));

    }

    public void  clickRankMain(View view){
        startActivity(new Intent(MainActivity.this,Rank.class));
    }

    public void  clickManageMain(View view){
        startActivity(new Intent(MainActivity.this,QuestionManager.class));
    }
}
