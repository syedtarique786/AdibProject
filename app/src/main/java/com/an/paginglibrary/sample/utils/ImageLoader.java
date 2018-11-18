package com.an.paginglibrary.sample.utils;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.an.paginglibrary.sample.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.concurrent.Executors;

public class ImageLoader {

    private static Picasso instance;

    /**
     * create shared instance for picasso.
     */
    public static Picasso getSharedInstance(Context context) {
        if (instance == null) {
            instance = new Picasso.Builder(context)
                    .executor(Executors.newSingleThreadExecutor())
                    .indicatorsEnabled(false).build();
        }
        return instance;
    }

    /**
     * load simple image to ImageView.
     * @param context   current class activity.
     * @param imageUrl  url of the image.
     * @param imageView view where image will load
     */
    public static void loadImage(Context context, String imageUrl, ImageView imageView, int width, int height) {
        getSharedInstance(context)
                .load(imageUrl)
                .resize(width, height)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    /** load image normally */
    public static void loadImage(Context context, String imageUrl, ImageView imageView) {
        getSharedInstance(context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    /** load image with round corner */
    public static void loadImageRound(Context context, String imageUrl, ImageView imageView) {
        getSharedInstance(context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .transform(new CircleTransform(context.getResources().getDimensionPixelSize(R.dimen.detail_page_logo_size)))
                .into(imageView);
    }
    /** load image with round top corner */
    public static void loadImageRoundTop(Context context, String imageUrl, ImageView imageView) {
        getSharedInstance(context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .transform(new CircleTransform(context.getResources().getDimensionPixelSize(R.dimen.detail_page_logo_size)))
                .into(imageView);
    }

    /**
     * load simple image to ImageView.
     * @param context     current class activity.
     * @param imageUrl    url of the image.
     * @param imageView   view where image will load
     * @param progressBar showing loader on ImageView.
     */
    public static void loadImageCallback(Context context, String imageUrl, ImageView imageView, final ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        getSharedInstance(context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }
    /** load image with round corner with callback*/
    public static void loadImageRoundCallback(Context context, String imageUrl, ImageView imageView, final ProgressBar progressBar) {
        progressBar.setVisibility(View.VISIBLE);
        getSharedInstance(context)
                .load(imageUrl)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .transform(new CircleTransform(context.getResources().getDimensionPixelSize(R.dimen.detail_page_logo_size)))
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError(Exception e) {
                        progressBar.setVisibility(View.GONE);
                    }

                });
    }

}
