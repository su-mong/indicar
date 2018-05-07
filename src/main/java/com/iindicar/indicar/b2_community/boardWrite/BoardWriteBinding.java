package com.iindicar.indicar.b2_community.boardWrite;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.iindicar.indicar.data.vo.WriteFileVO;
import com.iindicar.indicar.utils.ImageUtil;

/**
 * Created by yeseul on 2018-04-29.
 */

public class BoardWriteBinding {

    @BindingAdapter(value = {"pageChange"})
    public static void setPageChangeListener(final RecyclerView recyclerView,
                                             final BoardWriteViewModel viewModel){

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                viewModel.onScrollChanged(recyclerView, newState);
            }
        });

        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                viewModel.getCurrentItem(recyclerView.getLayoutManager());
                viewModel.getTotalPage(recyclerView);
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                viewModel.getCurrentItem(recyclerView.getLayoutManager());
                viewModel.getTotalPage(recyclerView);
            }
        });
    }

    @BindingAdapter(value = {"imageVisible"})
    public static void setVisibility(ImageView imageView,
                                     WriteFileVO vo){

        if(vo.getImageUrl() != null) { // 앨범에서 받아온 사진이 있는 경우
            imageView.setVisibility(View.VISIBLE);
            ImageUtil.loadImage(imageView, vo.getImageUrl()); // 앨범 사진을 띄운다
        } else { // 앨범에서 받아온 사진이 없는 경우
            if (vo.getFilePath() != null && !vo.getFilePath().equals("")) { // 서버에서 받아온 사진이 있는 경우
                imageView.setVisibility(View.VISIBLE);
                ImageUtil.loadImage(imageView, vo.getFilePath()); // 서버 사진 띄우기
            } else {
                imageView.setVisibility(View.GONE);
            }
        }
    }

    @BindingAdapter(value = {"pickerVisible"})
    public static void setVisibility(LinearLayout layout,
                                     WriteFileVO vo){

        if( (vo.getFilePath() == null || vo.getFilePath().equals(""))
                && (vo.getImageUrl() == null) ) {
            layout.setVisibility(View.VISIBLE);
        } else {
            layout.setVisibility(View.GONE);
        }
    }
}
