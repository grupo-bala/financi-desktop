package grupobala.Database.Transaction.IDBTransaction;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.sql.SQLException;
import java.util.Date;

public interface IDBTransaction {
    public ITransaction addTransaction(
        String username,
        double valor,
        String title,
        CategoryEnum category,
        Date date
    ) throws SQLException;

    public void removeTransaction(String username, int transactionID)
        throws SQLException;
}
