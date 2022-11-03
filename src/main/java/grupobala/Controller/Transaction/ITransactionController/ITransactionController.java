package grupobala.Controller.Transaction.ITransactionController;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.util.Date;

public interface ITransactionController {
    public void addTransaction(
        int userID,
        double wage,
        String title,
        CategoryEnum category,
        Date date
    ) throws Exception;

    public void updateTransaction(int userID, ITransaction transaction)
        throws Exception;

    public void removeTransaction(
        int userID,
        int transactionID,
        double transactionValue,
        double userBalance
    ) throws Exception;
}
