package grupobala.Database.Authenticator.IDBAuthenticator;

import java.sql.SQLException;

public interface IDBAuthenticator {
    public boolean login(String username, String password) throws SQLException;

    public boolean signUp(
        String username,
        String senha,
        String name,
        double wage
    ) throws SQLException;
}
