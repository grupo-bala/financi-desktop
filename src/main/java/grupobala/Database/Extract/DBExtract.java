package grupobala.Database.Extract;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Date;

import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.Extract.IDBExtract.IDBExtract;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.Transaction;
import grupobala.Entities.Transaction.ITransaction.ITransaction;

public class DBExtract implements IDBExtract {
    private IDBConnection databaseConnection;

    public DBExtract(IDBConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<ITransaction> getExtract(String username, Date initial, Date end) throws SQLException, ParseException {
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
        
        DateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd");

        query = String.format(
            Locale.US,
            "SELECT * FROM movimentacao WHERE idusuario = %d AND data BETWEEN '%s' AND '%s'",
            userID,
            formateDate.format(initial),
            formateDate.format(end)
        );

        result = this.databaseConnection.executeQuery(query);

        ArrayList<ITransaction> transactions = new ArrayList<>();

        while (result.next()) {
            String title = result.getString("titulo");
            double wage = Double.valueOf(result.getString("valor"));
            int ID = Integer.valueOf(result.getString("id"));
            Date date = formateDate.parse(result.getString("data"));
            int categoryID = Integer.valueOf(result.getString("idcategoria"));
            
            query = String.format(
                Locale.US,
                "SELECT nome FROM categoria WHERE id = %d",
                categoryID
            );

            ResultSet categoryResult = this.databaseConnection.executeQuery(query);

            categoryResult.next();

            CategoryEnum category = CategoryEnum.getCategory(categoryResult.getString("nome"));

            categoryResult.close();
            
            ITransaction transaction = new Transaction(ID, wage, title, category, date);

            transactions.add(transaction);
        }

        result.close();

        return transactions;
    }
}
