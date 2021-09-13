package com.grove.diet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.google.gson.Gson;
import com.grove.diet.R;
import com.grove.diet.models.PojoDays;
import com.grove.diet.models.PojoWeekDiet;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DietAdapter extends RecyclerView.Adapter<DietAdapter.ViewHolder> {

    Context activity;
    private final PojoWeekDiet weekDietData;

    public DietAdapter(Context activity,PojoWeekDiet weekDietData) {
        this.activity = activity;
        this.weekDietData = weekDietData;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvDay;
        RecyclerView recyDays;

        public ViewHolder(View view) {
            super(view);
            tvDay = view.findViewById(R.id.tvDay);
            recyDays = view.findViewById(R.id.recyDays);
        }

    }

    @Override
    public DietAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_diet, parent, false);
        return new DietAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(DietAdapter.ViewHolder holder, int position) {
        if (position == 0) {
            holder.tvDay.setText(activity.getString(R.string.monday));
            List<PojoDays> pojoDays = new Gson().fromJson(new Gson().toJson(weekDietData.getMonday()), new TypeToken<List<PojoDays>>() {
            }.getType());
            DaysAdapter daysAdapter = new DaysAdapter(activity, pojoDays);
            holder.recyDays.setAdapter(daysAdapter);
        } else if (position == 1) {
            holder.tvDay.setText(activity.getString(R.string.wednesday));
            List<PojoDays> pojoDays = new Gson().fromJson(new Gson().toJson(weekDietData.getWednesday()), new TypeToken<List<PojoDays>>() {
            }.getType());
            DaysAdapter daysAdapter = new DaysAdapter(activity, pojoDays);
            holder.recyDays.setAdapter(daysAdapter);
        } else if (position == 2) {
            holder.tvDay.setText(activity.getString(R.string.thursday));
            List<PojoDays> pojoDays = new Gson().fromJson(new Gson().toJson(weekDietData.getThursday()), new TypeToken<List<PojoDays>>() {
            }.getType());
            DaysAdapter daysAdapter = new DaysAdapter(activity, pojoDays);
            holder.recyDays.setAdapter(daysAdapter);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
