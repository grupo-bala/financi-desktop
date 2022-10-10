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
    public boolean login(String username, String password) throws SQLException {
        String query = String.format(
            Locale.US,
            "SELECT nome FROM usuario WHERE nomeusuario = '%s' AND senha = '%s'",
            username,
            password
        );

        ResultSet result = DBController.STATEMENT.executeQuery(query);

        return result.isBeforeFirst();
    }

    @Override
    public boolean signUp(
        String username,
        String password,
        String name,
        double wage
    ) throws SQLException {
        try {
            String query = String.format(
                Locale.US,
                "INSERT INTO usuario(nome, nomeusuario, senha, rendafixa) VALUES ('%s', '%s', '%s', %f)",
                name,
                username,
                password,
                wage
            );

            DBController.STATEMENT.executeUpdate(query);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }
}
