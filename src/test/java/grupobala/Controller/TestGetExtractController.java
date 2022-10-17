package grupobala.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import grupobala.Controller.Extract.ExtractController;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.SetupForTest.SetupForTest;

public class TestGetExtractController {
    
    @Test
    public void checkExtractEntry() throws SQLException, ParseException {

        SetupForTest.truncateTables(); 
        int financiUserID = SetupForTest.addFinanciUser();
        ITransaction transaction = SetupForTest.addDefaultTransaction(
            financiUserID
        );

        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.set(Calendar.YEAR, 2020);
        calendarBegin.set(Calendar.MONTH, Calendar.DECEMBER);
        calendarBegin.set(Calendar.DAY_OF_MONTH, 31);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(Calendar.YEAR, 2022);
        calendarEnd.set(Calendar.MONTH, Calendar.DECEMBER);
        calendarEnd.set(Calendar.DAY_OF_MONTH, 31);

        Date dataBegin = calendarBegin.getTime();
        Date dataEnd = calendarEnd.getTime();

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract(financiUserID, dataBegin, dataEnd);
        
        assertEquals(transaction.getValue(), teste.getEntry());
    }

    @Test
    public void checkExtractOutput() throws SQLException, ParseException {
        
        SetupForTest.truncateTables(); 
        int financiUserID = SetupForTest.addFinanciUser();
        SetupForTest.addDefaultTransaction(financiUserID);

        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.set(Calendar.YEAR, 2020);
        calendarBegin.set(Calendar.MONTH, Calendar.DECEMBER);
        calendarBegin.set(Calendar.DAY_OF_MONTH, 31);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(Calendar.YEAR, 2022);
        calendarEnd.set(Calendar.MONTH, Calendar.DECEMBER);
        calendarEnd.set(Calendar.DAY_OF_MONTH, 31);

        Date dataBegin = calendarBegin.getTime();
        Date dataEnd = calendarEnd.getTime();

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract(financiUserID, dataBegin, dataEnd);
        
        assertEquals(0, teste.getOutput());
    }

    @Test
    public void checkTitle() throws SQLException, ParseException {
        
        SetupForTest.truncateTables(); 
        int financiUserID = SetupForTest.addFinanciUser();
        ITransaction transaction = SetupForTest.addDefaultTransaction(
            financiUserID
        );

        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.set(Calendar.YEAR, 2020);
        calendarBegin.set(Calendar.MONTH, Calendar.DECEMBER);
        calendarBegin.set(Calendar.DAY_OF_MONTH, 31);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(Calendar.YEAR, 2022);
        calendarEnd.set(Calendar.MONTH, Calendar.DECEMBER);
        calendarEnd.set(Calendar.DAY_OF_MONTH, 31);

        Date dataBegin = calendarBegin.getTime();
        Date dataEnd = calendarEnd.getTime();

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract(financiUserID, dataBegin, dataEnd);
        
        assertEquals(transaction.getTitle(), teste.iterator().next().getTitle());
    }

    @Test
    public void checkCategory() throws SQLException, ParseException {
        
        SetupForTest.truncateTables(); 
        int financiUserID = SetupForTest.addFinanciUser();
        ITransaction transaction = SetupForTest.addDefaultTransaction(
            financiUserID
        );

        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.set(Calendar.YEAR, 2020);
        calendarBegin.set(Calendar.MONTH, Calendar.DECEMBER);
        calendarBegin.set(Calendar.DAY_OF_MONTH, 31);

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(Calendar.YEAR, 2022);
        calendarEnd.set(Calendar.MONTH, Calendar.DECEMBER);
        calendarEnd.set(Calendar.DAY_OF_MONTH, 31);

        Date dataBegin = calendarBegin.getTime();
        Date dataEnd = calendarEnd.getTime();

        ExtractController controler = new ExtractController();
        IExtract teste = controler.getExtract(financiUserID, dataBegin, dataEnd);
        
        assertEquals(transaction.getCategory(), teste.iterator().next().getCategory());
    }
}
