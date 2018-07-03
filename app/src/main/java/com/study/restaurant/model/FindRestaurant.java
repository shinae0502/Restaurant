package com.study.restaurant.model;

public class FindRestaurant {
    Cities cities;
    Sort sort;
    Boundary boundary;
    Filter filter;

    public Filter getFilter() {
        if (filter == null)
            filter = new Filter();
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Boundary getBoundary() {
        if (boundary == null) {
            boundary = new Boundary();
            boundary.parent = this;
        }
        return boundary;
    }

    public void setBoundary(Boundary boundary) {
        boundary.parent = this;
        this.boundary = boundary;
    }

    public Sort getSort() {
        if (sort == null)
            sort = new Sort();
        return sort;
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    public Cities getCities() {
        return cities;
    }

    public void setCities(Cities cities) {
        cities.parent = this;

        //지역이 하나라도 선택되어있다면
        if (cities.getIsSelectedRegion())
            boundary.releaseAll();

        this.cities = cities;
    }
}