package grupobala.Entities.User;

import grupobala.Entities.User.IUser.IUser;

public class User implements IUser {

    private static boolean INSTANCIATED;
    private static String USERNAME;
    private static String NAME;
    private static double VALUE;
    private static int ID;

    public User(String username, String name, double value, int ID) {
        if (!User.INSTANCIATED) {
            User.USERNAME = username;
            User.NAME = name;
            User.VALUE = value;
            User.ID = ID;
            User.INSTANCIATED = true;
        }
    }

    public User() {
        if (!User.INSTANCIATED) {
            throw new RuntimeException("Usuário precisa ser inicializado");
        }
    }

    @Override
    public int getID() {
        return User.ID;
    }

    @Override
    public double getValue() {
        return User.VALUE;
    }

    @Override
    public String getName() {
        return User.NAME;
    }

    @Override
    public String getUsername() {
        return User.USERNAME;
    }

    @Override
    public void setBalance(double balance) {
        User.VALUE = balance;
    }

    @Override
    public void close() {
        User.INSTANCIATED = false;
    }

    @Override
    public void setName(String name) {
        User.NAME = name;
    }

    @Override
    public void setUsername(String username) {
        User.USERNAME = username;
    }
}
