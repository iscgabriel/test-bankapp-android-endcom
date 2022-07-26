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
import com.gabrielvalles.test_bankapp_android_endcom.adapters.MovimientosAdapter;
import com.gabrielvalles.test_bankapp_android_endcom.apis.BankAppApi;
import com.gabrielvalles.test_bankapp_android_endcom.models.Movimientos;
import com.gabrielvalles.test_bankapp_android_endcom.models.MovimientosResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovimientosFragment extends Fragment {

    private Retrofit retrofit;
    private RecyclerView recyclerView;
    private MovimientosAdapter movimientosAdapter;

    public static MovimientosFragment newInstance(String p1, String p2) {
        MovimientosFragment fragment = new MovimientosFragment();
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

        movimientosAdapter = new MovimientosAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(movimientosAdapter);

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);


        retrofit = new Retrofit.Builder()
                .baseUrl("http://bankapp.endcom.mx/api/bankappTest/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        getMovimientosResponse();

        return view;

    }

    private void getMovimientosResponse() {
        BankAppApi service = retrofit.create(BankAppApi.class);
        Call<MovimientosResponse> movimientoRespuestaCall = service.obtenerMovimientosResponse();

        movimientoRespuestaCall.enqueue(new Callback<MovimientosResponse>() {
            @Override
            public void onResponse(Call<MovimientosResponse> call, Response<MovimientosResponse> response) {
                try {
                    if (response.isSuccessful()) {
                        MovimientosResponse movimientosResponse = response.body();
                        ArrayList<Movimientos> listaMovimientos = movimientosResponse.getMovimientos();
                        movimientosAdapter.concatenarMovimientos(listaMovimientos);
                    } else {
                        Log.e("MOVIMIENTOS", " onResponse: " + response.errorBody());
                    }
                } catch (Exception ex){
                    Toast.makeText(getActivity().getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MovimientosResponse> call, Throwable t) {
                Log.e("MOVIMIENTOS", " onFailure: " + t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), "Error de Conexion", Toast.LENGTH_SHORT).show();
            }

        });
    }

}
