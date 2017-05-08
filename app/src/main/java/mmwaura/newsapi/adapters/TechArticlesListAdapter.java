package mmwaura.newsapi.adapters;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import mmwaura.newsapi.ArticleActivity;
import mmwaura.newsapi.R;
import mmwaura.newsapi.data.Source;

/**
 * Created by mark on 4/24/17.
 */

public class TechArticlesListAdapter extends RecyclerView.Adapter<TechArticlesListAdapter.ViewHolder> implements View.OnClickListener{
    ViewHolder holder = null;
    Context context;
    View view;
    private Activity activity;
    private LayoutInflater inflater;
    private List<Source> postsItems;
    private Intent intent;
    private ImageLoader imageLoader;
    private FragmentManager fragmentManager;

    public TechArticlesListAdapter(Activity mActivity, List<Source> mPostsItems) {
        this.activity = mActivity;
        this.postsItems = mPostsItems;
    }


    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return postsItems.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Source item = postsItems.get(position);

        holder.tech_title.setText(item.get_category());
        holder.tech_title.setOnClickListener(this);

        holder.tech_title.setTag(R.id.TAG_OBJECT, item);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.techsourcesarticles_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tech_articleTitle) {
            Source item = (Source) v.getTag(R.id.TAG_OBJECT);

            Intent intent = new Intent(activity, ArticleActivity.class);
            intent.putExtra("category", item.get_category());
            activity.startActivity(intent);

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tech_title, tech_description, tech_source, tech_url;


        public ViewHolder(View v) {
            super(v);
            tech_title = (TextView) v.findViewById(R.id.tech_articleTitle);


        }
    }


}
