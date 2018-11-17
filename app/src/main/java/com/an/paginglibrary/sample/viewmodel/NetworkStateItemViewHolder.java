package com.an.paginglibrary.sample.viewmodel;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.an.paginglibrary.sample.databinding.NetworkItemBinding;
import com.an.paginglibrary.sample.utils.NetworkState;

/**
 * Created by A1SMMVA6 on 29/08/18.
 */

public class NetworkStateItemViewHolder extends RecyclerView.ViewHolder {

    private NetworkItemBinding binding;

    public NetworkStateItemViewHolder(NetworkItemBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bindView(NetworkState networkState) {
        if (networkState != null && networkState.getStatus() == NetworkState.Status.RUNNING) {
            binding.progressBar.setVisibility(View.VISIBLE);
        } else {
            binding.progressBar.setVisibility(View.GONE);
        }

        if (networkState != null && networkState.getStatus() == NetworkState.Status.FAILED) {
            binding.errorMsg.setVisibility(View.VISIBLE);
            binding.errorMsg.setText(networkState.getMsg());
        } else {
            binding.errorMsg.setVisibility(View.GONE);
        }
    }
}
