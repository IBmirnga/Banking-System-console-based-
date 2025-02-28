package com.IBMirnga.bank.repository;

import com.IBMirnga.bank.entity.Transaction;
import com.IBMirnga.bank.entity.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserRepository {
    private static Set<User> users = new HashSet<>();

    static {
        User user1 = new User("admin", "admin", "080123456", "admin", 0.0);
        User user2 = new User("user2", "user2", "12345", "user", 1000.0);
        User user3 = new User("user3", "user3", "123456", "user", 2000.0);
        User user4 = new User("user4", "user4", "123456", "user", 3000.0);

        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
    }

    public void printUser() {
        System.out.println(users);
    }

    public User login(String username, String password) {
     List<User> loginList = users.stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .toList();

     if (!loginList.isEmpty()) {
         return loginList.getFirst();
     } else {
         return null;
     }
    }

    public boolean addNewCustomer(String username, String password, String contactNumber) {
        User user = new User(username, password, contactNumber, "user", 500.0);
        return users.add(user);
    }

    public Double checkBalance(String userId) {
       List<User> result = users.stream()
                .filter(user -> user.getUsername().equals(userId))
                .toList();

       if (!result.isEmpty()) {
           return result.getFirst().getBalance();
       } else {
           return null;
       }
    }

    public User getUSer(String userId) {
        List<User> result = users.stream()
                .filter(user -> user.getUsername().equals(userId))
                .toList();

        if (!result.isEmpty()) {
            return result.getFirst();
        } else {
            return null;
        }
    }

    public boolean transferAmount(String userId, String payeeUserId, Double amount) {
       boolean isDebit = debit(userId, amount);
       boolean isCredit = credit(payeeUserId, amount);

       return isDebit && isCredit;
    }

    private boolean debit(String userId, Double amount, String payeeUserId) {
        User user = getUSer(userId);
        Double accountBalance = user.getBalance();

        users.remove(user);

        Double finalBalance = accountBalance - amount;
        user.setBalance(finalBalance);

        Transaction transaction = new Transaction(
                LocalDate.now(),
                payeeUserId,
                amount,
                "Debit",
                accountBalance,
                finalBalance
        );

        return users.add(user);
    }

    private boolean credit(String payeeUserId, Double amount, String userId) {
        User user = getUSer(userId);
        Double accountBalance = user.getBalance();

        users.remove(user);

        Double finalBalance = accountBalance + amount;
        user.setBalance(finalBalance);

        return users.add(user);
    }
}
