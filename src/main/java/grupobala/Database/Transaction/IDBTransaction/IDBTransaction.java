package grupobala.Database.Transaction.IDBTransaction;

import java.sql.SQLException;
import java.util.Date;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;

public interface IDBTransaction {
    public ITransaction addTransaction(
        String username,
        double valor,
        String description,
        String title,
        CategoryEnum category,
        Date date
    ) throws SQLException;

    public void removeTransaction(
        String username,
        int transactionID
    ) throws SQLException;
}
