package grupobala.Entities.Goal.IGoal;

import java.util.Date;

public interface IGoal {
    public String getTitle();

    public void setTitle(String title);

    public double getObjective();

    public void setObjective(double objective);

    public Date getExpectedDate();

    public void setExpectedDate(Date date);

    public double getIdealValuePerMonth();

    public void setIdealValuePerMonth(double value);

    public double getAmountDeposited();

    public void setAmountDeposited(double amountDeposited);

    public int getID();
}
