package com.study.restaurant.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.study.restaurant.BR;

public class Filter extends BaseObservable implements Cloneable {

    //카테고리
    //전체
    //가고싶다
    //가봤어요
    boolean isAll = true;
    boolean isWannaGo, isHaveBeen;

    //음식종류
    boolean koreanFood, japaneseFood, chineseFood, westernFood, worldWideFood, buffet, cafe, bar;

    //가격
    boolean cost1, cost2, cost3, cost4;

    //park
    boolean dontCare, available;

    @Bindable
    public boolean isDirty() {
        return koreanFood || japaneseFood || chineseFood || westernFood || worldWideFood || buffet || cafe || bar
                || isWannaGo || isHaveBeen || cost1 || cost2 || cost3 || cost4 || dontCare || available;
    }


    @Bindable
    public boolean isAll() {
        return isAll;
    }

    public void setAll(boolean all) {
        isAll = all;
        notifyPropertyChanged(BR.all);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isWannaGo() {
        return isWannaGo;
    }

    public void setWannaGo(boolean wannaGo) {
        isWannaGo = wannaGo;
        notifyPropertyChanged(BR.wannaGo);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isHaveBeen() {
        return isHaveBeen;
    }

    public void setHaveBeen(boolean haveBeen) {
        isHaveBeen = haveBeen;
        notifyPropertyChanged(BR.haveBeen);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isKoreanFood() {
        return koreanFood;
    }

    public void setKoreanFood(boolean koreanFood) {
        this.koreanFood = koreanFood;
        notifyPropertyChanged(BR.koreanFood);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isJapaneseFood() {
        return japaneseFood;
    }

    public void setJapaneseFood(boolean japaneseFood) {
        this.japaneseFood = japaneseFood;
        notifyPropertyChanged(BR.japaneseFood);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isChineseFood() {
        return chineseFood;
    }

    public void setChineseFood(boolean chineseFood) {
        this.chineseFood = chineseFood;
        notifyPropertyChanged(BR.chineseFood);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isWesternFood() {
        return westernFood;
    }

    public void setWesternFood(boolean westernFood) {
        this.westernFood = westernFood;
        notifyPropertyChanged(BR.westernFood);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isWorldWideFood() {
        return worldWideFood;
    }

    public void setWorldWideFood(boolean worldWideFood) {
        this.worldWideFood = worldWideFood;
        notifyPropertyChanged(BR.worldWideFood);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isBuffet() {
        return buffet;
    }

    public void setBuffet(boolean buffet) {
        this.buffet = buffet;
        notifyPropertyChanged(BR.buffet);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isCafe() {
        return cafe;
    }

    public void setCafe(boolean coffee) {
        this.cafe = cafe;
        notifyPropertyChanged(BR.cafe);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isBar() {
        return bar;
    }

    public void setBar(boolean bar) {
        this.bar = bar;
        notifyPropertyChanged(BR.bar);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isCost1() {
        return cost1;
    }

    public void setCost1(boolean cost1) {
        this.cost1 = cost1;
        notifyPropertyChanged(BR.cost1);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isCost2() {
        return cost2;
    }

    public void setCost2(boolean cost2) {
        this.cost2 = cost2;
        notifyPropertyChanged(BR.cost2);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isCost3() {
        return cost3;
    }

    public void setCost3(boolean cost3) {
        this.cost3 = cost3;
        notifyPropertyChanged(BR.cost3);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isCost4() {
        return cost4;
    }

    public void setCost4(boolean cost4) {
        this.cost4 = cost4;
        notifyPropertyChanged(BR.cost4);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isDontCare() {
        return dontCare;
    }

    public void setDontCare(boolean dontCare) {
        this.dontCare = dontCare;
        notifyPropertyChanged(BR.dontCare);
        notifyPropertyChanged(BR.dirty);
    }

    @Bindable
    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
        notifyPropertyChanged(BR.available);
        notifyPropertyChanged(BR.dirty);
    }

    public Filter clone() throws CloneNotSupportedException {
        return (Filter) super.clone();
    }
}
