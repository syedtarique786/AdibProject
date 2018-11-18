package com.an.paginglibrary.sample.datasource;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.an.paginglibrary.sample.AppController;
import com.an.paginglibrary.sample.BaseConstants;
import com.an.paginglibrary.sample.BuildConfig;
import com.an.paginglibrary.sample.model.Feed;
import com.an.paginglibrary.sample.model.Results;
import com.an.paginglibrary.sample.utils.LogUtils;
import com.an.paginglibrary.sample.utils.NetworkState;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FeedDataSource extends PageKeyedDataSource<Long, Results> implements BaseConstants {

    private static final String TAG = FeedDataSource.class.getSimpleName();

    private AppController appController;

    private MutableLiveData<NetworkState> networkState;
    private MutableLiveData<NetworkState> initialLoading;

    public FeedDataSource(AppController appController) {
        this.appController = appController;
        networkState = new MutableLiveData<>();
        initialLoading = new MutableLiveData<NetworkState>();
    }


    public MutableLiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public MutableLiveData<NetworkState> getInitialLoading() {
        return initialLoading;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Long> params, @NonNull LoadInitialCallback<Long, Results> callback) {

        initialLoading.postValue(NetworkState.LOADING);
        networkState.postValue(NetworkState.LOADING);
        LogUtils.errorLog(TAG, "Initial Loading Request" + appController.getRestApi().fetchFeed(BuildConfig.NYTIME_API_KEY).toString());

        appController.getRestApi().fetchFeed(BuildConfig.NYTIME_API_KEY)
                .enqueue(new Callback<Feed>() {
                    @Override
                    public void onResponse(Call<Feed> call, Response<Feed> response) {
                        if (response.isSuccessful()) {
                            assert response.body() != null;
                            callback.onResult(response.body().getArticles(), null, 2l);
                            initialLoading.postValue(NetworkState.LOADED);
                            networkState.postValue(NetworkState.LOADED);

                        } else {
                            initialLoading.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                            networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
                        }
                    }

                    @Override
                    public void onFailure(Call<Feed> call, Throwable t) {
                        String errorMessage = t == null ? "unknown error" : t.getMessage();
                        Retryable retryable = new Retryable() {
                            @Override
                            public void retry() {
                                loadInitial(params, callback);
                            }
                        };
                        handleError(retryable, t, errorMessage);
                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Results> callback) {
        //It's useful in cases where the data changes and we need to fetch our list starting from the middle.
        LogUtils.errorLog("Load Before ", "" + params.requestedLoadSize + " " + callback.toString());
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Long> params, @NonNull LoadCallback<Long, Results> callback) {

        LogUtils.errorLog(TAG, "Loading Rang " + params.key + " RequestedLoadSize " + params.requestedLoadSize);
        networkState.postValue(NetworkState.LOADING);

        appController.getRestApi().fetchFeed(BuildConfig.NYTIME_API_KEY).enqueue(new Callback<Feed>() {
            @Override
            public void onResponse(Call<Feed> call, Response<Feed> response) {
                if (response.isSuccessful()) {
                    long nextKey = (params.key == response.body().getNum_results()) ? null : params.key + 1;
                    callback.onResult(response.body().getArticles(), nextKey);
                    networkState.postValue(NetworkState.LOADED);

                } else
                    networkState.postValue(new NetworkState(NetworkState.Status.FAILED, response.message()));
            }

            @Override
            public void onFailure(Call<Feed> call, Throwable t) {
                String errorMessage = t == null ? "unknown error" : t.getMessage();
                Retryable retryable = new Retryable() {
                    @Override
                    public void retry() {
                        loadAfter(params, callback);
                    }
                };
                handleError(retryable, t, errorMessage);

            }
        });
    }

    private void handleError(Retryable retryable, Throwable t, String errorMessage) {
        LogUtils.errorLog("errorMessage", errorMessage);
        networkState.postValue(new NetworkState(NetworkState.Status.FAILED, t.getMessage()));
    }
}
