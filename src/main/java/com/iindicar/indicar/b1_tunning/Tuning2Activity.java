package com.iindicar.indicar.b1_tunning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.databinding.ObservableInt;
import android.databinding.ObservableList;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;

import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.UnityPlayerActivity;
import com.iindicar.indicar.data.dao.BaseDao;
import com.iindicar.indicar.data.dao.CarDataDao;
import com.iindicar.indicar.data.vo.CarDataVO;
import com.iindicar.indicar.databinding.ActivityTuning2Binding;
import com.iindicar.indicar.databinding.LayoutCarListItemBinding;
import com.iindicar.indicar.utils.DialogUtil;
import com.iindicar.indicar.utils.LocaleHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Tuning2Activity extends BaseActivity<ActivityTuning2Binding> {
    // 차량 로고 관련
    private static final int NUM_OF_CAR_LOGO = 8;
    private static final int[] CAR_LOGO_IMAGE_LIST = {
            R.drawable.tuninglogo0,
            R.drawable.tuninglogo1,
            R.drawable.tuninglogo2,
            R.drawable.tuninglogo3,
            R.drawable.tuninglogo4,
            R.drawable.tuninglogo5,
            R.drawable.tuninglogo6,
            R.drawable.tuninglogo7,
    };
    private static final String[] CAR_LOGO_NAME_LIST = {
            "ALL",
            "HYUNDAI",
            "KIA",
            "VOLKSWAGEN",
            "CHEVROLET",
            "AUDI",
            "BMW",
            "BENZ"
    };

    Resources resources;

    public final ObservableBoolean isLoading = new ObservableBoolean(false); // 프로그래스 바 바인딩
    public final ObservableList<CarDataVO> selectedCarList = new ObservableArrayList<>(); // 선택한 회사(로고)의 차량 리스트 바인딩
    public final ObservableField<String> searchInput = new ObservableField<>(); // 검색어 입력 텍스트 바인딩

    HashMap<String, List<CarDataVO>> carListMap = new HashMap<>(); // 자동차 리스트

    private CarDataDao carDao = new CarDataDao();
    private CarListAdapter adapter;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding.setActivity(this);

        Context carListContext = LocaleHelper.setLocale(getApplicationContext());
        resources = carListContext.getResources();

        initView();
        getCarList();
    }

    private void initView() {
        // 액션바 뒤로가기 버튼
        actionBarBinding.buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //언어 설정
        binding.textEmpty.setText(resources.getString(R.string.strNoCar));
        binding.searchText.setHint(resources.getString(R.string.tuning2Hint));

        // 차량 로고 이미지 탭 뷰
        // 선택 된 로고 : 크기 키우고 차량 리스트 갱신
        // 나머지 : 크기 작게
        binding.tabLogo.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(final TabLayout.Tab tab) {
                ImageView imageView = tab.getCustomView().findViewById(R.id.logo_image);
                if(imageView != null){
                    int width = imageView.getLayoutParams().width;
                    imageView.setPadding(0, 0, 0, 0);
                    ColorMatrix matrix = new ColorMatrix();
                    matrix.setSaturation(0.8F); //0이면 greyscale
                    ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
                    imageView.setColorFilter(cf);
                }
                if(tab.getTag() != null) {
                    getSelectedCarList(tab.getTag().toString());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                ImageView imageView = tab.getCustomView().findViewById(R.id.logo_image);
                if(imageView != null){
                    imageView.setPadding(0, 60, 0, 0);
                    ColorMatrix matrix = new ColorMatrix();
                    matrix.setSaturation(0.3F);                        //0이면 grayscale
                    ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
                    imageView.setColorFilter(cf);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                ImageView imageView = tab.getCustomView().findViewById(R.id.logo_image);
                if(imageView != null){
                    imageView.setPadding(0, 0, 0, 0);
                    ColorMatrix matrix = new ColorMatrix();
                    matrix.setSaturation(0.8F); //0이면 greyscale
                    ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
                    imageView.setColorFilter(cf);
                }
            }
        });
        // 탭에 로고 이미지, 태그(회사명) 등록
        for(int i = 0 ; i < NUM_OF_CAR_LOGO ; i++){
            TabLayout.Tab tab = binding.tabLogo.newTab();
            View tabView = LayoutInflater.from(this).inflate(R.layout.tab_item_car_logo, null);
            ImageView imageView = tabView.findViewById(R.id.logo_image);
            imageView.setImageResource(CAR_LOGO_IMAGE_LIST[i]);
            imageView.setPadding(0, 60, 0, 0);
            ColorMatrix matrix = new ColorMatrix();
            matrix.setSaturation(0.3F); //0이면 greyscale
            ColorMatrixColorFilter cf = new ColorMatrixColorFilter(matrix);
            imageView.setColorFilter(cf);
            tab.setCustomView(tabView);
            tab.setTag(CAR_LOGO_NAME_LIST[i]);
            binding.tabLogo.addTab(tab);
        }

        adapter = new CarListAdapter(this);
        adapter.setItemClickListener(new CarListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startUnity(position);
            }
        });
        binding.gridViewCarList.setAdapter(adapter);
        binding.gridViewCarList.setEmptyView(binding.textEmpty);

        binding.searchText.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String filterText = editable.toString();
                searchInput.set(filterText);
                ((CarListAdapter) binding.gridViewCarList.getAdapter()).getFilter().filter(filterText);
                if(filterText.length() == 0){
                    binding.searchText.clearFocus();
                }
                //기업 로고를 'all brand'로 바꾼다.
                binding.tabLogo.getTabAt(0).select();
            }
        });
    }

    private void startUnity(final int position) {
        // 다이얼로그 먼저 띄우고 실행
        DialogUtil.showDialog(this,
                R.drawable.icon_wifi,
                resources.getString(R.string.strWarningData),
                "",
                0.9, 0.5,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                        Intent intent = new Intent(Tuning2Activity.this, UnityPlayerActivity.class);
                        intent.putExtra("scene","new_scene");
                        intent.putExtra("login_method",prefLogin.getString("login_method",null));
                        intent.putExtra("email",prefLogin.getString("email",null));
                        intent.putExtra("car_name", adapter.getItemList().get(position).getCarDataName());
                    }
                });
    }

    private void getSelectedCarList(String tag){
        selectedCarList.clear();

        if(CAR_LOGO_NAME_LIST[0].equals(tag)) { // 차량 리스트 전체
            for (Map.Entry<String, List<CarDataVO>> entry : carListMap.entrySet()) {
                selectedCarList.addAll(entry.getValue());
            }
        } else { // 회사별 리스트
            if(carListMap.containsKey(tag)) {
                selectedCarList.addAll(carListMap.get(tag));
            }
        }

        if(isLoading.get()) {
            ((CarListAdapter) binding.gridViewCarList.getAdapter()).setItemAllList(selectedCarList);
            isLoading.set(false);
        }
    }

    // 서버에서 차량 리스트 받아오기
    private void getCarList(){
        isLoading.set(true);

        carDao.getDataList(null, new BaseDao.LoadDataListCallBack() {
            @Override
            public void onDataListLoaded(List list) {
                List<CarDataVO> carList = list;
                for(int i = 0 ; i < list.size() ; i++){
                    String company = carList.get(i).getCarDataCompany();
                    if(!carListMap.containsKey(company)){
                        carListMap.put(company, new ArrayList<CarDataVO>());
                    }
                    carListMap.get(carList.get(i).getCarDataCompany()).add(carList.get(i));
                }
                getSelectedCarList(CAR_LOGO_NAME_LIST[0]);
            }

            @Override
            public void onDataNotAvailable() {
                isLoading.set(false);
                showSnackBar(resources.getString(R.string.strCarLoadErr));
            }
        });
    }

    public static class CarListAdapter extends BaseAdapter implements Filterable {
        private Context context;
        private List<CarDataVO> itemAllList;
        private List<CarDataVO> itemList;
        private OnItemClickListener itemClickListener;
        private Filter listFilter;

        public CarListAdapter(Context context){
            this.context = context;
        }

        public List<CarDataVO> getItemAllList() {
            return itemAllList;
        }

        public void setItemAllList(List<CarDataVO> itemAllList) {
            this.itemAllList = itemAllList;
        }

        @Override
        public int getCount() {
            return itemList == null ? 0 : itemList.size();
        }

        @Override
        public Object getItem(int i) {
            return itemList.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        public List<CarDataVO> getItemList() {
            return itemList;
        }

        public void updateList(List<CarDataVO> items){
            this.itemList = items;
            notifyDataSetChanged();
        }

        @Override
        public View getView(final int position, View view, ViewGroup viewGroup) {
            CarDataVO item = (CarDataVO) getItem(position);
            LayoutCarListItemBinding binding;

            if(view == null){
                view = LayoutInflater.from(context).inflate(R.layout.layout_car_list_item, null);
            }

            binding = DataBindingUtil.bind(view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(itemClickListener != null){
                        itemClickListener.onItemClick(view, position);
                    }
                }
            });

            binding.setItem(item);

            return view;
        }

        @Override
        public Filter getFilter() {
            if (listFilter == null) {
                listFilter = new CarListAdapter.ListFilter();
            }
            return listFilter;
        }

        private class ListFilter extends Filter {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();

                if (constraint == null || constraint.length() == 0) {
                    results.values = itemAllList;
                    results.count = itemAllList.size();
                } else {
                    ArrayList<CarDataVO> resultList = new ArrayList<>();

                    for (CarDataVO item : itemAllList) {
                        if (item.getCarDataName().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                                item.getCarDataCompany().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                                item.getCarDataNameKor().contains(constraint.toString()) ||
                                item.getCarDataCompanyKor().contains(constraint.toString())){
                            resultList.add(item);
                        }
                    }

                    results.values = resultList;
                    results.count = resultList.size();
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                // update listview by filtered data list.
                itemList = (List<CarDataVO>) results.values;

                // notify
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        }

        public void setItemClickListener(OnItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        public interface OnItemClickListener{

            void onItemClick(View view, int position);
        }
    }

    @BindingAdapter(value = {"items"})
    public static void bindItemList(GridView gridView, List<CarDataVO> items){
        CarListAdapter adapter = (CarListAdapter)gridView.getAdapter();
        if(adapter != null){
            adapter.updateList(items);
        }
    }
}
