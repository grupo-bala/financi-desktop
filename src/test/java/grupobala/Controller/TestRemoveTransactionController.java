package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import grupobala.Controller.Transaction.TransactionController;
import grupobala.SetupForTest.SetupForTest;
import org.junit.jupiter.api.Test;

public class TestRemoveTransactionController {

    private TransactionController transactionController = new TransactionController();

    @Test
    public void testRemoveTransaction() throws Exception {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();
        int transactionID = SetupForTest.addDefaultTransaction();
        transactionController.removeTransaction("financi", transactionID);
    }

    @Test
    public void testRemoveTransactionShouldFailNonexistentUser()
        throws Exception {
        SetupForTest.truncateTables();
        Exception exception = assertThrows(
            Exception.class,
            () -> {
                transactionController.removeTransaction("Nonexistent", -1);
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
        SetupForTest.addFinanciUser();
        Exception exception = assertThrows(
            Exception.class,
            () -> {
                transactionController.removeTransaction("financi", -1);
            }
        );
        String expected = "Não foi possível apagar a transação";
        String result = exception.getMessage();
        assertEquals(expected, result);
    }
}
