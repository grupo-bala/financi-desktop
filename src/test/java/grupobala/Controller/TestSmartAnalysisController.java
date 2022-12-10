package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Controller.Authentication.IAuthenticationController.IAuthenticationController;
import grupobala.Controller.SmartAnalysis.SmartAnalysisController;
import grupobala.Controller.Transaction.TransactionController;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import java.sql.*;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

public class TestSmartAnalysisController {

    @Test
    public void testConvertToMonth() throws SQLException {
        SetupForTest.truncateTables();
        SmartAnalysisController smartAnalysisController = new SmartAnalysisController();
        int financiUserID = SetupForTest.addFinanciUser();
        ITransaction userTransaction = SetupForTest.addDefaultTransaction(
            financiUserID
        );
        String result = smartAnalysisController.convertToMonth(
            userTransaction.getDate()
        );
        String expected = "10";
        assertEquals(expected, result);
    }

    @Test
    public void testGetTotalEntry() throws Exception, SQLException {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();
        SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

        authController.signUp("smart-analysis", "Financi@123", "Financi", 0);
        authController.signIn("smart-analysis", "Financi@123");
        User user = new User();

        float transactionValueO = 1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueO,
                "teste",
                CategoryEnum.OTHERS,
                new Date()
            );

        float transactionValueF = 1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueF,
                "teste",
                CategoryEnum.FOOD,
                new Date()
            );
        double result = smartAnalysisController.getTotalEntry(
            smartAnalysisController.getTransactions(user.getID())
        );
        double expected = 2000;
        assertEquals(expected, result);
        new User().close();
    }

    @Test
    public void testGetTotalOutput() throws Exception, SQLException {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();
        SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

        authController.signUp("smart-analysis", "Financi@123", "Financi", 0);
        authController.signIn("smart-analysis", "Financi@123");
        User user = new User();

        float transactionValueO = -1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueO,
                "teste",
                CategoryEnum.OTHERS,
                new Date()
            );

        float transactionValueF = -1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueF,
                "teste",
                CategoryEnum.FOOD,
                new Date()
            );
        double result = smartAnalysisController.getTotalOutput(
            smartAnalysisController.getTransactions(user.getID())
        );
        double expected = 2000;
        assertEquals(expected, result);
        new User().close();
    }

    @Test
    public void testGetHighestEntryPercentageByCategory()
        throws Exception, SQLException, ParseException {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();
        SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

        authController.signUp("smart-analysis", "Financi@123", "Financi", 0);
        authController.signIn("smart-analysis", "Financi@123");
        User user = new User();

        float transactionValueO = 3000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueO,
                "teste",
                CategoryEnum.OTHERS,
                new Date()
            );

        float transactionValueF = 1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueF,
                "teste",
                CategoryEnum.FOOD,
                new Date()
            );

        Pair<CategoryEnum, Double> result = smartAnalysisController.getHighestEntryPercentageByCategory(
            user.getID()
        );
        Pair<CategoryEnum, Double> expected = new Pair<CategoryEnum, Double>(
            CategoryEnum.OTHERS,
            75.0
        );
        assertEquals(expected, result);
        new User().close();
    }

    @Test
    public void testGetHighestOutputPercentageByCategory()
        throws Exception, SQLException, ParseException {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();
        SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

        authController.signUp("smart-analysis", "Financi@123", "Financi", 0);
        authController.signIn("smart-analysis", "Financi@123");
        User user = new User();

        float transactionValueO = -1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueO,
                "teste",
                CategoryEnum.OTHERS,
                new Date()
            );

        float transactionValueF = -3000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueF,
                "teste",
                CategoryEnum.FOOD,
                new Date()
            );

        Pair<CategoryEnum, Double> result = smartAnalysisController.getHighestOutputPercentageByCategory(
            user.getID()
        );
        Pair<CategoryEnum, Double> expected = new Pair<CategoryEnum, Double>(
            CategoryEnum.FOOD,
            75.0
        );
        assertEquals(expected, result);
        new User().close();
    }

    @Test
    public void testGetValuesByCategory() throws Exception, SQLException {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();
        SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

        authController.signUp("smart-analysis", "Financi@123", "Financi", 0);
        authController.signIn("smart-analysis", "Financi@123");
        User user = new User();

        float transactionValueO = 1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueO,
                "teste",
                CategoryEnum.OTHERS,
                new Date()
            );
        float transactionValueF = 1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueF,
                "teste",
                CategoryEnum.FOOD,
                new Date()
            );
        Map<CategoryEnum, Double> valuesByCategory = smartAnalysisController.getValuesByCategory(
            smartAnalysisController.getTransactions(user.getID())
        );
        Pair<CategoryEnum, Double> result = new Pair<>(
            CategoryEnum.FOOD,
            valuesByCategory.get(CategoryEnum.FOOD)
        );
        Pair<CategoryEnum, Double> expected = new Pair<CategoryEnum, Double>(
            CategoryEnum.FOOD,
            1000.0
        );
        assertEquals(expected, result);
        new User().close();
    }

    @Test
    public void testCompareValuesByCategorys() throws Exception, SQLException {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();
        SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

        authController.signUp("smart-analysis", "Financi@123", "Financi", 0);
        authController.signIn("smart-analysis", "Financi@123");
        User user = new User();

        float transactionValueO = 998;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueO,
                "teste",
                CategoryEnum.OTHERS,
                new Date()
            );

        float transactionValueF = -999;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueF,
                "teste",
                CategoryEnum.FOOD,
                new Date()
            );

        Pair<CategoryEnum, Double> result = smartAnalysisController.compareValuesByCategorys(
            smartAnalysisController.getTransactions(user.getID())
        );
        Pair<CategoryEnum, Double> expected = new Pair<CategoryEnum, Double>(
            CategoryEnum.FOOD,
            999.0
        );
        assertEquals(expected, result);
        new User().close();
    }

    @Test
    public void testConvertOutputToString()
        throws Exception, SQLException, ParseException {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();
        SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

        authController.signUp("smart-analysis", "Financi@123", "Financi", 0);
        authController.signIn("smart-analysis", "Financi@123");
        User user = new User();

        float transactionValue = -3000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValue,
                "teste",
                CategoryEnum.OTHERS,
                new Date()
            );

        float transactionValueF = -1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueF,
                "teste",
                CategoryEnum.FOOD,
                new Date()
            );
        String result = smartAnalysisController.convertOutputToString(
            user.getID()
        );
        String expected =
            "Seus gastos com outras coisas representam 75% do total";
        assertEquals(expected, result);
        new User().close();
    }

    @Test
    public void testConvertEntryToString()
        throws Exception, SQLException, ParseException {
        SetupForTest.truncateTables();
        IAuthenticationController authController = new AuthenticationController();
        SmartAnalysisController smartAnalysisController = new SmartAnalysisController();

        authController.signUp("smart-analysis", "Financi@123", "Financi", 0);
        authController.signIn("smart-analysis", "Financi@123");
        User user = new User();

        float transactionValueO = 3000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueO,
                "teste",
                CategoryEnum.OTHERS,
                new Date()
            );

        float transactionValueF = 1000;
        new TransactionController()
            .addTransaction(
                user.getID(),
                transactionValueF,
                "teste",
                CategoryEnum.FOOD,
                new Date()
            );

        String result = smartAnalysisController.convertEntryToString(
            user.getID()
        );
        String expected =
            "Suas entradas com outras coisas representam 75% do total";
        new User().close();
        assertEquals(expected, result);
    }
}
