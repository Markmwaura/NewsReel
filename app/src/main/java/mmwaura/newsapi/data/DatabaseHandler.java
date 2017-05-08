package mmwaura.newsapi.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mark on 4/24/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Versio
    private static final int DATABASE_VERSION = 3;

    // Database Name
    private static final String DATABASE_NAME = "NewsDatabase";

    // Sources table name
    private static final String TABLE_SOURCES = "sources";

    // Articles table name
    private static final String TABLE_ARTICLES = "articles";

    private static final String KEY_ID_SOURCE = "_id";
    private static final String KEY_ID_ARTICLE = "_id";
    // Sources Table
    private static final String SOURCE_ID = "id";
    private static final String SOURCE_NAME = "name";
    private static final String SOURCE_DESCRIPTION = "description";
    private static final String SOURCE_URL = "url";
    private static final String SOURCE_CATEGORY = "category";

    //Articles Table
    private static final String ARTICLE_NEWSSOURCE_ID = "id";
    private static final String ARTICLE_TITLE = "title";
    private static final String ARTICLE_DESCRIPTION = "description";
    private static final String ARTICLE_URL = "url";
    private static final String ARTICLE_URLTOIMAGE = "urltoimage";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_SOURCES_TABLE = "CREATE TABLE " + TABLE_SOURCES + "("
                + KEY_ID_SOURCE + " INTEGER PRIMARY KEY," + SOURCE_ID + " TEXT,"
                + SOURCE_NAME + " TEXT," + SOURCE_DESCRIPTION + " TEXT,"
                + SOURCE_URL + " TEXT,"
                + SOURCE_CATEGORY + " TEXT"
                + ")";

        String CREATE_ARTICLES_TABLE = "CREATE TABLE " + TABLE_ARTICLES + "("
                + KEY_ID_ARTICLE + " INTEGER PRIMARY KEY," + ARTICLE_NEWSSOURCE_ID + " TEXT,"
                + ARTICLE_TITLE + " TEXT,"
                + ARTICLE_DESCRIPTION + " TEXT,"
                + ARTICLE_URL + " TEXT,"
                + ARTICLE_URLTOIMAGE + " TEXT"
                + ")";


        db.execSQL(CREATE_SOURCES_TABLE);
        db.execSQL(CREATE_ARTICLES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SOURCES);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ARTICLES);

        // Create tables again
        onCreate(db);

    }

    //Add sources to table
    public void addSource(Source source) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(SOURCE_ID, source.get_id());
        cValues.put(SOURCE_NAME, source.get_name());
        cValues.put(SOURCE_DESCRIPTION, source.get_description());
        cValues.put(SOURCE_URL, source.get_url());
        cValues.put(SOURCE_CATEGORY, source.get_category());
        // Inserting Row
        db.insert(TABLE_SOURCES, null, cValues);
        db.close(); // Close database connection


    }

    //Add sources to table
    public void addArticle(Article article) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cValues = new ContentValues();
        cValues.put(ARTICLE_NEWSSOURCE_ID, article.get_news_source_id());
        cValues.put(ARTICLE_TITLE, article.get_title());
        cValues.put(ARTICLE_DESCRIPTION, article.get_desc());
        cValues.put(ARTICLE_URL, article.get_url());
        cValues.put(ARTICLE_URLTOIMAGE, article.get_urltoimage());
        // Inserting Row
        db.insert(TABLE_ARTICLES, null, cValues);
        db.close(); // Close database connection


    }

    // Getting All Sources
    public List<Source> getAllSources() {
        List<Source> sourceList = new ArrayList<Source>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SOURCES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Source source = new Source();
                source.set_id(cursor.getString(1));
                source.set_name(cursor.getString(2));
                source.set_description(cursor.getString(3));
                source.set_url(cursor.getString(4));
                source.set_category(cursor.getString(5));

                // Adding article to list
                sourceList.add(source);
            } while (cursor.moveToNext());
        }

        // return sources list
        return sourceList;
    }

    public List<Source> getAllSources(String sourcecategory){
        List<Source> tech_articleList = new ArrayList<Source>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SOURCES,null, SOURCE_CATEGORY + "=?",
                new String[] { sourcecategory }, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Source source = new Source();
                source.set_category(cursor.getString(1));

                tech_articleList.add(source);
            } while (cursor.moveToNext());
        }
        return tech_articleList;

    }

    public List<Article> getAllArticles(String sourcecategory){
        List<Article> tech_articleList = new ArrayList<Article>();

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_ARTICLES,null, ARTICLE_NEWSSOURCE_ID + "=?",
                new String[] { sourcecategory }, null, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                Article article = new Article();
                article.set_news_source_id(cursor.getString(1));
                article.set_title(cursor.getString(2));
                article.set_desc(cursor.getString(3));
                article.set_url(cursor.getString(4));
                article.set_urltoimage(cursor.getString(5));

                tech_articleList.add(article);
            } while (cursor.moveToNext());
        }
        return tech_articleList;

    }


    // Getting All articles
    public List<Article> getAllArticles() {
        List<Article> articleList = new ArrayList<Article>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_ARTICLES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Article article = new Article();
                article.set_news_source_id(cursor.getString(1));
                article.set_title(cursor.getString(2));
                article.set_desc(cursor.getString(3));
                article.set_url(cursor.getString(4));
                article.set_urltoimage(cursor.getString(5));


                // Adding article to list
                articleList.add(article);
            } while (cursor.moveToNext());
        }

        // return article list
        return articleList;
    }


}
