package com.iindicar.indicar.b2_community.boardList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ScrollingView;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.MotionEvent;
import android.view.View;

import com.commit451.teleprinter.Teleprinter;
import com.crashlytics.android.Crashlytics;
import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.a1_main.MainActivity;
import com.iindicar.indicar.b2_community.BoardFilterActivity;
import com.iindicar.indicar.b2_community.boardDetail.BoardDetailActivity;
import com.iindicar.indicar.b2_community.boardWrite.BoardWriteEditActivity;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.data.vo.UserVO;
import com.iindicar.indicar.databinding.BoardListFragmentBinding;
import com.iindicar.indicar.utils.LocaleHelper;
import com.iindicar.indicar.utils.ScrollBottomAction;

import java.util.List;

import io.fabric.sdk.android.Fabric;

import static android.app.Activity.RESULT_OK;
import static com.iindicar.indicar.Constant.RequestCode.REQUEST_BOARD_DETAIL;


/**
 * Created by yeseul on 2018-04-17.
 */

public class BoardListFragment extends BaseFragment<BoardListFragmentBinding> implements BoardListNavigator {
    private static final String TAG = BoardListFragment.class.getSimpleName();
    private final int REQUEST_BOARD_FILTER = 111; // 게시판 필터링

    private int boardTab;
    private BoardListViewModel viewModel;
    private BoardListAdapter adapter;
    private int isUpdate;
    private Teleprinter keyboard;
    int scrollVel = 0;

    public final ObservableField<String> textSearch = new ObservableField<>();
    public final ObservableBoolean isSearchBarOpen = new ObservableBoolean(false);
    public final ObservableInt tabId = new ObservableInt();
    public final ObservableBoolean showButton = new ObservableBoolean(true);

    Resources resources;


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
        Fabric.with(getActivity(), new Crashlytics());

        boardTab = getArguments().getInt("boardTab");
        tabId.set(boardTab);
        Context boardListContext = LocaleHelper.setLocale(getActivity());
        resources = boardListContext.getResources();

        viewModel.boardTab.set(boardTab);
        viewModel.setNavigator(this);
        viewModel.start();


    }


    /**
     * call after view created
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.setViewModel(viewModel);

        //binding.editTextSearch.setHint(resources.getString(R.string.communityHint));

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
        adapter.setOnItemClickListener(new BoardListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                viewModel.openBoardDetail(position);
            }
        });

        binding.recyclerViewBoardContainer.setAdapter(adapter);
        binding.recyclerViewBoardContainer.setNestedScrollingEnabled(false);
        ((SimpleItemAnimator) binding.recyclerViewBoardContainer.getItemAnimator()).setSupportsChangeAnimations(false);

//        // bind scroll listener to show/hide buttons
        binding.scrollview.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                scrollVel = Math.abs(oldScrollY - scrollY); //스크롤 속도 위아래 상관없이

                Handler handler = new Handler();

                if (oldScrollY > scrollY)
                    viewModel.isPageUpScrolling.set(true);//스크롤 위로

                if (scrollVel > 100) { // 100이상일때 버튼 숨김
                    viewModel.isScrolling.set(true);
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if (scrollVel < 10)
                                viewModel.isScrolling.set(false);
                        }
                    }, 500);
                }

                if (scrollVel < 10) // 10 이하일때 버튼 표시
                    viewModel.isScrolling.set(false);


                if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) { //바닥에 닿으면 버튼 표시
                    viewModel.isScrolling.set(false);
                }

                if (scrollY == 0){ //최상단 가면 버튼 표시 / 위로 버튼 숨김
                    viewModel.isScrolling.set(false);
                    viewModel.isPageUpScrolling.set(false);
                }
            }
        });


        binding.buttonBoardWrite.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//                    binding.buttonBoardWrite.setColorFilter(Color.parseColor("#A500FFFF"), PorterDuff.Mode.SRC_ATOP);
                    binding.buttonBoardWrite.setColorFilter(Color.argb(100, 255, 150, 0));

                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    binding.buttonBoardWrite.setColorFilter(null);
                    Intent intent = new Intent(getContext(), BoardWriteEditActivity.class);
                    startActivityForResult(intent, 13);
                }
                return true;
            }
        });

        binding.buttonFastUp.setOnTouchListener(new View.OnTouchListener()

        {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    binding.scrollview.scrollTo(0, 0);
                }
                return true;
            }
        });

        ((MainActivity)

                getActivity()).

                getActionBarBinding().buttonLeft.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BoardFilterActivity.class);
                startActivityForResult(intent, REQUEST_BOARD_FILTER);
                getActivity().overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
            }
        });

        /*if (boardTab == 1)
            binding.searchBarLayout.setVisibility(View.VISIBLE);

        binding.buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSearch(binding.editTextSearch.getText().toString());
            }


        });

        binding.editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    binding.buttonSearch.callOnClick();
                    return true;
                }
                return false;
            }
        });*/
    }

    @Override
    public void openBoardDetail(int position) {

        SharedPreferences prefLogin = context.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String loginId = prefLogin.getString("id", "0");
        String loginName = prefLogin.getString("name", "fail");
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
        Snackbar.make(binding.getRoot(), resources.getString(R.string.lastPage), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onImageAttached(BoardDetailVO board, BoardFileVO vo) {
        int position = adapter.getItemList().indexOf(board);
        adapter.setBoardFile(position, vo);
    }

    @Override
    public void onSearch(String searchWord) {
        viewModel.onSearch(binding.recyclerViewBoardContainer, searchWord);
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
        if (resultCode != RESULT_OK)
            return;

        if (requestCode == REQUEST_BOARD_DETAIL) { // result from BoardDetailActivity
            if (data.getBooleanExtra("isUpdated", false))
                viewModel.onRefresh(binding.recyclerViewBoardContainer);
        }
        if (requestCode == 13) { // result from BoardDetailActivity
            if (data.getBooleanExtra("isUpdated", false))
                viewModel.onRefresh(binding.recyclerViewBoardContainer);
        }

        if (requestCode == REQUEST_BOARD_FILTER) { // 검색
            onSearch(data.getStringExtra("searchKey"));
        }
    }

    @Override
    public void onResume() {
        super.onResume();

    }
}