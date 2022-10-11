package grupobala.Entities.Transaction;

import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.util.Date;

public class Transaction implements ITransaction {

    private int id;
    private double valor;
    private String description;
    private String title;
    private String category;
    private Date date;

    public Transaction(
        int id,
        double valor,
        String description,
        String title,
        String category,
        Date date
    ) {
        this.id = id;
        this.valor = valor;
        this.description = description;
        this.title = title;
        this.category = category;
        this.date = date;
    }

    public int getId() {
        return this.id;
    }

    public double getValor() {
        return this.valor;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTitle() {
        return this.title;
    }

    public String getCategory() {
        return this.category;
    }

    public Date getDate() {
        return this.date;
    }
}
