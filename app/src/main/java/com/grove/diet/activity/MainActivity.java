package com.grove.diet.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.grove.diet.R;
import com.grove.diet.adapters.DietAdapter;
import com.grove.diet.models.PojoDiet;
import com.grove.diet.models.PojoWeekDiet;
import com.grove.diet.repository.ApiClient;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.grove.diet.utils.GlobalFunctions.getTime;
import static com.grove.diet.utils.GlobalFunctions.setAlarm;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyDiet;
    private DietAdapter dietAdapter;
    private RelativeLayout rlLoaderView;
    private TextView tvNotAvailable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyDiet = findViewById(R.id.recyDiet);
        rlLoaderView = findViewById(R.id.rlLoaderView);
        tvNotAvailable = findViewById(R.id.tvNotAvailable);

        initView();
    }

    private void initView() {

        getDietData();
    }

    private void getDietData() {
        rlLoaderView.setVisibility(View.VISIBLE);
        Call<PojoDiet> call = ApiClient.get().getDiet();
        call.enqueue(new Callback<PojoDiet>() {
            @Override
            public void onResponse(Call<PojoDiet> call, Response<PojoDiet> response) {
                rlLoaderView.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body() != null) {
                    dietAdapter = new DietAdapter(MainActivity.this, response.body().getWeekDietData());
                    recyDiet.setAdapter(dietAdapter);
                    setReminder(response.body().getWeekDietData());
                } else {
                    tvNotAvailable.setVisibility(View.VISIBLE);
                    tvNotAvailable.setText(getString(R.string.something_went_wrong));
                }
            }

            @Override
            public void onFailure(Call<PojoDiet> call, Throwable t) {
                rlLoaderView.setVisibility(View.GONE);
                tvNotAvailable.setVisibility(View.VISIBLE);
                tvNotAvailable.setText(getString(R.string.no_internet_connection));
            }
        });
    }

    private void setReminder(PojoWeekDiet weekDietData){
        for(int count = 0;count<weekDietData.getMonday().size();count++){
            List<String> time = Arrays.asList(weekDietData.getMonday().get(count).getMealTime().split(":"));
            long value = getTime(Integer.parseInt(time.get(0)),Integer.parseInt(time.get(1)),2,false);
            if( value  < System.currentTimeMillis()){
                value = getTime(Integer.parseInt(time.get(0)),Integer.parseInt(time.get(1)),2,true);
            }
            setAlarm(value,MainActivity.this,weekDietData.getMonday().get(count).getFood());
        }
        for(int count = 0;count<weekDietData.getWednesday().size();count++){
            List<String> time = Arrays.asList(weekDietData.getWednesday().get(count).getMealTime().split(":"));
            long value = getTime(Integer.parseInt(time.get(0)),Integer.parseInt(time.get(1)),4,false);
            if( value  < System.currentTimeMillis()){
                value = getTime(Integer.parseInt(time.get(0)),Integer.parseInt(time.get(1)),4,true);
            }
            setAlarm(value,MainActivity.this,weekDietData.getWednesday().get(count).getMealTime());
        }
        for(int count = 0;count<weekDietData.getThursday().size();count++){
            List<String> time = Arrays.asList(weekDietData.getThursday().get(count).getMealTime().split(":"));
            long value = getTime(Integer.parseInt(time.get(0)),Integer.parseInt(time.get(1)),5,false);
            if(value  < System.currentTimeMillis()){
                value = getTime(Integer.parseInt(time.get(0)),Integer.parseInt(time.get(1)),5,true);
            }
            setAlarm(value,MainActivity.this,weekDietData.getThursday().get(count).getMealTime());
        }
    }


}