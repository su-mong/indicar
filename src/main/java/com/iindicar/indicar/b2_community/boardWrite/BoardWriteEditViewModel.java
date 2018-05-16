package com.iindicar.indicar.b2_community.boardWrite;

import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.iindicar.indicar.Constant;
import com.iindicar.indicar.data.dao.BaseDao;
import com.iindicar.indicar.data.dao.BoardDao;
import com.iindicar.indicar.data.dao.BoardFileDao;
import com.iindicar.indicar.data.vo.WriteBoardVO;
import com.iindicar.indicar.data.vo.WriteFileVO;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeseul on 2018-05-06.
 */

public class BoardWriteEditViewModel {

    public static final int MAX_PAGE = 15;

    public final ObservableField<String> boardType = new ObservableField<>();
    public final ObservableInt currentPage = new ObservableInt();
    public final ObservableInt totalPage = new ObservableInt();

    private BoardDao boardDao;
    private BoardFileDao fileDao;

    private BoardWriteEditNavigator navigator;
    private BoardWriteItemPageNavigator pageNavigator;

    private WriteBoardVO boardVO;
    private List<WriteFileVO> boardItems = new ArrayList<>();

    public boolean isNewBoard;

    public List<Boolean> DONE_FILE_UPLOAD_COUNT = new ArrayList<>();

    public BoardWriteEditViewModel(){
        boardDao = new BoardDao();
        fileDao = new BoardFileDao();
    }

    // this will be called only writing a New Board
    public void start(String userId, String userName){
        isNewBoard = true;

        this.boardVO = new WriteBoardVO();
        this.boardVO.setUserId(userId);
        this.boardVO.setUserName(userName);

        boardType.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable observable, int i) {
                boardVO.setBoardType(boardType.get());
            }
        });
    }

    // this will be called only Editing Exist Board
    public void start(WriteBoardVO boardVO, List<WriteFileVO> boardItems) {
        isNewBoard = false;

        this.boardVO = boardVO;
        this.boardItems = boardItems;
        totalPage.set(boardItems.size());
    }

    public void setNavigator(BoardWriteEditNavigator navigator) {
        this.navigator = navigator;
    }

    public void setPageNavigator(BoardWriteItemPageNavigator pageNavigator) {
        this.pageNavigator = pageNavigator;
    }

    public List<WriteFileVO> getBoardItems() {
        return boardItems;
    }

    // board type 라디오 버튼 커스텀
    public void setBoardTypeFiltering(View view){
        String tag = view.getTag().toString();

        if(Constant.BoardType.DAY_LIFE.equals(tag)){
            boardType.set(Constant.BoardType.DAY_LIFE);

        } else if(Constant.BoardType.MARKET.equals(tag)){
            boardType.set(Constant.BoardType.MARKET);

        } else if(Constant.BoardType.DIY.equals(tag)) {
            boardType.set(Constant.BoardType.DIY);
        }
    }

    public void onPrevPageButtonClicked(){
        pageNavigator.pageChangeToPosition(currentPage.get() - 1);
    }

    public void onNextPageButtonClicked(){
        pageNavigator.pageChangeToPosition(currentPage.get() + 1);
    }

    public void onSubmitClicked(){

        // 사진을 등록하지 않은 페이지 체크
        for (int i = 0 ; i < boardItems.size() ; i++){
            WriteFileVO vo = boardItems.get(i);

            if(vo.getFilePath() == null || vo.getFilePath().equals("")){
                navigator.showSnackBar("사진을 등록해주세요.");
                pageNavigator.pageChangeToPosition(i);
                return;
            }
        }

        // 파일을 서버에 업로드한다.
        for(WriteFileVO vo : boardItems){
            // index (atch_file_id) 없는 경우 - new file
            if(vo.getFileIndex() == null || vo.getFileIndex().equals("")) {
                insertNewFile(vo);
            } else {
                updateExistingFile(vo.getImageUrl() != null, vo);
            }
        }
    }

    public void pageAddClick(){
        if(totalPage.get() == MAX_PAGE){
            navigator.showSnackBar("페이지를 더이상 추가할 수 없습니다.");
            return;
        }
        pageNavigator.addPage(currentPage.get() + 1);
    }

    public void pageRemoveClick(){
        if(totalPage.get() == 1){
            navigator.showSnackBar("페이지를 더이상 삭제할 수 없습니다.");
            return;
        }
        pageNavigator.removePage(currentPage.get());
    }

    private void insertNewFile(final WriteFileVO vo){

        RequestParams params = new RequestParams();
        try {
            params.put("file", new File(vo.getFilePath()));
            params.put("file_cn1", vo.getWriteText());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fileDao.insertData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {

                vo.setFileIndex((String) data); // atch_file_id 추가

                DONE_FILE_UPLOAD_COUNT.add(true); // 완료 목록 추가
                if(DONE_FILE_UPLOAD_COUNT.size() == boardItems.size()){
                    startUploadBoard();
                }
            }

            @Override
            public void onDataNotAvailable() {
                navigator.showSnackBar("게시물 등록에 실패하였습니다. 다시 시도해주세요.");
                navigator.onActivityFinish();
            }
        });
    }

    private void updateExistingFile(boolean isFileUpdated, final WriteFileVO vo){

        RequestParams params = new RequestParams();
        params.put("atch_file_id", vo.getFileIndex());
        params.put("file_cn", vo.getWriteText());

        if(isFileUpdated){ // 사진이 수정 된 경우
            try {
                params.put("file", new File(vo.getFilePath()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        fileDao.updateData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                DONE_FILE_UPLOAD_COUNT.add(true); // 완료 목록에 추가
                if(DONE_FILE_UPLOAD_COUNT.size() == boardItems.size()){
                    startUploadBoard();
                }
            }
            @Override
            public void onDataNotAvailable() {
                navigator.showSnackBar("게시물 등록에 실패하였습니다. 다시 시도해주세요.");
                navigator.onActivityFinish();
            }
        });
    }

    private void startUploadBoard(){

        getFileIndexArray();

        if(isNewBoard){ // 게시물 등록
            insertNewBoard();
        } else { // 게시물 수정
            updateExistingBoard();
        }
    }

    // 해당 게시물을 서버에 업로드한다.
    private void insertNewBoard(){

        RequestParams params = new RequestParams();
        params.put("bbs_id", boardVO.getBoardType());
        params.put("ntcr_nm", boardVO.getUserName());
        params.put("ntcr_id", boardVO.getUserId());
        params.put("atch_file_id", boardVO.getFileIndex());

        boardDao.insertData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                navigator.onBoardUploaded();
            }

            @Override
            public void onDataNotAvailable() {
                navigator.showSnackBar("게시물 등록에 실패하였습니다. 다시 시도해주세요.");
                navigator.onActivityFinish();
            }
        });
    }

    // 해당 게시물을 수정한다.
    private void updateExistingBoard(){

        RequestParams params = new RequestParams();
        params.put("bbs_id", boardVO.getBoardType());
        params.put("ntt_id", boardVO.getBoardId());
        params.put("atch_file_id", boardVO.getFileIndex());

        boardDao.updateData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                navigator.onBoardUpdated();
            }

            @Override
            public void onDataNotAvailable() {
                navigator.showSnackBar("게시물 수정에 실패하였습니다. 다시 시도해주세요.");
                navigator.onActivityFinish();
            }
        });
    }

    private void getFileIndexArray() {
        String[] fileIndexArray = new String[boardItems.size()];
        for(int i = 0 ; i < boardItems.size() ; i++){
            fileIndexArray[i] = boardItems.get(i).getFileIndex();
        }
        boardVO.setFileIndex(fileIndexArray);
    }

}
