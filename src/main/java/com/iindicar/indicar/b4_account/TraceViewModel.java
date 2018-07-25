package com.iindicar.indicar.b4_account;

import android.databinding.ObservableBoolean;
import android.databinding.ObservableInt;
import android.support.v7.widget.RecyclerView;

import com.iindicar.indicar.Constant;
import com.iindicar.indicar.b2_community.boardList.BoardListAdapter;
import com.iindicar.indicar.data.dao.BaseDao;
import com.iindicar.indicar.data.dao.BoardDao;
import com.iindicar.indicar.data.dao.BoardFileDao;
import com.iindicar.indicar.data.dao.UserDao;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.data.vo.UserVO;
import com.loopj.android.http.RequestParams;

import java.util.List;


/**
 * Created by yeseul on 2018-04-14.
 */

public class TraceViewModel {

    private static final String TAG = TraceViewModel.class.getSimpleName();
    private final int PAGE_UNIT_COUNT = 15;

    public final ObservableBoolean isDataLoading = new ObservableBoolean(false);
    public final ObservableInt boardTab = new ObservableInt();

    public final ObservableBoolean isPageUpScrolling = new ObservableBoolean(false);
    public final ObservableBoolean isScrolling = new ObservableBoolean(false);
    public final ObservableBoolean isVerticalScrolling = new ObservableBoolean(false);

    private TraceNavigator navigator;

    private BoardDao boardDao;
    private BoardFileDao fileDao;
    private UserDao userDao;

    private Boolean isListEnd = false;

    private int currentPage = 1;

    public TraceViewModel() {
        boardDao = new BoardDao();
        fileDao = new BoardFileDao();
        userDao = new UserDao();
    }

    public void setNavigator(TraceNavigator navigator) {
        this.navigator = navigator;
    }

    public void onRefresh(RecyclerView recyclerView) {
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter != null && adapter instanceof BoardListAdapter) {
            ((BoardListAdapter) adapter).clearItems();
        }
        currentPage = 1;
        isListEnd = false;
    }

    public void openBoardDetail(int position) {

        navigator.openBoardDetail(position);
    }


    public void getBoardListTrace(String user_id, String category) {

        isDataLoading.set(true);

        RequestParams params = new RequestParams();

        /** TODO (2018.05.03) vo로 바꾸고 Gson 사용 */

        params.put("id", user_id);
        params.put("ntcr_id", user_id);
        params.put("pageIndex", "1");
        params.put("pageUnit", "100");
        params.put("bbs_id", "all");
        params.put("searchCnd", "");
        params.put("branch_id", Constant.locale);

        boardDao.getDataListTrace(category, params, new BaseDao.LoadDataListCallBack() {
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
