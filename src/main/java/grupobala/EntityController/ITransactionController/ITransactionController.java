package grupobala.EntityController.ITransactionController;

import java.sql.Date;
import java.sql.SQLException;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;

public interface ITransactionController {
    
    public ITransaction addTransaction(String username, double value, String title, CategoryEnum category, Date date) throws SQLException;

    public void updateTransaction(String username, ITransaction transaction) throws SQLException;

    public void removeTransaction(String username, int transactionID) throws SQLException;

}
