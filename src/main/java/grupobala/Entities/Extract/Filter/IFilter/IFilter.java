package grupobala.Entities.Extract.Filter.IFilter;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.util.Date;

public interface IFilter {
    public boolean matchesFilter(ITransaction transaction);

    public void setFromDate(Date date);

    public void setToDate(Date date);

    public void setCategory(CategoryEnum category);
}
