package grupobala.Controller.Transaction.ITransactionController;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.sql.Date;

public interface ITransactionController {
    public ITransaction addTransaction(
        String username,
        double wage,
        String title,
        CategoryEnum category,
        Date date
    ) throws Exception;

    public void updateTransaction(String username, ITransaction transaction)
        throws Exception;

    public void removeTransaction(String username, int transactionID)
        throws Exception;
}
