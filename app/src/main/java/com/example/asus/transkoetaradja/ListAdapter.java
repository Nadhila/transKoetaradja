package com.example.asus.transkoetaradja;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ASUS on 11/3/2016.
 */
public class ListAdapter extends ArrayAdapter<String> {
    int groupid;
    ArrayList<String> records;
    Context context;

    public ListAdapter(Context context, int vg, int id, ArrayList<String> records ) {
        super(context,vg, id, records);
        this.context=context;
        groupid=vg;
        this.records=records;
    }

    public View getView(final int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(groupid, parent, false);

        String[] row_items=records.get(position).split("_");

        TextView t4halte= (TextView) view.findViewById(R.id.output);
        t4halte.setText(row_items[1]);

        return view;
    }
}
