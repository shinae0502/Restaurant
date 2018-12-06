package com.study.restaurant.ui.followerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.study.restaurant.R;

public class FollowerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follower);
        RecyclerView followerRv = findViewById(R.id.followerRv);
        followerRv.setLayoutManager(new LinearLayoutManager(this));
        followerRv.setAdapter(new FollowerRvAdt());
    }

    public static void go(AppCompatActivity appCompatActivity) {
        Intent intent = new Intent(appCompatActivity, FollowerActivity.class);
        appCompatActivity.startActivity(intent);
    }

    private class FollowerRvAdt extends RecyclerView.Adapter {
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_follower, parent, false);
            return new FollowerHolder(v);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    private class FollowerHolder extends RecyclerView.ViewHolder {
        public FollowerHolder(View itemView) {
            super(itemView);
        }
    }
}
