package mmwaura.newsapi;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mmwaura.newsapi.adapters.CatArticlesListAdapter;
import mmwaura.newsapi.data.Article;
import mmwaura.newsapi.data.DatabaseHandler;

/**
 * Created by mark on 4/24/17.
 */

public class ArticleActivity extends BaseActivity {

    ListView tech_articlesList;

    Context context;
    View view;
    DatabaseHandler db;
    private RecyclerView techarticlesList;
    private List<Article> techarticleItems;
    private CatArticlesListAdapter techarticleListAdapter;
    private Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        String category = getIntent().getStringExtra("category");
        setTitle(category + " articles");
        Toast.makeText(this, category, Toast.LENGTH_LONG).show();

        techarticlesList = (RecyclerView) findViewById(R.id.tech_articlesList);

        techarticleItems = new ArrayList<Article>();
        techarticleListAdapter = new CatArticlesListAdapter(activity, techarticleItems );

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        techarticlesList.setLayoutManager(mLayoutManager);
        techarticlesList.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        //tech_articlesList.setItemAnimator(new DefaultItemAnimator());
        techarticlesList.setAdapter(techarticleListAdapter);

        db = new DatabaseHandler(this);

        List<Article> articles = db.getAllArticles(category);

            for (Article article : articles) {

                Article item = new Article();

                item.set_title(article.get_title());
                item.set_desc(article.get_desc());
                item.set_url(article.get_url());
                item.set_news_source_id(article.get_news_source_id());
                item.set_urltoimage(article.get_urltoimage());

                techarticleItems.add(item);

            }


        techarticleListAdapter.notifyDataSetChanged();
    }


}
