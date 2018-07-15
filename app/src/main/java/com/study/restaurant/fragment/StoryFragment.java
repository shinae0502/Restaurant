package com.study.restaurant.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.study.restaurant.R;
import com.study.restaurant.adapter.StoryAdapter;
import com.study.restaurant.model.Story;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class StoryFragment extends Fragment {

    private Context context; //

    TextView textView;
    ArrayList<Story> storyList = new ArrayList<>();

    RecyclerView recyclerView_Story;
    GridLayoutManager gridLayoutManager_Story;

    StoryAdapter adapter_Story;

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

        View view = inflater.inflate(R.layout.activity_scrolling_story, container,false);

        recyclerView_Story =  view.findViewById(R.id.recyclerView_Story);

        // JSON data Parsing 및 셋팅
        storyList = setData(jsonString);
        // Story의 RecyclerView 셋팅
        setStoryRecyclerView();

        return view ;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void setStoryRecyclerView(){   // Story의 RecyclerView 셋팅

        // RecyclerView 객체 참조
        recyclerView_Story.setHasFixedSize(true);

        //  RecyclerView에 Adapter 설정
//        Log.d("testtest", ""+storyList.size());
        adapter_Story = new StoryAdapter(context, storyList);
        recyclerView_Story.setAdapter(adapter_Story);

        // View의 형태 정의 : LayoutManager 정의 --> GridLayoutManager 로 설정
        gridLayoutManager_Story = new GridLayoutManager(getContext(), 2);
        recyclerView_Story.setLayoutManager(gridLayoutManager_Story);

    }

    public ArrayList<Story> setData(String jsonString){

        String story_id = null;
        String title = null;
        String subtitle = null;
        String imageName = null;
        int imageID;
//        Image image = null;
//        ImageView imageView = null;

        try {
            JSONArray jarray = new JSONObject(jsonString).getJSONArray("data");
            for (int i = 0; i < jarray.length() - 1; i++) {
                HashMap map = new HashMap<>();
                JSONObject jObject = jarray.getJSONObject(i);

                story_id = jObject.optString("story_id");
                title = jObject.optString("title");
                subtitle = jObject.optString("subtitle");
                imageName = jObject.optString("image");  // ??????

                imageID = getResources().getIdentifier(imageName, "drawable", "com.example.kim.gangnam_project_02");
//                image = ContextCompat.getDrawable(context, imageID);
//                image = jObject.get(ContextCompat.getDrawable(context, R.drawable.images_image01_story));
                storyList.add(new Story(story_id, title, subtitle, imageID));  // ?????

            }
        } catch (JSONException e) {
//            Log.d("testest",e.toString());
            e.printStackTrace();
        }
        return storyList;
    } // ------------------------------------------------ setData()

}