package mmwaura.newsapi.app;

/**
 * Created by mark on 4/24/17.
 */
public class AppConstants {
    public static final String TAG = "news";
    public static final String MAINSOURCES_URL = "https://newsapi.org/v1/sources";
    public static final String MAINARTICLES_URL = "https://newsapi.org/v1/articles?apiKey=df26d28a5e3247cc8dddb492097613ce";

    public static final String MAINARTICLES_VAR_URL = MAINARTICLES_URL + "&source=";

    public static final String ARTICLES_URL = "https://newsapi.org/v1/articles?apiKey=df26d28a5e3247cc8dddb492097613ce&sortBy=latest&source=";
}