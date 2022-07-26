package com.gabrielvalles.test_bankapp_android_endcom.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gabrielvalles.test_bankapp_android_endcom.R;
import com.gabrielvalles.test_bankapp_android_endcom.adapters.TarjetaAdapter;
import com.gabrielvalles.test_bankapp_android_endcom.apis.BankAppApi;
import com.gabrielvalles.test_bankapp_android_endcom.models.Tarjetas;
import com.gabrielvalles.test_bankapp_android_endcom.models.TarjetasResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TarjetaFragment extends Fragment {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private TarjetaAdapter tarjetaAdapter;

    public static TarjetaFragment newInstance(String param1, String param2) {
        TarjetaFragment fragment = new TarjetaFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recyclerview, container, false);

        recyclerView = view.findViewById(R.id.myRecyclerView);

        tarjetaAdapter = new TarjetaAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(tarjetaAdapter);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://bankapp.endcom.mx/api/bankappTest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getTarjetaResponse();

        return view;

    }

    private void getTarjetaResponse() {
        BankAppApi service = retrofit.create(BankAppApi.class);
        Call<TarjetasResponse> tarjetaRespuestaCall = service.obtenerTarjetasResponse();

        tarjetaRespuestaCall.enqueue(new Callback<TarjetasResponse>() {
            @Override
            public void onResponse(Call<TarjetasResponse> call, Response<TarjetasResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        TarjetasResponse tarjetaRespuesta = response.body();
                        ArrayList<Tarjetas> listaTarjetas = tarjetaRespuesta.getTarjetas();
                        tarjetaAdapter.concatenarTarjetas(listaTarjetas);
                    } else {
                        Log.e("TARJETAS", " onResponse: " + response.errorBody());
                    }
                } catch (Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TarjetasResponse> call, Throwable t) {
                Log.e("TARJETAS", " onFailure: " + t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), "Error de Conexion", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
