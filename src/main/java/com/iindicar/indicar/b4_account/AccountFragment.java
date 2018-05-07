package com.iindicar.indicar.b4_account;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.a1_main.LoginActivity;
import com.iindicar.indicar.databinding.FragmentAccountBinding;
import com.iindicar.indicar.utils.ConstClass;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;

import java.util.Observable;

public class AccountFragment extends BaseFragment<FragmentAccountBinding> {
    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.ivAProfileImage.setBackground(new ShapeDrawable(new OvalShape()));
        if(Build.VERSION.SDK_INT>=21) {
            binding.ivAProfileImage.setClipToOutline(true);
        }

        final SharedPreferences prefLogin = this.getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        binding.tvAName.setText(prefLogin.getString("name","로그인 실패"));
        binding.tvAEmail.setText(prefLogin.getString("email","로그인 실패"));
        String profile_img_url = prefLogin.getString("profile_img_url","0");
        if(!profile_img_url.equals("0")) {
            Glide.with(getActivity()).load(profile_img_url).into(binding.ivAProfileImage);
        }

        binding.btnAGotoProfile.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ProfileActivity.class);
                startActivity(intent);
            }
        });
        binding.btnALogout.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!Session.getCurrentSession().isClosed()) {
                    UserManagement.requestLogout(new LogoutResponseCallback() {
                        @Override
                        public void onCompleteLogout() {
                            SharedPreferences.Editor editor = prefLogin.edit();
                            editor.putLong("profileEditDate",0);
                            editor.putString("_id","0");
                            editor.putString("login_method","0");
                            editor.putString("name","0");
                            editor.putString("profile_img_url","0");
                            editor.putString("email","fail");
                            editor.apply();

                            Intent i = new Intent(getActivity(), LoginActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                            getActivity().finish();
                        }
                    });
                } else if(FirebaseAuth.getInstance().getCurrentUser() != null) {
                    FirebaseAuth.getInstance().signOut();
                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putLong("profileEditDate",0);
                    editor.putString("_id","0");
                    editor.putString("login_method","0");
                    editor.putString("name","0");
                    editor.putString("profile_img_url","0");
                    editor.putString("email","fail");
                    editor.apply();

                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    getActivity().finish();
                } else if(AccessToken.getCurrentAccessToken() != null) {
                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putLong("profileEditDate",0);
                    editor.putString("_id","0");
                    editor.putString("login_method","0");
                    editor.putString("name","0");
                    editor.putString("profile_img_url","0");
                    editor.putString("email","fail");
                    editor.apply();

                    LoginManager.getInstance().logOut();
                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    getActivity().finish();
                } else {
                    Toast.makeText(getActivity(),ConstClass.strLoginedErr,Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = prefLogin.edit();
                    editor.putLong("profileEditDate",0);
                    editor.putString("_id","0");
                    editor.putString("login_method","0");
                    editor.putString("name","0");
                    editor.putString("profile_img_url","0");
                    editor.putString("email","fail");
                    editor.apply();

                    Intent i = new Intent(getActivity(), LoginActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    getActivity().finish();
                }
            }
        });

        binding.btnAAlliance.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.cafe.naver.com/ArticleList.nhn?search.clubid=29055312&search.menuid=18&search.boardtype=L"));
                startActivity(intent);
            }
        });

        binding.btnALike.setOnClickListener(traceClickListener);
        binding.btnAWriting.setOnClickListener(traceClickListener);
        binding.btnAComment.setOnClickListener(traceClickListener);

        binding.btnABasket.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),ConstClass.strNotPrepare,Toast.LENGTH_SHORT).show();
            }
        });
        binding.btnAHowtouse.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),ConstClass.strNotPrepare,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefLogin = this.getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        binding.tvAName.setText(prefLogin.getString("name","로그인 실패"));
    }

    //Trace Button Event
    Button.OnClickListener traceClickListener = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(),TraceActivity.class);
            switch(v.getId()) {
                case R.id.btnA_like:
                    intent.putExtra("trace",1);
                    break;
                case R.id.btnA_writing:
                    intent.putExtra("trace",2);
                    break;
                case R.id.btnA_comment:
                    intent.putExtra("trace",3);
                    break;
            }
            startActivity(intent);
        }
    };
}
