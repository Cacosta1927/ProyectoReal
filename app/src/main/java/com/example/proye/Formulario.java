package com.example.proye;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Formulario extends AppCompatActivity {

    EditText etNombre, etApellido, etEdad, etCorreo;
    Button btnAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        etNombre   = (EditText)findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        etEdad     = (EditText) findViewById(R.id.etEdad);
        etCorreo   = (EditText) findViewById(R.id.etCorreo);
        btnAceptar  = (Button) findViewById(R.id.btnAceptar);
///boton envia a mostrar los datos registraos
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String varnom = etNombre.getText().toString();
                String varape = etApellido.getText().toString();
                String vareda = etEdad.getText().toString();
                String varcor = etCorreo.getText().toString();
                Intent intent = new Intent(Formulario.this,MostrarDatos.class);
                intent.putExtra("nom", varnom);
                intent.putExtra("ape", varape);
                intent.putExtra("eda", vareda);
                intent.putExtra("cor", varcor);
                startActivity(intent);
            }
        });

    }
}