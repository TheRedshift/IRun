package com.example.rahulsoni.irun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

//Adapted with help from https://stackoverflow.com/questions/30386271/simple-listliew-with-2-columns and https://stackoverflow.com/questions/2432951/how-to-display-a-two-column-listview-in-android

public class CustomListRun extends BaseAdapter {


    private ArrayList<Runners> listData;
    private LayoutInflater layoutInflater;
    private Context context;

    public CustomListRun(Context context, ArrayList<Runners> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {

            convertView = layoutInflater.inflate(R.layout.row_item, null);
            holder = new ViewHolder();

            holder.date = convertView.findViewById(R.id.runDate);
            holder.distance = convertView.findViewById(R.id.runDistance);
            holder.time = convertView.findViewById(R.id.runTime);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.date.setText(String.valueOf(listData.get(position).getDate()));
        holder.distance.setText(String.format("%.2f", listData.get(position).getDistance()));
        holder.time.setText(String.valueOf(listData.get(position).getTime()));




        return convertView;
    }

    static class ViewHolder {
        TextView date;
        TextView distance;
        TextView time;
    }

}
