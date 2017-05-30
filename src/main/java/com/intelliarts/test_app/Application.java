package com.intelliarts.test_app;

import com.intelliarts.test_app.controller.Dispatcher;

import java.util.Scanner;

public class Application {
    public static void main (String [] args){
        Scanner commandReader = new Scanner(System.in);
        Dispatcher dispatcher = new Dispatcher();
        String userInput = "";
        while(!userInput.equals("exit")){
            System.out.println("\nEnter command: ");
            userInput = commandReader.nextLine();
            dispatcher.commandIdentification(userInput.trim());
        }
    }
}
