package grupobala.Entities.Extract;

import grupobala.Entities.Extract.IExtract.IExtract;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.util.ArrayList;
import java.util.Iterator;

public class Extract implements IExtract {

    private ArrayList<ITransaction> transactions;
    private double entry;
    private double output;

    public Extract(ArrayList<ITransaction> transactions) {
        this.transactions = transactions;
        this.entry = 0;
        this.output = 0;

        for (ITransaction x : transactions) {
            this.transactions.add(x);

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
    public Iterator<ITransaction> iterator() {
        return this.transactions.iterator();
    }
}
