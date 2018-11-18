package com.an.paginglibrary.sample.utils;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.ViewGroup;

import com.an.paginglibrary.sample.model.Results;
import com.an.paginglibrary.sample.views.CircleImageView;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class ImageUtils {
    private final Context context;
    private final CircleImageView view;
    private final List<Results.MediaBean.MediametadataBean> mediaList;

    public ImageUtils(Context context, List<Results.MediaBean.MediametadataBean> mediaList, CircleImageView view) {
        this.context = context;
        this.mediaList = mediaList;
        this.view = view;
        rearrangeMediaData(mediaList);
    }

    private void rearrangeMediaData(List<Results.MediaBean.MediametadataBean> mediaList) {
        HashMap<Integer, Results.MediaBean.MediametadataBean> hashMap = new HashMap<>();
        Collections.sort(mediaList, new Comparator<Results.MediaBean.MediametadataBean>() {
            @Override
            public int compare(Results.MediaBean.MediametadataBean o1, Results.MediaBean.MediametadataBean o2) {
                return new Long(o1.getHeight()).compareTo(new Long(o2.getHeight()));
            }
        });

        for (Results.MediaBean.MediametadataBean bean : mediaList) {
            hashMap.put(bean.getHeight(), bean);
        }

        // now let's sort the map
       /* if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            LinkedHashMap<String, Results.MediaBean.MediametadataBean> sorted = hashMap
                    .entrySet()
                    .stream()
                    .sorted(comparingByValue())
                    .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        }*/
        int density = context.getResources().getDisplayMetrics().densityDpi;
        getHeightWidth(density, hashMap);

    }


    private void getHeightWidth(Integer densityMedium, HashMap<Integer, Results.MediaBean.MediametadataBean> map) {
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry)iterator.next();
            if((int)entry.getKey() <= densityMedium){
                int height = ((Results.MediaBean.MediametadataBean)entry.getValue()).getHeight();
                int weight = ((Results.MediaBean.MediametadataBean)entry.getValue()).getWidth();
                String url = ((Results.MediaBean.MediametadataBean)entry.getValue()).getUrl();
                ImageLoader.loadImageRound(context, url, view);
                break;
            }
        }
    }

    private int dpToPx(int dp) {
        float density = context.getResources()
                .getDisplayMetrics()
                .density;
        return Math.round((float) dp * density);
    }
}
