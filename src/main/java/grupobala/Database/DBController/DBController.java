package grupobala.Database.DBController;

import grupobala.Database.IDBController.IDBController;
import grupobala.Database.Setup.Setup;
import java.sql.*;
import java.util.Locale;

public class DBController implements IDBController {

    private static boolean INSTANCIATED = false;
    private static Statement STATEMENT = null;

    public DBController() {
        if (!DBController.INSTANCIATED) {
            DBController.INSTANCIATED = true;

            try {
                Connection connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
                );

                DBController.STATEMENT = connection.createStatement();
            } catch (SQLException e) {
                DBController.STATEMENT = Setup.setup();
            }
        }
    }

    @Override
    public boolean login(String username, String password) {
        String query = String.format(
            Locale.US,
            "SELECT nome FROM usuario WHERE nomeusuario = '%s' AND senha = '%s'",
            username,
            password
        );

        try {
            ResultSet result = DBController.STATEMENT.executeQuery(query);

            result.next();
            result.getString("nome");
        } catch (SQLException e) {
            return false;
        }

        return true;
    }

    @Override
    public boolean signUp(
        String username,
        String password,
        String name,
        double wage
    ) throws Exception {
        String query = String.format(
            Locale.US,
            "SELECT nome FROM usuario WHERE nomeusuario = '%s'",
            username
        );

        try {
            ResultSet result = DBController.STATEMENT.executeQuery(query);

            result.next();
            result.getString("nome");
        } catch (SQLException e) {
            query =
                String.format(
                    Locale.US,
                    "INSERT INTO usuario(nome, nomeusuario, senha, rendafixa) VALUES ('%s', '%s', '%s', %f)",
                    name,
                    username,
                    password,
                    wage
                );

            DBController.STATEMENT.executeUpdate(query);

            return true;
        }

        return false;
    }

    public void dropEverything() throws Exception {
        String[] queries = {
            "TRUNCATE TABLE usuario CASCADE",
            "TRUNCATE TABLE meta CASCADE",
            "TRUNCATE TABLE aulaassistida CASCADE",
            "TRUNCATE TABLE aula CASCADE",
            "TRUNCATE TABLE movimentacao CASCADE",
            "TRUNCATE TABLE categoria CASCADE",
        };

        for (String query : queries) {
            DBController.STATEMENT.executeUpdate(query);
        }
    }
}
