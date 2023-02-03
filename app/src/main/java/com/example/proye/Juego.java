package com.example.proye;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.CountDownTimer;

import com.bumptech.glide.Glide;
import com.example.proye.Adaptadores.AdapterOpciones;
import com.example.proye.Modelos.mArticulos;
import com.example.proye.Modelos.mOpciones;
import com.example.proye.Util.Variables;

import java.util.ArrayList;
import java.util.List;


public class Juego extends AppCompatActivity {
    //Nuestros 3 objetos principales
    RecyclerView rvOpciones;
    ImageView imgObjeto;
    Button btnMotos, btnCarros, btnTodos;

    //Modelos y Utilidades del proyecto
    List<mOpciones> mData;
    List<mArticulos> mDataArticulo;
    AdapterOpciones adapterOpciones;
    Variables variables = new Variables();
    //private Instant Glide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        OBJETOS();
        BOTONES();
        Seleccion();

    }

    private void Seleccion (){
        if (variables.seleccion == 0){
           PrimeraCarga();
        }else if(variables.seleccion == 1)

        {
            Motos();
        }else if(variables.seleccion == 2){
           Carros();
        }
    }

    private void OBJETOS(){
        rvOpciones = findViewById(R.id.rvOpciones);
        imgObjeto = findViewById(R.id.imgObjeto);
        btnTodos = findViewById(R.id.btnTodos);
        btnMotos = findViewById(R.id.btnMotos);
        btnCarros = findViewById(R.id.btnCarros);
    }
        private void BOTONES(){
            btnTodos.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    variables.seleccion = 0;

                    mData.clear();
                    mDataArticulo.clear();
                    PrimeraCarga();
                }
            });

        btnMotos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                variables.seleccion = 1;
                mData.clear();
                mDataArticulo.clear();
               Motos();
            }
        });

        btnCarros.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                variables.seleccion = 2;

                mData.clear();
                mDataArticulo.clear();
                Carros();
            }
        });
    }

    private void Carros(){

        //Cargar Imagen principal

        mDataArticulo = new ArrayList<>(); GET("https://cursoandroid2023.000webhostapp.com/api/query_tipoCategoria.php?tipo=1");
        final int[] valorDadoA = {0};

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
                //resutlado.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {

                valorDadoA[0] = (int)Math.floor(Math.random()*mDataArticulo.size());

                variables.id = mDataArticulo.get(valorDadoA[0]).getId();

                //Libreria Glide para cargar las imagenes de internet
                Glide.with(Juego.this).load(mDataArticulo.get(valorDadoA[0]).getImagen()).into(imgObjeto);

                //Cargar lista de Opciones

                mData = new ArrayList<>();

                mData.add(new mOpciones("0", mDataArticulo.get(valorDadoA[0]).getId(),mDataArticulo.get(valorDadoA[0]).getNombre()));

                mDataArticulo.remove(valorDadoA[0]);

                //Bucle for para los articulos de forma aleatoria
                for (int i = 0; i <= 2; i = i + 1){
                    int valorDadol = (int)Math.floor(Math.random()*mDataArticulo.size());
                    Log.d("Valor", String.valueOf(valorDadol));
                    Log.d("Valor", mDataArticulo.get(valorDadol).getId());
                    Log.d("Valor", mDataArticulo.get(valorDadol).getNombre());
                    String id = mDataArticulo.get(valorDadol).getId();
                    String nombre = mDataArticulo.get(valorDadol).getNombre();
                    mDataArticulo.remove(valorDadol);
                    mData.add(new mOpciones(String.valueOf(i), id, nombre));

                }

                adapterOpciones = new AdapterOpciones(Juego.this, mData);
                rvOpciones.setAdapter(adapterOpciones);
                rvOpciones.setLayoutManager(new LinearLayoutManager(Juego.this));

            }
        }.start();



  /*      mDataArticulo.add(new mArticulos("51","2","Clasico","https://thumbs.dreamstime.com/z/carro-antiguo-14103935.jpg"));
        mDataArticulo.add(new mArticulos("52","2","Deportivo","http://www.elistas.net/lista/mundo-autos/archivo/indice/221/msg/261/cid/image001.jpg"));
        mDataArticulo.add(new mArticulos("53","2","elegante","https://haciendofotos.com/wp-content/uploads/fotos-de-coches-fotos-de-coches-de-lujo-maserati-pixabay.jpg"));
        mDataArticulo.add(new mArticulos("54","2","Convertible","https://queautocompro.com/wp-content/uploads/2013/07/NewportConvertibleJaguarXJLCabriolet_01-(Copiar).jpg"));
        mDataArticulo.add(new mArticulos("55","2","Jeep","https://s3.envato.com/files/85505101/Jeep_Wrangler_Polar_2014_%20(12).jpg"));
        mDataArticulo.add(new mArticulos("56","2","Hibrido","https://www.somosindustria.com/media/img/socials/fb_Autos-hibridos_01-19.jpg"));
        mDataArticulo.add(new mArticulos("57","2","Electrico","https://static1.elcorreo.com/www/multimedia/202002/14/media/cortadas/coches-electricos-kSiC-U100156048802qtF-624x385@El%20Correo.jpg"));
*/


      /*  //Valor aletorio para eleccion del articulo principal
        int valorDadoA = (int)Math.floor(Math.random()*mDataArticulo.size());

        variables.id = mDataArticulo.get(valorDadoA).getId();

        //Libreria Glide para cargar las imagenes de internet
        Glide.with(this).load(mDataArticulo.get(valorDadoA).getImagen()).into(imgObjeto);
        //Cargar lista de Opciones

        mData = new ArrayList<>();

        mData.add(new mOpciones("0", mDataArticulo.get(valorDadoA).getId(),mDataArticulo.get(valorDadoA).getNombre()));

        mDataArticulo.remove(valorDadoA);

        //Bucle for para los articulos de forma aleatoria
        for (int i = 0; i <= 2; i = i + 1){
            int valorDado = (int)Math.floor(Math.random()*mDataArticulo.size());
            Log.d("Valor", String.valueOf(valorDado));
            Log.d("Valor", mDataArticulo.get(valorDado).getId());
            Log.d("Valor", mDataArticulo.get(valorDado).getNombre());
            String id = mDataArticulo.get(valorDado).getId();
            String nombre = mDataArticulo.get(valorDado).getNombre();
            mDataArticulo.remove(valorDado);
            mData.add(new mOpciones(String.valueOf(i), id, nombre));

            //if (i == 3) return;
        }

        adapterOpciones = new AdapterOpciones(this, mData);
        rvOpciones.setAdapter(adapterOpciones);
        rvOpciones.setLayoutManager(new LinearLayoutManager(this));*/

    }

    private void Motos(){

        //Cargar Imagen principal
        mDataArticulo = new ArrayList<>(); GET("https://cursoandroid2023.000webhostapp.com/api/query_tipoCategoria.php?tipo=2");
        final int[] valorDadoA = {0};

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
                //resutlado.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {

                valorDadoA[0] = (int)Math.floor(Math.random()*mDataArticulo.size());

                variables.id = mDataArticulo.get(valorDadoA[0]).getId();

                //Libreria Glide para cargar las imagenes de internet
                Glide.with(Juego.this).load(mDataArticulo.get(valorDadoA[0]).getImagen()).into(imgObjeto);

                //Cargar lista de Opciones

                mData = new ArrayList<>();

                mData.add(new mOpciones("0", mDataArticulo.get(valorDadoA[0]).getId(),mDataArticulo.get(valorDadoA[0]).getNombre()));

                mDataArticulo.remove(valorDadoA[0]);

                //Bucle for para los articulos de forma aleatoria
                for (int i = 0; i <= 2; i = i + 1){
                    int valorDadol = (int)Math.floor(Math.random()*mDataArticulo.size());
                    Log.d("Valor", String.valueOf(valorDadol));
                    Log.d("Valor", mDataArticulo.get(valorDadol).getId());
                    Log.d("Valor", mDataArticulo.get(valorDadol).getNombre());
                    String id = mDataArticulo.get(valorDadol).getId();
                    String nombre = mDataArticulo.get(valorDadol).getNombre();
                    mDataArticulo.remove(valorDadol);
                    mData.add(new mOpciones(String.valueOf(i), id, nombre));

                }

                adapterOpciones = new AdapterOpciones(Juego.this, mData);
                rvOpciones.setAdapter(adapterOpciones);
                rvOpciones.setLayoutManager(new LinearLayoutManager(Juego.this));

            }
        }.start();











      /*  mDataArticulo = new ArrayList<>();

        mDataArticulo.add(new mArticulos("1", "1","Ducati","https://www.enter.co/wp-content/uploads/2018/09/02-PANIGALE-V4-S_UC35011_High-768x432.jpg"));
        mDataArticulo.add(new mArticulos("2","1","Dominar","https://static.wixstatic.com/media/2c5853_fbf0f28141674515b7d679fc7f0218b5~mv2.jpg/v1/fill/w_952,h_652,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/2c5853_fbf0f28141674515b7d679fc7f0218b5~mv2.jpg"));
        mDataArticulo.add(new mArticulos("3","1","Pulsar","https://www.motor.com.co/__export/1645198347560/sites/motor/img/2022/02/18/20220218_093227367_6140f682e147b_r_1631648652274_56-28-1121-560.jpeg_172596871.jpeg"));
        mDataArticulo.add(new mArticulos("4","1","Vespa","https://prepro.loscoches.com/wp-content/uploads/2021/10/Vespa-Sprint-150.jpg.webp?_ga=2.143387511.334910163.1673882455-713543180.1673882455&_gl=1*m3i296*_ga*NzEzNTQzMTgwLjE2NzM4ODI0NTU.*_ga_N164ZHEW9P*MTY3Mzg4MjQ1NC4xLjEuMTY3Mzg4MjQ1NC4wLjAuMA.."));
        mDataArticulo.add(new mArticulos("5","1","Honda","https://mma.prnewswire.com/media/1451923/HONDA_XR_190L_2021.jpg?w=200"));
        mDataArticulo.add(new mArticulos("6","1","Harley","https://phantom-marca.unidadeditorial.es/f46903b10600c755406651b22f4a2745/resize/660/f/webp/assets/multimedia/imagenes/2021/04/27/16195129472150.jpg"));
        mDataArticulo.add(new mArticulos("7","1","rouster","https://revistamoto.com/wp_rm/wp-content/uploads/2018/01/Harley-Davidson-Livewire-Statics-019.jpg"));

        //Valor aletorio para eleccion del articulo principal
        int valorDadoA = (int)Math.floor(Math.random()*mDataArticulo.size());

        variables.id = mDataArticulo.get(valorDadoA).getId();

        //Libreria Glide para cargar las imagenes de internet
        Glide.with(this).load(mDataArticulo.get(valorDadoA).getImagen()).into(imgObjeto);
        //Cargar lista de Opciones

        mData = new ArrayList<>();

        mData.add(new mOpciones("0", mDataArticulo.get(valorDadoA).getId(),mDataArticulo.get(valorDadoA).getNombre()));

        mDataArticulo.remove(valorDadoA);

        //Bucle for para los articulos de forma aleatoria
        for (int i = 0; i <= 2; i = i + 1){
            int valorDado = (int)Math.floor(Math.random()*mDataArticulo.size());
            Log.d("Valor", String.valueOf(valorDado));
            Log.d("Valor", mDataArticulo.get(valorDado).getId());
            Log.d("Valor", mDataArticulo.get(valorDado).getNombre());
            String id = mDataArticulo.get(valorDado).getId();
            String nombre = mDataArticulo.get(valorDado).getNombre();
            mDataArticulo.remove(valorDado);
            mData.add(new mOpciones(String.valueOf(i), id, nombre));

            //if (i == 3) return;
        }

        adapterOpciones = new AdapterOpciones(this, mData);
        rvOpciones.setAdapter(adapterOpciones);
        rvOpciones.setLayoutManager(new LinearLayoutManager(this));*/

    }

    private void PrimeraCarga(){

        //Cargar Imagen principal

        mDataArticulo = new ArrayList<>(); GET("https://cursoandroid2023.000webhostapp.com/api/query_tipoCategoria.php?tipo=4");
        final int[] valorDadoA = {0};

        new CountDownTimer(1000, 1000) {

            public void onTick(long millisUntilFinished) {
                //resutlado.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {

                valorDadoA[0] = (int)Math.floor(Math.random()*mDataArticulo.size());

                variables.id = mDataArticulo.get(valorDadoA[0]).getId();

                //Libreria Glide para cargar las imagenes de internet
                Glide.with(Juego.this).load(mDataArticulo.get(valorDadoA[0]).getImagen()).into(imgObjeto);

                //Cargar lista de Opciones

                mData = new ArrayList<>();

                mData.add(new mOpciones("0", mDataArticulo.get(valorDadoA[0]).getId(),mDataArticulo.get(valorDadoA[0]).getNombre()));

                mDataArticulo.remove(valorDadoA[0]);

                //Bucle for para los articulos de forma aleatoria
                for (int i = 0; i <= 2; i = i + 1){
                    int valorDadol = (int)Math.floor(Math.random()*mDataArticulo.size());
                    Log.d("Valor", String.valueOf(valorDadol));
                    Log.d("Valor", mDataArticulo.get(valorDadol).getId());
                    Log.d("Valor", mDataArticulo.get(valorDadol).getNombre());
                    String id = mDataArticulo.get(valorDadol).getId();
                    String nombre = mDataArticulo.get(valorDadol).getNombre();
                    mDataArticulo.remove(valorDadol);
                    mData.add(new mOpciones(String.valueOf(i), id, nombre));

                }

                adapterOpciones = new AdapterOpciones(Juego.this, mData);
                rvOpciones.setAdapter(adapterOpciones);
                rvOpciones.setLayoutManager(new LinearLayoutManager(Juego.this));

            }
        }.start();











    /*    mDataArticulo = new ArrayList<>();

        mDataArticulo.add(new mArticulos("51","2","Clasico","https://thumbs.dreamstime.com/z/carro-antiguo-14103935.jpg"));
        mDataArticulo.add(new mArticulos("52","2","Deportivo","http://www.elistas.net/lista/mundo-autos/archivo/indice/221/msg/261/cid/image001.jpg"));
        mDataArticulo.add(new mArticulos("53","2","elegante","https://haciendofotos.com/wp-content/uploads/fotos-de-coches-fotos-de-coches-de-lujo-maserati-pixabay.jpg"));
        mDataArticulo.add(new mArticulos("54","2","Convertible","https://queautocompro.com/wp-content/uploads/2013/07/NewportConvertibleJaguarXJLCabriolet_01-(Copiar).jpg"));
        mDataArticulo.add(new mArticulos("55","2","Jeep","https://s3.envato.com/files/85505101/Jeep_Wrangler_Polar_2014_%20(12).jpg"));
        mDataArticulo.add(new mArticulos("56","2","Hibrido","https://www.somosindustria.com/media/img/socials/fb_Autos-hibridos_01-19.jpg"));
        mDataArticulo.add(new mArticulos("57","2","Electrico","https://static1.elcorreo.com/www/multimedia/202002/14/media/cortadas/coches-electricos-kSiC-U100156048802qtF-624x385@El%20Correo.jpg"));
        mDataArticulo.add(new mArticulos("1", "1","Ducati","https://www.enter.co/wp-content/uploads/2018/09/02-PANIGALE-V4-S_UC35011_High-768x432.jpg"));
        mDataArticulo.add(new mArticulos("2","1","Dominar","https://static.wixstatic.com/media/2c5853_fbf0f28141674515b7d679fc7f0218b5~mv2.jpg/v1/fill/w_952,h_652,al_c,q_85,usm_0.66_1.00_0.01,enc_auto/2c5853_fbf0f28141674515b7d679fc7f0218b5~mv2.jpg"));
        mDataArticulo.add(new mArticulos("3","1","Pulsar","https://www.motor.com.co/__export/1645198347560/sites/motor/img/2022/02/18/20220218_093227367_6140f682e147b_r_1631648652274_56-28-1121-560.jpeg_172596871.jpeg"));
        mDataArticulo.add(new mArticulos("4","1","Vespa","https://prepro.loscoches.com/wp-content/uploads/2021/10/Vespa-Sprint-150.jpg.webp?_ga=2.143387511.334910163.1673882455-713543180.1673882455&_gl=1*m3i296*_ga*NzEzNTQzMTgwLjE2NzM4ODI0NTU.*_ga_N164ZHEW9P*MTY3Mzg4MjQ1NC4xLjEuMTY3Mzg4MjQ1NC4wLjAuMA.."));
        mDataArticulo.add(new mArticulos("5","1","Honda","https://mma.prnewswire.com/media/1451923/HONDA_XR_190L_2021.jpg?w=200"));
        mDataArticulo.add(new mArticulos("6","1","Harley","https://phantom-marca.unidadeditorial.es/f46903b10600c755406651b22f4a2745/resize/660/f/webp/assets/multimedia/imagenes/2021/04/27/16195129472150.jpg"));
        mDataArticulo.add(new mArticulos("7","1","rouster","https://revistamoto.com/wp_rm/wp-content/uploads/2018/01/Harley-Davidson-Livewire-Statics-019.jpg"));




        //Valor aletorio para eleccion del articulo principal
        int valorDadoA = (int)Math.floor(Math.random()*mDataArticulo.size());

        variables.id = mDataArticulo.get(valorDadoA).getId();

        //Libreria Glide para cargar las imagenes de internet
        Glide.with(this).load(mDataArticulo.get(valorDadoA).getImagen()).into(imgObjeto);

        //Cargar lista de Opciones

        mData = new ArrayList<>();

        mData.add(new mOpciones("0", mDataArticulo.get(valorDadoA).getId(),mDataArticulo.get(valorDadoA).getNombre()));

        mDataArticulo.remove(valorDadoA);

        //Bucle for para los articulos de forma aleatoria
        for (int i = 0; i <= 2; i = i + 1){
            int valorDado = (int)Math.floor(Math.random()*mDataArticulo.size());
            Log.d("Valor", String.valueOf(valorDado));
            Log.d("Valor", mDataArticulo.get(valorDado).getId());
            Log.d("Valor", mDataArticulo.get(valorDado).getNombre());
            String id = mDataArticulo.get(valorDado).getId();
            String nombre = mDataArticulo.get(valorDado).getNombre();
            mDataArticulo.remove(valorDado);
            mData.add(new mOpciones(String.valueOf(i), id, nombre));

            //if (i == 3) return;
        }

        adapterOpciones = new AdapterOpciones(this, mData);
        rvOpciones.setAdapter(adapterOpciones);
        rvOpciones.setLayoutManager(new LinearLayoutManager(this));*/

    }
    public void GET(String url){
        mDataArticulo = new ArrayList<>();

        // Instantiate the RequestQueue.

        RequestQueue queue = Volley.newRequestQueue(this);
        String urlcategorias = url;
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
                            //tvOficina.setText(resultJSON);

                            Log.d("JSON", resultJSON.toString());

                            JSONArray contenidoJSON;

                            if (resultJSON.equals("1")){ //hay datos a mostrar

                                contenidoJSON = jsonObject.getJSONArray("oficinas"); //estado es el nombre del campo en el JSON
                                for (int i = 0; i <= contenidoJSON.length(); i = i + 1){

                                    String id = contenidoJSON.getJSONObject(i).getString("id");
                                    String descripcion = contenidoJSON.getJSONObject(i).getString("descripcion");
                                    String nombre = contenidoJSON.getJSONObject(i).getString("nombre");
                                    String fotoProducto = contenidoJSON.getJSONObject(i).getString("imagen");

                                    mDataArticulo.add(new mArticulos(id, id, nombre, fotoProducto));

                                }

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

}