package com.fintech.banking;

import org.junit.jupiter.api.*;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Bank Account Tests")
public class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("John Doe", new BigDecimal("1000.00"));
    }

    @Test
    @DisplayName("Should create account with initial balance")
    void testAccountCreation() {
        assertNotNull(account.getAccountId());
        assertEquals("John Doe", account.getOwnerName());
        assertEquals(new BigDecimal("1000.00"), account.getBalance());
        assertTrue(account.isActive());
    }

    @Test
    @DisplayName("Should deposit money successfully")
    void testDeposit() {
        account.deposit(new BigDecimal("500.00"));
        assertEquals(new BigDecimal("1500.00"), account.getBalance());
        assertEquals(1, account.getTransactions().size());
    }

    @Test
    @DisplayName("Should throw exception for negative deposit")
    void testNegativeDeposit() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(new BigDecimal("-100.00"));
        });
        assertEquals("Deposit amount must be positive", exception.getMessage());
    }

    @Test
    @DisplayName("Should withdraw money successfully")
    void testWithdraw() {
        account.withdraw(new BigDecimal("300.00"));
        assertEquals(new BigDecimal("700.00"), account.getBalance());
    }

    @Test
    @DisplayName("Should throw exception for insufficient balance")
    void testInsufficientBalance() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            account.withdraw(new BigDecimal("1500.00"));
        });
        assertEquals("Insufficient balance", exception.getMessage());
    }

    @Test
    @DisplayName("Should transfer money between accounts")
    void testTransfer() {
        BankAccount targetAccount = new BankAccount("Jane Smith", new BigDecimal("500.00"));
        
        account.transfer(targetAccount, new BigDecimal("200.00"));
        
        assertEquals(new BigDecimal("800.00"), account.getBalance());
        assertEquals(new BigDecimal("700.00"), targetAccount.getBalance());
    }

    @Test
    @DisplayName("Should throw exception when transferring to null account")
    void testTransferToNullAccount() {
        assertThrows(IllegalArgumentException.class, () -> {
            account.transfer(null, new BigDecimal("100.00"));
        });
    }

    @Test
    @DisplayName("Should not allow operations on deactivated account")
    void testDeactivatedAccount() {
        account.deactivate();
        assertFalse(account.isActive());
        
        assertThrows(IllegalStateException.class, () -> {
            account.deposit(new BigDecimal("100.00"));
        });
    }

    @Test
    @DisplayName("Should record all transactions")
    void testTransactionHistory() {
        account.deposit(new BigDecimal("200.00"));
        account.withdraw(new BigDecimal("150.00"));
        account.deposit(new BigDecimal("50.00"));
        
        assertEquals(3, account.getTransactions().size());
        assertEquals("DEPOSIT", account.getTransactions().get(0).getType());
        assertEquals("WITHDRAWAL", account.getTransactions().get(1).getType());
    }

    @Test
    @DisplayName("Should handle decimal amounts correctly")
    void testDecimalAmounts() {
        account.deposit(new BigDecimal("123.45"));
        assertEquals(new BigDecimal("1123.45"), account.getBalance());
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCaseTests {
        
        @Test
        @DisplayName("Should handle zero balance withdrawal")
        void testZeroBalanceWithdrawal() {
            BankAccount zeroAccount = new BankAccount("Test User", BigDecimal.ZERO);
            assertThrows(IllegalStateException.class, () -> {
                zeroAccount.withdraw(new BigDecimal("1.00"));
            });
        }

        @Test
        @DisplayName("Should handle large amounts")
        void testLargeAmounts() {
            BankAccount richAccount = new BankAccount("Rich User", new BigDecimal("1000000.00"));
            richAccount.deposit(new BigDecimal("500000.00"));
            assertEquals(new BigDecimal("1500000.00"), richAccount.getBalance());
        }
    }
}
