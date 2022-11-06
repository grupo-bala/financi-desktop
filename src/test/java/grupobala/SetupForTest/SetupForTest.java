package grupobala.SetupForTest;

import grupobala.Entities.Category.CategoryEnum;
import grupobala.Entities.Goal.Goal;
import grupobala.Entities.Goal.IGoal.IGoal;
import grupobala.Entities.Transaction.ITransaction.ITransaction;
import grupobala.Entities.Transaction.Transaction;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class SetupForTest {

    public static void truncateTables() throws SQLException {
        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
        );

        Statement statement = connection.createStatement();

        String[] queries = {
            "TRUNCATE TABLE usuario CASCADE",
            "TRUNCATE TABLE meta CASCADE",
            "TRUNCATE TABLE aulaassistida CASCADE",
            "TRUNCATE TABLE aula CASCADE",
            "TRUNCATE TABLE movimentacao CASCADE",
        };

        for (String query : queries) {
            statement.executeUpdate(query);
        }

        connection.close();
    }

    public static void removeFinanciDB() throws SQLException {
        try {
            Connection rootConnection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/?user=postgres&password=postgres"
            );

            String query = "DROP DATABASE financi";

            rootConnection.createStatement().executeUpdate(query);

            rootConnection.close();
        } catch (Exception e) {
            System.out.println("Financi database already dropped");
        }
    }

    public static int addFinanciUser() throws SQLException {
        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
        );

        Statement statement = connection.createStatement();

        String query =
            "INSERT INTO usuario(nome, nomeusuario, senha, saldo) VALUES ('Financi', 'financi', '1234', 1000)";

        statement.executeUpdate(query);

        query = "SELECT id FROM usuario WHERE nomeusuario = 'financi'";

        ResultSet result = statement.executeQuery(query);

        result.next();

        int financiID = result.getInt("id");

        connection.close();

        return financiID;
    }

    public static ITransaction addDefaultTransaction(int financiUserID)
        throws SQLException {
        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
        );

        Statement statement = connection.createStatement();

        double value = 1000;
        boolean isEntry = value > 0;
        String title = "Teste";
        Calendar calendar = Calendar.getInstance();
        CategoryEnum category = CategoryEnum.OTHERS;

        calendar.set(2022, 9, 21);
        Date date = calendar.getTime();

        DateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd");

        String query = String.format(
            Locale.US,
            "INSERT INTO movimentacao(idusuario, valor, data, idcategoria, titulo, entrada) VALUES (%d, %f, '%s', 6, '%s', '%s')",
            financiUserID,
            value,
            formateDate.format(date),
            title,
            isEntry
        );

        statement.executeUpdate(query);

        query =
            String.format(
                Locale.US,
                "SELECT id FROM movimentacao WHERE idusuario = %d",
                financiUserID
            );

        ResultSet result = statement.executeQuery(query);

        result.next();

        int transactionID = result.getInt("id");

        connection.close();

        ITransaction transaction = new Transaction(
            transactionID,
            1000,
            title,
            category,
            date
        );

        return transaction;
    }

    public static IGoal addDefaultGoal(int financiUserID) throws SQLException {
        Connection connection = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/financi?user=postgres&password=postgres"
        );

        Statement statement = connection.createStatement();
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2022, 9, 21);

        Date date = calendar.getTime();
        String title = "Teste";
        double objective = 1000;
        double idealValuePerMonth = 123;

        DateFormat formateDate = new SimpleDateFormat("yyyy-MM-dd");

        String query = String.format(
            Locale.US,
            "INSERT INTO meta(titulo, idusuario, valormeta, valoratual, datalimite, valoridealpormes) VALUES ('%s', %d, %f, %f, '%s', %f)",
            title,
            financiUserID,
            objective,
            0.0,
            formateDate.format(date),
            idealValuePerMonth
        );

        int updates = statement.executeUpdate(query);

        if (updates == 0) {
            throw new SQLException("Something goes wrong");
        }

        query = String.format(
            Locale.US,
            "SELECT id FROM meta WHERE idusuario = %d AND titulo = '%s'",
            financiUserID,
            title
        );

        ResultSet result = statement.executeQuery(query);

        result.next();

        int goalID = result.getInt("id");

        connection.close();

        IGoal goal = new Goal(goalID, title, objective, calendar.getTime(), idealValuePerMonth);

        return goal;
    }
}
