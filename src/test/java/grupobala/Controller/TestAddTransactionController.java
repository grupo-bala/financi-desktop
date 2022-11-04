package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertThrows;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Controller.Transaction.ITransactionController.ITransactionController;
import grupobala.Controller.Transaction.TransactionController;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestAddTransactionController {

    ITransactionController transactionController = new TransactionController();
    AuthenticationController authenticationController = new AuthenticationController();
    Calendar calendar = Calendar.getInstance();
    Date dateTransaction = calendar.getTime();
    String titleTranction = new String("teste");
    double wageTransaction = 30.5;
    CategoryEnum categoryTransaction = CategoryEnum.OTHERS;

    @Test
    public void testAddTransaction() throws Exception {
        SetupForTest.truncateTables();
        authenticationController.signUp("financi12", "123", "financi", 100);
        authenticationController.signIn("financi12", "123");

        transactionController.addTransaction(
            new User().getID(),
            wageTransaction,
            titleTranction,
            categoryTransaction,
            dateTransaction
        );

        new User().close();
    }

    @Test
    public void testeAddTransactionFailUserIDNoneExistent() throws Exception {
        SetupForTest.truncateTables();

        assertThrows(
            Exception.class,
            () -> {
                transactionController.addTransaction(
                    -1,
                    wageTransaction,
                    titleTranction,
                    categoryTransaction,
                    dateTransaction
                );
            }
        );
    }
}
