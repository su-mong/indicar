package com.iindicar.indicar.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.View;

/**
 * Created by yeseul on 2018-05-07.
 */

public class DialogUtil {

    public static void showDialog(Context context, String title, String subTitle,
                                  double rWidth, double rHeight,
                                  View.OnClickListener positiveButtonListener){

        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        CustomAlertDialog dialog = new CustomAlertDialog(context);
        dialog.setTitle(title)
                .setSubTitle(subTitle)
                .setPositiveButtonListener(positiveButtonListener)
                .setSize((int) (size.x * rWidth), (int) (size.y * rHeight))
                .show();
    }

    public static void showDialog(Context context, int iconId, String title, String subTitle,
                                  double rWidth, double rHeight,
                                  View.OnClickListener positiveButtonListener){

        Display display = ((Activity)context).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        CustomAlertDialog dialog = new CustomAlertDialog(context);
        dialog.setImageId(iconId)
                .setTitle(title)
                .setSubTitle(subTitle)
                .setPositiveButtonListener(positiveButtonListener)
                .setSize((int) (size.x * rWidth), (int) (size.y * rHeight))
                .show();
    }

}
