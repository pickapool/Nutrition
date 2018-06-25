package com.example.lloyd.healthnutritiontool;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lloyd on 12/20/2017.
 */

public class list_adapter extends BaseAdapter implements Filterable {

    private Context context;
    private List<list_stats> list_statsList;
    private List<list_stats> mStringFilterList;
    ValueFilter valueFilter;

    public list_adapter(Context context, List<list_stats> list_statsList) {
        this.context = context;
        this.list_statsList = list_statsList;
        mStringFilterList = list_statsList;
    }


    @Override
    public int getCount() {
        return list_statsList.size();
    }

    @Override
    public Object getItem(int position) {
        return list_statsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

    View v = View.inflate(context, R.layout.list_status, null);
    TextView dorrr = v.findViewById(R.id.dorr);
    TextView agee = v.findViewById(R.id.agee);
    TextView hh = v.findViewById(R.id.h);
    TextView ww = v.findViewById(R.id.w);
    TextView mm = v.findViewById(R.id.m);
    TextView s1 = v.findViewById(R.id.stats1);
    TextView s2 = v.findViewById(R.id.stats2);
    TextView s3 = v.findViewById(R.id.stats3);
    TextView s4 = v.findViewById(R.id.stats4);
    TextView rep = v.findViewById(R.id.bns_name);
    list_stats getrow = list_statsList.get(position);

    dorrr.setText("Date of Recording: " + String.valueOf(getrow.getDorr()));
    agee.setText("Age: " + String.valueOf(getrow.getAgee()));
    hh.setText("Height: " + String.valueOf(getrow.getHhh()));
    ww.setText("Weight: " + String.valueOf(getrow.getWww()));
    mm.setText("MUAC: " + String.valueOf(getrow.getMmm()));
    s1.setText(String.valueOf(getrow.getSs1()));
    s2.setText(String.valueOf(getrow.getSs2()));
    s3.setText(String.valueOf(getrow.getSs3()));
    s4.setText(String.valueOf(getrow.getSs4()));
    rep.setText("Representative: "+String.valueOf(getrow.getRepresentaive()));
    return v;

    }


    @Override
    public Filter getFilter() {
        if (valueFilter == null) {
            valueFilter = new ValueFilter();
        }
        return valueFilter;
    }
    private class ValueFilter extends Filter{

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            if (constraint != null && constraint.length() > 0) {
                ArrayList<list_stats> filterList = new ArrayList<list_stats>();
                for (int i = 0; i < mStringFilterList.size(); i++) {
                    if ((mStringFilterList.get(i).getDorr().toUpperCase())
                            .contains(constraint.toString().toUpperCase())) {

                        list_stats bean = new list_stats(mStringFilterList.get(i)
                                .getRepresentaive(),mStringFilterList.get(i)
                                .getDorr(), mStringFilterList.get(i)
                                .getAgee(),mStringFilterList.get(i)
                                .getHhh(),mStringFilterList.get(i)
                                .getWww(),mStringFilterList.get(i)
                                .getMmm(),mStringFilterList.get(i)
                                .getSs1(),mStringFilterList.get(i)
                                .getSs2(),mStringFilterList.get(i)
                                .getSs3(),mStringFilterList.get(i)
                                .getSs4());
                        filterList.add(bean);
                    }
                }
                results.count = filterList.size();
                results.values = filterList;
            } else {
                results.count = mStringFilterList.size();
                results.values = mStringFilterList;
            }
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            list_statsList = (ArrayList<list_stats>) results.values;
            notifyDataSetChanged();
        }
    }
}
