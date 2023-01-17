package com.example.proye.Adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proye.Juego;
import com.example.proye.Modelos.mOpciones;
import com.example.proye.R;
import com.example.proye.Util.Variables;

import java.util.List;


public class AdapterOpciones extends RecyclerView.Adapter<AdapterOpciones.MyViewHolder> {

        boolean ischeckDone = false;

        private Context mContext ;
        List<mOpciones> mData;
        Variables variables = new Variables();

        public AdapterOpciones(Context mContext, List<mOpciones> mData) {
                this.mContext = mContext;
                this.mData = mData;

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

                View view ;
                LayoutInflater mInflater = LayoutInflater.from(mContext);
                view = mInflater.inflate(R.layout.cardveiw_opciones,parent,false);
                return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {

                holder.tvOpcion.setText(mData.get(position).getNombre());

                holder.cardView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                                Log.d("ID", variables.id);

                                if (mData.get(position).getIdObjeto().equals(variables.id)){
                                        Toast.makeText(mContext, "Eleccion Correcta", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(mContext, Juego.class);
                                        mContext.startActivity(intent);

                                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                                        MediaPlayer mp = MediaPlayer.create(mContext, notification);
                                        mp.start();

                                }else{
                                        Toast.makeText(mContext, "Eleccion Incorrecta", Toast.LENGTH_SHORT).show();
                                }

                        }
                });

        }

        @Override
        public int getItemCount() {
                return mData.size();
        }

        public static class MyViewHolder extends RecyclerView.ViewHolder {


                TextView tvOpcion;
                CardView cardView;

                public MyViewHolder(View itemView) {
                        super(itemView);

                        cardView =  itemView.findViewById(R.id.cardview_id);
                        tvOpcion = itemView.findViewById(R.id.tvOpcion);



                }
        }

}