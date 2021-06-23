package com.allam.carsapp.viewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.allam.carsapp.db.ApiClient;
import com.allam.carsapp.db.models.CarModel;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    private static final String TAG = "MainViewModel";
    public MutableLiveData<CarModel> carModelMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> errorMessage = new MutableLiveData<>();

    public void carsRequest(int page) {
        Observable<CarModel> observable =
                ApiClient.getINSTANCE()
                        .knowAboutUsObservable(page)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
        Observer<CarModel> observer = new Observer<CarModel>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(@NonNull CarModel carModel) {
                carModelMutableLiveData.postValue(carModel);
                errorMessage.postValue(null);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                errorMessage.postValue(e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "onComplete: ");
            }
        };
        observable.subscribe(observer);
    }
}
