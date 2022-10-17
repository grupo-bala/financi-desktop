package grupobala.Controller.Extract;

import grupobala.Controller.Extract.IExtractController.IExtractController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Extract.DBExtract;
import grupobala.Entities.Extract.Extract;
import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class ExtractController implements IExtractController {

    DBExtract dbExtract;

    public ExtractController() {
        dbExtract = new DBExtract(new DBConnection());
    }

    @Override
    public IExtract getExtract(int id, Date initial, Date end)
        throws SQLException, ParseException {
        ArrayList<ITransaction> transactions = dbExtract.getExtract(
            id,
            initial,
            end
        );
        IExtract extract = new Extract(transactions);

        return extract;
    }
}
