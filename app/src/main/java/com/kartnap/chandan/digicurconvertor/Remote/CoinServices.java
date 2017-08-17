package com.kartnap.chandan.digicurconvertor.Remote;

import com.kartnap.chandan.digicurconvertor.Model.Coin;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Chandan on 8/17/2017.
 */

public interface CoinServices {

    @GET("data/price")
    Call<Coin> calculateValue(@Query("fsym") String from
    ,@Query("tsyms") String to);



}
