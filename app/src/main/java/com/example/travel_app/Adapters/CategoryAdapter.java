package com.example.travel_app.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.travel_app.Activities.HotelesActivity;
import com.example.travel_app.Activities.RestauranteActivity;
import com.example.travel_app.Activities.ViajesActivity;
import com.example.travel_app.Domains.CategoryDomain;
import com.example.travel_app.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    ArrayList<CategoryDomain> items;


    public CategoryAdapter(ArrayList<CategoryDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        // Configurar el título de la categoría
        holder.titleTxt.setText(items.get(position).getTitle());

        // Obtener el identificador de la imagen correspondiente
        int drawableResourceId = holder.itemView.getResources().getIdentifier(
                items.get(position).getPicPath(), "drawable", holder.itemView.getContext().getOpPackageName());

        // Cargar la imagen usando Glide
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.picImg);

        // Configurar el clic en el item para redirigir a diferentes actividades
        holder.itemView.setOnClickListener(v -> {
            Intent intent = null;
            String category = items.get(position).getTitle();

            // Dependiendo de la categoría, abrir la actividad correspondiente
            switch (category) {
                case "Viajes":
                    intent = new Intent(holder.itemView.getContext(), ViajesActivity.class);
                    break;
                case "Hoteles":
                    intent = new Intent(holder.itemView.getContext(), HotelesActivity.class);
                    break;
                case "Restaurantes":
                    intent = new Intent(holder.itemView.getContext(), RestauranteActivity.class);
                    break;
                default:
                    // Si se selecciona una categoría desconocida, no hacer nada o manejar el error
                    return;
            }

            // Pasar la categoría seleccionada a la actividad, si es necesario
            intent.putExtra("category", category);

            // Iniciar la actividad seleccionada
            holder.itemView.getContext().startActivity(intent);
        });
    }


    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titleTxt;
        ImageView picImg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt=itemView.findViewById(R.id.titleTxt);
            picImg=itemView.findViewById(R.id.catImg);
        }
    }
}
