package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Controller.Authentication.IAuthenticationController.IAuthenticationController;
import grupobala.Controller.Extract.ExtractController;
import grupobala.Controller.Extract.IExtractController.IExtractController;
import grupobala.Controller.Transaction.ITransactionController.ITransactionController;
import grupobala.Controller.Transaction.TransactionController;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.IUser.IUser;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import java.util.Calendar;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class TestEditTransactionController {

    private IAuthenticationController authenticationController = new AuthenticationController();
    private ITransactionController transactionController = new TransactionController();
    private IExtractController extractController = new ExtractController();
    private Calendar calendar = Calendar.getInstance();
    private Date dateTransaction = calendar.getTime();

    @Test
    public void testEditTransaction() throws Exception {
        SetupForTest.truncateTables();

        authenticationController.signUp("jose", "123", "jose", 500);
        authenticationController.signIn("jose", "123");

        IUser user = new User();

        transactionController.addTransaction(
            user.getID(),
            -20,
            "teste",
            CategoryEnum.CLOTHING,
            dateTransaction
        );

        IExtract extract = extractController.getExtract();
        ITransaction transaction = extract.iterator().next();

        transaction.setTitle("teste editado");
        transaction.setValue(20);
        transaction.setCategory(CategoryEnum.OTHERS);

        assertDoesNotThrow(() -> {
            transactionController.updateTransaction(user.getID(), transaction);
        });

        user.close();
    }

    @Test
    public void testShouldFailEditGoalUserIDNoneExistent() throws Exception {
        SetupForTest.truncateTables();

        authenticationController.signUp("jose", "123", "jose", 500);
        authenticationController.signIn("jose", "123");

        IUser user = new User();

        transactionController.addTransaction(
            user.getID(),
            -20,
            "teste",
            CategoryEnum.CLOTHING,
            dateTransaction
        );

        IExtract extract = extractController.getExtract();
        ITransaction transaction = extract.iterator().next();

        transaction.setTitle("teste editado");
        transaction.setValue(20);
        transaction.setCategory(CategoryEnum.OTHERS);

        assertThrows(
            Exception.class,
            () -> {
                transactionController.updateTransaction(-1, transaction);
            }
        );
    }
}
