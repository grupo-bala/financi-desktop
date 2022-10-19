package grupobala.Controller.Extract;

import grupobala.Controller.Extract.IExtractController.IExtractController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Extract.DBExtract;
import grupobala.Entities.Extract.Extract;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.User;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExtractController implements IExtractController {

    DBExtract dbExtract;

    public ExtractController() {
        dbExtract = new DBExtract(new DBConnection());
    }

    @Override
    public IExtract getExtract(Date initial, Date end) throws SQLException, ParseException {

        ArrayList<ITransaction> transactions = dbExtract.getExtract(new User().getID(),initial, end);

        IExtract extract = new Extract(transactions);

        return extract;
    }

    @Override
    public IExtract getExtract() throws SQLException, ParseException {

        Calendar calendarBegin = Calendar.getInstance();
        calendarBegin.set(Calendar.DAY_OF_MONTH, 1);
        Date dataBegin = calendarBegin.getTime();

        Calendar calendarEnd = Calendar.getInstance();
        Date dataEnd = calendarEnd.getTime();

        return getExtract(dataBegin, dataEnd);
    }
}
