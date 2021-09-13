package com.grove.diet.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PojoWeekDiet {

    @SerializedName("monday")
    @Expose
    private List<PojoMonday> monday = null;
    @SerializedName("wednesday")
    @Expose
    private List<PojoWednesday> wednesday = null;
    @SerializedName("thursday")
    @Expose
    private List<PojoThursday> thursday = null;

    public List<PojoMonday> getMonday() {
        return monday;
    }

    public void setMonday(List<PojoMonday> monday) {
        this.monday = monday;
    }

    public List<PojoWednesday> getWednesday() {
        return wednesday;
    }

    public void setWednesday(List<PojoWednesday> wednesday) {
        this.wednesday = wednesday;
    }

    public List<PojoThursday> getThursday() {
        return thursday;
    }

    public void setThursday(List<PojoThursday> thursday) {
        this.thursday = thursday;
    }

}
