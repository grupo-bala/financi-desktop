package grupobala.Entities.Transaction.ITransaction;

import java.util.Date;

public interface ITransaction {
    public int getId();

    public double getValor();

    public String getDescription();

    public String getTitle();

    public String getCategory();

    public Date getDate();
}
