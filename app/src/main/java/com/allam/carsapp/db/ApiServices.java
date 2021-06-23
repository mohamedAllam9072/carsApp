package com.allam.carsapp.db;

import com.allam.carsapp.db.models.CarModel;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiServices {
    //   cars?page={page}
    @GET("cars")
    Observable<CarModel> carsResponse(@Query("page") int page);
}