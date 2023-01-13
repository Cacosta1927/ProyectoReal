package com.example.proye;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    ImageView imMenu;
    ConstraintLayout clMenu,clBanner,clExit;
    Boolean menuact = false;
    TextView tvCuenta;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imMenu = findViewById(R.id.imMenu);
        clMenu = findViewById(R.id.clMenu);
        clBanner = findViewById(R.id.clBanner);
        clExit = findViewById(R.id.clExit);
        tvCuenta =findViewById(R.id.tvCuenta);

        Bundle varapellido = getIntent().getExtras();
        String prueba = varapellido.getString("ape") + " " + varapellido.getString("nombre");
        tvCuenta.setText(prueba);

        imMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (menuact){
                    clMenu.setVisibility(View.GONE);
                    clBanner.setVisibility(View.VISIBLE);
                    menuact = false;
                }
                else{
                    clMenu.setVisibility(View.VISIBLE);
                    clBanner.setVisibility(View.GONE);
                    menuact = true;
                }
            }
        });
        clExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clMenu.setVisibility(View.GONE);
                clBanner.setVisibility(View.VISIBLE);
                menuact = false;
                Intent intent = new Intent(Home.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}