package com.IBMirnga.bank.repository;

import com.IBMirnga.bank.entity.User;

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
}
