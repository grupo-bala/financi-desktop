package grupobala.Database;

import static org.junit.jupiter.api.Assertions.*;

import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Transaction.DBTransaction;
import grupobala.Database.Transaction.IDBTransaction.IDBTransaction;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.Transaction.Transaction;
import grupobala.SetupForTest.SetupForTest;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class TestDBTransaction {

    private IDBTransaction databaseTransaction = new DBTransaction(
        new DBConnection()
    );

    @Test
    public void testAddTransaction() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date transactionDate = calendar.getTime();

        this.databaseTransaction.addTransaction(
                financiUserID,
                100,
                "Testes",
                CategoryEnum.OTHERS,
                transactionDate
            );
    }

    @Test
    public void testAddTransactionShouldFailNonexistentUser()
        throws SQLException {
        SetupForTest.truncateTables();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date transactionDate = calendar.getTime();

        assertThrows(
            SQLException.class,
            () -> {
                this.databaseTransaction.addTransaction(
                        -1,
                        100,
                        "Testes",
                        CategoryEnum.OTHERS,
                        transactionDate
                    );
            }
        );
    }

    @Test
    public void testRemoveTransaction() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();
        ITransaction transaction = SetupForTest.addDefaultTransaction(financiUserID);

        this.databaseTransaction.removeTransaction(financiUserID, transaction.getId());
    }

    @Test
    public void testRemoveTransactionShouldFailNonexistentUser()
        throws SQLException {
        SetupForTest.truncateTables();

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.databaseTransaction.removeTransaction(
                        -1,
                        1
                    );
            }
        );

        String expected = "Movimentação não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveTransactionShouldFailNonexistentTransactionID()
        throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.databaseTransaction.removeTransaction(financiUserID, -1);
            }
        );

        String expected = "Movimentação não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void testUpdateTransaction() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();
        ITransaction transaction = SetupForTest.addDefaultTransaction(financiUserID);

        transaction.setTitle("Testes Testes e mais Testes");

        this.databaseTransaction.updateTransaction(financiUserID, transaction);
    }

    @Test
    public void testUpdateTransactionShouldFailNonexistentUser()
        throws SQLException {
        SetupForTest.truncateTables();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date transactionDate = calendar.getTime();

        ITransaction transaction = new Transaction(
            -1,
            1000,
            "Teste",
            CategoryEnum.OTHERS,
            transactionDate
        );

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.databaseTransaction.updateTransaction(
                        -1,
                        transaction
                    );
            }
        );

        String expected = "Movimentação não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void testUpdateTransactionShouldFailNonexistentID()
        throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date transactionDate = calendar.getTime();

        ITransaction transaction = new Transaction(
            -1,
            1000,
            "Teste",
            CategoryEnum.OTHERS,
            transactionDate
        );

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.databaseTransaction.updateTransaction(
                        financiUserID,
                        transaction
                    );
            }
        );

        String expected = "Movimentação não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }
}
