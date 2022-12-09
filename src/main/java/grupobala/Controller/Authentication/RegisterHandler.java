package grupobala.Controller.Authentication;

import grupobala.Controller.Authentication.AuthenticationHandler.AuthenticationHandler;
import grupobala.Crypt.Encryptor.Encryptor;
import grupobala.Crypt.Implementations.SHA256Encryptor;
import grupobala.Database.Authenticator.DBAuthenticator;
import grupobala.Database.Connection.DBConnection;

public class RegisterHandler implements AuthenticationHandler {

    DBAuthenticator dbAuthenticator = new DBAuthenticator(new DBConnection());
    Encryptor encryptor = new SHA256Encryptor();
    AuthenticationHandler next;

    @Override
    public void setNext(AuthenticationHandler handler) {
        this.next = handler;
    }

    @Override
    public void handle(String username, String password, String name, float wage) throws Exception {
        String hashPassword = encryptor.encrypt(password);

        if (!dbAuthenticator.signUp(username, hashPassword, name, wage)) {
            throw new Exception("Erro interno");
        } else if (this.next != null) {
            this.next.handle(username, hashPassword, name, wage);
        }
    }
    
}
