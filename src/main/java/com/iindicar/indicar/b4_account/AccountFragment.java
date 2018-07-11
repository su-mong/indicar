package com.iindicar.indicar.b4_account;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.crashlytics.android.Crashlytics;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.iindicar.indicar.BaseFragment;
import com.iindicar.indicar.R;
import com.iindicar.indicar.a1_main.LoginActivity;
import com.iindicar.indicar.a1_main.Tutorial;
import com.iindicar.indicar.databinding.FragmentAccountBinding;
import com.iindicar.indicar.utils.HttpClient;
import com.iindicar.indicar.utils.LocaleHelper;
import com.kakao.auth.Session;
import com.kakao.usermgmt.UserManagement;
import com.kakao.usermgmt.callback.LogoutResponseCallback;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import io.fabric.sdk.android.Fabric;

public class AccountFragment extends BaseFragment<FragmentAccountBinding> {

    String partnerCategory;
    Resources resources;
    String locale; SharedPreferences prefLogin;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_account;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(getActivity(),new Crashlytics());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.setFragment(this);
        Context fragmentContext = LocaleHelper.setLocale(getActivity());
        resources = fragmentContext.getResources();

        binding.textAccountTitle.setText(resources.getString(R.string.AccountTitle));
        binding.buttontxAlliacne.setText(resources.getString(R.string.AllianceTitle));
        binding.buttontxBasket.setText(resources.getString(R.string.BasketTitle));
        binding.buttontxComment.setText(resources.getString(R.string.MycommentTitle));
        binding.buttontxHowtouse.setText(resources.getString(R.string.HowtouseTitle));
        binding.buttontxLike.setText(resources.getString(R.string.LikeTitle));
        binding.buttontxLogout.setText(resources.getString(R.string.LogoutTitle));
        binding.buttontxWriting.setText(resources.getString(R.string.MywritingTitle));
        binding.tvAAddress.setText(resources.getString(R.string.AccountNoAddress));

        binding.textSubtitle.setText(resources.getString(R.string.AccountSub));
        binding.subbuttonAlliance.setText(resources.getString(R.string.AllianceSub));
        binding.subbuttonBasket.setText(resources.getString(R.string.BasketSub));
        binding.subbuttonComment.setText(resources.getString(R.string.MycommentSub));
        binding.subbuttonHowtouse.setText(resources.getString(R.string.HowtouseSub));
        binding.subbuttonLike.setText(resources.getString(R.string.LikeSub));
        binding.subbuttonLogout.setText(resources.getString(R.string.LogoutSub));
        binding.subbuttonWriting.setText(resources.getString(R.string.MywritingSub));

        binding.ivAProfileImage.setBackground(new ShapeDrawable(new OvalShape()));
        if (Build.VERSION.SDK_INT >= 21) {
            binding.ivAProfileImage.setClipToOutline(true);
        }

        prefLogin = this.getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        binding.tvAName.setText(prefLogin.getString("name", "로그인 실패"));
        binding.tvAEmail.setText(prefLogin.getString("email", "로그인 실패"));
        String profile_img_url = prefLogin.getString("profile_img_url", "0");
        locale = prefLogin.getString("locale",getActivity().getResources().getConfiguration().locale.getLanguage());
        if (!profile_img_url.equals("0")) {
            Glide.with(getActivity()).load(profile_img_url).into(binding.ivAProfileImage);
        }
    }

    //아래쪽은 모두 버튼 리스너
    public void btnAGotoProfile() {
        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        startActivity(intent);
    }
    public void btnABasket() {
        showSnackBar(resources.getString(R.string.strNotPrepare));
    }
    public void btnAHowtouse() {
        startActivity(new Intent(context,Tutorial.class));
        //getActivity().overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }
    public void btnAAlliance() {
        //팝업창을 보여주는 함수
        ImageView popupTitle;
        ImageView btnSend;
        ImageView btnAlertCancel;
        final EditText editName;
        final EditText editEmail;
        final EditText editReason;

        LayoutInflater factory = LayoutInflater.from(getActivity());
        final View popupDialogView = factory.inflate(R.layout.alert_partner, null);
        final AlertDialog dialog = new AlertDialog.Builder(getActivity()).create();
        dialog.setView(popupDialogView);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        popupTitle = (ImageView) popupDialogView.findViewById(R.id.ivAP_Title);
        btnSend = (ImageView) popupDialogView.findViewById(R.id.btn_send);
        btnAlertCancel = (ImageView) popupDialogView.findViewById(R.id.btn_x);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_dropdown_item, resources.getStringArray(R.array.partnerCategory));
        Spinner dropdown =(Spinner)popupDialogView.findViewById(R.id.spinner1);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                partnerCategory=parent.getItemAtPosition(position).toString();
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        //언어 설정
        popupTitle.setImageDrawable(resources.getDrawable(R.drawable.partner_1));
        btnSend.setImageDrawable(resources.getDrawable(R.drawable.profile_suggest_btn));
        TextView tvAP_category = popupDialogView.findViewById(R.id.tvAP_category);
        tvAP_category.setText(resources.getString(R.string.strPartner1));
        TextView tvAP_company = popupDialogView.findViewById(R.id.tvAP_company);
        tvAP_company.setText(resources.getString(R.string.strPartner2));
        TextView tvAP_email = popupDialogView.findViewById(R.id.tvAP_email);
        tvAP_email.setText(resources.getString(R.string.strPartner3));
        TextView tvAP_reason = popupDialogView.findViewById(R.id.tvAP_reason);
        tvAP_reason.setText(resources.getString(R.string.strPartner4));

        editName = (EditText) popupDialogView.findViewById(R.id.edit_name);
        editEmail = (EditText) popupDialogView.findViewById(R.id.edit_email);
        editReason = (EditText) popupDialogView.findViewById(R.id.edit_reason);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        btnSend.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String textReason = editReason.getText().toString();
                    String textName = editName.getText().toString();
                    String textEmail = editEmail.getText().toString();
                    RequestParams params = new RequestParams();
                    params.put("alliance_name", textName);
                    params.put("alliance_email", textEmail);
                    params.put("alliance_cn", textReason);
                    params.put("alliance_type", partnerCategory);


                    final String URL = "/insertAlliance";

                    HttpClient.post(URL, params, new AsyncHttpResponseHandler() {
                        @Override
                        public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                            String response;

                            try {
                                response = new String(responseBody);
                                showSnackBar(resources.getString(R.string.AllianceSuccess));
                                return;

                            } catch (Exception e) {
                                Log.e("Error", "insertData() with URL: " + URL + " " + R.string.data_not_available);
                                showSnackBar(resources.getString(R.string.strErrwithCode)+e.toString());
                                e.printStackTrace();
                                return;
                            }
                        }

                        @Override
                        public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                            Log.e("Error", "insertData() with URL: " + URL + " " + R.string.server_not_available);
                            showSnackBar(resources.getString(R.string.strErrwithCode)+error.toString());
                            error.printStackTrace();
                        }
                    });
                    dialog.dismiss();
                }
            });

        btnAlertCancel.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });

        dialog.show();
    }
    public void btnALogout() {
            if (!Session.getCurrentSession().isClosed()) {
                UserManagement.requestLogout(new LogoutResponseCallback() {
                    @Override
                    public void onCompleteLogout() {
                        SharedPreferences.Editor editor = prefLogin.edit();
                        editor.putLong("profileEditDate", 0);
                        editor.putString("_id", "0");
                        editor.putString("login_method", "0");
                        editor.putString("name", "0");
                        editor.putString("profile_img_url", "0");
                        editor.putString("email", "fail");
                        editor.putInt("EventAlarm", 1);
                        editor.putInt("OtherAlarm", 0);
                        editor.apply();

                        Intent i = new Intent(getActivity(), LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(i);
                        getActivity().finish();
                    }
                });
            } else if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                FirebaseAuth.getInstance().signOut();
                SharedPreferences.Editor editor = prefLogin.edit();
                editor.putLong("profileEditDate", 0);
                editor.putString("_id", "0");
                editor.putString("login_method", "0");
                editor.putString("name", "0");
                editor.putString("profile_img_url", "0");
                editor.putString("email", "fail");
                editor.putInt("EventAlarm", 1);
                editor.putInt("OtherAlarm", 0);
                editor.apply();

                Intent i = new Intent(getActivity(), LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                getActivity().finish();
            } else if (AccessToken.getCurrentAccessToken() != null) {
                SharedPreferences.Editor editor = prefLogin.edit();
                editor.putLong("profileEditDate", 0);
                editor.putString("_id", "0");
                editor.putString("login_method", "0");
                editor.putString("name", "0");
                editor.putString("profile_img_url", "0");
                editor.putString("email", "fail");
                editor.putInt("EventAlarm", 1);
                editor.putInt("OtherAlarm", 0);
                editor.apply();

                LoginManager.getInstance().logOut();
                Intent i = new Intent(getActivity(), LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                getActivity().finish();
            } else {
                Toast.makeText(getActivity(), getString(R.string.strLoginedErr), Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = prefLogin.edit();
                editor.putLong("profileEditDate", 0);
                editor.putString("_id", "0");
                editor.putString("login_method", "0");
                editor.putString("name", "0");
                editor.putString("profile_img_url", "0");
                editor.putString("email", "fail");
                editor.putInt("EventAlarm", 1);
                editor.putInt("OtherAlarm", 0);
                editor.apply();

                Intent i = new Intent(getActivity(), LoginActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                getActivity().finish();
            }
    }
    public void traceClickListener(View v) {
        Intent intent = new Intent(getActivity(), TraceActivity.class);
        switch (v.getId()) {
            case R.id.btnA_like:
                intent.putExtra("trace", 1);
                break;
            case R.id.btnA_writing:
                intent.putExtra("trace", 2);
                break;
            case R.id.btnA_comment:
                intent.putExtra("trace", 3);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefLogin = this.getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        binding.tvAName.setText(prefLogin.getString("name", "로그인 실패"));

        Context context2 = LocaleHelper.setLocale(getActivity());
        resources = context2.getResources();

        binding.textAccountTitle.setText(resources.getString(R.string.AccountTitle));
        binding.buttontxAlliacne.setText(resources.getString(R.string.AllianceTitle));
        binding.buttontxBasket.setText(resources.getString(R.string.BasketTitle));
        binding.buttontxComment.setText(resources.getString(R.string.MycommentTitle));
        binding.buttontxHowtouse.setText(resources.getString(R.string.HowtouseTitle));
        binding.buttontxLike.setText(resources.getString(R.string.LikeTitle));
        binding.buttontxLogout.setText(resources.getString(R.string.LogoutTitle));
        binding.buttontxWriting.setText(resources.getString(R.string.MywritingTitle));
        binding.tvAAddress.setText(resources.getString(R.string.AccountNoAddress));

        binding.textSubtitle.setText(resources.getString(R.string.AccountSub));
        binding.subbuttonAlliance.setText(resources.getString(R.string.AllianceSub));
        binding.subbuttonBasket.setText(resources.getString(R.string.BasketSub));
        binding.subbuttonComment.setText(resources.getString(R.string.MycommentSub));
        binding.subbuttonHowtouse.setText(resources.getString(R.string.HowtouseSub));
        binding.subbuttonLike.setText(resources.getString(R.string.LikeSub));
        binding.subbuttonLogout.setText(resources.getString(R.string.LogoutSub));
        binding.subbuttonWriting.setText(resources.getString(R.string.MywritingSub));
    }
}
