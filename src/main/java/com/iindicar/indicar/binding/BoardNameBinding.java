package com.iindicar.indicar.binding;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.widget.TextView;

import com.iindicar.indicar.R;
import com.iindicar.indicar.utils.LocaleHelper;

/**
 * Created by yeseul on 2018-04-22.
 */

public class BoardNameBinding {

    @BindingAdapter({"boardName"})
    public static void bindBoardTypeToName(TextView textView, String boardType) {

        Context boardNameBindingContext = LocaleHelper.setLocale(textView.getContext());
        Resources resources = boardNameBindingContext.getResources();

        String boardName = "";

        if(boardType == null) return;

        if(boardType.equals("daylife")) boardName = resources.getString(R.string.boardDaylife);
        else if(boardType.equals("market")) boardName = resources.getString(R.string.boardMarket);
        else if(boardType.equals("diy")) boardName = resources.getString(R.string.boardDiy);
        else if(boardType.equals("test_main")) boardName = resources.getString(R.string.boardTest);

        textView.setText(boardName);
    }
}