package grupobala.Entities.Goal;

import grupobala.Entities.Goal.IGoal.IGoal;
import java.util.Date;

public class Goal implements IGoal {

    private int id;
    private String title;
    private double objective;
    private Date date;
    private double idealValuePerMonth;
    private double amountDeposited;

    public Goal(
        int id,
        String title,
        double objective,
        Date date,
        double idealValuePerMonth
    ) {
        this.id = id;
        this.title = title;
        this.objective = objective;
        this.date = date;
        this.idealValuePerMonth = idealValuePerMonth;
        this.amountDeposited = 0;
    }

    public Goal(
        int id,
        String title,
        double objective,
        Date date,
        double idealValuePerMonth,
        double depositedValue
    ) {
        this.id = id;
        this.title = title;
        this.objective = objective;
        this.date = date;
        this.idealValuePerMonth = idealValuePerMonth;
        this.amountDeposited = depositedValue;
    }

    @Override
    public String getTitle() {
        return this.title;
    }

    @Override
    public double getObjective() {
        return this.objective;
    }

    @Override
    public Date getExpectedDate() {
        return this.date;
    }

    @Override
    public double getIdealValuePerMonth() {
        return this.idealValuePerMonth;
    }

    @Override
    public double getAmountDeposited() {
        return this.amountDeposited;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public void setObjective(double objective) {
        this.objective = objective;
    }

    @Override
    public void setExpectedDate(Date date) {
        this.date = date;
    }

    @Override
    public void setIdealValuePerMonth(double value) {
        this.idealValuePerMonth = value;
    }

    @Override
    public void setAmountDeposited(double amountDeposited) {
        this.amountDeposited = amountDeposited;
    }
}
