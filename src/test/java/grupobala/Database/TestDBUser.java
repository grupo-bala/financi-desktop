package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

import org.junit.jupiter.api.Test;

import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.User.DBUser;
import grupobala.Database.User.IDBUser.IDBUser;
import grupobala.SetupForTest.SetupForTest;

public class TestDBUser {
    
    IDBConnection databaseConnection = new DBConnection();
    IDBUser userDB = new DBUser(databaseConnection);

    @Test
    public void testSetUserBalance() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();

        this.userDB.setUserBalance(financiUserID, 2000);

        String query = String.format(
            Locale.US,
            "SELECT saldo FROM usuario WHERE id = %d",
            financiUserID
        );

        ResultSet result = this.databaseConnection.executeQuery(query);

        result.next();

        double expected = 2000;
        double resultBalance = result.getDouble("saldo");

        result.close();

        assertEquals(expected, resultBalance);
    }

    @Test
    public void testSetUserBalanceShouldFailNonexistentUser() throws SQLException {
        SetupForTest.truncateTables();

        Exception exception = assertThrows(SQLException.class, () -> {
            this.userDB.setUserBalance(-1, 0);
        });

        String expected = "Usuário não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }
}
