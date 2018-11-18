package com.an.paginglibrary.sample.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;
import android.support.annotation.NonNull;

import com.an.paginglibrary.sample.AppController;
import com.an.paginglibrary.sample.BaseConstants;
import com.an.paginglibrary.sample.datasource.factory.FeedDataFactory;
import com.an.paginglibrary.sample.model.Results;
import com.an.paginglibrary.sample.utils.NetworkState;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class FeedViewModel extends ViewModel {

    private PagedList.BoundaryCallback boundaryCallback;
    private Executor executor;
    private LiveData<NetworkState> networkState;
    private LiveData<PagedList<Results>> articleLiveData;


    private AppController appController;
    private FeedDataFactory feedDataFactory;

    public FeedViewModel(@NonNull AppController appController, PagedList.BoundaryCallback boundaryCallback) {
        this.appController = appController;
        this.boundaryCallback = boundaryCallback;
        init();
    }

    private void init() {
        executor = Executors.newFixedThreadPool(5);

        feedDataFactory = new FeedDataFactory(appController);
        networkState = Transformations.switchMap(feedDataFactory.getMutableLiveData(),
                dataSource -> dataSource.getNetworkState());

        PagedList.Config pagedListConfig = (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        //.setPrefetchDistance((BaseConstants.DEFAULT_PER_PAGE)/3)     // A value of 0 indicates that no list items will be loaded until they are specifically
                        //.setInitialLoadSizeHint(BaseConstants.DEFAULT_PER_PAGE)    // Defines how many items to load when first load occurs.
                        .setPageSize(BaseConstants.DEFAULT_PER_PAGE)  // Defines the number of items loaded at once from the DataSource.
                        .build();
        articleLiveData = new LivePagedListBuilder(feedDataFactory, pagedListConfig)
                .setFetchExecutor(executor)
                .setBoundaryCallback(boundaryCallback)
                .build();
    }


    public LiveData<NetworkState> getNetworkState() {
        return networkState;
    }

    public LiveData<PagedList<Results>> getArticleLiveData() {
        return articleLiveData;
    }

    public FeedDataFactory getFeedDataFactory() {
        return feedDataFactory;
    }
}
