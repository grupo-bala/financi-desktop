package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Controller.Authentication.IAuthenticationController.IAuthenticationController;
import grupobala.Controller.Extract.ExtractController;
import grupobala.Controller.Transaction.TransactionController;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Iterator.IteratorEnum.IteratorEnum;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class TestGetExtractController {

    @Test
    public void checkExtractEntry() throws Exception {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();

        authController.signUp("extract", "Financi@123", "Financi", 0);
        authController.signIn("extract", "Financi@123");

        User user = new User();

        float transactionValue = 1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValue,
                "teste",
                CategoryEnum.OTHERS,
                new Date()
            );

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract();

        assertEquals(transactionValue, teste.getEntry());
        new User().close();
    }

    @Test
    public void checkExtractOutput() throws Exception {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();

        authController.signUp("extract", "Financi@123", "Financi", 0);
        authController.signIn("extract", "Financi@123");

        User user = new User();

        float transactionValue = -1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValue,
                "teste",
                CategoryEnum.OTHERS,
                new Date()
            );

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract();

        assertEquals(transactionValue, teste.getOutput());
        new User().close();
    }

    @Test
    public void checkTitle() throws Exception {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();

        authController.signUp("extract", "Financi@123", "Financi", 0);
        authController.signIn("extract", "Financi@123");

        User user = new User();
        String transactionTitle = "teste";
        new TransactionController()
            .addTransaction(
                user.getID(),
                1000,
                transactionTitle,
                CategoryEnum.OTHERS,
                new Date()
            );

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract();

        assertEquals(transactionTitle, teste.iterator(IteratorEnum.REVERSE).next().getTitle());

        new User().close();
    }

    @Test
    public void checkCategory() throws Exception {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();
        authController.signUp("extract", "Financi@123", "Financi", 0);
        authController.signIn("extract", "Financi@123");

        User user = new User();
        CategoryEnum category = CategoryEnum.ENTERTAINMENT;
        new TransactionController()
            .addTransaction(user.getID(), 1000, "teste", category, new Date());

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract();

        assertEquals(category, teste.iterator(IteratorEnum.REVERSE).next().getCategory());

        new User().close();
    }
}
