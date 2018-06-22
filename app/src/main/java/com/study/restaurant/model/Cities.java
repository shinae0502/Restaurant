package com.study.restaurant.model;

import java.util.ArrayList;

public class Cities {

    ArrayList<City> cities;

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public City getCity(String cityName){
        for(City city : cities)
        {
            if(city.city_name.equals(cityName))
                return city;
        }
        return new City();
    }


    public void setRegions(ArrayList<Region> regions) {
        for(City city : cities)
        {
            for(Region region: regions)
            {
                if(city.city_id.equals(region.city_id))
                {
                    region.setParent(city);
                    city.regions.add(region);
                }
            }
        }
    }

    public boolean isDirty() {
        for(City city : cities)
        {
            for(Region region: city.regions)
            {
                if(region.isSelected == true)
                    return true;
            }
        }
        return false;
    }
}
