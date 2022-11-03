package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import grupobala.Controller.Transaction.TransactionController;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.IUser.IUser;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import org.junit.jupiter.api.Test;

public class TestRemoveTransactionController {

    private TransactionController transactionController = new TransactionController();

    @Test
    public void testRemoveTransaction() throws Exception {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();
        ITransaction transaction = SetupForTest.addDefaultTransaction(
            financiUserID
        );
        transactionController.removeTransaction(
            financiUserID,
            transaction.getId(),
            transaction.getValue(),
            new User("financi", "Financi", 0, 0).getValue()
        );
        new User().close();
    }

    @Test
    public void testRemoveTransactionShouldFailNonexistentUser()
        throws Exception {
        SetupForTest.truncateTables();
        Exception exception = assertThrows(
            Exception.class,
            () -> {
                transactionController.removeTransaction(-1, -1, 1.10, 1.10);
            }
        );
        String expected = "Não foi possível apagar a transação";
        String result = exception.getMessage();
        assertEquals(expected, result);
    }

    @Test
    public void testRemoveTransactionShouldFailNonexistentID()
        throws Exception {
        SetupForTest.truncateTables();
        int financiUserID = SetupForTest.addFinanciUser();
        Exception exception = assertThrows(
            Exception.class,
            () -> {
                transactionController.removeTransaction(
                    financiUserID,
                    -1,
                    1.10,
                    1.10
                );
            }
        );
        String expected = "Não foi possível apagar a transação";
        String result = exception.getMessage();
        assertEquals(expected, result);
    }
}
