package com.gabrielvalles.test_bankapp_android_endcom.apis;

import com.gabrielvalles.test_bankapp_android_endcom.models.CuentaResponse;
import com.gabrielvalles.test_bankapp_android_endcom.models.MovimientosResponse;
import com.gabrielvalles.test_bankapp_android_endcom.models.SaldosResponse;
import com.gabrielvalles.test_bankapp_android_endcom.models.TarjetasResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BankAppApi {

    @GET("cuenta")
    Call<CuentaResponse> getCuentaResponse();

    @GET("saldos")
    Call<SaldosResponse> obtenerSaldosResponse();

    @GET("tarjetas")
    Call<TarjetasResponse> obtenerTarjetasResponse();

    @GET("movimientos")
    Call<MovimientosResponse> obtenerMovimientosResponse();


}
