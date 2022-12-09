package grupobala.Controller.Authentication.AuthenticationHandler;

public interface AuthenticationHandler {
    public void setNext(AuthenticationHandler handler);

    public void handle(
        String username,
        String password,
        String name,
        float wage
    ) throws Exception;
}
