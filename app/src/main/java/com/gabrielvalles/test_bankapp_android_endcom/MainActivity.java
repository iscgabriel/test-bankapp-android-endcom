package com.gabrielvalles.test_bankapp_android_endcom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView tvAgregarTarjeta = findViewById(R.id.tvAgregarTarjeta);
        tvAgregarTarjeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AgregarTarjetaActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}