package com.grove.diet.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.grove.diet.R;
import com.grove.diet.models.PojoDays;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class DaysAdapter extends RecyclerView.Adapter<DaysAdapter.ViewHolder> {

    private Context activity;
    private List<PojoDays> pojoDays = new ArrayList<>();

    public DaysAdapter(Context activity, List<PojoDays> pojoDays) {
        this.activity = activity;
        this.pojoDays = pojoDays;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvFood, tvTime;

        public ViewHolder(View view) {
            super(view);
            tvFood = view.findViewById(R.id.tvFood);
            tvTime = view.findViewById(R.id.tvTime);
        }

    }

    @Override
    public DaysAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_days, parent, false);
        return new DaysAdapter.ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(DaysAdapter.ViewHolder holder, int position) {
        holder.tvFood.setText(pojoDays.get(position).getFood());
        holder.tvTime.setText(pojoDays.get(position).getMealTime());
    }

    @Override
    public int getItemCount() {
        return pojoDays.size();
    }
}
