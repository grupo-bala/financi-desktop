package grupobala.Database;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.Test;

import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Extract.DBExtract;
import grupobala.Database.Extract.IDBExtract.IDBExtract;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.SetupForTest.SetupForTest;

public class TestDBExtract {
    private IDBExtract extract = new DBExtract(new DBConnection());

    @Test
    public void testGetExtract() throws SQLException, ParseException {
        SetupForTest.truncateTables();
        SetupForTest.addFinanciUser();
        SetupForTest.addDefaultTransaction();

        Calendar initialCalendar = Calendar.getInstance();

        initialCalendar.set(Calendar.YEAR, 2022);
        initialCalendar.set(Calendar.MONTH, Calendar.OCTOBER);
        initialCalendar.set(Calendar.DAY_OF_MONTH, 01);

        Calendar endCalendar = Calendar.getInstance();

        endCalendar.set(Calendar.YEAR, 2022);
        endCalendar.set(Calendar.MONTH, Calendar.OCTOBER);
        endCalendar.set(Calendar.DAY_OF_MONTH, 31);

        ArrayList<ITransaction> transactions = this.extract.getExtract("financi", initialCalendar.getTime(), endCalendar.getTime());

        int expectedSize = 1;
        int result = transactions.size();

        assertEquals(expectedSize, result);
    }

    @Test
    public void testGetExtractShouldFailNonexistentUser() throws SQLException, ParseException {
        SetupForTest.truncateTables();

        Exception exception = assertThrows(SQLException.class, () -> {
            this.extract.getExtract("usuárioInexistente", null, null);
        });

        String expected = "Usuário não existe";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }
}
