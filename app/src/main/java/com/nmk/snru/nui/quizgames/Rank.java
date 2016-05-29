package com.nmk.snru.nui.quizgames;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Rank extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
    }
    public void clickRankToMain(View view){
        startActivity(new Intent(Rank.this,MainActivity.class));
    }
}
