package com.gabrielvalles.test_bankapp_android_endcom.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gabrielvalles.test_bankapp_android_endcom.R;
import com.gabrielvalles.test_bankapp_android_endcom.models.Saldos;

import java.util.ArrayList;

public class SaldosAdapter extends RecyclerView.Adapter<SaldosAdapter.ViewHolder> {

    private ArrayList<Saldos> data;
    private Context context;

    public SaldosAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saldos, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Saldos saldos = data.get(position);
        //String.valueOf - para que no mande error porque este valor es un INT
        holder.saldoGeneral.setText("$" + String.valueOf(saldos.getSaldoGeneral()));
        holder.totalIngresos.setText("$" + String.valueOf(saldos.getIngresos()));
        holder.totalGastos.setText("$" + String.valueOf(saldos.getGastos()));

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void concatenarSaldo(ArrayList<Saldos> listaSaldos) {
        Log.e("ERROR IF", "Entró a concatenar Saldos ");
        data.addAll(listaSaldos);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView saldoGeneral, totalIngresos, totalGastos;

        public ViewHolder(View itemView) {
            super(itemView);

            saldoGeneral = itemView.findViewById(R.id.tvSaldoGeneral);
            totalIngresos = itemView.findViewById(R.id.tvIngresos);
            totalGastos = itemView.findViewById(R.id.tvGastos);
        }
    }


}
