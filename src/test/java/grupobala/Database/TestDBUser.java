package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.User.DBUser;
import grupobala.Database.User.IDBUser.IDBUser;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;
import org.junit.jupiter.api.Test;

public class TestDBUser {

    IDBConnection databaseConnection = new DBConnection();
    IDBUser userDB = new DBUser(databaseConnection);

    @Test
    public void testSetUserBalance() throws Exception {
        SetupForTest.truncateTables();
        AuthenticationController authController = new AuthenticationController();
        authController.signUp("financi", "Financi@123", "Financi", 0);
        authController.signIn("financi", "Financi@123");

        this.userDB.setUserBalance(new User().getID(), 2000);

        String query = String.format(
            Locale.US,
            "SELECT saldo FROM usuario WHERE id = %d",
            new User().getID()
        );

        ResultSet result = this.databaseConnection.executeQuery(query);

        result.next();

        double expected = 2000;
        double resultBalance = result.getDouble("saldo");

        result.close();
        new User().close();

        assertEquals(expected, resultBalance);
    }

    @Test
    public void testSetUserBalanceShouldFailNonexistentUser()
        throws SQLException {
        SetupForTest.truncateTables();

        Exception exception = assertThrows(
            SQLException.class,
            () -> {
                this.userDB.setUserBalance(-1, 0);
            }
        );

        String expected = "Usuário não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }

    @Test
    public void testGetUserPassword() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();

        new User("financi", "Financi", 1000, financiUserID);

        String result = this.userDB.getPassword();
        String expected = "Financi@123";

        assertEquals(expected, result);

        new User().close();
    }

    @Test
    public void updatePassword() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();

        new User("financi", "Financi", 1000, financiUserID);

        this.userDB.updatePassword("4321");

        String result = this.userDB.getPassword();
        String expected = "4321";

        assertEquals(expected, result);

        new User().close();
    }

    @Test
    public void updateUserInformation() throws SQLException {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();

        new User("financi", "Financi", 1000, financiUserID);

        new User().setName("FinanciFinanci");
        new User().setUsername("financiiiiiiiii");

        assertDoesNotThrow(() -> {
            this.userDB.updateUserInformation();
        });

        new User().close();
    }
}
