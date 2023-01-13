package com.example.proye;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.Normalizer;
import java.util.ResourceBundle;

public class MostrarDatos extends AppCompatActivity {

    TextView tvrNombre, tvrApellido, tvrEdad, tvrCorreo;
    Button btnOk;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_datos);
        btnOk = findViewById(R.id.btnOk);
        tvrNombre = findViewById(R.id.tvrNombre);
        tvrApellido = findViewById(R.id.tvrApellido);
        tvrEdad = findViewById(R.id.tvrEdad);
        tvrCorreo = findViewById(R.id.tvrCorreo);
        Bundle varnombre = getIntent().getExtras();
        Bundle varapellido = getIntent().getExtras();
        Bundle varedad = getIntent().getExtras();
        Bundle varcorreo = getIntent().getExtras();

        ///captura//////////
        tvrNombre.setText(varnombre.getString("nom"));
        tvrApellido.setText(varapellido.getString("ape"));
        tvrEdad.setText(varedad.getString("eda"));
        tvrCorreo.setText(varcorreo.getString("cor"));
////Boton
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String var = tvrApellido.getText().toString();
                String nom = tvrNombre.getText().toString();
                Intent intent = new Intent(MostrarDatos.this,MainActivity.class);
                intent.putExtra("ape", var);
                intent.putExtra("nombre", nom);
                startActivity(intent);
            }
  });
}
}
