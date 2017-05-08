package mmwaura.newsapi.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;

import java.util.List;

import mmwaura.newsapi.R;
import mmwaura.newsapi.data.Article;

/**
 * Created by mark on 4/24/17.
 */

public class TechArticleListAdapter extends BaseAdapter implements View.OnClickListener {
    ViewHolder holder = null;
    private Activity activity;
    private LayoutInflater inflater;
    private List<Article> postsItems;
    private Intent intent;
    private ImageLoader imageLoader;

    public TechArticleListAdapter(Activity mActivity, List<Article> mPostsItems) {
        this.activity = mActivity;
        this.postsItems = mPostsItems;
    }

    @Override
    public int getCount() {
        return postsItems.size();
    }

    @Override
    public Object getItem(int location) {
        return postsItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.articles_item, null);
            holder = new ViewHolder(convertView);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Article item = (Article) getItem(position);
        holder.title.setText(item.get_title());
        holder.description.setText(item.get_desc());
        holder.source.setText(item.get_news_source_id());
        holder.url.setText(item.get_url());

        return convertView;
    }

    @Override
    public void onClick(View v) {
    }

    private class ViewHolder {
        TextView title, description, source, url;


        ViewHolder(View v) {
            title = (TextView) v.findViewById(R.id.articleTitle);
            description = (TextView) v.findViewById(R.id.articledescription);
            source = (TextView) v.findViewById(R.id.articlesource);
            url = (TextView) v.findViewById(R.id.articleurl);

        }
    }


}
