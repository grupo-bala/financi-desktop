package grupobala.Controller.Authentication;

import grupobala.Controller.Authentication.AuthenticationHandler.AuthenticationHandler;
import grupobala.Controller.Authentication.IAuthenticationController.IAuthenticationController;
import grupobala.Crypt.Encryptor.Encryptor;
import grupobala.Crypt.Implementations.SHA256Encryptor;
import grupobala.Database.Authenticator.DBAuthenticator;
import grupobala.Database.Connection.DBConnection;
import grupobala.Entities.User.IUser.IUser;
import java.sql.SQLException;

public class AuthenticationController implements IAuthenticationController {

    DBAuthenticator dbAuthenticator;
    Encryptor encryptor;
    AuthenticationHandler authenticationHandler;

    public AuthenticationController() {
        this.dbAuthenticator = new DBAuthenticator(new DBConnection());
        this.encryptor = new SHA256Encryptor();
        
        AuthenticationHandler usernameHandler = new UsernameHandler();
        AuthenticationHandler passwordHandler = new PasswordHandler();
        AuthenticationHandler registerHandler = new RegisterHandler();

        usernameHandler.setNext(passwordHandler);
        passwordHandler.setNext(registerHandler);
        this.authenticationHandler = usernameHandler;
    }

    @Override
    public IUser signIn(String username, String password) throws Exception {
        String hashPassword = encryptor.encrypt(password);

        try {
            return dbAuthenticator.login(username, hashPassword);
        } catch (SQLException e) {
            throw new Exception("Usuário ou senha inválidos");
        }
    }

    @Override
    public void signUp(
        String username,
        String password,
        String name,
        float wage
    ) throws Exception {
        this.authenticationHandler.handle(username, password, name, wage);
    }
}
