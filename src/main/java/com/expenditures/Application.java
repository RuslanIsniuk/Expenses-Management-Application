package com.expenditures;

import com.expenditures.controller.Dispatcher;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        Scanner commandReader = new Scanner(System.in);
        Dispatcher dispatcher = new Dispatcher();
        String userInput = "";

        System.out.println("Type \"help\" to get list of commands.");

        while (!userInput.equals("exit")) {
            System.out.println("\nEnter command: ");
            userInput = commandReader.nextLine();
            dispatcher.commandIdentification(userInput.trim());
        }
    }
}
