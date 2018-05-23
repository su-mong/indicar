package com.iindicar.indicar.b2_community;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.ObservableInt;
import android.os.Bundle;
import android.util.Log;

import com.iindicar.indicar.BaseActivity;
import com.iindicar.indicar.R;
import com.iindicar.indicar.databinding.CarFilterActivityBinding;
import com.iindicar.indicar.utils.CarDB;

public class CarFilterActivity extends BaseActivity<CarFilterActivityBinding> {

    CarDB carDB;
    SQLiteDatabase db;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.enter_no_anim, R.anim.exit_no_anim);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        carDB = new CarDB(getApplicationContext(), "carDB", null, 1);
        db = carDB.getReadableDatabase();
        String searchWord = "LS";


        ListViewAdapter firstAdapter = new ListViewAdapter();
        binding.listView.setAdapter(firstAdapter);
        String sql1 = "SELECT specName,parentName FROM carDB WHERE level=1";
        String sql2 = "SELECT specName,parentName FROM carDB WHERE level=2";
        String sql3 = "SELECT specName,parentName FROM carDB WHERE level=3";
        Cursor cursor1 = db.rawQuery(sql1, null);
        Cursor cursor2 = db.rawQuery(sql2, null);
        Cursor cursor3 = db.rawQuery(sql3, null);
        String tmp1;
        String tmp2;
        String tmp3;
        while (cursor1.moveToNext()) {
            tmp1 = cursor1.getString(0);
            Log.d("ddf cursor1", tmp1);
            while (cursor2.moveToNext()) {
                if (tmp1.equals(cursor2.getString(1))) {
                    Log.d("ddf cursor2", cursor2.getString(0));
                    tmp2 = cursor2.getString(0);
                    while (cursor3.moveToNext()) {
                        if (tmp2.equals(cursor3.getString(1))) {
                            Log.d("ddf cursor3", cursor3.getString(0));
                            tmp3 = cursor3.getString(0);
                            firstAdapter.addItem(tmp1, tmp2, tmp3);
                            Log.d("ddf db", tmp1 + " " + tmp2 + " " + tmp3);
                        }
                    }
                    cursor3.moveToFirst();
                }
            }
            cursor2.moveToFirst();
        }
    }




    @Override
    protected int getLayoutId() {
        return R.layout.car_filter_activity;
    }

    @Override
    protected void setActionBarImage(ObservableInt centerImageId, ObservableInt leftImageId) {
        centerImageId.set(R.drawable.logo_community);
        leftImageId.set(R.drawable.btn_back);
    }
//
//    //ExpandableListView에 사용할 Adapter
//    public class FirstAdapter extends BaseExpandableListAdapter {
//        Context context;
//        ArrayList<String> car1stName = new ArrayList<>();
//
//        public class FirstViewHolder {
//            public TextView tvName1;
//        }
//
//        public FirstAdapter(Context context) {
//            this.context = context;
//
//            String sql = "SELECT specName FROM carDB WHERE level=1";
//            Cursor cursor = db.rawQuery(sql, null);
//            while (cursor.moveToNext()) {
//                String tmp = cursor.getString(0);
//
//                car1stName.add(tmp);
//            }
//        }
//
//
//        @Override
//        public int getChildrenCount(int groupPosition) {
//            return 1;
//        }
//
//        @Override
//        public Object getGroup(int groupPosition) {
//            return groupPosition;
//        }
//
//        @Override
//        public int getGroupCount() {
//            return car1stName.size();
//        }
//
//        @Override
//        public long getGroupId(int groupPosition) {
//            return groupPosition;
//        }
//
//        @Override
//        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//            FirstViewHolder viewHolder;
//            if (convertView == null) {
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate(R.layout.item_listview, null);
//
//                viewHolder = new FirstViewHolder();
//                viewHolder.tvName1 = convertView.findViewById(R.id.tvName);
//
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (FirstViewHolder) convertView.getTag();
//            }
//
//            viewHolder.tvName1.setText(car1stName.get(groupPosition));
//            return convertView;
//        }
//
//        @Override
//        public Object getChild(int arg0, int arg1) {
//            return arg1;
//        }
//
//        @Override
//        public long getChildId(int groupPosition, int childPosition) {
//            return childPosition;
//        }
//
//        @Override
//        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//            ArrayList<String> car2ndName = new ArrayList<>();
//            ArrayList<String> car2ndNameTmp = new ArrayList<>();
//            final ArrayList<ArrayList<String>> car3rdName = new ArrayList<>();
//            ArrayList<ArrayList<String>> car3rdNameTmp = new ArrayList<>();
//            String sql2 = "SELECT specName FROM carDB WHERE parentName='" + car1stName.get(groupPosition) + "' AND useChecker=0;";
//            Cursor cursor2 = db.rawQuery(sql2, null);
//            while (cursor2.moveToNext()) {
//                String tmp = cursor2.getString(0);
//                car2ndName.add(tmp);
//                car2ndNameTmp.add(tmp);
//            }
//
//            for (int i = 0; i < car2ndName.size(); i++) {
//                ArrayList<String> tmp3rd = new ArrayList<>();
//                String flagCar = car2ndName.get(i);
//                String sql3 = "SELECT specName FROM carDB WHERE parentName='" + flagCar + "' AND useChecker=0;";
//                Cursor cursor3 = db.rawQuery(sql3, null);
//                while (cursor3.moveToNext()) {
//                    tmp3rd.add(cursor3.getString(0));
//
//                }
//                car3rdName.add(tmp3rd);
//                car3rdNameTmp.add(tmp3rd);
//            }
//
//            SecondLevelExpandableListView secondList = new SecondLevelExpandableListView(context);
//            secondList.setAdapter(new SecondAdapter(context, car2ndName, car3rdName));
//            secondList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
//
//                @Override
//                public boolean onChildClick(ExpandableListView parent, View v,
//                                            int groupPosition, int childPosition, long id) {
//                    Toast.makeText(
//                            getApplicationContext(), "click " + car3rdName.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT)
//                            .show();
//                    Intent resultIntent = new Intent();
//                    resultIntent.putExtra("car_name", car3rdName.get(groupPosition).get(childPosition));
//                    setResult(13, resultIntent);
//                    return false;
//                }
//            });
//            secondList.setGroupIndicator(null);
//            return secondList;
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return true;
//        }
//
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return true;
//        }
//    }
//
//    class SecondLevelExpandableListView extends ExpandableListView {
//        public SecondLevelExpandableListView(Context context) {
//            super(context);
//        }
//
//        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//            //999999 is a size in pixels. ExpandableListView requires a maximum height in order to do measurement calculations.
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(999999, MeasureSpec.AT_MOST);
//            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        }
//    }
//
//    public class SecondAdapter extends BaseExpandableListAdapter {
//        private Context context;
//        private ArrayList<String> car2ndName; // header titles
//        // child data in format of header title, child title
//        private ArrayList<ArrayList<String>> car3rdName;
//
//        public class SecondViewHolder {
//            TextView tvName2;
//        }
//
//        public class ThirdViewHolder {
//            TextView tvName3;
//        }
//
//        public SecondAdapter(Context context, ArrayList<String> car2ndName, ArrayList<ArrayList<String>> car3rdName) {
//            this.context = context;
//            this.car2ndName = car2ndName;
//            this.car3rdName = car3rdName;
//        }
//
//        @Override
//        public Object getGroup(int groupPosition) {
//            return groupPosition;
//        }
//
//        @Override
//        public int getGroupCount() {
//            return car2ndName.size();
//        }
//
//        @Override
//        public long getGroupId(int groupPosition) {
//            return groupPosition;
//        }
//
//
//        @Override
//        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//            SecondViewHolder viewHolder;
//            if (convertView == null) {
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate(R.layout.item2nd, null);
//
//                viewHolder = new SecondViewHolder();
//                viewHolder.tvName2 = convertView.findViewById(R.id.tvName2);
//
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (SecondViewHolder) convertView.getTag();
//            }
//
//            viewHolder.tvName2.setText(car2ndName.get(groupPosition));
//            return convertView;
//        }
//
//        @Override
//        public Object getChild(int groupPosition, int childPosition) {
//            return childPosition;
//        }
//
//        @Override
//        public long getChildId(int groupPosition, int childPosition) {
//            return childPosition;
//        }
//
//        @Override
//        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//            ThirdViewHolder viewHolder;
//            if (convertView == null) {
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate(R.layout.item3rd, null);
//
//                viewHolder = new ThirdViewHolder();
//                viewHolder.tvName3 = convertView.findViewById(R.id.tvName3);
//
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (ThirdViewHolder) convertView.getTag();
//            }
//            final String selected_carname = car3rdName.get(groupPosition).get(childPosition);
//            viewHolder.tvName3.setText(selected_carname);
//
//            return convertView;
//        }
//
//        @Override
//        public int getChildrenCount(int groupPosition) {
//            return car3rdName.get(groupPosition).size();
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return true;
//        }
//
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return true;
//        }
//    }
//
//    //검색 시 사용할 Adapter
//    public class SearchAdapter extends BaseExpandableListAdapter {
//        Context context;
//        ArrayList<String> searchParent = new ArrayList<>();
//        ArrayList<ArrayList<String>> searchResult = new ArrayList<>();
//
//        public class searchParentHolder {
//            public TextView tvSName1;
//        }
//
//        public class searchChildHolder {
//            public TextView tvSName2;
//        }
//
//        public SearchAdapter(Context context, String searchText) {
//            this.context = context;
//
//            //맨 처음 아래쪽 for문의 searchParent.get(i)를 정상적으로 작동시키기 위한 구문.
//            //맨 처음 데이터가 들어올 때, searchParent에는 데이터가 없으므로 searchParent.get(0)를 하면 nullException이 발생하므로 이를 방지하기 위한 것이다.
//            //따라서, searchParent.get(i+1)과 searchResult.get(i)끼리 대응해야 한다.
//            searchParent.add("");
//            String sql = "SELECT * FROM carDB WHERE specName LIKE \"%" + searchText + "%\" AND level == 3";
//            Cursor cursor = db.rawQuery(sql, null);
//
//            while (cursor.moveToNext()) {
//                String carName = cursor.getString(0);
//                String parentName = cursor.getString(2);
//
//                for (int i = 0; i < searchParent.size(); i++) {
//                    if (parentName.equals(searchParent.get(i))) {//부모값을 찾았으면
//                        searchResult.get(i).add(carName);
//                        break;
//                    } else if (i == searchParent.size()) {//searchParent에 해당 parent값이 없으면
//                        searchParent.add(parentName);
//                        searchResult.add(new ArrayList<String>());
//                        searchResult.get(i).add(carName);
//                    }
//                }
//            }
//
//            if (searchResult.size() == 0) {
//                Toast.makeText(getApplicationContext(), "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//        @Override
//        public Object getChild(int arg0, int arg1) {
//            return arg1;
//        }
//
//        @Override
//        public long getChildId(int groupPosition, int childPosition) {
//            return childPosition;
//        }
//
//        @Override
//        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
//            final searchChildHolder viewHolder;
//
//            if (convertView == null) {
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate(R.layout.item_listview2, null);
//
//                viewHolder = new searchChildHolder();
//                viewHolder.tvSName2 = convertView.findViewById(R.id.tvName0);
//
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (searchChildHolder) convertView.getTag();
//            }
//
//            viewHolder.tvSName2.setText(searchResult.get(groupPosition).get(childPosition + 1));
//
//            viewHolder.tvSName2.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(), viewHolder.tvSName2.getText().toString(), Toast.LENGTH_SHORT).show();
//                }
//            });
//
//            return convertView;
//        }
//
//        @Override
//        public int getChildrenCount(int groupPosition) {
//            return (searchResult.get(groupPosition).size() - 1);
//        }
//
//        @Override
//        public Object getGroup(int groupPosition) {
//            return groupPosition;
//        }
//
//        @Override
//        public int getGroupCount() {
//            return searchParent.size() - 1;
//        }
//
//        @Override
//        public long getGroupId(int groupPosition) {
//            return groupPosition;
//        }
//
//        @Override
//        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
//            searchParentHolder viewHolder;
//
//            if (convertView == null) {
//                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                convertView = inflater.inflate(R.layout.item_listview, null);
//
//                viewHolder = new searchParentHolder();
//                viewHolder.tvSName1 = convertView.findViewById(R.id.tvName);
//
//                convertView.setTag(viewHolder);
//            } else {
//                viewHolder = (searchParentHolder) convertView.getTag();
//            }
//
//            viewHolder.tvSName1.setText(searchParent.get(groupPosition + 1));
//            return convertView;
//        }
//
//        @Override
//        public boolean hasStableIds() {
//            return true;
//        }
//
//        @Override
//        public boolean isChildSelectable(int groupPosition, int childPosition) {
//            return true;
//        }
//    }

}
