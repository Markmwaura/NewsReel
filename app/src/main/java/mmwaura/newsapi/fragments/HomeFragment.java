package mmwaura.newsapi.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mmwaura.newsapi.R;
import mmwaura.newsapi.adapters.ArticlesListAdapter;
import mmwaura.newsapi.data.Article;
import mmwaura.newsapi.data.DatabaseHandler;

/**
 * Created by mark on 4/24/17.
 */

public class HomeFragment extends Fragment {

    Context context;
    View view;


    private RecyclerView articleList;
    private List<Article> articleItems;
    private ArticlesListAdapter articleListAdapter;
    DatabaseHandler db;
    private RecyclerView recyclerView;


    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        this.context = getActivity().getApplicationContext();
        view = inflater.inflate(R.layout.fragment_home, container, false);

        initializeView();
        articleItems = new ArrayList<Article>();
        articleListAdapter = new ArticlesListAdapter(getActivity(), articleItems);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        articleList.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        articleList.setItemAnimator(new DefaultItemAnimator());
        articleList.setAdapter(articleListAdapter);

        db = new DatabaseHandler(context);

        List<Article> articles = db.getAllArticles();

        for (Article article : articles) {

            Article item = new Article();

            item.set_title(article.get_title());
            item.set_desc(article.get_desc());
            item.set_url(article.get_url());
            item.set_news_source_id(article.get_news_source_id());
            item.set_urltoimage(article.get_urltoimage());

            articleItems.add(item);

        }
        articleListAdapter.notifyDataSetChanged();

        return view;

    }

    private void initializeView() {
        articleList = (RecyclerView) view.findViewById(R.id.allarticlesList);

    }


}
