package com.iindicar.indicar.b2_community.boardWrite;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Point;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.iindicar.indicar.BaseViewModel;
import com.iindicar.indicar.Constant;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.dao.BaseDao;
import com.iindicar.indicar.data.dao.BoardDao;
import com.iindicar.indicar.data.dao.BoardFileDao;
import com.iindicar.indicar.data.vo.WriteBoardVO;
import com.iindicar.indicar.data.vo.WriteFileVO;
import com.iindicar.indicar.utils.CustomAlertDialog;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import io.apptik.multiview.layoutmanagers.ViewPagerLayoutManager;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

/**
 * Created by yeseul on 2018-04-28.
 */

public class BoardWriteViewModel {

    private static final int MAX_PAGE = 15;

    /** TODO 뷰로 빼기 */
    public final ObservableBoolean isIntroPage = new ObservableBoolean(true);

    public final ObservableInt currentPage = new ObservableInt(0);
    public final ObservableInt totalPage = new ObservableInt(0);

    private BoardDao boardDao;
    private BoardFileDao fileDao;

    public WriteBoardVO boardVO = new WriteBoardVO();

    private Boolean isUpdating = false;

    // key 값 기준으로 오름차순 정렬
    private TreeMap<Integer, String> doneList = new TreeMap<>();

    @NonNull
    private BoardWriteNavigator navigator;

    public BoardWriteViewModel(){
        this.boardDao = new BoardDao();
        this.fileDao = new BoardFileDao();
    }

    public void setNavigator(BoardWriteNavigator navigator) {
        this.navigator = navigator;
    }

    public void start() {
        Intent intent = navigator.getActivityIntent();
        this.isUpdating = intent.getBooleanExtra("isUpdating", false);

        if(isUpdating){
            boardVO.setBoardId(intent.getStringExtra("boardId"));
            boardVO.setBoardType(intent.getStringExtra("boardType"));
        } else {
            boardVO.setBoardType(Constant.DAY_LIFE.get());
        }
    }

    public void setBoardType(View view) {
        String tag = view.getTag().toString();
        boardVO.setBoardType(tag);
    }

    public void onNextButtonClicked(){
        navigator.onPageChangedToDetail();
        isIntroPage.set(false);
    }

    public void onCancelButtonClicked(){
        navigator.onCancelWrite();
    }

    public void onSubmitButtonClicked(){
        navigator.onSubmitWrite();
    }

    public void addNewPage(RecyclerView recyclerView){
        BoardWriteAdapter adapter = (BoardWriteAdapter) recyclerView.getAdapter();

        WriteFileVO vo = new WriteFileVO();

        if(adapter.getItemCount() == 0){ // add first page
            adapter.addItem(vo);
        } else if(adapter.getItemCount() < MAX_PAGE){ // add next to current page
            adapter.addItem(currentPage.get() + 1, vo);
        }

        getCurrentItem(recyclerView.getLayoutManager());
        getTotalPage(recyclerView);
    }

    public void onDeletePageClicked(final RecyclerView recyclerView){
        BoardWriteAdapter adapter = (BoardWriteAdapter) recyclerView.getAdapter();

        WriteFileVO vo = adapter.getItem(currentPage.get());

        if((vo.getFilePath() == null || vo.getFilePath().equals("")) // 사진 없음
            && (vo.getWriteText() == null || vo.getWriteText().equals(""))){ // 글도 없음

            // 페이지 바로 삭제
            deleteCurrentPage(recyclerView);
            return;
        }

        Display display = ((Activity)recyclerView.getContext()).getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        final CustomAlertDialog dialog = new CustomAlertDialog(recyclerView.getContext());
        dialog.setImageId(R.drawable.button_trash)
                .setTitle("현재 슬라이드를 정말로 삭제하시겠습니까?")
                .setSubTitle("Delete this slide.")
                .setPositiveButtonListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        deleteCurrentPage(recyclerView);
                        dialog.dismiss();
                    }
                })
                .setSize((int) (size.x * 0.9), (int) (size.y * 0.25))
                .show();
    }

    public void deleteCurrentPage(RecyclerView recyclerView){
        BoardWriteAdapter adapter = (BoardWriteAdapter) recyclerView.getAdapter();

        if(adapter.getItemCount() > 0) {
            adapter.removeItem(currentPage.get());
        }

        if(adapter.getItemCount() == 0) { // add page if list is empty
            addNewPage(recyclerView);
        }

        getCurrentItem(recyclerView.getLayoutManager());
        getTotalPage(recyclerView);
    }

    public void onScrollChanged(RecyclerView recyclerView, int state){
        if(state == SCROLL_STATE_IDLE) {
            getCurrentItem(recyclerView.getLayoutManager());
        }
    }

    public void getCurrentItem(RecyclerView.LayoutManager layoutManager){
        int current = ((ViewPagerLayoutManager) layoutManager).getCurrentPage();
        currentPage.set(current);
    }

    public void scrollToNextPage(RecyclerView recyclerView){
        BoardWriteAdapter adapter = (BoardWriteAdapter) recyclerView.getAdapter();

        if(currentPage.get() < adapter.getItemCount())
            recyclerView.smoothScrollToPosition(currentPage.get() + 1);
    }

    public void scrollToPrevPage(RecyclerView recyclerView){
        if(currentPage.get() > 0)
            recyclerView.smoothScrollToPosition(currentPage.get() - 1);
    }

    public void getTotalPage(RecyclerView recyclerView) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();

        if (adapter != null) {
            totalPage.set(adapter.getItemCount());
        }
    }

    public void startUpload(List<WriteFileVO> writeItems){
        uploadFiles(writeItems);
    }

    private void uploadFiles(final List<WriteFileVO> writeItems) {

        for(int i = 0 ; i < writeItems.size() ; i++){
            final int position = i;
            final WriteFileVO vo = writeItems.get(i);

            final String atchFileId = vo.getFileIndex();

            if(atchFileId == null || atchFileId.equals("")){ // index 를 가지고있지 않은 경우 insert

                RequestParams params = new RequestParams();
                try {
                    params.put("file", new File(vo.getFilePath()));
                    navigator.showTestToast("getFileIndex" + vo.getFilePath());
                    params.put("file_cn", vo.getWriteText());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }

                fileDao.insertData(params, new BaseDao.LoadDataCallBack() {
                    @Override
                    public void onDataLoaded(Object data) {
                        doneList.put(position, (String) data); // 완료 목록에 추가
                        vo.setFileIndex((String) data); // index 등록
                        if(doneList.size() == writeItems.size()){
                            uploadBoard();
                        }
                    }
                    @Override
                    public void onDataNotAvailable() {
                        navigator.showTestToast("게시물 등록 실패. 다시 시도해주세요.");
                        navigator.onFinishActivity();
                    }
                });
            } else { // index 를 가지고 있는 경우 update

                RequestParams params = new RequestParams();
                navigator.showTestToast("update file() with atchFileId: " + atchFileId);
                params.put("atch_file_id", atchFileId);
                params.put("file_cn", vo.getWriteText());

                // 사진이 수정되지 않은 경우
                if(vo.getImageUrl() != null) {
                    try {
                        params.put("file", new File(vo.getFilePath()));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }

                fileDao.updateData(params, new BaseDao.LoadDataCallBack() {
                    @Override
                    public void onDataLoaded(Object data) {
                        doneList.put(position, atchFileId); // 완료 목록에 추가
                        if(doneList.size() == writeItems.size()){
                            uploadBoard();
                        }
                    }
                    @Override
                    public void onDataNotAvailable() {
                        navigator.showTestToast("게시물 등록 실패. 다시 시도해주세요.");
                        navigator.onFinishActivity();
                    }
                });
            }
        }
    }

    public void uploadBoard(){

        getFileIndexArray();

        if(!isUpdating) {
            RequestParams params = new RequestParams();
            params.put("bbs_id", boardVO.getBoardType());
            params.put("ntcr_nm", boardVO.getUserName());
            params.put("ntcr_id", boardVO.getUserId());
            params.put("atch_file_id", boardVO.getFileIndex());

            boardDao.insertData(params, new BaseDao.LoadDataCallBack() {
                @Override
                public void onDataLoaded(Object data) {
                    navigator.onFinishActivity();
                }

                @Override
                public void onDataNotAvailable() {
                    navigator.showTestToast("업로드 실패");
                }
            });
        } else {
            RequestParams params = new RequestParams();
            params.put("bbs_id", boardVO.getBoardType());
            params.put("atch_file_id", boardVO.getFileIndex());
            params.put("ntt_id", boardVO.getBoardId());

            boardDao.updateData(params, new BaseDao.LoadDataCallBack() {
                @Override
                public void onDataLoaded(Object data) {
                    navigator.onFinishActivity();
                }

                @Override
                public void onDataNotAvailable() {
                    navigator.showTestToast("업로드 실패");
                }
            });
        }
    }

    private void getFileIndexArray() {
        String[] fileIndexArray = new String[doneList.size()];
        for(int i = 0 ; i < doneList.size() ; i++){
            fileIndexArray[i] = doneList.get(i);
        }
        boardVO.setFileIndex(fileIndexArray);
        navigator.showTestToast(fileIndexArray.toString());
    }

}
