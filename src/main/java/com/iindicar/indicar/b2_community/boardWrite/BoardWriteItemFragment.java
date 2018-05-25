package com.iindicar.indicar.b2_community.boardWrite;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.commit451.teleprinter.Teleprinter;
import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.vo.WriteFileVO;
import com.iindicar.indicar.databinding.BoardWriteItemFragmentBinding;
import com.iindicar.indicar.utils.DialogUtil;
import com.iindicar.indicar.utils.IPickPhotoHelper;
import com.iindicar.indicar.utils.PickPhotoHelper;
import com.iindicar.indicar.utils.RealPathUtil;

import java.util.ArrayList;
import java.util.List;

import io.apptik.multiview.layoutmanagers.ViewPagerLayoutManager;

import static com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditViewModel.MAX_PAGE;

/**
 * Created by yeseul on 2018-05-06.
 */

public class BoardWriteItemFragment extends BaseFragment<BoardWriteItemFragmentBinding> implements BoardWriteItemPageNavigator {

    private BoardWriteEditViewModel viewModel;
    private BoardWriteAdapter adapter;
    private ViewPagerLayoutManager layoutManager;
    private PickPhotoHelper pickPhotoHelper;
    private Teleprinter keyboard;

    @Override
    protected int getLayoutId() {
        return R.layout.board_write_item_fragment;
    }

    public void setViewModel(BoardWriteEditViewModel viewModel) {
        this.viewModel = viewModel;
        this.viewModel.setPageNavigator(this);
    }

    public void setPickPhotoHelper(PickPhotoHelper pickPhotoHelper) {
        this.pickPhotoHelper = pickPhotoHelper;
    }

    public void setKeyboard(Teleprinter keyboard) {
        this.keyboard = keyboard;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        binding.setViewModel(viewModel);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        // set up adapter
        adapter = new BoardWriteAdapter(context, viewModel, viewModel.getBoardItems());

        // 앨범 버튼 클릭 콜백
        adapter.setAlbumButtonClickListener(new BoardWriteAdapter.AlbumButtonClickListener() {
            @Override
            public void onClick(View view, int position) {
                pickFromAlbum(position);
            }
        });
        // 카메라 버튼 클릭 콜백
        adapter.setCameraButtonClickListener(new BoardWriteAdapter.CameraButtonClickListener() {
            @Override
            public void onClick(View view, int position) {
                pickFromCamera(position);
            }
        });
        // 이미지 클릭 콜백
        adapter.setImageButtonClickListener(new BoardWriteAdapter.ImageButtonClickListener() {
            @Override
            public void onClick(View view, int position) {
                showPhotoDeleteDialog(position);
            }
        });
        binding.pageContainer.setAdapter(adapter);

        // add first default page
        if (adapter.getItemCount() == 0) {
            addPage(0);
        }

        // set up layout manager
        layoutManager = new ViewPagerLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);
        binding.pageContainer.setLayoutManager(layoutManager);

        // 화면 스크롤로 페이지를 넘길 때 currentPage 바꾼다
        binding.pageContainer.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    viewModel.currentPage.set(layoutManager.findFirstVisibleItemPosition());
                }
            }
        });

        // 페이지 삭제시 currentPage 바꾼다
        binding.pageContainer.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
                // Do Nothing
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {

                viewModel.currentPage.set(layoutManager.findFirstVisibleItemPosition());
            }
        });
    }

    @Override
    public void pageChangeToPosition(int position) {
        binding.pageContainer.smoothScrollToPosition(position);
        viewModel.currentPage.set(position);
    }

    @Override
    public void addPage(int position) {
        adapter.addItem(position, new WriteFileVO());
        viewModel.totalPage.set(adapter.getItemCount());
        pageChangeToPosition(position);
    }

    @Override
    public void removePage(final int position) {
        WriteFileVO vo = adapter.getItem(position);

        if((vo.getFilePath() == null || vo.getFilePath().equals("")) // 사진 없음
                && (vo.getWriteText() == null || vo.getWriteText().equals(""))){ // 글도 없음

            // 페이지 바로 삭제
            adapter.removeItem(position);
            viewModel.totalPage.set(adapter.getItemCount());
            return;
        }

        DialogUtil.showDialog(context,
                "현재 슬라이드를 정말로 삭제하시겠습니까?",
                "Delete this slide.",
                0.9, 0.25,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        adapter.removeItem(position);
                        adapter.notifyDataSetChanged();
                        viewModel.totalPage.set(adapter.getItemCount());
                    }
                });
    }

    // 카메라로 사진을 찍는다.
    private void pickFromCamera(final int position){

        pickPhotoHelper.pickFromCamera(new IPickPhotoHelper.loadPhotoCallBack<Uri>() {
            @Override
            public void onPhotoLoaded(Uri photoUri,String imagePath) {
                Log.d("ddf uri_acti",photoUri.toString());
                adapter.getItemList().get(viewModel.currentPage.get()).setImageUrl(photoUri);
                adapter.getItemList().get(viewModel.currentPage.get()).setFilePath(imagePath);
                adapter.notifyItemChanged(viewModel.currentPage.get());
            }

            @Override
            public void onPhotoLoaded(Uri photoUri) {

            }

            @Override
            public void onPhotoNotAvailable() {
                Log.e("BoardWriteAdapter", "processPickPhoto() PICK_FROM_CAMERA " + R.string.photo_from_storage_not_available);
            }
        });
    }

    // 앨범에서 사진을 가져온다.
    private void pickFromAlbum(final int position){

        pickPhotoHelper.pickFromAlbum(MAX_PAGE - viewModel.totalPage.get() + 1, new IPickPhotoHelper.loadPhotoListCallBack<Uri>() {
            @Override
            public void onPhotoListLoaded(List<Uri> photoUriList) {

                final int IMAGE_COUNT = photoUriList.size(); // 리스트 개수
                viewModel.totalPage.set(viewModel.totalPage.get() + IMAGE_COUNT - 1);

                ArrayList<WriteFileVO> listFromAlbum = new ArrayList<>();

                for(int i = 0 ; i < IMAGE_COUNT ; i++){
                    WriteFileVO vo = new WriteFileVO();
                    vo.setImageUrl(photoUriList.get(i));
                    vo.setFilePath(RealPathUtil.getRealPath(context,photoUriList.get(i)));
                    listFromAlbum.add(vo);
                }

                // 첫번째 사진은 리스트에서 빼고 현재 페이지에 붙인다.
                WriteFileVO firstPhoto = listFromAlbum.remove(0);
                adapter.getItemList().get(viewModel.currentPage.get()).setImageUrl(firstPhoto.getImageUrl());
                adapter.getItemList().get(viewModel.currentPage.get()).setFilePath(firstPhoto.getFilePath());
                adapter.notifyItemChanged(viewModel.currentPage.get());

                // 나머지 사진은 현재 페이지 뒤에 추가
                adapter.addItems(position + 1, listFromAlbum);
            }

            @Override
            public void onPhotoNotAvailable() {
                Log.e("BoardWriteAdapter", "processPickPhoto() PICK_FROM_ALBUM " + R.string.photo_from_storage_not_available);
            }
        });
    }

    // 파일의 절대경로를 얻는다.
    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = getActivity().managedQuery(contentUri, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

        cursor.moveToFirst();

        return cursor.getString(column_index);
    }

    // 사진 삭제 경고창을 띄운다.
    public void showPhotoDeleteDialog(final int position){

        DialogUtil.showDialog(context,
                "사진을 정말로 삭제하시겠습니까?",
                "delete this photo.",
                0.9, 0.25,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        adapter.getItemList().get(position).setImageUrl(null);
                        adapter.getItemList().get(position).setFilePath("");
                        adapter.notifyItemChanged(position);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}