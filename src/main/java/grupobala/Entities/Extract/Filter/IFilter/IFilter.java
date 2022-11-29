package grupobala.Entities.Extract.Filter.IFilter;

import grupobala.Entities.Transaction.ITransaction.ITransaction;

public interface IFilter {
    public boolean matchesFilter(ITransaction transaction);
}
