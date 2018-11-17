package com.an.paginglibrary.sample.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.text.Html;
import android.text.Layout;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.an.paginglibrary.sample.R;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtils {



    public static String getDate(String dateString) {

        try{
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
            Date date = format1.parse(dateString);
            DateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
            return sdf.format(date);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return "xx";
        }
    }

    public static String getFormattedDate(String dateString) {

        try{
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format1.parse(dateString);
            DateFormat sdf = new SimpleDateFormat("MMM d, yyyy");
            return sdf.format(date);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return "xx, xx, xx";
        }
    }

    public static String getTime(String dateString) {

        try{
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
            Date date = format1.parse(dateString);
            DateFormat sdf = new SimpleDateFormat("h:mm a");
            Date netDate = (date);
            return sdf.format(netDate);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return "xx";
        }
    }


    public static long getRandomNumber() {
        long x = (long) ((Math.random() * ((100000 - 0) + 1)) + 0);
        return x;
    }


    public static void shareTextOnly(Context context, String title, String link) {
        Intent share = new Intent();
        share.setAction(Intent.ACTION_SEND);
        share.setType("text/plain");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            share.addFlags(Intent.FLAG_ACTIVITY_NEW_DOCUMENT);
        } else {
            share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        }
        share.putExtra(Intent.EXTRA_SUBJECT, title);
        //share.putExtra(Intent.EXTRA_TEXT, Html.fromHtml(link));
        share.putExtra(Intent.EXTRA_TEXT, link);
        context.startActivity(Intent.createChooser(share, context.getString(R.string.label_share_feed)));
    }

    public static void showToastS(Context context, String text) {
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
        toast.show();
    }

    public static void showToastL(Context context, String text) {
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
        toast.show();
    }


    private void shareImage(Context context, String imagePath) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("image/png");
        File imageFileToShare = new File(imagePath);
        Uri uri = Uri.fromFile(imageFileToShare);
        share.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(share, "Share Image"));
    }
}
