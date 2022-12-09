package grupobala.Controller.SmartAnalysis;

import grupobala.Controller.SmartAnalysis.ISmartAnalysisController.ISmartAnalysisController;
import grupobala.Database.Connection.DBConnection;
import grupobala.Database.Extract.DBExtract;
import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

public class SmartAnalysisController implements ISmartAnalysisController {

    DBExtract dbExtract;

    public SmartAnalysisController() {
        dbExtract = new DBExtract(new DBConnection());
    }

    @Override
    public String convertToMonth(Date date) {
        String pattern = "yyyy-MM-dd";

        DateFormat dFormat = new SimpleDateFormat(pattern);

        String sDate = dFormat.format(date);

        String month = (sDate.substring(5, 7));

        return month;
    }

    @Override
    public Double getTotalEntry(ArrayList<ITransaction> entrys) {
        Double totalEntry = 0.0;
        for (ITransaction transaction : getEntrysArrayList(entrys)) {
            totalEntry += transaction.getValue();
        }
        if (totalEntry == 0) {
            totalEntry = 1.0;
        }
        return totalEntry;
    }

    @Override
    public Double getTotalOutput(ArrayList<ITransaction> userTransactions) {
        Double totalOutput = 0.0;
        for (ITransaction transaction : getOutputsArrayList(userTransactions)) {
            totalOutput += Math.abs(transaction.getValue());
        }
        if (totalOutput == 0) {
            totalOutput = 1.0;
        }
        return totalOutput;
    }

    @Override
    public Pair<CategoryEnum, Double> getHighestOutputPercentageByCategory(
        int userID
    ) throws Exception, SQLException, ParseException {
        try {
            ArrayList<ITransaction> outputs = getOutputsArrayList(
                getTransactions(userID)
            );
            Pair<CategoryEnum, Double> highestCategory = compareValuesByCategorys(
                outputs
            );
            double total = getTotalOutput(outputs);
            double highestPercentage = highestCategory.getValue() * 100.0;
            return new Pair<CategoryEnum, Double>(
                highestCategory.getKey(),
                highestPercentage / total
            );
        } catch (SQLException error) {
            throw new Exception("erro ao pegar maior por categoria");
        } catch (ParseException error) {
            throw new Exception("erro ao pegar maior por categoria");
        }
    }

    @Override
    public Pair<CategoryEnum, Double> getHighestEntryPercentageByCategory(
        int userID
    ) throws Exception, SQLException, ParseException {
        try {
            ArrayList<ITransaction> entrys = getEntrysArrayList(
                getTransactions(userID)
            );
            Pair<CategoryEnum, Double> highestCategory = compareValuesByCategorys(
                entrys
            );
            double total = getTotalEntry(entrys);
            double highestPercentage = highestCategory.getValue() * 100.0;
            return new Pair<CategoryEnum, Double>(
                highestCategory.getKey(),
                highestPercentage / total
            );
        } catch (SQLException error) {
            throw new Exception("erro ao pegar maior por categoria");
        } catch (ParseException error) {
            throw new Exception("erro ao pegar maior por categoria");
        }
    }

    @Override
    public ArrayList<ITransaction> getTransactions(int userID)
        throws Exception {
        try {
            ArrayList<ITransaction> transactions = dbExtract.getCompleteExtract(userID);
            return transactions;
        } catch (SQLException error) {
            throw new Exception();
        } catch (ParseException error) {
            throw new Exception();
        }
    }

    @Override
    public ArrayList<ITransaction> getEntrysArrayList(
        ArrayList<ITransaction> userTransactions
    ) {
        ArrayList<ITransaction> entrys = new ArrayList<>();
        for (ITransaction transaction : userTransactions) {
            if (transaction.getValue() > 0) {
                entrys.add(transaction);
            }
        }

        return entrys;
    }

    @Override
    public ArrayList<ITransaction> getOutputsArrayList(
        ArrayList<ITransaction> userTransactions
    ) {
        ArrayList<ITransaction> outputs = new ArrayList<>();
        for (ITransaction transaction : userTransactions) {
            if (transaction.getValue() < 0) {
                outputs.add(transaction);
            }
        }
        return outputs;
    }

    @Override
    public Map<CategoryEnum, Double> getValuesByCategory(
        ArrayList<ITransaction> transactions
    ) {
        Map<CategoryEnum, Double> valuesByCategory = createCategoryMap();
        for (ITransaction transaction : transactions) {
            CategoryEnum category = transaction.getCategory();
            double value = 0.0;
            switch (category) {
                case FOOD:
                    value =
                        Math.abs(transaction.getValue()) +
                        valuesByCategory.get(category);
                    valuesByCategory.replace(category, value);
                    break;
                case CLOTHING:
                    value =
                        Math.abs(transaction.getValue()) +
                        valuesByCategory.get(category);
                    valuesByCategory.replace(category, value);
                    break;
                case HEALTH:
                    value =
                        Math.abs(transaction.getValue()) +
                        valuesByCategory.get(category);
                    valuesByCategory.replace(category, value);
                    break;
                case ENTERTAINMENT:
                    value =
                        Math.abs(transaction.getValue()) +
                        valuesByCategory.get(category);
                    valuesByCategory.replace(category, value);
                    break;
                case PAYMENTS:
                    value =
                        Math.abs(transaction.getValue()) +
                        valuesByCategory.get(category);
                    valuesByCategory.replace(category, value);
                    break;
                case OTHERS:
                    value =
                        Math.abs(transaction.getValue()) +
                        valuesByCategory.get(category);
                    valuesByCategory.replace(category, value);
                    break;
            }
        }
        return valuesByCategory;
    }

    public Map<CategoryEnum, Double> createCategoryMap() {
        Map<CategoryEnum, Double> valuesByCategory = new HashMap<>();
        valuesByCategory.put(CategoryEnum.FOOD, 0.0);
        valuesByCategory.put(CategoryEnum.CLOTHING, 0.0);
        valuesByCategory.put(CategoryEnum.HEALTH, 0.0);
        valuesByCategory.put(CategoryEnum.ENTERTAINMENT, 0.0);
        valuesByCategory.put(CategoryEnum.PAYMENTS, 0.0);
        valuesByCategory.put(CategoryEnum.OTHERS, 0.0);

        return valuesByCategory;
    }

    @Override
    public Pair<CategoryEnum, Double> compareValuesByCategorys(
        ArrayList<ITransaction> transactions
    ) {
        Map<CategoryEnum, Double> valuesByCategory =
            this.getValuesByCategory(transactions);
        Pair<CategoryEnum, Double> highestCategory = new Pair<>(
            CategoryEnum.FOOD,
            0.0
        );
        for (Map.Entry<CategoryEnum, Double> set : valuesByCategory.entrySet()) {
            if (
                Math.abs(set.getValue()) > Math.abs(highestCategory.getValue())
            ) {
                highestCategory = new Pair<>(set.getKey(), set.getValue());
            }
        }
        return highestCategory;
    }

    @Override
    public String convertOutputToString(int userID) throws Exception {
        try {
            Pair<CategoryEnum, Double> highest = getHighestOutputPercentageByCategory(
                userID
            );
            CategoryEnum category = highest.getKey();
            String categoryString = "";
            if (highest.getValue() == 0) {
                return "Você ainda não gastou";
            }
            switch (category) {
                case FOOD:
                    categoryString =
                        "Seus gastos com alimentação representam " +
                        Math.round(Math.abs(highest.getValue())) +
                        "% do total";
                    break;
                case CLOTHING:
                    categoryString =
                        "Seus gastos com vestimenta representam " +
                        Math.round(Math.abs(highest.getValue())) +
                        "% do total";
                    break;
                case HEALTH:
                    categoryString =
                        "Seus gastos com saúde representam " +
                        Math.round(Math.abs(highest.getValue())) +
                        "% do total";
                    break;
                case ENTERTAINMENT:
                    categoryString =
                        "Seus gastos com entretenimento representam " +
                        Math.round(Math.abs(highest.getValue())) +
                        "% do total";
                    break;
                case PAYMENTS:
                    categoryString =
                        "Seus gastos com pagamentos representam " +
                        Math.round(Math.abs(highest.getValue())) +
                        "% do total";
                    break;
                case OTHERS:
                    categoryString =
                        "Seus gastos com outras coisas representam " +
                        Math.round(Math.abs(highest.getValue())) +
                        "% do total";
                    break;
            }
            return categoryString;
        } catch (SQLException error) {
            throw new Exception("Não foi possivel converter para string");
        } catch (ParseException error) {
            throw new Exception("Não foi possivel converter para string");
        }
    }

    @Override
    public String convertEntryToString(int userID) throws Exception {
        try {
            Pair<CategoryEnum, Double> highest = getHighestEntryPercentageByCategory(
                userID
            );
            CategoryEnum category = highest.getKey();
            String categoryString = "";
            if (highest.getValue() == 0.0) {
                return "Você ainda não teve entradas";
            }
            switch (category) {
                case FOOD:
                    categoryString =
                        "Suas entradas com alimentação representam " +
                        Math.round(highest.getValue()) +
                        "% do total";
                    break;
                case CLOTHING:
                    categoryString =
                        "Suas entradas com vestimenta representam " +
                        Math.round(highest.getValue()) +
                        "% do total";
                    break;
                case HEALTH:
                    categoryString =
                        "Suas entradas com saúde representam " +
                        Math.round(highest.getValue()) +
                        "% do total";
                    break;
                case ENTERTAINMENT:
                    categoryString =
                        "Suas entradas com entretenimento representam " +
                        Math.round(highest.getValue()) +
                        "% do total";
                    break;
                case PAYMENTS:
                    categoryString =
                        "Suas entradas com pagamentos representam " +
                        Math.round(highest.getValue()) +
                        "% do total";
                    break;
                case OTHERS:
                    categoryString =
                        "Suas entradas com outras coisas representam " +
                        Math.round(highest.getValue()) +
                        "% do total";
                    break;
            }
            return categoryString;
        } catch (SQLException error) {
            throw new Exception("Não foi possivel converter para string");
        } catch (ParseException error) {
            throw new Exception("Não foi possivel converter para string");
        }
    }

    public String getHint(int userID)
        throws Exception, SQLException, ParseException {
        Pair<CategoryEnum, Double> highest = getHighestOutputPercentageByCategory(
            userID
        );
        String categoryString = "";
        switch (highest.getKey()) {
            case FOOD:
                categoryString =
                    "Observa-se um grande gasto com comida. Logo, seria benéfico comer fora menos vezes ou com intervalos mais espaçados.";
                break;
            case CLOTHING:
                categoryString =
                    "Observa-se um grande gasto com vestimenta. Logo, seria benéfico apostar em peças curingas, que podem ser usadas em diversas combinações.";
                break;
            case HEALTH:
                categoryString =
                    "Observa-se um grande gasto com saúde. Logo, seria benéfico fazer mais exercícios e manter uma alimentação saudável. Se cuida ;)";
                break;
            case ENTERTAINMENT:
                categoryString =
                    "Observa-se um grande gasto com entretenimento. Logo, seria benéfico se entreter assistindo um filme pela internet";
                break;
            case PAYMENTS:
                categoryString =
                    "Observa-se um grande gasto com boletos e contas. Logo, seria benéfico prestar atenção quanto ao gasto exagerado de energia e água";
                break;
            case OTHERS:
                categoryString =
                    "Observa-se um grande gasto com outras coisas. Logo, seria benéfico se policiar ";
                break;
        }
        if (highest.getValue() == 0.0) {
            return "";
        }
        return categoryString;
    }
}
