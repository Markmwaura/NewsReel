package dukaconnect.newsapi.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import dukaconnect.newsapi.R;
import dukaconnect.newsapi.data.Source;

/**
 * Created by mark on 4/24/17.
 */

public class TechSourcesListAdapter extends RecyclerView.Adapter<TechSourcesListAdapter.MyViewHolder> {
    MyViewHolder holder = null;
    private Activity activity;
    private LayoutInflater inflater;
    private List<Source> postsItems;
    private Intent intent;
    private ImageLoader imageLoader;

    public TechSourcesListAdapter(Activity mActivity, List<Source> mPostsItems) {
        this.activity = mActivity;
        this.postsItems = mPostsItems;
    }

    @Override
    public int getItemCount() {
        return postsItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        Source item = postsItems.get(position);

        holder.tech_description.setText(item.get_category());

    }



    public class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView tech_title, tech_description, tech_source, tech_url;


        public MyViewHolder(View v) {
            super(v);

            tech_description = (TextView) v.findViewById(R.id.tech_articledescription);


        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.techarticles_item, parent, false);

        return new MyViewHolder(itemView);
    }




}
