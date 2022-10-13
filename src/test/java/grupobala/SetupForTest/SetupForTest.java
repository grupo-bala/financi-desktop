package grupobala.SetupForTest;

import java.sql.*;

public class SetupForTest {

    public static void truncateTables() throws SQLException {
        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
        );

        Statement statement = connection.createStatement();

        String[] queries = {
            "TRUNCATE TABLE usuario CASCADE",
            "TRUNCATE TABLE meta CASCADE",
            "TRUNCATE TABLE aulaassistida CASCADE",
            "TRUNCATE TABLE aula CASCADE",
            "TRUNCATE TABLE movimentacao CASCADE",
        };

        for (String query : queries) {
            statement.executeUpdate(query);
        }

        connection.close();
    }

    public static void removeFinanciDB() throws SQLException {
        try {
            Connection rootConnection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/?user=postgres&password=postgres"
            );

            String query = "DROP DATABASE financi";

            rootConnection.createStatement().executeUpdate(query);

            rootConnection.close();
        } catch (Exception e) {
            System.out.println("Financi database already dropped");
        }
    }

    public static void addFinanciUser() throws SQLException {
        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
        );

        Statement statement = connection.createStatement();

        String query =
            "INSERT INTO usuario(nome, nomeusuario, senha, rendafixa) VALUES ('Financi', 'financi', '1234', 1000)";

        statement.executeUpdate(query);

        connection.close();
    }
}
