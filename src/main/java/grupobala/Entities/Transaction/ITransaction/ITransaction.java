package grupobala.Entities.Transaction.ITransaction;

import java.util.Date;

import grupobala.Entities.Category.CategoryEnum;

public interface ITransaction {
    public int getId();

    public double getValor();

    public String getTitle();

    public CategoryEnum getCategory();

    public Date getDate();
}
