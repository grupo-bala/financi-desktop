package grupobala.Database.Transaction;

import grupobala.Controller.Authentication.AuthenticationController;
import grupobala.Database.Category.DBCategory;
import grupobala.Database.Category.IDBCategory.IDBCategory;
import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.Transaction.IDBTransaction.IDBTransaction;
import grupobala.Database.User.DBUser;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.User.User;
import grupobala.Entities.Transaction.Transaction;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DBTransaction implements IDBTransaction {

    private IDBConnection databaseConnection;

    public DBTransaction(IDBConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ITransaction addTransaction(
        int userID,
        double value,
        String title,
        CategoryEnum category,
        Date date
    ) throws SQLException {
        IDBCategory categoryDB = new DBCategory(this.databaseConnection);

        int categoryID = categoryDB.getCategoryID(category);

        DateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd");

        boolean isEntry = value > 0.0;

        String query = String.format(
            Locale.US,
            "INSERT INTO movimentacao(idusuario, valor, data, idcategoria, titulo, entrada) VALUES (%d, %f, '%s', %d, '%s', '%s')",
            userID,
            value,
            formateDate.format(date),
            categoryID,
            title,
            isEntry
        );

        this.databaseConnection.executeUpdate(query);

        query =
            String.format(
                Locale.US,
                "SELECT id FROM movimentacao WHERE idusuario = %d ORDER BY id DESC",
                userID
            );

        ResultSet result = this.databaseConnection.executeQuery(query);

        if (!result.isBeforeFirst()) {
            throw new SQLException("Algo de errado ocorreu");
        }

        result.next();

        int newTransactionID = result.getInt("id");

        result.close();
        DBUser dbUser = new DBUser(databaseConnection);
        dbUser.setUserBalance(userID, new User().getValue() + value);

        return new Transaction(newTransactionID, value, title, category, date);
    }

    @Override
    public void removeTransaction(int userID, int transactionID)
        throws SQLException {
        String query = String.format(
            Locale.US,
            "DELETE FROM movimentacao WHERE id = %d AND idusuario = %d",
            transactionID,
            userID
        );

        int howManyUpdates = this.databaseConnection.executeUpdate(query);

        if (howManyUpdates == 0) {
            throw new SQLException("Movimentação não existe");
        }
    }

    @Override
    public void updateTransaction(int userID, ITransaction transaction)
        throws SQLException {
        IDBCategory categoryDB = new DBCategory(this.databaseConnection);

        int categoryID = categoryDB.getCategoryID(transaction.getCategory());

        boolean isEntry = transaction.getValue() > 0.0;

        DateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd");

        String query = String.format(
            Locale.US,
            "UPDATE movimentacao SET valor = %f, data = '%s', idcategoria = '%s', titulo = '%s', entrada = '%s' WHERE idusuario = %d AND id = %d",
            transaction.getValue(),
            formateDate.format(transaction.getDate()),
            categoryID,
            transaction.getTitle(),
            isEntry,
            userID,
            transaction.getId()
        );

        int howManyUpdates = this.databaseConnection.executeUpdate(query);

        if (howManyUpdates == 0) {
            throw new SQLException("Movimentação não existe");
        }
    }
}
