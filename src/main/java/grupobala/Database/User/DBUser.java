package grupobala.Database.User;

import grupobala.Database.Connection.IDBConnection.IDBConnection;
import grupobala.Database.User.IDBUser.IDBUser;
import grupobala.Entities.User.User;

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
}
