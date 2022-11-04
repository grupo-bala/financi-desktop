package grupobala.Database.Transaction.IDBTransaction;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.sql.SQLException;
import java.util.Date;

public interface IDBTransaction {
    public ITransaction addTransaction(
        int userID,
        double value,
        String title,
        CategoryEnum category,
        Date date
    ) throws SQLException;

    public void removeTransaction(int userID, int transactionID)
        throws SQLException;

    public void updateTransaction(int userID, ITransaction transaction)
        throws SQLException;
}
