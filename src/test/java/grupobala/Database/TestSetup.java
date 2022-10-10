package grupobala.Database;

import static org.junit.jupiter.api.Assertions.*;

import grupobala.Database.Setup.Setup;
import java.sql.*;
import java.util.*;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

public class TestSetup {

    @Test
    @Order(1)
    public void testDBCreation() throws Exception {
        Statement statement = DriverManager
            .getConnection(
                "jdbc:postgresql://localhost:5432/?user=postgres&password=postgres"
            )
            .createStatement();

        TestSetup.removeDB(statement);

        Setup.setup();

        ArrayList<String> databases = new ArrayList<>();

        String query = "SELECT datname FROM pg_database";

        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            databases.add(queryResult.getString("datname"));
        }

        assertNotEquals(-1, databases.indexOf("financi"));
    }

    @Test
    @Order(2)
    public void testDBCreatedTables() throws Exception {
        Statement statement = DriverManager
            .getConnection(
                "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
            )
            .createStatement();

        ArrayList<String> expectedTables = new ArrayList<>(
            Arrays.asList(
                "movimentacao",
                "categoria",
                "usuario",
                "meta",
                "aulaassistida",
                "aula"
            )
        );

        ArrayList<String> tables = new ArrayList<>();

        String query =
            "SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema'";

        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            tables.add(queryResult.getString("tablename"));
        }

        expectedTables.sort(Comparator.naturalOrder());
        tables.sort(Comparator.naturalOrder());

        assertEquals(expectedTables, tables);
    }

    private static void removeDB(Statement statement) {
        try {
            String query = "DROP DATABASE financi";

            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Financi database already dropped");
        }
    }
}
