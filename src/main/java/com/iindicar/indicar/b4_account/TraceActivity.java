package com.iindicar.indicar.b4_account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.support.v7.widget.SimpleItemAnimator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.crashlytics.android.Crashlytics;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.BaseRecyclerViewAdapter;
import com.iindicar.indicar.R;
import com.iindicar.indicar.b2_community.boardDetail.BoardDetailActivity;
import com.iindicar.indicar.b2_community.boardList.BoardListAdapter;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.data.vo.UserVO;
import com.iindicar.indicar.databinding.ActivityTraceBinding;
import com.iindicar.indicar.utils.LocaleHelper;

import java.util.List;

import io.fabric.sdk.android.Fabric;

import static com.iindicar.indicar.Constant.RequestCode.REQUEST_BOARD_DETAIL;

public class TraceActivity extends BaseActivity<ActivityTraceBinding> implements TraceNavigator {
    public final ObservableField<String> textSearch = new ObservableField<>();
    public final ObservableBoolean isSearchBarOpen = new ObservableBoolean(false);
    String id;
    private TraceViewModel viewModel;
    private BoardListAdapter adapter;

    Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this,new Crashlytics());

        Context traceContext = LocaleHelper.setLocale(getApplicationContext());
        resources = traceContext.getResources();

        this.viewModel = new TraceViewModel();
        binding.setViewModel(viewModel);
        binding.setActivity(this);
        viewModel.boardTab.set(1);
        viewModel.setNavigator(this);
        binding.setViewModel(viewModel);
        //viewPager 어댑터 설정
        adapter = new BoardListAdapter(this, 1);

        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                viewModel.openBoardDetail(position);
            }
        });

        binding.recyclerViewBoardContainer.setAdapter(adapter);
        binding.recyclerViewBoardContainer.setNestedScrollingEnabled(false);
        ((SimpleItemAnimator) binding.recyclerViewBoardContainer.getItemAnimator()).setSupportsChangeAnimations(false);

        SharedPreferences prefLogin = getApplicationContext().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        id = prefLogin.getString("id", null);

        Intent intent = getIntent();
        int trace = intent.getIntExtra("trace", 1);
        switch (trace) {
            case 1:
                setLikeActivity();
                break;
            case 2:
                setWritingActivity();
                break;
            case 3:
                setCommentActivity();
                break;
            default:
                setLikeActivity();
                break;
        }

        //언어별 뷰 설정
        binding.constraintTitle.setBackground(resources.getDrawable(R.drawable.title_trace));
        binding.btnALike2.setBackground(resources.getDrawable(R.drawable.btna_like));
        binding.btnAWriting2.setBackground(resources.getDrawable(R.drawable.btna_writing));
        binding.btnAComment2.setBackground(resources.getDrawable(R.drawable.btna_comment));

        //버튼 클릭 이벤트 설정
        binding.btnALike2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLikeActivity();
            }
        });
        binding.btnAWriting2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                setWritingActivity();
            }
        });
        binding.btnAComment2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCommentActivity();
            }
        });

        actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_trace;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        centerImageId.set(R.drawable.logo_account);
        leftImageId.set(R.drawable.btn_back);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    private void setLikeActivity() {
        binding.ivALikeOn.setImageResource(R.drawable.btna_on);
        binding.ivAWritingOn.setImageResource(0);
        binding.ivACommentOn.setImageResource(0);
//        binding.viewPagerTrace.setCurrentItem(2);
//        viewModel.getBoardListTrace(id,"like");
        viewModel.getBoardListTrace(id, "like");
        /*try {

        } catch (Exception e) {

        }*/
    }

    private void setWritingActivity() {
        binding.ivALikeOn.setImageResource(0);
        binding.ivAWritingOn.setImageResource(R.drawable.btna_on);
        binding.ivACommentOn.setImageResource(0);
        viewModel.getBoardListTrace(id, "mine");
        /*try {

        } catch (Exception e) {

        }*/
    }

    private void setCommentActivity() {
        binding.ivALikeOn.setImageResource(0);
        binding.ivAWritingOn.setImageResource(0);
        binding.ivACommentOn.setImageResource(R.drawable.btna_on);
        viewModel.getBoardListTrace(id, "comment");
    }

    @Override
    public void openBoardDetail(int position) {
        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        Intent intent = new Intent(this, BoardDetailActivity.class);
        String loginId = prefLogin.getString("id", "admin");
        String loginName = prefLogin.getString("name", "이예슬");
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
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    @Override
    public void showPageEndMessage() {

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
        adapter.updateItems(list);
    }

}