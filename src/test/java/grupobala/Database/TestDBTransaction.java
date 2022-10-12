package grupobala.Database;

import static org.junit.jupiter.api.Assertions.*;

import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Transaction.DBTransaction;
import grupobala.Database.Transaction.IDBTransaction.IDBTransaction;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
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
        TestDBTransaction.setupDBForTest();

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
        TestDBTransaction.setupDBForTest();

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
        TestDBTransaction.setupDBForTest();

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
        TestDBTransaction.setupDBForTest();

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
        TestDBTransaction.setupDBForTest();

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.databaseTransaction.removeTransaction("financi", -1);
            }
        );

        String expected = "ID de transação não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void testUpdateTransaction() throws SQLException {
        TestDBTransaction.setupDBForTest();
        // TODO
    }

    @Test
    public void testUpdateTransactionShouldFail() throws SQLException {
        TestDBTransaction.setupDBForTest();
        // TODO
    }

    public static void setupDBForTest() throws SQLException {
        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
        );

        Statement statement = connection.createStatement();

        String[] queries = {
            "TRUNCATE TABLE usuario CASCADE",
            "TRUNCATE TABLE meta CASCADE",
            "TRUNCATE TABLE aulaassistida CASCADE",
            "TRUNCATE TABLE aula CASCADE",
            "TRUNCATE TABLE movimentacao CASCADE",
            "INSERT INTO usuario(nome, nomeusuario, senha, rendafixa) VALUES ('Financi', 'financi', '1234', 1000)",
        };

        for (String query : queries) {
            statement.executeUpdate(query);
        }

        connection.close();
    }
}
