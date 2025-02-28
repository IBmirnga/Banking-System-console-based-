package com.IBMirnga.bank.service;

import com.IBMirnga.bank.entity.User;
import com.IBMirnga.bank.repository.UserRepository;

public class UserService {
   private UserRepository userRepository = new UserRepository();

   public void printUser() {
       userRepository.printUser();
   }

   public User login(String username, String password) {
       return userRepository.login(username, password);
   }

   public boolean addNewCustomer(String username, String password, String contactNumber) {
       return userRepository.addNewCustomer(username, password, contactNumber);
   }

    public Double checkBalance(String userId) {
       return userRepository.checkBalance(userId);
    }

    public User getUSer(String userId) {
       return userRepository.getUSer(userId);
    }

    public boolean transferAmount(String userId, String payeeUserId, Double amount) {
       return userRepository.transferAmount(userId, payeeUserId, amount);
    }
}
