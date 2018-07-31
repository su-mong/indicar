package com.iindicar.indicar.b2_community.boardList;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.iindicar.indicar.R;
import com.iindicar.indicar.data.vo.BoardDetailVO;

import java.util.List;

import static com.iindicar.indicar.Constant.BoardType.DAY_LIFE;
import static com.iindicar.indicar.Constant.BoardType.MARKET;

/**
 * Created by yeseul on 2018-04-30.
 */

public class BoardListBinding {

    @BindingAdapter(value = {"items"})
    public static void setItems(RecyclerView recyclerView,
                                List<BoardDetailVO> list){

        BoardListAdapter adapter = (BoardListAdapter) recyclerView.getAdapter();
        if(adapter != null){
            adapter.updateItems(list);
        }
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
