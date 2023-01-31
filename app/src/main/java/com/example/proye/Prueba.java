package com.example.proye;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Prueba extends AppCompatActivity {

    TextView tvOficina, tvCantidad, tvLog, tvNomproduc;
    ImageView ivProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prueba);
        GET();

    }


    //funcion para capturar Json
    public void GET(){
        // Instantiate the RequestQueue.
        tvOficina = findViewById(R.id.tvOficinas);
        tvCantidad = findViewById(R.id.tvCantidad);
        tvLog = findViewById(R.id.tvLog4);
        tvNomproduc = findViewById(R.id.tvNomProduc2);
        ivProducto = findViewById(R.id.ivProducto);

        RequestQueue queue = Volley.newRequestQueue(this);
        String urlcategorias = "https://cursoandroid2023.000webhostapp.com/api/query_tipoCategoria.php?tipo=2";
        Log.d("URL", urlcategorias);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, urlcategorias,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        // Display the first 500 characters of the response string.
                        Log.e("VOLLEY exitosa", response);

                        JSONObject jsonObject = null;
                        try {
                            jsonObject = new JSONObject(response);

                            String resultJSON = jsonObject.getString("estado");//Estado es el nombre del campo en el JSON
                            tvOficina.setText(resultJSON);

                            Log.d("JSON", resultJSON.toString());

                            JSONArray contenidoJSON;

                            if (resultJSON.equals("1")){ //hay datos a mostrar

                                contenidoJSON = jsonObject.getJSONArray("oficinas"); //estado es el nombre del campo en el JSON


                                String id = contenidoJSON.getJSONObject(0).getString("id");
                                tvOficina.setText(id);

                                String totales = contenidoJSON.getJSONObject(0).getString("descripcion");
                                tvCantidad.setText(totales);
                                Log.d("JSON",totales);

                                String logjson = contenidoJSON.getJSONObject(0).getString("log");
                                tvLog.setText(logjson);

                                String nomproducto = contenidoJSON.getJSONObject(0).getString("nombre");
                                tvNomproduc.setText(nomproducto);
                                String fotoProducto = contenidoJSON.getJSONObject(0).getString("imagen");
                               pintarFoto(fotoProducto);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", "That didn't work!");
            }

        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);

    }


    public void pintarFoto(String foto){
        ivProducto = findViewById(R.id.ivProducto);
        Glide.with(this).load(foto).into(ivProducto);
    }


}