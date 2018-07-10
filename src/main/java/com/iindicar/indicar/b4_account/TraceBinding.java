package com.iindicar.indicar.b4_account;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.utils.RecyclerViewDecoration;

import java.util.List;

import static com.iindicar.indicar.Constant.BoardTab.BOARD_ALL;
import static com.iindicar.indicar.Constant.BoardTab.BOARD_POPULAR;
import static com.iindicar.indicar.Constant.BoardType.DAY_LIFE;
import static com.iindicar.indicar.Constant.BoardType.MARKET;

/**
 * Created by yeseul on 2018-04-30.
 */

public class TraceBinding {

    @BindingAdapter(value = {"items"})
    public static void setItems(RecyclerView recyclerView,
                                List<BoardDetailVO> list){

        TraceAdapter adapter = (TraceAdapter) recyclerView.getAdapter();
        if(adapter != null){
            adapter.updateItems(list);
        }
    }

    @BindingAdapter(value = {"binding"})
    public static void setLayoutManager(RecyclerView recyclerView, final TraceViewModel viewModel){

            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.addItemDecoration(new RecyclerViewDecoration(5, 0, 0, 0));
    }

    @BindingAdapter(value = {"filter"})
    public static void bindFilterImage(ImageView imageView, String boardType){
        if(DAY_LIFE.equals(boardType)){
            imageView.setImageResource(R.drawable.filter_day);
        } else if(MARKET.equals(boardType)){
            imageView.setImageResource(R.drawable.filter_sec);
        } else {
            imageView.setImageResource(R.drawable.filter_diy);
        }
    }
}
