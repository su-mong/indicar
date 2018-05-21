package com.iindicar.indicar.b2_community.boardDetail;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.iindicar.indicar.R;
import com.iindicar.indicar.b4_account.ProfileSuggest;
import com.iindicar.indicar.data.dao.BaseDao;
import com.iindicar.indicar.data.dao.BoardCommentDao;
import com.iindicar.indicar.data.dao.BoardDao;
import com.iindicar.indicar.data.dao.BoardFileDao;
import com.iindicar.indicar.data.dao.UserDao;
import com.iindicar.indicar.data.vo.BoardCommentVO;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.data.vo.UserVO;
import com.loopj.android.http.RequestParams;

import java.util.List;
import java.util.TreeMap;

/**
 * Created by yeseul on 2018-04-13.
 *
 */

public class BoardDetailViewModel {

    public final ObservableBoolean isBoardDataLoading = new ObservableBoolean(true);
    public final ObservableBoolean isCommentDataLoading = new ObservableBoolean(true);

    private BoardDao boardDao;
    private BoardFileDao fileDao;
    private BoardCommentDao commentDao;
    private UserDao userDao;

    private BoardDetailNavigator navigator;

    public BoardDetailVO boardHeader;
    public String loginId;
    public String loginName;
    private int answerNo;

    private boolean isCommentUpdating = false;

    public final ObservableBoolean isKeyboardOpen = new ObservableBoolean(false);
    public final ObservableBoolean isPageUpScrolling = new ObservableBoolean(false);
    public final ObservableBoolean isScrolling = new ObservableBoolean(false);
    public final ObservableBoolean isLikeBoard = new ObservableBoolean();
    public final ObservableField<String> commentWrite = new ObservableField<>();

    private static int doneCount = 0;

    public BoardDetailViewModel() {
        this.boardDao = new BoardDao();
        this.fileDao = new BoardFileDao();
        this.commentDao = new BoardCommentDao();
        this.userDao = new UserDao();
    }

    public void setNavigator(BoardDetailNavigator navigator) {
        this.navigator = navigator;
    }

    public void start() {
        Intent intent = navigator.getActivityIntent();
        Bundle bundle = intent.getBundleExtra("board");
        boardHeader = bundle.getParcelable("boardVO");
        this.loginId = intent.getStringExtra("loginId");
        this.loginName = intent.getStringExtra("loginName");

        checkIsLikeBoard();
        onRefreshBoard();
    }

    private void checkIsLikeBoard() {
        RequestParams params = new RequestParams();
        params.put("_id", loginId);
        boardDao.getLikeModel().getLikeList(params, new BaseDao.LoadDataListCallBack() {
            @Override
            public void onDataListLoaded(List list) {
                if(list == null){
                    isLikeBoard.set(false);
                    return;
                }

                for(int i = 0 ; i < list.size() ; i++){
                    BoardDetailVO vo = (BoardDetailVO) list.get(i);
                    if(boardHeader.getBoardType().equals(vo.getBoardType())
                            && boardHeader.getBoardId().equals(vo.getBoardId())){
                        isLikeBoard.set(true);
                        return;
                    }
                }
            }

            @Override
            public void onDataNotAvailable() {
                isLikeBoard.set(false);
            }
        });
    }

    public void onRefreshBoard() {

        isBoardDataLoading.set(true);

        getBoardData();
        getFileData();
        getCommentList();
    }

    public void getBoardData(){

        RequestParams params = new RequestParams();
        params.put("bbs_id", boardHeader.getBoardType());
        params.put("ntt_id", boardHeader.getBoardId());

        boardDao.getData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                Log.d("ddf getBoarddata",boardHeader.getBoardContent());
                boardHeader = ((BoardDetailVO) data);

                getUser();
            }

            @Override
            public void onDataNotAvailable() {
                navigator.onBoardNotAvailable();
            }
        });
    }

    private void getUser() {
        RequestParams params = new RequestParams();
        params.put("_id", boardHeader.getUserId());

        userDao.getData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                UserVO vo = (UserVO) data;
                boardHeader.setUserProfileUrl(vo.getProfileImageUrl());
                navigator.onHeaderAdded(boardHeader);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    private void getFileData() {

        final TreeMap<Integer, BoardFileVO> doneFile = new TreeMap<>();

        final String[] fileIndexArray = boardHeader.getAtchFileId();

        // 이미지가 없는 게시물
        if(fileIndexArray == null || fileIndexArray.length == 0||fileIndexArray[0].equals("FILE_9")||fileIndexArray[0].equals("FILE_10")){

            isBoardDataLoading.set(false);
            return;
        }

        for(int i = 0 ; i < fileIndexArray.length ; i++) {
            final int position = i;
            RequestParams params = new RequestParams();
            params.put("atch_file_id", fileIndexArray[i]);

            fileDao.getData(params, new BaseDao.LoadDataCallBack() {
                @Override
                public void onDataLoaded(Object data) {
                    BoardFileVO vo = (BoardFileVO) data;
                    doneFile.put(position, vo);

                    if(doneFile.size() == fileIndexArray.length) {
                        isBoardDataLoading.set(false);
                        for(BoardFileVO file : doneFile.values()){
                            navigator.onItemAdded(file);
                        }
                    }
                }

                @Override
                public void onDataNotAvailable() {
                    doneFile.put(position, null);
                }
            });
        }
    }

    private void getCommentList() {
        isCommentDataLoading.set(true);

        RequestParams params = new RequestParams();
        params.put("bbs_id", boardHeader.getBoardType());
        params.put("ntt_id", boardHeader.getBoardId());

        commentDao.getDataList(params, new BaseDao.LoadDataListCallBack() {
            @Override
            public void onDataListLoaded(List list) {
                navigator.onCommentUpdated(list);
                isCommentDataLoading.set(false);

                getUserProfile(list);
            }

            @Override
            public void onDataNotAvailable() {
                navigator.onCommentUpdated_emtpy();
                isCommentDataLoading.set(false);
            }
        });
    }

    private void getUserProfile(List list) {

        if(list == null){
            return;
        }

        for(int i = 0 ; i < list.size() ; i++){
            final BoardCommentVO comment = (BoardCommentVO) list.get(i);

            if(comment.getUserKey() == null){
                continue;
            }

            RequestParams params = new RequestParams();
            params.put("_id", comment.getUserKey());

            userDao.getData(params, new BaseDao.LoadDataCallBack() {
                @Override
                public void onDataLoaded(Object data) {
                    UserVO vo = (UserVO) data;
                    navigator.onCommentProfileAttached(comment, vo);
                }

                @Override
                public void onDataNotAvailable() {

                }
            });
        }

    }

    public void sendReport(String reason){
        RequestParams params = new RequestParams();
        params.put("bbs_id", boardHeader.getBoardType());
        params.put("ntt_id", boardHeader.getBoardId());
        params.put("writer_id", loginId);
        params.put("reason",reason);


        boardDao.sendReport(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {

            }
            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    /* Data Binding
    *
    * 좋아요 버튼 onClick 메서드
    * */
    public boolean onLikeButtonClick(View view, MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            RequestParams params = new RequestParams();
            params.put("_id", loginId);
            params.put("bbs_id", boardHeader.getBoardType());
            params.put("ntt_id", boardHeader.getBoardId());

            boardDao.getLikeModel().toggleLike(params, new BaseDao.LoadDataCallBack() {
                @Override
                public void onDataLoaded(Object data) {
                    int likeCount = Integer.parseInt(boardHeader.getLikeCount());
                    Log.d("ddf","islike"+isLikeBoard.get());
                    if(isLikeBoard.get()) { // 좋아요 였는데 취소함
                        boardHeader.setLikeCount(String.valueOf(likeCount - 1));
                    } else {
                        boardHeader.setLikeCount(String.valueOf(likeCount + 1));
                    }
                    isLikeBoard.set(!isLikeBoard.get());
                    onRefreshBoard();
                    navigator.onLikeBoard();
                }

                @Override
                public void onDataNotAvailable() {

                }
            });
        }
        return true;
    }

    public void onCommentSubmitClick(){
        navigator.hideKeyboard();
        if(commentWrite.get().equals("")){
            return;
        }

        if(isCommentUpdating)
            updateComment();
        else{
            insertComment();
        }
    }

    public void startCommentUpdating(BoardCommentVO vo) {
        isCommentUpdating = true;
        commentWrite.set(vo.getContent());
        answerNo = vo.getCommentIndex();
    }

    public void updateComment(){

        RequestParams params = new RequestParams();
        params.put("ntt_id", boardHeader.getBoardId());
        params.put("answer_no", answerNo);
        params.put("answer", commentWrite.get());

        commentDao.updateData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                commentWrite.set("");
                isCommentUpdating = false;
                getCommentList();
            }
            @Override
            public void onDataNotAvailable() {
                isCommentUpdating = false;
            }
        });
    }

    public void insertComment(){
        RequestParams params = new RequestParams();
        params.put("bbs_id", boardHeader.getBoardType());
        params.put("ntt_id", boardHeader.getBoardId());
        params.put("answer", commentWrite.get());
        params.put("writer_nm", loginName);
        params.put("writer_id", loginId);


        commentDao.insertData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                commentWrite.set("");
                isCommentUpdating = false;
                getCommentList();
            }
            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    /* Data Binding
    *
    * 댓글 버튼의 onClick 메서드
    * */
    public boolean onCommentButtonClick(View view, MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            navigator.showKeyboard();
        }
        return true;
    }

    public void deleteComment(int index) {
        RequestParams params = new RequestParams();
        params.put("ntt_id", boardHeader.getBoardId());
        params.put("answer_no", index);

        commentDao.deleteData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                getCommentList();
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    public void deleteBoard() {
        RequestParams params = new RequestParams();
        params.put("bbs_id", this.boardHeader.getBoardType());
        params.put("ntt_id", this.boardHeader.getBoardId());

        boardDao.deleteData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                navigator.onDeleteBoard();
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }
}
