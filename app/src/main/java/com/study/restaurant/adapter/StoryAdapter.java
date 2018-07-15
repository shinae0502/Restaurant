package com.study.restaurant.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.study.restaurant.R;
import com.study.restaurant.model.Story;

import java.util.ArrayList;

public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.ItemViewHolder> {

    private Context context;   // ?????

    ArrayList<Story> storyList;
    ItemViewHolder viewHolder;

    // 생성자
    public StoryAdapter(final Context context, ArrayList<Story> storyList) {   // ?????
        this.context = context;
        this.storyList = storyList;
    }

    // ViewHolder 생성
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view
                = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.content_story_recyclerview, parent, false);
        viewHolder = new ItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

        // gridlayout LayoutParams
        GridLayoutManager.LayoutParams layoutParams = (GridLayoutManager.LayoutParams)holder.itemView.getLayoutParams();
        layoutParams.height = 570; //  ---------------  ??????????
        layoutParams.width = layoutParams.height;

// ------------------------------------------------------------------------------- ?????
        // 이미지 주소값을 이미지파일로... 읽어와야

//        Log.d("imageID??", ""+storyList.get(position).getImage());
//        Log.d("imageIDxx", ""+R.drawable.storyimage01);

//        holder.storyContentImage.setBackground(ContextCompat.getDrawable(context, R.drawable.storyimage01));
        holder.storyContentImage.setBackground(ContextCompat.getDrawable(context, storyList.get(position).getImage())); // ?????
//        holder.storyContentImage.setBackground(storyList.get(position).getImage());
        holder.storyContentTitle.setText(storyList.get(position).getTitle());
        holder.storyContentSubtitle.setText(storyList.get(position).getSubtitle());
    }

    @Override
    public int getItemCount() {
        return storyList.size();
    }

    // 커스텀 뷰홀더
    // item layout 에 존재하는 위젯들을 바인딩
    class ItemViewHolder extends RecyclerView.ViewHolder{

        private ImageView storyContentImage;
        private TextView storyContentTitle;
        private TextView storyContentSubtitle;

        public ItemViewHolder(View itemView) {
            super(itemView);
            storyContentImage = (ImageView) itemView.findViewById(R.id.imageview_content_story_image);
            storyContentTitle = (TextView) itemView.findViewById(R.id.textview_content_story_title);
            storyContentSubtitle = (TextView) itemView.findViewById(R.id.textview_content_story_subtitle);
        }
    } // ------------------------------------------ class ItemViewHolder

} // =========================================================  class StoryAdapter


