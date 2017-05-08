package mmwaura.newsapi;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import mmwaura.newsapi.adapters.TechArticleListAdapter;
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
    private ListView techarticleList;
    private List<Article> techarticleItems;
    private TechArticleListAdapter techarticleListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        String category = getIntent().getStringExtra("category");
        Toast.makeText(this, category, Toast.LENGTH_LONG).show();

        tech_articlesList = (ListView) findViewById(R.id.tech_articlesList);

        techarticleItems = new ArrayList<Article>();
        techarticleListAdapter = new TechArticleListAdapter(this, techarticleItems);

        tech_articlesList.setAdapter(techarticleListAdapter);

        db = new DatabaseHandler(this);

        List<Article> articles = db.getAllArticles(category);

            for (Article article : articles) {

                Article item = new Article();

                item.set_title(article.get_title());
                item.set_desc(article.get_desc());
                item.set_url(article.get_url());
                item.set_news_source_id(article.get_news_source_id());

                techarticleItems.add(item);

            }


        techarticleListAdapter.notifyDataSetChanged();
    }


}
