package org.example;

import java.util.ArrayList;

class Articleextention extends Saveable {
    private FileWorker _Article_file_Worker;

    public Articleextention() {
        fileName = "Articles.txt";
        valueType = "Article";
        _Article_file_Worker = new FileWorker(fileName, valueType);
    }

    public void SearchArticle(int user_id) {
        System.out.println("############################\n");
        ArrayList<Article> Articles = _Article_file_Worker.ReadAllArticles();
        for (int i = 0; i < Articles.size(); i++) {
            Article article = Articles.get(i);
            if (article.GetUserID() == user_id)
                System.out.print(article.ShowBrief());
            System.out.println("############################\n");
        }
    }

    public void ShowAllArticles() {
        System.out.println("############################\n");
        ArrayList<Article> articles = new ArrayList<Article>();
        articles = _Article_file_Worker.ReadAllArticles();

        for (int i = 0; i < articles.size(); i++)
            System.out.print(articles.get(i).ShowBrief());
        System.out.println("############################\n");
    }

    public void AddArticle(Article article) {
        System.out.println("############################\n");
        _Article_file_Worker.Write(article.toString(), true);
        System.out.println("############################\n");
    }

    public void EditArticle(int id, boolean wana_publish) {
        System.out.println("############################\n");
        boolean state = false;
        ArrayList<Article> articles = _Article_file_Worker.ReadAllArticles();
        for (int i = 0; i < articles.size(); i++)
            if (articles.get(i).Getid() == id && articles.get(i).GetPublish() != wana_publish) {
                state = true;
                articles.get(i).SetPublish(wana_publish);
                break;
            }

        if (state) System.out.println("Article " + (wana_publish ? "Published" : "Un Poblished"));
        else System.out.println("Article Not Found");
        OverriteFile(articles);
        System.out.println("############################\n");

    }

    private void OverriteFile(ArrayList<Article> articles) {

        String temp = "";
        for (int i = 0; i < articles.size(); i++)
            temp += articles.get(i).toString();

        _Article_file_Worker.Write(temp, false);
    }

}