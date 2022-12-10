package grupobala.Controller.Transaction;

import grupobala.Controller.Transaction.ITransactionController.ITransactionController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Transaction.DBTransaction;
import grupobala.Database.Transaction.IDBTransaction.IDBTransaction;
import grupobala.Database.User.DBUser;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.sql.SQLException;
import java.util.Date;

public class TransactionController implements ITransactionController {

    @Override
    public void addTransaction(
        int userID,
        double wage,
        String title,
        CategoryEnum category,
        Date date
    ) throws Exception {
        IDBTransaction dbTransaction = new DBTransaction(new DBConnection());
        try {
            dbTransaction.addTransaction(userID, wage, title, category, date);
        } catch (SQLException error) {
            throw new Exception("Erro ao adicionar transação");
        }
    }

    @Override
    public void updateTransaction(int userID, ITransaction transaction)
        throws Exception {
        IDBTransaction dbtTransaction = new DBTransaction(new DBConnection());
        try {
            dbtTransaction.updateTransaction(userID, transaction);
        } catch (SQLException error) {
            throw new Exception("Erro ao adicionar transação");
        }
    }

    @Override
    public void removeTransaction(
        int userID,
        int transactionID,
        double transactionValue,
        double userBalance
    ) throws Exception {
        DBTransaction dbTransaction = new DBTransaction(new DBConnection());
        DBUser dbUser = new DBUser(new DBConnection());
        try {
            dbTransaction.removeTransaction(userID, transactionID);
            if (transactionValue > 0) dbUser.setUserBalance(
                userID,
                (userBalance - transactionValue)
            ); else {
                dbUser.setUserBalance(
                    userID,
                    (userBalance + (transactionValue * -1))
                );
            }
        } catch (SQLException error) {
            throw new Exception("Não foi possível apagar a transação");
        }
    }
}
