package com.example.proye;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Home extends AppCompatActivity {
    ImageView imMenu;
    ConstraintLayout clMenu,clsoporte,clExit,clJuego,clPueba;
    Boolean menuact = false;
    TextView tvCuenta;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imMenu = findViewById(R.id.imMenu);
        clMenu = findViewById(R.id.clMenu);
        clsoporte = findViewById(R.id.clSoporte);
        clExit = findViewById(R.id.clExit);
        tvCuenta =findViewById(R.id.tvCuenta);
        clJuego = findViewById(R.id.clJuegoo);
        clPueba = findViewById(R.id.clPrueba);
        Bundle usuario = getIntent().getExtras();
        String prueba = usuario.getString("email");

        tvCuenta.setText(prueba);

        imMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuact){
                    clMenu.setVisibility(View.GONE);
                    menuact = false;
                }
                else{
                    clMenu.setVisibility(View.VISIBLE);
                    menuact = true;
                }
            }
        });

      /* /clJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clMenu.setVisibility(View.GONE);
                clsoporte.setVisibility(View.VISIBLE);
                menuact = false;
                Intent intent = new Intent(Home.this, Juego.class);
                startActivity(intent);
            }
        });*/
        clJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String var = etEmail.getText().toString();
                Intent intent = new Intent(Home.this,Juego.class);
                startActivity(intent);
            }
        });

        clPueba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String var = etEmail.getText().toString();
                Intent intent = new Intent(Home.this,Prueba.class);
                startActivity(intent);
            }
        });

        clExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clMenu.setVisibility(View.GONE);
                clsoporte.setVisibility(View.VISIBLE);
                menuact = false;
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}