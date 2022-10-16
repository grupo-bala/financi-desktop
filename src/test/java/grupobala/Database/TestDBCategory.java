package grupobala.Database;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import grupobala.Database.Category.DBCategory;
import grupobala.Database.Category.IDBCategory.IDBCategory;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Entities.Category.CategoryEnum;

public class TestDBCategory {
    IDBConnection databaseConnection = new DBConnection();
    IDBCategory categoryDB = new DBCategory(databaseConnection);

    @Test
    public void testGetCategoryID() throws SQLException {
        String query = "SELECT id FROM categoria WHERE nome = 'comida'";

        ResultSet result = databaseConnection.executeQuery(query);

        result.next();

        int expected = result.getInt("id");

        result.close();

        int ID = categoryDB.getCategoryID(CategoryEnum.FOOD);

        assertEquals(expected, ID);
    }

    @Test
    public void testGetCategoryName() throws SQLException {
        String query = "SELECT nome FROM categoria WHERE id = 1";

        ResultSet result = databaseConnection.executeQuery(query);

        result.next();

        String expected = result.getString("nome");

        result.close();

        String ID = categoryDB.getCategoryName(1);

        assertEquals(expected, ID);
    }

    @Test
    public void testGetCategoryNameShouldFailNonexistentID() throws SQLException {
        Exception exception = assertThrows(SQLException.class, () -> {
            categoryDB.getCategoryName(-1);
        });

        String expected = "Categoria inexistente";
        String result = exception.getMessage();

        assertEquals(expected, result);
    }
}
