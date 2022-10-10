package grupobala.Database.IDBController;

public interface IDBController {
    public boolean login(String username, String password) throws Exception;

    public boolean signUp(
        String username,
        String senha,
        String name,
        double wage
    ) throws Exception;
}
