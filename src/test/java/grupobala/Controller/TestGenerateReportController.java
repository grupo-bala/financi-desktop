package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Controller.Authentication.IAuthenticationController.IAuthenticationController;
import grupobala.Controller.Report.ReportController;
import grupobala.Controller.Transaction.ITransactionController.ITransactionController;
import grupobala.Controller.Transaction.TransactionController;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Report.CSVReport;
import grupobala.Entities.Report.PDFReport;
import grupobala.Entities.User.User;
import grupobala.SetupForTest.SetupForTest;
import java.util.Calendar;
import org.junit.jupiter.api.Test;

public class TestGenerateReportController {

    IAuthenticationController authenticationController = new AuthenticationController();
    ReportController reportController = new ReportController();
    ITransactionController transactionController = new TransactionController();

    @Test
    public void testGeneratePDFReport() throws Exception {
        SetupForTest.truncateTables();
        

        authenticationController.signUp("financi12", "123", "financi", 100);
        authenticationController.signIn("financi12", "123");

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
            reportController.setReporter(new PDFReport());
            reportController.generateReport();
        });

        new User().close();
    }

    @Test
    public void testGenerateCSVReport() throws Exception {
        SetupForTest.truncateTables();

        authenticationController.signUp("financi12", "123", "financi", 100);
        authenticationController.signIn("financi12", "123");

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
            reportController.setReporter(new CSVReport());
            reportController.generateReport();
        });

        new User().close();
    }
}
