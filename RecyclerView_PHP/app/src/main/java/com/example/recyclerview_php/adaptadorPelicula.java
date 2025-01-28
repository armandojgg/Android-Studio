package com.example.recyclerview_php;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adaptadorPelicula extends RecyclerView.Adapter<adaptadorPelicula.PeliculaViewHolder> {
    private ArrayList<Pelicula> peliculas;

    public adaptadorPelicula(ArrayList<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }

    @Override
    public PeliculaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.objeto, parent, false);
        return new PeliculaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PeliculaViewHolder holder, int position) {
        Pelicula pelicula = peliculas.get(position);
        holder.textoSuperior.setText(pelicula.getNombre());
        holder.textoIntermedio.setText(pelicula.getDirector());
        holder.textoInferior.setText(pelicula.getDescripcion());

        android.view.animation.Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.primeraanimacion);
        holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return peliculas.size();
    }

    public static class PeliculaViewHolder extends RecyclerView.ViewHolder {
        TextView textoSuperior;
        TextView textoIntermedio;
        TextView textoInferior;

        public PeliculaViewHolder(View itemView) {
            super(itemView);
            textoSuperior = itemView.findViewById(R.id.textoSuperior);
            textoIntermedio = itemView.findViewById(R.id.textoIntermedio);
            textoInferior = itemView.findViewById(R.id.textoInferior);
        }
    }
}
