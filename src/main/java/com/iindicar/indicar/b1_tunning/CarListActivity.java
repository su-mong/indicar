package com.iindicar.indicar.b1_tunning;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.UnityPlayerActivity;
import com.iindicar.indicar.data.dao.BaseDao;
import com.iindicar.indicar.data.dao.CarDataDao;
import com.iindicar.indicar.databinding.ActivityCarListBinding;
import com.iindicar.indicar.databinding.LayoutCarListItemBinding;
import com.iindicar.indicar.data.vo.CarDataVO;
import com.iindicar.indicar.utils.ConstClass;
import com.iindicar.indicar.utils.DialogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarListActivity extends BaseActivity<ActivityCarListBinding> {
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
            ConstClass.hyundai,
            ConstClass.kia,
            ConstClass.Volkswagen,
            ConstClass.chevrolet,
            ConstClass.audi,
            ConstClass.bmw,
            ConstClass.benz
    };

    public final ObservableBoolean isLoading = new ObservableBoolean(false); // 프로그래스 바 바인딩
    public final ObservableList<CarDataVO> selectedCarList = new ObservableArrayList<>(); // 선택한 회사(로고)의 차량 리스트 바인딩
    public final ObservableField<String> searchInput = new ObservableField<>(); // 검색어 입력 텍스트 바인딩

    HashMap<String, List<CarDataVO>> carListMap = new HashMap<>(); // 자동차 리스트

    private CarDataDao carDao = new CarDataDao();
    private CarListAdapter adapter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_car_list;
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

        // 차량 검색
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
            }
        });

        binding.searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ((CarListAdapter) binding.gridViewCarList.getAdapter()).getFilter().filter(binding.searchText.getText());
                    binding.searchText.clearFocus();
                    return true;
                }
                return false;
            }
        });
    }

    private void startUnity(final int position) {
        // 다이얼로그 먼저 띄우고 실행
        DialogUtil.showDialog(this,
                R.drawable.icon_wifi,
                "NOTICE\n\n차량을 내려받습니다. 소량의 데이터 통화료가 발생합니다. WIFI 환경에서 진행하는 것을 권장합니다. 한 번 차량을 다운받으시면, 다음부터는 로딩 없이 이용 가능합니다.\n(단, 업데이터가 있을 경우 제외)\n\n앞으로 나오는 모든 이미지와 기타 결과물은 상업적 용도로 사용하실 수 없으며, 그 권리는 자사와 기타 업체들에게 있습니다. 이용을 원하시는 경우, 문의 주시기 바랍니다. 상업적 용도 외에 개인적인 사용은 허가합니다. 감사합니다.",
                "",
                0.9, 0.5,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SharedPreferences prefLogin = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);

                        Intent intent = new Intent(CarListActivity.this, UnityPlayerActivity.class);
                        intent.putExtra("scene","new_scene");
                        intent.putExtra("login_method",prefLogin.getString("login_method",null));
                        intent.putExtra("email",prefLogin.getString("email",null));
                        intent.putExtra("car_name", adapter.getItemList().get(position).getCarDataName());
//                        startActivity(intent);
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
                showSnackBar(ConstClass.strCarLoadErr);
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
