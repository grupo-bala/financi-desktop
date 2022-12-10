package grupobala.Database;

import static org.junit.jupiter.api.Assertions.*;

import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.Setup.Setup;
import grupobala.SetupForTest.SetupForTest;
import java.sql.*;
import java.util.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@Order(1)
@TestMethodOrder(OrderAnnotation.class)
public class TestSetup {

    @Test
    @Order(1)
    public void testDBCreation() throws SQLException {
        SetupForTest.removeFinanciDB();

        Connection connection = Setup.setup();

        String query =
            "SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema'";

        ArrayList<String> expectedTables = new ArrayList<>(
            Arrays.asList(
                "movimentacao",
                "categoria",
                "usuario",
                "meta",
                "aulaassistida",
                "aula",
                "curso"
            )
        );

        ArrayList<String> tables = new ArrayList<>();

        Statement statement = connection.createStatement();

        ResultSet queryResult = statement.executeQuery(query);

        connection.close();

        while (queryResult.next()) {
            tables.add(queryResult.getString("tablename"));
        }

        expectedTables.sort(Comparator.naturalOrder());
        tables.sort(Comparator.naturalOrder());

        assertEquals(expectedTables, tables);
    }

    @Test
    @Order(2)
    public void testCategoryTable() throws SQLException {
        IDBConnection databaseConnection = new DBConnection();

        String query = "SELECT nome FROM categoria";

        ResultSet result = databaseConnection.executeQuery(query);

        ArrayList<String> values = new ArrayList<>();

        while (result.next()) {
            values.add(result.getString("nome"));
        }

        result.close();

        ArrayList<String> expected = new ArrayList<>(
            Arrays.asList(
                "comida",
                "roupa",
                "saúde",
                "entretenimento",
                "pagamentos",
                "outros"
            )
        );

        expected.sort(Comparator.naturalOrder());
        values.sort(Comparator.naturalOrder());

        assertEquals(expected, values);
    }
}
