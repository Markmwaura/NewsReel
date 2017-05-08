package mmwaura.newsapi.data;

/**
 * Created by mark on 4/23/17.
 */

public class Article {
    String _news_source_id;
    String _title;
    String _desc;
    String _url;
    String _urltoimage;


    // constructor
    public Article(String news_source_id, String title, String desc, String url, String urltoimage) {
        this._news_source_id = news_source_id;
        this._title = title;
        this._desc = desc;
        this._url = url;
        this._urltoimage = urltoimage;
    }

    public Article(){

    }


    public String get_news_source_id() {
        return _news_source_id;
    }

    public void set_news_source_id(String _news_source_id) {
        this._news_source_id = _news_source_id;
    }

    public String get_title() {
        return _title;
    }

    public void set_title(String _title) {
        this._title = _title;
    }

    public String get_desc() {
        return _desc;
    }

    public void set_desc(String _desc) {
        this._desc = _desc;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }

    public String get_urltoimage() {
        return _urltoimage;
    }

    public void set_urltoimage(String _urltoimage) {
        this._urltoimage = _urltoimage;
    }

}
