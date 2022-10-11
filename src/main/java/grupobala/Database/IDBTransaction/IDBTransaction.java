package grupobala.Database.IDBTransaction;

import java.util.Date;

public interface IDBTransaction {
    public boolean addTransaction(
        double valor,
        String description,
        String title,
        String category,
        Date date
    );
}
