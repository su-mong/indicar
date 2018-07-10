package com.iindicar.indicar.b3_shopping;


import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.crashlytics.android.Crashlytics;
import com.iindicar.indicar.R;
import com.iindicar.indicar.utils.LocaleHelper;

import io.fabric.sdk.android.Fabric;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShoppingFragment extends Fragment {

    Resources resources;

    public ShoppingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(getActivity(),new Crashlytics());

        Context shoppingContext = LocaleHelper.setLocale(getActivity());
        resources = shoppingContext.getResources();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_shopping, container, false);

        ImageView ivSTitle = view.findViewById(R.id.ivS_title);
        Drawable titleDrawable = resources.getDrawable(R.drawable.seeyousoon);
        ivSTitle.setImageDrawable(titleDrawable);

        return view;
    }

}
