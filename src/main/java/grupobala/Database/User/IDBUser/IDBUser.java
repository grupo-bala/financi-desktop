package grupobala.Database.User.IDBUser;

import java.sql.SQLException;

public interface IDBUser {
    public void setUserBalance(int userID, double balance) throws SQLException;

    public void updateUserInformation() throws SQLException;

    public void updatePassword(String password) throws SQLException;

    public String getPassword() throws SQLException;
}
