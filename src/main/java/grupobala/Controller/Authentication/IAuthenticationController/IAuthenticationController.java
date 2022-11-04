package grupobala.Controller.Authentication.IAuthenticationController;

import grupobala.Entities.User.IUser.IUser;

public interface IAuthenticationController {
    public IUser signIn(String username, String password) throws Exception;

    public void signUp(
        String username,
        String password,
        String name,
        float wage
    ) throws Exception;
}
