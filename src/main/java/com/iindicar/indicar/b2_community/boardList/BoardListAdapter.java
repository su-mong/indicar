package com.iindicar.indicar.b2_community.boardList;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iindicar.indicar.BaseRecyclerViewAdapter;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditActivity;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.databinding.BoardAllItemBinding;
import com.iindicar.indicar.databinding.BoardPopularItemBinding;
import com.iindicar.indicar.utils.LocaleHelper;

import java.util.List;

/**
 * Created by yeseul on 2018-04-17.
 */

public class BoardListAdapter extends BaseRecyclerViewAdapter<BoardDetailVO, RecyclerView.ViewHolder> {

    public static final int BOARD_POPULAR = 0;
    public static final int BOARD_ALL = 1;
    private final int BOARD_TYPE;

    Resources resources;

    public BoardListAdapter(Context context, List<BoardDetailVO> list, int boardType) {
        super(context);
        itemList = list;
        this.BOARD_TYPE = boardType;

        Context context1 = LocaleHelper.setLocale(context);
        resources = context1.getResources();
    }

    public BoardListAdapter(Context context, int boardType) {
        super(context);
        this.BOARD_TYPE = boardType;

        Context context1 = LocaleHelper.setLocale(context);
        resources = context1.getResources();
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
        itemList.get(position).setBoardContent(vo.getFileContent());
        String temp = vo.getFileUrl();
       temp=temp.replace("8080", "9000");
        itemList.get(position).setMainImageUrl("http://" + temp);
        itemList.get(position).setMainImageWidth(vo.getFileWidth());
        itemList.get(position).setMainImageHeight(vo.getFileHeight());
        notifyItemChanged(position);
    }


    public void setUserProfile(int position, String url) {
        String temp = url;
        temp.replace("8080", "9000");
        itemList.get(position).setUserProfileUrl(temp);
        notifyItemChanged(position);
    }

    @Override
    protected void onBindView(final RecyclerView.ViewHolder holder, int position) {
        final int pos = position;
        BoardDetailVO vo = itemList.get(position);

        if (holder instanceof BoardPopularViewHolder) {

            ((BoardPopularViewHolder) holder).binding.setItem(vo);
            ((BoardPopularViewHolder) holder).binding.imageMain.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        ((BoardPopularViewHolder) holder).binding.imageMain.setColorFilter(Color.argb(50, 155, 150, 0));

                    }
                    if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                        ((BoardPopularViewHolder) holder).binding.imageMain.setColorFilter(null);
                        onItemClickListener.onItemClick(view,pos);
                    }
                    return true;
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

        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == BOARD_POPULAR) {
            View view = LayoutInflater.from(context).inflate(R.layout.board_popular_item, null);
            return new BoardPopularViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.board_all_item, null);
            TextView tvBA_Like = view.findViewById(R.id.tvBA_Like);
            tvBA_Like.setText(resources.getString(R.string.strBoardAlllike));
            TextView tvBA_Comment = view.findViewById(R.id.tvBA_Comment);
            tvBA_Comment.setText(resources.getString(R.string.strBoardAllcomment));
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
