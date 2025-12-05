package org.example;

public class Program {

    public static void main(String[] args) {
        if(args.length < 3) {
            System.out.println("Success");
            return;
        }

        String url  = args[0];
        String user = args[1];
        String pass = args[2];

        UserInterface ui = new UserInterface(url, user, pass);
        ui.display(); //show main menu
    }

}
