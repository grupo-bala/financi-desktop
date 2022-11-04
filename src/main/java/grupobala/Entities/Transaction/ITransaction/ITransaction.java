package grupobala.Entities.Transaction.ITransaction;

import grupobala.Entities.Category.CategoryEnum;
import java.util.Date;

public interface ITransaction {
    public int getId();

    public double getValue();

    public String getTitle();

    public CategoryEnum getCategory();

    public Date getDate();

    public void setValue(double value);

    public void setTitle(String title);

    public void setCategory(CategoryEnum category);

    public void setDate(Date date);
}
