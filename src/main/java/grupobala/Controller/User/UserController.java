package grupobala.Controller.User;

import grupobala.Controller.User.IUserController.IUserController;
import grupobala.Crypt.Implementations.SHA256Encryptor;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.User.DBUser;
import grupobala.Database.User.IDBUser.IDBUser;
import grupobala.Entities.User.IUser.IUser;
import grupobala.Entities.User.User;

public class UserController implements IUserController {

    @Override
    public void editUserInfo(String newUserName, String name) throws Exception {
        IUser user = new User();

        user.setName(name);
        user.setUsername(newUserName);

        try {
            IDBUser idbUser = new DBUser(new DBConnection());
            idbUser.updateUserInformation();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void updatePassword(String oldPassword, String newPassword)
        throws Exception {
        IDBUser idbUser = new DBUser(new DBConnection());
        SHA256Encryptor encryptor = new SHA256Encryptor();

        if (!idbUser.getPassword().equals(encryptor.encrypt(oldPassword))) {
            throw new Exception("Senha atual errada");
        } else {
            try {
                idbUser.updatePassword(encryptor.encrypt(newPassword));
            } catch (Exception e) {
                throw new Exception(e.getMessage());
            }
        }
    }
}
