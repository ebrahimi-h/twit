package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class FileWorker{

    private String _valueType;
    private String _fileName;
    FileWorker(String fileName, String valueType){
        final int i = 2;
        _fileName = fileName;
        _valueType = valueType;
    }

    private void ShowException(){
        System.out.println("An error occurred When Write on " + _valueType);
    }
    public void Write(String value, boolean state){
        try (FileWriter _writer = new FileWriter(_fileName, state))
        {
            _writer.write(value);
            System.out.println(_valueType + " Inserted Successfully.");
        }
        catch (IOException e) {
            ShowException();
            e.printStackTrace();
        }
    }

    private int SeparateIndex(String value){
        return value.indexOf('|');
    }
    private String Separate(String value){
        return value.substring(SeparateIndex(value) + 1, value.length());
    }
    private String GetNext(String value){
        return value.substring(0, SeparateIndex(value));
    }

    private Article ArticleSegmentor(String value){
        String temp = value;
        int _id = Integer.parseInt(GetNext(temp));
        temp = Separate(temp);

        String title = GetNext(temp);
        temp = Separate(temp);

        String brief = GetNext(temp);
        temp = Separate(temp);

        String content = GetNext(temp);
        temp = Separate(temp);

        String create_date = GetNext(temp);
        temp = Separate(temp);

        boolean _is_published = GetNext(temp).equals("false") ? false: true;
        temp = Separate(temp);

        int _user_id = Integer.parseInt(temp);

        return new Article(_id, title ,brief ,content ,create_date ,_is_published ,_user_id);
    }

    private User UserSegmentor(String value){
        String temp = value;
        int id = Integer.parseInt(GetNext(temp));

        temp = Separate(temp);
        String username = GetNext(temp);
        temp = Separate(temp);
        int nationalCode = Integer.parseInt(GetNext(temp));
        temp = Separate(temp);
        String birth_day = GetNext(temp);
        temp = Separate(temp);
        String password = temp;

        return new User(id, username, nationalCode, birth_day, password);
    }

    public ArrayList<Article> ReadAllArticles(){
        ArrayList<Article> Articles = new ArrayList<Article>();

        try
        {
            File _file = new File(_fileName);
            Scanner myReader = new Scanner(_file);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                Articles.add(ArticleSegmentor(data));
            }
            myReader.close();
        }
        catch (FileNotFoundException e){
            ShowException();
            e.printStackTrace();
        }

        return Articles;
    }

    public ArrayList<User> ReadAllUsers(){
        ArrayList<User> users = new ArrayList<User>();

        try
        {
            File _file = new File(_fileName);
            Scanner myReader = new Scanner(_file);
            while (myReader.hasNextLine())
            {
                String data = myReader.nextLine();
                users.add(UserSegmentor(data));
            }
            myReader.close();
        }
        catch (FileNotFoundException e){
            ShowException();
            e.printStackTrace();
        }

        return users;
    }
}