package com.study.restaurant.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.restaurant.R;
import com.study.restaurant.adapter.TopListAdapter;
import com.study.restaurant.model.TopList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class TopListFragment extends Fragment {

    private Context context; //

    TextView textView;
    ArrayList<TopList> toplistList = new ArrayList<>();

    RecyclerView recyclerView_TopList;
    LinearLayoutManager linearLayoutManager_TopList;

    TopListAdapter adapter_TopList;

    private String jsonString
            = "{"
            + "\"data\":"
            + "["
            + "{\"story_id\" : \"1\", \"title\":\"타이틀 1\", \"subtitle\":\"서브 타이틀 1\", \"image\":\"storyimage01\"},"
            + "{\"story_id\" : \"2\", \"title\":\"타이틀 2\", \"subtitle\":\"서브 타이틀 2\", \"image\":\"storyimage02\"},"
            + "{\"story_id\" : \"3\", \"title\":\"타이틀 3\", \"subtitle\":\"서브 타이틀 3\", \"image\":\"storyimage03\"},"
            + "{\"story_id\" : \"4\", \"title\":\"타이틀 4\", \"subtitle\":\"서브 타이틀 4\", \"image\":\"storyimage04\"},"
            + "{\"story_id\" : \"5\", \"title\":\"타이틀 5\", \"subtitle\":\"서브 타이틀 5\", \"image\":\"storyimage05\"},"
            + "{\"story_id\" : \"6\", \"title\":\"타이틀 6\", \"subtitle\":\"서브 타이틀 6\", \"image\":\"그림 6\"}"
            + "]"
            + "}";

    //
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_scrolling_toplist, container,false);

        // RecyclerView 객체 참조
        recyclerView_TopList =  view.findViewById(R.id.recyclerView_TopList);

        // JSON data Parsing 및 셋팅
        toplistList = setData(jsonString);
        // TopList의 RecyclerView 셋팅
        setTopListRecyclerView();

        return view ;
    }

    @Override
    public void onAttach(Context context) {  // ----- ???
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() { // ----- ???
        super.onDetach();
    }

    public void setTopListRecyclerView(){   // TopList의 RecyclerView 셋팅

        // RecyclerView 객체 참조
        recyclerView_TopList.setHasFixedSize(true);

        //  RecyclerView에 Adapter 설정
//        Log.d("testtest", ""+storyList.size());
        adapter_TopList = new TopListAdapter(context, toplistList);
        recyclerView_TopList.setAdapter(adapter_TopList);

        // View의 형태 정의 : LayoutManager 정의 --> GridLayoutManager 로 설정
        linearLayoutManager_TopList = new LinearLayoutManager(context);
        recyclerView_TopList.setLayoutManager(linearLayoutManager_TopList);
    }

    public ArrayList<TopList> setData(String jsonString){

        String toplist_id = null;
        String title = null;
        String subtitle = null;
        String imageName = null;
        int imageID;
        int hit;
        String badge;
        Date date;
//        Image image = null;
//        ImageView imageView = null;

        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONArray("data");
            for (int i = 0; i < jarray.length()-1; i++) {
                HashMap map = new HashMap<>();
                JSONObject jObject = jarray.getJSONObject(i);

                toplist_id = jObject.optString("story_id");
                title = jObject.optString("title");
                subtitle = jObject.optString("subtitle");
                imageName = jObject.optString("image");  // ??????

                // 현재 시간 가져오기
                long now = System.currentTimeMillis();
//                date = new Date(now);
//                date = Object.optString("date");

                imageID = getResources().getIdentifier(imageName, "drawable", "com.example.kim.gangnam_project_02");
//                image = ContextCompat.getDrawable(context, imageID);
//                image = jObject.get(ContextCompat.getDrawable(context, R.drawable.images_image01_story));
                toplistList.add(new TopList(toplist_id, title, subtitle, "???", imageID, 0, "??" ));  // ?????
            }
        } catch (JSONException e) {
//            Log.d("testest",e.toString());
            e.printStackTrace();
        }
        return toplistList;
    } // ------------------------------------------------ setData()


}




/*
   public TopListFragment() {
        // Required empty public constructor
    }

    public static TopListFragment newInstance() {
        TopListFragment fragment = new TopListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_top_list, container, false);
        return v;
    }





 */