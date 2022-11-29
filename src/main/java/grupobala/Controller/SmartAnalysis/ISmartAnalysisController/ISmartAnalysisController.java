package grupobala.Controller.SmartAnalysis.ISmartAnalysisController;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javafx.util.Pair;

public interface ISmartAnalysisController {
    public String convertToMonth(Date date);

    public Double getTotalEntry(ArrayList<ITransaction> entrys);

    public Double getTotalOutput(ArrayList<ITransaction> userTransactions);

    public Pair<CategoryEnum, Double> getHighestOutputPercentageByCategory(
        int userID
    ) throws Exception, SQLException, ParseException;

    public Pair<CategoryEnum, Double> getHighestEntryPercentageByCategory(
        int userID
    ) throws Exception, SQLException, ParseException;

    public ArrayList<ITransaction> getTransactions(int userID) throws Exception;

    public ArrayList<ITransaction> getEntrysArrayList(
        ArrayList<ITransaction> userTransactions
    );

    public ArrayList<ITransaction> getOutputsArrayList(
        ArrayList<ITransaction> userTransactions
    );

    public Map<CategoryEnum, Double> getValuesByCategory(
        ArrayList<ITransaction> transactions
    );

    public Pair<CategoryEnum, Double> compareValuesByCategorys(
        ArrayList<ITransaction> transactions
    );

    public String convertOutputToString(int userID) throws Exception;

    public String convertEntryToString(int userID) throws Exception;

    public String getHint(int userID)
        throws Exception, SQLException, ParseException;
}
