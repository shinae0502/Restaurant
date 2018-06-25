package com.study.restaurant;

import android.app.AlertDialog;

import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.Cities;
import com.study.restaurant.model.City;
import com.study.restaurant.model.Region;
import com.study.restaurant.util.LOG;

import org.junit.Test;

import java.util.ArrayList;

public class ExampleUnitTestJava {

    @Test
    public void test() {
        System.out.println("test");
    }

    public Cities makeDummy() {
        Cities cities = new Cities();
        cities.addCity(new City.Builder().setCity_id("1").setCity_name("강남").create());
        cities.addCity(new City.Builder().setCity_id("2").setCity_name("강북").create());
        ArrayList<Region> regionArrayList = new ArrayList<>();
        regionArrayList.add(new Region.Builder().setCity_id("1").setRegion_id("100").setRegion_name("강남역").create());
        regionArrayList.add(new Region.Builder().setCity_id("2").setRegion_id("101").setRegion_name("가로수길").create());
        cities.setRegions(regionArrayList);
        return cities;
    }

    @Test
    public void dummyTest() throws CloneNotSupportedException {
        Cities cities = makeDummy();
        System.out.println(cities.toString());
        Cities cities1 = cities.clone();
    }


    @Test
    public void cloneTest() {
        Cities cities = new Cities();
        cities.addCity(new City.Builder()
                .setCity_id("1")
                .setCity_name("강남")
                .create());

        cities.addCity(new City.Builder()
                .setCity_id("2")
                .setCity_name("강북")
                .create());
        try {
            Cities cities1 = cities.clone();
            cities1.getCities().get(0).setCity_name("asss");
            System.out.println(cities.getCities().get(0).getCity_name());
            System.out.println(cities1.getCities().get(0).getCity_name());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cityClone() {
        City city = new City.Builder().setCity_id("0").setCity_name("name").create();
        try {
            City city1 = city.clone();
            city1.setCity_name("asfdasdf");
            System.out.println(city1.getCity_name());
            System.out.println(city.getCity_name());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void regionClone() {
        City city = new City.Builder().setCity_name("BB").setCity_id("0").create();

        Region region = new Region();
        region.setChecked(false);
        region.setRegion_id("1");
        region.setRegion_name("region name");
        region.setParent(city);

        System.out.println(region.toString());
        try {
            Region region1 = region.clone();
            region1.setRegion_name("asfasadf");
            region1.getParent().setCity_name("assss");
            System.out.println(region1.toString());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
