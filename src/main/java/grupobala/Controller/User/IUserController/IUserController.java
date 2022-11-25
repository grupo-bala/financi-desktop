package grupobala.Controller.User.IUserController;

public interface IUserController {
    public void editUserInfo(String newUserName, String name) throws Exception;

    public void updatePassword(String oldPassword, String newPassword) throws Exception;
}
