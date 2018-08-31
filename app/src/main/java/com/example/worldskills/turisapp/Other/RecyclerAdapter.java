package com.example.worldskills.turisapp.Other;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.worldskills.turisapp.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.HolderRecycler> {

    ArrayList<Lugares> listaLugares;

    public RecyclerAdapter(ArrayList<Lugares> listaLugares) {
        this.listaLugares = listaLugares;
    }

    @NonNull
    @Override
    public HolderRecycler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recycler,null,false);

        return new HolderRecycler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecycler holderRecycler, int i) {
        holderRecycler.lblNombre.setText(listaLugares.get(i).getNombre());
        holderRecycler.lblDescripcion.setText(listaLugares.get(i).getDescripcion());
        holderRecycler.lblDireccion.setText(listaLugares.get(i).getUbicacion());
        holderRecycler.imagen.setImageResource(Integer.parseInt(listaLugares.get(i).getFoto()));
    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    public class HolderRecycler extends RecyclerView.ViewHolder {
        TextView lblNombre,lblDescripcion,lblDireccion;
        ImageView imagen;
        public HolderRecycler(@NonNull View itemView) {
            super(itemView);
            lblNombre=itemView.findViewById(R.id.nombreLugar);
            lblDescripcion=itemView.findViewById(R.id.descripcionLugar);
            lblDireccion=itemView.findViewById(R.id.direccionLugar);
            imagen=itemView.findViewById(R.id.imagenLugar);
        }
    }
}
