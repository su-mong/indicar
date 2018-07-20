package com.iindicar.indicar.b2_community.boardDetail;

import android.content.Intent;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.iindicar.indicar.Constant;
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
 */

public class BoardDetailViewModel {

    public final ObservableBoolean isBoardDataLoading = new ObservableBoolean(true);
    public final ObservableBoolean isCommentDataLoading = new ObservableBoolean(true);
    private boolean COMMENT_ADDED = true;
    private boolean COMMENT_UPDATED = false;

    private BoardDao boardDao;
    private BoardFileDao fileDao;
    private BoardCommentDao commentDao;
    private UserDao userDao;

    private BoardDetailNavigator navigator;

    public BoardDetailVO boardHeader;
    public String loginId;
    public String loginName;
    private int answerNo;
    private Boolean isListEnd = false;

    private int currentPage = 1;

    private boolean isCommentUpdating = false;
    private final int PAGE_UNIT_COUNT = 15;

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
        getFileData();
        getCommentList(COMMENT_ADDED);
    }

    private void checkIsLikeBoard() {
        RequestParams params = new RequestParams();
        params.put("id", loginId);
        boardDao.getLikeModel().getLikeList(params, new BaseDao.LoadDataListCallBack() {
            @Override
            public void onDataListLoaded(List list) {
                isLikeBoard.set(false);

                for (int i = 0; i < list.size(); i++) {
                    BoardDetailVO vo = (BoardDetailVO) list.get(i);
                    if (boardHeader.getBoardType().equals(vo.getBoardType())
                            && boardHeader.getBoardId().equals(vo.getBoardId())) {
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
        currentPage = 1;
        isListEnd = false;
        getBoardData();
//        getCommentList();
    }

    public void getBoardData() {
        RequestParams params = new RequestParams();
        params.put("bbs_id", boardHeader.getBoardType());
        params.put("ntt_id", boardHeader.getBoardId());
        params.put("branch_id", Constant.locale);

        boardDao.getData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                boardHeader = ((BoardDetailVO) data);
                getFileData();
                navigator.onHeaderAdded(boardHeader);
                checkIsLikeBoard();


            }

            @Override
            public void onDataNotAvailable() {
                navigator.onBoardNotAvailable();
            }
        });
    }

    private void getUser() {
        RequestParams params = new RequestParams();
        params.put("id", boardHeader.getUserId());

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
        if (fileIndexArray == null || fileIndexArray.length == 0 || fileIndexArray[0].equals("FILE_9") || fileIndexArray[0].equals("FILE_10")) {

            isBoardDataLoading.set(false);
            return;
        }

        for (int i = 0; i < fileIndexArray.length; i++) {
            final int position = i;
            RequestParams params = new RequestParams();
            params.put("atch_file_id", fileIndexArray[i]);
            fileDao.getData(params, new BaseDao.LoadDataCallBack() {
                @Override
                public void onDataLoaded(Object data) {
                    BoardFileVO vo = (BoardFileVO) data;
                    String temp = vo.getFileUrl();
                    temp = "http://" + temp.replace("8080", "9000");
                    vo.setFileUrl(temp);
                    doneFile.put(position, vo);
                    if (doneFile.size() == fileIndexArray.length) {
                        isBoardDataLoading.set(false);
                        for (BoardFileVO file : doneFile.values()) {
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


    private void getUserProfile(List list) {

        if (list == null) {
            return;
        }

        for (int i = 0; i < list.size(); i++) {
            final BoardCommentVO comment = (BoardCommentVO) list.get(i);

            if (comment == null) {
                isListEnd = true;
                continue;
            } else {
                if (comment.getUserKey() == null)
                    continue;
            }

            RequestParams params = new RequestParams();
            params.put("id", comment.getUserKey());

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

    public void sendReport(String reason) {
        RequestParams params = new RequestParams();
        params.put("bbs_id", boardHeader.getBoardType());
        params.put("ntt_id", boardHeader.getBoardId());
        params.put("writer_id", loginId);
        params.put("report_content", reason);
        boardDao.sendReport(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                navigator.onSended();
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
    public boolean onLikeButtonClick(View view, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            RequestParams params = new RequestParams();
            params.put("id", loginId);
            params.put("ntt_id", boardHeader.getBoardId());

            boardDao.getLikeModel().toggleLike(params, new BaseDao.LoadDataCallBack() {
                @Override
                public void onDataLoaded(Object data) {
                    navigator.onUpdatedBoard();
                }

                @Override
                public void onDataNotAvailable() {

                }
            });
        }
        return true;
    }

    public void onCommentSubmitClick() {
        navigator.hideKeyboard();
        if (commentWrite.get().equals("")) {
            return;
        }

        if (isCommentUpdating)
            updateComment();
        else {
            insertComment();
        }
    }

    public void startCommentUpdating(BoardCommentVO vo) {
        isCommentUpdating = true;
        commentWrite.set(vo.getContent());
        answerNo = vo.getCommentIndex();
    }

    public void updateComment() {

        RequestParams params = new RequestParams();
        params.put("id", answerNo);
        params.put("comment_cn", commentWrite.get());

        commentDao.updateData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                commentWrite.set("");
                isListEnd = false;
                currentPage = 1;
                getCommentList(COMMENT_UPDATED);
            }

            @Override
            public void onDataNotAvailable() {
                isCommentUpdating = false;
            }
        });
    }

    public void insertComment() {
        RequestParams params = new RequestParams();
        params.put("ntt_id", boardHeader.getBoardId());
        params.put("comment_cn", commentWrite.get());
        params.put("writer_id", loginId);

        commentDao.insertData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {

                commentWrite.set("");
                isListEnd = false;
                currentPage = 1;
                getCommentList(COMMENT_UPDATED);
            }

            @Override
            public void onDataNotAvailable() {

            }
        });
    }

    public void getCommentList(final boolean ISADDED) {
//         마지막 페이지
        if (isListEnd) {
            isCommentDataLoading.set(false);
            navigator.showPageEndMessage();
            return;
        }
        isCommentDataLoading.set(true);

        RequestParams params = new RequestParams();
        params.put("ntt_id", boardHeader.getBoardId());
        params.put("pageIndex", String.valueOf(currentPage));
        params.put("pageUnit", PAGE_UNIT_COUNT);

        commentDao.getDataList(params, new BaseDao.LoadDataListCallBack() {
            @Override
            public void onDataListLoaded(List list) {
                int size = list.size();
                // end of board list
                if (size != PAGE_UNIT_COUNT) {
                    isListEnd = true;
                }
                currentPage++;

                if (ISADDED)
                    navigator.onCommentAdded(list);
                else
                    navigator.onCommentUpdated(list);
                isCommentDataLoading.set(false);
            }

            @Override
            public void onDataNotAvailable() {
                navigator.onCommentUpdated_emtpy();
                isCommentDataLoading.set(false);
            }
        });
    }

    /* Data Binding
     *
     * 댓글 버튼의 onClick 메서드
     * */
    public boolean onCommentButtonClick(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            navigator.showKeyboard();
        }
        return true;
    }

    public void deleteComment(int index) {

        RequestParams params = new RequestParams();
        params.put("id", index);

        commentDao.deleteData(params, new BaseDao.LoadDataCallBack() {
            @Override
            public void onDataLoaded(Object data) {
                isListEnd = false;
                currentPage = 1;
                getCommentList(COMMENT_UPDATED);
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
