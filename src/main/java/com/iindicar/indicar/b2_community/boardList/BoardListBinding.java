package com.iindicar.indicar.b2_community.boardList;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.utils.RecyclerViewDecoration;

import java.util.List;

import static com.iindicar.indicar.Constant.BoardTab.BOARD_ALL;
import static com.iindicar.indicar.Constant.BoardTab.BOARD_POPULAR;

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

    @BindingAdapter(value = {"binding"})
    public static void setLayoutManager(RecyclerView recyclerView, final BoardListViewModel viewModel){
        int boardTab = viewModel.boardTab.get();

        if(boardTab == BOARD_POPULAR) { // 인기 게시물 뷰
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        } else if(boardTab == BOARD_ALL){ // 전체 게시물 뷰
            recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
            recyclerView.addItemDecoration(new RecyclerViewDecoration(5, 0, 0, 0));
        }
    }
}
