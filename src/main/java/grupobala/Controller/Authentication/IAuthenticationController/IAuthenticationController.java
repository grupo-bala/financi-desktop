package grupobala.Controller.Authentication.IAuthenticationController;

public interface IAuthenticationController {
    public void signIn(String username, String password) throws Exception;

    public void signUp(
        String username,
        String password,
        String name,
        float wage
    ) throws Exception;
}
