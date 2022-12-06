package grupobala.Entities.Extract.Filter.IFilter;

import java.util.Date;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;

public interface IFilter {
    public boolean matchesFilter(ITransaction transaction);
    public void setFromDate(Date date);
    public void setToDate(Date date);
    public void setCategory(CategoryEnum category);
}
