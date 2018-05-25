package com.iindicar.indicar.b2_community.boardList;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class BoardListAdapter extends BaseRecyclerViewAdapter<BoardDetailVO, RecyclerView.ViewHolder> {

    public static final int BOARD_POPULAR = 0;
    public static final int BOARD_ALL = 1;
    private final int BOARD_TYPE;

    public BoardListAdapter(Context context, List<BoardDetailVO> list, int boardType) {
        super(context);
        itemList = list;
        this.BOARD_TYPE = boardType;
    }

    public BoardListAdapter(Context context, int boardType) {
        super(context);
        this.BOARD_TYPE = boardType;
    }

    @Override
    public int getItemViewType(int position) {
        if (BOARD_TYPE == BOARD_POPULAR) {
            return BOARD_POPULAR;
        } else {
            return BOARD_ALL;
        }
    }

    public void setBoardFile(int position, BoardFileVO vo) {
        Log.d("ddf BoardListAdapter", "position"+position);
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

        if (holder instanceof BoardPopularViewHolder) {
            Log.d("ddff", "onBindView");
            ((BoardPopularViewHolder) holder).binding.setItem(vo);
            ((BoardPopularViewHolder) holder).binding.imageMain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(view, pos);
                    }
                }
            });

            if ((position % 2) == 0) { // 짝수
                ((BoardPopularViewHolder) holder).binding.container.setGravity(Gravity.RIGHT);
            } else {
                ((BoardPopularViewHolder) holder).binding.container.setGravity(Gravity.LEFT);
            }

        } else if (holder instanceof BoardAllViewHolder) {

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
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BOARD_POPULAR) {
            View view = LayoutInflater.from(context).inflate(R.layout.board_popular_item, null);
            Log.d("ddff", "view생성");
            return new BoardPopularViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.board_all_item, null);
            return new BoardAllViewHolder(view);
        }
    }

    public class BoardPopularViewHolder extends RecyclerView.ViewHolder {

        protected BoardPopularItemBinding binding;

        public BoardPopularViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public class BoardAllViewHolder extends RecyclerView.ViewHolder {

        protected BoardAllItemBinding binding;

        public BoardAllViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
