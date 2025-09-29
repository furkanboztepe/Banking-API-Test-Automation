package com.fintech.banking;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BankAccount {
    private String accountId;
    private String ownerName;
    private BigDecimal balance;
    private List<Transaction> transactions;
    private boolean isActive;

    public BankAccount(String ownerName, BigDecimal initialBalance) {
        this.accountId = UUID.randomUUID().toString();
        this.ownerName = ownerName;
        this.balance = initialBalance;
        this.transactions = new ArrayList<>();
        this.isActive = true;
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        if (!isActive) {
            throw new IllegalStateException("Account is not active");
        }
        balance = balance.add(amount);
        transactions.add(new Transaction("DEPOSIT", amount, balance));
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (!isActive) {
            throw new IllegalStateException("Account is not active");
        }
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient balance");
        }
        balance = balance.subtract(amount);
        transactions.add(new Transaction("WITHDRAWAL", amount, balance));
    }

    public void transfer(BankAccount targetAccount, BigDecimal amount) {
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account cannot be null");
        }
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }
        if (!isActive || !targetAccount.isActive()) {
            throw new IllegalStateException("Both accounts must be active");
        }
        if (balance.compareTo(amount) < 0) {
            throw new IllegalStateException("Insufficient balance for transfer");
        }
        
        this.withdraw(amount);
        targetAccount.deposit(amount);
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    public boolean isActive() {
        return isActive;
    }

    public void deactivate() {
        this.isActive = false;
    }

    public static class Transaction {
        private String type;
        private BigDecimal amount;
        private BigDecimal balanceAfter;
        private long timestamp;

        public Transaction(String type, BigDecimal amount, BigDecimal balanceAfter) {
            this.type = type;
            this.amount = amount;
            this.balanceAfter = balanceAfter;
            this.timestamp = System.currentTimeMillis();
        }

        public String getType() {
            return type;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public BigDecimal getBalanceAfter() {
            return balanceAfter;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }
}
