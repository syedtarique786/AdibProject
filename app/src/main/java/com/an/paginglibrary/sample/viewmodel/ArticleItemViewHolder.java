package com.an.paginglibrary.sample.viewmodel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.an.paginglibrary.sample.R;
import com.an.paginglibrary.sample.databinding.FeedItemBinding;
import com.an.paginglibrary.sample.model.Results;
import com.an.paginglibrary.sample.utils.AppUtils;
import com.an.paginglibrary.sample.utils.ImageLoader;
import com.an.paginglibrary.sample.utils.ImageUtils;
import com.an.paginglibrary.sample.utils.StringUtils;
import com.squareup.picasso.Picasso;

/**
 * Created by A1SMMVA6 on 29/08/18.
 */

public class ArticleItemViewHolder extends RecyclerView.ViewHolder {
    private Context context;
    private FeedItemBinding binding;

    public ArticleItemViewHolder(FeedItemBinding binding, Context context) {
        super(binding.getRoot());
        this.binding = binding;
        this.context = context;
    }

    public void bindTo(Results article) {
        binding.itemDesc.setVisibility(View.VISIBLE);

        String author = article.getByline() == null || article.getByline().isEmpty() ? context.getString(R.string.author_name) : article.getByline();
        //String titleString = String.format(context.getString(R.string.item_title), author, article.getTitle());

        binding.itemTitle.setText(article.getTitle());
        binding.itemAuthor.setText(author);
        binding.itemDescription.setText(article.getAbstractX());
        binding.itemDateTime.setText(AppUtils.getFormattedDate(article.getPublished_date()));


        new ImageUtils(context, article.getMedia().get(0).getMediametadata(), binding.itemProfileImg);
        //ImageLoader.loadImageRoundCallback(context, new ImageUtils(context, article.getMedia().get(0), binding.itemProfileImg));

        Picasso.get().load(article.getMedia().get(0).getMediametadata().get(0).getUrl()).resize(200, 200).into(binding.itemProfileImg);

        binding.itemShares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!StringUtils.isEmptyOrNull(article.getTitle()) && !StringUtils.isEmptyOrNull(article.getTitle())) {
                    AppUtils.shareTextOnly(context, article.getTitle(), article.getUrl());
                } else {
                    AppUtils.showToastS(context, "Nothing to share");

                }
            }
        });
    }
}
