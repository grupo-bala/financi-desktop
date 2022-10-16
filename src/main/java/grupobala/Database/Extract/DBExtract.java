package grupobala.Database.Extract;

import grupobala.Database.Category.DBCategory;
import grupobala.Database.Category.IDBCategory.IDBCategory;
import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.Extract.IDBExtract.IDBExtract;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.Transaction.Transaction;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class DBExtract implements IDBExtract {

    private IDBConnection databaseConnection;

    public DBExtract(IDBConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public ArrayList<ITransaction> getExtract(
        int userID,
        Date initial,
        Date end
    ) throws SQLException, ParseException {
        DateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd");

        String query = String.format(
            Locale.US,
            "SELECT * FROM movimentacao WHERE idusuario = %d AND data BETWEEN '%s' AND '%s'",
            userID,
            formateDate.format(initial),
            formateDate.format(end)
        );

        ResultSet result = this.databaseConnection.executeQuery(query);

        ArrayList<ITransaction> transactions = new ArrayList<>();

        IDBCategory categoryDB = new DBCategory(this.databaseConnection);

        while (result.next()) {
            String title = result.getString("titulo");
            double wage = result.getDouble("valor");
            int ID = result.getInt("id");
            Date date = formateDate.parse(result.getString("data"));
            String categoryName = categoryDB.getCategoryName(
                result.getInt("idcategoria")
            );
            CategoryEnum category = CategoryEnum.getCategory(categoryName);

            ITransaction transaction = new Transaction(
                ID,
                wage,
                title,
                category,
                date
            );

            transactions.add(transaction);
        }

        result.close();

        return transactions;
    }
}
