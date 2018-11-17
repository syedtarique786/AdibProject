package com.an.paginglibrary.sample.utils;

import android.text.TextUtils;

public class StringUtils {
    public static boolean isEmpty(String value) {
        return (TextUtils.isEmpty(value) || value.trim().isEmpty());
    }

    public static boolean isEmptyOrNull(String value) {
        return (TextUtils.isEmpty(value) || value.trim().isEmpty() || value.trim().equalsIgnoreCase("null"));
    }
}
