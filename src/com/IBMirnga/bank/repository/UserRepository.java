package com.IBMirnga.bank.repository;

import com.IBMirnga.bank.entity.Transaction;
import com.IBMirnga.bank.entity.User;

import java.time.LocalDate;
import java.util.*;

public class UserRepository {
    private static final Set<User> users = new HashSet<>();
    private static final List<Transaction> transactions = new ArrayList<>();
    public static final Map<String, Boolean> chequeBookRequest = new HashMap<>();

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
       boolean isDebit = debit(userId, amount, payeeUserId);
       boolean isCredit = credit(payeeUserId, amount, userId);

       return isDebit && isCredit;
    }

    public void withdrawal(String userId, Double amount) {
        User user = getUSer(userId);
        Double accountBalance = user.getBalance();
        
        if (accountBalance >= amount) {
            Double finalBalance = accountBalance - amount;
            user.setBalance(finalBalance);

            Transaction transaction = new Transaction(
                    LocalDate.now(),
                    userId,
                    amount,
                    "Withdrawal",
                    accountBalance,
                    finalBalance,
                    userId
            );
            System.out.println("Withdrawal made successfully!!");
            transactions.add(transaction);
        } else {
            System.out.println("Insufficient Balance!!");
        }
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
                finalBalance,
                userId
        );
        System.out.println(transaction);

        transactions.add(transaction);

        return users.add(user);
    }

    private boolean credit(String payeeUserId, Double amount, String userId) {
        User user = getUSer(userId);
        Double accountBalance = user.getBalance();

        users.remove(user);

        Double finalBalance = accountBalance + amount;
        user.setBalance(finalBalance);

        Transaction transaction = new Transaction(
                LocalDate.now(),
                userId,
                amount,
                "Credit",
                accountBalance,
                finalBalance,
                payeeUserId
        );
        System.out.println(transaction);

        transactions.add(transaction);

        return users.add(user);
    }

    public void printTransactions(String userId) {
       List<Transaction> filteredTransactions = transactions.stream()
                .filter(transaction -> transaction.getTransactionPerformedBy().equals(userId))
                .toList();

       System.out.println("Date \t\t User id \t Amount \t Type \t Initial-Balance \t Final-Balance");
       System.out.println("---------------------------------------------------------------------------");
       for (Transaction t: filteredTransactions) {
           System.out.println(t.getTransactionDate()
                   + "\t\t" + t.getTransactionUserId()
                   + "\t\t" + t.getTransactionAmount()
                   + "\t\t" + t.getTransactionType()
                   + "\t\t" + t.getInitialBalance()
                   + "\t\t" + t.getFinalBalance()
           );
       }
        System.out.println("---------------------------------------------------------------------------");
    }

    public void chequeBookRequest(String userId) {
        chequeBookRequest.put(userId, false);
    }

    public Map<String, Boolean> getALlChequeBookRequest() {
        return chequeBookRequest;
    }

    public List<String> getChequeBookUserId() {
        List<String> userIds = new ArrayList<>();

        for (Map.Entry<String, Boolean> entry : chequeBookRequest.entrySet()) {
            if (!entry.getValue()) {
                userIds.add(entry.getKey());
            }
        }
        return userIds;
    }

    public void approveChequeBookRequest(String userId) {
        if (chequeBookRequest.containsKey(userId)) {
            chequeBookRequest.put(userId, true);
        }
    }
}
