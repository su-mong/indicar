package com.iindicar.indicar.b4_account;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.BaseRecyclerViewAdapter;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.databinding.BoardAllItemBinding;
import com.iindicar.indicar.databinding.BoardPopularItemBinding;

import java.util.List;

/**
 * Created by yeseul on 2018-04-17.
 */

public class TraceAdapter extends BaseRecyclerViewAdapter<BoardDetailVO, RecyclerView.ViewHolder> {

    public static final int BOARD_POPULAR = 0;
    public static final int BOARD_ALL = 1;

    public TraceAdapter(Context context, List<BoardDetailVO> list) {
        super(context);
        itemList = list;
    }

    public TraceAdapter(Context context ) {
        super(context);
    }

    @Override
    public int getItemViewType(int position) {
            return BOARD_ALL;
    }

    public void setBoardFile(int position, BoardFileVO vo) {
        itemList.get(position).setBoardContent(vo.getFileContent());
        itemList.get(position).setMainImageUrl(vo.getFileUrl());
        itemList.get(position).setMainImageWidth(vo.getFileWidth());
        itemList.get(position).setMainImageHeight(vo.getFileHeight());
        notifyItemChanged(position);
    }


    public void setUserProfile(int position, String url) {
        itemList.get(position).setUserProfileUrl(url);
        notifyItemChanged(position);
    }

    @Override
    protected void onBindView(RecyclerView.ViewHolder holder, int position) {
        final int pos = position;
        BoardDetailVO vo = itemList.get(position);



            ((BoardAllViewHolder) holder).binding.setItem(vo);
            ((BoardAllViewHolder) holder).binding.textBoardContent.setText(vo.getMainImageUrl());
            ((BoardAllViewHolder) holder).binding.imageMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, pos);
                    }
                }
            });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.board_all_item, null);
            return new BoardAllViewHolder(view);
    }



    public class BoardAllViewHolder extends RecyclerView.ViewHolder {

        protected BoardAllItemBinding binding;

        public BoardAllViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
