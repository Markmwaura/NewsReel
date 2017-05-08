package mmwaura.newsapi.adapters;

import android.app.Activity;
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

import mmwaura.newsapi.R;
import mmwaura.newsapi.WebViewActivity;
import mmwaura.newsapi.data.Article;
import mmwaura.newsapi.font.RobotoTextView;

/**
 * Created by mark on 4/24/17.
 */

public class ArticlesListAdapter extends RecyclerView.Adapter<ArticlesListAdapter.ViewHolder> implements View.OnClickListener {
    ViewHolder holder = null;
    private Activity activity;
    private LayoutInflater inflater;
    private List<Article> postsItems;
    private Intent intent;
    private ImageLoader imageLoader;

    public ArticlesListAdapter(Activity mActivity, List<Article> mPostsItems) {
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
    public void onBindViewHolder(final ArticlesListAdapter.ViewHolder holder, int position) {
        Article item = postsItems.get(position);
        holder.title.setText(item.get_title());
        holder.description.setText(item.get_desc());
        holder.source.setText(item.get_news_source_id());
        holder.url.setText(item.get_url());
        ///holder.articleimage.setImageDrawable(item.get_urltoimage());
        Picasso.with(activity).load(item.get_urltoimage()).into(holder.articleimage);
        holder.title.setTag(R.id.TAG_OBJECT, item);

        holder.title.setOnClickListener(this);

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.articles_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.articleTitle){
            Article item = (Article) v.getTag(R.id.TAG_OBJECT);
            Intent intent = new Intent(activity, WebViewActivity.class);
            intent.putExtra("Url",item.get_url());

            activity.startActivity(intent);
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
            articleimage = (ImageView) v.findViewById(R.id.articleimage);

        }
    }
}
