package com.study.restaurant.model;

import java.util.ArrayList;

public class City {

    //도시는 지역을 갖고 있다.
    ArrayList<Region> regions = new ArrayList<>();

    public ArrayList<Region> getRegions() {
        return regions;
    }

    public void setRegions(ArrayList<Region> regions) {
        this.regions = regions;
    }

    //지역은 선택 될 수 있고 선택된 카운트를 가져 올 수 있어야한다.
    public int getSelectedRegionCount()
    {
        int count = 0;

        if(regions != null)
        {
            for(Region region : regions)
            {
                if(region.isSelected)
                    count++;
            }
        }

        return count;
    }


    String city_id;
    String city_name;

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }


}
