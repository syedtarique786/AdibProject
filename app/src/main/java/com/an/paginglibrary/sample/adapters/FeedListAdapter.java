package com.an.paginglibrary.sample.adapters;

import android.arch.paging.PagedListAdapter;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.an.paginglibrary.sample.databinding.FeedItemBinding;
import com.an.paginglibrary.sample.databinding.NetworkItemBinding;
import com.an.paginglibrary.sample.model.Results;
import com.an.paginglibrary.sample.utils.NetworkState;
import com.an.paginglibrary.sample.viewmodel.ArticleItemViewHolder;
import com.an.paginglibrary.sample.viewmodel.NetworkStateItemViewHolder;

public class FeedListAdapter extends PagedListAdapter<Results, RecyclerView.ViewHolder> {

    private static final int TYPE_PROGRESS = 0;
    private static final int TYPE_ITEM = 1;

    private Context context;
    private NetworkState networkState;

    public FeedListAdapter(Context context) {
        super(Results.DIFF_CALLBACK);
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        if (viewType == TYPE_PROGRESS) {
            NetworkItemBinding headerBinding = NetworkItemBinding.inflate(layoutInflater, parent, false);
            NetworkStateItemViewHolder viewHolder = new NetworkStateItemViewHolder(headerBinding);
            return viewHolder;

        } else {
            FeedItemBinding itemBinding = FeedItemBinding.inflate(layoutInflater, parent, false);
            ArticleItemViewHolder viewHolder = new ArticleItemViewHolder(itemBinding, context);
            return viewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ArticleItemViewHolder) {
            ((ArticleItemViewHolder) holder).bindTo(getItem(position));
        } else {
            ((NetworkStateItemViewHolder) holder).bindView(networkState);
        }
    }


    private boolean hasExtraRow() {
        if (networkState != null && networkState != NetworkState.LOADED) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (hasExtraRow() && position == getItemCount() - 1) {
            return TYPE_PROGRESS;
        } else {
            return TYPE_ITEM;
        }
    }

    public void setNetworkState(NetworkState newNetworkState) {
        NetworkState previousState = this.networkState;
        boolean previousExtraRow = hasExtraRow();
        this.networkState = newNetworkState;
        boolean newExtraRow = hasExtraRow();
        if (previousExtraRow != newExtraRow) {
            if (previousExtraRow) {
                notifyItemRemoved(getItemCount());
            } else {
                notifyItemInserted(getItemCount());
            }
        } else if (newExtraRow && previousState != newNetworkState) {
            notifyItemChanged(getItemCount() - 1);
        }
    }

}
