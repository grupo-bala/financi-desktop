package grupobala.Database.Authenticator.IDBAuthenticator;

import grupobala.Entities.User.IUser.IUser;
import java.sql.SQLException;

public interface IDBAuthenticator {
    public IUser login(String username, String password) throws SQLException;

    public boolean signUp(
        String username,
        String senha,
        String name,
        double wage
    ) throws SQLException;
}
