package grupobala.Controller.Authentication;

import grupobala.Controller.Authentication.IAuthenticationController.IAuthenticationController;
import grupobala.Crypt.Encryptor.Encryptor;
import grupobala.Crypt.Implementations.SHA256Encryptor;
import grupobala.Database.Authenticator.DBAuthenticator;
import grupobala.Database.Connection.DBConnection;
import java.sql.SQLException;

public class AuthenticationController implements IAuthenticationController {

    DBAuthenticator dbAuthenticator;
    Encryptor encryptor;

    public AuthenticationController() {
        this.dbAuthenticator = new DBAuthenticator(new DBConnection());
        this.encryptor = new SHA256Encryptor();
    }

    @Override
    public void signIn(String username, String password) throws Exception {
        String hashPassword = encryptor.encrypt(password);

        try {
            dbAuthenticator.login(username, hashPassword);
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
        String hashPassword = encryptor.encrypt(password);
        if (!dbAuthenticator.signUp(username, hashPassword, name, wage)) {
            throw new Exception("Nome de usuário já em uso");
        }
    }
}
