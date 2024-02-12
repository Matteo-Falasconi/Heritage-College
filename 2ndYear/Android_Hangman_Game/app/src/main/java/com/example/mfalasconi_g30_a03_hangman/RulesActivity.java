package com.example.mfalasconi_g30_a03_hangman;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RulesActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        Button btnBackRules = findViewById(R.id.btnBackRules);
        btnBackRules.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int clicked = view.getId();
        if(clicked == R.id.btnBackRules) {
            finish();
        }
    }
}