package grupobala.Entities.User.IUser;

public interface IUser {
    public int getID();

    public double getValue();

    public String getName();

    public String getUsername();

    public void setBalance(double balance);

    public void close();
}
