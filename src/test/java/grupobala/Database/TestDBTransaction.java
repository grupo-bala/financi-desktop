package grupobala.Database;

import static org.junit.jupiter.api.Assertions.*;

import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Transaction.DBTransaction;
import grupobala.Database.Transaction.IDBTransaction.IDBTransaction;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.SetupForTest.SetupForTest;
import grupobala.Entities.Transaction.Transaction;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class TestDBTransaction {

    private IDBTransaction databaseTransaction = new DBTransaction(
        new DBConnection()
    );

    @Test
    @Order(1)
    public void testAddTransaction() throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date transactionDate = calendar.getTime();

        ITransaction transaction =
            this.databaseTransaction.addTransaction(
                    "financi",
                    100,
                    "Testes",
                    CategoryEnum.OTHERS,
                    transactionDate
                );

        assertNotNull(transaction);
    }

    @Test
    public void testAddTransactionShouldFailNonexistentUser()
        throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date transactionDate = calendar.getTime();

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.databaseTransaction.addTransaction(
                        "usuarioInexistente",
                        100,
                        "Testes",
                        CategoryEnum.OTHERS,
                        transactionDate
                    );
            }
        );

        String expected = "Usuário não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveTransaction() throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date transactionDate = calendar.getTime();

        ITransaction transaction =
            this.databaseTransaction.addTransaction(
                    "financi",
                    100,
                    "Testes",
                    CategoryEnum.OTHERS,
                    transactionDate
                );

        int transactionID = transaction.getId();

        this.databaseTransaction.removeTransaction("financi", transactionID);
    }

    @Test
    public void testRemoveTransactionShouldFailNonexistentUser()
        throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.databaseTransaction.removeTransaction(
                        "usuárioNãoExistente",
                        1
                    );
            }
        );

        String expected = "Usuário não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void testRemoveTransactionShouldFailNonexistentTransactionID()
        throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.databaseTransaction.removeTransaction("financi", -1);
            }
        );

        String expected = "Movimentação não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void testUpdateTransaction() throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date transactionDate = calendar.getTime();

        ITransaction transaction =
            this.databaseTransaction.addTransaction(
                    "financi",
                    100,
                    "Testes",
                    CategoryEnum.OTHERS,
                    transactionDate
                );

        transaction.setTitle("Testes Testes e mais Testes");

        this.databaseTransaction.updateTransaction("financi", transaction);
    }

    @Test
    public void testUpdateTransactionShouldFailNonexistentUser()
        throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2022);
        calendar.set(Calendar.MONTH, Calendar.OCTOBER);
        calendar.set(Calendar.DAY_OF_MONTH, 21);

        Date transactionDate = calendar.getTime();

        ITransaction transaction =
            this.databaseTransaction.addTransaction(
                    "financi",
                    100,
                    "Testes",
                    CategoryEnum.OTHERS,
                    transactionDate
                );

        transaction.setTitle("Teste Teste");

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.databaseTransaction.updateTransaction(
                        "usuárioNãoExistente",
                        transaction
                    );
            }
        );

        String expected = "Usuário não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void testUpdateTransactionShouldFailNonexistentTransactionID()
        throws SQLException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();

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
                        "financi",
                        transaction
                    );
            }
        );

        String expected = "Movimentação não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }
}
