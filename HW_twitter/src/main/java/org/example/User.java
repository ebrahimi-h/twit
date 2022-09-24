package org.example;

import java.util.ArrayList;

class User {
    private int _id, _national_code;
    private String _username, _password, _birth_day;


    public User(int id, String username, int nationalCode, String birth_day, String password)
    {
        _id = id;
        _national_code = nationalCode;
        _username = username;
        _birth_day = birth_day;
        _password = password;
    }

    public void SetID(int id){
        _id = id;
    }
    public int GetID(){
        return _id;
    }
    public void SetBirthday(String birthday){
        _birth_day = birthday;
    }
    public String GetBirthday(){
        return _birth_day;
    }
    public void SetNationalCode(int nationalCode){
        _national_code = nationalCode;
    }
    public int GetNationalCode(){
        return _national_code;
    }
    public void SetUsername(String username){
        _username = username;
    }
    public String GetUsername(){
        return _username;
    }
    public void SetPassword(String password){
        _password = password;
    }
    public String GetPassword(){
        return _password;
    }


    @Override
    public String toString() {
        return String.format(_id + "|" + _username + "|" + _national_code + "|" + _birth_day + "|" + _password + "\n");
    }
}

class UserExtention extends Saveable{

    private FileWorker _user_file_Worker;
    public UserExtention(){
        fileName = "users.txt";
        valueType = "User";
        _user_file_Worker = new FileWorker(fileName, valueType);
    }

    public void AddUser(User user){
        _user_file_Worker.Write(user.toString(), true);
    }

    public int SearchUser(String username, String password){

        System.out.println("############################\n");
        ArrayList<User> users = _user_file_Worker.ReadAllUsers();
        int id = 0;

        for (int i = 0; i < users.size(); i++){
            User user = users.get(i);
            if(user.GetUsername().equals(username) && user.GetPassword().equals(password))
                id = user.GetID();
        }


        if(id == 0) System.out.println("User Not Found");
        else System.out.println("Welcome " + username);
        System.out.println("############################\n");

        return id;
    }

    public void EditUser(int id, String new_password){
        System.out.println("############################\n");
        boolean state = false;
        ArrayList<User> users = _user_file_Worker.ReadAllUsers();
        for (int i = 0; i < users.size(); i++)
            if(users.get(i).GetID() == id){
                users.get(i).SetPassword(new_password);
                break;
            }
        System.out.println("User Edited");

        OverriteFile(users);
        System.out.println("############################\n");
    }


    private void OverriteFile(ArrayList<User> users){
        String temp = "";
        for (int i = 0; i < users.size(); i++)
            temp += users.get(i).toString();

        _user_file_Worker.Write(temp, false);
    }
}

class ArticleExtention extends Saveable{
    private FileWorker _Article_file_Worker;

    public ArticleExtention() {
        fileName = "Articles.txt";
        valueType = "Article";
        _Article_file_Worker = new FileWorker(fileName, valueType);
    }

    public void SearchArticle(int user_id){
        System.out.println("############################\n");
        ArrayList<Article> Articles = _Article_file_Worker.ReadAllArticles();
        for (int i = 0; i < Articles.size(); i++){
            Article article = Articles.get(i);
            if(article.GetUserID() == user_id)
                System.out.print(article.ShowBrief());
            System.out.println("############################\n");
        }
    }
    public void ShowAllArticles(){
        System.out.println("############################\n");
        ArrayList<Article> articles = new ArrayList<Article>();
        articles = _Article_file_Worker.ReadAllArticles();

        for (int i = 0; i < articles.size(); i++)
            System.out.print(articles.get(i).ShowBrief());
        System.out.println("############################\n");
    }
    public void AddArticle(Article article){
        System.out.println("############################\n");
        _Article_file_Worker.Write(article.toString(), true);
        System.out.println("############################\n");
    }

    public void EditArticle(int id, boolean wana_publish){
        System.out.println("############################\n");
        boolean state = false;
        ArrayList<Article> articles = _Article_file_Worker.ReadAllArticles();
        for (int i = 0; i < articles.size(); i++)
            if(articles.get(i).Getid() == id && articles.get(i).GetPublish() != wana_publish)
            {
                state = true;
                articles.get(i).SetPublish(wana_publish);
                break;
            }

        if(state) System.out.println("Article " + (wana_publish ? "Published": "Un Poblished"));
        else System.out.println("Article Not Found");
        OverriteFile(articles);
        System.out.println("############################\n");

    }

    private void OverriteFile(ArrayList<Article> articles){

        String temp = "";
        for (int i = 0; i < articles.size(); i++)
            temp += articles.get(i).toString();

        _Article_file_Worker.Write(temp, false);
    }

}