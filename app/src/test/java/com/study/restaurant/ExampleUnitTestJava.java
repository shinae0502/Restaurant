package com.study.restaurant;

import android.app.AlertDialog;

import com.google.gson.Gson;
import com.study.restaurant.api.ApiManager;
import com.study.restaurant.model.Cities;
import com.study.restaurant.model.City;
import com.study.restaurant.model.Region;
import com.study.restaurant.model.StoreSpec;
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

    @Test
    public void storeSpecTest() {
        String dummy1 = "{\"img1\":\"http://www.globalcardsalud.com/wp-content/uploads/2011/12/banquete.jpg\",\"img2\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVjQ08rDw0pOvd8q7Un6KByZ1GrFMAKGb89JTT1pZQlQVpiSEC\",\"img3\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Good_Food_Display_-_NCI_Visuals_Online.jpg/220px-Good_Food_Display_-_NCI_Visuals_Online.jpg\",\"img4\":\"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT5koo3US24I9QEb-Su1ZUz9nVFW9-10IZ_V7QtDKxMUsARUTIv\",\"img5\":\"https://steptohealth.co.kr/wp-content/uploads/2017/03/foods-to-avoid-eating-for-breakfast-500x283.jpg\",\"updateDate\":\"2018-07-03\",\"openingHours\":\"월-금: 11:10 - 21:30\\n       토: 11:10- 15:30\",\"breaktime\":\"15:00-17:00\",\"prices\":\"만원-2만원 / 1인\",\"menu1\":\"국밥\",\"menu2\":\"정식\",\"menu3\":\"술국\",\"menu1_price\":\"7,000\",\"menu2_price\":\"10,000\",\"menu3_price\":\"13,000\",\"keyword\":\"강남역,에디터,국밥\",\"reviews\":[{\"prifile_pic\":\"a\",\"name\":\"양사랑\",\"review_count\":\"10\",\"follower\":\"234\",\"tag\":\"농민백암왕순대\",\"review\":\"가가가가가\",\"img1\":\"1\",\"img2\":\"2\",\"img3\":\"3\",\"img4\":\"4\",\"img5\":\"5\",\"like\":\"5\",\"comment\":\"10\",\"date\":\"2018-07-03\"},{\"prifile_pic\":\"a\",\"name\":\"자라\",\"review_count\":\"10\",\"follower\":\"234\",\"tag\":\"농민백암왕순대\",\"review\":\"가가가가가\",\"img1\":\"1\",\"img2\":\"2\",\"img3\":\"3\",\"img4\":\"4\",\"img5\":\"5\",\"like\":\"5\",\"comment\":\"10\",\"date\":\"2018-07-03\"},{\"prifile_pic\":\"a\",\"name\":\"고고\",\"review_count\":\"10\",\"follower\":\"234\",\"tag\":\"농민백암왕순대\",\"review\":\"가가가가가\",\"img1\":\"1\",\"img2\":\"2\",\"img3\":\"3\",\"img4\":\"4\",\"img5\":\"5\",\"like\":\"5\",\"comment\":\"10\",\"date\":\"2018-07-03\"}]}";
        StoreSpec storeSpec = new Gson().fromJson(dummy1, StoreSpec.class);
        System.out.println(storeSpec.getReviews().size());
        System.out.println(storeSpec.getReviews().get(1).getName());
    }
}
