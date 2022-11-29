package grupobala.Entities.Extract.Filter;

import java.util.Date;

import grupobala.Entities.Extract.Filter.IFilter.IFilter;
import grupobala.Entities.Transaction.ITransaction.ITransaction;

public class DateFilter implements IFilter {
    private Date fromDate;
    private Date toDate;

    public DateFilter(Date fromDate, Date toDate) {
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public boolean matchesFilter(ITransaction transaction) {
        Date transactionDate = transaction.getDate();

        return transactionDate.after(this.fromDate) && transactionDate.before(this.toDate);
    }
    
}
