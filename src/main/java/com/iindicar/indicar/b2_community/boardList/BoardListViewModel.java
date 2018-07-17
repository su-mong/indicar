package com.iindicar.indicar.b2_community.boardList;

import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.iindicar.indicar.Constant;
import com.iindicar.indicar.data.dao.BaseDao;
import com.iindicar.indicar.data.dao.BoardDao;
import com.iindicar.indicar.data.dao.BoardFileDao;
import com.iindicar.indicar.data.dao.UserDao;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.data.vo.UserVO;
import com.iindicar.indicar.utils.LocaleHelper;
import com.loopj.android.http.RequestParams;

import java.util.List;

import static com.iindicar.indicar.Constant.BoardTab.BOARD_POPULAR;


/**
 * Created by yeseul on 2018-04-14.
 */

public class BoardListViewModel {

    private static final String TAG = BoardListViewModel.class.getSimpleName();
    private final int PAGE_UNIT_COUNT = 15;

    public final ObservableBoolean isDataLoading = new ObservableBoolean(false);
    public final ObservableInt boardTab = new ObservableInt();

    public final ObservableBoolean isPageUpScrolling = new ObservableBoolean(false);
    public final ObservableBoolean isScrolling = new ObservableBoolean(false);
    public final ObservableBoolean isVerticalScrolling = new ObservableBoolean(false);

    private BoardListNavigator navigator;

    private BoardDao boardDao;
    private BoardFileDao fileDao;
    private UserDao userDao;
    private Boolean isListEnd = false;

    private int currentPage = 1;
    private String userId;

    public BoardListViewModel() {
        boardDao = new BoardDao();
        fileDao = new BoardFileDao();
        userDao = new UserDao();

    }

    public void setNavigator(BoardListNavigator navigator) {
        this.navigator = navigator;
    }

    public void onRefresh(RecyclerView recyclerView) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null && adapter instanceof BoardListAdapter) {
            ((BoardListAdapter) adapter).clearItems();
        }
        currentPage = 1;
        isListEnd = false;
        getBoardList();
    }

    public void onSearch(RecyclerView recyclerView, String searchWord) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null && adapter instanceof BoardListAdapter) {
            ((BoardListAdapter) adapter).clearItems();
        }
        currentPage = 1;
        isListEnd = false;
        getBoardListOnSearch(searchWord);
    }

    public void start() {
        getBoardList();
    }

    public void openBoardDetail(int position) {

        navigator.openBoardDetail(position);
    }

    public void getBoardList() {
        // 마지막 페이지
        if (isListEnd) {
            isDataLoading.set(false);
            navigator.showPageEndMessage();
            return;
        }

        isDataLoading.set(true);

        RequestParams params = new RequestParams();

        /** TODO (2018.05.03) vo로 바꾸고 Gson 사용 */
        params.put("bbs_id", "all");
        if (boardTab.get() == BOARD_POPULAR) {
            params.put("searchCnd", "pop");
        } else {
            params.put("searchCnd", "");
        }
        params.put("pageIndex", String.valueOf(currentPage));
        params.put("pageUnit", PAGE_UNIT_COUNT);
        params.put("branch_id", Constant.locale);


        boardDao.getDataList(params, new BaseDao.LoadDataListCallBack() {
            @Override
            public void onDataListLoaded(List list) {
                int size = list.size();

                // end of board list
                if (size != PAGE_UNIT_COUNT) {
                    isListEnd = true;
                }
                currentPage++;

                navigator.onListAdded(list);
                isDataLoading.set(false);

                // 메인 사진을 받아온다
                getImageFile(list);
            }

            @Override
            public void onDataNotAvailable() {
                isDataLoading.set(false);
                isListEnd = true;
            }
        });
    }



    public void getBoardListOnSearch(String searchWord) {
        isDataLoading.set(true);

        RequestParams params = new RequestParams();
        /** TODO (2018.05.03) vo로 바꾸고 Gson 사용 */
        params.put("bbs_id", "all");
        params.put("searchCnd", "search");
        params.put("searchWord", searchWord);
        params.put("pageIndex", String.valueOf(currentPage));
        params.put("pageUnit", PAGE_UNIT_COUNT);
        boardDao.getDataList(params, new BaseDao.LoadDataListCallBack() {
            @Override
            public void onDataListLoaded(List list) {
                int size = list.size();

                // end of board list
                if (size != PAGE_UNIT_COUNT) {
                    isListEnd = true;
                }

                currentPage++;

                navigator.onListAdded(list);
                isDataLoading.set(false);

                // 메인 사진을 받아온다
                getImageFile(list);
            }

            @Override
            public void onDataNotAvailable() {
                isDataLoading.set(false);
                isListEnd = true;
            }
        });
    }


    private void getImageFile(final List<BoardDetailVO> list) {

        for (int i = 0; i < list.size(); i++) {
            final int position = i;
            final BoardDetailVO board = list.get(i);

            // 유저 프로필 사진
            RequestParams param = new RequestParams();
            param.put("id", board.getUserId());

            userDao.getData(param, new BaseDao.LoadDataCallBack() {
                @Override
                public void onDataLoaded(Object data) {
                    UserVO vo = (UserVO) data;
                    list.get(position).setUserProfileUrl(vo.getProfileImageUrl());

                }

                @Override
                public void onDataNotAvailable() {

                }
            });

            // 메인 사진
            String[] atchFileId = board.getAtchFileId();

            RequestParams params = new RequestParams();
            // 사진이 존재하지 않는 게시물
            if (atchFileId == null || atchFileId.length == 0) {

            } else {
                params.put("atch_file_id", atchFileId[0]);
                fileDao.getData(params, new BaseDao.LoadDataCallBack() {
                    @Override
                    public void onDataLoaded(Object data) {
                        BoardFileVO file = (BoardFileVO) data;
                        navigator.onImageAttached(list.get(position), file);
                    }

                    @Override
                    public void onDataNotAvailable() {

                    }
                });
            }


        }
    }
}
