package grupobala.SetupForTest;

import java.util.Locale;
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


    public static int addDefaultTransaction() throws SQLException {
        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
        );

        Statement statement = connection.createStatement();

        String query = "SELECT id FROM usuario WHERE nomeusuario = 'financi'";

        ResultSet result = statement.executeQuery(query);

        if (!result.isBeforeFirst()) {
            throw new SQLException("Usuário financi não foi adicionado");
        }

        result.next();

        int financiUserID = Integer.valueOf(result.getString("id"));

        query =
            String.format(
                Locale.US,
                "INSERT INTO movimentacao(idusuario, valor, data, idcategoria, titulo, entrada) VALUES (%d, 1000, '2022-10-21', 1, 'Teste', true)",
                financiUserID
            );

        statement.executeUpdate(query);
        
        query = String.format(Locale.US, "SELECT id FROM movimentacao WHERE idusuario = %d", financiUserID);

	result = statement.executeQuery(query);
	
	result.next();
	
	int transactionID = Integer.valueOf(result.getString("id"));

        connection.close();
        
        return transactionID;
    }
}

