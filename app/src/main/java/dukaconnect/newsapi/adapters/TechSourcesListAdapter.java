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


        // loading album cover using Glide library
       // Glide.with(mContext).load(album.getThumbnail()).into(holder.thumbnail);

//        holder.overflow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showPopupMenu(holder.overflow);
//            }
//        });
    }

//    @SuppressLint("InflateParams")
//    @Override
//    public View getView(final int position, View convertView, ViewGroup parent) {
//        if (inflater == null) {
//            inflater = (LayoutInflater) activity
//                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.techarticles_item, null);
//            holder = new ViewHolder(convertView);
//
//            convertView.setTag(holder);
//        } else {
//            holder = (ViewHolder) convertView.getTag();
//        }
//
//        Source item = (Source) getItem(position);
//        holder.tech_title.setText(item.get_name());
//        holder.tech_description.setText(item.get_category());
//        holder.tech_source.setText(item.get_description());
//        holder.tech_url.setText(item.get_url());
//
//        return convertView;
//    }


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
