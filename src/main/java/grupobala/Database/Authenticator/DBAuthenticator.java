package grupobala.Database.Authenticator;

import java.sql.*;
import java.util.Locale;

import grupobala.Database.Authenticator.IDBAuthenticator.IDBAuthenticator;
import grupobala.Database.Connection.IDBConnection.IDBConnection;

public class DBAuthenticator implements IDBAuthenticator {
    private IDBConnection databaseConnection;

    public DBAuthenticator(IDBConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public boolean login(String username, String password) throws SQLException {
        String query = String.format(
            Locale.US,
            "SELECT nome FROM usuario WHERE nomeusuario = '%s' AND senha = '%s'",
            username,
            password
        );

        ResultSet result = this.databaseConnection.executeQuery(query);

        boolean loginResult = result.isBeforeFirst();

        return loginResult;
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

            this.databaseConnection.executeUpdate(query);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }    
}
