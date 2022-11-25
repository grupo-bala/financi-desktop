package grupobala.Entities.User.IUser;

public interface IUser {
    public int getID();

    public double getValue();

    public String getName();

    public String getUsername();

    public void setBalance(double balance);

    public void setName(String name);

    public void setUsername(String username);

    public void close();
}
