package com.gabrielvalles.test_bankapp_android_endcom.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabrielvalles.test_bankapp_android_endcom.R;
import com.gabrielvalles.test_bankapp_android_endcom.models.Movimientos;

import java.util.ArrayList;

public class MovimientosAdapter extends RecyclerView.Adapter<MovimientosAdapter.ViewHolder>{

    private ArrayList<Movimientos> data;
    private Context context;

    public MovimientosAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }



    public void concatenarMovimientos(ArrayList<Movimientos> listaMovimientos) {
        data.addAll(listaMovimientos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovimientosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movimientos, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MovimientosAdapter.ViewHolder holder, int position) {
        String tipoAbono = "abono";
        String tipoCargo = "cargo";

        Movimientos m = data.get(position);
        holder.tipoMov.setText(m.getTipo());
        holder.fechaMov.setText(m.getFecha());

        if(m.getTipo().equals(tipoAbono)){
            holder.montoMov.setTextColor(Color.parseColor("#3ecca9"));
            holder.montoMov.setText("+$" + m.getMonto());
        }else if(m.getTipo().equals(tipoCargo)){
            holder.montoMov.setTextColor(Color.RED);
            holder.montoMov.setText("-$" + m.getMonto());
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tipoMov, fechaMov, montoMov;

        public ViewHolder(View itemView) {
            super(itemView);

            tipoMov = itemView.findViewById(R.id.tvConcepto);
            fechaMov = itemView.findViewById(R.id.tvFechaMovimiento);
            montoMov = itemView.findViewById(R.id.tvSaldoMovimiento);

        }
    }


}
