package mmwaura.newsapi.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import mmwaura.newsapi.R;
import mmwaura.newsapi.adapters.TechArticlesListAdapter;
import mmwaura.newsapi.data.DatabaseHandler;
import mmwaura.newsapi.data.Source;

/**
 * Created by mark on 4/24/17.
 */

public class TechArticlesFragment extends Fragment {
    Context context;
    View view;
    DatabaseHandler db;
    private RecyclerView tech_articleList;
    private List<Source> tech_articleItems;
    private TechArticlesListAdapter tech_articleListAdapter;
    private String category;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        this.context = getActivity().getApplicationContext();
        view = inflater.inflate(R.layout.fragment_techarticles, container, false);


        initializeView();
        tech_articleItems = new ArrayList<Source>();
        tech_articleListAdapter = new TechArticlesListAdapter(getActivity(), tech_articleItems);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        tech_articleList.setLayoutManager(mLayoutManager);
        //recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        tech_articleList.setItemAnimator(new DefaultItemAnimator());
        tech_articleList.setAdapter(tech_articleListAdapter);


        db = new DatabaseHandler(context);
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            category = bundle.getString("category", "general");
        }

        new addList().execute();


        return view;
    }

    private void initializeView() {
        tech_articleList = (RecyclerView) view.findViewById(R.id.list_tech_articles_sources);

    }

    private class addList extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            List<Source> sources = db.getAllSources(category);

            for (Source cns : sources) {

                Source item = new Source();

                item.set_id(cns.get_id());
                item.set_name(cns.get_name());
                item.set_category(cns.get_category());


                tech_articleItems.add(item);

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            tech_articleListAdapter.notifyDataSetChanged();

        }
    }
}
