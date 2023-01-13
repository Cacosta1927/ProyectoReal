package com.example.proye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button btnIngresar, btnRegistro;
    EditText etEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.etEmail);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnRegistro = findViewById(R.id.btnRegistro);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            ///boton envia al menu
            public void onClick(View view) {
                String var = etEmail.getText().toString();
                Intent intent = new Intent(MainActivity.this,Home.class);
                intent.putExtra("email", var);
                startActivity(intent);
            }
        });
        ///boton envia az la vista de formulario
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String var = etEmail.getText().toString();
                Intent intent = new Intent(MainActivity.this,Formulario.class);
                startActivity(intent);
            }
        });

    }
}