package dukaconnect.newsapi.fragments;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dukaconnect.newsapi.R;

/**
 * Created by mark on 4/24/17.
 */

public class HomeFragment extends Fragment{

    Context context;
    View view;

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        this.context = getActivity().getApplicationContext();
        view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;

    }
}
