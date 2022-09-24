package org.example;
import java.util.Scanner;

public class Main {
    static User GetUserFromInput() {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter You'r Information Below:");

        int national_code = 0, id = 0;
        String username = "", password = "", birthday = "";
        System.out.print("ID: ");
        id = input.nextInt();

        System.out.print("National Number: ");
        national_code = input.nextInt();

        input.nextLine();

        System.out.print("Username: ");
        username = input.nextLine();

        System.out.print("Password: ");
        password = input.nextLine();

        System.out.print("Birthday: ");
        birthday = input.nextLine();

        return new User(id, username, national_code, birthday, password);
    }

    static Article GetArticleFromInput(int user_id) {

        Scanner input = new Scanner(System.in);

        System.out.println("Enter Article Informations Below:");

        int id = 0;
        String create_date = "", title = "", brief = "", content = "";


        System.out.println("id: ");
        id = input.nextInt();

        input.nextLine();

        System.out.print("Title: ");
        title = input.nextLine();

        System.out.print("Brief: ");
        brief = input.nextLine();

        System.out.print("Content: ");
        content = input.nextLine();

        System.out.print("Create Date: ");
        create_date = input.nextLine();

        return new Article(id, title, brief, content, create_date, user_id);
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static int MainMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println("***Article Manager***");
        System.out.println("-1-Clear Screen");
        System.out.println("1-Sign In");
        System.out.println("2-Sign Up");
        System.out.println("3-Visit Articles");

        System.out.println("0-Exit");
        System.out.print("Choice: ");
        int choice = input.nextInt();
        return choice;
    }

    static int UserMenu() {
        Scanner input = new Scanner(System.in);

        System.out.println("***Article Manager***");
        System.out.println("-1-Clear Screen");
        System.out.println("1-Update Password");
        System.out.println("2-See My Articles");
        System.out.println("3-Add New Article");
        System.out.println("4-Publish Article");
        System.out.println("5-UnPublish Article");


        System.out.println("0-Exit");
        System.out.print("Choice: ");
        int choice = input.nextInt();
        return choice;
    }

    public static void main(String[] args) {
        int _user_id = 0;
        String _user_name = "";

        clearScreen();
        Scanner input = new Scanner(System.in);
        userExtention userWorker = new userExtention();
        articleExtention articleWorker = new articleExtention();

        while (1 == 1) {
            if (_user_id != 0)
                break;

            int choice = MainMenu();
            clearScreen();

            if (choice == 0) {
                clearScreen();
                System.out.println("Exit Program");
                break;
            }
            String password = "";
            switch (choice) {
                case -1:
                    clearScreen();
                    break;

                case 1:
                    System.out.println("Enter Username: ");
                    String username = input.nextLine();

                    System.out.println("Enter Password: ");
                    password = input.nextLine();

                    _user_id = userWorker.SearchUser(username, password);
                    _user_name = username;
                    break;

                case 2:
                    User user = GetUserFromInput();
                    userWorker.AddUser(user);
                    break;

                case 3:
                    articleWorker.ShowAllArticles();
                    break;
            }
        }

        clearScreen();
        clearScreen();
        while (1 == 1) {

            int choice = UserMenu();
            clearScreen();

            if (choice == 0) {
                clearScreen();
                System.out.println("Exit Program");
                break;
            }
            int _id = 0;
            String password = "";
            switch (choice) {
                case -1:
                    clearScreen();
                    break;
                case 1:
                    System.out.println("Enter New Password For Update You'r Account: ");
                    password = input.nextLine();
                    userWorker.EditUser(_user_id, password);
                    break;

                case 2:
                    articleWorker.SearchArticle(_user_id);
                    break;

                case 3:
                    Article Article = GetArticleFromInput(_user_id);
                    articleWorker.AddArticle(Article);
                    break;

                case 4:
                    System.out.println("Enter Article id For Publish it:");
                    _id = input.nextInt();
                    articleWorker.EditArticle(_id, true);
                    break;

                case 5:
                    System.out.println("Enter Article id For Un Publish it:");
                    _id = input.nextInt();
                    articleWorker.EditArticle(_id, false);
                    break;
            }

        }

    }

}