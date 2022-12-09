package grupobala.Entities.Extract;

import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Iterator.IteratorInterface;
import grupobala.Entities.Iterator.ReverseIterator;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.util.ArrayList;
import java.util.Date;

public class Extract implements IExtract {

    private ArrayList<ITransaction> transactions;
    private double entry;
    private double output;

    public Extract(ArrayList<ITransaction> transactions) {
        this.transactions = transactions;
        this.entry = 0;
        this.output = 0;

        this.transactions.sort((t1, t2) -> {
                Date date1 = t1.getDate();
                Date date2 = t2.getDate();

                if (date1.equals(date2)) {
                    return 0;
                } else if (date1.before(date2)) {
                    return -1;
                } else {
                    return 1;
                }
            });

        for (ITransaction x : this.transactions) {
            if (x.getValue() < 0) {
                this.output += x.getValue();
            } else {
                this.entry += x.getValue();
            }
        }
    }

    @Override
    public double getEntry() {
        return this.entry;
    }

    @Override
    public double getOutput() {
        return this.output;
    }

    @Override
    public IteratorInterface<ITransaction> iterator() {
        return new ReverseIterator<ITransaction>(this.transactions);
    }
}
