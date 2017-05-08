package mmwaura.newsapi.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import mmwaura.newsapi.R;

/**
 * Created by mark on 4/24/17.
 */

public class ArticleFragment extends Fragment {

    Context context;
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        this.context = getActivity().getApplicationContext();
        view = inflater.inflate(R.layout.activity_articles, container, false);
        return view;
    }
}
