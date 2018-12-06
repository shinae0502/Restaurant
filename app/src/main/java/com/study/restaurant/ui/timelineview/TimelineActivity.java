package com.study.restaurant.ui.timelineview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;

public class TimelineActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timeline);

        RecyclerView timeLineRv = findViewById(R.id.timeLineRv);

        timeLineRv.setLayoutManager(new LinearLayoutManager(this));
        timeLineRv.setAdapter(new NewsRvAdt());

    }

    public static void go(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, TimelineActivity.class);
        appCompatActivity.startActivity(intent);
    }


    public class NewsRvAdt extends RecyclerView.Adapter<RvHolder> {

        @Override
        public RvHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
            RvHolder rvHolder = new RvHolder(v);
            return rvHolder;
        }

        @Override
        public void onBindViewHolder(RvHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 100;
        }
    }

    public class RvHolder extends RecyclerView.ViewHolder {

        public RvHolder(View itemView) {
            super(itemView);
        }
    }
}
