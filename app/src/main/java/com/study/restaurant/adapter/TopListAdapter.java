package com.study.restaurant.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.restaurant.R;
import com.study.restaurant.model.TopList;

import java.util.ArrayList;

public class TopListAdapter extends RecyclerView.Adapter<TopListAdapter.ItemViewHolder> {

    private Context context;   // ?????

    ArrayList<TopList> toplistList;
    ItemViewHolder viewHolder;

    // 생성자
    public TopListAdapter(final Context context, ArrayList<TopList> toplistList) {   // ?????
        this.context = context;
        this.toplistList = toplistList;
    }

    // ViewHolder 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_toplist_recyclerview, parent, false);
        viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

// ------------------------------------------------------------------------------- ?????
        // 이미지 주소값을 이미지파일로... 읽어와야

//        Log.d("imageID??", ""+storyList.get(position).getImage());
//        Log.d("imageIDxx", ""+R.drawable.storyimage01);

//        holder.storyContentImage.setBackground(ContextCompat.getDrawable(context, R.drawable.storyimage01));
//        holder.toplistContentImage.setBackground(ContextCompat.getDrawable(context, toplistList.get(position).getImage())); // ?????
//        holder.storyContentImage.setBackground(storyList.get(position).getImage());
        holder.toplistContentTitle.setText(toplistList.get(position).getTitle());
        holder.toplistContentSubtitle.setText(toplistList.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return toplistList.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩
    class ItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView toplistContentImage;
        private TextView toplistContentTitle;
        private TextView toplistContentSubtitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            toplistContentImage = (ImageView) itemView.findViewById(R.id.imageview_content_toplist_image);
            toplistContentTitle = (TextView) itemView.findViewById(R.id.textview_content_toplist_title);
            toplistContentSubtitle = (TextView) itemView.findViewById(R.id.textview_content_toplist_subtitle);
        }
    } // ------------------------------------------ class ItemViewHolder

} // =========================================================  class StoryAdapter


