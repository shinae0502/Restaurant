package com.study.restaurant.model;

public class FoodCategotyModel {

    FoodCategory foodCategory = FoodCategory.NONE;

    public enum FoodCategory {
        NONE,
        KOREAN_FOOD,
        JAPANESE_FOOD,
        CHINESE_FOOD,
        WESTERN_FOOD,
        WORLD_WIDE_FOOD,
        BUFFET,
        CAFE,
        BAR
    }

    public void setFoodCategory(FoodCategory foodCategory) { ;
        if (this.foodCategory == foodCategory) {
            this.foodCategory = FoodCategory.NONE;
        } else {
            this.foodCategory = foodCategory;
        }
    }

    public FoodCategory getFoodCategory() {
        return foodCategory;
    }

    public boolean isSelected(FoodCategory foodCategory) {
        return this.foodCategory == foodCategory;
    }
}