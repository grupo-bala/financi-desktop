package grupobala.Controller.Transaction;

import grupobala.Controller.Transaction.ITransactionController.ITransactionController;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.sql.Date;
import java.sql.SQLException;

public class TransactionController implements ITransactionController {

    public ITransaction addTransaction(
        String username,
        double value,
        String title,
        CategoryEnum category,
        Date date
    ) throws SQLException {
        return null;
    }

    public void updateTransaction(String username, ITransaction transaction)
        throws SQLException {}

    public void removeTransaction(String username, int transactionID)
        throws SQLException {}
}
