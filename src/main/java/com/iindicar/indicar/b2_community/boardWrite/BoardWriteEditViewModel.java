package com.iindicar.indicar.b2_community.boardWrite;

import android.content.Context;
import android.content.res.Resources;
import android.databinding.Observable;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.util.Log;
import android.view.View;

import com.iindicar.indicar.Constant;
import com.iindicar.indicar.R;
import com.iindicar.indicar.data.dao.BaseDao;
import com.iindicar.indicar.data.dao.BoardDao;
import com.iindicar.indicar.data.dao.BoardFileDao;
import com.iindicar.indicar.data.vo.WriteBoardVO;
import com.iindicar.indicar.data.vo.WriteFileVO;
import com.iindicar.indicar.utils.LocaleHelper;
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
    public int currentPageNum = 0;
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
    Resources resources;
    public BoardWriteEditViewModel(Context context) {
        boardDao = new BoardDao();
        fileDao = new BoardFileDao();
        Context loginContext = LocaleHelper.setLocale(context);
        resources = loginContext.getResources();
    }

    // this will be called only writing a New Board
    public void start(String userId, String userName) {
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

    public void setCarName(String carName) {
        this.boardVO.setCarName(carName);
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
    public void setBoardTypeFiltering(View view) {
        String tag = view.getTag().toString();

        if (Constant.BoardType.DAY_LIFE.equals(tag)) {
            boardType.set(Constant.BoardType.DAY_LIFE);

        } else if (Constant.BoardType.MARKET.equals(tag)) {
            boardType.set(Constant.BoardType.MARKET);

        } else if (Constant.BoardType.DIY.equals(tag)) {
            boardType.set(Constant.BoardType.DIY);
        }
    }

    public void onPrevPageButtonClicked() {
        if (currentPageNum < 1)
            return;
        currentPageNum--;
        pageNavigator.pageChangeToPosition(currentPageNum);
    }

    public void onNextPageButtonClicked() {
        if (currentPageNum == totalPage.get() - 1)
            return;
        currentPageNum++;
        pageNavigator.pageChangeToPosition(currentPageNum);
    }

    public void onSubmitClicked() {

        // 사진을 등록하지 않은 페이지 체크
        for (int i = 0; i < boardItems.size(); i++) {
            WriteFileVO vo = boardItems.get(i);

            if (vo.getFilePath() == null || vo.getFilePath().equals("")) {
                navigator.showSnackBar(resources.getString(R.string.needPhoto));
                pageNavigator.pageChangeToPosition(i);
                return;
            }
        }

        // 파일을 서버에 업로드한다.
        for (WriteFileVO vo : boardItems) {
            // index (atch_file_id) 없는 경우 - new file
            if (vo.getFileIndex() == null || vo.getFileIndex().equals("")) {

                insertNewFile(vo);
            } else {
                updateExistingFile(vo.getImageUrl() != null, vo);
            }
        }
    }

    public void pageAddClick() {
        if (totalPage.get() == MAX_PAGE) {
            navigator.showSnackBar(resources.getString(R.string.cannotAddPage));
            return;
        }
        currentPageNum++;
        pageNavigator.addPage(currentPageNum);
    }

    public void pageRemoveClick() {
        if (totalPage.get() == 1) {
            navigator.showSnackBar(resources.getString(R.string.cannotDeletePage));
            return;
        }
        pageNavigator.removePage(currentPage.get());
    }

    private void insertNewFile(final WriteFileVO vo) {

        RequestParams params = new RequestParams();
        navigator.pbOn();
        try {
            params.put("file", new File(vo.getFilePath()));
            params.put("file_cn", vo.getWriteText());
            Log.d("file", new File(vo.getFilePath()).toString());
            Log.d("file_cn", vo.getWriteText());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fileDao.insertData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {

                vo.setFileIndex((String) data); // atch_file_id 추가
                Log.d("ddf getFileIndex", vo.getFileIndex());
                DONE_FILE_UPLOAD_COUNT.add(true); // 완료 목록 추가
                if (DONE_FILE_UPLOAD_COUNT.size() == boardItems.size()) {
                    startUploadBoard();
                }
            }

            @Override
            public void onDataNotAvailable() {
                navigator.showSnackBar(resources.getString(R.string.failedRegisterPost));
                navigator.pbOff();
                navigator.onActivityFinish();
            }
        });
    }

    private void updateExistingFile(boolean isFileUpdated, final WriteFileVO vo) {
        navigator.pbOn();
        RequestParams params = new RequestParams();
        params.put("atch_file_id", vo.getFileIndex());
        params.put("file_cn", vo.getWriteText());

        if (isFileUpdated) { // 사진이 수정 된 경우
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
                if (DONE_FILE_UPLOAD_COUNT.size() == boardItems.size()) {
                    startUploadBoard();
                }
            }

            @Override
            public void onDataNotAvailable() {
                navigator.showSnackBar(resources.getString(R.string.failedRegisterPost));
                navigator.pbOff();
                navigator.onActivityFinish();
            }
        });
    }

    private void startUploadBoard() {

        getFileIndexArray();

        if (isNewBoard) { // 게시물 등록
            insertNewBoard();
        } else { // 게시물 수정
            updateExistingBoard();
        }
    }

    // 해당 게시물을 서버에 업로드한다.
    private void insertNewBoard() {

        RequestParams params = new RequestParams();
        params.put("bbs_id", boardVO.getBoardType());
        params.put("ntcr_nm", boardVO.getUserName());
        params.put("ntt_sj", "title");
        params.put("ntcr_id", boardVO.getUserId());
        String fileIndexString = "";
        for (int i = 0; i < boardVO.getFileIndex().length; i++) {
            if (i == 0)
                fileIndexString = boardVO.getFileIndex()[i];
            else
                fileIndexString += "," + boardVO.getFileIndex()[i];
        }
        params.put("atch_file_id", fileIndexString);
        params.put("carSpecName", boardVO.getCarName());
        params.put("branch_id", Constant.locale);
        Log.d("bbs_id", boardVO.getBoardType());
        Log.d("ntcr_nm", boardVO.getUserName());
        Log.d("ntcr_id", boardVO.getUserId());
        Log.d("atch_file_id", boardVO.getFileIndex()[0]);
        Log.d("carSpecName", "" + boardVO.getCarName());
        Log.d("branch_id", Constant.locale);
        boardDao.insertData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                navigator.pbOff();
                navigator.onBoardUploaded();
            }
            @Override
            public void onDataNotAvailable() {
                navigator.pbOff();
                navigator.showSnackBar(resources.getString(R.string.failedRegisterPost));
                navigator.onActivityFinish();
            }
        });
    }
    // 해당 게시물을 수정한다.
    private void updateExistingBoard() {
        RequestParams params = new RequestParams();
        params.put("bbs_id", boardVO.getBoardType());
        params.put("ntt_id", boardVO.getBoardId());
        params.put("atch_file_id", boardVO.getFileIndex());
        boardDao.updateData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                navigator.pbOff();
                navigator.onBoardUpdated();
            }
            @Override
            public void onDataNotAvailable() {
                navigator.pbOff();
                navigator.showSnackBar(resources.getString(R.string.failedModifyPost));
                navigator.onActivityFinish();
            }
        });
    }
    private void getFileIndexArray() {
        String[] fileIndexArray = new String[boardItems.size()];
        for (int i = 0; i < boardItems.size(); i++) {
            fileIndexArray[i] = boardItems.get(i).getFileIndex();
        }
        boardVO.setFileIndex(fileIndexArray);
    }
}
