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
import com.gabrielvalles.test_bankapp_android_endcom.adapters.CuentaAdapter;
import com.gabrielvalles.test_bankapp_android_endcom.apis.BankAppApi;
import com.gabrielvalles.test_bankapp_android_endcom.models.Cuenta;
import com.gabrielvalles.test_bankapp_android_endcom.models.CuentaResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class CuentaFragment extends Fragment {

    private Retrofit retrofit;
    private static final String url  = "http://bankapp.endcom.mx/api/bankappTest/";
    private RecyclerView recyclerView;
    private CuentaAdapter cuentaAdapter;

    public static CuentaFragment newIntance(String p1, String p2){
        CuentaFragment fragment = new CuentaFragment();
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

        cuentaAdapter = new CuentaAdapter(getActivity().getApplicationContext());
        recyclerView.setAdapter(cuentaAdapter);

        recyclerView.setHasFixedSize(true);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity().getApplicationContext(), 1);
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder().baseUrl("http://bankapp.endcom.mx/api/bankappTest/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        getCuentaResponse();

        return view;
    }

    private void getCuentaResponse() {
        BankAppApi api = retrofit.create(BankAppApi.class);
        Call<CuentaResponse> cuentaResponseCall = api.getCuentaResponse();


        cuentaResponseCall.enqueue(new Callback<CuentaResponse>() {
            @Override
            public void onResponse(Call<CuentaResponse> call, Response<CuentaResponse> response) {
                try {
                    if (response.isSuccessful()){
                        CuentaResponse cuentaResponse = response.body();
                        ArrayList<Cuenta> lista = cuentaResponse.getCuenta();
                        cuentaAdapter.concatenarCuenta(lista);
                    }else{
                        Log.e("ERROR IF", " onResponse: " + response.errorBody());
                    }
                }catch (Exception e){
                    Toast.makeText(getActivity().getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.e("ERROR TRY", " onResponse: " + e.getMessage().toString());
                }
            }

            @Override
            public void onFailure(Call<CuentaResponse> call, Throwable t) {
                Log.e("ERROR FAILURE", " onFailure: " + t.getMessage());
                Toast.makeText(getActivity().getApplicationContext(), "NO SE PUDO CONECTAR", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
