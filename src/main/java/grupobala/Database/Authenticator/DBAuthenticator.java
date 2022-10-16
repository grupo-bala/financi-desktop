package grupobala.Database.Authenticator;

import grupobala.Database.Authenticator.IDBAuthenticator.IDBAuthenticator;
import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Entities.User.IUser.IUser;
import grupobala.Entities.User.User;
import java.sql.*;
import java.util.Locale;

public class DBAuthenticator implements IDBAuthenticator {

    private IDBConnection databaseConnection;

    public DBAuthenticator(IDBConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public IUser login(String username, String password) throws SQLException {
        String query = String.format(
            Locale.US,
            "SELECT * FROM usuario WHERE nomeusuario = '%s' AND senha = '%s'",
            username,
            password
        );

        ResultSet result = this.databaseConnection.executeQuery(query);

        if (!result.isBeforeFirst()) {
            throw new SQLException("Usuário não existe");
        }

        result.next();

        String name = result.getString("nome");
        double balance = result.getDouble("saldo");
        int ID = result.getInt("id");

        IUser user = new User(username, name, balance, ID);

        result.close();
        user.close();

        return user;
    }

    @Override
    public boolean signUp(
        String username,
        String password,
        String name,
        double balance
    ) throws SQLException {
        try {
            String query = String.format(
                Locale.US,
                "INSERT INTO usuario(nome, nomeusuario, senha, saldo) VALUES ('%s', '%s', '%s', %f)",
                name,
                username,
                password,
                balance
            );

            this.databaseConnection.executeUpdate(query);
        } catch (SQLException e) {
            return false;
        }

        return true;
    }
}
