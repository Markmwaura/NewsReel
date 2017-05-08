package mmwaura.newsapi.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import com.squareup.picasso.Picasso;

import java.util.List;

import mmwaura.newsapi.ArticleActivity;
import mmwaura.newsapi.R;
import mmwaura.newsapi.WebViewActivity;
import mmwaura.newsapi.data.Article;
import mmwaura.newsapi.font.RobotoTextView;

/**
 * Created by mark on 4/24/17.
 */

public class CatArticlesListAdapter extends RecyclerView.Adapter<CatArticlesListAdapter.ViewHolder> implements View.OnClickListener {

    public Activity activity;
    private LayoutInflater inflater;
    private List<Article> postsItems;
    private Intent intent;
    private ImageLoader imageLoader;


    public CatArticlesListAdapter(Activity mActivity, List<Article> mPostsItems) {
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
    public void onBindViewHolder(final CatArticlesListAdapter.ViewHolder holder, int position) {
        Article item = postsItems.get(position);
        holder.title.setText(item.get_title());
        holder.description.setText(item.get_desc());
        //holder.source.setText(item.get_news_source_id());
        holder.url.setText(item.get_url());
        if(item.get_urltoimage()!=null){
            Picasso.with(this.activity).setLoggingEnabled(true);
            Picasso.with(this.activity).load(item.get_urltoimage()).into(holder.articleimage);
            Log.i("Adapter-Category", "Finished loading image into view.");

        }else{
            Log.i("Adapter-null image url", "Finished loading image into view.");
        }

        holder.title.setTag(R.id.TAG_OBJECT, item);

        holder.title.setOnClickListener(this);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_articles_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.articleTitle){
            Article item = (Article) v.getTag(R.id.TAG_OBJECT);
            Intent intent = null;
            try {
                intent = new Intent(v.getContext(), WebViewActivity.class);
                intent.putExtra("Url",item.get_url());
               v.getContext().startActivity(intent);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, description, source, url;
        ImageView articleimage;


        public ViewHolder(View v) {
            super(v);
            title = (RobotoTextView) v.findViewById(R.id.articleTitle);
            description = (TextView) v.findViewById(R.id.articledescription);
            source = (TextView) v.findViewById(R.id.articlesource);
            url = (TextView) v.findViewById(R.id.articleurl);
            articleimage = (ImageView) v.findViewById(R.id.catarticleimage);

        }
    }
}
