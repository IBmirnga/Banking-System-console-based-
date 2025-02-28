package com.IBMirnga.bank.main;

import com.IBMirnga.bank.entity.User;
import com.IBMirnga.bank.service.UserService;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    static Main main = new Main();
    static UserService userService = new UserService();

    public static void main(String[] args) {

        //UserService userService = new UserService();

        while (true) {
            System.out.println("Enter your username");
            String username = scanner.next();

            System.out.println("Enter your password");
            String password = scanner.next();

            //System.out.println("Username: "+ username + "Password: " + password);

            //userService.printUser();

            User user = userService.login(username, password);
            if (user != null && user.getRole().equals("admin")) {
                main.initAdmin();
                //System.out.println("You're Logged in Successfully!!");
            } else if (user != null && user.getRole().equals("user")) {
                main.initCustomer(user);
            } else {
                System.out.println("Login Failed!!");
            }
        }
    }

    private void initAdmin() {

        boolean flag = true;

        while (flag) {
            //System.out.println("You're an Admin");
            System.out.println("1, Exit/Logout");
            System.out.println("2, Create a customer account");

            int selectOption = scanner.nextInt();

            switch (selectOption) {
                case 1:
                    //System.out.println("Write some logic for exit");
                    flag = false;
                    System.out.println("You have successfully logged out");
                    break;
                case 2:
                    main.addNewCustomer();
                    //System.out.println("add new customer");
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    public void addNewCustomer() {
        System.out.println("Enter username");
        String username = scanner.next();

        System.out.println("Enter password");
        String password = scanner.next();

        System.out.println("Enter contact number");
        String contactNumber = scanner.next();

        boolean result = userService.addNewCustomer(username, password, contactNumber);

        if (result) {
            System.out.println("Customer account is created...");
        } else {
            System.out.println("Customer account creation failed...");
        }
    }

    private void initCustomer(User user) {
        boolean flag = true;

        while (flag) {

            System.out.println("1, Exit/Logout");
            System.out.println("2, Check balance");
            System.out.println("3, Transfer fund");

            int selectOption = scanner.nextInt();

            switch (selectOption) {
                case 1:
                    //System.out.println("Write some logic for exit");
                    flag = false;
                    System.out.println("You have successfully logged out");
                    break;
                case 2:
                    double balance = main.checkBalance(user.getUsername());
                    System.out.println("Your bank balance is " + balance);
                    break;
                case 3:
                    main.transferFund(user);
                    break;
                default:
                    System.out.println("Wrong choice");
            }

        }
        //System.out.println("You're a Customer");
    }

    private void transferFund(User userDetails) {
        System.out.println("Enter payee user id");
        String payeeAccountId = scanner.next();

        User user = getUSer(payeeAccountId);

        if (user != null) {
            System.out.println("Enter amount to transfer");
            double amount = scanner.nextDouble();

            double userAccountBalance = checkBalance(userDetails.getUsername());

            if (userAccountBalance >= amount) {
                boolean result = userService.transferAmount(userDetails.getUsername(), payeeAccountId, amount);

                if (result) {
                    System.out.println("Amount transferred successfully!!");
                } else {
                    System.out.println("Transfer failed...");
                }
            } else {
                System.out.println("Insufficient Balance!!");
            }

        } else {
            System.out.println("Please enter valid username");
        }
    }

    private User getUSer(String userId) {
        return userService.getUSer(userId);
    }

    private double checkBalance(String userId) {
        return userService.checkBalance(userId);
    }
}