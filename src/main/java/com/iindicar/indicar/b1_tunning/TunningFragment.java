package com.iindicar.indicar.b1_tunning;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.BaseRecyclerViewAdapter;
import com.iindicar.indicar.b2_community.boardDetail.BoardDetailActivity;
import com.iindicar.indicar.data.vo.BoardDetailVO;
import com.iindicar.indicar.data.vo.BoardFileVO;
import com.iindicar.indicar.data.vo.UserVO;
import com.iindicar.indicar.databinding.TunningFragmentBinding;

import java.util.List;
import java.util.Observable;

import static android.app.Activity.RESULT_OK;
import static com.iindicar.indicar.Constant.RequestCode.REQUEST_BOARD_DETAIL;

public class TunningFragment extends BaseFragment<TunningFragmentBinding> implements NoticeNavigator{

    private NoticeViewModel viewModel;
    private NoticeAdapter adapter;

    public TunningFragment() {
        this.viewModel = new NoticeViewModel();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.tunning_fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel.setNavigator(this);
        viewModel.start();
    }

    /** call after view created */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.btnTToTuning2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CarListActivity.class);
                startActivity(intent);
            }
        });

        binding.setViewModel(viewModel);

        adapter = new NoticeAdapter(context);
        adapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                viewModel.openBoardDetail(position);
            }
        });
        binding.noticeContainer.setAdapter(adapter);
        binding.noticeContainer.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        ((SimpleItemAnimator)binding.noticeContainer.getItemAnimator()).setSupportsChangeAnimations(false);
    }

    @Override
    public void openBoardDetail(int position) {

        SharedPreferences prefLogin = context.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String loginId = prefLogin.getString("_id", "admin");
        String loginName = prefLogin.getString("name", "admin");

        Intent intent = new Intent(context, BoardDetailActivity.class);

        BoardDetailVO vo = adapter.getItem(position);
        Bundle bundle = new Bundle();
        bundle.putParcelable("boardVO", vo);
        intent.putExtra("board", bundle);
        intent.putExtra("loginId", loginId);
        intent.putExtra("loginName", loginName);
        intent.putExtra("commentCount", vo.getCommentCount());

        if(vo.getUserId().equals(loginId)){ // 로그인 유저 정보와 글쓴이 정보 동일
            intent.putExtra("canUpdate", true);
        } else {
            intent.putExtra("canUpdate", false);
        }

        startActivityForResult(intent, REQUEST_BOARD_DETAIL);
        ((Activity)context).overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
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
        if(resultCode != RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_BOARD_DETAIL){ // result from BoardDetailActivity
            /*if(data.getBooleanExtra("isUpdated", false)) {
                viewModel.onRefresh(binding.recyclerViewBoardContainer);
            }*/
        }
    }

}
