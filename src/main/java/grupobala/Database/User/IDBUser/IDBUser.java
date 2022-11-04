package grupobala.Database.User.IDBUser;

import java.sql.SQLException;

public interface IDBUser {
    public void setUserBalance(int userID, double balance) throws SQLException;
}
