package com.gabrielvalles.test_bankapp_android_endcom.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gabrielvalles.test_bankapp_android_endcom.R;
import com.gabrielvalles.test_bankapp_android_endcom.models.Cuenta;

import java.util.ArrayList;

public class CuentaAdapter extends RecyclerView.Adapter<CuentaAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Cuenta> data;

    public CuentaAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cuenta, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cuenta cuenta = data.get(position);
        holder.cuentaNombreCompleto.setText(cuenta.getNombreCompleto());
        holder.cuentaUltimaSesion.setText("Último inicio " + cuenta.getUltimaSesion());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void concatenarCuenta(ArrayList<Cuenta> listaCuenta) {
        data.addAll(listaCuenta);
        notifyDataSetChanged();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView cuentaNombreCompleto, cuentaUltimaSesion;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            cuentaNombreCompleto = itemView.findViewById(R.id.tvNombreCompleto);
            cuentaUltimaSesion = itemView.findViewById(R.id.tvUltimaSesion);
        }
    }


}
