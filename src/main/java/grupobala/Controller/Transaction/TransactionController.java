package grupobala.Controller.Transaction;

import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Transaction.DBTransaction;
import grupobala.Controller.Transaction.ITransactionController.ITransactionController;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.sql.Date;
import java.sql.SQLException;


public class TransactionController implements ITransactionController {

    @Override
    public ITransaction addTransaction(
        String username,
        double wage,
        String title,
        CategoryEnum category,
        Date date
    ) throws Exception {
        return null;
    }

    @Override
    public void updateTransaction(String username, ITransaction transaction)
        throws Exception {}

    @Override
    public void removeTransaction(String username, int transactionID)
        throws Exception {
            DBTransaction dbTransaction = new DBTransaction(new DBConnection());

            try { 
                dbTransaction.removeTransaction(username, transactionID);
            }
            catch(SQLException error){
                throw new Exception("Não foi possível apagar a transação");
            }
        
        }
}
