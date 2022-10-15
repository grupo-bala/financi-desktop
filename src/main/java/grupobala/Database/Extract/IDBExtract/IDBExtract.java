package grupobala.Database.Extract.IDBExtract;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

import grupobala.Entities.Transaction.ITransaction.ITransaction;

public interface IDBExtract {
    public ArrayList<ITransaction> getExtract(String username, Date initial, Date end) throws SQLException, ParseException;
}
