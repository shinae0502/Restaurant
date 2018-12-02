package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class OpenHours extends BaseObservable implements Parcelable {

    public enum DAYS {
        MON, TUE
    }

    private int open_hour_id;
    private int store_id;
    private String mon_open;
    private String mon_close;
    private String mon_break_start;
    private String mon_break_end;
    private String tue_open;
    private String tue_close;
    private String tue_break_start;
    private String tue_break_end;
    private String wed_open;
    private String wed_close;
    private String wed_break_start;
    private String wed_break_end;
    private String thu_open;
    private String thu_close;
    private String thu_break_start;
    private String thu_break_end;
    private String fri_open;
    private String fri_close;
    private String fri_break_start;
    private String fri_break_end;
    private String sat_open;
    private String sat_close;
    private String sat_break_start;
    private String sat_break_end;
    private String sun_open;
    private String sun_close;
    private String sun_break_start;
    private String sun_break_end;

    protected OpenHours(Parcel in) {
        open_hour_id = in.readInt();
        store_id = in.readInt();
        mon_open = in.readString();
        mon_close = in.readString();
        mon_break_start = in.readString();
        mon_break_end = in.readString();
        tue_open = in.readString();
        tue_close = in.readString();
        tue_break_start = in.readString();
        tue_break_end = in.readString();
        wed_open = in.readString();
        wed_close = in.readString();
        wed_break_start = in.readString();
        wed_break_end = in.readString();
        thu_open = in.readString();
        thu_close = in.readString();
        thu_break_start = in.readString();
        thu_break_end = in.readString();
        fri_open = in.readString();
        fri_close = in.readString();
        fri_break_start = in.readString();
        fri_break_end = in.readString();
        sat_open = in.readString();
        sat_close = in.readString();
        sat_break_start = in.readString();
        sat_break_end = in.readString();
        sun_open = in.readString();
        sun_close = in.readString();
        sun_break_start = in.readString();
        sun_break_end = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(open_hour_id);
        dest.writeInt(store_id);
        dest.writeString(mon_open);
        dest.writeString(mon_close);
        dest.writeString(mon_break_start);
        dest.writeString(mon_break_end);
        dest.writeString(tue_open);
        dest.writeString(tue_close);
        dest.writeString(tue_break_start);
        dest.writeString(tue_break_end);
        dest.writeString(wed_open);
        dest.writeString(wed_close);
        dest.writeString(wed_break_start);
        dest.writeString(wed_break_end);
        dest.writeString(thu_open);
        dest.writeString(thu_close);
        dest.writeString(thu_break_start);
        dest.writeString(thu_break_end);
        dest.writeString(fri_open);
        dest.writeString(fri_close);
        dest.writeString(fri_break_start);
        dest.writeString(fri_break_end);
        dest.writeString(sat_open);
        dest.writeString(sat_close);
        dest.writeString(sat_break_start);
        dest.writeString(sat_break_end);
        dest.writeString(sun_open);
        dest.writeString(sun_close);
        dest.writeString(sun_break_start);
        dest.writeString(sun_break_end);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<OpenHours> CREATOR = new Creator<OpenHours>() {
        @Override
        public OpenHours createFromParcel(Parcel in) {
            return new OpenHours(in);
        }

        @Override
        public OpenHours[] newArray(int size) {
            return new OpenHours[size];
        }
    };

    public int getOpen_hour_id() {
        return open_hour_id;
    }

    public void setOpen_hour_id(int open_hour_id) {
        this.open_hour_id = open_hour_id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getMon_open() {
        return mon_open;
    }

    public void setMon_open(String mon_open) {
        this.mon_open = mon_open;
    }

    public String getMon_close() {
        return mon_close;
    }

    public void setMon_close(String mon_close) {
        this.mon_close = mon_close;
    }

    public String getMon_break_start() {
        return mon_break_start;
    }

    public void setMon_break_start(String mon_break_start) {
        this.mon_break_start = mon_break_start;
    }

    public String getMon_break_end() {
        return mon_break_end;
    }

    public void setMon_break_end(String mon_break_end) {
        this.mon_break_end = mon_break_end;
    }

    public String getTue_open() {
        return tue_open;
    }

    public void setTue_open(String tue_open) {
        this.tue_open = tue_open;
    }

    public String getTue_close() {
        return tue_close;
    }

    public void setTue_close(String tue_close) {
        this.tue_close = tue_close;
    }

    public String getTue_break_start() {
        return tue_break_start;
    }

    public void setTue_break_start(String tue_break_start) {
        this.tue_break_start = tue_break_start;
    }

    public String getTue_break_end() {
        return tue_break_end;
    }

    public void setTue_break_end(String tue_break_end) {
        this.tue_break_end = tue_break_end;
    }

    public String getWed_open() {
        return wed_open;
    }

    public void setWed_open(String wed_open) {
        this.wed_open = wed_open;
    }

    public String getWed_close() {
        return wed_close;
    }

    public void setWed_close(String wed_close) {
        this.wed_close = wed_close;
    }

    public String getWed_break_start() {
        return wed_break_start;
    }

    public void setWed_break_start(String wed_break_start) {
        this.wed_break_start = wed_break_start;
    }

    public String getWed_break_end() {
        return wed_break_end;
    }

    public void setWed_break_end(String wed_break_end) {
        this.wed_break_end = wed_break_end;
    }

    public String getThu_open() {
        return thu_open;
    }

    public void setThu_open(String thu_open) {
        this.thu_open = thu_open;
    }

    public String getThu_close() {
        return thu_close;
    }

    public void setThu_close(String thu_close) {
        this.thu_close = thu_close;
    }

    public String getThu_break_start() {
        return thu_break_start;
    }

    public void setThu_break_start(String thu_break_start) {
        this.thu_break_start = thu_break_start;
    }

    public String getThu_break_end() {
        return thu_break_end;
    }

    public void setThu_break_end(String thu_break_end) {
        this.thu_break_end = thu_break_end;
    }

    public String getFri_open() {
        return fri_open;
    }

    public void setFri_open(String fri_open) {
        this.fri_open = fri_open;
    }

    public String getFri_close() {
        return fri_close;
    }

    public void setFri_close(String fri_close) {
        this.fri_close = fri_close;
    }

    public String getFri_break_start() {
        return fri_break_start;
    }

    public void setFri_break_start(String fri_break_start) {
        this.fri_break_start = fri_break_start;
    }

    public String getFri_break_end() {
        return fri_break_end;
    }

    public void setFri_break_end(String fri_break_end) {
        this.fri_break_end = fri_break_end;
    }

    public String getSat_open() {
        return sat_open;
    }

    public void setSat_open(String sat_open) {
        this.sat_open = sat_open;
    }

    public String getSat_close() {
        return sat_close;
    }

    public void setSat_close(String sat_close) {
        this.sat_close = sat_close;
    }

    public String getSat_break_start() {
        return sat_break_start;
    }

    public void setSat_break_start(String sat_break_start) {
        this.sat_break_start = sat_break_start;
    }

    public String getSat_break_end() {
        return sat_break_end;
    }

    public void setSat_break_end(String sat_break_end) {
        this.sat_break_end = sat_break_end;
    }

    public String getSun_open() {
        return sun_open;
    }

    public void setSun_open(String sun_open) {
        this.sun_open = sun_open;
    }

    public String getSun_close() {
        return sun_close;
    }

    public void setSun_close(String sun_close) {
        this.sun_close = sun_close;
    }

    public String getSun_break_start() {
        return sun_break_start;
    }

    public void setSun_break_start(String sun_break_start) {
        this.sun_break_start = sun_break_start;
    }

    public String getSun_break_end() {
        return sun_break_end;
    }

    public void setSun_break_end(String sun_break_end) {
        this.sun_break_end = sun_break_end;
    }

    public static Creator<OpenHours> getCREATOR() {
        return CREATOR;
    }

    @Bindable
    public String getOffDay() {
        ArrayList<String> offDays = new ArrayList<>();
        if (isDayoffMonday())
            offDays.add("월");
        if (isDayoffTuesday())
            offDays.add("화");
        if (isDayoffWednesday())
            offDays.add("수");
        if (isDayoffThursday())
            offDays.add("목");
        if (isDayoffFriday())
            offDays.add("금");
        if (isDayoffSaturday())
            offDays.add("토");
        if (isDayoffSunday())
            offDays.add("일");
        return "쉬는날: " + android.text.TextUtils.join(",", offDays);
    }

    @Bindable
    public boolean isHasOffDay() {
        return (isDayoffMonday() ||
                isDayoffTuesday() ||
                isDayoffWednesday() ||
                isDayoffThursday() ||
                isDayoffFriday() ||
                isDayoffSaturday() ||
                isDayoffSunday());
    }

    @Bindable
    public String getWeekDayOpenHours() {
        String openHours = "";
        if (isHasWeekDaysOpenHours()) {
            openHours += "평일:" + mon_open + "-" + mon_close;
        }
        return openHours;
    }

    @Bindable
    public String getWeekendOpenHours() {
        String openHours = "";
        if (isHasWeekendOpenHours()) {
            openHours += "주말:" + sat_open + "-" + sat_close;
        }
        return openHours;
    }

    @Bindable
    public boolean isDayoffMonday() {
        return (mon_open == null || mon_open.length() == 0);
    }

    @Bindable
    public boolean isDayoffTuesday() {
        return (tue_open == null || tue_open.length() == 0);
    }

    @Bindable
    public boolean isDayoffWednesday() {
        return (wed_open == null || wed_open.length() == 0);
    }

    @Bindable
    public boolean isDayoffThursday() {
        return (thu_open == null || thu_open.length() == 0);
    }

    @Bindable
    public boolean isDayoffFriday() {
        return (fri_open == null || fri_open.length() == 0);
    }

    @Bindable
    public boolean isDayoffSaturday() {
        return (sat_open == null || sat_open.length() == 0);
    }

    @Bindable
    public boolean isDayoffSunday() {
        return (sun_open == null || sun_open.length() == 0);
    }

    @Bindable
    public String getMonOpenHours() {
        return "월: " + mon_open + "-" + mon_close;
    }

    @Bindable
    public String getTueOpenHours() {
        return "화: " + tue_open + "-" + tue_close;
    }

    @Bindable
    public String getWedOpenHours() {
        return "수: " + wed_open + "-" + wed_close;
    }

    @Bindable
    public String getThuOpenHours() {
        return "목: " + thu_open + "-" + thu_close;
    }

    @Bindable
    public String getFriOpenHours() {
        return "금: " + fri_open + "-" + fri_close;
    }

    @Bindable
    public String getSatOpenHours() {
        return "토: " + sat_open + "-" + sat_close;
    }

    @Bindable
    public String getSunOpenHours() {
        return "일: " + sun_open + "-" + sun_close;
    }

    @Bindable
    public boolean isHasWeekDaysOpenHours() {
        return (mon_open.equals(tue_open)
                && mon_open.equals(wed_open)
                && mon_open.equals(thu_open)
                && mon_open.equals(fri_open)
                && mon_close.equals(tue_close)
                && mon_close.equals(wed_close)
                && mon_close.equals(thu_close)
                && mon_close.equals(fri_close));
    }

    @Bindable
    public boolean isHasWeekendOpenHours() {
        return (sat_open.equals(sun_open)
                && sat_close.equals(sun_close));
    }

    @Bindable
    public boolean isHasBreakMonday() {
        return !(mon_break_start == null || mon_break_start.length() == 0);
    }

    @Bindable
    public boolean isHasBreakTuesday() {
        return !(tue_break_start == null || tue_break_start.length() == 0);
    }

    @Bindable
    public boolean isHasBreakWednesday() {
        return !(wed_break_start == null || wed_break_start.length() == 0);
    }

    @Bindable
    public boolean isHasBreakThursday() {
        return !(thu_break_start == null || thu_break_start.length() == 0);
    }

    @Bindable
    public boolean isHasBreakFriday() {
        return !(fri_break_start == null || fri_break_start.length() == 0);
    }

    @Bindable
    public boolean isHasBreakSaturday() {
        return !(sat_break_start == null || sat_break_start.length() == 0);
    }

    @Bindable
    public boolean isHasBreakSunday() {
        return !(sun_break_start == null || sun_break_start.length() == 0);
    }

    @Bindable
    public String getMonBreakHours() {
        return "월: " + mon_break_start + "-" + mon_break_end;
    }

    @Bindable
    public String getTueBreakHours() {
        return "화: " + tue_break_start + "-" + tue_break_end;
    }

    @Bindable
    public String getWedBreakHours() {
        return "수: " + wed_break_start + "-" + wed_break_end;
    }

    @Bindable
    public String getThuBreakHours() {
        return "목: " + thu_break_start + "-" + thu_break_end;
    }

    @Bindable
    public String getFriBreakHours() {
        return "금: " + fri_break_start + "-" + fri_break_end;
    }

    @Bindable
    public String getSatBreakHours() {
        return "토: " + sat_break_start + "-" + sat_break_end;
    }

    @Bindable
    public String getSunBreakHours() {
        return "일: " + sun_break_start + "-" + sun_break_end;
    }

}
