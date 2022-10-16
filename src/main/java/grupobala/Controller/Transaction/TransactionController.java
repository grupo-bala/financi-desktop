package grupobala.Controller.Transaction;

import grupobala.Controller.Transaction.ITransactionController.ITransactionController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Transaction.DBTransaction;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.sql.Date;
import java.sql.SQLException;

public class TransactionController implements ITransactionController {

    @Override
    public ITransaction addTransaction(
        int userID,
        double wage,
        String title,
        CategoryEnum category,
        Date date
    ) throws Exception {
        return null;
    }

    @Override
    public void updateTransaction(int userID, ITransaction transaction)
        throws Exception {}

    @Override
    public void removeTransaction(int userID, int transactionID)
        throws Exception {
        DBTransaction dbTransaction = new DBTransaction(new DBConnection());

        try {
            dbTransaction.removeTransaction(userID, transactionID);
        } catch (SQLException error) {
            throw new Exception("Não foi possível apagar a transação");
        }
    }
}
