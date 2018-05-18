package com.iindicar.indicar.b2_community.boardList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ScrollingView;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.BaseRecyclerViewAdapter;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.boardDetail.BoardDetailActivity;
import com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditActivity;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.data.vo.UserVO;
import com.iindicar.indicar.databinding.BoardListFragmentBinding;
import com.iindicar.indicar.utils.ScrollBottomAction;

import java.util.List;

import static android.app.Activity.RESULT_OK;
import static com.iindicar.indicar.Constant.RequestCode.REQUEST_BOARD_DETAIL;


/**
 * Created by yeseul on 2018-04-17.
 */

public class BoardListFragment extends BaseFragment<BoardListFragmentBinding> implements BoardListNavigator {
    private static final String TAG = BoardListFragment.class.getSimpleName();

    private int boardTab;
    private BoardListViewModel viewModel;
    private BoardListAdapter adapter;
    private int isUpdate;

    public BoardListFragment() {
        this.viewModel = new BoardListViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.board_list_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boardTab = getArguments().getInt("boardTab");
        Log.d("ddff", "boardlistcreated_Tab " + boardTab+" "+TAG);
        viewModel.boardTab.set(boardTab);
        viewModel.setNavigator(this);
        viewModel.start();
        if(boardTab==1){

        }
    }

    /**
     * call after view created
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setViewModel(viewModel);

        // set scroll bottom action listener to recyclerView
        new ScrollBottomAction()
                .with(binding.scrollview)
                .setOnScrollBottomListener(new ScrollBottomAction.onScrollEndListener() {
                    @Override
                    public void onScrollBottom(ScrollingView view) {
                        // request more list when scroll is bottom
                        viewModel.getBoardList();
                    }
                });

        adapter = new BoardListAdapter(context, boardTab);

        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                viewModel.openBoardDetail(position);
            }
        });

        binding.recyclerViewBoardContainer.setAdapter(adapter);
        binding.recyclerViewBoardContainer.setNestedScrollingEnabled(false);
        ((SimpleItemAnimator) binding.recyclerViewBoardContainer.getItemAnimator()).setSupportsChangeAnimations(false);

        // bind scroll listener to show/hide buttons
        binding.scrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                boolean isPageUp = (oldScrollY - scrollY) > 20; // scroll up
                boolean isPageDown = (scrollY - oldScrollY) > 20; // scroll down
                boolean isVerticalScrolling = (oldScrollX - scrollX) > 20 || (scrollX - oldScrollX) > 20;

                Handler handler = new Handler();

                if (isPageUp) {
                    viewModel.isPageUpScrolling.set(true);
                    viewModel.isScrolling.set(true);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.isPageUpScrolling.set(false);
                            viewModel.isScrolling.set(false);
                        }
                    }, 500);
                }

                if (isPageDown) {
                    viewModel.isScrolling.set(true);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.isScrolling.set(false);
                        }
                    }, 500);
                }

                if (isVerticalScrolling) {
                    viewModel.isVerticalScrolling.set(true);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            viewModel.isVerticalScrolling.set(false);
                        }
                    }, 500);
                }
            }
        });

        binding.buttonBoardWrite.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    Intent intent = new Intent(getContext(), BoardWriteEditActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.enter_bottom, R.anim.exit_no_anim);
                }
                return true;
            }
        });

        binding.buttonFastUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.scrollview.fullScroll(View.FOCUS_UP);
                }
                return true;
            }
        });
    }

    @Override
    public void openBoardDetail(int position) {

        SharedPreferences prefLogin = context.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String loginId = prefLogin.getString("_id", "admin");
        String loginName = prefLogin.getString("name", "이예슬");

        Intent intent = new Intent(context, BoardDetailActivity.class);

        BoardDetailVO vo = adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("boardVO", vo);
        intent.putExtra("board", bundle);
        intent.putExtra("boardVO", vo);
        intent.putExtra("loginId", loginId);
        intent.putExtra("loginName", loginName);

        if (vo.getUserId().equals(loginId)) { // 로그인 유저 정보와 글쓴이 정보 동일
            intent.putExtra("canUpdate", true);
        } else {
            intent.putExtra("canUpdate", false);
        }
        startActivityForResult(intent, REQUEST_BOARD_DETAIL);
        ((Activity) context).overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    @Override
    public void showPageEndMessage() {
        Toast.makeText(context, "마지막 페이지 입니다.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onImageAttached(BoardDetailVO board, BoardFileVO vo) {
        int position = adapter.getItemList().indexOf(board);
        adapter.setBoardFile(position, vo);
    }

    @Override
    public void onSearch(String searchWord) {
        viewModel.onSearch(binding.recyclerViewBoardContainer,searchWord);
    }


    @Override
    public void onProfileAttached(BoardDetailVO board, UserVO vo) {
        int position = adapter.getItemList().indexOf(board);
        adapter.setUserProfile(position, vo.getProfileImageUrl());
    }

    @Override
    public void onListAdded(List<BoardDetailVO> list) {
        adapter.addItems(list);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        isUpdate = 0;
        if (resultCode != RESULT_OK)
            return;

        if (requestCode == REQUEST_BOARD_DETAIL) { // result from BoardDetailActivity
            if (data.getBooleanExtra("isUpdated", false))
                isUpdate=1;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isUpdate == 1) {
            viewModel.onRefresh(binding.recyclerViewBoardContainer);
        }


    }
}