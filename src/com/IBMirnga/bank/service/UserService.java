package com.IBMirnga.bank.service;

import com.IBMirnga.bank.entity.User;
import com.IBMirnga.bank.repository.UserRepository;

import java.util.List;
import java.util.Map;

public class UserService {
   private final UserRepository userRepository = new UserRepository();

   public void printUser() {
       userRepository.printUser();
   }

   public User login(String username, String password) {
       return userRepository.login(username, password);
   }

   public boolean addNewCustomer(String username, String password, String contactNumber, Double amount) {
       return userRepository.addNewCustomer(username, password, contactNumber, amount);
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

    public void withdrawal(String userId, Double amount) {
        userRepository.withdrawal(userId, amount);
    }

    public void deposit(String userId, Double amount) {
       userRepository.deposit(userId, amount);
    }

    public void printTransactions(String userId) {
        userRepository.printTransactions(userId);
    }

    public void chequeBookRequest(String userId) {
        userRepository.chequeBookRequest(userId);
    }

    public Map<String, Boolean> getALlChequeBookRequest() {
        return userRepository.getALlChequeBookRequest();
    }

    public List<String> getChequeBookUserId() {
       return userRepository.getChequeBookUserId();
    }

    public void approveChequeBookRequest(String userId) {
       userRepository.approveChequeBookRequest(userId);
    }
}
