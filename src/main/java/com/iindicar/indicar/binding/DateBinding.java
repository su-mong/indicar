package com.iindicar.indicar.binding;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.iindicar.indicar.R;
import com.iindicar.indicar.utils.LocaleHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yeseul on 2018-04-22.
 */

public class DateBinding {

    @BindingAdapter({"date"})
    public static void convertDateToDisplayText(TextView textView, String inputDate) {

        Context dateBindingContext = LocaleHelper.setLocale(textView.getContext());
        Resources resources = dateBindingContext.getResources();

        if(inputDate == null){
            return;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat dateOnly = new SimpleDateFormat("yyyy/MM/dd");

        Date input = null;
        try {
            input = dateFormat.parse(inputDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String displayString = "";

        long diffTimeMillis = System.currentTimeMillis() - input.getTime(); // 경과된 시간 (ms)
        int diffTime = (int) (diffTimeMillis / (1000 * 60)); // 분 단위로 변경

        if (diffTime >= 0) {
            if (diffTime < 60) { // ~ 59분 전
                displayString = diffTime + resources.getString(R.string.minutes);
            } else {
                diffTime = diffTime / 60; // 시간 단위로 변경

                if (diffTime < 24) { // ~ 23시간 전
                    displayString = diffTime + resources.getString(R.string.hours);;
                } else { // 날짜 출력
                    displayString = dateOnly.format(input);
                }
            }
        }
        textView.setText(displayString);
    }
}
