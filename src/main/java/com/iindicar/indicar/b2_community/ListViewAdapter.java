package com.iindicar.indicar.b2_community;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.iindicar.indicar.ListViewItem;
import com.iindicar.indicar.R;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter implements Filterable {


    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();
    private ArrayList<ListViewItem> filteredItemList = listViewItemList;
    Filter listFilter;

    // ListViewAdapter의 생성자
    public ListViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return filteredItemList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_listview, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView titleTextView1 = (TextView) convertView.findViewById(R.id.text_name1);
        TextView titleTextView2 = (TextView) convertView.findViewById(R.id.text_name2);
        TextView titleTextView3 = (TextView) convertView.findViewById(R.id.text_name3);

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ListViewItem listViewItem = filteredItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        titleTextView1.setText(listViewItem.get1());
        titleTextView2.setText(listViewItem.get2());
        titleTextView3.setText(listViewItem.get3());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position);
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String tv1, String tv2, String tv3) {
        ListViewItem item = new ListViewItem();

        item.set1(tv1);
        item.set2(tv2);
        item.set3(tv3);

        listViewItemList.add(item);
    }


    @Override
    public Filter getFilter() {
        if (listFilter == null) {
            listFilter = new ListFilter();
        }
        return listFilter;

    }

    private class ListFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                results.values = listViewItemList;
                results.count = listViewItemList.size();
            } else {
                ArrayList<ListViewItem> itemList = new ArrayList<ListViewItem>();

                for (ListViewItem item : listViewItemList) {
                    if (item.get1().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                            item.get2().toUpperCase().contains(constraint.toString().toUpperCase()) ||
                            item.get3().toUpperCase().contains(constraint.toString().toUpperCase())) {
                        itemList.add(item);
                    }
                }

                results.values = itemList;
                results.count = itemList.size();
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            // update listview by filtered data list.
            filteredItemList = (ArrayList<ListViewItem>) results.values;

            // notify
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }
    }


}
