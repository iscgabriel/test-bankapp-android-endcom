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
import com.gabrielvalles.test_bankapp_android_endcom.adapters.SaldosAdapter;
import com.gabrielvalles.test_bankapp_android_endcom.apis.BankAppApi;
import com.gabrielvalles.test_bankapp_android_endcom.models.Saldos;
import com.gabrielvalles.test_bankapp_android_endcom.models.SaldosResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SaldosFragment extends Fragment {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    SaldosAdapter saldosAdapter;

    public static SaldosFragment newInstance(String p1, String p2){
        SaldosFragment fragment = new SaldosFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.recyclerview, container, false);

        recyclerView = vista.findViewById(R.id.myRecyclerView);

        saldosAdapter = new SaldosAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(saldosAdapter);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://bankapp.endcom.mx/api/bankappTest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getSaldosResponse();

        return vista;

    }

    private void getSaldosResponse() {
        BankAppApi api = retrofit.create(BankAppApi.class);
        Call<SaldosResponse> saldosResponseCall = api.obtenerSaldosResponse();

        saldosResponseCall.enqueue(new Callback<SaldosResponse>() {
            @Override
            public void onResponse(Call<SaldosResponse> call, Response<SaldosResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        SaldosResponse saldosRespuesta = response.body();
                        ArrayList<Saldos> listaSaldo = saldosRespuesta.getSaldo();
                        Log.e("ERROR IF", " lista: " + response.body());
                        saldosAdapter.concatenarSaldo(listaSaldo);
                    } else {
                        Log.e("ERROR IF", " onResponse: " + response.errorBody());
                    }
                } catch (Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("ERROR CATCH", " onResponse: " + ex.getMessage());
                }
            }
            @Override
            public void onFailure(Call<SaldosResponse> call, Throwable t) {
                Log.e("ERROR FAILURE", " onFailure: " + t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), "Error de Conexion", Toast.LENGTH_SHORT).show();
            }


        });
    }
}
