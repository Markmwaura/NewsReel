package dukaconnect.newsapi.data;

/**
 * Created by mark on 4/24/17.
 */

public class Source {
    String _id;
    String _name;
    String _description;
    String _url;
    String _category;

    public Source(String id, String name, String description, String url, String category) {
        this._id = id;
        this._name = name;
        this._description = description;
        this._url = description;
        this._category = category;


    }

    public Source(){

    }


    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }

    public String get_url() {
        return _url;
    }

    public void set_url(String _url) {
        this._url = _url;
    }

    public String get_category() {
        return _category;
    }

    public void set_category(String _category) {
        this._category = _category;
    }
}
