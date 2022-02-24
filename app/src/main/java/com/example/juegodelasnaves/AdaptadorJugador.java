package com.example.juegodelasnaves;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorJugador extends RecyclerView.Adapter<AdaptadorJugador.ViewHolder> {
ArrayList<Jugador> jugadores;
Context context;
int activity;

public AdaptadorJugador(ArrayList<Jugador> objects, Context ctx, int resource){
    this.jugadores= objects;
    this.context=ctx;
    this.activity = resource;

}
    // onCreateViewHolder sirve para asignar la vista que hemos creado para cada uno de los items de
    // nuestro ArrayList<Producto>. La vista es producto_item
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_jugador, parent, false);
        return new ViewHolder(view);
    }

    // onBindViewHolder nos permite settear el contenido de nuestro ArrayList<Producto> en los text
    // view que tiene asignados cada uno de nuestros items. Como no puedo meter valores de tipo int
    // o double en un textView lo que hago es concatenar los valores con un conjunto de carácteres
    // vacío convirtiendo así el valor de tipo double o int en String sin modificar su valor
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNombre.setText(jugadores.get(position).getNombreJugador());
        holder.textViewPuntuacion.setText("" + jugadores.get(position).getPuntuacionJugador());

    }

    @Override
    public int getItemCount() {
        return jugadores.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView textViewNombre, textViewPuntuacion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewNombre = (TextView) itemView.findViewById(R.id.tVNombre);
            textViewPuntuacion = (TextView) itemView.findViewById(R.id.tvPuntuacion);
        }
    }
    }