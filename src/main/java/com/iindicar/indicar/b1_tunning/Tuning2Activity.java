package com.iindicar.indicar.b1_tunning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.databinding.ObservableInt;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.UnityPlayerActivity;
import com.iindicar.indicar.databinding.ActivityTuning2Binding;
import com.iindicar.indicar.utils.CarData;
import com.iindicar.indicar.utils.CarData2;
import com.iindicar.indicar.utils.ConstClass;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class Tuning2Activity extends BaseActivity<ActivityTuning2Binding> {
    //차 버튼 클릭 시 나오는 팝업창에 필요한 요소들을 선언.
    private PopupWindow pwindow;
    private int mWidthPixels, mHeightPixels;
    private Button btnPopAgree;
    private Button btnPopCancel;

    //이미지뷰 배열 및 인덱스
    List<ImageView> btnCar = new ArrayList<>();
    int logoIndex = 0;
    int searchPlag = 0; //0은 검색off, 1은 검색on
    int carIndex = 0;

    //자동차 정보 관련 변수
    //브랜드별 List는 좌,우 버튼 액션 처리용이고, 차량 이름과 인덱스만 들어간 List는 검색용이다.
    List<CarData> AudiData = new ArrayList<>();
    List<CarData> BenzData = new ArrayList<>();
    List<CarData> BmwData = new ArrayList<>();
    List<CarData> CheData = new ArrayList<>();
    List<CarData> FoxData = new ArrayList<>();
    List<CarData> HyunData = new ArrayList<>();
    List<CarData> KiaData = new ArrayList<>();
    List<CarData2> CarAllData = new ArrayList<>();
    List<String> CarImgAdd = new ArrayList<>();
    List<String> CarSearchName = new ArrayList<>();
    LinearLayout llTuningCar;
    int carNum = 0; int width = 250; int height = 120;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //팝업창을 위한 선언부+작업부.
        WindowManager w = getWindowManager();
        Display d = w.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        d.getMetrics(metrics);
        mWidthPixels = metrics.widthPixels;
        mHeightPixels = metrics.heightPixels;

        if (Build.VERSION.SDK_INT >= 14 && Build.VERSION.SDK_INT < 17) {
            try {
                mWidthPixels = (Integer) Display.class.getMethod("getRawWidth").invoke(d);
                mHeightPixels = (Integer) Display.class.getMethod("getRawHeight").invoke(d);
            } catch (Exception e) {
            }
        } else if (Build.VERSION.SDK_INT >= 17) {
            try {
                Point realSize = new Point();
                Display.class.getMethod("getRealSize", Point.class).invoke(d, realSize);
                mWidthPixels = realSize.x;
                mHeightPixels = realSize.y;
            } catch (Exception e) {
            }
        }

        //이미지뷰 크기 구하기: 화면의 가로 사이즈*0.44556, 이미지뷰 가로 크기*0.565625
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = (int)(size.x*0.44556);
        height = (int)(width*0.565625);

        //차 데이터 받아오기
        try {
            String json = new carDataGet().execute().get();
            carDataGroup(json);
            carNum = CarImgAdd.size(); //저장되어 있는 자동차의 갯수 구하기
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), ConstClass.strCarLoadErr,Toast.LENGTH_SHORT).show();
        }

        binding.ivTLogo.setImageResource(R.drawable.tuninglogo0);
        llTuningCar = new LinearLayout(this);
        llTuningCar.setMinimumHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        llTuningCar.setMinimumWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        llTuningCar.setOrientation(LinearLayout.HORIZONTAL);
        for(int i=0;i<carNum;i++) {
            btnCar.add(new ImageView(getApplicationContext()));
        }

        for(int i=0;i<2;i++) {
            LinearLayout linear = new LinearLayout(getApplicationContext());
            linear.setOrientation(LinearLayout.VERTICAL);
            linear.setTag(i);
            linear.setMinimumWidth(width);
            linear.setMinimumHeight(height);
            llTuningCar.addView(linear);

            if(i==0) {
                for (int j=0; j<(carNum+1)/2; j++) {
                    Glide.with(getApplicationContext()).load(CarImgAdd.get(j*2+i)).override(width, height).into(btnCar.get(j*2+i));
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width,height);
                    btnCar.get(j*2+i).setLayoutParams(params);
                    linear.addView(btnCar.get(j*2+i));
                    btnCar.get(j*2+i).setClickable(true);
                    btnCar.get(j*2+i).setOnClickListener(btnCar_listener);
                    btnCar.get(j*2+i).setId(j*2+i);
                }
            } else {
                for (int j=0; j<carNum/2; j++) {
                    Glide.with(getApplicationContext()).load(CarImgAdd.get(j*2+i)).override(width, height).into(btnCar.get(j*2+i));
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width,height);
                    btnCar.get(j*2+i).setLayoutParams(params);
                    linear.addView(btnCar.get(j*2+i));
                    btnCar.get(j*2+i).setClickable(true);
                    btnCar.get(j*2+i).setOnClickListener(btnCar_listener);
                    btnCar.get(j*2+i).setId(j*2+i);
                }
            }
        }
        binding.svTCar.addView(llTuningCar);

        //차량 검색 이벤트
        binding.btnTSearch.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.ivTLogo.setImageResource(R.drawable.tuninglogo0);
                searchPlag = 1;
                CarSearchName.clear();
                logoIndex = 0;
                for(int i=0;i<btnCar.size();i++) {
                    btnCar.get(i).setImageResource(0);
                    btnCar.get(i).setVisibility(View.GONE);
                }

                String strSearch = binding.etTSearch.getText().toString().toLowerCase();
                int indexbt = 0;

                if(strSearch.length() == 0) {
                    indexbt = 0;
                    searchPlag = 0;
                    for(int i=0;i<CarAllData.size();i++) {
                        btnCar.get(i).setVisibility(View.VISIBLE);
                        Glide.with(getApplicationContext()).load(CarImgAdd.get(i)).override(width,height).into(btnCar.get(i));
                    }
                } else {
                    for(int i=0;i<CarAllData.size();i++) {
                        if(CarAllData.get(i).carDataName.toLowerCase().contains(strSearch.toLowerCase()) || CarAllData.get(i).carDataCompany.toLowerCase().contains(strSearch.toLowerCase())) {
                            btnCar.get(indexbt).setVisibility(View.VISIBLE);
                            Glide.with(getApplicationContext()).load(CarImgAdd.get(i)).override(width,height).into(btnCar.get(indexbt));
                            CarSearchName.add(CarAllData.get(i).carDataName);
                            indexbt++;
                        } else if(CarAllData.get(i).carDataNameKor.contains(strSearch) || CarAllData.get(i).carDataCompanyKor.contains(strSearch)) {
                            btnCar.get(indexbt).setVisibility(View.VISIBLE);
                            Glide.with(getApplicationContext()).load(CarImgAdd.get(i)).override(width,height).into(btnCar.get(indexbt));
                            CarSearchName.add(CarAllData.get(i).carDataName);
                            indexbt++;
                        }
                    }
                }

                if(indexbt == 0 && searchPlag == 1) {
                    Toast.makeText(getApplicationContext(),ConstClass.strNoSearchResultErr,Toast.LENGTH_SHORT).show();
                }
            }
        });

        //버튼 이벤트 - Main
        binding.btnTLeft.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.ivTLogo.setImageResource(0);
                searchPlag = 0;
                //binding.etTSearch.setText("");
                for(int i=0;i<btnCar.size();i++) {
                    btnCar.get(i).setImageResource(0);
                    btnCar.get(i).setVisibility(View.GONE);
                }

                switch (logoIndex) {
                    case 0:
                        //Benz
                        logoIndex = 7;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo7);
                        if(BenzData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<BenzData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(BenzData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 1:
                        //All
                        logoIndex = 0;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo0);
                        if(CarAllData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<CarAllData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(CarAllData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 2:
                        //Hyundai
                        logoIndex = 1;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo1);
                        if(HyunData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<HyunData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(HyunData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 3:
                        //Kia
                        logoIndex = 2;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo2);
                        if(KiaData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<KiaData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(KiaData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 4:
                        //FoxData
                        logoIndex = 3;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo3);
                        if(FoxData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<FoxData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(FoxData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 5:
                        //CHEVROLET
                        logoIndex = 4;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo4);
                        if(CheData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<CheData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(CheData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 6:
                        //Audi
                        logoIndex = 5;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo5);
                        if(AudiData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<AudiData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(AudiData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 7:
                        //BMW
                        logoIndex = 6;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo6);
                        if(BmwData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<BmwData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(BmwData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                }
            }
        });

        binding.btnTRight.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.ivTLogo.setImageResource(0);
                searchPlag = 0;
                //binding.etTSearch.setText("");
                for(int i=0;i<btnCar.size();i++) {
                    btnCar.get(i).setImageResource(0);
                    btnCar.get(i).setVisibility(View.GONE);
                }

                switch (logoIndex) {
                    case 0:
                        //HYUNDAI
                        logoIndex = 1;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo1);
                        if(HyunData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<HyunData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(HyunData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 1:
                        //KIA
                        logoIndex = 2;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo2);
                        if(KiaData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<KiaData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(KiaData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 2:
                        //FOX
                        logoIndex = 3;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo3);
                        if(FoxData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<FoxData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(FoxData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 3:
                        //CHE
                        logoIndex = 4;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo4);
                        if(CheData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<CheData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(CheData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 4:
                        //AUDI
                        logoIndex = 5;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo5);
                        if(AudiData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<AudiData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(AudiData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 5:
                        //BMW
                        logoIndex = 6;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo6);
                        if(BmwData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<BmwData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(BmwData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 6:
                        //BENZ
                        logoIndex = 7;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo7);
                        if(BenzData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<BenzData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(BenzData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                    case 7:
                        //All
                        logoIndex = 0;
                        binding.ivTLogo.setImageResource(R.drawable.tuninglogo0);
                        if(CarAllData.size() == 0) {
                            Toast.makeText(getApplicationContext(),ConstClass.strNoCar,Toast.LENGTH_SHORT).show();
                        } else {
                            for(int i=0;i<CarAllData.size();i++) {
                                Glide.with(getApplicationContext()).load(CarImgAdd.get(CarAllData.get(i).carDataImgIndex)).override(width,height).into(btnCar.get(i));
                                btnCar.get(i).setVisibility(View.VISIBLE);
                            }
                        }
                        break;
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_tuning2;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        centerImageId.set(R.drawable.logo_tuning);
        leftImageId.set(R.drawable.btn_back);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    //차량 데이터 받아오는 부분.
    //1: 웹에서 차량 데이터(json)를 string으로 받아온다.
    public class carDataGet extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            binding.pbTLoading.setVisibility(View.VISIBLE);
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            StringBuffer response = new StringBuffer();
            try {
                String apiURL = ConstClass.getCarLink;
                URL url = new URL(apiURL);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("GET");
                int responseCode = conn.getResponseCode();
                BufferedReader br;
                if(responseCode == 200) {
                    br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                }
                String inputLine;
                while((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }
                br.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return response.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }

    //2. 받아온 string을 분류한다.
    private void carDataGroup(String strData) {
        try {
            JSONArray jArray1 = new JSONArray(strData);
            int length = jArray1.length();

            for (int i = 0; i < length; i++) {
                JSONObject jObject1 = jArray1.getJSONObject(i);
                String carCompany = jObject1.getString(ConstClass.company);
                CarImgAdd.add(i, ConstClass.http + jObject1.getString(ConstClass.img_url));
                CarData tmpData = new CarData(jObject1.getString(ConstClass.car_name), i);
                CarData2 tmpData2 = new CarData2(tmpData.carDataName,jObject1.getString(ConstClass.car_name_kor), i, carCompany,jObject1.getString(ConstClass.company_kor));
                CarAllData.add(tmpData2);
                if (carCompany.equals(ConstClass.audi)) {
                    AudiData.add(tmpData);
                } else if (carCompany.equals(ConstClass.benz)) {
                    BenzData.add(tmpData);
                } else if (carCompany.equals(ConstClass.bmw)) {
                    BmwData.add(tmpData);
                } else if (carCompany.equals(ConstClass.chevrolet)) {
                    CheData.add(tmpData);
                } else if (carCompany.equals(ConstClass.hyundai)) {
                    HyunData.add(tmpData);
                } else if (carCompany.equals(ConstClass.kia)) {
                    KiaData.add(tmpData);
                } else if (carCompany.equals(ConstClass.Volkswagen)) {
                    FoxData.add(tmpData);
                }
            }
            binding.pbTLoading.setVisibility(View.GONE);
        } catch (Exception e) {
            binding.pbTLoading.setVisibility(View.GONE);
            Toast.makeText(getApplicationContext(), ConstClass.strCarLoadErr, Toast.LENGTH_SHORT).show();
        }
    }

    //자동차 버튼을 클릭했을 때.
    private ImageView.OnClickListener btnCar_listener = new ImageView.OnClickListener() {
        public void onClick(View v) {
            carIndex = v.getId();
            initiatePopupWindow();
        }
    };

    //팝업창을 보여주는 함수.
    private void initiatePopupWindow() {
        LayoutInflater inflater = (LayoutInflater) Tuning2Activity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        try {
            int popupWidth = (int)Math.round(mWidthPixels*0.8);
            int popupHeight = (int)Math.round(mHeightPixels*0.5);

            View layout = inflater. inflate(R.layout.alert_download, (ViewGroup) findViewById(R.id.popup_download));
            pwindow = new PopupWindow(layout, popupWidth, popupHeight, true);
            pwindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
            btnPopAgree = layout.findViewById(R.id.btnAlertDownload);
            /*RelativeLayout.LayoutParams agreeParams = new RelativeLayout.LayoutParams((int)Math.round(popupWidth*0.33),(int)Math.round(popupHeight*0.174));
            agreeParams.setMargins((int)Math.round(popupWidth*0.109),(int)Math.round(popupHeight*0.735),0,0);
            btnPopAgree.setLayoutParams(agreeParams);*/
            btnPopCancel = layout.findViewById(R.id.btnAlertDownCancel);
            /*RelativeLayout.LayoutParams cancelParams = new RelativeLayout.LayoutParams((int)Math.round(popupWidth*0.33),(int)Math.round(popupHeight*0.174));
            cancelParams.setMargins((int)Math.round(popupWidth*0.552),(int)Math.round(popupHeight*0.735),0,0);
            btnPopCancel.setLayoutParams(cancelParams);*/
            pwindow.setTouchable(true);

            btnPopAgree.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);

                    Intent intent = new Intent(Tuning2Activity.this, UnityPlayerActivity.class);
                    intent.putExtra("scene","new_scene");
                    intent.putExtra("login_method",prefLogin.getString("login_method",null));
                    intent.putExtra("email",prefLogin.getString("email",null));
                    pwindow.dismiss();

                    if(searchPlag == 0) {
                        switch (logoIndex) {
                            case 0:
                                intent.putExtra("car_name", CarAllData.get(carIndex).carDataName);
                                startActivity(intent);
                                break;
                            case 1:
                                intent.putExtra("car_name", HyunData.get(carIndex).carDataName);
                                startActivity(intent);
                                break;
                            case 2:
                                intent.putExtra("car_name", KiaData.get(carIndex).carDataName);
                                startActivity(intent);
                                break;
                            case 3:
                                intent.putExtra("car_name", FoxData.get(carIndex).carDataName);
                                startActivity(intent);
                                break;
                            case 4:
                                intent.putExtra("car_name", CheData.get(carIndex).carDataName);
                                startActivity(intent);
                                break;
                            case 5:
                                intent.putExtra("car_name", AudiData.get(carIndex).carDataName);
                                startActivity(intent);
                                break;
                            case 6:
                                intent.putExtra("car_name", BmwData.get(carIndex).carDataName);
                                startActivity(intent);
                                break;
                            case 7:
                                intent.putExtra("car_name", BenzData.get(carIndex).carDataName);
                                startActivity(intent);
                                break;
                        }
                    } else if(searchPlag == 1) {
                        intent.putExtra("car_name", CarSearchName.get(carIndex));
                        startActivity(intent);
                    }
                }
            });
            btnPopCancel.setOnClickListener(new Button.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pwindow.dismiss();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
