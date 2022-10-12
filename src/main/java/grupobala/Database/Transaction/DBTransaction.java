package grupobala.Database.Transaction;

import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.Transaction.IDBTransaction.IDBTransaction;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
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
        String username,
        double valor,
        String title,
        CategoryEnum category,
        Date date
    ) throws SQLException {
        String query = String.format(
            Locale.US,
            "SELECT id FROM usuario WHERE nomeusuario = '%s'",
            username
        );

        ResultSet result = this.databaseConnection.executeQuery(query);

        if (!result.isBeforeFirst()) {
            throw new SQLException("Usuário não existe");
        }

        result.next();

        int userID = Integer.valueOf(result.getString("id"));

        result.close();

        query =
            String.format(
                Locale.US,
                "SELECT id FROM categoria WHERE nome = '%s'",
                category.databaseName
            );

        result = this.databaseConnection.executeQuery(query);

        if (!result.isBeforeFirst()) {
            throw new SQLException("Categoria não existe");
        }

        result.next();

        int categoryID = Integer.valueOf(result.getString("id"));

        result.close();

        DateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd");

        boolean isEntry = valor > 0.0;

        query =
            String.format(
                Locale.US,
                "INSERT INTO movimentacao(idusuario, valor, data, idcategoria, titulo, entrada) VALUES (%d, %f, '%s', %d, '%s', '%s')",
                userID,
                valor,
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

        result = this.databaseConnection.executeQuery(query);

        if (!result.isBeforeFirst()) {
            throw new SQLException("Algo de errado ocorreu");
        }

        result.next();

        int newTransactionID = Integer.valueOf(result.getString("id"));

        result.close();

        return new Transaction(newTransactionID, valor, title, category, date);
    }

    @Override
    public void removeTransaction(String username, int transactionID) throws SQLException {
        String query = String.format(
            Locale.US,
            "SELECT * FROM usuario WHERE nomeusuario = '%s'",
            username
        );

        ResultSet result = this.databaseConnection.executeQuery(query);

        if (!result.isBeforeFirst()) {
            throw new SQLException("Usuário não existe");
        }

        result.close();

        query = String.format(
            Locale.US,
            "DELETE FROM movimentacao WHERE id = %d",
            transactionID
        );

        int howManyUpdates = this.databaseConnection.executeUpdate(query);

        if (howManyUpdates == 0) {
            throw new SQLException("ID de transação não existe");
        }
    }
}
