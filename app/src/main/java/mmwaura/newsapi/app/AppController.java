package mmwaura.newsapi.app;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


/**
 * Created by mark on 4/23/17.
 */

public class AppController extends Application {

    public static AppController appInstance;
    public static final String TAG = AppController.class.getSimpleName();
    private RequestQueue requestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        appInstance = this;
    }

    public static synchronized AppController getInstance() {
        return appInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return requestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
}
