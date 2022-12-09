package grupobala.Controller.Authentication;

import java.util.regex.Pattern;

import grupobala.Controller.Authentication.AuthenticationHandler.AuthenticationHandler;

public class PasswordHandler implements AuthenticationHandler {

    private AuthenticationHandler next;
    Pattern passwordPattern = Pattern.compile("^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9]).+$");

    @Override
    public void setNext(AuthenticationHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(String username, String password, String name, float wage) throws Exception {
        boolean result = passwordPattern.matcher(password).find();

        if (!result) {
            throw new Exception("Senha fraca");
        } else if (this.next != null) {
            this.next.handle(username, password, name, wage);
        }
    }
}
