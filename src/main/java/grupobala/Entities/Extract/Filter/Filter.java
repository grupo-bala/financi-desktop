package grupobala.Entities.Extract.Filter;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Extract.Filter.IFilter.IFilter;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.util.Date;

public class Filter implements IFilter {

    private Date fromDate;
    private Date toDate;
    private CategoryEnum category;

    @Override
    public boolean matchesFilter(ITransaction transaction) {
        Date transactionDate = transaction.getDate();
        CategoryEnum transCategory = transaction.getCategory();

        boolean result = true;

        if (this.fromDate != null) {
            result &= transactionDate.after(this.fromDate);
        } if (this.toDate != null) {
            result &= transactionDate.before(this.toDate);
        } if (this.category != null) {
            result &= transCategory.databaseName.equals(this.category.databaseName);
        }

        return result;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }
}
