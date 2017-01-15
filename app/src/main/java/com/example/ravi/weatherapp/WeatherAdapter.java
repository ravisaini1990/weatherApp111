package com.example.ravi.weatherapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ravi.weatherapp.model.List;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Ravi on 1/15/2017.
 *  View adapter to bind weather data to view
 */

public class WeatherAdapter extends BaseAdapter{

    private java.util.List<List> moviesList;
    private Context context;

    public WeatherAdapter(Context context, java.util.List<List> moviesList) {
        this.moviesList = moviesList;
        this.context =context;
    }

    @Override
    public int getCount() {
        return moviesList.size();
    }

    @Override
    public Object getItem(int i) {
        return moviesList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.rowitem, null, false);
            holder = new ViewHolder();
            holder.nameTextView = (TextView) convertView.findViewById(R.id.title111);
            holder.icon = (TextView) convertView.findViewById(R.id.icon1);
            holder.time = (TextView) convertView.findViewById(R.id.time);
            holder.humidity = (TextView) convertView.findViewById(R.id.humidity);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        List person = (List) getItem(position);

        holder.nameTextView.setText("Temp:"+ person.main.temp);
        Date expiry = new Date(person.dt * 1000);
        SimpleDateFormat dt = new SimpleDateFormat("EEE hh:mm:ss", Locale.US);
        holder.time.setText("Time:"+dt.format(expiry));
        holder.humidity.setText("humidity:"+ person.main.humidity);
        holder.icon.setText("Wind Speed:"+person.wind.speed);

        if (position % 2 == 1) {
            convertView.setBackgroundColor(Color.LTGRAY);
        } else {
            convertView.setBackgroundColor(Color.WHITE);
        }

        return convertView;
    }

    static class ViewHolder {
        private TextView nameTextView;
        private TextView icon;
        private TextView humidity;
        private TextView time;
    }


    /*
    Notify changes in dataset
     */
    public void updateDataSet(ArrayList<List> finalData) {
        this.moviesList = finalData;
        notifyDataSetChanged();
    }
}

