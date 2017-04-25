package dukaconnect.newsapi;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import dukaconnect.newsapi.app.AppController;
import dukaconnect.newsapi.data.Article;
import dukaconnect.newsapi.data.DatabaseHandler;
import dukaconnect.newsapi.data.Source;
import dukaconnect.newsapi.fragments.HomeFragment;
import dukaconnect.newsapi.fragments.TechArticlesFragment;
import dukaconnect.newsapi.fragments.TechSourcesFragment;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public FirebaseAuth auth;
    DrawerLayout drawer;
    FirebaseUser user;
    TextView welcometext;
    MainSignInActivity mainSignInActivity = new MainSignInActivity();
    DatabaseHandler db;
    private Fragment fragment = null;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);
        welcometext = (TextView) findViewById(R.id.welcome_email);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        onNavigationItemSelected(navigationView.getMenu().findItem(R.id.nav_all));
        View header = ((NavigationView) findViewById(R.id.nav_view)).getHeaderView(0);
        TextView tv = ((TextView) header.findViewById(R.id.welcome_email));

        tv.setText(" Hi " + MainSignInActivity.auth.getCurrentUser().getEmail());
        drawer.openDrawer(Gravity.START);

        // newRequestSources(AppConstants.MAINSOURCES_URL);

        ///newRequestArticles(AppConstants.THENEXTWEB_URL);

        List<Source> sources = db.getAllSources();
        List<Article> articles = db.getAllArticles();

//        for (Source sc : sources) {
//            newRequestArticles(AppConstants.ARTICLES_URL+sc.get_id() );
//
//        }

        for (Article cns : articles) {
            String log = "News source id: " + cns.get_news_source_id() + " Url: " + cns.get_url();
            // Writing Contacts to log
            Log.d("articles: ", log);

        }


    }


    public void newRequestArticles(String url) {
        Log.i("amark", "here");
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("amike", response.toString());

                if (response != null) {

                    parseJsonArticles(response);
                    Log.i("amike", "here");
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("amike", error.toString());
            }
        });
        AppController.getInstance().getRequestQueue().add(jsonReq);
    }

    public void parseJsonArticles(JSONObject response) {


        try {

            JSONArray feedArray = response.getJSONArray("articles");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject articleObj = (JSONObject) feedArray.get(i);

                db.addArticle(
                        new Article(response.getString("source"), articleObj.getString("title"), articleObj.getString("description"), articleObj.getString("url"), articleObj.getString("urlToImage")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void newRequestSources(String url) {
        Log.i("mark", "here");
        JsonObjectRequest jsonReq = new JsonObjectRequest(Request.Method.GET, url,
                null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Log.i("mike", response.toString());

                if (response != null) {

                    parseJson(response);
                    Log.i("mike", "here");
                }
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("mike", error.toString());
            }
        });
        AppController.getInstance().getRequestQueue().add(jsonReq);
    }

    public void parseJson(JSONObject response) {


        try {
            JSONArray feedArray = response.getJSONArray("sources");

            for (int i = 0; i < feedArray.length(); i++) {
                JSONObject sourcesObj = (JSONObject) feedArray.get(i);
                //Log.i(postObj.getString("id"), postObj.getString("category"));
                db.addSource(new Source(sourcesObj.getString("id"),
                        sourcesObj.getString("name"), sourcesObj.getString("description"),
                        sourcesObj.getString("url"), sourcesObj.getString("category")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new AlertDialog.Builder(this)
                    .setTitle("Really Exit?")
                    .setMessage("Are you sure you want to exit?")
                    .setNegativeButton(android.R.string.no, null)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface arg0, int arg1) {
                            signOut();

                            MainActivity.super.onBackPressed();
                        }
                    }).create().show();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {
            fragment = new HomeFragment();
            setTitle("All Articles");

        } else if (id == R.id.nav_techsources) {
            fragment = new TechSourcesFragment();
            setTitle("All technology Sources");

        } else if (id == R.id.nav_techsource_article) {
            fragment = new TechArticlesFragment();
            setTitle("Articles from Source");

        } else if (id == R.id.nav_signout) {
            signOut();

        }


        if (fragment != null) {

            drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            drawer.closeDrawer(GravityCompat.START);
            fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frameContainer, fragment).commit();
        }

        return true;
    }

    private void signOut() {
        // Firebase sign out

        MainSignInActivity.signOut();
        // Clear previous activities and return to login screen.

        Intent intent = new Intent(this, MainSignInActivity.class);
        intent.putExtra("finish", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
        startActivity(intent);
        finish();

    }

}
