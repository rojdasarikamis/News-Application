package com.androidevreni.rsstutorial.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidevreni.rsstutorial.R;
import com.androidevreni.rsstutorial.models.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mustafademir on 28.06.2016.
 */
public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {

    private String TAG = getClass().getSimpleName();

    private Context context;
    private List<Post> postList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView category;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            category = (TextView) view.findViewById(R.id.category);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }
    }


    public PostsAdapter(Context context, List<Post> postList) {
        this.context = context;
        this.postList = postList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Post post = postList.get(position);
        holder.title.setText(post.getTitle());
        holder.category.setText(post.getCategory().get(0));
        Log.i(TAG, "image url > : " + post.getImageUrl());
        Picasso.with(context).load(post.getImageUrl()).into(holder.imageView);


    }

    @Override
    public int getItemCount() {
        return postList.size();
    }
}
