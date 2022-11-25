package grupobala.Database.User;

import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.User.IDBUser.IDBUser;
import grupobala.Entities.User.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class DBUser implements IDBUser {

    private IDBConnection databaseConnection;

    public DBUser(IDBConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public void setUserBalance(int userID, double balance) throws SQLException {
        String query = String.format(
            Locale.US,
            "UPDATE usuario SET saldo = %f WHERE id = %d",
            balance,
            userID
        );

        int howManyUpdates = this.databaseConnection.executeUpdate(query);

        if (howManyUpdates == 0) {
            throw new SQLException("Usuário não existe");
        }

        new User().setBalance(balance);
    }

    @Override
    public void updateUserInformation() throws SQLException {
        User user = new User();

        String query = String.format(
            Locale.US,
            "UPDATE usuario SET nome = '%s', nomeusuario = '%s' WHERE id = %d",
            user.getName(),
            user.getUsername(),
            user.getID()
        );

        int howManyUpdates = this.databaseConnection.executeUpdate(query);

        if (howManyUpdates == 0) {
            throw new SQLException("Usuário não existe");
        }
    }

    @Override
    public void updatePassword(String password) throws SQLException {
        String query = String.format(
            Locale.US,
            "UPDATE usuario SET senha = '%s' WHERE id = %d",
            password,
            new User().getID()
        );

        int howManyUpdates = this.databaseConnection.executeUpdate(query);

        if (howManyUpdates == 0) {
            throw new SQLException("Usuário não existe");
        }
    }

    @Override
    public String getPassword() throws SQLException {
        String query = String.format(
            Locale.US,
            "SELECT senha FROM usuario WHERE id = %d",
            new User().getID()
        );

        ResultSet result = this.databaseConnection.executeQuery(query);

        if (!result.isBeforeFirst()) {
            throw new SQLException("Algo de errado ocorreu");
        }

        result.next();

        String password = result.getString("senha");

        result.close();

        return password;
    }
}
