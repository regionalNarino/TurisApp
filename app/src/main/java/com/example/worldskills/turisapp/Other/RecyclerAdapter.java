package com.example.worldskills.turisapp.Other;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.worldskills.turisapp.R;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.HolderRecycler>
    implements View.OnClickListener{

    ArrayList<Lugares> listaLugares;

    public RecyclerAdapter(ArrayList<Lugares> listaLugares) {
        this.listaLugares = listaLugares;
    }

    View.OnClickListener listener;

    @NonNull
    @Override
    public HolderRecycler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View vista;
        if (Variables.tipoLista.equals("list")){
            vista=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item_recycler,viewGroup,false);
        }else{
            vista=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list_grid,viewGroup,false);
        }

        vista.setOnClickListener(this);
        return new HolderRecycler(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderRecycler holderRecycler, int i) {
        holderRecycler.lblNombre.setText(listaLugares.get(i).getNombre());
        holderRecycler.lblDireccion.setText(listaLugares.get(i).getUbicacion());
        holderRecycler.imagen.setImageResource(Integer.parseInt(listaLugares.get(i).getFoto()));
        if (Variables.tipoLista.equals("list")){
            holderRecycler.lblDescripcion.setText(listaLugares.get(i).getDescripcionCorta());
        }
    }

    @Override
    public int getItemCount() {
        return listaLugares.size();
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener click){
        this.listener=click;

    }

    public class HolderRecycler extends RecyclerView.ViewHolder {
        TextView lblNombre,lblDescripcion,lblDireccion;
        ImageView imagen;
        public HolderRecycler(@NonNull View itemView) {
            super(itemView);
            if (Variables.tipoLista.equals("list")){
                lblNombre=itemView.findViewById(R.id.nombreLugar);
                lblDescripcion=itemView.findViewById(R.id.descripcionLugar);
                lblDireccion=itemView.findViewById(R.id.direccionLugar);
                imagen=itemView.findViewById(R.id.imagenLugar);
            }
            if (Variables.tipoLista.equals("grid")){
                lblNombre=itemView.findViewById(R.id.nombreGrid);
                lblDireccion=itemView.findViewById(R.id.direccionGrid);
                imagen=itemView.findViewById(R.id.imagenGrid);
            }


        }
    }
}
