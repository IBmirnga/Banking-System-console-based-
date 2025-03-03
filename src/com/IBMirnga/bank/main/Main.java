package com.IBMirnga.bank.main;

import com.IBMirnga.bank.entity.User;
import com.IBMirnga.bank.service.UserService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    static final Main main = new Main();
    static final UserService userService = new UserService();

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
                System.out.println("Incorrect username OR password");
            }
        }
    }

    private void initAdmin() {

        boolean flag = true;
        String userId = "";

        while (flag) {
            //System.out.println("You're an Admin");
            System.out.println("1, Exit/Logout");
            System.out.println("2, Create a customer account");
            System.out.println("3, See all transactions");
            System.out.println("4, Check user balance");
            System.out.println("5, Approve cheque book request");

            int selectOption = scanner.nextInt();
            switch (selectOption) {
                case 1:
                    //System.out.println("Write some logic for exit");
                    flag = false;
                    System.out.println("You have successfully logged out");
                    break;
                case 2:
                    addNewCustomer();
                    //System.out.println("add new customer");
                    break;
                case 3:
                    System.out.println("Enter user id");
                    userId = scanner.next();
                    printTransactions(userId);
                    break;
                case 4:
                    System.out.println("Enter user id");
                    userId = scanner.next();
                    double accountBalance = checkBalance(userId);
                    System.out.println("This customer's account balance is " + accountBalance);
                    break;
                case 5:
                    List<String> userIds = getChequeBookUserId();
                    System.out.println("Please select user id from below");
                    System.out.println(userIds);

                    userId = scanner.next();
                    approveChequeBookRequest(userId);
                    System.out.println("Cheque book request approved!!");
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private void approveChequeBookRequest(String userId) {
        userService.approveChequeBookRequest(userId);
    }

    public List<String> getChequeBookUserId() {
        return userService.getChequeBookUserId();
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

//            System.out.println("1, Exit/Logout");
//            System.out.println("2, Check balance");
//            System.out.println("3, Transfer fund");
//            System.out.println("4, Print transactions");
//            System.out.println("5, Make chequebook request");

            System.out.println("1, Exit/Logout");
            System.out.println("2, Check balance");
            System.out.println("3, Make withdrawal");
            System.out.println("4, Transfer fund");
            System.out.println("5, Print transactions");
            System.out.println("6, Make chequebook request");

            int selectOption = scanner.nextInt();

            switch (selectOption) {
                case 1:
                    //System.out.println("Write some logic for exit");
                    flag = false;
                    System.out.println("You have successfully logged out");
                    break;
                case 2:
                    double balance = checkBalance(user.getUsername());
                    System.out.println("Your bank balance is " + balance);
                    break;
                case 3:
                    System.out.println("Enter amount");
                    double amount = scanner.nextDouble();
                    withdrawal(user.getUsername(), amount);
                    break;
                case 4:
                    transferFund(user);
                    break;
                case 5:
                    printTransactions(user.getUsername());
                    break;
                case 6:
                    String userId = user.getUsername();
                    Map<String, Boolean> map = getALlChequeBookRequest();

                    if (map.containsKey(userId) && map.get(userId)) {
                        System.out.println("You have already raised a request and it is already approved");
                    } else if (map.containsKey(userId) && !map.get(userId)) {
                        System.out.println("You have already raised a request and it is pending approval");
                    } else {
                        chequeBookRequest(userId);
                        System.out.println("Request raised successfully");
                    }
                    break;
                default:
                    System.out.println("Wrong choice");
            }

        }
        //System.out.println("You're a Customer");
    }

    public void withdrawal(String userId, Double amount) {
        userService.withdrawal(userId, amount);
    }

    private void chequeBookRequest(String userId) {
        userService.chequeBookRequest(userId);
    }

    public Map<String, Boolean> getALlChequeBookRequest() {
        return userService.getALlChequeBookRequest();
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

    private void printTransactions(String userId) {
         userService.printTransactions(userId);
    }
}