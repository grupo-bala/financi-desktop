package grupobala.Entities.Extract.IExtract;

import grupobala.Entities.Transaction.ITransaction.ITransaction;

public interface IExtract extends Iterable<ITransaction> {

    public double getEntry();

    public double getOutput();
}
