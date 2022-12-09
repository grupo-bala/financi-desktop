package grupobala.Entities.Extract.IExtract;

import grupobala.Entities.Iterator.IterableInterface;
import grupobala.Entities.Transaction.ITransaction.ITransaction;

public interface IExtract extends IterableInterface<ITransaction> {
    public double getEntry();

    public double getOutput();
}
