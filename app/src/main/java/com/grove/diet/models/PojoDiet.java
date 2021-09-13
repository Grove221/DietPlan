package com.grove.diet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PojoDiet {

    @SerializedName("diet_duration")
    @Expose
    private Integer dietDuration;
    @SerializedName("week_diet_data")
    @Expose
    private PojoWeekDiet weekDietData;

    public Integer getDietDuration() {
        return dietDuration;
    }

    public void setDietDuration(Integer dietDuration) {
        this.dietDuration = dietDuration;
    }

    public PojoWeekDiet getWeekDietData() {
        return weekDietData;
    }

    public void setWeekDietData(PojoWeekDiet weekDietData) {
        this.weekDietData = weekDietData;
    }


}
