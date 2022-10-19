package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Controller.Authentication.IAuthenticationController.IAuthenticationController;
import grupobala.Controller.Extract.ExtractController;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class TestGetExtractController {

    @Test
    public void checkExtractEntry() throws Exception {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();

        authController.signUp("extract", "1234", "Financi", 0);
        authController.signIn("extract", "1234");

        ITransaction transaction = SetupForTest.addDefaultTransaction(
            new User().getID()
        );

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract();

        assertEquals(transaction.getValue(), teste.getEntry());
        new User().close();
    }

    @Test
    public void checkExtractOutput() throws Exception {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();

        authController.signUp("extract", "1234", "Financi", 0);
        authController.signIn("extract", "1234");

        ITransaction transaction = SetupForTest.addDefaultTransaction(
            new User().getID()
        );

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract();

        assertEquals(0, teste.getOutput());
        new User().close();
    }

    @Test
    public void checkTitle() throws Exception {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();

        authController.signUp("extract", "1234", "Financi", 0);
        authController.signIn("extract", "1234");

        ITransaction transaction = SetupForTest.addDefaultTransaction(
            new User().getID()
        );

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract();

        assertEquals(
            transaction.getTitle(),
            teste.iterator().next().getTitle()
        );

        new User().close();
    }

    @Test
    public void checkCategory() throws Exception {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();
        authController.signUp("extract", "1234", "Financi", 0);
        authController.signIn("extract", "1234");

        ITransaction transaction = SetupForTest.addDefaultTransaction(
            new User().getID()
        );

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract();

        assertEquals(
            transaction.getCategory(),
            teste.iterator().next().getCategory()
        );

        new User().close();
    }
}
