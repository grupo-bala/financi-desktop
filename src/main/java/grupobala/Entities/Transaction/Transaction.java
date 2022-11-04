package grupobala.Entities.Transaction;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.util.Date;

public class Transaction implements ITransaction {

    private int id;
    private double value;
    private String title;
    private CategoryEnum category;
    private Date date;

    public Transaction(
        int id,
        double value,
        String title,
        CategoryEnum category,
        Date date
    ) {
        this.id = id;
        this.value = value;
        this.title = title;
        this.category = category;
        this.date = date;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public CategoryEnum getCategory() {
        return this.category;
    }

    @Override
    public Date getDate() {
        return this.date;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    @Override
    public void setDate(Date date) {
        this.date = date;
    }
}
