package com.iindicar.indicar.b1_tunning;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iindicar.indicar.R;
import com.iindicar.indicar.BaseRecyclerViewAdapter;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.databinding.NoticeItemBinding;

/**
 * Created by yeseul on 2018-05-01.
 */

public class NoticeAdapter extends BaseRecyclerViewAdapter<BoardDetailVO, NoticeAdapter.NoticeViewHolder> {

    public NoticeAdapter(Context context) {
        super(context);
    }

    public void setBoardFile(int position, BoardFileVO vo){
        itemList.get(position).setBoardContent(vo.getFileContent());
        itemList.get(position).setMainImageUrl(vo.getFileUrl());
        notifyItemChanged(position);
    }

    public void setUserProfile(int position, String url) {
        itemList.get(position).setUserProfileUrl(url);
        notifyItemChanged(position);
    }

    @Override
    protected void onBindView(NoticeAdapter.NoticeViewHolder holder, int position) {
        final int pos = position;
        BoardDetailVO vo = itemList.get(position);

        holder.binding.setItem(vo);
        holder.binding.imageMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(onItemClickListener != null){
                    onItemClickListener.onItemClick(view, pos);
                }
            }
        });
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_item, null);
        return new NoticeViewHolder(view);
    }

    public class NoticeViewHolder extends RecyclerView.ViewHolder {

        protected NoticeItemBinding binding;

        public NoticeViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}
