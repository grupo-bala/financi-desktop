package grupobala.Database.Category;

import java.sql.*;
import java.util.Locale;

import grupobala.Database.Category.IDBCategory.IDBCategory;
import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Entities.Category.CategoryEnum;

public class DBCategory implements IDBCategory {
    private IDBConnection databaseConnection;

    public DBCategory(IDBConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public int getCategoryID(CategoryEnum category) throws SQLException {
        String query =
            String.format(
                Locale.US,
                "SELECT id FROM categoria WHERE nome = '%s'",
                category.databaseName
            );

        ResultSet result = this.databaseConnection.executeQuery(query);

        if (!result.isBeforeFirst()) {
            throw new SQLException("Categoria não existe");
        }

        result.next();

        int categoryID = result.getInt("id");

        result.close();

        return categoryID;
    }

    @Override
    public String getCategoryName(int categoryID) throws SQLException {
        String query =
            String.format(
                Locale.US,
                "SELECT nome FROM categoria WHERE id = %d",
                categoryID
            );

        ResultSet result =
            this.databaseConnection.executeQuery(query);

        if (!result.isBeforeFirst()) {
            throw new SQLException("Categoria inexistente");
        }

        result.next();

        String categoryName = result.getString("nome");

        result.close();

        return categoryName;
    }
}
