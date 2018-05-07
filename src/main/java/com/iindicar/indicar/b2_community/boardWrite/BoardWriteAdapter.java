package com.iindicar.indicar.b2_community.boardWrite;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.graphics.Point;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.commit451.teleprinter.Teleprinter;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BaseRecyclerViewAdapter;
import com.iindicar.indicar.data.vo.WriteFileVO;
import com.iindicar.indicar.databinding.BoardWriteItemBinding;
import com.iindicar.indicar.utils.CustomAlertDialog;
import com.iindicar.indicar.utils.IPickPhotoHelper;
import com.iindicar.indicar.utils.PickPhotoHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeseul on 2018-03-25.
 *
 * TODO (2018.05.03) BoardFileDao 뷰모델로 옮기기
 *                   + PhotoPicker 도 같이 밖으로 빼야될듯.
 */

public class BoardWriteAdapter extends BaseRecyclerViewAdapter<WriteFileVO, BoardWriteAdapter.BoardWriteViewHolder> {

    private TextChangedListener textChangedListener;
    private AlbumButtonClickListener albumButtonClickListener;
    private CameraButtonClickListener cameraButtonClickListener;
    private ImageButtonClickListener imageButtonClickListener;

    public BoardWriteAdapter(Context context) {
        super(context);
    }

    public BoardWriteAdapter(Context context, List<WriteFileVO> list) {
        super(context);
        this.itemList = list;
    }

    public void setTextChangedListener(TextChangedListener textChangedListener) {
        this.textChangedListener = textChangedListener;
    }

    public void setAlbumButtonClickListener(AlbumButtonClickListener albumButtonClickListener) {
        this.albumButtonClickListener = albumButtonClickListener;
    }

    public void setCameraButtonClickListener(CameraButtonClickListener cameraButtonClickListener) {
        this.cameraButtonClickListener = cameraButtonClickListener;
    }

    public void setImageButtonClickListener(ImageButtonClickListener imageButtonClickListener) {
        this.imageButtonClickListener = imageButtonClickListener;
    }

    @Override
    protected void onBindView(final BoardWriteViewHolder holder, int position) {

        final int pos = position;

        holder.binding.setItem(itemList.get(position));

        // 앨범에서 사진 선택 콜백
        if(albumButtonClickListener != null) {
            holder.binding.buttonFromAlbum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    albumButtonClickListener.onClick(view, pos);
                }
            });
        }

        // 카메라 사진 찍기 콜백
        if(cameraButtonClickListener != null) {
            holder.binding.buttonFromCamera.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    cameraButtonClickListener.onClick(view, pos);
                }
            });
        }

        // 선택된 사진 클릭 콜백
        if(imageButtonClickListener != null) {
            holder.binding.imageWrite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageButtonClickListener.onClick(view, pos);
                }
            });
        }

        // 텍스트 입력 콜백
        if(textChangedListener != null) {
            holder.binding.textWrite.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    textChangedListener.onTextChanged(editable.toString(), pos);
                }
            });
        }
    }

    @Override
    public BoardWriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.board_write_item, null);
        return new BoardWriteViewHolder(view);
    }

    public class BoardWriteViewHolder extends RecyclerView.ViewHolder {

        BoardWriteItemBinding binding;

        public BoardWriteViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }

    public interface TextChangedListener {

        void onTextChanged(String text, int position);
    }

    public interface CameraButtonClickListener {

        void onClick(View view, int position);
    }

    public interface AlbumButtonClickListener {

        void onClick(View view, int position);
    }

    public interface ImageButtonClickListener {

        void onClick(View view, int position);
    }

}
