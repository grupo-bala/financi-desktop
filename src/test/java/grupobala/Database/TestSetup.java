package grupobala.Database;

import static org.junit.jupiter.api.Assertions.*;

import grupobala.Database.Setup.Setup;
import java.sql.*;
import java.util.*;
import org.junit.jupiter.api.Test;

public class TestSetup {

    @Test
    public void testDBCreation() throws SQLException {
        TestSetup.removeDB();

        Statement statement = Setup.setup();

        String query =
            "SELECT tablename FROM pg_catalog.pg_tables WHERE schemaname != 'pg_catalog' AND schemaname != 'information_schema'";

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

        ResultSet queryResult = statement.executeQuery(query);

        while (queryResult.next()) {
            tables.add(queryResult.getString("tablename"));
        }

        expectedTables.sort(Comparator.naturalOrder());
        tables.sort(Comparator.naturalOrder());

        assertEquals(expectedTables, tables);
    }

    private static void removeDB() {
        try {
            Statement statement = DriverManager
                .getConnection(
                    "jdbc:postgresql://localhost:5432/?user=postgres&password=postgres"
                )
                .createStatement();

            String query = "DROP DATABASE financi";

            statement.executeUpdate(query);
        } catch (Exception e) {
            System.out.println("Financi database already dropped");
        }
    }
}
