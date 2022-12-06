package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Controller.Report.IReportController.IReportController;
import grupobala.Controller.Report.ReportController;
import grupobala.Controller.Transaction.ITransactionController.ITransactionController;
import grupobala.Controller.Transaction.TransactionController;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import java.util.Calendar;
import org.junit.jupiter.api.Test;

public class TestGenerateReportController {

    @Test
    public void testGenerateReport() throws Exception {
        SetupForTest.truncateTables();
        AuthenticationController authenticationController = new AuthenticationController();

        authenticationController.signUp("financi12", "123", "financi", 100);
        authenticationController.signIn("financi12", "123");

        IReportController reportController = new ReportController();
        ITransactionController transactionController = new TransactionController();

        transactionController.addTransaction(
            new User().getID(),
            30,
            "aaaaa",
            CategoryEnum.CLOTHING,
            Calendar.getInstance().getTime()
        );
        transactionController.addTransaction(
            new User().getID(),
            -30,
            "bbbbbawegwgeewgwegweg",
            CategoryEnum.CLOTHING,
            Calendar.getInstance().getTime()
        );

        assertDoesNotThrow(() -> {
            reportController.generateReport();
        });
    }
}
