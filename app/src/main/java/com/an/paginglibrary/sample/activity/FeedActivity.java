package com.an.paginglibrary.sample.activity;

import android.arch.lifecycle.Observer;
import android.arch.paging.PagedList;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.an.paginglibrary.sample.AppController;
import com.an.paginglibrary.sample.R;
import com.an.paginglibrary.sample.adapters.FeedListAdapter;
import com.an.paginglibrary.sample.databinding.FeedActivityBinding;
import com.an.paginglibrary.sample.datasource.factory.FeedDataFactory;
import com.an.paginglibrary.sample.model.Results;
import com.an.paginglibrary.sample.utils.LogUtils;
import com.an.paginglibrary.sample.viewmodel.FeedViewModel;

public class FeedActivity extends AppCompatActivity {

    PagedList.BoundaryCallback boundaryCallback = new PagedList.BoundaryCallback() {
        @Override
        public void onZeroItemsLoaded() {
            super.onZeroItemsLoaded();
            LogUtils.errorLog("ZeroItemsLoaded", "True");
        }

        @Override
        public void onItemAtFrontLoaded(@NonNull Object itemAtFront) {
            super.onItemAtFrontLoaded(itemAtFront);
            LogUtils.errorLog("ItemAtFrontLoaded", " " + itemAtFront.toString());
        }

        @Override
        public void onItemAtEndLoaded(@NonNull Object itemAtEnd) {
            super.onItemAtEndLoaded(itemAtEnd);
            LogUtils.errorLog("ItemAtEndLoaded", " " + itemAtEnd.toString());
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }

        @Override
        public String toString() {
            return super.toString();
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
        }
    };
    private FeedListAdapter adapter;
    private FeedViewModel feedViewModel;
    private FeedActivityBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Using DataBinding, we setup the layout for the activity
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //Initialize the ViewModel
        feedViewModel = new FeedViewModel(AppController.create(this), boundaryCallback);

        binding.listFeed.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new FeedListAdapter(getApplicationContext());

        binding.swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        binding.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshNewsDataAsync();
            }
        });

        /*
         * Step 4: When a new page is available, we call submitList() method
         * of the PagedListAdapter class
         *
         * */
        //feedViewModel.getArticleLiveData().observe(this, new Observer<PagedList<Results>>() {
        feedViewModel.getArticleLiveData().observe(this, articles -> {
            adapter.submitList(articles);
            binding.swipeRefreshLayout.setRefreshing(false);
        });
       /* feedViewModel.getArticleLiveData().observe(this, pagedList -> {
            adapter.submitList(pagedList);
        });*/

        /*
         * Step 5: When a new page is available, we call submitList() method
         * of the PagedListAdapter class
         *
         * */
        feedViewModel.getNetworkState().observe(this, networkState -> {
            adapter.setNetworkState(networkState);
        });

        binding.listFeed.setAdapter(adapter);
    }


    /**
     * Calling api to get the data from the backend
     */
    private void refreshNewsDataAsync() {
        if (feedViewModel == null) return;
        FeedDataFactory feedDataFactory = feedViewModel.getFeedDataFactory();
        if (feedDataFactory.getMutableLiveData().getValue() != null) {
            feedDataFactory.getMutableLiveData().getValue().invalidate();
        } else {
            feedViewModel.getArticleLiveData().observe(this, pagedList -> {
                adapter.submitList(pagedList);
            });
        }
    }
}
