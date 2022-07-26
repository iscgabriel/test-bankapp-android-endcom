package com.gabrielvalles.test_bankapp_android_endcom.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabrielvalles.test_bankapp_android_endcom.R;
import com.gabrielvalles.test_bankapp_android_endcom.models.Tarjetas;

import java.util.ArrayList;

public class TarjetaAdapter extends RecyclerView.Adapter<TarjetaAdapter.ViewHolder>{

    private ArrayList<Tarjetas> data;
    private Context context;

    public TarjetaAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tarjetas, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String statusOff = "desactivada";

        Tarjetas t = data.get(position);
        holder.statusTarjeta.setText(t.getEstado());
        holder.numeroTarjeta.setText(t.getTarjeta());
        holder.nombreTarjeta.setText(t.getNombre());
        holder.tipoTarjeta.setText(t.getTipo());
        holder.saldoTarjeta.setText("$" + String.valueOf(t.getSaldo()));

        if(t.getEstado().equals(statusOff)){
            holder.statusTarjeta.setTextColor(Color.LTGRAY);
            holder.saldoTarjeta.setTextColor(Color.LTGRAY);
            holder.imgTarjeta.setImageResource(R.drawable.card_not_active);
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void concatenarTarjetas(ArrayList<Tarjetas> listaTarjetas) {
        data.addAll(listaTarjetas);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgTarjeta;
        private TextView statusTarjeta, numeroTarjeta, nombreTarjeta, tipoTarjeta, saldoTarjeta;

        public ViewHolder(View itemView) {
            super(itemView);

            imgTarjeta      = itemView.findViewById(R.id.imgTarjeta);
            statusTarjeta   = itemView.findViewById(R.id.tvStatusTarjeta);
            numeroTarjeta   = itemView.findViewById(R.id.tvNumeroTarjeta);
            nombreTarjeta   = itemView.findViewById(R.id.tvNombreTarjeta);
            tipoTarjeta     = itemView.findViewById(R.id.tvTipoTarjeta);
            saldoTarjeta    = itemView.findViewById(R.id.tvSaldoTarjeta);
        }
    }


}
