package com.ypx.imagepickerdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker);

        AppCompatButton btnPicker = findViewById(R.id.btn_picker);
        btnPicker.setOnClickListener(view -> {

        });
        AppCompatButton btnPickerSample = findViewById(R.id.btn_show_sample);
        btnPickerSample.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PickerActivity.this, MainActivity.class));
            }
        });
    }
}