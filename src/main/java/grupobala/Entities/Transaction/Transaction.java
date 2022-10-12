package grupobala.Entities.Transaction;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.util.Date;

public class Transaction implements ITransaction {

    private int id;
    private double valor;
    private String title;
    private CategoryEnum category;
    private Date date;

    public Transaction(
        int id,
        double valor,
        String title,
        CategoryEnum category,
        Date date
    ) {
        this.id = id;
        this.valor = valor;
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

    public String getTitle() {
        return this.title;
    }

    public CategoryEnum getCategory() {
        return this.category;
    }

    public Date getDate() {
        return this.date;
    }
}
