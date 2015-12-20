package com.todorovh.helpinghands;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter {
    private Context context;
    private int[] idAndTextStrings= {
            R.string.login,
            R.string.register,
            R.string.events
    };

    public MyAdapter (Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return idAndTextStrings.length;
    }

    @Override
    public Object getItem(int position) {
        return idAndTextStrings[position];
    }

    @Override
    public long getItemId(int position) {
        return idAndTextStrings[position];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row;
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.custom_row, parent, false);
        } else {
            row = convertView;
        }
        TextView titleTextView = (TextView) row.findViewById(R.id.TextView1);
        titleTextView.setText(idAndTextStrings[position]);
        return row;
    }
}
