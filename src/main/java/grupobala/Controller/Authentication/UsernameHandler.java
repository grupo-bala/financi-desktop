package grupobala.Controller.Authentication;

import grupobala.Controller.Authentication.AuthenticationHandler.AuthenticationHandler;
import grupobala.Database.Authenticator.DBAuthenticator;
import grupobala.Database.Connection.DBConnection;

public class UsernameHandler implements AuthenticationHandler {

    private AuthenticationHandler next;
    private DBAuthenticator dbAuthenticator = new DBAuthenticator(new DBConnection());

    @Override
    public void setNext(AuthenticationHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(String username, String password, String name, float wage) throws Exception {
        if (dbAuthenticator.hasUsername(username)) {
            throw new Exception("Nome de usuário já em uso");
        } else if (this.next != null) {
            this.next.handle(username, password, name, wage);
        }
    }
    
}
