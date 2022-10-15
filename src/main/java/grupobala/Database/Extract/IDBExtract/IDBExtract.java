package grupobala.Database.Extract.IDBExtract;

import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public interface IDBExtract {
    public ArrayList<ITransaction> getExtract(
        int userID,
        Date initial,
        Date end
    ) throws SQLException, ParseException;
}
