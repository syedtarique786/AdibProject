package com.an.paginglibrary.sample;

import android.app.Application;
import android.content.Context;

import com.an.paginglibrary.sample.rest.RestApi;
import com.an.paginglibrary.sample.rest.RestApiFactory;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

public class AppController extends Application {

    public static Context context;
    private RestApi restApi;
    private Scheduler scheduler;

    private static AppController get(Context context) {
        return (AppController) context.getApplicationContext();
    }

    public static AppController create(Context context) {
        return AppController.get(context);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }

    public RestApi getRestApi() {
        if (restApi == null) {
            restApi = RestApiFactory.create();
        }
        return restApi;
    }

    public void setRestApi(RestApi restApi) {
        this.restApi = restApi;
    }

    public Scheduler subscribeScheduler() {
        if (scheduler == null) {
            scheduler = Schedulers.io();
        }

        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}
