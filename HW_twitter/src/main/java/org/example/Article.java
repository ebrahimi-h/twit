package org.example;


class Article{

    private int _id, _user_id;
    private String _title, _brief, _create_date, _content;
    private boolean _is_published;

    public Article(int id, String title, String brief, String content, String create_date, boolean is_published, int user_id){
        this(id, title, brief, content, create_date, user_id);
        _is_published = is_published;
    }

    public Article(int id, String title, String brief, String content, String create_date, int user_id){
        _id = id;
        _title = title;
        _brief = brief;
        _create_date = create_date;
        _user_id = user_id;
        _content = content;
        _is_published = false;

    }

    public void Setid(int id){
        _id = id;
    }
    public int Getid(){
        return _id;
    }
    public void SetTitle(String title){
        _title = title;
    }
    public String GetTitle(){
        return _title;
    }
    public void SetBrief(String biref){
        _brief = biref;
    }
    public String GetBrief(){
        return _brief;
    }
    public void SetUserID(int user_id){
        _user_id = user_id;
    }
    public int GetUserID(){
        return _user_id;
    }
    public void SetContent(String content){
        _content = content;
    }
    public String GetContent(){
        return _content;
    }

    public void SetCreateDate(String create_date){
        _create_date = create_date;
    }
    public String GetCreateDate(){
        return _create_date;
    }
    public boolean GetPublish(){
        return _is_published;
    }
    public void SetPublish(boolean state){
        _is_published = state;
    }

    public String ShowBrief(){
        return "\nID: " + _id + " - Title: " + _title + "\nBrief: " + _brief + "\n" + (_is_published ? "Published" : "Not Published") + "\n";
    }

    @Override
    public String toString() {
        return String.format(_id + "|" + _title + "|" + _brief + "|" + _content + "|" + _create_date + "|" + _is_published + "|" + _user_id + "\n");
    }

}
